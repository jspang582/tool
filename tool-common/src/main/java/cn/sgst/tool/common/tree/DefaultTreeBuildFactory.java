package cn.sgst.tool.common.tree;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: fli
 * @email: fli@sstir.cn
 * @date: 2019/7/26 10:22
 */
@Data
public class DefaultTreeBuildFactory<T extends Tree> extends AbstractTreeBuildFactory<T> {

    /**
     * 顶级节点的父节点id(默认-1)
     */
    private String rootParentId = "-1";

    /**
     * 查询子节点的集合
     *
     * @param totalNodes     所有节点的集合
     * @param node           被查询节点的id
     * @param childNodeLists 被查询节点的子节点集合
     */
    private void buildChildNodes(List<T> totalNodes, T node, List<T> childNodeLists) {
        if (totalNodes == null || node == null) {
            return;
        }

        List<T> nodeSubLists = getSubChildsLevelOne(totalNodes, node);

        if (nodeSubLists.size() == 0) {

        } else {
            for (T nodeSubList : nodeSubLists) {
                buildChildNodes(totalNodes, nodeSubList, new ArrayList<>());
            }
        }

        childNodeLists.addAll(nodeSubLists);
        node.setChildrenNodes(childNodeLists);
    }

    /**
     * 获取子一级节点的集合
     *
     * @param list 所有节点的集合
     * @param node 被查询节点的model
     */
    private List<T> getSubChildsLevelOne(List<T> list, T node) {
        List<T> nodeList = new ArrayList<>();
        for (T nodeItem : list) {
            if (nodeItem.getNodeParentId().equals(node.getNodeId())) {
                nodeList.add(nodeItem);
            }
        }
        return nodeList;
    }

    @Override
    protected List<T> beforeBuild(List<T> nodes) {

        //默认不进行前置处理,直接返回

        return nodes;
    }

    @Override
    protected List<T> executeBuilding(List<T> nodes) {
        for (T treeNode : nodes) {
            this.buildChildNodes(nodes, treeNode, new ArrayList<>());
        }
        return nodes;
    }

    @Override
    protected List<T> afterBuild(List<T> nodes) {

        //去掉所有的二级节点
        ArrayList<T> results = new ArrayList<>();
        for (T node : nodes) {
            if (node.getNodeParentId().equals(rootParentId)) {
                results.add(node);
            }
        }
        return results;
    }
}
