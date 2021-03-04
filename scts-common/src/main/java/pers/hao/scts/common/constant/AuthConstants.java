package pers.hao.scts.common.constant;

public interface AuthConstants {

    /**
     * JWT存储权限前缀
     */
    String AUTHORITY_PREFIX = "ROLE_";

    /**
     * JWT存储权限属性
     */
    String AUTHORITY_CLAIM_NAME = "authorities";

    /**
     * 认证信息Http请求头
     */
    String HEADER_TOKEN = "Authorization";

    /**
     * JWT令牌前缀
     */
    String JWT_TOKEN_PREFIX = "Bearer ";

    /**
     * JWT载体key
     */
    String JWT_PAYLOAD_KEY = "payload";

    /**
     * Redis缓存权限规则key
     */
    String RESOURCE_ROLES_KEY = "auth:resourceRoles";

    /**
     * Redis缓存token信息前缀
     */
    String RESOURCE_TOKEN_PREFIX = "auth-token:";

    String ACCESS = "access:";
    String AUTH_TO_ACCESS = "auth_to_access:";
    String AUTH = "auth:";
    String REFRESH_AUTH = "refresh_auth:";
    String ACCESS_TO_REFRESH = "access_to_refresh:";
    String REFRESH = "refresh:";
    String REFRESH_TO_ACCESS = "refresh_to_access:";
    String CLIENT_ID_TO_ACCESS = "client_id_to_access:";
    String UNAME_TO_ACCESS = "uname_to_access:";


    /**
     * 黑名单token前缀
     */
    String TOKEN_BLACKLIST_PREFIX = "auth:token:blacklist:";

    String CLIENT_DETAILS_FIELDS = "client_id, CONCAT('{noop}',client_secret) as client_secret, resource_ids, scope, "
            + "authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, "
            + "refresh_token_validity, additional_information, autoapprove";

    String BASE_CLIENT_DETAILS_SQL = "select " + CLIENT_DETAILS_FIELDS + " from oauth_client_details";

    String FIND_CLIENT_DETAILS_SQL = BASE_CLIENT_DETAILS_SQL + " order by client_id";

    String SELECT_CLIENT_DETAILS_SQL = BASE_CLIENT_DETAILS_SQL + " where client_id = ?";

    /**
     * 密码加密方式
     */
    String BCRYPT = "{bcrypt}";

    String JWT_USER_ID_KEY = "id";

    String JWT_CLIENT_ID_KEY = "client_id";
    /**
     * 后台管理接口路径匹配
     */
    String ADMIN_URL_PATTERN ="/scts-oauth/**" ;

    /**
     * Reids 验证码key前缀
     */
    String REDIS_CAPTCHA_PREFIX = "scts.captcha.";

    /**
     * 传参验证码key
     */
    String CAPTCHA_KEY = "key";

    /**
     * 传参验证码值
     */
    String CAPTCHA_VALUE = "code";

    /**
     * openId参数名
     */
    String PARAMETER_NAME_OPEN_ID = "openId";

    /**
     * providerId参数名
     */
    String PARAMETER_NAME_PROVIDER_ID = "providerId";

    /**
     * 第三方登录路径
     */
    String SOCIAL_LOGIN_URL = "/auth/callback/**";

}
