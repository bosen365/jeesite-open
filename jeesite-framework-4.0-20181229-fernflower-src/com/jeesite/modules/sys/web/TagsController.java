package com.jeesite.modules.sys.web;	
	
import com.jeesite.common.web.BaseController;	
import com.jeesite.common.web.http.ServletUtils;	
import javax.servlet.http.HttpServletRequest;	
import org.springframework.stereotype.Controller;	
import org.springframework.ui.Model;	
import org.springframework.web.bind.annotation.RequestMapping;	
	
@Controller	
@RequestMapping({"tags"})	
public class TagsController extends BaseController {	
   @RequestMapping({"iconselect"})	
   public String iconselect(HttpServletRequest request, Model model) {	
      model.addAllAttributes(ServletUtils.getParameters(request));	
      return "tagsview/form/iconselect";	
   }	
	
   @RequestMapping({"imageclip"})	
   public String imageclip(HttpServletRequest request, Model model) {	
      model.addAllAttributes(ServletUtils.getParameters(request));	
      return AdviceController.ALLATORIxDEMO ("\u0001r\u0012`\u0003z\u0010dZu\u001aa\u0018<\u001c~\u0014t\u0010p\u0019z\u0005");	
   }	
	
   @RequestMapping({"treeselect"})	
   public String treeselect(HttpServletRequest request, Model model) {	
      model.addAllAttributes(ServletUtils.getParameters(request));	
      return "tagsview/form/treeselect";	
   }	
}	
