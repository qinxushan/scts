package pers.hao.scts.ums.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import pers.hao.scts.entity.ums.UserRoleRelation;
import pers.hao.scts.ums.service.IUserRoleRelationService;
import pers.hao.scts.ums.constant.UmcProperties;
import pers.hao.scts.ums.mapper.UserRoleRelationMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色关联关系表 服务实现类
 * </p>
 *
 * @author hujiahao
 * @since 2020-11-22
 */
@Service
public class UserRoleRelationServiceImpl extends ServiceImpl<UserRoleRelationMapper, UserRoleRelation> implements IUserRoleRelationService {

    private UmcProperties umcProperties;

    @Autowired
    public void setUmcProperties(UmcProperties umcProperties) {
        this.umcProperties = umcProperties;
    }

    @Override
    public void saveDefaultUserRoleRelation(int userAuthId) {
        UserRoleRelation userRoleRelation = new UserRoleRelation();
        userRoleRelation.setUserId(userAuthId);
        userRoleRelation.setRoleId(umcProperties.getRole());
        this.baseMapper.insert(userRoleRelation);
    }
}
