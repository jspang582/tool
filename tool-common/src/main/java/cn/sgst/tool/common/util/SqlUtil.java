package cn.sgst.tool.common.util;

import java.util.List;

/**
 * sql语句工具类
 * @author: fli
 * @email: fli@sstir.cn
 * @date: 2019/7/26 10:04
 */
public class SqlUtil {
    /**
     * @Description 根据集合的大小，输出相应个数"?"
     */
    public static String parse(List<?> list) {
        String str = "";
        if (list != null && list.size() > 0) {
            str = str + "?";
            for (int i = 1; i < list.size(); i++) {
                str = str + ",?";
            }
        }
        return str;
    }

}
