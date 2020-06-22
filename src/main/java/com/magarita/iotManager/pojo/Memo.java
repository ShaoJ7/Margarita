package com.magarita.iotManager.pojo;

import java.util.Date;

/**
 * 备忘录表
 */
public class Memo {
    private Integer id;
    private String content;
    private Date time;
    private Employee emp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Employee getEmp() {
        return emp;
    }

    public void setEmp(Employee emp) {
        this.emp = emp;
    }

    @Override
    public String toString() {
        return "Memo{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", time=" + time +
                ", emp=" + emp +
                '}';
    }
}
