package com.abba.model.po;

import lombok.*;
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
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_sys_parameter")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONE)
public class SysParameter implements Serializable {

    @Id
    @GeneratedValue(generator = "sysGenerator")
    @GenericGenerator(name = "sysGenerator", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    @Column(name = "c_parent_id")
    private String parentId;

    @Column(name = "c_value")
    private String value;

    @Column(name = "c_comment")
    private String comment;

    @Column(name = "c_name")
    private String name;

    @Column(name = "c_level")
    private Integer level;

    @Column(name = "c_type")
    private String type;

    @Column(name = "c_code")
    private String code;

}
