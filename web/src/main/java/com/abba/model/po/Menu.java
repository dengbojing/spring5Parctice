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
import javax.persistence.ManyToMany;
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
@Table(name = "t_menu")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Menu implements Serializable {
    @Id
    @GeneratedValue(generator = "menuGenerator")
    @GenericGenerator(name = "menuGenerator", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    @Column(name = "c_name")
    private String name;

    @Column(name = "c_url")
    private String url;

    @Column(name = "c_level")
    private Integer level;

    @Column(name = "c_prent_id")
    private Integer parentId;

    @Column(name = "c_icon")
    private String icon;

    @Column(name = "c_order")
    private Integer order;

}
