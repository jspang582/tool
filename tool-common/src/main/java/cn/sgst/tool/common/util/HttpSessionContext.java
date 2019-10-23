package cn.sgst.tool.common.util;

import javax.servlet.http.HttpSession;

/**
 * 非Controller中获取当前session的工具类
 * @author: fli
 * @email: fli@sstir.cn
 * @date: 2019/7/26 9:53
 */
public class HttpSessionContext {

    private static ThreadLocal<HttpSession> tl = new ThreadLocal<HttpSession>();

    public static void put(HttpSession s) {
        tl.set(s);
    }

    public static HttpSession get() {
        return tl.get();
    }

    public static void remove() {
        tl.remove();
    }
}
