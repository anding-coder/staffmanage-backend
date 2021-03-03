package com.kilogod.code.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kilogod.code.common.res.ResultCode;
import com.kilogod.code.domain.Assets;
import com.kilogod.code.domain.dto.AssetsQueryDTO;
import com.kilogod.code.mapper.AssetsMapper;
import com.kilogod.code.service.IAssetsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.bind.ValidationException;
import java.util.List;

/**
 * <p>
 * 资产信息 服务实现类
 * </p>
 *
 * @author Anding
 */
@Service
public class AssetsServiceImpl extends ServiceImpl<AssetsMapper, Assets> implements IAssetsService {

    @Resource
    private AssetsMapper mapper;

    @Override
    public int insert(Assets assets) throws ValidationException {
        int insert = mapper.insert(assets);
        if (insert < 1) {
            throw new ValidationException(ResultCode.INSERT_FAIL);
        }
        return insert;
    }

    @Override
    public int delete(String id) throws ValidationException {
        int i = mapper.delete(new QueryWrapper<Assets>().eq("id", id));
        if (i<1){
            throw new ValidationException(ResultCode.DELETE_FAIL);
        }
        return i;
    }

    @Override
    public int updateData(Assets assets) throws ValidationException {
        int i = mapper.updateById(assets);
        if (i<1){
            throw new ValidationException(ResultCode.UPDATE_FAIL);
        }
        return i;
    }

    @Override
    public List<Assets> getList(AssetsQueryDTO dto) throws ValidationException {
        List<Assets> lists = mapper.selectList(new QueryWrapper<Assets>()
                    .like(StringUtils.isNotBlank(dto.getDept()),"dept",dto.getDept())
                    .like(StringUtils.isNotBlank(dto.getAssetsNo()),"assets_no",dto.getAssetsNo())
                    .like(StringUtils.isNotBlank(dto.getAssetsType()),"assets_type",dto.getAssetsType())
                    .like(StringUtils.isNotBlank(dto.getModel()),"model",dto.getModel())
                    .eq(null!=dto.getNum(),"num",dto.getNum())
                    .like(StringUtils.isNotBlank(dto.getUnit()),"unit",dto.getUnit())
                    .like(StringUtils.isNotBlank(dto.getUsername()),"username",dto.getUsername())
                    .eq(null!=dto.getStartTime(),"start_time",dto.getStartTime())
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
