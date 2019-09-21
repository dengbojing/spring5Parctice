package com.abba.service;

import com.abba.model.bo.SysParam;
import com.abba.model.vo.SysParameterVO;

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
    List<T> getByParentIdAndType(Integer parentId, String type);

    /**
     * 获取该类型的根节点（level为0）
     * @param type
     * @return
     */
    Optional<T> getRootByType(String type);


    /**
     * 根绝type查询所有数据
     * @param type 数据类型
     * @return
     */
    List<T> getAll(String type);

    /**
     * 添加系统参数
     * @param param
     * @return
     */
    Optional<T> add(SysParam param);

    /**
     * 批量添加系统参数
     * @param params
     * @return
     */
    void batchAdd(List<SysParam> params);
}
