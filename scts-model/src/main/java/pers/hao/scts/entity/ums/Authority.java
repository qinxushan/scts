package pers.hao.scts.entity.ums;

import lombok.Data;

import java.io.Serializable;

/**
 * @author hujiahao
 */
@Data
public class Authority implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;
    private String type;
    private String uri;
    private String method;
    private String name;
}
