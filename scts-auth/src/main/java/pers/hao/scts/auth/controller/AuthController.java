package pers.hao.scts.auth.controller;

import com.xkcoding.justauth.AuthRequestFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.hao.scts.auth.vo.AuthCallbackVo;
import pers.hao.scts.common.util.Result;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author hujiahao
 */
@RestController
@RequestMapping("/auth")
@Slf4j
@AllArgsConstructor
public class AuthController {

    private final AuthRequestFactory authRequestFactory;

    @GetMapping("/social/list")
    public List<String> list() {
        return authRequestFactory.oauthList();
    }

    @GetMapping("/login/{oauthType}")
    public Result<?> renderAuth(@PathVariable String oauthType, HttpServletResponse response) throws IOException {
        AuthRequest authRequest = authRequestFactory.get(oauthType);
        String authorize = authRequest.authorize(oauthType + "::" + AuthStateUtils.createState());
        return Result.success(authorize);
    }

    @RequestMapping("/callback/{oauthType}")
    public Result<?> login(@PathVariable String oauthType, AuthCallback callback) {
        AuthCallbackVo authCallbackVo = new AuthCallbackVo();
        BeanUtils.copyProperties(callback, authCallbackVo);
        authCallbackVo.setOauthType(oauthType);
        return Result.success(authCallbackVo);
    }

}
