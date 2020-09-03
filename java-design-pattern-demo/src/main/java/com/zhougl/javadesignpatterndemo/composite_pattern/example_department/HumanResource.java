package com.zhougl.javadesignpatterndemo.composite_pattern.example_department;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/3/5 18:34
 */
public abstract class HumanResource {
    protected long id;
    protected double salary;

    public HumanResource(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public abstract double calculateSalary();
}
