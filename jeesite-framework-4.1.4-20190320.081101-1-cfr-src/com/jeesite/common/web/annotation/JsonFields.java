/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.common.web.annotation;	
	
import com.jeesite.common.web.annotation.JsonField;	
import java.lang.annotation.Annotation;	
import java.lang.annotation.ElementType;	
import java.lang.annotation.Retention;	
import java.lang.annotation.RetentionPolicy;	
import java.lang.annotation.Target;	
	
@Target(value={ElementType.METHOD})	
@Retention(value=RetentionPolicy.RUNTIME)	
public @interface JsonFields {	
    public JsonField[] value();	
}	
	
