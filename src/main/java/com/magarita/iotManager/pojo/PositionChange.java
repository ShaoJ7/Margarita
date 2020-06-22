package com.magarita.iotManager.pojo;

import java.util.Date;

/**
 * 职位调度表
 */
public class PositionChange {
    private Integer id;
    private Date startTime;
    private Date effectTime;
    private Deal DmStatus;
    private Deal GmStatus;
    private Type type;
    private Employee eId;
    private Position pId;
    private Position tId;
    private String empReason;
    private String dmComment;
    private String gmComment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEffectTime() {
        return effectTime;
    }

    public void setEffectTime(Date effectTime) {
        this.effectTime = effectTime;
    }

    public Deal getDmStatus() {
        return DmStatus;
    }

    public void setDmStatus(Deal dmStatus) {
        DmStatus = dmStatus;
    }

    public Deal getGmStatus() {
        return GmStatus;
    }

    public void setGmStatus(Deal gmStatus) {
        GmStatus = gmStatus;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Employee geteId() {
        return eId;
    }

    public void seteId(Employee eId) {
        this.eId = eId;
    }

    public Position getpId() {
        return pId;
    }

    public void setpId(Position pId) {
        this.pId = pId;
    }

    public Position gettId() {
        return tId;
    }

    public void settId(Position tId) {
        this.tId = tId;
    }

    public String getEmpReason() {
        return empReason;
    }

    public void setEmpReason(String empReason) {
        this.empReason = empReason;
    }

    public String getDmComment() {
        return dmComment;
    }

    public void setDmComment(String dmComment) {
        this.dmComment = dmComment;
    }

    public String getGmComment() {
        return gmComment;
    }

    public void setGmComment(String gmComment) {
        this.gmComment = gmComment;
    }

    @Override
    public String toString() {
        return "PositionChange{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", effectTime=" + effectTime +
                ", DmStatus=" + DmStatus +
                ", GmStatus=" + GmStatus +
                ", type=" + type +
                ", eId=" + eId +
                ", pId=" + pId +
                ", tId=" + tId +
                ", empReason='" + empReason + '\'' +
                ", dmComment='" + dmComment + '\'' +
                ", gmComment='" + gmComment + '\'' +
                '}';
    }
}
