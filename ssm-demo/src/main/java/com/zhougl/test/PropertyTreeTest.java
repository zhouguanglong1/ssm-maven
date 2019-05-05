package com.zhougl.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author by zhougl
 * @classname PropertyTreeTest
 * @desc TODO
 * @date 2019/5/5 12:49
 */
public class PropertyTreeTest {
    public static void main(String[] args) {
        List<Map<String,Object>> list = new ArrayList<>();
        List<Tree> list1 = new ArrayList<>();
        Map<String,Object> map = new HashMap<>(5);
        map.put("id","1");
        map.put("property_name","属性名");
        map.put("parent_id","");
        Map<String,Object> map1 = new HashMap<>(5);
        map1.put("id","2");
        map1.put("property_name","属性名1");
        map1.put("parent_id","1");
        Map<String,Object> map2 = new HashMap<>(5);
        map2.put("id","3");
        map2.put("property_name","属性名2");
        map2.put("parent_id","1");
        Map<String,Object> map3 = new HashMap<>(5);
        map3.put("id","4");
        map3.put("property_name","属性名3");
        map3.put("parent_id","1");
        Map<String,Object> map4 = new HashMap<>(5);
        map4.put("id","5");
        map4.put("property_name","属性名4");
        map4.put("parent_id","2");
        list.add(map);
        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);

        List<Tree> rootList = new ArrayList<>();
        list.forEach(m -> {
            if(m.get("parent_id")==null){
                Tree tree = new Tree();
                tree.setId((String) m.get("id"));
                tree.setPropertyName((String) m.get("property_name"));
                rootList.add(tree);
            }
        });

        /*rootList.forEach(tree -> {
            recurse(tree,list);
        });*/


        list.forEach(m -> {
            Tree tree = new Tree();
            tree.setId((String) m.get("id"));
            tree.setPropertyName((String) m.get("property_name"));
            if(m.get("parent_id")!=null){
                String parent_id = (String) m.get("parent_id");
                list.forEach(m1 -> {
                    // 如果父id等于当前id
                    if(parent_id.equals(m1.get("id"))){
                        Tree tree1 = new Tree();
                        tree1.setId((String) m1.get("id"));
                        tree1.setPropertyName((String) m1.get("property_name"));
                        if(tree1.getChildren() == null || tree1.getChildren().isEmpty()){
                            List<Tree> children = new ArrayList<>();
                            children.add(tree);
                            tree1.setChildren(children);
                        }else{
                            tree1.getChildren().add(tree);
                        }

                        list1.add(tree1);
                    }
                });
            }else{
                list1.add(tree);
            }
        });

        System.out.println(list);
        System.out.println(list1);
    }



    private static class Tree {
        private String propertyName;
        private String id;
        private List<Tree> children;

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

        public List<Tree> getChildren() {
            return children;
        }

        public void setChildren(List<Tree> children) {
            this.children = children;
        }

        @Override
        public String toString() {
            return "Tree{" +
                    "propertyName='" + propertyName + '\'' +
                    ", id='" + id + '\'' +
                    ", children=" + children +
                    '}';
        }
    }
}
