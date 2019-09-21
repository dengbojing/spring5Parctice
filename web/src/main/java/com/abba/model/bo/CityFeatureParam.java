package com.abba.model.bo;

import com.abba.entity.AbstractParam;
import com.abba.entity.CityEntity;
import com.abba.entity.GPSEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author dengbojing
 */
@Getter
@Setter
public class CityFeatureParam extends AbstractParam {

    private String type;

    private GPSEntity gps;

    private String name;

    private CityEntity city;

}
