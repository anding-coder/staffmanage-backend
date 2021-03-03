package com.kilogod.code.common.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author Anding
 * @Desc
 */
@Data
public class BaseQuery {
    /**
     * 页码
     */
    @ApiModelProperty("页码")
    private Integer page = 1;

    /**
     * 每页数量
     */
    @ApiModelProperty("每页数量")
    private Integer size = 10;
}
