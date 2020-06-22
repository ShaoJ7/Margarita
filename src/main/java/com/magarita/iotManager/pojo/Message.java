package com.magarita.iotManager.pojo;

import java.util.Date;

/**
 * 信箱
 */
public class Message {
    private Integer id;
    private Employee fromId;
    private Employee toId;
    private String content;
    private Date StartTime;
    private Date endTime;
    private Status status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getFromId() {
        return fromId;
    }

    public void setFromId(Employee fromId) {
        this.fromId = fromId;
    }

    public Employee getToId() {
        return toId;
    }

    public void setToId(Employee toId) {
        this.toId = toId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getStartTime() {
        return StartTime;
    }

    public void setStartTime(Date startTime) {
        StartTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", fromId=" + fromId +
                ", toId=" + toId +
                ", content='" + content + '\'' +
                ", StartTime=" + StartTime +
                ", endTime=" + endTime +
                ", status=" + status +
                '}';
    }
}
