package com.abba.model.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * @author dengbojing
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_industrial_park")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class IndustrialPark implements Serializable {

    @Id
    @GeneratedValue(generator = "industrialGenerator")
    @GenericGenerator(name = "industrialGenerator", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    @Column(name = "c_name")
    private String name;

    @Column(name = "c_area")
    private Integer area;

    private City city;

    @Column(name = "c_level")
    private Integer level;

    @Column(name = "c_desc", length = 1000)
    private String desc;

    @Column(name = "c_main_industry_type")
    private String mainIndustryType;

    @Column(name = "c_main_industry_code")
    private String mainIndustryCode;

    @Column(name = "c_address")
    private String address;

    @Column(name = "c_telephone")
    private String telephone;

    @Column(name = "c_panoramic_view")
    private String panoramicView;

    private GPS gps;

    @OneToMany(fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "c_resource_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private List<InvestmentResource> resources;

}
