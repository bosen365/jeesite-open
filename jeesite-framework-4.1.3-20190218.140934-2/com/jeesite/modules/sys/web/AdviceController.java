package com.jeesite.modules.sys.web;	
	
import com.jeesite.common.codec.EncodeUtils;	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.lang.DateUtils;	
import com.jeesite.common.mybatis.mapper.query.QueryColumn;	
import com.jeesite.common.web.BaseController;	
import java.beans.PropertyEditorSupport;	
import java.util.Date;	
import javax.servlet.http.HttpServletRequest;	
import javax.validation.ConstraintViolationException;	
import javax.validation.ValidationException;	
import org.apache.shiro.authc.AuthenticationException;	
import org.apache.shiro.authz.UnauthenticatedException;	
import org.apache.shiro.authz.UnauthorizedException;	
import org.springframework.validation.BindException;	
import org.springframework.web.bind.WebDataBinder;	
import org.springframework.web.bind.annotation.ControllerAdvice;	
import org.springframework.web.bind.annotation.ExceptionHandler;	
import org.springframework.web.bind.annotation.InitBinder;	
	
@ControllerAdvice	
public class AdviceController {	
   @ExceptionHandler({UnauthenticatedException.class, UnauthorizedException.class, AuthenticationException.class})	
   protected String exceptionHandlerTo403Page() {	
      return ALLATORIxDEMO(" 47)7iqvv");	
   }	
	
   @InitBinder	
   protected void initBinder(WebDataBinder binder, HttpServletRequest request) {	
      binder.setAutoGrowCollectionLimit(Integer.MAX_VALUE);	
      String[] var10001 = new String[8];	
      boolean var10003 = true;	
      var10001[0] = "global";	
      var10001[1] = ALLATORIxDEMO("!))'')ho");	
      var10001[2] = "sqlMa";	
      var10001[3] = ALLATORIxDEMO("54*\b'5ho");	
      var10001[4] = "currentUser";	
      var10001[5] = ALLATORIxDEMO("&374 (1\u00136#7ho");	
      var10001[6] = "corCode";	
      var10001[7] = ALLATORIxDEMO("%*4 \b$+ ");	
      binder.setDisallowedFields(var10001);	
      binder.registerCustomEditor(String.class, new PropertyEditorSupport() {	
         public void setAsText(String ax) {	
            a.setValue(EncodeUtils.xssFilter(ax));	
         }	
      });	
      binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {	
         public void setAsText(String ax) {	
            a.setValue(DateUtils.parseDate(ax));	
         }	
      });	
      if (binder.getTarget() instanceof BaseEntity) {	
         BaseEntity a = (BaseEntity)binder.getTarget();	
         request.setAttribute(BaseController.WEB_DATA_BINDER_SOURCE, a.clone());	
         request.setAttribute(BaseController.WEB_DATA_BINDER_TARGET, a);	
      }	
	
   }	
	
   @ExceptionHandler({BindException.class, ConstraintViolationException.class, ValidationException.class})	
   protected String exceptionHandlerTo400Page() {	
      return "error/400";	
   }	
	
   public static String ALLATORIxDEMO(String s) {	
      int var10000 = 4 << 4 ^ (3 ^ 5) << 1;	
      int var10001 = 4 << 4 ^ 5;	
      int var10002 = 4 << 4 ^ 3 << 1;	
      int var10003 = (s = (String)s).length();	
      char[] var10004 = new char[var10003];	
      boolean var10006 = true;	
      int var5 = var10003 - 1;	
      var10003 = var10002;	
      int var3;	
      var10002 = var3 = var5;	
      char[] var1 = var10004;	
      int var4 = var10003;	
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
}	
