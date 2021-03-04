package pers.hao.scts.tms.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pers.hao.scts.common.util.Result;

@FeignClient(value = "scts-ums")
public interface UmsService {

    /**
     * 获取用户真实姓名
     * @param userAuthId 用户编号
     * @return 用户真实姓名
     */
    @GetMapping("/sys-users/{auth-user-id}/real-name")
    Result<String> getUserRealName(@PathVariable("auth-user-id") Integer userAuthId);

}
