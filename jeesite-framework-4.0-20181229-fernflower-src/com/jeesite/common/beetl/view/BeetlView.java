package com.jeesite.common.beetl.view;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.l.C;	
import java.util.Map;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.apache.commons.lang3.StringUtils;	
import org.beetl.ext.spring.BeetlSpringView;	
import org.springframework.beans.factory.NoSuchBeanDefinitionException;	
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;	
	
public class BeetlView extends BeetlSpringView {	
   protected void renderMergedTemplateModel(Map model, HttpServletRequest request, HttpServletResponse response) throws NoSuchBeanDefinitionException, NoUniqueBeanDefinitionException {	
      String a = request.getRequestURI();	
      String a = request.getContextPath();	
      Map var10000;	
      if (StringUtils.startsWith(a, a + Global.getAdminPath())) {	
         a = (new StringBuilder()).insert(0, a).append(Global.getAdminPath()).toString();	
         var10000 = model;	
      } else {	
         if (StringUtils.startsWith(a, (new StringBuilder()).insert(0, a).append(Global.getFrontPath()).toString())) {	
            a = (new StringBuilder()).insert(0, a).append(Global.getFrontPath()).toString();	
         }	
	
         var10000 = model;	
      }	
	
      var10000.put("ctx", a);	
	
      try {	
         super.renderMergedTemplateModel(model, request, response);	
      } catch (IllegalStateException var7) {	
         if (!StringUtils.contains(var7.getMessage(), "getOutputStream() has already been called for this response")) {	
            throw var7;	
         }	
      }	
   }	
}	
