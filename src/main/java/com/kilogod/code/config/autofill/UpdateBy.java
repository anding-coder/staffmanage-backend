package com.kilogod.code.config.autofill;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author Anding
 * @Desc 修改人
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( {ElementType.FIELD})
public @interface UpdateBy {
}
