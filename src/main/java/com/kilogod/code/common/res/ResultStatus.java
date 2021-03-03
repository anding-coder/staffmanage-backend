package com.kilogod.code.common.res;

/**
 * @Author Anding
 * @Desc
 */
public enum ResultStatus {
    SUCCESS_STATUS(ResultCode.SUCCESS_STATUS, "操作成功"),
    FAIL_STATUS(ResultCode.FAIL_STATUS, "操作失败"),
    NOT_LOGIN(ResultCode.NOT_LOGIN, "用户未登录或超时"),
    PARAM_ERROR(ResultCode.PARAM_ERROR, "请求参数错误"),
    MSG_CODE_ERROR(ResultCode.MSG_CODE_ERROR, "短信验证码错误"),
    TOKEN_ERROR(ResultCode.TOKEN_ERROR, "TOKEN错误"),
    OPENID_ERROR(ResultCode.OPENID_ERROR, "OPENID错误"),
    SYSTEM_ERROR(ResultCode.SYSTEM_ERROR, "系统内部错误"),
    LOGIN_ERROR(ResultCode.LOGIN_ERROR, "登陆错误"),
    SEARCH_FAIL(ResultCode.SEARCH_FAIL, "搜索接口获取数据失败"),
    WAP_FAIL(ResultCode.WAP_FAIL, "微信公众号返回数据错误"),
    INSERT_FAIL(ResultCode.INSERT_FAIL, "新增失败"),
    DELETE_FAIL(ResultCode.DELETE_FAIL, "删除失败"),
    UPDATE_FAIL(ResultCode.UPDATE_FAIL, "修改失败"),
    QUERY_FAIL(ResultCode.QUERY_FAIL, "查询失败"),
    TEMPLATE_EMPTY(ResultCode.TEMPLATE_EMPTY, "模板内容为空"),
    SELF_CODE_EXIST(ResultCode.SELF_CODE_EXIST, "用户个人识别码已存在"),
    CONTENT_NOT_NULL(ResultCode.CONTENT_NOT_NULL, "留言内容不能为空"),
    USERID_USERPASSWORD_FAIL(ResultCode.USERID_USERPASSWORD_FAIL, "用户名或密码错误"),
    CLASS_EXIST(ResultCode.CLASS_EXIST, "班级已存在"),
    COURSE_EXIST(ResultCode.COURSE_EXIST, "科目已存在"),
    QUERY_IS_NULL(ResultCode.QUERY_IS_NULL, "查询数据为空"),
    USER_NOT_NULL(ResultCode.USER_NOT_NULL, "用户名密码不能为空"),
    AUTH_NOT_ENOUGH(ResultCode.AUTH_NOT_ENOUGH, "权限不够"),
    ACCOUNT_NOT_LOGIN(ResultCode.ACCOUNT_NOT_LOGIN, "账号不允许登录，请联系管理员"),
    ;


    private String status;
    private String message;

    ResultStatus(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public static String getMessage(String status) {
        for (ResultStatus resultStatus : ResultStatus.values()) {
            if (resultStatus.getStatus().equals(status)) {
                return resultStatus.message;
            }
        }
        return "操作失败！";
    }
}

