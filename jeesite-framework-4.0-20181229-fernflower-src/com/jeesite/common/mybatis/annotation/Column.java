package com.jeesite.common.mybatis.annotation;	
	
import com.jeesite.common.mybatis.mapper.query.QueryType;	
import java.lang.annotation.Documented;	
import java.lang.annotation.Retention;	
import java.lang.annotation.RetentionPolicy;	
	
@Retention(RetentionPolicy.RUNTIME)	
@Documented	
public @interface Column {	
   String comment() default "";	
	
   Class type() default Class.class;	
	
   String attrName() default "";	
	
   boolean isUpdate() default true;	
	
   boolean isPK() default false;	
	
   boolean isQuery() default true;	
	
   String label() default "";	
	
   QueryType queryType() default QueryType.EQ;	
	
   String name() default "";	
	
   boolean isInsert() default true;	
	
   boolean isTreeName() default false;	
	
   Class includeEntity() default Class.class;	
}	
