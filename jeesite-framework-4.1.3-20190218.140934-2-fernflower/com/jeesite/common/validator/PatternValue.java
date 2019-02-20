package com.jeesite.common.validator;	
	
import java.lang.annotation.Documented;	
import java.lang.annotation.ElementType;	
import java.lang.annotation.Retention;	
import java.lang.annotation.RetentionPolicy;	
import java.lang.annotation.Target;	
import javax.validation.Constraint;	
	
@Documented	
@Constraint(	
   validatedBy = {PatternValueValidator.class}	
)	
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})	
@Retention(RetentionPolicy.RUNTIME)	
public @interface PatternValue {	
   String regexp() default "";	
	
   Class[] groups() default {};	
	
   Class[] payload() default {};	
	
   String value();	
	
   PatternValue.Flag[] flags() default {};	
	
   String message() default "{javax.validation.constraints.Pattern.message}";	
	
   @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})	
   @Retention(RetentionPolicy.RUNTIME)	
   @Documented	
   public @interface List {	
      PatternValue[] value();	
   }	
	
   public static enum Flag {	
      MULTILINE(8);	
	
      private final int value;	
      COMMENTS(4),	
      DOTALL(32),	
      CANON_EQ(128),	
      CASE_INSENSITIVE(2),	
      UNIX_LINES(1),	
      UNICODE_CASE(64);	
	
      static {	
         PatternValue.Flag[] var10000 = new PatternValue.Flag[7];	
         boolean var10002 = true;	
         var10000[0] = UNIX_LINES;	
         var10000[1] = CASE_INSENSITIVE;	
         var10000[2] = COMMENTS;	
         var10000[3] = MULTILINE;	
         var10000[4] = DOTALL;	
         var10000[5] = UNICODE_CASE;	
         var10000[6] = CANON_EQ;	
      }	
	
      public int getValue() {	
         return this.value;	
      }	
	
      // $FF: synthetic method	
      private Flag(int var3) {	
         this.value = var3;	
      }	
   }	
}	
