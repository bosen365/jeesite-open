package com.jeesite.common.validator;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.lang.StringUtils;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Map;	
import java.util.Set;	
import javax.validation.ConstraintViolation;	
import javax.validation.ConstraintViolationException;	
import javax.validation.Validator;	
import org.hyperic.sigar.FileWatcher;	
import org.hyperic.sigar.cmd.Watch;	
	
public class ValidatorUtils {	
   public static List extractPropertyAndMessageAsList(ConstraintViolationException e) {	
      return extractPropertyAndMessageAsList(e.getConstraintViolations(), " ");	
   }	
	
   public static List extractPropertyAndMessageAsList(Set constraintViolations) {	
      return extractPropertyAndMessageAsList(constraintViolations, " ");	
   }	
	
   public static Map extractPropertyAndMessage(ConstraintViolationException e) {	
      return extractPropertyAndMessage(e.getConstraintViolations());	
   }	
	
   public static List extractPropertyAndMessageAsList(ConstraintViolationException e, String separator) {	
      return extractPropertyAndMessageAsList(e.getConstraintViolations(), separator);	
   }	
	
   public static String ALLATORIxDEMO(String s) {	
      int var10000 = 1 << 3 ^ 3 ^ 5;	
      int var10001 = 4 << 3 ^ 5;	
      int var10002 = (2 ^ 5) << 3 ^ 3 ^ 5;	
      int var10003 = (s = (String)s).length();	
      char[] var10004 = new char[var10003];	
      boolean var10006 = true;	
      int var5 = var10003 - 1;	
      var10003 = var10002;	
      int var3;	
      var10002 = var3 = var5;	
      char[] var1 = var10004;	
      int var4 = var10003;	
      var10001 = var10000;	
      var10000 = var10002;	
	
      for(int var2 = var10001; var10000 >= 0; var10000 = var3) {	
         var10001 = var3;	
         char var6 = s.charAt(var3);	
         --var3;	
         var1[var10001] = (char)(var6 ^ var2);	
         if (var3 < 0) {	
            break;	
         }	
	
         var10002 = var3--;	
         var1[var10002] = (char)(s.charAt(var10002) ^ var4);	
      }	
	
      return new String(var1);	
   }	
	
   public static List extractMessage(Set constraintViolations) {	
      List a = ListUtils.newArrayList();	
      Iterator var2;	
      Iterator var10000 = var2 = constraintViolations.iterator();	
	
      while(var10000.hasNext()) {	
         ConstraintViolation a = (ConstraintViolation)var2.next();	
         var10000 = var2;	
         a.add(a.getMessage());	
      }	
	
      return a;	
   }	
	
   public static void validateWithException(Object object, Class... groups) throws ConstraintViolationException {	
      validateWithException(null.ALLATORIxDEMO(), object, groups);	
   }	
	
   public static void validateWithException(Validator validator, Object object, Class... groups) throws ConstraintViolationException {	
      Set a;	
      if (!(a = validator.validate(object, groups)).isEmpty()) {	
         throw new ConstraintViolationException(a);	
      }	
   }	
	
   public static List extractMessage(ConstraintViolationException e) {	
      return extractMessage(e.getConstraintViolations());	
   }	
	
   public static boolean validate(StringBuilder message, Object object, Class... groups) {	
      try {	
         validateWithException(null.ALLATORIxDEMO(), object, groups);	
         return true;	
      } catch (ConstraintViolationException var5) {	
         List a;	
         if ((a = extractPropertyAndMessageAsList(var5, ": ")).size() > 0) {	
            a.add(0, "☆");	
            message.append(StringUtils.join(a, "<br/>☆"));	
         }	
	
         return false;	
      }	
   }	
	
   public static Map extractPropertyAndMessage(Set constraintViolations) {	
      Map a = MapUtils.newHashMap();	
      Iterator var2;	
      Iterator var10000 = var2 = constraintViolations.iterator();	
	
      while(var10000.hasNext()) {	
         ConstraintViolation a = (ConstraintViolation)var2.next();	
         var10000 = var2;	
         a.put(a.getPropertyPath().toString(), a.getMessage());	
      }	
	
      return a;	
   }	
	
   public static List extractPropertyAndMessageAsList(Set constraintViolations, String separator) {	
      List a = ListUtils.newArrayList();	
      Iterator var3;	
      Iterator var10000 = var3 = constraintViolations.iterator();	
	
      while(var10000.hasNext()) {	
         ConstraintViolation a = (ConstraintViolation)var3.next();	
         var10000 = var3;	
         a.add(a.getPropertyPath() + separator + a.getMessage());	
      }	
	
      return a;	
   }	
}	
