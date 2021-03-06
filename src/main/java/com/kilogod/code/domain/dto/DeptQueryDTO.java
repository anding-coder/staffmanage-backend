package com.kilogod.code.domain.dto;

import com.kilogod.code.common.query.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 部门信息
 * </p>
 *
 * @author Anding
 */
@Data
@ApiModel(value="DeptQueryDTO查询对象", description="部门信息查询入参")
public class DeptQueryDTO extends BaseQuery {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "部门名称")
    private String deptName;

    @ApiModelProperty(value = "部门主任")
    private String director;

    @ApiModelProperty(value = "部门副主任")
    private String deputyDirector;

    @ApiModelProperty(value = "部门项目经理")
    private String projectmanager;

    @ApiModelProperty(value = "部门地址")
    private String address;






}
