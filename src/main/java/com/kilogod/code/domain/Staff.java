package com.kilogod.code.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.kilogod.code.config.autofill.CreateTime;
import com.kilogod.code.config.autofill.UpdateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 人员信息
 * </p>
 *
 * @author Anding
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Staff对象", description="人员信息")
public class Staff implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

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

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "毕业时间")
    private Date graduationDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "第一次参加工作的时间")
    private Date firstWorkdate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "进入本公司工作的时间")
    private Date companyWorkdate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "目前劳动合同开始的时间")
    private Date contractStarttime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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

    @ApiModelProperty(value = "创建时间")
    @CreateTime
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @UpdateTime
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @ApiModelProperty(value = "扩展字段")
    private String ext1;

    @ApiModelProperty(value = "扩展字段")
    private String ext2;

    @ApiModelProperty(value = "扩展字段")
    private String ext3;

    @ApiModelProperty(value = "扩展字段")
    private String ext4;


}
