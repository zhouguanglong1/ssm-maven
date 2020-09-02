package com.zhougl.javadesignpatterndemo.repsonsibility_command_pattern.bean;

/**
 * @author by zhougl
 * @classname WorkflowActionParam
 * @desc TODO
 * @date 2019/6/4 15:50
 */
public class WorkflowActionParam {
    private Long id;
    private String code;
    private Object param;
    private String tanentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getParam() {
        return param;
    }

    public void setParam(Object param) {
        this.param = param;
    }

    public String getTanentId() {
        return tanentId;
    }

    public void setTanentId(String tanentId) {
        this.tanentId = tanentId;
    }

    @Override
    public String toString() {
        return "WorkflowActionParam{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", param=" + param +
                ", tanentId='" + tanentId + '\'' +
                '}';
    }
}
