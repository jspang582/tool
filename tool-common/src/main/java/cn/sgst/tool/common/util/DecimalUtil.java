package cn.sgst.tool.common.util;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * decimal变量获取
 *
 */
public class DecimalUtil {

    /**
     * 获取Object的值
     * @author: fli
     * @email: fli@sstir.cn
     * @date: 2019/7/26 9:31
     */
    public static Long getLong(Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof BigDecimal) {
            return ((BigDecimal) object).longValue();
        }
        if (object instanceof BigInteger) {
            return ((BigInteger) object).longValue();
        }
        if (object instanceof Long) {
            return ((Long) object);
        }
        return null;
    }

}
