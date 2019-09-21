package com.abba.model.vo;

import com.abba.entity.AbstractVO;
import com.abba.entity.CityEntity;
import com.abba.entity.GPSEntity;
import com.abba.enums.CityFeatureTypeEnum;
import com.abba.model.dto.CityFeatureDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author dengbojing
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class CityFeatureVO extends AbstractVO<CityFeatureDTO> {
    public CityFeatureVO(CityFeatureDTO dto, String...ignoreProperties){
        super(dto,ignoreProperties);
        this.typeName = CityFeatureTypeEnum.getName(this.type);
    }

    private String id;

    private Integer type;

    private String typeName;

    private GPSEntity gps;

    private String name;

    private CityEntity city;
}
