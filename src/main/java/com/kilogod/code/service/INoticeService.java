package com.kilogod.code.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kilogod.code.domain.Notice;
import com.kilogod.code.domain.dto.NoticeQueryDTO;

import javax.xml.bind.ValidationException;
import java.util.List;

/**
 * <p>
 * 公告 服务类
 * </p>
 *
 * @author Anding
 */
public interface INoticeService extends IService<Notice> {

    /**
    * 添加公告
    *
    * @param notice 公告
    * @return int
    * @throws ValidationException
    */
    int insert(Notice notice) throws ValidationException;

    /**
    * 删除公告
    *
    * @param id 主键
    * @return int
    * @throws ValidationException
    */
    int delete(String id) throws ValidationException;

    /**
    * 修改公告
    *
    * @param notice 公告
    * @return int
    * @throws ValidationException
    */
    int updateData(Notice notice) throws ValidationException;

    /**
     * 查询公告分页数据
     * @return List<Notice>
     */
    List<Notice> getList(NoticeQueryDTO dto) throws ValidationException;

    /**
    * 批量删除公告
    * @param ids
    * @return
    * @throws ValidationException
    */
    int batchDelete(List<Integer> ids) throws ValidationException;

}
