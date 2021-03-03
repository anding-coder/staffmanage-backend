package com.kilogod.code.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kilogod.code.common.res.ResultCode;
import com.kilogod.code.domain.Notice;
import com.kilogod.code.domain.dto.NoticeQueryDTO;
import com.kilogod.code.mapper.NoticeMapper;
import com.kilogod.code.service.INoticeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.bind.ValidationException;
import java.util.List;

/**
 * <p>
 * 公告 服务实现类
 * </p>
 *
 * @author Anding
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements INoticeService {

    @Resource
    private NoticeMapper mapper;

    @Override
    public int insert(Notice notice) throws ValidationException {
        int insert = mapper.insert(notice);
        if (insert < 1) {
            throw new ValidationException(ResultCode.INSERT_FAIL);
        }
        return insert;
    }

    @Override
    public int delete(String id) throws ValidationException {
        int i = mapper.delete(new QueryWrapper<Notice>().eq("id", id));
        if (i<1){
            throw new ValidationException(ResultCode.DELETE_FAIL);
        }
        return i;
    }

    @Override
    public int updateData(Notice notice) throws ValidationException {
        int i = mapper.updateById(notice);
        if (i<1){
            throw new ValidationException(ResultCode.UPDATE_FAIL);
        }
        return i;
    }

    @Override
    public List<Notice> getList(NoticeQueryDTO dto) throws ValidationException {
        List<Notice> lists = mapper.selectList(new QueryWrapper<Notice>()
                    .like(StringUtils.isNotBlank(dto.getDept()),"dept",dto.getDept())
                    .like(StringUtils.isNotBlank(dto.getContent()),"content",dto.getContent())
                    .like(StringUtils.isNotBlank(dto.getStatus()),"status",dto.getStatus())
                    .like(StringUtils.isNotBlank(dto.getCreateBy()),"create_by",dto.getCreateBy())
                    .like(StringUtils.isNotBlank(dto.getUpdateBy()),"update_by",dto.getUpdateBy())
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
