/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.mybatis.annotation;	
	
import com.jeesite.common.mybatis.mapper.query.QueryType;	
import java.lang.annotation.Annotation;	
import java.lang.annotation.Documented;	
import java.lang.annotation.Retention;	
import java.lang.annotation.RetentionPolicy;	
import org.apache.ibatis.type.JdbcType;	
import org.apache.ibatis.type.TypeHandler;	
import org.apache.ibatis.type.UnknownTypeHandler;	
	
@Retention(value=RetentionPolicy.RUNTIME)	
@Documented	
public @interface Column {	
    public QueryType queryType() default QueryType.EQ;	
	
    public boolean isTreeName() default false;	
	
    public String comment() default "";	
	
    public String attrName() default "";	
	
    public String name() default "";	
	
    public boolean isUpdate() default true;	
	
    public JdbcType jdbcType() default JdbcType.UNDEFINED;	
	
    public boolean isQuery() default true;	
	
    public Class<?> javaType() default void.class;	
	
    public boolean isUpdateForce() default false;	
	
    public String label() default "";	
	
    public Class<?> includeEntity() default Class.class;	
	
    public boolean isInsert() default true;	
	
    public Class<? extends TypeHandler> typeHandler() default UnknownTypeHandler.class;	
	
    public boolean isPK() default false;	
}	
	
