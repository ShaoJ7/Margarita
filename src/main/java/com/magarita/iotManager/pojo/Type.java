package com.magarita.iotManager.pojo;

/**
 * 部署变动
 */
public enum Type implements BaseEnum<Type,String> {
    LEAVE("下线"),CHANGE("部署变动");
    private String value;

    Type(String value) {
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
