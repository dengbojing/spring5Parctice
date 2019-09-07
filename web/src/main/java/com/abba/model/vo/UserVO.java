package com.abba.model.vo;

import com.abba.entity.AbstractVO;
import com.abba.model.dto.UserDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * @author dengbojing
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class UserVO extends AbstractVO<UserDTO> {
    public UserVO(UserDTO user,String...ignoreProperties) {
        super(user,ignoreProperties);
    }

    private String id;

    private String loginName;

    private String userName;

    private String phoneNum;

    private String avatar;

    private String email;

    private String address;

    private String gender;

    private LocalDate birth;

    private String jwt;

    private List<RoleVO> roles;

}
