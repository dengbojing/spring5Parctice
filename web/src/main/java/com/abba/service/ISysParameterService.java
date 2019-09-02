package com.abba.service;

import com.abba.vo.SysParameterVO;

import java.util.List;
import java.util.Optional;

/**
 * @author dengbojing
 */
public interface ISysParameterService<T> {

    /**
     * 根据id和类型获取系统参数
     * @return
     */
    List<T> queryByParentIdAndType(Integer parentId, String type);

    /**
     * 获取该类型的根节点（level为0）
     * @param type
     * @return
     */
    Optional<T> queryRootByType(String type);


    List<SysParameterVO> queryAll(String type);
}
