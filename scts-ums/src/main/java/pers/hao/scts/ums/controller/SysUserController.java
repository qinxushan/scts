package pers.hao.scts.ums.controller;


import me.zhyd.oauth.model.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.hao.scts.common.util.Result;
import pers.hao.scts.ums.service.ISysUserService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hujiahao
 * @since 2021-02-01
 */
@RestController
@RequestMapping("/sys-users")
public class SysUserController {

    ISysUserService sysUserService;

    @Autowired
    public void setSysUserService(ISysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    @PostMapping()
    public Boolean insertSysUserAuth(@RequestBody AuthUser authUser){
        return true;
    }

    @GetMapping("/{auth-user-id}/real-name")
    public Result<String> getUserRealName(@PathVariable("auth-user-id") Integer authUserId){
        String userRealName = sysUserService.getUserRealName(authUserId);

        return Result.success(userRealName);
    }
}

