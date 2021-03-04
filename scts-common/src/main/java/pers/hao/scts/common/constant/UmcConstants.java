package pers.hao.scts.common.constant;

/**
 * 微服务UMC常量
 * @author hujiahao
 */
public interface UmcConstants {
    /**
     * 用户名密码登录类型
     */
    String USERNAME_PASSWORD_TYPE = "SYSTEM";

    /**
     * Redis 储存所有用户权限key
     */
    String KEY_ALL_PERMISISON = "umc:permissions";

    /**
     * Redis 储存用户权限key
     */
    String KEY_USER_PERMISISON = "umc:permissions:%d";

    /**
     * 权限资源类型
     */
    String RESOURCE_TYPE_MENU = "menu";
    String RESOURCE_TYPE_BTN = "button";

    /**
     * 请求方法
     */
    String RESOURCE_REQUEST_METHOD_GET = "GET";
    String RESOURCE_REQUEST_METHOD_PUT = "PUT";
    String RESOURCE_REQUEST_METHOD_DELETE = "DELETE";
    String RESOURCE_REQUEST_METHOD_POST = "POST";

    String CHECK_AUTHORITY_REQUEST_URL = "http://scts-ums/authorities/validatedAuthority";


}
