package com.abba.service.impl;

import com.abba.dao.IMenuDao;
import com.abba.model.bo.MenuParam;
import com.abba.model.dto.MenuDTO;
import com.abba.model.po.Menu;
import com.abba.service.IMenuService;
import com.google.common.base.Preconditions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author dengbojing
 */
@Service
public class MenuServiceImpl implements IMenuService<MenuDTO> {

    private final IMenuDao<Menu> menuDao;

    public MenuServiceImpl(IMenuDao<Menu> menuDao) {
        this.menuDao = menuDao;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public List<MenuDTO> getUserById(String userId) {
        StringBuilder sqlBuilder = new StringBuilder("select * from t_menu where id in ( ");
        sqlBuilder.append(" select c_menu_id from t_permission_menu where c_permission_id in(");
        sqlBuilder.append(" select c_permission_id from t_role_permission where c_role_id in(");
        sqlBuilder.append(" select c_role_id from t_user_role where c_user_id = ?0)))");
        Map<Integer, Object> paramMap = new HashMap<>();
        paramMap.put(0, userId);
        List<Menu> menus = menuDao.sqlQueryList(sqlBuilder.toString(), paramMap);
        return menus.stream().map(menu -> new MenuDTO(menu)).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public Optional<MenuDTO> add(MenuParam param) {
        Menu menu = new Menu();
        param.copyTo(menu);
        menuDao.create(menu);
        return Optional.of(new MenuDTO(menu));
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public List<MenuDTO> getAll() {
        List<Menu> menus = menuDao.findAll();
        return menus.stream().map(MenuDTO::new).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Optional<MenuDTO> update(MenuParam param) {
        checkNotNull(param.getId(),"菜单id不能为空");
        Menu menu = new Menu();
        param.copyTo(menu);
        menuDao.mergeByPrimaryKey(menu);
        return Optional.of(new MenuDTO(menu));
    }
}
