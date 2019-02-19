package com.jeesite.common.mybatis.annotation;	
	
import com.jeesite.common.mybatis.mapper.query.QueryType;	
import java.lang.annotation.Documented;	
import java.lang.annotation.Retention;	
import java.lang.annotation.RetentionPolicy;	
import org.apache.ibatis.type.JdbcType;	
import org.apache.ibatis.type.UnknownTypeHandler;	
	
@Retention(RetentionPolicy.RUNTIME)	
@Documented	
public @interface Column {	
   String name() default "";	
	
   QueryType queryType() default QueryType.EQ;	
	
   JdbcType jdbcType() default JdbcType.UNDEFINED;	
	
   Class includeEntity() default Class.class;	
	
   Class typeHandler() default UnknownTypeHandler.class;	
	
   Class javaType() default void.class;	
	
   boolean isUpdate() default true;	
	
   String attrName() default "";	
	
   boolean isTreeName() default false;	
	
   String comment() default "";	
	
   boolean isPK() default false;	
	
   String label() default "";	
	
   boolean isQuery() default true;	
	
   boolean isInsert() default true;	
	
   boolean isUpdateForce() default false;	
}	
