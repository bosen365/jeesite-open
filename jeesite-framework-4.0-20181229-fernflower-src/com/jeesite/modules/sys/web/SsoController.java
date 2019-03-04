package com.jeesite.modules.sys.web;	
	
import com.jeesite.common.codec.EncodeUtils;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.shiro.authc.FormToken;	
import com.jeesite.common.web.BaseController;	
import com.jeesite.common.web.http.ServletUtils;	
import com.jeesite.modules.sys.utils.UserUtils;	
import javax.servlet.http.HttpServletRequest;	
import org.apache.shiro.authc.AuthenticationException;	
import org.springframework.stereotype.Controller;	
import org.springframework.ui.Model;	
import org.springframework.web.bind.annotation.PathVariable;	
import org.springframework.web.bind.annotation.RequestMapping;	
import org.springframework.web.bind.annotation.RequestParam;	
	
@Controller	
public class SsoController extends BaseController {	
   @RequestMapping({"sso/{username}/{token}"})	
   public String sso(@PathVariable String username, @PathVariable String token, HttpServletRequest request, @RequestParam(defaultValue = "${adminPath}") String url, String relogin, Model model) {	
      if (UserUtils.getLoginInfo() != null && !ObjectUtils.toBoolean(relogin)) {	
         return (new StringBuilder()).insert(0, "redirect:").append(EncodeUtils.decodeUrl2(url)).toString();	
      } else {	
         if (token != null) {	
            try {	
               FormToken a = new FormToken();	
               a.setUsername(username);	
               a.setSsoToken(token);	
               a.setParams(ServletUtils.getExtParams(request));	
               UserUtils.getSubject().login(a);	
               return (new StringBuilder()).insert(0, "redirect:").append(EncodeUtils.decodeUrl2(url)).toString();	
            } catch (AuthenticationException var9) {	
               AuthenticationException a = var9;	
               if (!var9.getMessage().startsWith(AdviceController.ALLATORIxDEMO ("~\u0006tO"))) {	
                  a = new AuthenticationException(AdviceController.ALLATORIxDEMO ("~\u0006tO掛朶夢赐？讂聇粎箲瑳呋ぷ"), var9);	
               }	
	
               model.addAttribute(AdviceController.ALLATORIxDEMO ("\u0010k\u0016v\u0005g\u001c|\u001b"), a);	
            }	
         }	
	
         return AdviceController.ALLATORIxDEMO ("\u0010a\u0007|\u0007<A#F");	
      }	
   }	
}	
