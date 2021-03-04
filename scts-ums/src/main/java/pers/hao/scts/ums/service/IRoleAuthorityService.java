package pers.hao.scts.ums.service;


import com.baomidou.mybatisplus.extension.service.IService;
import pers.hao.scts.entity.ums.Authority;
import pers.hao.scts.entity.ums.RoleAuthority;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hujiahao
 * @since 2021-02-06
 */
public interface IRoleAuthorityService extends IService<RoleAuthority> {

    /**
     * 获取角色对应权限
     * @param roleId 角色编号
     * @return 权限列表
     */
    List<Authority> getRolePermissionInfo(Integer roleId);

}
