package com.abba.controller;

import com.abba.entity.response.BaseResponse;
import com.abba.model.bo.MenuParam;
import com.abba.model.dto.MenuDTO;
import com.abba.model.vo.MenuVO;
import com.abba.service.IMenuService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dengbojing
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    private final IMenuService<MenuDTO> menuService;

    public MenuController(IMenuService<MenuDTO> menuService) {
        this.menuService = menuService;
    }

    @PostMapping("/menus")
    public BaseResponse<MenuVO> getAll(){
        return null;
    }

    @PostMapping("/loginUser")
    public BaseResponse<List<MenuVO>> getByLoginUserId(@RequestBody MenuParam param){
        List<MenuDTO> dtos = menuService.getUserById(param.getUserId());
        List<MenuVO> vos = dtos.stream().map(menuDTO -> new MenuVO(menuDTO)).collect(Collectors.toList());
        return BaseResponse.<List<MenuVO>>builder().build().adaptive(CollectionUtils::isNotEmpty,vos,"未查询到所属菜单","sucess");
    }
}
