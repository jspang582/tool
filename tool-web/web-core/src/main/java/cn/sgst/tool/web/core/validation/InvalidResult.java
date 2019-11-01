package cn.sgst.tool.web.core.validation;

import lombok.Data;

/**
 * 参数校验异常包装
 *
 * @author: fli
 * @email: fli@sstir.cn
 * @date: 2019/11/1 17:23
 */
@Data
public class InvalidResult {

    private String field;
    private Object rejectedValue;
    private String errorMessage;
}
