package com.kilogod.code.config.annotation.validateuser;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.kilogod.code.config.JWTToken;
import com.kilogod.code.domain.vo.UserInfoVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.xml.bind.ValidationException;

/**
 * @author anding
 * @Description:
 */
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().isAssignableFrom(UserInfoVO.class) && methodParameter.hasParameterAnnotation(ValidateUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {

        String token = nativeWebRequest.getHeader("Authorization");
        if (StringUtils.isBlank(token) || token.indexOf("X-KILOGOD-AUTHORIZATION") <= -1) {
            throw new ValidationException("token有误！");
        }
        token = token.split(" ")[1];
        UserInfoVO userInfo = null;
        try {
            userInfo = JWTToken.verifyToken(token);
        } catch (JWTDecodeException e) {
            e.printStackTrace();
        }catch (NullPointerException e) {
            e.printStackTrace();
        }
        return userInfo;
    }
}
