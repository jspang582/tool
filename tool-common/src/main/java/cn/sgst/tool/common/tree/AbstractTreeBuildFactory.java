package cn.sgst.tool.common.tree;

import java.util.List;

/**
 * 树构建的抽象类，定义构建tree的基本步骤
 * @author: fli
 * @email: fli@sstir.cn
 * @date: 2019/7/26 10:21
 */
public abstract class AbstractTreeBuildFactory<T> {
    /**
     * 树节点构建整体过程
     *
     */
    public List<T> doTreeBuild(List<T> nodes) {

        //构建之前的节点处理工作
        List<T> readyToBuild = beforeBuild(nodes);

        //具体构建的过程
        List<T> builded = executeBuilding(readyToBuild);

        //构建之后的处理工作
        return afterBuild(builded);
    }

    /**
     * 构建之前的处理工作
     *
     */
    protected abstract List<T> beforeBuild(List<T> nodes);

    /**
     * 具体的构建过程
     *
     */
    protected abstract List<T> executeBuilding(List<T> nodes);

    /**
     * 构建之后的处理工作
     *
     */
    protected abstract List<T> afterBuild(List<T> nodes);
}
