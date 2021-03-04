package pers.hao.scts.ums.service;

import pers.hao.scts.entity.ums.Authority;
import pers.hao.scts.entity.ums.CheckedAuthority;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 用户权限
 * @author hujiahao
 */
public interface IAuthorityService {

    /**
     * 获取所有权限资源
     * @return 权限列表
     */
    List<Authority> getAllAuthority();

    /**
     * 获取权限资源通过用户编号
     * @param userAuthId 用户编号
     * @return 用户权限编号
     */
    List<String> getAuthorityByUserAuthId(Integer userAuthId);

    /**
     * 用户权限检验
     * @param authorityCodeList 权限编码
     * @param requestUri 请求路径
     * @param requestMethod 请求方法
     * @return 检验结果
     */
    Mono<CheckedAuthority> checkUserAuthority(List<String> authorityCodeList, String requestUri, String requestMethod);


}
