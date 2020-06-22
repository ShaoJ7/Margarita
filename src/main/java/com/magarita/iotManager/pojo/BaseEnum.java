package com.magarita.iotManager.pojo;

public interface BaseEnum<E extends Enum<?>, T> {
    T getValue();
}
