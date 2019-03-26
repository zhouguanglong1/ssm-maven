package com.zhougl.javadesignpatterndemo.prototype_pattern.v2;

/**
 * @author zhougl
 * 2019/3/17
 **/
public abstract class Shape implements Cloneable {
    private String id;
    protected String type;

    abstract void draw();

    public String getType(){
        return type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Object clone() {
        Shape clone = null;
        try {
            clone = (Shape)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}
