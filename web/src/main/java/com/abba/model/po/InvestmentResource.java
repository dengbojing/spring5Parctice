package com.abba.model.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author dengbojing
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_investment_resource")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class InvestmentResource implements Serializable {

    @Id
    @GeneratedValue(generator = "investmentGenerator")
    @GenericGenerator(name = "investmentGenerator", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    @Column(name = "c_name")
    private String name;

    @Column(name = "area")
    private Integer area;

    @Column(name = "c_address")
    private String address;

    @Column(name = "c_type")
    private String type;

    @Column(name = "c_total_floor")
    private Integer totalFloor;

    @Column(name = "c_floor")
    private Integer floor;

    @Column(name = "c_desc", length = 1000)
    private String desc;

    @Column(name = "c_telephone")
    private String telephone;

    @Column(name = "c_contact")
    private String contact;

    @Column(name = "c_land_status")
    private String landStatus;

    @Column(name = "c_land_type")
    private String landType;

    @Column(name = "c_build_date")
    private LocalDate buildDate;

    @Column(name = "c_img_url")
    private String imgUrl;

    @Column(name = "c_panoramic_view")
    private String panoramicView;

    private GPS gps;

    @ManyToOne
    @JoinColumn(name = "c_industrial_park_id", insertable = false, updatable = false)
    private IndustrialPark industrialPark;


    public void setGcjLat(Double lat) {
        this.gps.setGcjLat(lat);
    }

    public void setGcjLon(Double lon) {
        this.gps.setGcjLon(lon);
    }

    public void setWgs84Lat(Double lat) {
        this.gps.setWgs84Lat(lat);
    }

    public void setWgs84Lon(Double lon) {
        this.gps.setWgs84Lat(lon);
    }

    public Double getGcjLat() {
        return this.gps.getGcjLat();
    }

    public Double getGcjLon() {
        return this.gps.getGcjLon();
    }


    public Double getWgs84Lat(){
        return this.gps.getWgs84Lat();
    }

    public Double getWgs84Lon(){
        return this.gps.getWgs84Lon();
    }

}
