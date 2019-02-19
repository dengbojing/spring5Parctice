package com.abba.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * @author dengbojing
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_user")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User implements Serializable {

    @Id
    @GeneratedValue(generator = "userTableGenerator")
    @GenericGenerator(name = "userTableGenerator", strategy = "uuid2")
    @Column
    private String pid;
    @Column
    private String userLoginName;
    @Column
    private String userLoginPwd;
    @Column
    private String userName;
    @Column
    private String userPhone;
    @Column
    private String userHeadImg;
    @Column
    private String userEmail;
    @Column
    private String userAddress;
    @Column
    private String userGender;

    @Transient
    private LocalDate birth;
}
