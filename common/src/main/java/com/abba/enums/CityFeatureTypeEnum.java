package com.abba.enums;

import lombok.Getter;

import java.util.stream.Stream;

/**
 * @author dengbojing
 */
@Getter
public enum CityFeatureTypeEnum {

    /**
     * 无类型
     */
    DEFAULT(0, "默认类型"),
    /**
     * 地标
     */
    LAND_MARK(1,"地标"),
    /**
     * 交通枢纽
     */
    TRANSPORTATION(2,"交通枢纽"),
    /**
     * 特产
     */
    SPECIALTY(3,"特产"),
    /**
     * 景点
     */
    ATTRACTION(4,"景点");


    private Integer code;

    private String value;

    CityFeatureTypeEnum(Integer code, String value){
        this.code = code;
        this.value = value;
    }

    public static String getName(Integer code){
        return Stream.of(CityFeatureTypeEnum.values())
                .filter(cityFeatureTypeEnum -> cityFeatureTypeEnum.getCode().equals(code))
                .findAny().orElse(CityFeatureTypeEnum.DEFAULT).name();
    }

}
