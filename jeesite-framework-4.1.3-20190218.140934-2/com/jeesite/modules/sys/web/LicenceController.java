package com.jeesite.modules.sys.web;	
	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.shiro.realm.BaseAuthorizingRealm;	
import com.jeesite.common.web.BaseController;	
import com.jeesite.modules.sys.entity.User;	
import com.jeesite.modules.sys.utils.PwdUtils;	
import com.jeesite.modules.sys.utils.UserUtils;	
import java.util.HashMap;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.hyperic.sigar.shell.ShellCommandUsageException;	
import org.springframework.stereotype.Controller;	
import org.springframework.ui.Model;	
import org.springframework.web.bind.annotation.RequestMapping;	
import org.springframework.web.bind.annotation.ResponseBody;	
import org.springframework.web.multipart.MultipartFile;	
	
@Controller	
public class LicenceController extends BaseController {	
   @RequestMapping({"licence"})	
   public String licence(HttpServletRequest request, HttpServletResponse var2, Model var3) {	
      request.setAttribute("message", "产品许可信息.");	
      return "error/402";	
   }	
	
   @RequestMapping({"licence/sava"})	
   @ResponseBody	
   public String licenceSave(String username, String password, MultipartFile licFile, HttpServletRequest request, HttpServletResponse response) {	
      if (!UserUtils.getUser().isAdmin()) {	
         label54: {	
            if (!StringUtils.isBlank(username) && !StringUtils.isBlank(password)) {	
               User a;	
               if ((a = UserUtils.getByLoginCode(username)) != null && (a == null || a.isAdmin() && PwdUtils.validatePassword(password, a.getPassword()))) {	
                  BaseAuthorizingRealm.isValidCodeLogin(a.getLoginCode(), (String)null, "success");	
                  break label54;	
               }	
	
               if (a != null && BaseAuthorizingRealm.isValidCodeLogin(a.getLoginCode(), (String)null, "failed")) {	
                  return this.renderResult("false", "您填写的账号或密码错误次数过多，账号已被锁定");	
               }	
	
               return this.renderResult("false", "您填写的账号或密码错误");	
            }	
	
            return this.renderResult("false", "用户名或密码不能为空");	
         }	
      }	
	
      try {	
         HashMap a;	
         String var10000 = (String)(a = new HashMap()).get("type");	
         String[] var10001 = new String[1];	
         boolean var10003 = true;	
         var10001[0] = "0";	
         if (StringUtils.inString(var10000, var10001)) {	
            return this.renderResult("true", (new StringBuilder()).insert(0, "恭喜您，激活成功！<br/>您激活的服务为").append((String)a.get("title")).append("。<br/>请重启服务，开启畅快体验之旅").toString());	
         }	
      } catch (Exception var7) {	
         this.logger.error("Read licence error.", var7);	
      }	
	
      return this.renderResult("false", "对不起，激活失败！您提交的许可文件不正确。");	
   }	
}	
