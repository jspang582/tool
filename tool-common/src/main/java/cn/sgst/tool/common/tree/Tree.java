package cn.sgst.tool.common.tree;

import java.util.List;

/**
 * 构造树节点的接口规范
 * @author: fli
 * @email: fli@sstir.cn
 * @date: 2019/7/26 10:17
 */
public interface Tree {
    /**
     * 获取节点id
     */
    String getNodeId();

    /**
     * 获取节点父id
     */
    String getNodeParentId();

    /**
     * 设置children
     */
    void setChildrenNodes(List childrenNodes);
}
