package com.abba.model.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * @author dengbojing
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_geo_map")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GEOMap {


    @Id
    @GeneratedValue(generator = "GEOMapGenerator")
    @GenericGenerator(name = "GEOMapGenerator", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    @Column(name = "c_data_id")
    private String dataId;

    @Column(name = "c_geo_json")
    private String geoJson;

}
