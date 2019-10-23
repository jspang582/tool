package cn.sgst.tool.common.page;

import java.io.Serializable;
import java.util.List;

/**
 * 分页 Page 对象接口
 * @author: fli
 * @email: fli@sstir.cn
 * @date: 2019/10/15 14:44
 */
public interface IPage<T> extends Serializable {

    /**
     * 当前分页总页数
     */
    default long getPages() {
        if (getSize() == 0) {
            return 0L;
        }
        long pages = getTotal() / getSize();
        if (getTotal() % getSize() != 0) {
            pages++;
        }
        return pages;
    }

    /**
     * 内部什么也不干
     * <p>只是为了 json 反序列化时不报错</p>
     */
    default IPage<T> setPages(long pages) {
        // to do nothing
        return this;
    }

    /**
     * 分页记录列表
     *
     * @return 分页对象记录列表
     */
    List<T> getRecords();

    /**
     * 设置分页记录列表
     */
    IPage<T> setRecords(List<T> records);

    /**
     * 当前满足条件总行数
     *
     * @return 总条数
     */
    long getTotal();

    /**
     * 设置当前满足条件总行数
     */
    IPage<T> setTotal(long total);

    /**
     * 当前分页总页数
     *
     * @return 总页数
     */
    long getSize();

    /**
     * 设置当前分页总页数
     */
    IPage<T> setSize(long size);

    /**
     * 当前页，默认 1
     *
     * @return 当前页
     */
    long getCurrent();

    /**
     * 设置当前页
     */
    IPage<T> setCurrent(long current);





}
