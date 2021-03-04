package pers.hao.scts.ums.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.hao.scts.entity.ums.Role;
import pers.hao.scts.entity.ums.UserRoleRelation;
import pers.hao.scts.ums.service.IRoleService;
import pers.hao.scts.ums.service.IUserRoleRelationService;
import pers.hao.scts.ums.mapper.RoleMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author hujiahao
 * @since 2020-11-22
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    private IUserRoleRelationService userRoleRelationService;

    @Autowired
    public void setUserRoleRelationService(IUserRoleRelationService userRoleRelationService) {
        this.userRoleRelationService = userRoleRelationService;
    }

    @Override
    public List<Role> selectRoleByUserId(Integer userId) {
        // 查询用户角色
        QueryWrapper<UserRoleRelation> queryRoleWrapper = new QueryWrapper<>();
        queryRoleWrapper.eq("user_id", userId);
        List<UserRoleRelation> userRoleRelations = userRoleRelationService.list(queryRoleWrapper);
        if (userRoleRelations.isEmpty()) {
            return new ArrayList<>();
        }

        // 查询角色权限
        List<Integer> roleIds = userRoleRelations.stream().map(UserRoleRelation::getRoleId).collect(Collectors.toList());
        return this.baseMapper.selectBatchIds(roleIds);
    }
}
