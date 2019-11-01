package cn.sgst.tool.web.core.error;

import cn.sgst.tool.common.exception.ServiceException;
import cn.sgst.tool.web.core.validation.InvalidResult;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.ServletException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
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
 * </ul>
 * @author: fli
 * @email: fli@sstir.cn
 * @date: 2019/10/17 11:12
 * @see ErrorAttributes
 * @see CustomExceptionHandler
 */
public final class CustomErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = new LinkedHashMap<>();
        addStatus(errorAttributes, webRequest);
        addErrorDetails(errorAttributes, webRequest, includeStackTrace);
        return errorAttributes;
    }

    private void addStatus(Map<String, Object> errorAttributes,
                           WebRequest webRequest) {
        Integer status = getAttribute(webRequest,
                "javax.servlet.error.status_code");
        if (status == null) {
            errorAttributes.put("status", 999);
            errorAttributes.put("error", "None");
            return;
        }
        errorAttributes.put("status", status);
        Throwable error = getError(webRequest);
        // 如果是自定义业务异常
        if(error instanceof ServiceException){
            errorAttributes.put("error", ((ServiceException) error).getErrorMessage());
            return;
        }
        try {
            errorAttributes.put("error", HttpStatus.valueOf(status).getReasonPhrase());
        }
        catch (Exception ex) {
            // Unable to obtain a reason
            errorAttributes.put("error", "Http Status " + status);
        }
    }

    private void addErrorDetails(Map<String, Object> errorAttributes,
                                 WebRequest webRequest, boolean includeStackTrace) {
        Throwable error = getError(webRequest);
        if (error != null) {
            if((error instanceof ServiceException)){
                return;
            }
            while (error instanceof ServletException && error.getCause() != null) {
                error = ((ServletException) error).getCause();
            }
            addErrorMessage(errorAttributes, error);
            if (includeStackTrace) {
                addStackTrace(errorAttributes, error);
            }
        }
        Object message = getAttribute(webRequest, "javax.servlet.error.message");
        if ((!StringUtils.isEmpty(message) || errorAttributes.get("message") == null)
                && !(error instanceof BindingResult)) {
            errorAttributes.put("message",
                    StringUtils.isEmpty(message) ? "No message available" : message);
        }
    }

    private void addErrorMessage(Map<String, Object> errorAttributes, Throwable error) {
        BindingResult result = extractBindingResult(error);
        if (result == null) {
            errorAttributes.put("message", error.getMessage());
            return;
        }
        if (result.hasErrors()) {
            //按需重新封装需要返回的错误信息
            List<InvalidResult> invalidArguments = new ArrayList<>();
            //解析原错误信息，封装后返回，此处返回非法的字段名称，原始值，错误信息
            for (FieldError fieldError : result.getFieldErrors()) {
                InvalidResult invalidArgument = new InvalidResult();
                invalidArgument.setErrorMessage(fieldError.getDefaultMessage());
                invalidArgument.setField(fieldError.getField());
                invalidArgument.setRejectedValue(fieldError.getRejectedValue());
                invalidArguments.add(invalidArgument);
            }
            errorAttributes.put("message",invalidArguments);
        }
        else {
            errorAttributes.put("message", "No errors");
        }
    }

    private BindingResult extractBindingResult(Throwable error) {
        if (error instanceof BindingResult) {
            return (BindingResult) error;
        }
        if (error instanceof MethodArgumentNotValidException) {
            return ((MethodArgumentNotValidException) error).getBindingResult();
        }
        return null;
    }

    private void addStackTrace(Map<String, Object> errorAttributes, Throwable error) {
        StringWriter stackTrace = new StringWriter();
        error.printStackTrace(new PrintWriter(stackTrace));
        stackTrace.flush();
        errorAttributes.put("trace", stackTrace.toString());
    }


    @SuppressWarnings("unchecked")
    private <T> T getAttribute(RequestAttributes requestAttributes, String name) {
        return (T) requestAttributes.getAttribute(name, RequestAttributes.SCOPE_REQUEST);
    }

}
