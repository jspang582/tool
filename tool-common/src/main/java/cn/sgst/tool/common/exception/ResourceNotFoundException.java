package cn.sgst.tool.common.exception;


/**
 * 自定义404异常
 * @author: fli
 * @email: fli@sstir.cn
 * @date: 2019/7/26 13:49
 */
public class ResourceNotFoundException extends ServiceException {

    private static final int ERROR_CODE = 404;

    public ResourceNotFoundException() {
        super(ERROR_CODE, "请求资源不存在");
    }

    public ResourceNotFoundException(String errorMessage) {
        super(ERROR_CODE, errorMessage);
    }

    /**
     * 不拷贝栈信息，提高性能
     *
     */
    @Override
    public synchronized Throwable fillInStackTrace() {
        return null;
    }

    @Override
    public Integer getCode() {
        return super.getCode();
    }

    @Override
    public void setCode(Integer code) {
        super.setCode(code);
    }

    @Override
    public String getErrorMessage() {
        return super.getErrorMessage();
    }

    @Override
    public void setErrorMessage(String errorMessage) {
        super.setErrorMessage(errorMessage);
    }
}
