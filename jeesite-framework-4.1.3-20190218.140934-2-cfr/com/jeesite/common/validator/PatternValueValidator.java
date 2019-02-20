/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  javax.validation.ConstraintValidator	
 *  javax.validation.ConstraintValidatorContext	
 *  org.hibernate.validator.internal.util.logging.Log	
 *  org.hibernate.validator.internal.util.logging.LoggerFactory	
 */	
package com.jeesite.common.validator;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.validator.PatternValue;	
import java.lang.annotation.Annotation;	
import java.lang.invoke.MethodHandles;	
import java.util.regex.Matcher;	
import java.util.regex.Pattern;	
import java.util.regex.PatternSyntaxException;	
import javax.validation.ConstraintValidator;	
import javax.validation.ConstraintValidatorContext;	
import org.hibernate.validator.internal.util.logging.Log;	
import org.hibernate.validator.internal.util.logging.LoggerFactory;	
	
public class PatternValueValidator	
implements ConstraintValidator<PatternValue, CharSequence> {	
    private Pattern pattern;	
    private static final Log log = LoggerFactory.make((MethodHandles.Lookup)MethodHandles.lookup());	
	
    public boolean isValid(CharSequence value, ConstraintValidatorContext constraintValidatorContext) {	
        if (value == null) {	
            return true;	
        }	
        return this.pattern.matcher(value).matches();	
    }	
	
    public void initialize(PatternValue parameters) {	
        int n;	
        PatternValue.Flag[] a2 = parameters.flags();	
        int a3 = 0;	
        PatternValue.Flag[] arrflag = a2;	
        int n2 = arrflag.length;	
        int n3 = n = 0;	
        while (n3 < n2) {	
            PatternValue.Flag a4 = arrflag[n];	
            a3 |= a4.getValue();	
            n3 = ++n;	
        }	
        try {	
            String a5 = Global.getProperty(parameters.value(), parameters.regexp());	
            this.pattern = Pattern.compile(a5, a3);	
            return;	
        }	
        catch (PatternSyntaxException a6) {	
            throw log.getInvalidRegularExpressionException(a6);	
        }	
    }	
}	
	
