package pers.hao.scts.common.constant;

/**
 * @author hujiahao
 */
public interface SysConstants {

    /**
     * json类型报文，UTF-8字符集
     */
    String JSON_UTF8 = "application/json;charset=UTF-8";

    /**
     * 模块名的前缀
     */
    String PATH_PREFIX = "/scts";

    /**
     * 索引自1开头检索，跳过第一个字符就是检索的字符的问题
     */
    int FROM_INDEX = 1;

    Integer ACCESS_GATEWAY_FILTER_ORDER =  -1;
}
