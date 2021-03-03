package com.kilogod.code.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kilogod.code.domain.Dept;
import com.kilogod.code.domain.dto.DeptQueryDTO;

import javax.xml.bind.ValidationException;
import java.util.List;

/**
 * <p>
 * 部门信息 服务类
 * </p>
 *
 * @author Anding
 */
public interface IDeptService extends IService<Dept> {

    /**
    * 添加部门信息
    *
    * @param dept 部门信息
    * @return int
    * @throws ValidationException
    */
    int insert(Dept dept) throws ValidationException;

    /**
    * 删除部门信息
    *
    * @param id 主键
    * @return int
    * @throws ValidationException
    */
    int delete(String id) throws ValidationException;

    /**
    * 修改部门信息
    *
    * @param dept 部门信息
    * @return int
    * @throws ValidationException
    */
    int updateData(Dept dept) throws ValidationException;

    /**
     * 查询部门信息分页数据
     * @return List<Dept>
     */
    List<Dept> getList(DeptQueryDTO dto) throws ValidationException;

    /**
    * 批量删除部门信息
    * @param ids
    * @return
    * @throws ValidationException
    */
    int batchDelete(List<Integer> ids) throws ValidationException;

}
