package com.kilogod.code.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kilogod.code.common.res.ResultData;
import com.kilogod.code.config.annotation.validateuser.ValidateUser;
import com.kilogod.code.domain.Staff;
import com.kilogod.code.domain.dto.StaffQueryDTO;
import com.kilogod.code.domain.vo.UserInfoVO;
import com.kilogod.code.service.IStaffService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;

/**
 * <p>
 * 人员信息 前端控制器
 * </p>
 *
 * @author Anding
 */
@Slf4j
@Api(tags = {"人员信息"})
@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private IStaffService staffService;

    @GetMapping("/getSelfUser")
    @ApiOperation(value = "查询个人信息")
    public ResultData<Staff> getSelfUser(@ValidateUser UserInfoVO vo){
        ResultData rc = new ResultData();
        try {
            Staff staff = staffService.getSelfUser(vo);
            rc.setData(staff);
        } catch (ValidationException e) {
            rc.setError(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            rc.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return rc;
    }

    @PostMapping("/insert")
    @ApiOperation(value = "新增人员信息")
    public ResultData insert(@RequestBody Staff staff){
        ResultData rc = new ResultData();
        try {
            rc.setData(staffService.insert(staff));
        } catch (ValidationException e) {
            rc.setError(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            rc.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return rc;
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除人员信息")
    public ResultData delete(@RequestParam String id){
        ResultData rc = new ResultData();
        try {
            rc.setData(staffService.delete(id));
        } catch (ValidationException e) {
            rc.setError(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            rc.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return rc;
    }

    @PutMapping("/update")
    @ApiOperation(value = "更新人员信息")
    public ResultData update(@RequestBody Staff staff){
        ResultData rc = new ResultData();
        try {
            rc.setData(staffService.updateData(staff));
        } catch (ValidationException e) {
            rc.setError(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            rc.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return rc;
    }

    @GetMapping("/list")
    @ApiOperation(value = "查询人员信息分页数据")
    public ResultData<PageInfo<Staff>> getList(StaffQueryDTO dto){
        ResultData rc = new ResultData();
        try {
            PageHelper.startPage(dto.getPage(), dto.getSize());
            List<Staff> list = staffService.getList(dto);
            PageInfo<Staff> data = new PageInfo<>(list);
            rc.setData(data);
        } catch (ValidationException e) {
            rc.setError(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            rc.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return rc;
    }

    @PostMapping("/batchDelete")
    @ApiOperation(value = "批量删除人员信息")
    public ResultData batchDelete(@RequestBody List<Integer> ids) {
        ResultData rc = new ResultData();
        try {
            rc.setData(staffService.batchDelete(ids));
        } catch (ValidationException e) {
            rc.setError(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            rc.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return rc;
    }

}
