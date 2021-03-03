package com.kilogod.code.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author anding
 * @Description:
 */
@Data
public class UserPwdDTO {
    @ApiModelProperty("用户id")
    private Integer userId;
    @ApiModelProperty("旧密码")
    private String oldpwd;
    @ApiModelProperty("新密码")
    private String newpwd;
}
