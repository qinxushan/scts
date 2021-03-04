package pers.hao.scts.auth.vo;

import lombok.Data;

@Data
public class AuthCallbackVo {
    private String code;
    private String state;
    private String oauthType;
}
