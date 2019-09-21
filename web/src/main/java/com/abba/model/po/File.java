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

/**
 * @author dengbojing
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_file")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class File {

    @Id
    @GeneratedValue(generator = "fileGenerator")
    @GenericGenerator(name = "fileGenerator", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    @Column(name = "c_file_type")
    private String fileType;

    @Column(name = "c_biz_type")
    private String bizType;

    @Column(name = "c_biz_id")
    private String bizId;

    @Column(name = "c_suffix")
    private String suffix;

    @Column(name = "c_size", precision = 3)
    private Double size;

    @Column(name = "c_name")
    private String name;

    @Column(name= "c_origin_name")
    private String originName;

    @Column(name = "c_key")
    private String key;

    @Column(name = "c_url")
    private String url;

    @Column(name = "c_path")
    private String path;

}
