package com.abba.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author dengbojing
 */
@Getter
@Setter
@NoArgsConstructor
public class CityEntity {

    private String cityName;

    private Integer cityCode;

    private Integer districtCode;

    private String districtName;

    private String regionName;

    private Integer regionCode;
}
