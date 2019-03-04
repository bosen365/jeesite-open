package com.jeesite.common.validator;	
	
import com.jeesite.common.entity.DataEntity;	
import javax.validation.ConstraintValidator;	
import javax.validation.ConstraintValidatorContext;	
	
public class OptimisticLockValidator implements ConstraintValidator {	
   public void initialize(OptimisticLock constraintAnnotation) {	
   }	
	
   protected boolean checkLastUpdateDateTime(DataEntity entity) {	
      Long a;	
      if ((a = entity.getLastUpdateDateTime()) != null && entity.getUpdateDate() != null) {	
         Long a = entity.getUpdateDate().getTime();	
         if (a < a) {	
            return false;	
         }	
      }	
	
      return true;	
   }	
	
   public boolean isValid(DataEntity value, ConstraintValidatorContext context) {	
      return this.checkLastUpdateDateTime(value);	
   }	
}	
