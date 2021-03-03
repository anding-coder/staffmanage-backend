package com.kilogod.code.domain.dto;

import com.kilogod.code.common.query.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 公告
 * </p>
 *
 * @author Anding
 */
@Data
@ApiModel(value="NoticeQueryDTO查询对象", description="公告查询入参")
public class NoticeQueryDTO extends BaseQuery {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "通知部门")
    private String dept;

    @ApiModelProperty(value = "消息内容")
    private String content;

    @ApiModelProperty(value = "状态")
    private String status;



    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "修改人")
    private String updateBy;




}
