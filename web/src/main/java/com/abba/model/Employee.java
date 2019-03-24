package com.abba.model;

import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_user")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Employee {
    @Id
    @GeneratedValue(generator = "userTableGenerator")
    @GenericGenerator(name = "userTableGenerator", strategy = "uuid2")
    @Column
    private String id;
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
