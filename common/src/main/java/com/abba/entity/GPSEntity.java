package com.abba.entity;

import com.abba.util.GPSConverterHelper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author dengbojing
 */
@Getter
@Setter
@NoArgsConstructor
public class GPSEntity {
    private double lat;
    private double lon;

    /**
     * 纬度(国标）
     */
    private Double gcjLat;

    /**
     * 经度(国标）
     */
    private Double gcjLon;

    /**
     * 纬度(wgs84--国际标准）
     */
    private Double wgs84Lat;

    /**
     * 经度(wgs84--国际标准）
     */
    private Double wgs84Lon;

    private Double zaxis;

    public GPSEntity(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
        this.gcjLat = lat;
        this.gcjLon = lon;

    }


    @Override
    public String toString() {
        return "lat:" + lat + "," + "lon:" + lon;
    }
}
