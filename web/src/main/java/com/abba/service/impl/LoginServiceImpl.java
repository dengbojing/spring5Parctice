package com.abba.service.impl;

import com.abba.dao.IUserDao;
import com.abba.entity.Subject;
import com.abba.exception.BusinessException;
import com.abba.model.bo.LoginParam;
import com.abba.model.dto.UserDTO;
import com.abba.model.po.User;
import com.abba.service.ILoginService;
import com.abba.util.JwtUtil;
import com.abba.util.StringHelper;
import com.google.common.base.Preconditions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author dengbojing
 */
@Service
public class LoginServiceImpl implements ILoginService<UserDTO> {

    private final IUserDao<User> userDao;

    private final JwtUtil jwtUtil;

    public LoginServiceImpl(IUserDao<User> userDao,JwtUtil jwtUtil) {
        this.userDao = userDao;
        this.jwtUtil = jwtUtil;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public Optional<String> login(LoginParam loginParam) {
        String hql = "from User where loginName = ?1";
        Map<Integer,Object> params = new HashMap<>(2);
        params.put(1, loginParam.getName());
        User user = userDao.hqlQuery(hql,params);
        checkNotNull(user,"用户名密码错误");
        if(StringHelper.isEmpty(user.getId()) || StringHelper.isEmpty(user.getLoginPwd()) || !user.getLoginPwd().equals(loginParam.getPwd())){
            throw new BusinessException("用户名密码错误");
        }
        Subject subject = new Subject();
        subject.setUserId(user.getId());
        subject.setCompanyId("99999");
        subject.setCompanyType(Subject.CompanyType.PERSONAL);
        String token = jwtUtil.general(subject);
        return Optional.ofNullable(token);
    }
}
