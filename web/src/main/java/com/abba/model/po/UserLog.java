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
import java.time.LocalDate;


/**
 * @author dengbojing
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_user_log")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserLog implements Serializable {

    @Id
    @GeneratedValue(generator = "userGenerator")
    @GenericGenerator(name = "userGenerator", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    private String loginName;

    private String loginId;

    private String operation;

    private LocalDate operateDate;
}
