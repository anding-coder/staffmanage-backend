package com.kilogod.code.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kilogod.code.common.res.ResultCode;
import com.kilogod.code.config.JWTToken;
import com.kilogod.code.domain.LoginLog;
import com.kilogod.code.domain.Staff;
import com.kilogod.code.domain.dto.StaffQueryDTO;
import com.kilogod.code.domain.dto.UserDTO;
import com.kilogod.code.domain.dto.UserPwdDTO;
import com.kilogod.code.domain.vo.UserInfoVO;
import com.kilogod.code.domain.vo.UserVO;
import com.kilogod.code.mapper.LoginLogMapper;
import com.kilogod.code.mapper.StaffMapper;
import com.kilogod.code.service.IStaffService;
import com.kilogod.code.util.IpUtils;
import com.kilogod.code.util.MD5Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.ValidationException;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 人员信息 服务实现类
 * </p>
 *
 * @author Anding
 */
@Service
public class StaffServiceImpl extends ServiceImpl<StaffMapper, Staff> implements IStaffService {

    @Resource
    private StaffMapper mapper;
    @Resource
    private LoginLogMapper logMapper;

    @Override
    public int insert(Staff staff) throws ValidationException {
        if (StringUtils.isBlank(staff.getPassword())){
            staff.setPassword(MD5Utils.encrypt(staff.getStaffid(),"123456"));
        }else {
            staff.setPassword(MD5Utils.encrypt(staff.getStaffid(),staff.getPassword()));
        }
        int insert = mapper.insert(staff);
        if (insert < 1) {
            throw new ValidationException(ResultCode.INSERT_FAIL);
        }
        return insert;
    }

    @Override
    public int delete(String id) throws ValidationException {
        int i = mapper.delete(new QueryWrapper<Staff>().eq("id", id));
        if (i<1){
            throw new ValidationException(ResultCode.DELETE_FAIL);
        }
        return i;
    }

    @Override
    public int updateData(Staff staff) throws ValidationException {
        int i = mapper.updateById(staff);
        if (i<1){
            throw new ValidationException(ResultCode.UPDATE_FAIL);
        }
        return i;
    }

    @Override
    public List<Staff> getList(StaffQueryDTO dto) throws ValidationException {
        List<Staff> lists = mapper.selectList(new QueryWrapper<Staff>()
                    .like(StringUtils.isNotBlank(dto.getStaffid()),"staffid",dto.getStaffid())
                    .like(StringUtils.isNotBlank(dto.getPassword()),"password",dto.getPassword())
                    .like(StringUtils.isNotBlank(dto.getName()),"name",dto.getName())
                    .like(StringUtils.isNotBlank(dto.getSex()),"sex",dto.getSex())
                    .like(StringUtils.isNotBlank(dto.getDept()),"dept",dto.getDept())
                    .like(StringUtils.isNotBlank(dto.getEmail()),"email",dto.getEmail())
                    .like(StringUtils.isNotBlank(dto.getPhone()),"phone",dto.getPhone())
                    .like(StringUtils.isNotBlank(dto.getIdcard()),"idcard",dto.getIdcard())
                    .like(StringUtils.isNotBlank(dto.getNativeplace()),"nativeplace",dto.getNativeplace())
                    .like(StringUtils.isNotBlank(dto.getResidence()),"residence",dto.getResidence())
                    .like(StringUtils.isNotBlank(dto.getPolitical()),"political",dto.getPolitical())
                    .like(StringUtils.isNotBlank(dto.getEducation()),"education",dto.getEducation())
                    .eq(null!=dto.getGraduationDate(),"graduation_date",dto.getGraduationDate())
                    .eq(null!=dto.getFirstWorkdate(),"first_workdate",dto.getFirstWorkdate())
                    .eq(null!=dto.getCompanyWorkdate(),"company_workdate",dto.getCompanyWorkdate())
                    .eq(null!=dto.getContractStarttime(),"contract_starttime",dto.getContractStarttime())
                    .eq(null!=dto.getContractEndtime(),"contract_endtime",dto.getContractEndtime())
                    .like(StringUtils.isNotBlank(dto.getEndtime()),"endtime",dto.getEndtime())
                    .like(StringUtils.isNotBlank(dto.getEquipment()),"equipment",dto.getEquipment())
                    .like(StringUtils.isNotBlank(dto.getOnJob()),"on_job",dto.getOnJob())
                    .like(StringUtils.isNotBlank(dto.getIsDel()),"is_del",dto.getIsDel())
                    .like(StringUtils.isNotBlank(dto.getRole()),"role",dto.getRole())
        );
        return lists;
    }

