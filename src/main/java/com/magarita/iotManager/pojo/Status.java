package com.magarita.iotManager.pojo;

/**
 * 请求状态
 */
public enum Status implements BaseEnum<Status,String> {
    AGREE("同意"),DISAGREE("驳回"),UNSETTLED("未处理");
    private String value;

    Status(String value) {
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
