package pers.hao.scts.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import me.zhyd.oauth.model.AuthUser;
import pers.hao.scts.entity.ums.SysUser;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hujiahao
 * @since 2021-02-01
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * 储存第三方信息
     * @param userId 用户编号
     * @param authUser 第三方用户信息
     */
    void saveSocialUser(Integer userId, AuthUser authUser);

    /**
     * 获取用户真实姓名
     * @param userAuthId 用户编号
     * @return 用户真实姓名
     */
    String getUserRealName(Integer userAuthId);
}
