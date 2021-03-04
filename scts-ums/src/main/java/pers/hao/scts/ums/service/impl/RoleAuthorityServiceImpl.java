package pers.hao.scts.ums.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.hao.scts.common.constant.UmcConstants;
import pers.hao.scts.entity.ums.Authority;
import pers.hao.scts.entity.ums.Menu;
import pers.hao.scts.entity.ums.PageElement;
import pers.hao.scts.entity.ums.RoleAuthority;
import pers.hao.scts.ums.mapper.RoleAuthorityMapper;
import pers.hao.scts.ums.service.IMenuService;
import pers.hao.scts.ums.service.IPageElementService;
import pers.hao.scts.ums.service.IRoleAuthorityService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hujiahao
 * @since 2021-02-06
 */
@Service
public class RoleAuthorityServiceImpl extends ServiceImpl<RoleAuthorityMapper, RoleAuthority> implements IRoleAuthorityService {

    private IMenuService menuService;

    private IPageElementService pageElementService;

    @Autowired
    public void setMenuService(IMenuService menuService) {
        this.menuService = menuService;
    }

    @Autowired
    public void setPageElementService(IPageElementService pageElementService) {
        this.pageElementService = pageElementService;
    }

    @Override
    public List<Authority> getRolePermissionInfo(Integer roleId) {
        QueryWrapper<RoleAuthority> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleId);
        List<RoleAuthority> roleAuthorities = this.baseMapper.selectList(queryWrapper);
        return roleAuthorities.stream().map(roleAuthority -> {
                Authority authority = new Authority();
                String authorityType = roleAuthority.getAuthorityType();
                if (UmcConstants.RESOURCE_TYPE_MENU.equals(authorityType)) {
                    Menu menu = menuService.getById(roleAuthority.getId());
                    BeanUtils.copyProperties(menu, authority);
                } else if (UmcConstants.RESOURCE_TYPE_BTN.equals(authorityType)) {
                    PageElement pageElement = pageElementService.getById(roleAuthority.getId());
                    BeanUtils.copyProperties(pageElement, authority);
                }
                return authority;
            }).collect(Collectors.toList());
    }
}
