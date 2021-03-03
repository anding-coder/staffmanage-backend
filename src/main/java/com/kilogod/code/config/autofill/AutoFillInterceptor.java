package com.kilogod.code.config.autofill;

import groovy.transform.EqualsAndHashCode;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Properties;

/**
 * @Author Anding
 * @Desc 字段的自动填充拦截器
 */
@Component
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Intercepts({@Signature(type = org.apache.ibatis.executor.Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class AutoFillInterceptor implements Interceptor {

    private Properties properties;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];

        /** 获取 SQL 命令*/
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();

        /** 获取参数 */
        Object parameter = invocation.getArgs()[1];
        if (SqlCommandType.UPDATE.equals(sqlCommandType)){
            parameter=((MapperMethod.ParamMap) parameter).get("param1");
        }

        if(parameter!=null) {
            /** 获取私有成员变量 */
            Field[] declaredFields = parameter.getClass().getDeclaredFields();
            if (parameter.getClass().getSuperclass() != null) {
                Field[] superField = parameter.getClass().getSuperclass().getDeclaredFields();
                declaredFields = ArrayUtils.addAll(declaredFields, superField);
            }

            for (Field field : declaredFields) {

                /** insert */
                if (field.getAnnotation(CreateTime.class) != null) {
                    insert(sqlCommandType, parameter, field, new Date());
                }

                /** update */
                if (field.getAnnotation(UpdateTime.class) != null) {
                    insertOrUpdate(sqlCommandType, parameter, field, new Date());
                }
                //TODO 创建人
                if (field.getAnnotation(CreateBy.class) != null) {
                    insert(sqlCommandType, parameter, field, "honey");
                }
                //TODO 修改人
                if (field.getAnnotation(UpdateBy.class) != null) {
                    insertOrUpdate(sqlCommandType, parameter, field, "Anding");
                }
            }
        }

        return invocation.proceed();
    }

    private void insertOrUpdate(SqlCommandType sqlCommandType, Object parameter, Field field, Object value) throws IllegalAccessException {
        if (SqlCommandType.INSERT.equals(sqlCommandType) || SqlCommandType.UPDATE.equals(sqlCommandType)) {
            field.setAccessible(true);
            field.set(parameter, value);
        }
    }

    private void insert(SqlCommandType sqlCommandType, Object parameter, Field field, Object value) throws IllegalAccessException {
        if (SqlCommandType.INSERT.equals(sqlCommandType)) {
            field.setAccessible(true);
            field.set(parameter, value);
        }
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof org.apache.ibatis.executor.Executor) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties prop) {
        this.properties = prop;
    }
}
