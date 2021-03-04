package pers.hao.scts.ums.constant;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = UmcProperties.PREFIX)
@Data
public class UmcProperties {

    public static final String PREFIX = "init-user";

    /**
     * 默认用户的角色
     */
    private Integer role;
}
