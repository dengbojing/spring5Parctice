package com.abba.model.dto;

import com.abba.entity.AbstractDTO;
import com.abba.entity.CityEntity;
import com.abba.entity.GPSEntity;
import com.abba.model.po.CityFeature;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author dengbojing
 */
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class CityFeatureDTO extends AbstractDTO<CityFeature> {

    public CityFeatureDTO(CityFeature cityFeature, String... ignoreProperties) {
        super(cityFeature, ignoreProperties);
    }

    private String id;

    private Integer type;

    private GPSEntity gps;

    private String name;

    private CityEntity city;
}
