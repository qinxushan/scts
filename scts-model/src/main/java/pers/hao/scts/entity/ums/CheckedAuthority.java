package pers.hao.scts.entity.ums;

import lombok.Data;

/**
 * @author hujiahao
 */
@Data
public class CheckedAuthority {
    /**
     * 用户权限
     */
    private Authority authority;

    /**
     * 是否拥有此权限
     */
    private Boolean authorized;
}
