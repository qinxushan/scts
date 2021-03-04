package pers.hao.scts.auth.granter;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import com.alibaba.fastjson.JSON;
import com.xkcoding.justauth.AuthRequestFactory;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.enums.AuthResponseStatus;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.UserDeniedAuthorizationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import pers.hao.scts.token.entity.SocialAuthenticationToken;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author hujiahao
 */
@Slf4j
public class SocialTokenGranter extends AbstractTokenGranter {

    private static final String GRANT_TYPE = "social";

    private AuthenticationManager authenticationManager;

    private AuthRequestFactory authRequestFactory;

    public SocialTokenGranter(AuthorizationServerTokenServices tokenServices,
                               ClientDetailsService clientDetailsService,
                               OAuth2RequestFactory requestFactory,
                              AuthRequestFactory authRequestFactory,
                              AuthenticationManager authenticationManager) {
        this(tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
        this.authenticationManager = authenticationManager;
        this.authRequestFactory = authRequestFactory;
    }

    protected SocialTokenGranter(AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, String grantType) {
        super(tokenServices, clientDetailsService, requestFactory, grantType);
    }

    @Override
    @SneakyThrows
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
        Map<String, String> parameters = new LinkedHashMap<>(tokenRequest.getRequestParameters());
        String code = parameters.get("code");
        String state = parameters.get("state");
        String oauthType = parameters.get("oauthType");
        if (StringUtils.isBlank(code)) {
            throw new UserDeniedAuthorizationException("未传入请求参数");
        }

        // 第三方登录
        TimeInterval timer = DateUtil.timer();
        AuthRequest authRequest = authRequestFactory.get(oauthType);
        AuthCallback authCallback = AuthCallback.builder().code(code).state(state).build();
        AuthResponse<?> response = authRequest.login(authCallback);
        AuthUser authUser = null;
        if (response.getCode() == AuthResponseStatus.SUCCESS.getCode()) {
            authUser = (AuthUser) response.getData();
        }
        log.info(String.valueOf(timer.intervalRestart()));

        // 系统授权
        Authentication authentication = new SocialAuthenticationToken(authUser);
        authentication = authenticationManager.authenticate(authentication);
        OAuth2Request oAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
        return new OAuth2Authentication(oAuth2Request, authentication);
    }
}
