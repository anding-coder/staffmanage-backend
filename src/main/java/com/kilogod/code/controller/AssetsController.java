package com.kilogod.code.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kilogod.code.common.res.ResultData;
import com.kilogod.code.domain.Assets;
import com.kilogod.code.domain.dto.AssetsQueryDTO;
import com.kilogod.code.service.IAssetsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;

/**
 * <p>
 * 资产信息 前端控制器
 * </p>
 *
 * @author Anding
 */
@Slf4j
@Api(tags = {"资产信息"})
@RestController
@RequestMapping("/assets")
public class AssetsController {

    @Autowired
    private IAssetsService assetsService;


    @PostMapping("/insert")
    @ApiOperation(value = "新增资产信息")
    public ResultData insert(@RequestBody Assets assets){
        ResultData rc = new ResultData();
        try {
            rc.setData(assetsService.insert(assets));
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
    @ApiOperation(value = "删除资产信息")
    public ResultData delete(@RequestParam String id){
        ResultData rc = new ResultData();
        try {
            rc.setData(assetsService.delete(id));
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
    @ApiOperation(value = "更新资产信息")
    public ResultData update(@RequestBody Assets assets){
        ResultData rc = new ResultData();
        try {
            rc.setData(assetsService.updateData(assets));
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
    @ApiOperation(value = "查询资产信息分页数据")
    public ResultData<PageInfo<Assets>> getList(AssetsQueryDTO dto){
        ResultData rc = new ResultData();
        try {
            PageHelper.startPage(dto.getPage(), dto.getSize());
            List<Assets> list = assetsService.getList(dto);
            PageInfo<Assets> data = new PageInfo<>(list);
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
    @ApiOperation(value = "批量删除资产信息")
    public ResultData batchDelete(@RequestBody List<Integer> ids) {
        ResultData rc = new ResultData();
        try {
            rc.setData(assetsService.batchDelete(ids));
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
