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
@Table(name = "t_permission")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Permission implements Serializable {

    @Id
    @GeneratedValue(generator = "permissionGenerator")
    @GenericGenerator(name = "permissionGenerator", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    @Column(name = "c_permission_type")
    private String permissionType;

    @Column(name = "c_permission_name")
    private String permissionName;

    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @Fetch(FetchMode.SELECT)
    @JoinTable(name="t_permission_menu",
            joinColumns={@JoinColumn(name="c_permission_id", referencedColumnName = "id", nullable = false)},
            inverseJoinColumns={@JoinColumn(name="c_menu_id",referencedColumnName = "id", nullable = false)},
            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)
    )
    private List<Menu> menus;


    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @Fetch(FetchMode.SELECT)
    @JoinTable(name="t_permission_page",
            joinColumns={@JoinColumn(name="c_permission_id", referencedColumnName = "id", nullable = false)},
            inverseJoinColumns={@JoinColumn(name="c_page_id",referencedColumnName = "id", nullable = false)},
            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)
    )
    private List<Page> pages;
}
