package pers.hao.scts.ums.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户角色关联关系表 前端控制器
 * </p>
 *
 * @author hujiahao
 * @since 2020-11-22
 */
@RestController
@RequestMapping("/user-role-relation")
public class UserRoleRelationController {

    @GetMapping
    public String user(){
        return "principal";
    }
}

