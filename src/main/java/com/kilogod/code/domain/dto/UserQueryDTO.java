package com.kilogod.code.domain.dto;

import com.kilogod.code.common.query.BaseQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author honey
 * @description
 */
@Data
public class UserQueryDTO extends BaseQuery {
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "手机")
    private String phone;
    @ApiModelProperty(value = "用户角色")
    private String role;
}
