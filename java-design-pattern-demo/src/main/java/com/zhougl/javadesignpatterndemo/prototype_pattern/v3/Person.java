package com.zhougl.javadesignpatterndemo.prototype_pattern.v3;

import java.util.Arrays;
import java.util.List;

/**
 * @author zhougl
 * 2019/3/22
 **/
public class Person implements Cloneable {
    private String name;
    private String sex;
    private List<String> list;
    private Teacher teacher;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
     public String getSex() {
         return sex;
     }
     public void setSex(String sex) {
         this.sex = sex;
     }
     public List<String> getList() {
         return list;
     }
     public void setList(List<String> list) {
         this.list = list;
     }
     //可能会抛出不支持克隆异常，原因是没有实现Cloneable接口
    @Override
     protected Person clone(){
        try{
            return (Person) super.clone();
        }catch(CloneNotSupportedException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", list=" + list +
                ", teacher=" + teacher +
                '}';
    }

    public static void main(String[] args){
        Person person = new Person();
        person.setName("test");
        person.setSex("0");
        person.setList(Arrays.asList("嘻嘻嘻","哈哈哈"));
        Teacher teacher = new Teacher();
        teacher.setName("王老师");
        person.setTeacher(teacher);
        Person person1 = person.clone();
        System.out.println(person);
        System.out.println(person1);
//        person.setList(Arrays.asList("呵呵呵"));
        Teacher teacher1 = person1.getTeacher();
        teacher1.setName("方老师");
        person1.setTeacher(teacher1);
        System.out.println(person);
        System.out.println(person1);
        System.out.println(person.getTeacher() == person1.getTeacher());
    }

    public static class Teacher{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Teacher{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
