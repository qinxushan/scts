package pers.hao.scts.auth.social;

import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.model.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import pers.hao.scts.auth.feign.UmsService;
import pers.hao.scts.auth.service.CustomizeUserDetailsService;
import pers.hao.scts.common.util.Result;
import pers.hao.scts.common.util.ResultStatus;
import pers.hao.scts.token.entity.SocialAuthenticationToken;

import java.util.Objects;

/**
 * 社交登录验证提供者
 * @author hujiahao
 */
@Slf4j
public class SocialAuthenticationProvider implements AuthenticationProvider {

    private CustomizeUserDetailsService customizeUserDetailsService;

    private UmsService umsService;

    @Autowired
    public void setCustomizeUserDetailsService(CustomizeUserDetailsService customizeUserDetailsService) {
        this.customizeUserDetailsService = customizeUserDetailsService;
    }

    @Autowired
    public void setUmcService(UmsService umsService) {
        this.umsService = umsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 验证第三方登录是否在系统注册过
        SocialAuthenticationToken authenticationToken = (SocialAuthenticationToken) authentication;
        AuthUser authUser = (AuthUser) authenticationToken.getPrincipal();
        UserDetails userDetails = customizeUserDetailsService.loadUserByUsername(authUser.getUuid(), authUser.getSource());
        // 用户不存在创建新用户并授予权限
        if (Objects.isNull(userDetails)) {
            Result<Integer> result = umsService.registerSysUserAuth(authUser);
            userDetails = customizeUserDetailsService.loadUserByUsername(result.getData());
        }

        return new SocialAuthenticationToken(userDetails, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return SocialAuthenticationToken.class.isAssignableFrom(aClass);

    }
}
