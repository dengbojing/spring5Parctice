package com.abba.model.dto;

import com.abba.entity.AbstractDTO;
import com.abba.model.po.User;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

/**
 * @author dengbojing
 */
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class UserDTO extends AbstractDTO<User>{

    private String id;

    private String loginName;

    private String userName;

    private String gender;

    private String phoneNum;

    private LocalDate birth;

    private String avatar;

    private String address;

    private String email;

    private List<RoleDTO> roles;

    public UserDTO(User user, String... ignoreProperties) {
        super(user, ignoreProperties);
    }
}
