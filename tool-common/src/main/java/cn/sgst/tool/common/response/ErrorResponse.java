package cn.sgst.tool.common.response;

import cn.sgst.tool.common.exception.AbstractBaseExceptionEnum;
import lombok.Data;

/**
 * @author: fli
 * @email: fli@sstir.cn
 * @date: 2019/7/26 11:06
 */
@Data
public class ErrorResponse extends Response{



    public ErrorResponse(String message) {
        super(false, DEFAULT_ERROR_CODE, message, null);
    }

    public ErrorResponse(Integer code, String message) {
        super(false, code, message, null);
    }

    public ErrorResponse(Integer code, String message, Object object) {
        super(false, code, message, object);
    }

    public ErrorResponse(AbstractBaseExceptionEnum exceptionEnum){
        super(false,exceptionEnum.getCode(),exceptionEnum.getMessage(),null);
    }
}
