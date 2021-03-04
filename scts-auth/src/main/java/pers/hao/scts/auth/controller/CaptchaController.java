package pers.hao.scts.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.hao.scts.auth.service.ICaptchaService;
import pers.hao.scts.auth.entity.Captcha;
import pers.hao.scts.common.util.Result;

/**
 * @author hujiahao
 */
@RequestMapping("/captcha")
@RestController
public class CaptchaController {

    private ICaptchaService captchaService;

    @Autowired
    public void setCaptchaService(ICaptchaService captchaService) {
        this.captchaService = captchaService;
    }

    @GetMapping("/image")
    public Result<?> authCode() {
        Captcha code = captchaService.getCode();
        return Result.success(code);
    }


}
