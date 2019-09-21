package com.abba.model.dto;

import com.abba.entity.AbstractDTO;
import com.abba.entity.GPSEntity;
import com.abba.model.po.IndustrialPark;
import com.abba.model.po.InvestmentResource;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

/**
 * @author dengbojing
 */
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class InvestmentResourceDTO extends AbstractDTO<InvestmentResource> {

    public InvestmentResourceDTO(InvestmentResource resource, String... ignoreProperties) {
        super(resource, ignoreProperties);
    }

    private String id;

    private String name;

    private Integer area;

    private String address;

    private String type;

    private Integer totalFloor;

    private Integer floor;

    private String desc;

    private String telephone;

    private String contact;

    private String landStatus;

    private String landType;

    private LocalDate buildDate;

    private String imgUrl;

    private String panoramicView;

    private GPSEntity gps;

    private IndustrialParkDTO industrialPark;
}
