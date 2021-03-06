package com.kilogod.code.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kilogod.code.common.res.ResultCode;
import com.kilogod.code.domain.Dept;
import com.kilogod.code.domain.dto.DeptQueryDTO;
import com.kilogod.code.mapper.DeptMapper;
import com.kilogod.code.service.IDeptService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.bind.ValidationException;
import java.util.List;

/**
 * <p>
 * 部门信息 服务实现类
 * </p>
 *
 * @author Anding
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

    @Resource
    private DeptMapper mapper;

    @Override
    public int insert(Dept dept) throws ValidationException {
        int insert = mapper.insert(dept);
        if (insert < 1) {
            throw new ValidationException(ResultCode.INSERT_FAIL);
        }
        return insert;
    }

    @Override
    public int delete(String id) throws ValidationException {
        int i = mapper.delete(new QueryWrapper<Dept>().eq("id", id));
        if (i<1){
            throw new ValidationException(ResultCode.DELETE_FAIL);
        }
        return i;
    }

    @Override
    public int updateData(Dept dept) throws ValidationException {
        int i = mapper.updateById(dept);
        if (i<1){
            throw new ValidationException(ResultCode.UPDATE_FAIL);
        }
        return i;
    }

    @Override
    public List<Dept> getList(DeptQueryDTO dto) throws ValidationException {
        List<Dept> lists = mapper.selectList(new QueryWrapper<Dept>()
                    .like(StringUtils.isNotBlank(dto.getDeptName()),"dept_name",dto.getDeptName())
                    .like(StringUtils.isNotBlank(dto.getDirector()),"director",dto.getDirector())
                    .like(StringUtils.isNotBlank(dto.getDeputyDirector()),"deputy_director",dto.getDeputyDirector())
                    .like(StringUtils.isNotBlank(dto.getProjectmanager()),"projectmanager",dto.getProjectmanager())
                    .like(StringUtils.isNotBlank(dto.getAddress()),"address",dto.getAddress())
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
