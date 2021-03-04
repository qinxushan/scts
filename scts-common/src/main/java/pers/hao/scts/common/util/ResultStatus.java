package pers.hao.scts.common.util;


import lombok.Getter;

@Getter
public enum ResultStatus {

    /**
     * 通用模块异常
     */
    SUCCESS(200, "Success"),
    BAD_REQUEST(400, "Bad Request"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),

    // 授权模块异常 10XX
    TOKEN_NOT_FOUND(1000, "请求头未携带Token信息"),
    TOKEN_IS_EXPIRED(1001, "Token过期异常"),
    USER_NOT_AUTHORIZED(1002, "用户未授权"),

    // 用户模块异常 11XX
    USER_NOT_FOUND(1100, "用户未找到"),
    // 教学模块异常 12XX
    COURSE_CONFLICT(1200, "课程冲突"),
    // 数据库异常 20XX
    ILLEGAL_CHARACTER(2000, "非法字符");

    /** 业务异常码 */
    private final Integer code;
    /** 业务异常信息描述 */
    private final String message;

    ResultStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
