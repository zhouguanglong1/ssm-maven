package com.zhougl.test.tree;

import java.util.Set;

/**
 * @author by zhougl
 * @classname TreeEntity
 * @desc TODO
 * @date 2019/5/23 21:54
 */
public class TreeEntity<T> {
    private Long id;
    private Long parentId;
    private Set<T> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Set<T> getChildren() {
        return children;
    }

    public void setChildren(Set<T> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "TreeEntity{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", children=" + children +
                '}';
    }
}
