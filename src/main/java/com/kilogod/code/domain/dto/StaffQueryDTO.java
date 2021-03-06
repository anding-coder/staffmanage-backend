package com.kilogod.code.domain.dto;

import com.kilogod.code.common.query.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 人员信息
 * </p>
 *
 * @author Anding
 */
@Data
@ApiModel(value="StaffQueryDTO查询对象", description="人员信息查询入参")
public class StaffQueryDTO extends BaseQuery {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "员工号")
    private String staffid;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "部门")
    private String dept;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "身份证号码")
    private String idcard;

    @ApiModelProperty(value = "籍贯")
    private String nativeplace;

    @ApiModelProperty(value = "户口所在地")
    private String residence;

    @ApiModelProperty(value = "政治面貌")
    private String political;

    @ApiModelProperty(value = "全日制最高学历")
    private String education;

    @ApiModelProperty(value = "毕业时间")
    private Date graduationDate;

    @ApiModelProperty(value = "第一次参加工作的时间")
    private Date firstWorkdate;

    @ApiModelProperty(value = "进入本公司工作的时间")
    private String companyWorkdate;

    @ApiModelProperty(value = "目前劳动合同开始的时间")
    private Date contractStarttime;

    @ApiModelProperty(value = "目前劳动合同结束时间")
    private Date contractEndtime;

    @ApiModelProperty(value = "距离劳动合同结束时间")
    private String endtime;

    @ApiModelProperty(value = "拥有公司资产设备编号")
    private String equipment;

    @ApiModelProperty(value = "是否在职 1:是 0:否")
    private String onJob;

    @ApiModelProperty(value = "是否删除 1:是 0:否")
    private String isDel;

    @ApiModelProperty(value = "用户角色")
    private String role;


    @ApiModelProperty(value = "合同编号")
    private String ext1;

    @ApiModelProperty(value = "合同存放地址")
    private String ext2;





}
