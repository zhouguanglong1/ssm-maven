package com.zhougl.javadesignpatterndemo.composite_pattern.example_department;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/3/5 18:35
 */
public class Employee extends HumanResource {

    public Employee(long id, double salary) {
        super(id);
        this.salary = salary;
    }

    @Override
    public double calculateSalary() {
        return salary;
    }
}
