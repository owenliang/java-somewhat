package cc.yuerblog.annotation;

import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;

/**
 * 用于配置路由的注解，用于类方法
 * @author liangdong
 *
 */
@Target(ElementType.METHOD)	
@Retention(RetentionPolicy.RUNTIME)
public @interface Uri {
	String path() default "/";
}