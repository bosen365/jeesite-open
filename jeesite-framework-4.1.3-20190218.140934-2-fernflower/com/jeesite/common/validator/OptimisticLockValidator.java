package com.jeesite.common.validator;	
	
import com.jeesite.common.entity.DataEntity;	
import javax.validation.ConstraintValidator;	
import javax.validation.ConstraintValidatorContext;	
	
public class OptimisticLockValidator implements ConstraintValidator {	
   public void initialize(OptimisticLock constraintAnnotation) {	
   }	
	
   public boolean isValid(DataEntity value, ConstraintValidatorContext context) {	
      return this.checkLastUpdateDateTime(value);	
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
}	
