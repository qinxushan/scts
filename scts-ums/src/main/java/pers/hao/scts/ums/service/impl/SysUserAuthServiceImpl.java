package pers.hao.scts.ums.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import me.zhyd.oauth.model.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import pers.hao.scts.entity.ums.SysUserAuth;
import pers.hao.scts.ums.mapper.SysUserAuthMapper;
import pers.hao.scts.ums.service.ISysUserAuthService;
import pers.hao.scts.ums.service.ISysUserService;
import pers.hao.scts.ums.service.IUserRoleRelationService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author hujiahao
 * @since 2020-11-22
 */
@Service
public class SysUserAuthServiceImpl extends ServiceImpl<SysUserAuthMapper, SysUserAuth> implements ISysUserAuthService {

    private ISysUserService sysUserService;

    private IUserRoleRelationService userRoleRelationService;

    @Autowired
    public void setSysUserService(ISysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    @Autowired
    public void setUserRoleRelationService(IUserRoleRelationService userRoleRelationService) {
        this.userRoleRelationService = userRoleRelationService;
    }

    @Override
    public SysUserAuth getSysUserAuth(Integer userAuthId) {
        return this.baseMapper.selectById(userAuthId);
    }

    @Override
    public SysUserAuth getSysUserAuth(String identifier, String identityType) {
        LambdaQueryWrapper<SysUserAuth> queryUserWrapper = new LambdaQueryWrapper<>();
        queryUserWrapper.eq(SysUserAuth::getIdentifier, identifier);
        queryUserWrapper.eq(SysUserAuth::getIdentityType, identityType);
        return this.baseMapper.selectOne(queryUserWrapper);
    }

    @Override
    public Integer registerSysUserAuth(AuthUser authUser) {
        // 用户密保储存
        SysUserAuth sysUserAuth = new SysUserAuth();
        sysUserAuth.setIdentifier(authUser.getUuid());
        sysUserAuth.setIdentityType(authUser.getSource());
        this.baseMapper.insert(sysUserAuth);
        Integer userId = sysUserAuth.getId();
        // todo 用户基本信息储存
        sysUserService.saveSocialUser(userId, authUser);
        // 用户基本角色赋予
        userRoleRelationService.saveDefaultUserRoleRelation(userId);
        return userId;
    }


}
