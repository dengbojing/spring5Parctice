package com.abba.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author dengbojing
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_department")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Department implements Serializable {

    @Id
    @GeneratedValue(generator = "departmentTableGenerator")
    @GenericGenerator(name = "departmentTableGenerator", strategy = "uuid2")
    @Column(length = 36)
    private String pid;

    @Column
    private String departmentName;
}
