package com.jeesite.modules.sys.web;	
	
import com.jeesite.common.codec.DesUtils;	
import com.jeesite.common.codec.EncodeUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.Extend;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.j2cache.autoconfigure.J2CacheAutoConfiguration;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mapper.JsonMapper;	
import com.jeesite.common.service.ServiceException;	
import com.jeesite.common.web.BaseController;	
import com.jeesite.modules.sys.entity.User;	
import com.jeesite.modules.sys.service.UserService;	
import com.jeesite.modules.sys.utils.PwdUtils;	
import com.jeesite.modules.sys.utils.UserUtils;	
import java.util.Map;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.apache.shiro.authz.annotation.RequiresPermissions;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;	
import org.springframework.stereotype.Controller;	
import org.springframework.ui.Model;	
import org.springframework.web.bind.annotation.PostMapping;	
import org.springframework.web.bind.annotation.RequestMapping;	
import org.springframework.web.bind.annotation.ResponseBody;	
	
@Controller	
@RequestMapping({"${adminPath}/sys/user"})	
@ConditionalOnProperty(	
   name = {"web.core.enabled"},	
   havingValue = "true",	
   matchIfMissing = true	
)	
public class UserController extends BaseController {	
   @Autowired	
   private UserService userService;	
	
   @RequiresPermissions({"user"})	
   @PostMapping({"infoSavePqa"})	
   @ResponseBody	
   public String infoSavePqa(User user, String validPassword, String oldPwdQuestionAnswer, String oldPwdQuestionAnswer2, String oldPwdQuestionAnswer3) {	
      User a = UserUtils.getUser();	
      String a;	
      if (StringUtils.isNotBlank(a = Global.getProperty("shiro.loginSubmit.secretKey"))) {	
         validPassword = DesUtils.decode(validPassword, a);	
         oldPwdQuestionAnswer = DesUtils.decode(oldPwdQuestionAnswer, a);	
         oldPwdQuestionAnswer2 = DesUtils.decode(oldPwdQuestionAnswer2, a);	
         oldPwdQuestionAnswer3 = DesUtils.decode(oldPwdQuestionAnswer3, a);	
         user.setPwdQuestionAnswer(DesUtils.decode(user.getPwdQuestionAnswer(), a));	
         user.setPwdQuestionAnswer2(DesUtils.decode(user.getPwdQuestionAnswer2(), a));	
         user.setPwdQuestionAnswer3(DesUtils.decode(user.getPwdQuestionAnswer3(), a));	
      }	
	
      int a = false;	
      boolean var10000;	
      String var10002;	
      String[] var10003;	
      boolean var10005;	
      if (StringUtils.isBlank(a.getPwdQuestion()) && StringUtils.isBlank(a.getPwdQuestion2()) && StringUtils.isBlank(a.getPwdQuestion3())) {	
         if (!PwdUtils.validatePassword(validPassword, a.getPassword())) {	
            var10002 = "sys.user.passwordError";	
            var10003 = new String[0];	
            var10005 = true;	
            return this.renderResult("false", text(var10002, var10003));	
         }	
	
         var10000 = a = true;	
      } else {	
         if (PwdUtils.validatePassword(oldPwdQuestionAnswer, a.getPwdQuestionAnswer()) && PwdUtils.validatePassword(oldPwdQuestionAnswer2, a.getPwdQuestionAnswer2()) && PwdUtils.validatePassword(oldPwdQuestionAnswer3, a.getPwdQuestionAnswer3())) {	
            a = true;	
         }	
	
         var10000 = a;	
      }	
	
      if (var10000) {	
         a.setPwdQuestion(user.getPwdQuestion());	
         a.setPwdQuestionAnswer(PwdUtils.encryptPassword(user.getPwdQuestionAnswer()));	
         a.setPwdQuestion2(user.getPwdQuestion2());	
         a.setPwdQuestionAnswer2(PwdUtils.encryptPassword(user.getPwdQuestionAnswer2()));	
         a.setPwdQuestion3(user.getPwdQuestion3());	
         a.setPwdQuestionAnswer3(PwdUtils.encryptPassword(user.getPwdQuestionAnswer3()));	
         this.userService.updateQuestion(a);	
         var10002 = "sys.user.pwdQuestionModifySuccess";	
         var10003 = new String[0];	
         var10005 = true;	
         return this.renderResult("true", text(var10002, var10003));	
      } else {	
         var10002 = "sys.user.pwdQuestioAswerError";	
         var10003 = new String[0];	
         var10005 = true;	
         return this.renderResult("false", text(var10002, var10003));	
      }	
   }	
	