    @Override
    public int batchDelete(List<Integer> ids) throws ValidationException {
        int i = mapper.deleteBatchIds(ids);
        if (i<1){
            throw new ValidationException(ResultCode.DELETE_FAIL);
        }
        return i;
    }

    @Override
    public UserVO getLoginInfo(UserDTO userDTO, HttpServletRequest request) throws ValidationException, IOException {
        if (StringUtils.isBlank(userDTO.getStaffid())||StringUtils.isBlank(userDTO.getPassword())){
            throw new ValidationException("用户名密码不能为空！");
        }
        Staff user = mapper.selectOne(new QueryWrapper<Staff>().eq("staffid", userDTO.getStaffid()).eq("role",userDTO.getRole()));
        if (null==user||"".equals(user)){
            throw new ValidationException(ResultCode.USERID_USERPASSWORD_FAIL);
        }
        if (!MD5Utils.encrypt(userDTO.getStaffid(),userDTO.getPassword()).equals(user.getPassword())){
            throw new ValidationException(ResultCode.USERID_USERPASSWORD_FAIL);
        }
        UserInfoVO userInfo=new UserInfoVO();
        userInfo.setId(user.getId());
        userInfo.setName(user.getName());
        userInfo.setEmail(user.getEmail());
        userInfo.setStaffid(user.getStaffid());
        userInfo.setRole(user.getRole());
        userInfo.setPhone(user.getPhone());

        String token = JWTToken.createToken(userInfo);
        UserVO userVO=new UserVO();
        userVO.setToken(token);
        userVO.setDeadline(JWTToken.getDeadline(token));
        userVO.setUserInfo(userInfo);

        LoginLog loginLog=new LoginLog();
        String ipAddr = IpUtils.getIpAddr(request);
        String cityInfo = IpUtils.getCityInfo(ipAddr);
        loginLog.setIp(ipAddr);
        loginLog.setAddress(cityInfo);
        loginLog.setPhone(user.getPhone());
        loginLog.setUsername(user.getStaffid());
        loginLog.setName(user.getName());
        logMapper.insert(loginLog);

        return userVO;
    }

    @Override
    public boolean checkUserInfo(UserInfoVO userInfo) {
        Staff user = mapper.selectOne(new QueryWrapper<Staff>().eq("id", userInfo.getId()).eq("staffid", userInfo.getStaffid()));
        if (user==null){
            return false;
        }
        return true;
    }

    @Override
    public int updatePwd(UserPwdDTO dto) throws ValidationException {
        Staff user = mapper.selectOne(new QueryWrapper<Staff>().eq("id", dto.getUserId()));
        String old = MD5Utils.encrypt(user.getStaffid(), dto.getOldpwd());
        if (!old.equals(user.getPassword())){
            throw new ValidationException(ResultCode.USERID_USERPASSWORD_FAIL);
        }
        Staff exUser=new Staff();
        exUser.setId(dto.getUserId());
        exUser.setPassword(MD5Utils.encrypt(user.getStaffid(), dto.getNewpwd()));
        int update = mapper.updateById(exUser);
        if (update<1){
            throw new ValidationException(ResultCode.UPDATE_FAIL);
        }
        return update;
    }
}
