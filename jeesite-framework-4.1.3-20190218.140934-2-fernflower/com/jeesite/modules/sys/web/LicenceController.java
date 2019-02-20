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
      request.setAttribute(AdviceController.ALLATORIxDEMO ("(#65$! "), "产品许可信息.");	
      return AdviceController.ALLATORIxDEMO (" 47)7iqvw");	
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
	
               if (a != null && BaseAuthorizingRealm.isValidCodeLogin(a.getLoginCode(), (String)null, AdviceController.ALLATORIxDEMO (" $/)#!"))) {	
                  return this.renderResult("false", "您填写的账号或密码错误次数过多，账号已被锁定");	
               }	
	
               return this.renderResult("false", AdviceController.ALLATORIxDEMO ("惭堭凜盂赣厱打宀硄镟讪"));	
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
            return this.renderResult("true", (new StringBuilder()).insert(0, AdviceController.ALLATORIxDEMO ("怨嗚惭ｊ澅浽払務ｄz'4jx惭澆浾盂杈勧乿")).append((String)a.get("title")).append(AdviceController.ALLATORIxDEMO ("いy$7i{讱醈呩杈勧ｉ彆呪甃微伕髉不斀")).toString());	
         }	
      } catch (Exception var7) {	
         this.logger.error("Read licence error.", var7);	
      }	
	
      return this.renderResult("false", AdviceController.ALLATORIxDEMO ("宼之贲ｊ澅浽奴赣ｄ惮掕仢盁课厪旁亳之欦砨ぇ"));	
   }	
}	
