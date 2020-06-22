package com.magarita.iotManager.pojo;

/**
 * 类别枚举类
 */
public enum Category implements BaseEnum<Category,String> {
    MAN("控制" ),WOMEN("设备");
    private String value;

    Category(String value) {
        this.value = value;
    }


    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
