package com.kilogod.code.util;

import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author anding
 * @Description:
 */
public class MapUtils {

    public static Map<String, Object> objectToMap(Object obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = field.get(obj);
            map.put(fieldName, value);
        }
        return map;
    }

    /**
     * @Author anding
     * @Date 2020/9/18
     * @Param [paramMap, cls]
     * @return T
     * @Description 将map转为对象
     **/
    public static <T> T parseMap2Object(Map<String, Object> paramMap, Class<T> cls) {

        return JSONObject.parseObject(JSONObject.toJSONString(paramMap), cls);

    }
}
