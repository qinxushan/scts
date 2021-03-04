package pers.hao.scts.ums.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import me.zhyd.oauth.model.AuthUser;
import pers.hao.scts.entity.ums.SysUser;
import pers.hao.scts.ums.mapper.SysUserMapper;
import pers.hao.scts.ums.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hujiahao
 * @since 2021-02-01
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {


    @Override
    public void saveSocialUser(Integer userId, AuthUser authUser) {
        SysUser sysUser = new SysUser();
        sysUser.setUserId(userId);
        sysUser.setAvatar(authUser.getAvatar());
        sysUser.setNickname(authUser.getNickname());
        sysUser.setGender(Integer.parseInt(authUser.getGender().getCode()));
        this.baseMapper.insert(sysUser);
    }

    @Override
    public String getUserRealName(Integer userAuthId) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUserId, userAuthId);
        queryWrapper.select(SysUser::getRealName);
        return this.baseMapper.selectOne(queryWrapper).getRealName();
    }
}
