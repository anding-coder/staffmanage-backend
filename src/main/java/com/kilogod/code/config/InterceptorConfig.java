package com.kilogod.code.config;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.kilogod.code.config.annotation.validateuser.LoginUserHandlerMethodArgumentResolver;
import com.kilogod.code.domain.vo.UserInfoVO;
import com.kilogod.code.service.IStaffService;
import com.kilogod.code.service.impl.StaffServiceImpl;
import com.kilogod.code.util.BeanTool;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Anding
 * @describe
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer, HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(InterceptorConfig.class);

    /**
     * 进入controller层之前拦截请求
     *
     * @param request
     * @param response
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) {
        if ("OPTIONS".equals(request.getMethod()) || "/login".equals(request.getRequestURI())||
                "/staff/list".equals(request.getRequestURI())||
                "/file/upload".equals(request.getRequestURI())||
                "/staff/insert".equals(request.getRequestURI())||
                "/checkToken".equals(request.getRequestURI())||
                "/logoutToken".equals(request.getRequestURI())||
                "/ip".equals(request.getRequestURI())) {
            return true;
        }
        //1.判断入参是否携带token
        String token = request.getHeader("Authorization");
        if (StringUtils.isBlank(token) || token.indexOf("X-KILOGOD-AUTHORIZATION") <= -1) {
            response.setStatus(401);
            return false;
        }
        //2.判断token是否注销
        token = token.split(" ")[1];
        if (!LogoutToken.searchQueue(token)) {
            log.info("---------注销token---------");
            response.setStatus(401);
            return false;
        }
        //3.核销token
        try {
            UserInfoVO userInfo = JWTToken.verifyToken(token);
            if (userInfo==null){
                return false;
            }
            StaffServiceImpl userService =(StaffServiceImpl) BeanTool.getBean(IStaffService.class);
            boolean b = userService.checkUserInfo(userInfo);
            if (!b){
                return false;
            }
        } catch (JWTDecodeException e) {
            response.setStatus(401);
            log.error("token验证错误");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
//        log.info("--------------处理请求完成后视图渲染之前的处理操作---------------");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
//        log.info("---------------视图渲染之后的操作-------------------------");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自定义拦截器，添加拦截路径和排除拦截路径
        registry.addInterceptor(new InterceptorConfig()).addPathPatterns("/**")
//                .excludePathPatterns("/login/**")
                .excludePathPatterns("/swagger-ui.html/**")
                .excludePathPatterns("/swagger-resources/**")
                .excludePathPatterns("/v2/**")
                .excludePathPatterns("/error")
                .excludePathPatterns("/error")
                .excludePathPatterns("/webjars/**")
                .excludePathPatterns("/upload/updateUploadFileInfo")
//                .excludePathPatterns("/upload/downloadFile")
                .excludePathPatterns("/getCodeType")
                .excludePathPatterns("/**/favicon.ico");
    }

    @Bean
    public LoginUserHandlerMethodArgumentResolver loginUserHandlerMethodArgumentResolver() {
        return new LoginUserHandlerMethodArgumentResolver();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(loginUserHandlerMethodArgumentResolver());
    }
}

