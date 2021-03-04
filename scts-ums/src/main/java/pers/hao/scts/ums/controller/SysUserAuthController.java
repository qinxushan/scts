package pers.hao.scts.ums.controller;



import me.zhyd.oauth.model.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pers.hao.scts.common.util.Result;
import pers.hao.scts.entity.ums.SysUserAuth;
import pers.hao.scts.ums.service.ISysUserAuthService;


/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author hujiahao
 * @since 2020-11-22
 */
@RestController
@RequestMapping("/user-auths")
public class SysUserAuthController {

    ISysUserAuthService userAuthService;

    @Autowired
    public void setUserAuthService(ISysUserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }

    @GetMapping("/{user-auth-id}")
    public Result<SysUserAuth> getSysUserAuth(@PathVariable("user-auth-id") Integer userAuthId){
        SysUserAuth sysUserAuth = userAuthService.getSysUserAuth(userAuthId);
        return Result.success(sysUserAuth);
    }

    @GetMapping()
    public Result<SysUserAuth> getSysUserAuth(@RequestParam("identifier") String identifier, @RequestParam("identityType") String identityType){
        SysUserAuth sysUserAuth = userAuthService.getSysUserAuth(identifier, identityType);
        return Result.success(sysUserAuth);
    }

    @PostMapping("/social-user")
    public Result<Integer> registerSysUserAuth(@RequestBody AuthUser authUser) {
        Integer authUserId = userAuthService.registerSysUserAuth(authUser);
        return Result.success(authUserId);
    }


}

