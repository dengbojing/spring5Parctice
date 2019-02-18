package com.abba.vo;

import com.abba.entity.vo.BaseVO;
import com.abba.model.User;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author dengbojing
 */
@Data
@Builder
public class UserVO extends BaseVO<User> {

    @Override
    public UserVO setEntity(User user) {
        this.entity = user;
        return this;
    }
    private String pid;
    private String userLoginName;
    private String userName;
    private String userPhone;
    private String userHeadImg;
    private String userEmail;
    private String userAddress;
    private String userGender;
    private Date birth;
}
