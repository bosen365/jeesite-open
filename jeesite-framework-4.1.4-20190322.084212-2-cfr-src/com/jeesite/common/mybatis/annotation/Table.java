/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.mybatis.annotation;	
	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.JoinTable;	
import java.lang.annotation.Annotation;	
import java.lang.annotation.Documented;	
import java.lang.annotation.ElementType;	
import java.lang.annotation.Retention;	
import java.lang.annotation.RetentionPolicy;	
import java.lang.annotation.Target;	
	
@Retention(value=RetentionPolicy.RUNTIME)	
@Target(value={ElementType.TYPE})	
@Documented	
public @interface Table {	
    public JoinTable[] joinTable() default {};	
	
    public String extFromKeys() default "";	
	
    public String comment() default "";	
	
    public Column[] columns();	
	
    public String name() default "";	
	
    public String extWhereKeys() default "";	
	
    public String orderBy() default "";	
	
    public String alias() default "a";	
	
    public String extColumnKeys() default "";	
}	
	
