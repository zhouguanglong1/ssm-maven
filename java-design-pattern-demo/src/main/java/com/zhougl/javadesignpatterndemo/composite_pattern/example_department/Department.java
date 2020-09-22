package com.zhougl.javadesignpatterndemo.composite_pattern.example_department;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/3/5 18:36
 */
public class Department extends HumanResource {

    public Department(long id) {
        super(id);
    }

    private List<HumanResource> subNodes = new ArrayList<>();

    @Override
    public double calculateSalary() {
        double totalSalary = 0;
        for (HumanResource hr : subNodes) {
            totalSalary += hr.calculateSalary();
        }
        this.salary = totalSalary;
        return totalSalary;
    }

    public void addSubNode(HumanResource hr) {
        subNodes.add(hr);
    }
}
