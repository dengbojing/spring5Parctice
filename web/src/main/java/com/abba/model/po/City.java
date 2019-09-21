package com.abba.model.po;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author dengbojing
 */
@Getter
@Setter
@Embeddable
public class City {
    @Column(name = "c_city_code")
    private Integer cityCode;

    @Column(name = "c_city_name")
    private String cityName;

    @Column(name = "c_district_code")
    private Integer districtCode;

    @Column(name = "c_district_name")
    private String districtName;

    @Column(name = "c_region_name")
    private String regionName;

    @Column(name = "c_region_code")
    private Integer regionCode;
}
