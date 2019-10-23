package cn.sgst.tool.web.core.error;

import cn.sgst.tool.common.exception.ServiceException;
import cn.sgst.tool.common.response.Response;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * Default implementation of {@link ErrorAttributes}. Provides the following attributes
 * when possible:
 * <ul>
 * <li>timestamp - The time that the errors were extracted</li>
 * <li>status - The status code</li>
 * <li>error - The error reason</li>
 * <li>exception - The class name of the root exception (if configured)</li>
 * <li>message - The exception message</li>
 * <li>errors - Any {@link ObjectError}s from a {@link BindingResult} exception
 * <li>trace - The exception stack trace</li>
 * <li>path - The URL path when the exception was raised</li>
 * <li>customErrorMap - 捕获{@link ServiceException}异常时返回的{@link Response}</li>
 * </ul>
 * @author: fli
 * @email: fli@sstir.cn
 * @date: 2019/10/17 11:12
 * @see ErrorAttributes
 * @see CustomExceptionHandler
 */
public final class CustomErrorAttributes extends DefaultErrorAttributes {

    public static final String CUSTOM_ERROR_MAP_NAME = "customErrorMap";

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> map = super.getErrorAttributes(webRequest, includeStackTrace);
        map.put(CUSTOM_ERROR_MAP_NAME,webRequest.getAttribute(CUSTOM_ERROR_MAP_NAME, RequestAttributes.SCOPE_REQUEST));
        return map;
    }
}
