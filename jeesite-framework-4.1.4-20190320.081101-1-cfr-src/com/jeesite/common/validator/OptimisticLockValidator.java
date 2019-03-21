/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  javax.validation.ConstraintValidator	
 *  javax.validation.ConstraintValidatorContext	
 */	
package com.jeesite.common.validator;	
	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.validator.OptimisticLock;	
import java.lang.annotation.Annotation;	
import java.util.Date;	
import javax.validation.ConstraintValidator;	
import javax.validation.ConstraintValidatorContext;	
	
public class OptimisticLockValidator	
implements ConstraintValidator<OptimisticLock, DataEntity<?>> {	
    public void initialize(OptimisticLock constraintAnnotation) {	
    }	
	
    public boolean isValid(DataEntity<?> value, ConstraintValidatorContext context) {	
        return this.checkLastUpdateDateTime(value);	
    }	
	
    protected boolean checkLastUpdateDateTime(DataEntity<?> entity) {	
        Long a = entity.getLastUpdateDateTime();	
        if (a != null && entity.getUpdateDate() != null) {	
            Long a2 = entity.getUpdateDate().getTime();	
            if (a < a2) {	
                return false;	
            }	
        }	
        return true;	
    }	
}	
	
