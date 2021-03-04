package pers.hao.scts.ums.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.hao.scts.common.util.Result;
import pers.hao.scts.entity.ums.CheckedAuthority;
import pers.hao.scts.ums.service.IAuthorityService;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/authorities")
public class AuthorityController {

    IAuthorityService authorityService;

    @Autowired
    public void setAuthorityService(IAuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    @GetMapping("/user-auths/{userAuthId}")
    Result<List<String>> getAuthorityByUserAuthId(@PathVariable Integer userAuthId) {
        List<String> authorities = authorityService.getAuthorityByUserAuthId(userAuthId);
        return Result.success(authorities);
    }

    @GetMapping("/validatedAuthority")
    Mono<CheckedAuthority> checkUserAuthority(@RequestParam(required = false) List<String> authorityCodeList,
                                              @RequestParam String requestUri,
                                              @RequestParam String requestMethod) {
        return authorityService.checkUserAuthority(authorityCodeList, requestUri, requestMethod);
    }

}
