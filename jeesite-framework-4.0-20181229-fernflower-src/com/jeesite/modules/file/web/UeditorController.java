package com.jeesite.modules.file.web;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.l.j;	
import com.jeesite.common.web.BaseController;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.apache.shiro.authz.annotation.RequiresPermissions;	
import org.springframework.stereotype.Controller;	
import org.springframework.web.bind.annotation.PathVariable;	
import org.springframework.web.bind.annotation.RequestMapping;	
import org.springframework.web.bind.annotation.ResponseBody;	
	
@Controller	
@RequestMapping({"${adminPath}/file/ueditor"})	
public class UeditorController extends BaseController {	
   @RequiresPermissions({"user"})	
   @RequestMapping({""})	
   @ResponseBody	
   public String upload(HttpServletRequest request, HttpServletResponse response) {	
      return this.upload((String)null, request, response);	
   }	
	
   @RequiresPermissions({"user"})	
   @RequestMapping({"{action}"})	
   @ResponseBody	
   public String upload(@PathVariable String action, HttpServletRequest request, HttpServletResponse response) {	
      String a = Global.getUserfilesBaseDir((String)null);	
      return (new j(request, a, action)).ALLATORIxDEMO();	
   }	
}	
