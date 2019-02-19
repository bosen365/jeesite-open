package com.jeesite.common.web.annotation;	
	
import java.lang.annotation.ElementType;	
import java.lang.annotation.Retention;	
import java.lang.annotation.RetentionPolicy;	
import java.lang.annotation.Target;	
	
@Target({ElementType.METHOD})	
@Retention(RetentionPolicy.RUNTIME)	
public @interface JsonField {	
   Class type();	
	
   String[] filter() default {""};	
	
   String[] include() default {""};	
}	
