package com.jeesite.common.validator;	
	
import java.lang.annotation.Documented;	
import java.lang.annotation.ElementType;	
import java.lang.annotation.Retention;	
import java.lang.annotation.RetentionPolicy;	
import java.lang.annotation.Target;	
import javax.validation.Constraint;	
import org.hibernate.validator.constraints.Length;	
	
@Documented	
@Constraint(	
   validatedBy = {OptimisticLockValidator.class}	
)	
@Target({ElementType.TYPE})	
@Retention(RetentionPolicy.RUNTIME)	
public @interface OptimisticLock {	
   String message() default "表单已被其它用户修改，请刷新页面后再提交";	
	
   Class[] payload() default {};	
	
   Class[] groups() default {};	
	
   @Target({ElementType.TYPE})	
   @Retention(RetentionPolicy.RUNTIME)	
   @Documented	
   public @interface List {	
      Length[] value();	
   }	
}	
