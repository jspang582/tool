package cn.sgst.tool.common.exception;


/**
 * 定义ResponseEntity枚举异常
 * @author: fli
 * @email: fli@sstir.cn
 * @date: 2019/8/14 13:28
 */
public enum ResponseEntityExceptionEnum implements AbstractBaseExceptionEnum {

    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    UNSUPPORTED_MEDIA_TYPE(415, "Unsupported Media Type"),
    NOT_ACCEPTABLE(406, "Not Acceptable"),


    SERVICE_UNAVAILABLE(503, "Service Unavailable"),
    NOT_FOUND(404, "not found"),

    /**
     * bad request
     */
    MISSING_PATH_VARIABLE(400,"missing path variable"),
    METHOD_ARGUMENT_NOT_VALID(400,"参数校验失败"),
    TYPE_MIS_MATCH(400,"参数类型不匹配"),
    MISSING_SERVLET_REQUEST_PARAMETER_ERROR(400,"缺少必要参数"),
    SERVLET_REQUEST_BINDING(400,"servelt request binding error"),
    CONVERSION_NOT_SUPPORTED(400,"conversion not supported"),
    HTTP_MESSAGE_NOT_READABLE(400,"http message not readable"),
    HTTP_MESSAGE_NOT_WRITABLE(400,"http message not writeable"),
    MISSING_SERVLET_REQUEST_PART(400,"missing servlet request part"),
    BINDING_EXCEPTION(400,"binding error"),
    /**
     * 其它请求异常
     */
    BAD_REQUEST(400,"请求异常"),
    ;



    ResponseEntityExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;
    private String message;

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
