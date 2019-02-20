package com.jeesite.common.validator;	
	
import com.jeesite.common.config.Global;	
import java.lang.invoke.MethodHandles;	
import java.util.regex.Pattern;	
import java.util.regex.PatternSyntaxException;	
import javax.validation.ConstraintValidator;	
import javax.validation.ConstraintValidatorContext;	
import org.hibernate.validator.internal.util.logging.Log;	
import org.hibernate.validator.internal.util.logging.LoggerFactory;	
	
public class PatternValueValidator implements ConstraintValidator {	
   private Pattern pattern;	
   private static final Log log = LoggerFactory.make(MethodHandles.lookup());	
	
   public boolean isValid(CharSequence value, ConstraintValidatorContext constraintValidatorContext) {	
      return value == null ? true : this.pattern.matcher(value).matches();	
   }	
	
   public void initialize(PatternValue parameters) {	
      PatternValue.Flag[] a = parameters.flags();	
      int a = 0;	
      PatternValue.Flag[] var4 = a;	
      int var5 = a.length;	
	
      int var6;	
      for(int var10000 = var6 = 0; var10000 < var5; var10000 = var6) {	
         PatternValue.Flag a = var4[var6];	
         ++var6;	
         a |= a.getValue();	
      }	
	
      try {	
         String a = Global.getProperty(parameters.value(), parameters.regexp());	
         this.pattern = Pattern.compile(a, a);	
      } catch (PatternSyntaxException var8) {	
         throw log.getInvalidRegularExpressionException(var8);	
      }	
   }	
}	
