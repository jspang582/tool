package cn.sgst.tool.common.response;

/**
 *
 * @author: fli
 * @email: fli@sstir.cn
 * @date: 2019/7/26 11:06
 */
public class SuccessResponse extends Response{

    public SuccessResponse() {
        super(true, DEFAULT_SUCCESS_CODE, DEFAULT_SUCCESS_MESSAGE, null);
    }

    public SuccessResponse(Object object) {
        super(true, DEFAULT_SUCCESS_CODE, DEFAULT_SUCCESS_MESSAGE, object);
    }

    public SuccessResponse(Integer code, String message, Object object) {
        super(true, code, message, object);
    }
}
