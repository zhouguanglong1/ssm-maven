package com.zhougl.heartbeatdemo.protocol;

import java.io.Serializable;

/**
 * @author zhougl
 * 2019/2/13
 **/
public class CustomProtocol implements Serializable{

    private static final long serialVersionUID = 8357159084537585323L;
    private long id;
    private String content;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CustomProtocol(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public CustomProtocol() {
    }
}
