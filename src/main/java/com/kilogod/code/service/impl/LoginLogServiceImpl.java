package com.kilogod.code.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kilogod.code.common.res.ResultCode;
import com.kilogod.code.domain.LoginLog;
import com.kilogod.code.domain.dto.LoginLogQueryDTO;
import com.kilogod.code.mapper.LoginLogMapper;
import com.kilogod.code.service.ILoginLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.bind.ValidationException;
import java.util.List;

/**
 * <p>
 * 登录日志 服务实现类
 * </p>
 *
 * @author Anding
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements ILoginLogService {

    @Resource
    private LoginLogMapper mapper;

    @Override
    public int insert(LoginLog loginLog) throws ValidationException {
        int insert = mapper.insert(loginLog);
        if (insert < 1) {
            throw new ValidationException(ResultCode.INSERT_FAIL);
        }
        return insert;
    }

    @Override
    public int delete(String id) throws ValidationException {
        int i = mapper.delete(new QueryWrapper<LoginLog>().eq("id", id));
        if (i<1){
            throw new ValidationException(ResultCode.DELETE_FAIL);
        }
        return i;
    }

    @Override
    public int updateData(LoginLog loginLog) throws ValidationException {
        int i = mapper.updateById(loginLog);
        if (i<1){
            throw new ValidationException(ResultCode.UPDATE_FAIL);
        }
        return i;
    }

    @Override
    public List<LoginLog> getList(LoginLogQueryDTO dto) throws ValidationException {
        List<LoginLog> lists = mapper.selectList(new QueryWrapper<LoginLog>()
            .eq(StringUtils.isNotBlank(dto.getName()),"name",dto.getName())
            .eq(StringUtils.isNotBlank(dto.getUsername()),"username",dto.getUsername())
            .eq(StringUtils.isNotBlank(dto.getPhone()),"phone",dto.getPhone())
            .eq(StringUtils.isNotBlank(dto.getIp()),"ip",dto.getIp())
            .eq(StringUtils.isNotBlank(dto.getAddress()),"address",dto.getAddress())
        );
        return lists;
    }

    @Override
    public int batchDelete(List<Integer> ids) throws ValidationException {
        int i = mapper.deleteBatchIds(ids);
        if (i<1){
            throw new ValidationException(ResultCode.DELETE_FAIL);
        }
        return i;
    }
}
