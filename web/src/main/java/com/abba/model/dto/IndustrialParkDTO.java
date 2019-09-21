package com.abba.model.dto;

import com.abba.entity.AbstractDTO;
import com.abba.entity.GPSEntity;
import com.abba.model.po.IndustrialPark;
import com.abba.model.po.Menu;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author dengbojing
 */
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class IndustrialParkDTO extends AbstractDTO<IndustrialPark> {

    public IndustrialParkDTO(IndustrialPark park, String... ignoreProperties) {
        super(park, ignoreProperties);
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

    private List<GPSEntity> gpsRegions;

    private List<InvestmentResourceDTO> resources;
}
