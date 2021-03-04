package pers.hao.scts.auth.feign;

import me.zhyd.oauth.model.AuthUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import pers.hao.scts.common.util.Result;
import pers.hao.scts.entity.ums.SysUserAuth;

import java.util.List;

/**
 * 用户管理中心服务
 * @author hujiahao
 */
@FeignClient(value = "scts-ums")
public interface UmsService {

    /**
     * 通过用户编号获取用户授权信息
     * @param userAuthId 用户编号
     * @return 授权信息
     */
    @GetMapping("/user-auths/{user-auth-id}")
    Result<SysUserAuth> getSysUserAuth(@PathVariable("user-auth-id") Integer userAuthId);


    /**
     * 通过用户标识与登录类型获取用户授权信息
     * @param identifier 用户标识
     * @param identityType 登录类型
     * @return 授权信息
     */
    @GetMapping("/user-auths")
    Result<SysUserAuth> getSysUserAuth(@RequestParam("identifier") String identifier, @RequestParam("identityType") String identityType);

    /**
     * 通过第三方用户信息注册系统用户
     * @param authUser 第三方用户信息
     * @return 用户编号
     */
    @PostMapping("/user-auths/social-user")
    Result<Integer> registerSysUserAuth(@RequestBody AuthUser authUser);

    /**
     * 获取权限资源通过用户编号
     * @param userAuthId 用户编号
     * @return 用户权限
     */
    @GetMapping("/authorities/user-auths/{userAuthId}")
    Result<List<String>> getAuthorityByUserAuthId(@PathVariable Integer userAuthId);

}
