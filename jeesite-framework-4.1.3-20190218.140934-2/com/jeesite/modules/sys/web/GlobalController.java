package com.jeesite.modules.sys.web;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.Extend;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.common.shiro.realm.BaseAuthorizingRealm;	
import com.jeesite.common.utils.SpringUtils;	
import com.jeesite.common.web.BaseController;	
import com.jeesite.common.web.http.ServletUtils;	
import com.jeesite.modules.sys.entity.User;	
import com.jeesite.modules.sys.utils.PwdUtils;	
import com.jeesite.modules.sys.utils.UserUtils;	
import java.io.File;	
import java.io.IOException;	
import java.util.Map;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.hyperic.sigar.pager.PageList;	
import org.springframework.stereotype.Controller;	
import org.springframework.ui.Model;	
import org.springframework.web.bind.annotation.PathVariable;	
import org.springframework.web.bind.annotation.RequestMapping;	
import org.springframework.web.bind.annotation.ResponseBody;	
import org.springframework.web.multipart.MultipartFile;	
	
@Controller	
public class GlobalController extends BaseController {	
   @RequestMapping({"/global.min.js"})	
   @ResponseBody	
   public String globalJs(String ctx, HttpServletRequest request, Model model) {	
      StringBuilder a = new StringBuilder();	
      String a = request.getContextPath();	
      a.append("lang='" + Global.getLang() + "',");	
      a.append((new StringBuilder()).insert(0, "ctx='").append((String)StringUtils.defaultIfBlank(ctx, a)).append("',").toString());	
      a.append((new StringBuilder()).insert(0, "ctxPath='").append(a).append("',").toString());	
      a.append((new StringBuilder()).insert(0, "ctxAdmin='").append(a).append(Global.getAdminPath()).append("',").toString());	
      a.append((new StringBuilder()).insert(0, "ctxFront='").append(a).append(Global.getFrontPath()).append("',").toString());	
      a.append((new StringBuilder()).insert(0, "ctxStatic='").append(a).append("/static',").toString());	
      a.append((new StringBuilder()).insert(0, "Global={").append(Global.getConst("Global.Fields")).append("}").toString());	
      return a.toString();	
   }	
	
   @RequestMapping({""})	
   public String defaultPath() {	
      return (new StringBuilder()).insert(0, "redirect:").append(Global.getProperty("shiro.defaultPath")).toString();	
   }	
	
   @RequestMapping({"lang/{lang}"})	
   public String lang(@PathVariable String lang, HttpServletRequest request, HttpServletResponse response) {	
      if (StringUtils.isNotBlank(lang)) {	
         Global.setLang(lang, request, response);	
      }	
	
      return ServletUtils.isAjaxRequest(request) ? this.renderResult("1", "切换成功！") : (new StringBuilder()).insert(0, "redirect:").append(this.adminPath).append("/index").toString();	
   }	
	
   @RequestMapping({"licence/save"})	
   @ResponseBody	
   public String abcdefg(String username, String password, MultipartFile licFile, HttpServletRequest request, HttpServletResponse response) {	
      if (!UserUtils.getUser().isAdmin()) {	
         if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {	
            return this.renderResult("false", "用户名或密码不能为空");	
         }	
	
         User a;	
         if ((a = UserUtils.getByLoginCode(username)) == null || a != null && (!a.isAdmin() || !PwdUtils.validatePassword(password, a.getPassword()))) {	
            if (a != null && BaseAuthorizingRealm.isValidCodeLogin(a.getLoginCode(), (String)null, "failed")) {	
               return this.renderResult("false", "您填写的账号或密码错误次数过多，账号已被锁定");	
            }	
	
            return this.renderResult("false", "您填写的账号或密码错误");	
         }	
	
         BaseAuthorizingRealm.isValidCodeLogin(a.getLoginCode(), (String)null, "success");	
      }	
	
      try {	
         Map a;	
         String var10000 = (String)(a = MapperHelper.ck(licFile.getInputStream())).get("type");	
         String[] var10001 = new String[2];	
         boolean var10003 = true;	
         var10001[0] = "1";	
         var10001[1] = "2";	
         if (StringUtils.inString(var10000, var10001)) {	
            File a;	
            if ((a = SpringUtils.getLicFile("jeesite.lic")).exists()) {	
               a.delete();	
            }	
	
            licFile.transferTo(a);	
            return this.renderResult("true", (new StringBuilder()).insert(0, "恭喜您，激活成功！<br/>您激活的服务为").append((String)a.get("title")).append("<br/>请重启服务，开启畅快体验之旅").toString());	
         }	
      } catch (IOException var9) {	
         this.logger.error("Read licence error.");	
      }	
	
      return this.renderResult("false", "对不起，激活失败！您提交的许可文件不正确。");	
   }	
	
   @RequestMapping({"error/{errorCode}"})	
   public String error(@PathVariable String errorCode) {	
      return (new StringBuilder()).insert(0, "error/").append(errorCode).toString();	
   }	
}	
