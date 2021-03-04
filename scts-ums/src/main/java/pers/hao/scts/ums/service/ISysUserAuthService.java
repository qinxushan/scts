package pers.hao.scts.ums.service;

import me.zhyd.oauth.model.AuthUser;
import com.baomidou.mybatisplus.extension.service.IService;
import pers.hao.scts.entity.ums.SysUserAuth;


/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author hujiahao
 * @since 2020-11-22
 */
public interface ISysUserAuthService extends IService<SysUserAuth> {

    /**
     * 通过用户标识与登录类型获取用户授权信息
     *
     * @param userAuthId   用户标识
     * @return 授权信息
     */
    SysUserAuth getSysUserAuth(Integer userAuthId);
    /**
     * 通过用户标识与登录类型获取用户授权信息
     *
     * @param identifier   用户标识
     * @param identityType 登录类型
     * @return 授权信息
     */
    SysUserAuth getSysUserAuth(String identifier, String identityType);

    /**
     * 通过第三方用户信息注册系统用户
     * @param authUser 第三方用户信息
     * @return 用户编号
     */
    Integer registerSysUserAuth(AuthUser authUser);


}