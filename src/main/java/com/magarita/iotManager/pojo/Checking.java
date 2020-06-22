package com.magarita.iotManager.pojo;

/**
 * 考勤记录表
 */
public class Checking {
    private Integer id;
    private String morningCheck;
    private String mState;
    private String eveningCheck;
    private String eState;
    private Employee emp;
    private String nowDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMorningCheck() {
        return morningCheck;
    }

    public void setMorningCheck(String morningCheck) {
        this.morningCheck = morningCheck;
    }

    public String getmState() {
        return mState;
    }

    public void setmState(String mState) {
        this.mState = mState;
    }

    public String getEveningCheck() {
        return eveningCheck;
    }

    public void setEveningCheck(String eveningCheck) {
        this.eveningCheck = eveningCheck;
    }

    public String geteState() {
        return eState;
    }

    public void seteState(String eState) {
        this.eState = eState;
    }

    public Employee getEmp() {
        return emp;
    }

    public void setEmp(Employee emp) {
        this.emp = emp;
    }

    public String getNowDate() {
        return nowDate;
    }

    public void setNowDate(String nowDate) {
        this.nowDate = nowDate;
    }

    @Override
    public String toString() {
        return "Check{" +
                "id=" + id +
                ", morningCheck='" + morningCheck + '\'' +
                ", mState='" + mState + '\'' +
                ", eveningCheck='" + eveningCheck + '\'' +
                ", eState='" + eState + '\'' +
                ", emp=" + emp +
                ", nowDate='" + nowDate + '\'' +
                '}';
    }
}
