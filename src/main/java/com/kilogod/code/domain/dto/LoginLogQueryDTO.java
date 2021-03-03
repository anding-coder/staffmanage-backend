package com.kilogod.code.domain.dto;

import com.kilogod.code.common.query.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 登录日志
 * </p>
 *
 * @author Anding
 */
@Data
@ApiModel(value="LoginLogQueryDTO查询对象", description="登录日志查询入参")
public class LoginLogQueryDTO extends BaseQuery {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "手机")
    private String phone;

    @ApiModelProperty(value = "IP地址")
    private String ip;

    @ApiModelProperty(value = "地址")
    private String address;





}
