package com.abba.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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
    @GeneratedValue(generator = "companyTableGenerator")
    @GenericGenerator(name = "companyTableGenerator", strategy = "uuid2")
    @Column(length = 36)
    private String pid;

    /**
     * 公司名称
     */
    @Column
    private String name;

    /**
     * 地址
     */
    @Column
    private String address;

    /**
     * 注册资金（万元）
     */
    @Column(precision = 8)
    private BigDecimal funds;

    /**
     * 资金类型（美元，人民币）
     */
    @Column
    private String fundsType;

    /**
     * 是否上市 0 否 1是 -- 下同
     */
    @Column
    private String listed;

    /**
     * 是否存续 -1 吊销 0 注销 1存续
     */
    @Column
    private String status;

    /**
     * 成立日期
     */
    @Column
    private LocalDate establishment;

    /**
     * 是否融资
     */
    @Column
    private String financing;

    /**
     * 是否高新企业
     */
    @Column
    private String highTech;

    /**
     * 所在城市-片区
     */
    @Column
    private String region;

    /**
     * 所在城市
     */
    @Column
    private String city;

    /**
     * 是否个体商户
     */
    @Column
    private String individual;

    /**
     * 是否专利公司
     */
    @Column
    private String patent;


    /**
     * 纬度(国标）
     */
    @Column
    private Double gcjLat;

    /**
     * 经度(国标）
     */
    @Column
    private Double gcjLon;

    /**
     * 纬度(wgs84--国际标准）
     */
    @Column
    private Double wgs84Lat;

    /**
     * 经度(wgs84--国际标准）
     */
    @Column
    private Double wgs84Lon;

    /**
     * 行业种类
     */
    @Column
    private String industryType;

    /**
     * 行政区域
     */
    @Column
    private String district;


}
