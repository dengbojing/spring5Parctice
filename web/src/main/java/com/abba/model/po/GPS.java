package com.abba.model.po;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author dengbojing
 */
@Embeddable
@Getter
@Setter
public class GPS {

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
     * z轴
     */
    @Column(name = "c_z_axis")
    private Double zaxis;
}
