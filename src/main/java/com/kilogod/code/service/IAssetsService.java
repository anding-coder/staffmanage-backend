package com.kilogod.code.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kilogod.code.domain.Assets;
import com.kilogod.code.domain.dto.AssetsQueryDTO;

import javax.xml.bind.ValidationException;
import java.util.List;

/**
 * <p>
 * 资产信息 服务类
 * </p>
 *
 * @author Anding
 */
public interface IAssetsService extends IService<Assets> {

    /**
    * 添加资产信息
    *
    * @param assets 资产信息
    * @return int
    * @throws ValidationException
    */
    int insert(Assets assets) throws ValidationException;

    /**
    * 删除资产信息
    *
    * @param id 主键
    * @return int
    * @throws ValidationException
    */
    int delete(String id) throws ValidationException;

    /**
    * 修改资产信息
    *
    * @param assets 资产信息
    * @return int
    * @throws ValidationException
    */
    int updateData(Assets assets) throws ValidationException;

    /**
     * 查询资产信息分页数据
     * @return List<Assets>
     */
    List<Assets> getList(AssetsQueryDTO dto) throws ValidationException;

    /**
    * 批量删除资产信息
    * @param ids
    * @return
    * @throws ValidationException
    */
    int batchDelete(List<Integer> ids) throws ValidationException;

}
