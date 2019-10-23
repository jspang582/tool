package cn.sgst.tool.common.exception;

/**
 * @description: 异常规范
 * @author: fli
 * @email: fli@sstir.cn
 * @date: 2019/7/26 9:34
 */
public interface AbstractBaseExceptionEnum {

    /**
     * 获取异常的状态码
     */
    Integer getCode();

    /**
     * 获取异常的提示信息
     */
    String getMessage();
}
