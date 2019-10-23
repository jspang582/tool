package cn.sgst.tool.common.context;


import cn.sgst.tool.common.request.RequestData;

/**
 * 请求数据的临时容器
 * @author: fli
 * @email: fli@sstir.cn
 * @date: 2019/7/26 11:25
 */
public class RequestDataHolder {

    private static ThreadLocal<RequestData> holder = new ThreadLocal<>();

    public static void put(RequestData s) {
        holder.set(s);
    }

    public static RequestData get() {
        return holder.get();
    }

    public static void remove() {
        holder.remove();
    }
}
