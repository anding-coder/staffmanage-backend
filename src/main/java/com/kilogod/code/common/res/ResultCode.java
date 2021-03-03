package com.kilogod.code.common.res;

/**
 * @Author Anding
 * @Desc
 */
public interface ResultCode {

    /**
     * 成功状态码
     */
    String SUCCESS_STATUS = "200";

    /**
     * 统一失败状态码
     */
    String FAIL_STATUS = "201";

    /**
     * 用户未登录或超时状态码
     */
    String NOT_LOGIN = "301";

    /**
     * 请求参数错误状态码
     */
    String PARAM_ERROR = "202";

    /**
     * 短信验证码错误
     */
    String MSG_CODE_ERROR = "203";

    /**
     * TOKEN错误
     */
    String TOKEN_ERROR = "303";

    /**
     * OPENID错误
     */
    String OPENID_ERROR = "304";

    /**
     * 系统内部错误
     */
    String SYSTEM_ERROR = "500";

    /**
     * 登陆错误
     */
    String LOGIN_ERROR = "208";

    /**
     * 搜索接口获取数据失败
     */
    String SEARCH_FAIL = "502";

    /**
     * 微信公众号返回错误
     */
    String WAP_FAIL = "10001";

    /**
     * 新增失败
     */
    String INSERT_FAIL = "20001";

    /**
     * 删除失败
     */
    String DELETE_FAIL = "20002";

    /**
     * 修改失败
     */
    String UPDATE_FAIL = "20003";

    /**
     * 查询失败
     */
    String QUERY_FAIL = "20004";

    /**
     * 模板内容为空
     */
    String TEMPLATE_EMPTY = "20005";

    /**
     * 留言内容不能为空
     */
    String CONTENT_NOT_NULL  = "20006";

    /**
     * 查询数据为空
     */
    String QUERY_IS_NULL = "20007";

    /**
     * 用户个人识别码已存在
     */
    String SELF_CODE_EXIST = "30001";

    /**
     * 用户名或密码错误
     */
    String USERID_USERPASSWORD_FAIL  = "30002";

    /**
     * 班级已存在
     */
    String CLASS_EXIST  = "30003";

    /**
     * 班级已存在
     */
    String COURSE_EXIST  = "30004";

    /**
     * 用户名密码不能为空
     */
    String USER_NOT_NULL  = "30005";

    /**
     * 权限不够
     */
    String AUTH_NOT_ENOUGH  = "30006";
}
