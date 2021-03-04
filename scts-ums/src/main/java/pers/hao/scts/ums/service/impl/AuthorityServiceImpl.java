package pers.hao.scts.ums.service.impl;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import pers.hao.scts.common.constant.UmcConstants;
import pers.hao.scts.entity.ums.*;
import pers.hao.scts.ums.service.*;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author hujiahao
 */
@Service
@Slf4j
public class AuthorityServiceImpl implements IAuthorityService {

    private IMenuService menuService;

    private IPageElementService pageElementService;

    private IRoleService roleService;

    private IRoleAuthorityService roleAuthorityService;

    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    public void setMenuService(IMenuService menuService) {
        this.menuService = menuService;
    }

    @Autowired
    public void setPageElementService(IPageElementService pageElementService) {
        this.pageElementService = pageElementService;
    }

    @Autowired
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Autowired
    public void setRoleService(IRoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setRoleAuthorityService(IRoleAuthorityService roleAuthorityService) {
        this.roleAuthorityService = roleAuthorityService;
    }

    @Override
    public List<Authority> getAllAuthority() {
        String key = UmcConstants.KEY_ALL_PERMISISON;
        String permissionInfos = stringRedisTemplate.opsForValue().get(key);
        if (permissionInfos == null || StringUtils.isBlank(permissionInfos)) {
            // 权限总和
            List<Authority> result = new ArrayList<>();
            // 菜单权限
            List<Menu> menus = menuService.list();
            menus.forEach(menu -> {
                Authority authority = new Authority();
                BeanUtils.copyProperties(menu, authority);
                authority.setMethod(UmcConstants.RESOURCE_REQUEST_METHOD_GET);
                result.add(authority);
            });
            // 页面权限
            List<PageElement> elements = pageElementService.list();
            elements.forEach(element -> {
                Authority authority = new Authority();
                BeanUtils.copyProperties(element, authority);
                result.add(authority);
            });
            permissionInfos = JSONUtil.toJsonStr(result);
            stringRedisTemplate.opsForValue().set(key, permissionInfos, 12, TimeUnit.HOURS);
        }
        return JSONUtil.toList(permissionInfos, Authority.class);
    }

    @Override
    public Mono<CheckedAuthority> checkUserAuthority(List<String> authorityCodeList , String requestUri, String requestMethod) {
        // 如果获取不到权限列表，则为空集合
        if (authorityCodeList == null) {
            authorityCodeList = new ArrayList<>();
        }
        CheckedAuthority checkedAuthority = new CheckedAuthority();
        List<Authority> authorities = this.getAllAuthority();
        String recoveryRequestUri =  requestUri.replaceAll("%2F", "/");
        // 判断当前访问资源是否有权限控制
        List<Authority> matchAuthorities = authorities.parallelStream().filter(authority -> {
            String uri = authority.getUri();
            if (uri.indexOf("{") > 0) {
                uri = uri.replaceAll("\\{\\*}", "[a-zA-Z\\d]+");
            }
            String regEx = "^" + uri + "$";
            return (Pattern.compile(regEx).matcher(recoveryRequestUri).find())
                    && requestMethod.equals(authority.getMethod());
        }).collect(Collectors.toList());
        // 说明当前访问资源不做权限控制
        if (matchAuthorities.size() == 0) {
            checkedAuthority.setAuthorized(true);
            return Mono.just(checkedAuthority);
        }


        Authority current = null;
        // 判断当前用户是否拥有该访问资源的权限
        for (String authorityCode : authorityCodeList) {
            for (Authority matchAuthority : matchAuthorities) {
                if (authorityCode.equals(matchAuthority.getCode())) {
                    current = matchAuthority;
                    break;
                }
            }
        }
        if (current == null) {
            // 当前用户不拥有该权限
            checkedAuthority.setAuthorized(false);
        } else {
            // 当前用户拥有该资源的访问权限
            checkedAuthority.setAuthorized(true);
            checkedAuthority.setAuthority(current);
        }
        return Mono.just(checkedAuthority);
    }

    public List<String> getAuthorityByUserAuthId(Integer userAuthId) {
        String key = String.format(UmcConstants.KEY_USER_PERMISISON, userAuthId);
        String userPermissions = stringRedisTemplate.opsForValue().get(key);
        if (userPermissions == null || StringUtils.isBlank(userPermissions)) {
            // 查询用户角色编号
            List<Role> roles = roleService.selectRoleByUserId(userAuthId);
            List<Integer> roleIds = roles.stream().map(Role::getId).collect(Collectors.toList());
            // 查询角色权限
            List<String> authorityList = new ArrayList<>();
            roleIds.forEach(roleId -> {
                List<Authority> roleAuthority = roleAuthorityService.getRolePermissionInfo(roleId);
                List<String> authorityCodes = roleAuthority.stream().map(Authority::getCode).collect(Collectors.toList());
                authorityList.addAll(authorityCodes);
            });
            stringRedisTemplate.opsForValue().set(key, JSONUtil.parse(authorityList).toString(), 12, TimeUnit.HOURS);
            return authorityList;
        }
        return JSONUtil.toList(userPermissions, String.class);
    }
}
