package pers.hao.scts.auth.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pers.hao.scts.auth.service.CustomizeUserDetailsService;
import pers.hao.scts.auth.feign.UmsService;
import pers.hao.scts.common.constant.UmcConstants;
import pers.hao.scts.common.util.Result;
import pers.hao.scts.common.util.ResultStatus;
import pers.hao.scts.entity.ums.SysUserAuth;


import java.util.List;
import java.util.stream.Collectors;


/**
 * @author hujiahao
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements CustomizeUserDetailsService {

    private UmsService umsService;

    @Autowired
    public void setUmcService(UmsService umsService) {
        this.umsService = umsService;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return this.loadUserByUsername(s, UmcConstants.USERNAME_PASSWORD_TYPE);
    }

    @Override
    public UserDetails loadUserByUsername(Integer userAuthId) {
        Result<SysUserAuth> data = umsService.getSysUserAuth(userAuthId);
        if (!data.getCode().equals(ResultStatus.SUCCESS.getCode())) {
            return null;
        }
        SysUserAuth userAuth = data.getData();
        // 获取该用户的角色权限
        return getUserDetails(userAuth);
    }

    /**
     * 根据用户标识与登录类型获取用户信息
     *
     * @param identifier   用户标识
     * @param identityType 登录类型
     * @return 用户信息
     * @throws UsernameNotFoundException 用户未找到异常
     */
    @Override
    public UserDetails loadUserByUsername(String identifier, String identityType) {
        Result<SysUserAuth> data = umsService.getSysUserAuth(identifier, identityType);
        SysUserAuth userAuth = data.getData();
        return getUserDetails(userAuth);
    }

    public UserDetails getUserDetails(SysUserAuth userAuth) {
        // 获取该用户的角色权限
        List<String> result = umsService.getAuthorityByUserAuthId(userAuth.getId()).getData();
        List<GrantedAuthority> grantedAuthorities = result.stream().map(authorityCode -> (GrantedAuthority) new SimpleGrantedAuthority(authorityCode)).collect(Collectors.toList());
        String credential = userAuth.getCredential() == null ? "" : userAuth.getCredential();
        return new User(userAuth.getIdentifier(),
                credential,
                userAuth.getEnabled(),
                userAuth.getNotExpired(),
                userAuth.getCredentialsNotExpired(),
                userAuth.getAccountNotLocked(),
                grantedAuthorities);
    }
}
