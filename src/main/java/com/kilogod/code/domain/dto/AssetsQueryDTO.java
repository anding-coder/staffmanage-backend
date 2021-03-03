package com.kilogod.code.domain.dto;

import com.kilogod.code.common.query.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

/**
 * <p>
 * 资产信息
 * </p>
 *
 * @author Anding
 */
@Data
@ApiModel(value="AssetsQueryDTO查询对象", description="资产信息查询入参")
public class AssetsQueryDTO extends BaseQuery {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "使用部门")
    private String dept;

    @ApiModelProperty(value = "资产编号")
    private String assetsNo;

    @ApiModelProperty(value = "资产类型")
    private String assetsType;

    @ApiModelProperty(value = "规格型号")
    private String model;

    @ApiModelProperty(value = "数量")
    private Integer num;

    @ApiModelProperty(value = "单位")
    private String unit;

    @ApiModelProperty(value = "使用人")
    private String username;

    @ApiModelProperty(value = "开始使用时间")
    private LocalDate startTime;






}
