package com.zhougl.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author zhougl
 * 2019/3/23
 **/
public class NotifyEvent extends ApplicationEvent {

    private static final long serialVersionUID = -4521553506171718641L;
    private int event;
    private long fromUserId;
    private long toUserId;
    private long postId;

    public NotifyEvent(Object source) {
        super(source);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getEvent() {
        return event;
    }

    public void setEvent(int event) {
        this.event = event;
    }

    public long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public long getToUserId() {
        return toUserId;
    }

    public void setToUserId(long toUserId) {
        this.toUserId = toUserId;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }
}
