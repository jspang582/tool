package cn.sgst.tool.common.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * 线程池工具类
 * @author: fli
 * @email: fli@sstir.cn
 * @date: 2019/7/29 14:22
 */
public class ThreadPoolExecutorUtil {

    private static final ScheduledExecutorService ses = Executors.newScheduledThreadPool(10);


    public static void schedule(Runnable command, long delay, TimeUnit unit) {
        ses.schedule(command, delay, unit);
    }

    public static void execute(Runnable command) {
        schedule(command, 0, NANOSECONDS);
    }
}
