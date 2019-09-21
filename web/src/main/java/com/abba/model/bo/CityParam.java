package com.abba.model.bo;

import com.abba.entity.AbstractParam;
import lombok.Getter;
import lombok.Setter;

/**
 * @author dengbojing
 */
@Getter
@Setter
public class CityParam extends AbstractParam {

    private String cityName;

    private Integer cityCode;

    private String regionName;

    private Integer regionCode;

    private String districtName;

    private Integer districtCode;
}
