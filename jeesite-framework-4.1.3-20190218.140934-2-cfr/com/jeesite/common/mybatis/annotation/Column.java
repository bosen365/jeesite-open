/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  org.apache.ibatis.type.JdbcType	
 *  org.apache.ibatis.type.TypeHandler	
 *  org.apache.ibatis.type.UnknownTypeHandler	
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
    public String name() default "";	
	
    public QueryType queryType() default QueryType.EQ;	
	
    public JdbcType jdbcType() default JdbcType.UNDEFINED;	
	
    public Class<?> includeEntity() default Class.class;	
	
    public Class<? extends TypeHandler> typeHandler() default UnknownTypeHandler.class;	
	
    public Class<?> javaType() default void.class;	
	
    public boolean isUpdate() default true;	
	
    public String attrName() default "";	
	
    public boolean isTreeName() default false;	
	
    public String comment() default "";	
	
    public boolean isPK() default false;	
	
    public String label() default "";	
	
    public boolean isQuery() default true;	
	
    public boolean isInsert() default true;	
	
    public boolean isUpdateForce() default false;	
}	
	
