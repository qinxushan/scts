package pers.hao.scts.auth.service.impl;

import com.wf.captcha.SpecCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import pers.hao.scts.common.constant.AuthConstants;
import pers.hao.scts.auth.entity.Captcha;
import pers.hao.scts.auth.service.ICaptchaService;

import java.time.Duration;
import java.util.UUID;

/**
 * 验证码业务类
 * @author hujiahao
 */
@Service
public class CaptchaServiceImpl implements ICaptchaService {

    @Value("${auth.captcha.cache-time}")
    private String captchaCacheTime;

    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }


    /**
     * 获取验证码
     *
     * @return Result
     */
    @Override
    public Captcha getCode() {
        // 生成验证码编号
        Captcha captcha = new Captcha();
        String key = UUID.randomUUID().toString().replace("-","");
        SpecCaptcha specCaptcha = new SpecCaptcha(120, 40);
        String text = specCaptcha.text();
        captcha.setKey(key);
        captcha.setCodeUrl(specCaptcha.toBase64());
        // 储存验证码信息
        String cacheKey = AuthConstants.REDIS_CAPTCHA_PREFIX + key;
        stringRedisTemplate.opsForValue().set(cacheKey, text, Duration.ofMinutes(Long.parseLong(captchaCacheTime)));
        return captcha;
    }
}
