/*	
 * Decompiled with CFR 0.140.	
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
    public boolean isUpdateForce() default false;	
	
    public JdbcType jdbcType() default JdbcType.UNDEFINED;	
	
    public Class<? extends TypeHandler> typeHandler() default UnknownTypeHandler.class;	
	
    public String label() default "";	
	
    public boolean isTreeName() default false;	
	
    public boolean isUpdate() default true;	
	
    public Class<?> includeEntity() default Class.class;	
	
    public String name() default "";	
	
    public String comment() default "";	
	
    public boolean isQuery() default true;	
	
    public String attrName() default "";	
	
    public QueryType queryType() default QueryType.EQ;	
	
    public boolean isPK() default false;	
	
    public boolean isInsert() default true;	
	
    public Class<?> javaType() default void.class;	
}	
	
