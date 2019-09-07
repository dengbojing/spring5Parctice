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
     * 所在城市-片区
     */
    @Column(name = "c_region")
    private String region;

    /**
     * 所在城市
     */
    @Column(name = "c_city")
    private String city;

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
     * 纬度(国标）
     */
    @Column(name = "c_gcj_lat")
    private Double gcjLat;

    /**
     * 经度(国标）
     */
    @Column(name = "c_gcj_lon")
    private Double gcjLon;

    /**
     * 纬度(wgs84--国际标准）
     */
    @Column(name = "c_wgs84_lat")
    private Double wgs84Lat;

    /**
     * 经度(wgs84--国际标准）
     */
    @Column(name = "c_wgs84_lon")
    private Double wgs84Lon;

    /**
     * 行业种类
     */
    @Column(name = "c_industry_type")
    private String industryType;

    /**
     * 行政区域
     */
    @Column(name = "c_district")
    private String district;


}
