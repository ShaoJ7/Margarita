package com.magarita.iotManager.pojo;

/**
 * 请求状态
 */
public enum Deal implements BaseEnum<Deal,String> {
    AGREE("同意"),DISAGREE("驳回");
    private String value;

    Deal(String value) {
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
