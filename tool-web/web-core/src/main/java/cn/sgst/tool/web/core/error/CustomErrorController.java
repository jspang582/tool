package cn.sgst.tool.web.core.error;

import cn.sgst.tool.common.response.Response;
import cn.sgst.tool.common.util.RenderUtil;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.server.AbstractServletWebServerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import static cn.sgst.tool.web.core.error.CustomErrorAttributes.CUSTOM_ERROR_MAP_NAME;

/**
 * 装配自定义全局异常处理器替换Spring Boot对错误的处理
 * Basic global error {@link Controller}, rendering {@link ErrorAttributes}. More specific
 * errors can be handled either using Spring MVC abstractions (e.g.
 * {@code @ExceptionHandler}) or by adding servlet
 * {@link AbstractServletWebServerFactory#setErrorPages server error pages}.
 * @author: fli
 * @email: fli@sstir.cn
 * @date: 2019/10/17 10:57
 * @see ErrorAttributes
 * @see ErrorProperties
 * @see BasicErrorController
 */
@Controller
public final class CustomErrorController extends AbstractErrorController {

    private static final String ERROR_PATH = "/error";

    public CustomErrorController(ErrorAttributes errorAttributes, ErrorViewResolver errorViewResolver) {
        super(errorAttributes,Arrays.asList(errorViewResolver));
    }


    @RequestMapping(value = {ERROR_PATH},produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView errorHtml(HttpServletRequest request,
                                  HttpServletResponse response) {

        HttpStatus status = getStatus(request);
        Map<String, Object> model = Collections.unmodifiableMap(getErrorAttributes(
                request, false));
        response.setStatus(status.value());
        ModelAndView modelAndView = resolveErrorView(request, response, status, model);
        if(modelAndView != null){
            return modelAndView;
        }else {
            RenderUtil.renderJson(response,this.error(request, response));
            return null;
        }
    }

    @RequestMapping(value = {ERROR_PATH})
    @ResponseBody
    public Response error(HttpServletRequest request,HttpServletResponse response)
    {
        response.setStatus(HttpStatus.OK.value());
        Map<String, Object> body = getErrorAttributes(request,
               false);
        Response errorResponse = (Response)body.get(CUSTOM_ERROR_MAP_NAME);
        return (errorResponse != null) ? errorResponse :Response.error((int)body.get("status"),(String) body.get("error"));
    }




    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
