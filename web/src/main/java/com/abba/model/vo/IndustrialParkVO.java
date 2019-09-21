package com.abba.model.vo;

import com.abba.entity.AbstractVO;
import com.abba.entity.GPSEntity;
import com.abba.model.dto.IndustrialParkDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author dengbojing
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class IndustrialParkVO extends AbstractVO<IndustrialParkDTO> {

    public IndustrialParkVO(IndustrialParkDTO dto, String... ignoreProperties) {
        super(dto, ignoreProperties);
    }

    private String id;

    private String name;

    private Integer area;

    private String regionCode;

    private String regionName;

    private String cityCode;

    private String cityName;

    private String districtName;

    private String districtCode;

    private Integer level;

    private String desc;

    private String mainIndustryType;

    private String mainIndustryCode;

    private String address;

    private String telephone;

    private String panoramicView;

    private GPSEntity gps;

    private List<InvestmentResourceVO> resources;
}
