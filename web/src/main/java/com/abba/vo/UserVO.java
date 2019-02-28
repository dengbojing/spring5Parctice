package com.abba.vo;

import com.abba.entity.vo.BaseVO;
import com.abba.model.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * @author dengbojing
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserVO extends BaseVO<User> {
    public UserVO(User user,String...ignoreProperties) {
        super(user,ignoreProperties);
    }
    private String pid;
    private String userLoginName;
    private String userName;
    private String userPhone;
    private String userHeadImg;
    private String userEmail;
    private String userAddress;
    private String userGender;
    private LocalDate birth;

}
