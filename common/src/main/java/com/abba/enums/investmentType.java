package com.abba.enums;

/**
 * @author dengbojing
 */
public enum investmentType {

    /**
     * 1--土地
     */
    LAND(1,"土地"),
    /**
     * 2--写字楼
     */
    OFFICE_BUILDING(2,"写字楼"),
    /**
     * 3--厂房
     */
    FACTORY_BUILDING(3,"厂房");

    private Integer code;

    private String value;

    private investmentType(Integer code,String value){
        this.code = code;
        this.value = value;
    }
}
