package com.abba.model.vo;

import com.abba.entity.AbstractVO;
import com.abba.entity.GPSEntity;
import com.abba.model.dto.IndustrialParkDTO;
import com.abba.model.dto.InvestmentResourceDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author dengbojing
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class InvestmentResourceVO extends AbstractVO<InvestmentResourceDTO> {


    public InvestmentResourceVO(InvestmentResourceDTO dto, String... ignoreProperties) {
        super(dto, ignoreProperties);
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

    private IndustrialParkVO industrialPark;
}
