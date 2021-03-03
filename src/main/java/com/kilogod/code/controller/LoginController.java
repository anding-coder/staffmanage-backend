package com.kilogod.code.controller;

import com.kilogod.code.common.res.ResultData;
import com.kilogod.code.config.JWTToken;
import com.kilogod.code.config.LogoutToken;
import com.kilogod.code.config.annotation.validateuser.ValidateUser;
import com.kilogod.code.domain.Staff;
import com.kilogod.code.domain.dto.UserDTO;
import com.kilogod.code.domain.dto.UserPwdDTO;
import com.kilogod.code.domain.vo.UserInfoVO;
import com.kilogod.code.domain.vo.UserVO;
import com.kilogod.code.service.IStaffService;
import com.kilogod.code.util.IpUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.ValidationException;
import java.io.IOException;
import java.util.Map;

/**
 * @author honey
 * @description
 */
@Api(tags = "登录")
@RestController
public class LoginController {
    public static final String AUTHORIZATION = "Authorization";

    @Autowired
    private IStaffService userService;

    @ApiOperation("获取ip地址")
    @GetMapping("/ip")
    public String getIp(HttpServletRequest request) throws IOException {
        String ip = IpUtils.getIpAddr(request);
        String address = IpUtils.getCityInfo(ip);
        return "ip为："+ip+"，访问地址为："+address;
    }

    @ApiOperation("登录接口")
    @PostMapping("/login")
    public ResultData login(@RequestBody UserDTO userDTO, HttpServletRequest request) {
        ResultData rc = new ResultData();
        try {
            UserVO loginInfo = userService.getLoginInfo(userDTO,request);
            rc.setData(loginInfo);
        } catch (ValidationException e) {
            rc.setError(e.getMessage());
        } catch (Exception e) {
            rc.setErrorMsg("用户名或密码错误！");
        }
        return rc;
    }

    @ApiOperation(value = "注销登录token信息")
    @GetMapping(value = "/logoutToken")
    public ResultData logoutToken(HttpServletRequest request) {
        ResultData rc = new ResultData();
        try {
            String header = request.getHeader(AUTHORIZATION);
            if (StringUtils.isBlank(header)) {
                throw new ValidationException("token为空！");
            }
            LogoutToken.addQueue(header.split(" ")[1]);
            rc.setData(header);
        } catch (ValidationException e) {
            rc.setError(e.getMessage());
        } catch (Exception e) {
            rc.setErrorMsg("token为空！");
        }
        return rc;
    }

    @PostMapping("/checkToken")
    @ApiOperation("验证token信息")
    public ResultData checkToken(@RequestBody Map<String,String> map){
        ResultData rc = new ResultData();
        try{
            UserInfoVO userinfo = JWTToken.verifyToken(map.get("token"));
            if (userinfo==null){
                rc.setErrorMsg("token有误！");
                return rc;
            }
            boolean b = userService.checkUserInfo(userinfo);
            if (!b){
                rc.setErrorMsg("token有误！");
            }else{
                rc.setData(true);
            }
        }catch(Exception e){
            rc.setErrorMsg(e.getMessage());
        }
        return rc;
    }

    @PutMapping("/updatePwd")
    @ApiOperation(value = "修改密码")
    public ResultData updatePwd(@RequestBody UserPwdDTO dto, @ValidateUser UserInfoVO vo){
        ResultData rc = new ResultData();
        try {
            dto.setUserId(vo.getId());
            rc.setData(userService.updatePwd(dto));
        } catch (ValidationException e) {
            rc.setError(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            rc.setError();
            e.printStackTrace();
        }
        return rc;
    }

    @PutMapping("/resetPwd")
    @ApiOperation(value = "重置密码")
    public ResultData resetPwd(@RequestBody Staff staff){
        ResultData rc = new ResultData();
        try {
            rc.setData(userService.resetPwd(staff));
        } catch (ValidationException e) {
            rc.setError(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            rc.setError();
            e.printStackTrace();
        }
        return rc;
    }
}
