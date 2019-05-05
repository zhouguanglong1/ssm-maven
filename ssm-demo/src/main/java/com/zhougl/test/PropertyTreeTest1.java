package com.zhougl.test;

import com.zhougl.utils.JsonUtil;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

/**
 * @author by zhougl
 * @classname PropertyTreeTest1
 * @desc TODO
 * @date 2019/5/5 14:27
 */
public class PropertyTreeTest1 {

    public static void main(String[] args) {
        List<Tree> list = new ArrayList<>();
        List<Tree> result = new ArrayList<>();
        Tree tree = new Tree();
        tree.setId("1");
        tree.setParentId("0");
        tree.setPropertyName("父属性");
        Tree tree1 = new Tree();
        tree1.setId("2");
        tree1.setParentId("1");
        tree1.setPropertyName("属性1");
        Tree tree2 = new Tree();
        tree2.setId("3");
        tree2.setParentId("1");
        tree2.setPropertyName("属性2");
        Tree tree3 = new Tree();
        tree3.setId("4");
        tree3.setParentId("2");
        tree3.setPropertyName("属性3");
        Tree tree4 = new Tree();
        tree4.setId("5");
        tree4.setParentId("3");
        tree4.setPropertyName("属性4");

        list.add(tree);
        list.add(tree1);
        list.add(tree2);
        list.add(tree3);
        list.add(tree4);
        /*int i = 5;
        while (i > 0){

            i --;
        }*/

        /*list.forEach(t -> {
            if("0".equals(t.getParentId())){
                result.add(tree);
            }
            list.forEach(t1 -> {
                if(t1.getId().equals(t.getParentId())){
                    if(t.getChildren() == null){
                        t.setChildren(new ArrayList<>());
                    }
                    t.getChildren().add(t1);
                }
            });
        });*/

        System.out.println(list);

        list.forEach(t -> {
            if("0".equals(t.getParentId())){
                result.add(t);
            }
            list.forEach(t1 -> {
                if(t.getParentId().equals(t1.getId())){
                    if(t1.getChildren()==null){
                        t1.setChildren(new LinkedHashSet<>());
                    }
                    t1.getChildren().add(t);
//                    result.add(t1);
                }
            });
        });

        // id=1
        // id=1

        System.out.println(JsonUtil.toJSONString(result));

    }



    private static void recurse(Tree tree, List<Tree> list) {
        list.forEach(m -> {
            if(tree.getParentId().equals(m.getId())){

                recurse(m,list);
            }
        });
    }


    private static class Tree {
        private String propertyName;
        private String id;
        private String parentId;
//        private List<Tree> children;
        private LinkedHashSet<Tree> children;

        /*public List<Tree> getChildren() {
            return children;
        }

        public void setChildren(List<Tree> children) {
            this.children = children;
        }*/

        public LinkedHashSet<Tree> getChildren() {
            return children;
        }

        public void setChildren(LinkedHashSet<Tree> children) {
            this.children = children;
        }

        public String getPropertyName() {
            return propertyName;
        }

        public void setPropertyName(String propertyName) {
            this.propertyName = propertyName;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        @Override
        public String toString() {
            return "Tree{" +
                    "propertyName='" + propertyName + '\'' +
                    ", id='" + id + '\'' +
                    ", parentId='" + parentId + '\'' +
                    ", children=" + children +
                    '}';
        }
    }
}