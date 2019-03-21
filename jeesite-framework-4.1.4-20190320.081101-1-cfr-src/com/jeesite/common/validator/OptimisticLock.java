/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  javax.validation.Constraint	
 *  javax.validation.Payload	
 *  org.hibernate.validator.constraints.Length	
 */	
package com.jeesite.common.validator;	
	
import com.jeesite.common.validator.OptimisticLockValidator;	
import java.lang.annotation.Annotation;	
import java.lang.annotation.Documented;	
import java.lang.annotation.ElementType;	
import java.lang.annotation.Retention;	
import java.lang.annotation.RetentionPolicy;	
import java.lang.annotation.Target;	
import javax.validation.Constraint;	
import javax.validation.Payload;	
import org.hibernate.validator.constraints.Length;	
	
@Documented	
@Constraint(validatedBy={OptimisticLockValidator.class})	
@Target(value={ElementType.TYPE})	
@Retention(value=RetentionPolicy.RUNTIME)	
public @interface OptimisticLock {	
    public String message() default "\u8868\u5355\u5df2\u88ab\u5176\u5b83\u7528\u6237\u4fee\u6539\uff0c\u8bf7\u5237\u65b0\u9875\u9762\u540e\u518d\u63d0\u4ea4";	
	
    public Class<? extends Payload>[] payload() default {};	
	
    public Class<?>[] groups() default {};	
	
    @Target(value={ElementType.TYPE})	
    @Retention(value=RetentionPolicy.RUNTIME)	
    @Documented	
    public static @interface List {	
        public Length[] value();	
    }	
	
}	
	
