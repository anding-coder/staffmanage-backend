package com.kilogod.code.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author Anding
 * @Desc
 */
@Data
@ApiModel
public class UserDTO {

    @ApiModelProperty(value = "员工号")
    private String staffid;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("权限")
    private String role;

}
