package com.jeesite.common.mybatis.annotation;	
	
import java.lang.annotation.Documented;	
import java.lang.annotation.ElementType;	
import java.lang.annotation.Retention;	
import java.lang.annotation.RetentionPolicy;	
import java.lang.annotation.Target;	
	
@Retention(RetentionPolicy.RUNTIME)	
@Target({ElementType.TYPE})	
@Documented	
public @interface Table {	
   String extColumnKeys() default "";	
	
   String alias() default "a";	
	
   JoinTable[] joinTable() default {};	
	
   String orderBy() default "";	
	
   String extWhereKeys() default "";	
	
   String comment() default "";	
	
   Column[] columns();	
	
   String name() default "";	
	
   String extFromKeys() default "";	
}	
