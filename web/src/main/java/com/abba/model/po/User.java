package com.abba.model.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

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
    @GeneratedValue(generator = "userGenerator")
    @GenericGenerator(name = "userGenerator", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    @Column(name = "c_login_name")
    private String loginName;

    @Column(name = "c_login_pwd")
    private String loginPwd;

    @Column(name = "c_user_name")
    private String userName;

    @Column(name = "c_phone_num")
    private String phoneNum;

    @Column(name = "c_avatar")
    private String avatar;

    @Column(name = "c_email")
    private String email;

    @Column(name = "c_address")
    private String address;

    @Column(name = "c_gender")
    private String gender;

    @Column(name = "c_birth")
    private LocalDate birth;

    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @Fetch(FetchMode.SELECT)
    @JoinTable(name="t_user_role",
            joinColumns={@JoinColumn(name="c_user_id", referencedColumnName = "id", nullable = false)},
            inverseJoinColumns={@JoinColumn(name="c_role_id",referencedColumnName = "id", nullable = false)},
            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)
    )
    private List<Role> roles;
}
