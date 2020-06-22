package com.magarita.iotManager.pojo;

import java.util.Date;
import java.util.Objects;


/**
 * 设备实体类
 */
public class Employee {

    private Integer id;
    private String username;
    private String deviceport;
    private String realname;
    private String category;
    private String address;
    private String email;
    private Date birth;
    private String nation;
    private String bankcard;
    private String telphone;
    private Integer age;
    private String identity;
    private String education;
    private String experience;
    private Integer isDel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDeviceport() {
        return deviceport;
    }

    public void setDeviceport(String deviceport) {
        this.deviceport = deviceport;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getBankcard() {
        return bankcard;
    }

    public void setBankcard(String bankcard) {
        this.bankcard = bankcard;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", deviceport='" + deviceport + '\'' +
                ", realname='" + realname + '\'' +
                ", category=" + category +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", birth=" + birth +
                ", nation='" + nation + '\'' +
                ", bankcard='" + bankcard + '\'' +
                ", telphone='" + telphone + '\'' +
                ", age=" + age +
                ", identity='" + identity + '\'' +
                ", education='" + education + '\'' +
                ", experience='" + experience + '\'' +
                ", isDel=" + isDel +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) &&
                Objects.equals(username, employee.username) &&
                Objects.equals(deviceport, employee.deviceport) &&
                Objects.equals(realname, employee.realname) &&
                Objects.equals(category, employee.category) &&
                Objects.equals(address, employee.address) &&
                Objects.equals(email, employee.email) &&
                Objects.equals(birth, employee.birth) &&
                Objects.equals(nation, employee.nation) &&
                Objects.equals(bankcard, employee.bankcard) &&
                Objects.equals(telphone, employee.telphone) &&
                Objects.equals(age, employee.age) &&
                Objects.equals(identity, employee.identity) &&
                Objects.equals(education, employee.education) &&
                Objects.equals(experience, employee.experience) &&
                Objects.equals(isDel, employee.isDel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, deviceport, realname, category, address, email, birth, nation, bankcard, telphone, age, identity, education, experience, isDel);
    }
}
