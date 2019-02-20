package com.jeesite.modules.sys.web;	
	
import com.jeesite.common.image.CaptchaUtils;	
import com.jeesite.common.mybatis.mapper.query.QueryWhere;	
import com.jeesite.modules.sys.utils.ValidCodeUtils;	
import java.io.IOException;	
import javax.servlet.ServletException;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.apache.commons.lang3.StringUtils;	
import org.hyperic.sigar.pager.SortAttribute;	
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
}	
