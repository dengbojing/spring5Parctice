package com.abba.model.po;


import com.mysql.cj.protocol.PacketSentTimeHolder;
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
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author dengbojing
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_company")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Company implements Serializable {

    @Id
    @GeneratedValue(generator = "companyGenerator")
    @GenericGenerator(name = "companyGenerator", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    /**
     * 公司名称
     */
    @Column(name = "c_name")
    private String name;

    /**
     * 地址
     */
    @Column(name = "c_address")
    private String address;

    /**
     * 注册资金（万元）
     */
    @Column(name = "c_funds", precision = 8)
    private BigDecimal funds;

    /**
     * 资金类型（美元，人民币）
     */
    @Column(name = "c_funds_type")
    private String fundsType;

    /**
     * 是否上市 0 否 1是 -- 下同
     */
    @Column(name = "c_listed")
    private String listed;

    /**
     * 是否存续 -1 吊销 0 注销 1存续
     */
    @Column(name = "c_status")
    private String status;

    /**
     * 成立日期
     */
    @Column(name = "c_establishment")
    private LocalDate establishment;

    /**
     * 是否融资
     */
    @Column(name = "c_financing")
    private String financing;

    /**
     * 是否高新企业
     */
    @Column(name = "c_high_tech")
    private String highTech;

    /**
     * 是否个体商户
     */
    @Column(name = "c_individual")
    private String individual;

    /**
     * 是否专利公司
     */
    @Column(name = "c_patent")
    private String patent;

    /**
     * 坐标
     */
    private GPS gps;

    /**
     * 行业种类
     */
    @Column(name = "c_industry_type")
    private String industryType;

    /**
     * 城市信息
     */
    private City city;

    /**
     * 标签--是否规上,小微企业等等
     */
    @Column(name = "c_tags")
    private String tags;


    public void setGcjLat(double lat) {
        this.gps.setGcjLat(lat);
    }

    public void setGcjLon(double lon) {
        this.gps.setGcjLon(lon);
    }

    public void setWgs84Lat(double lat) {
        this.gps.setWgs84Lat(lat);
    }

    public void setWgs84Lon(double lon) {
        this.gps.setWgs84Lat(lon);
    }

    public double getGcjLat() {
        return this.gps.getGcjLat();
    }

    public double getGcjLon() {
        return this.gps.getGcjLon();
    }


    public Double getWgs84Lat(){
        return this.gps.getWgs84Lat();
    }

    public Double getWgs84Lon(){
        return this.gps.getWgs84Lon();
    }
}
