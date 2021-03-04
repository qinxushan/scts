package pers.hao.scts.auth.granter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.UserDeniedAuthorizationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import pers.hao.scts.common.constant.AuthConstants;
import pers.hao.scts.core.redis.util.RedisUtil;

import java.util.Map;

/**
 * 验证码的TokenGranter
 * @author hujiahao
 */
@Slf4j
public class CaptchaTokenGranter extends AbstractTokenGranter {

    private static final String GRANT_TYPE = "captcha";

    private AuthenticationManager authenticationManager;

    private RedisUtil redisUtil;

    public CaptchaTokenGranter(AuthorizationServerTokenServices tokenServices,
                               ClientDetailsService clientDetailsService,
                               OAuth2RequestFactory requestFactory,
                               RedisUtil redisUtil,
                               AuthenticationManager authenticationManager) {
        this(tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
        this.authenticationManager = authenticationManager;
        this.redisUtil = redisUtil;
    }

    protected CaptchaTokenGranter(AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, String grantType) {
        super(tokenServices, clientDetailsService, requestFactory, grantType);
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
        // 获取数据
        Map<String, String> parameters = tokenRequest.getRequestParameters();

        // 验证码验证
        String captchaKey = AuthConstants.REDIS_CAPTCHA_PREFIX + parameters.get(AuthConstants.CAPTCHA_KEY);
        String captchaValue = parameters.get(AuthConstants.CAPTCHA_VALUE);
        String captchaText = (String) redisUtil.get(captchaKey);
        if (StringUtils.isBlank(captchaKey)) {
            throw new UserDeniedAuthorizationException("请输入验证码");
        }
        if (captchaText == null) {
            throw new UserDeniedAuthorizationException("验证码已过期");
        }
        if (!StringUtils.equalsIgnoreCase(captchaValue, captchaText)) {
            throw new UserDeniedAuthorizationException("验证码不正确");
        }
        redisUtil.del(captchaKey);

        // 用户信息认证
        String username = parameters.get("username");
        String password = parameters.get("password");
        Authentication userAuth = new UsernamePasswordAuthenticationToken(username, password);
        userAuth = authenticationManager.authenticate(userAuth);
        if (userAuth == null || !userAuth.isAuthenticated()) {
            throw new InvalidGrantException("Could not authenticate user: " + username);
        }
        OAuth2Request oAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);

        return new OAuth2Authentication(oAuth2Request, userAuth);
    }
}
