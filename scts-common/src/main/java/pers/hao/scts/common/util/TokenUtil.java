package pers.hao.scts.common.util;

public class TokenUtil {

    public static String BEARER = "bearer";
    public static Integer AUTH_LENGTH = 7;

    /**
     * 获取token串
     *
     * @param auth token
     * @return String
     */
    public static String getToken(String auth) {
        if ((auth != null) && (auth.length() > AUTH_LENGTH)) {
            String headStr = auth.substring(0, 6).toLowerCase();
            if (headStr.equalsIgnoreCase(BEARER)) {
                auth = auth.substring(7);
            }
            return auth;
        }
        return null;
    }

}
