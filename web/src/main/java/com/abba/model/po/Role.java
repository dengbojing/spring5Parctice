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
import java.util.List;

/**
 * @author dengbojing
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_role")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Role implements Serializable {

    @Id
    @GeneratedValue(generator = "roleGenerator")
    @GenericGenerator(name = "roleGenerator", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    @Column(name = "c_role_name")
    private String name;

    @Column(name = "c_type")
    private String type;

    @Column(name = "c_status")
    private Integer status;


    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @Fetch(FetchMode.SELECT)
    @JoinTable(name="t_role_permission",
            joinColumns={@JoinColumn(name="c_role_id", referencedColumnName = "id", nullable = false)},
            inverseJoinColumns={@JoinColumn(name="c_permission_id",referencedColumnName = "id", nullable = false)},
            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)
    )
    private List<Permission> permissions;
}
