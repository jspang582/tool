package cn.sgst.tool.web.core.error;

import cn.sgst.tool.common.exception.ServiceException;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常捕捉
 * @author: fli
 * @email: fli@sstir.cn
 * @date: 2019/10/17 13:17
 */
@ControllerAdvice
@Order
public final class CustomExceptionHandler {


    /**
     * 拦截业务异常
     */
    @ExceptionHandler(ServiceException.class)
    public String handleServiceException(ServiceException ex, HttpServletRequest request) {
        //传入自己的错误代码，必须的，让自定义状态码与http状态码适配
        request.setAttribute("javax.servlet.error.status_code", ex.getCode());
        //转发到springBoot错误处理请求，能适配网页和Ajax的错误处理
        //请求/error后，会进入BasicErrorController(@RequestMapping("${server.error.path:${error.path:/error}}"))
        //页面的数据显示处理是使用：errorAttributes.getErrorAttributes获取显示的，是AbstractErrorController的方法
        //当需要把自己定义的Map错误信息传递到错误提示页面时，
        //可以编写一个自定义错误属性类处理：CustomErrorAttribute，继承DefaultErrorAttributes类，
        //重写getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace)方法
        return "forward:/error";

    }
}
