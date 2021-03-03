package com.kilogod.code.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kilogod.code.domain.LoginLog;
import com.kilogod.code.domain.dto.LoginLogQueryDTO;

import javax.xml.bind.ValidationException;
import java.util.List;

/**
 * <p>
 * 登录日志 服务类
 * </p>
 *
 * @author Anding
 */
public interface ILoginLogService extends IService<LoginLog> {

    /**
    * 添加登录日志
    *
    * @param loginLog 登录日志
    * @return int
    * @throws ValidationException
    */
    int insert(LoginLog loginLog) throws ValidationException;

    /**
    * 删除登录日志
    *
    * @param id 主键
    * @return int
    * @throws ValidationException
    */
    int delete(String id) throws ValidationException;

    /**
    * 修改登录日志
    *
    * @param loginLog 登录日志
    * @return int
    * @throws ValidationException
    */
    int updateData(LoginLog loginLog) throws ValidationException;

    /**
     * 查询登录日志分页数据
     * @return List<LoginLog>
     */
    List<LoginLog> getList(LoginLogQueryDTO dto) throws ValidationException;

    /**
    * 批量删除登录日志
    * @param ids
    * @return
    * @throws ValidationException
    */
    int batchDelete(List<Integer> ids) throws ValidationException;

}
