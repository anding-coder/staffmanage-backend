package com.kilogod.code.domain.vo;

import lombok.Data;

/**
 * @Author Anding
 * @Desc
 */
@Data
public class UserVO {
    private String token;
    private String deadline;
    private UserInfoVO userInfo;


    public UserInfoVO getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoVO userInfo) {
        this.userInfo = userInfo;
    }
}

