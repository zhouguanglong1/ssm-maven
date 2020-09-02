package com.zhougl.test.tree;


import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * @author by zhougl
 * @classname TreeUtils
 * @desc TODO
 * @date 2019/5/23 21:52
 */
public class TreeUtils {
    public static List<Object> getTree(List<? extends TreeEntity> list) {
        if (list.isEmpty()) {
            return new ArrayList<>();
        }
        try {
            Method getParentId = TreeEntity.class.getDeclaredMethod("getParentId");
            Method getId = TreeEntity.class.getDeclaredMethod("getId");
            Method getChildren = TreeEntity.class.getDeclaredMethod("getChildren");
            Method setChildren = TreeEntity.class.getDeclaredMethod("setChildren",
                    LinkedHashSet.class);
            List<Object> result = new ArrayList<>();
            for (Object treeEntity : list) {
                if (getChildren.invoke(treeEntity) == null) {
                    setChildren.invoke(treeEntity, new LinkedHashSet<>());
                }
                if (getId.invoke(treeEntity).equals(0L)) {
                    result.add(treeEntity);
                }
                for (Object treeEntity1 : list) {
                    if (!getParentId.invoke(treeEntity1).equals(0L) && getParentId.invoke(treeEntity1).equals(getId.invoke(treeEntity))) {
                        if (getChildren.invoke(treeEntity1) == null) {
                            setChildren.invoke(treeEntity1, new LinkedHashSet<>());
                        }
                        ((LinkedHashSet) getChildren.invoke(treeEntity1)).add(treeEntity);
                    }
                }
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    /**
     * 构造树形结构(递归)
     * @param treeNodes
     * @return
     */
    private List<TreeEntity> buildTree(List<TreeEntity> treeNodes) {
        List<TreeEntity> trees = new ArrayList<>();
        for (TreeEntity treeNode : treeNodes) {
            if (null == treeNode.getParentId() || 0L == treeNode.getParentId()) {
                trees.add(findChildren(treeNode, treeNodes));
            }
        }
        return trees;
    }

    private TreeEntity findChildren(TreeEntity treeNode, List<TreeEntity> treeNodes) {
        for (TreeEntity it : treeNodes) {
            if (treeNode.getId().equals(it.getParentId())) {
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new LinkedHashSet<>());
                }
                treeNode.getChildren().add(findChildren(it, treeNodes));
            }
        }
        return treeNode;
    }
}
