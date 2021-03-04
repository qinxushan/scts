package pers.hao.scts.auth.service;


import pers.hao.scts.auth.entity.Captcha;

/**
 * 验证码接口
 * @author hujiahao
 */
public interface ICaptchaService {

    /**
     * 获取验证码
     *
     * @return Captcha
     */
    Captcha getCode();
}
