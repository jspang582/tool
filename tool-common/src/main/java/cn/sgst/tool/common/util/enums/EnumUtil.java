package cn.sgst.tool.common.util.enums;


import cn.hutool.core.lang.Assert;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * IEnum枚举工具类
 * </p>
 *
 * @author: fli
 * @email: fli@sstir.cn
 * @date: 2020/9/11 10:08
 */
public class EnumUtil {

    /**
     * 指定类是否为Enum类
     *
     * @param clazz 类
     * @return 是否为Enum类
     */
    public static boolean isEnum(Class<? extends IEnum> clazz) {
        Assert.notNull(clazz, "clazz must not be null");
        return clazz.isEnum();
    }


    /**
     * Enum对象转String，调用{@link Enum#name()} 方法
     *
     * @param e Enum
     * @return name值
     */
    public static String toString(Enum<? extends IEnum> e) {
        return null != e ? e.name() : null;
    }

    /**
     * 获得枚举类中各枚举对象下value的值
     *
     * @param clazz 枚举类
     * @return value值列表
     */
    public static <K, V> List<K> getValues(Class<? extends IEnum<K, V>> clazz) {
        return getEnumConstants(clazz).stream().map(IEnum::getValue).collect(Collectors.toList());
    }


    /**
     * 获得枚举类中各枚举对象下text的值
     *
     * @param clazz 枚举类
     * @return text值列表
     */
    public static <K, V> List<V> getTexts(Class<? extends IEnum<K, V>> clazz) {
        return getEnumConstants(clazz).stream().map(IEnum::getText).collect(Collectors.toList());
    }

    /**
     * 获得枚举类中各枚举对象
     *
     * @param clazz clazz 枚举类
     * @return 枚举对象列表
     */
    public static <K, V> List<IEnum<K, V>> getEnumConstants(Class<? extends IEnum<K, V>> clazz) {
        check(clazz);
        final IEnum<K, V>[] enums = clazz.getEnumConstants();
        if (enums == null) {
            return new ArrayList<>();
        }
        return Arrays.asList(enums);
    }


    /**
     * 根据value获取对应的枚举对象
     *
     * @param value value值
     * @return 枚举对象
     */
    public static <K, V> IEnum<K, V> fromValue(Class<? extends IEnum<K, V>> clazz, Object value) {
        check(clazz);
        List<IEnum<K, V>> enumConstants = getEnumConstants(clazz);
        for (IEnum<K, V> e : enumConstants) {
            if (Objects.equals(value, e.getValue())) {
                return e;
            }
        }
        return null;
    }


    /**
     * 根据text获取对应的枚举对象
     *
     * @param text text值
     * @return 枚举对象
     */
    public static <K, V> IEnum<K, V> fromText(Class<? extends IEnum<K, V>> clazz, Object text) {
        check(clazz);
        List<IEnum<K, V>> enumConstants = getEnumConstants(clazz);
        for (IEnum<K, V> e : enumConstants) {
            if (Objects.equals(text, e.getText())) {
                return e;
            }
        }
        return null;
    }

    /**
     * 根据value获取对应的text
     *
     * @param value value值
     * @return 对应的text
     */
    public static <K, V> V getTextFromValue(Class<? extends IEnum<K, V>> clazz, Object value) {
        IEnum<K, V> e = fromValue(clazz, value);
        return e == null ? null : e.getText();
    }


    /**
     * 根据text获取对应的value
     *
     * @param text text值
     * @return 对应的value值
     */
    public static <K, V> K getValueFromText(Class<? extends IEnum<K, V>> clazz, Object text) {
        IEnum<K, V> e = fromText(clazz, text);
        return e == null ? null : e.getValue();
    }


    /**
     * 校验是否是枚举类型
     *
     * @param clazz 类
     */
    private static void check(Class<? extends IEnum> clazz) {
        Assert.isTrue(isEnum(clazz), "clazz must be enum type");
    }


}
