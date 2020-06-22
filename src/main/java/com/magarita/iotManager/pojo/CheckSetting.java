package com.magarita.iotManager.pojo;

public class CheckSetting {
    private Integer id;
    private String morningCheckLast;
    private String eveningCheckFirst;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMorningCheckLast() {
        return morningCheckLast;
    }

    public void setMorningCheckLast(String morningCheckLast) {
        this.morningCheckLast = morningCheckLast;
    }

    public String getEveningCheckFirst() {
        return eveningCheckFirst;
    }

    public void setEveningCheckFirst(String eveningCheckFirst) {
        this.eveningCheckFirst = eveningCheckFirst;
    }

    @Override
    public String toString() {
        return "CheckSetting{" +
                "id=" + id +
                ", morningCheckLast='" + morningCheckLast + '\'' +
                ", eveningCheckFirst='" + eveningCheckFirst + '\'' +
                '}';
    }
}
