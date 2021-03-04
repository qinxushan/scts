package pers.hao.scts.ums.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pers.hao.scts.entity.ums.Role;
import pers.hao.scts.ums.service.IRoleService;

import java.util.List;

/**
 * <p>
 * 用户角色表 前端控制器
 * </p>
 *
 * @author hujiahao
 * @since 2020-11-22
 */
@RestController
@RequestMapping("/roles")
public class RoleController {

    private IRoleService roleService;

    @Autowired
    public void setRoleService(IRoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/{userId}")
    public List<Role> selectRoleByUserId(@PathVariable Integer userId){
        return roleService.selectRoleByUserId(userId);
    }
}

