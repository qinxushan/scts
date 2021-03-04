package pers.hao.scts.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.hao.scts.entity.ums.Role;

import java.util.List;

/**
 * <p>
 * 用户角色表 服务类
 * </p>
 *
 * @author hujiahao
 * @since 2020-11-22
 */
public interface IRoleService extends IService<Role> {
    /**
     * 查询角色权限通过用户编号
     *   @param userId 用户编号
     *   @return 用户角色列表
     */
    List<Role> selectRoleByUserId(Integer userId);
}
