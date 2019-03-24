/*	
 * Decompiled with CFR 0.141.	
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
    private static final Log log = LoggerFactory.make(MethodHandles.lookup());	
    private Pattern pattern;	
	
    @Override	
    public boolean isValid(CharSequence value, ConstraintValidatorContext constraintValidatorContext) {	
        if (value == null) {	
            return true;	
        }	
        return this.pattern.matcher(value).matches();	
    }	
	
    @Override	
    public void initialize(PatternValue parameters) {	
        int n;	
        PatternValue.Flag[] a = parameters.flags();	
        int a2 = 0;	
        PatternValue.Flag[] arrflag = a;	
        int n2 = arrflag.length;	
        int n3 = n = 0;	
        while (n3 < n2) {	
            PatternValue.Flag a3 = arrflag[n];	
            a2 |= a3.getValue();	
            n3 = ++n;	
        }	
        try {	
            String a4 = Global.getProperty(parameters.value(), parameters.regexp());	
            this.pattern = Pattern.compile(a4, a2);	
            return;	
        }	
        catch (PatternSyntaxException a5) {	
            throw log.getInvalidRegularExpressionException(a5);	
        }	
    }	
}	
	
