package com.project.annotation;

import java.lang.annotation.*;

/**
 * @author 罗亚辉
 * @ClassName:
 * @Description:
 * @date 2019年06月04日 17:49
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface ClassType {
    String describe() default "";
}
