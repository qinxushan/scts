package pers.hao.scts.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.hao.scts.entity.ums.UserRoleRelation;

/**
 * <p>
 * 用户角色关联关系表 服务类
 * </p>
 *
 * @author hujiahao
 * @since 2020-11-22
 */
public interface IUserRoleRelationService extends IService<UserRoleRelation> {

    /**
     * 赋予第三方新注册用户基本角色
     * @param userAuthId 用户authId
     */
    void saveDefaultUserRoleRelation(int userAuthId);

}
