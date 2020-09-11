package cn.sgst.tool.common.util.enums;

/**
 * 定义枚举规范
 *
 * @author: fli
 * @email: fli@sstir.cn
 * @date: 2020/3/27 10:36
 */
public interface IEnum<K,V> {

    K getValue();

    V getText();
}
