package com.kilogod.code.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kilogod.code.domain.Staff;
import com.kilogod.code.domain.dto.StaffQueryDTO;
import com.kilogod.code.domain.dto.UserDTO;
import com.kilogod.code.domain.dto.UserPwdDTO;
import com.kilogod.code.domain.vo.UserInfoVO;
import com.kilogod.code.domain.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.ValidationException;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 人员信息 服务类
 * </p>
 *
 * @author Anding
 */
public interface IStaffService extends IService<Staff> {

    /**
    * 添加人员信息
    *
    * @param staff 人员信息
    * @return int
    * @throws ValidationException
    */
    int insert(Staff staff) throws ValidationException;

    /**
    * 删除人员信息
    *
    * @param id 主键
    * @return int
    * @throws ValidationException
    */
    int delete(String id) throws ValidationException;

    /**
    * 修改人员信息
    *
    * @param staff 人员信息
    * @return int
    * @throws ValidationException
    */
    int updateData(Staff staff) throws ValidationException;

    /**
     * 查询人员信息分页数据
     * @return List<Staff>
     */
    List<Staff> getList(StaffQueryDTO dto) throws ValidationException;

    /**
    * 批量删除人员信息
    * @param ids
    * @return
    * @throws ValidationException
    */
    int batchDelete(List<Integer> ids) throws ValidationException;

    /**
     * @Author anding
     * @Date 2020/12/13 11:48
     * @Description 登录
     **/
    UserVO getLoginInfo(UserDTO userDTO, HttpServletRequest request) throws ValidationException, IOException;
    /**
     * @Author anding
     * @Date 2020/12/13 11:48
     * @Description 检查登录信息
     **/
    boolean checkUserInfo(UserInfoVO userInfo);

    /**
     * @Author anding
     * @Date 2020/12/11 0:35
     * @Description 修改用户密码
     **/
    int updatePwd(UserPwdDTO dto) throws ValidationException;
}
