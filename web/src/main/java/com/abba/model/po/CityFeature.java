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

/**
 * @author dengbojing
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_city_feature")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONE)
public class CityFeature implements Serializable {
    @Id
    @GeneratedValue(generator = "cityFeatureGenerator")
    @GenericGenerator(name = "cityFeatureGenerator", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    @Column(name = "c_type")
    private Integer type;

    private GPS gps;

    @Column(name = "c_name")
    private String name;

    private City city;

}