   @RequiresPermissions({"user"})	
   @RequestMapping({"userSelect"})	
   public String userSelect(User user, String selectData, Model model) {	
      String a;	
      if (JsonMapper.fromJson(a = EncodeUtils.decodeUrl(selectData), Map.class) != null) {	
         model.addAttribute("selectData", a);	
      }	
	
      model.addAttribute("user", user);	
      return "modules/sys/userSelect";	
   }	
	
   @RequiresPermissions({"user"})	
   @RequestMapping({"info"})	
   public String info(User user, String op, Model model) {	
      if (StringUtils.isBlank(op)) {	
         op = "base";	
      }	
	
      model.addAttribute("op", op);	
      model.addAttribute("user", user.getCurrentUser());	
      return "modules/sys/userInfo";	
   }	
	
   @RequiresPermissions({"user"})	
   @PostMapping({"infoSaveBase"})	
   @ResponseBody	
   public String infoSaveBase(User user, HttpServletRequest request) {	
      String var10002;	
      String[] var10003;	
      boolean var10005;	
      if (StringUtils.isBlank(user.getUserName())) {	
         var10002 = "sys.user.userNameNotBlak";	
         var10003 = new String[0];	
         var10005 = true;	
         return this.renderResult("true", text(var10002, var10003));	
      } else {	
         User a = UserUtils.getUser();	
         a.setAvatarBase64(user.getAvatarBase64());	
         a.setUserName(user.getUserName());	
         a.setEmail(user.getEmail());	
         a.setMobile(user.getMobile());	
         a.setPhone(user.getPhone());	
         a.setSex(user.getSex());	
         a.setSign(user.getSign());	
         this.userService.updateUserInfo(a);	
         var10002 = "sys.user.infoSaveSuccess";	
         var10003 = new String[0];	
         var10005 = true;	
         return this.renderResult("true", text(var10002, var10003));	
      }	
   }	
	
   @RequiresPermissions({"user"})	
   @PostMapping({"infoSavePwd"})	
   @ResponseBody	
   public String infoSavePwd(User user, String oldPassword, String newPassword, String confirmNewPassword) {	
      User a = UserUtils.getUser();	
      String a;	
      if (StringUtils.isNotBlank(a = Global.getProperty("shiro.loginSubmit.secretKey"))) {	
         oldPassword = DesUtils.decode(oldPassword, a);	
         newPassword = DesUtils.decode(newPassword, a);	
         confirmNewPassword = DesUtils.decode(confirmNewPassword, a);	
      }	
	
      String var10002;	
      String[] var10003;	
      boolean var10005;	
      if (!PwdUtils.validatePassword(oldPassword, a.getPassword())) {	
         var10002 = "sys.user.oldPasswordError";	
         var10003 = new String[0];	
         var10005 = true;	
         return this.renderResult("false", text(var10002, var10003));	
      } else if (!StringUtils.equals(newPassword, confirmNewPassword)) {	
         var10002 = "sys.user.cofirmPasswrodError";	
         var10003 = new String[0];	
         var10005 = true;	
         return this.renderResult("false", text(var10002, var10003));	
      } else {	
         try {	
            this.userService.updatePassword(a.getUserCode(), confirmNewPassword);	
            var10002 = "sys.user.passwordModifySuccess";	
            var10003 = new String[0];	
            var10005 = true;	
            return this.renderResult("true", text(var10002, var10003));	
         } catch (ServiceException var8) {	
            return this.renderResult("false", var8.getMessage());	
         }	
      }	
   }	
	
   @RequiresPermissions({"user"})	
   @RequestMapping({"checkLoginCode"})	
   @ResponseBody	
   public String checkLoginCode(String oldLoginCode, String loginCode) {	
      return this.userService.checkLoginCode(oldLoginCode, loginCode);	
   }	
	
   @RequiresPermissions({"user"})	
   @RequestMapping({"listData"})	
   @ResponseBody	
   public Page listData(User user, HttpServletRequest request, HttpServletResponse response) {	
      if ("none".equals(user.getUserType())) {	
         return new Page(request, response);	
      } else {	
         user.setPage(new Page(request, response));	
         return this.userService.findPage(user);	
      }	
   }	
}	
