package pers.hao.scts.gateway.filter;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import pers.hao.scts.common.constant.AuthConstants;
import pers.hao.scts.common.constant.UmcConstants;
import pers.hao.scts.common.util.ResponseUtil;
import pers.hao.scts.common.util.ResultStatus;
import pers.hao.scts.core.redis.util.RedisUtil;
import pers.hao.scts.entity.ums.CheckedAuthority;
import pers.hao.scts.common.constant.SysConstants;
import pers.hao.scts.common.util.TokenUtil;
import pers.hao.scts.gateway.props.SystemProperties;
import reactor.core.publisher.Mono;

import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 网关统一的token验证
 *
 * @author pangu
 * @since 1.5.8
 */
@Slf4j
@Component
@AllArgsConstructor
public class AccessGatewayFilter implements GlobalFilter, Ordered {

    private final SystemProperties systemProperties;

    private final RedisUtil redisUtil;

    private final WebClient.Builder webClientBuilder;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 如果未启用网关验证，则跳过
        if (!systemProperties.getEnable()) {
            return chain.filter(exchange);
        }

        //　如果在忽略的url里，则跳过
        String requestUri = replacePrefix(exchange.getRequest().getPath().toString());
        String requestMethod = Objects.requireNonNull(exchange.getRequest().getMethod()).name();
        ServerHttpRequest.Builder mutate = exchange.getRequest().mutate();

        if (ignore(requestUri)) {
            return chain.filter(exchange);
        }

        // 验证token是否在请求头
        ServerHttpResponse resp = exchange.getResponse();
        String headerToken = exchange.getRequest().getHeaders().getFirst(AuthConstants.HEADER_TOKEN);
        if (headerToken == null) {
            return ResponseUtil.unauthorized(resp, ResultStatus.TOKEN_NOT_FOUND);
        }
        // 检验token是否存储在Redis
        String tokenKey = TokenUtil.getToken(headerToken);
        tokenKey = AuthConstants.RESOURCE_TOKEN_PREFIX + AuthConstants.AUTH + tokenKey;
        Boolean tokenExist = redisUtil.hasKey(tokenKey);
        if (!tokenExist) {
            return ResponseUtil.unauthorized(resp, ResultStatus.TOKEN_IS_EXPIRED);
        }

        // 查询用户所拥有的权限
        OAuth2Authentication authentication = (OAuth2Authentication)redisUtil.get(tokenKey);
        log.info(authentication.toString());
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        log.info(String.valueOf(authorities.size()));
        List<String> authorityCodeList = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        // 检验当前路径下用户是否拥有权限
        String validateRequest = null;
        URIBuilder uriBuilder;
        try {
            uriBuilder = new URIBuilder(UmcConstants.CHECK_AUTHORITY_REQUEST_URL);
            if (authorityCodeList.size() != 0) {
                uriBuilder.addParameter("authorityCodeList", StringUtils.join(authorityCodeList, ','));
            }
            uriBuilder.addParameter("requestUri", requestUri);
            uriBuilder.addParameter("requestMethod", requestMethod);
            validateRequest = uriBuilder.build().toString();
            log.info(validateRequest);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        assert validateRequest != null;
        Mono<CheckedAuthority> checkedAuthorityMono = webClientBuilder.build()
                .get()
                .uri(validateRequest)
                .header(AuthConstants.HEADER_TOKEN, headerToken)
                .retrieve()
                .bodyToMono(CheckedAuthority.class);

        // 判断token是否存在于redis,对于只允许一台设备场景适用。
        // 如只允许一台设备登录，需要在登录成功后，查询key是否存在，如存在，则删除此key，提供思路。

        return checkedAuthorityMono.flatMap(checkedAuthority -> {
            if (checkedAuthority.getAuthorized()) {
                if (checkedAuthority.getAuthority() != null) {
                    // todo 设置访问权限
                    log.info("若资源存在则请求设置访问日志");
                }
                ServerHttpRequest build = mutate.build();
                return chain.filter(exchange.mutate().request(build).build());
            } else {
                return ResponseUtil.unauthorized(resp, ResultStatus.USER_NOT_AUTHORIZED);
            }
        });
    }

    /**
     * 检查是否忽略url
     * @param path 路径
     * @return boolean
     */
    private boolean ignore(String path) {
        return systemProperties.getIgnoreUrl().stream()
                .map(url -> url.replace("/**", ""))
                .anyMatch(path::startsWith);
    }

    /**
     * 移除模块前缀
     * @param path 路径
     * @return String
     */
    private String replacePrefix(String path) {
        if (path.startsWith(SysConstants.PATH_PREFIX)) {
            return path.substring(path.indexOf(StringPool.SLASH, SysConstants.FROM_INDEX));
        }
        return path;
    }


    @Override
    public int getOrder() {
        return SysConstants.ACCESS_GATEWAY_FILTER_ORDER;
    }

}

