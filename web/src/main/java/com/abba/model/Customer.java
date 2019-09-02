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
@Table(name = "t_customer")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Customer implements Serializable {

    @Id
    @GeneratedValue(generator = "customerTableGenerator")
    @GenericGenerator(name = "customerTableGenerator", strategy = "uuid2")
    @Column(length = 36)
    private String pid;

    @Column
    private String customerName;
}
