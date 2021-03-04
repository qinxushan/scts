package pers.hao.scts.auth.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author hujiahao
 */
@ApiModel
@Data
public class Captcha {
    @ApiModelProperty(value = "验证码编号")
    private String key;
    @ApiModelProperty(value = "验证码图片资源URL")
    private String codeUrl;
}
