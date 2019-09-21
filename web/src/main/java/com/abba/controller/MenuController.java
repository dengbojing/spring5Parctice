package com.abba.controller;

import com.abba.entity.response.BaseResponse;
import com.abba.model.bo.MenuParam;
import com.abba.model.dto.MenuDTO;
import com.abba.model.vo.MenuVO;
import com.abba.service.IMenuService;
import com.abba.util.StringHelper;
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

    /**
     * 获取所有菜单
     * @return 统一返回格式 {@link BaseResponse}
     */
    @PostMapping("/menus")
    public BaseResponse<List<MenuVO>> getAll(){
        List<MenuDTO> dtos = menuService.getAll();
        List<MenuVO> vos = dtos.stream().map(MenuVO::new).collect(Collectors.toList());
        return BaseResponse.<List<MenuVO>>builder().build().success(vos);
    }

    /**
     * 获取登录用户的菜单
     * @param param 占位参数,主要获取用户id
     * @return 统一返回格式 {@link BaseResponse}
     */
    @PostMapping("/loginUser")
    public BaseResponse<List<MenuVO>> getByLoginUserId(@RequestBody MenuParam param){
        List<MenuDTO> dtos = menuService.getUserById(param.getUserId());
        List<MenuVO> vos = dtos.stream().map(menuDTO -> new MenuVO(menuDTO)).collect(Collectors.toList());
        return BaseResponse.<List<MenuVO>>builder().build().adaptive(CollectionUtils::isNotEmpty,vos,"未查询到所属菜单","success");
    }

    /**
     * 添加菜单
     * @param param 菜单参数
     * @return 统一返回格式 {@link BaseResponse}
     */
    @PostMapping("/add")
    public BaseResponse<MenuVO> add(@RequestBody MenuParam param){
        MenuDTO dto = menuService.add(param).orElseGet(MenuDTO::new);
        return BaseResponse.<MenuVO>builder().build()
                .adaptive(menuVO -> StringHelper.isNotEmpty(menuVO.getId()), new MenuVO(dto));
    }

    /**
     * 更新菜单
     * @param param 菜单参数
     * @return 统一返回格式 {@link BaseResponse}
     */
    @PostMapping("/update")
    public BaseResponse<String> update(@RequestBody MenuParam param){
        menuService.update(param);
        return BaseResponse.<String>builder().build().success("");
    }
}
