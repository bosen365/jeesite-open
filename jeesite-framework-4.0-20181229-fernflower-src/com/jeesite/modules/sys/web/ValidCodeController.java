package com.jeesite.modules.sys.web;	
	
import com.jeesite.common.image.CaptchaUtils;	
import com.jeesite.common.shiro.realm.LoginInfo;	
import com.jeesite.common.validator.ValidatorUtils;	
import com.jeesite.modules.sys.utils.ValidCodeUtils;	
import java.io.IOException;	
import javax.servlet.ServletException;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.apache.commons.lang3.StringUtils;	
import org.springframework.stereotype.Controller;	
import org.springframework.web.bind.annotation.RequestMapping;	
	
@Controller	
public class ValidCodeController {	
   @RequestMapping({"/validCode"})	
   public void validCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
      String a;	
      if (StringUtils.isNotBlank(a = request.getParameter("validCode"))) {	
         int a = ValidCodeUtils.validate(request, "validCode", a, false);	
         response.getOutputStream().print(a ? "true" : "false");	
      } else {	
         response.setContentType("image/png");	
         response.setHeader("Cache-Control", "no-cache, no-store");	
         response.setHeader("Pragma", "no-cache");	
         long a = System.currentTimeMillis();	
         response.setDateHeader("Last-Modified", a);	
         response.setDateHeader("Date", a);	
         response.setDateHeader("Expires", a);	
         String a = CaptchaUtils.generateCaptcha(response.getOutputStream());	
         request.getSession().setAttribute("validCode", a);	
      }	
   }	
	
   public static String ALLATORIxDEMO(String s) {	
      int var10000 = 2 << 3 ^ 4;	
      int var10001 = (2 ^ 5) << 3 ^ 2 ^ 5;	
      int var10002 = 5 << 3 ^ 3;	
      int var10003 = s.length();	
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
}	
