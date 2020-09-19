
package com.dili.account.common.xss;

import com.dili.account.exception.AccountBizException;
import org.apache.commons.lang.StringUtils;

/**
 * SQL过滤
 *
 * @author Mark sunlightcs@gmail.com
 */
public class SQLFilter {

    private static final String[] ILLEGAL_KEYWORDS = {"master", "truncate",
            "insert", "select", "delete",
            "update", "declare", "union",
            "alter", "drop"};

    public static boolean isSqlInject(String str) {
        if (StringUtils.isBlank(str)) {
            return true;
        }
        //去掉'|"|;|\字符
        str = StringUtils.replace(str, "'", "");
        str = StringUtils.replace(str, "\"", "");
        str = StringUtils.replace(str, ";", "");
        str = StringUtils.replace(str, "\\", "");

        //转换成小写
        str = str.toLowerCase();

        //判断是否包含非法字符
        for (String keyword : ILLEGAL_KEYWORDS) {
            if (str.contains(keyword)) {
                return false;
            }
        }
        return true;
    }

    /**
     * SQL注入过滤
     * @param str  待验证的字符串
     */
    public static String sqlInject(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        //去掉'|"|;|\字符
        str = StringUtils.replace(str, "'", "");
        str = StringUtils.replace(str, "\"", "");
        str = StringUtils.replace(str, ";", "");
        str = StringUtils.replace(str, "\\", "");

        //转换成小写
        str = str.toLowerCase();

        //判断是否包含非法字符
        for (String keyword : ILLEGAL_KEYWORDS) {
            if (str.contains(keyword)) {
                throw new AccountBizException("包含非法字符");
            }
        }
        return str;
    }
}
