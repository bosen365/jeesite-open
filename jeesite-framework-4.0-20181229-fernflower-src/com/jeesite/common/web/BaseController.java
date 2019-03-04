package com.jeesite.common.web;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.l.i.D;	
import com.jeesite.common.service.BaseService;	
import com.jeesite.common.web.http.ServletUtils;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
import org.springframework.beans.factory.annotation.Value;	
import org.springframework.ui.Model;	
import org.springframework.web.bind.WebDataBinder;	
import org.springframework.web.servlet.mvc.support.RedirectAttributes;	
	
public abstract class BaseController {	
   protected static final String REDIRECT = "redirect:";	
   protected Logger logger = LoggerFactory.getLogger(this.getClass());	
   public static final String WEB_DATA_BINDER_TARGET = (new StringBuilder()).insert(0, WebDataBinder.class.getName()).append(".TARGET").toString();	
   @Value("${frontPath}")	
   protected String frontPath;	
   @Value("${adminPath}")	
   protected String adminPath;	
   protected static final String FORWARD = "forward:";	
   public static final String WEB_DATA_BINDER_SOURCE = (new StringBuilder()).insert(0, WebDataBinder.class.getName()).append(".SOURCE").toString();	
	
   protected void addMessage(Model model, String... messages) {	
      StringBuilder a = new StringBuilder();	
      String[] var4 = messages;	
      int var5 = messages.length;	
	
      int var6;	
      for(int var10000 = var6 = 0; var10000 < var5; var10000 = var6) {	
         String a = var4[var6];	
         a.append(a).append(messages.length > 1 ? "<br/>" : "");	
         ++var6;	
      }	
	
      model.addAttribute("message", a.toString());	
   }	
	
   protected String renderResult(String result, String message) {	
      return this.renderResult((String)result, message, (Object)null);	
   }	
	
   protected Object getWebDataBinderTarget(HttpServletRequest request) {	
      return request.getAttribute(WEB_DATA_BINDER_TARGET);	
   }	
	
   protected String renderResult(HttpServletResponse response, String result, String message) {	
      return this.renderResult(response, result, message, (Object)null);	
   }	
	
   protected String renderResult(HttpServletResponse response, String result, String message, Object data) {	
      return ServletUtils.renderResult(response, result, message, data);	
   }	
	
   protected String renderResult(String result, String message, Object data) {	
      return ServletUtils.renderResult(result, message, data);	
   }	
	
   public static String text(String code, String... params) {	
      return Global.getText(code, params);	
   }	
	
   protected String renderResult(String result, StringBuilder message) {	
      return this.renderResult(result, message != null ? message.toString() : "");	
   }	
	
   protected void addMessage(RedirectAttributes redirectAttributes, String... messages) {	
      StringBuilder a = new StringBuilder();	
      String[] var4 = messages;	
      int var5 = messages.length;	
	
      int var6;	
      for(int var10000 = var6 = 0; var10000 < var5; var10000 = var6) {	
         String a = var4[var6];	
         a.append(a).append(messages.length > 1 ? "<br/>" : "");	
         ++var6;	
      }	
	
      redirectAttributes.addFlashAttribute("message", a.toString());	
   }	
	
   protected Object getWebDataBinderSource(HttpServletRequest request) {	
      return request.getAttribute(WEB_DATA_BINDER_SOURCE);	
   }	
}	
