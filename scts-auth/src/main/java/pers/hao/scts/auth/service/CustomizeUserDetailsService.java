package pers.hao.scts.auth.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author hujiahao
 */
public interface CustomizeUserDetailsService extends UserDetailsService {

    /**
     * 根据编号获取用户信息
     * @param userAuthId 用户编号
     * @return 用户信息
     * @throws UsernameNotFoundException 用户未找到异常
     */
    UserDetails loadUserByUsername(Integer userAuthId);
    /**
     * 根据用户标识与登录类型获取用户信息
     * @param identifier 用户标识
     * @param identityType 登录类型
     * @return 用户信息
     * @throws UsernameNotFoundException 用户未找到异常
     */
    UserDetails loadUserByUsername(String identifier, String identityType);

}
