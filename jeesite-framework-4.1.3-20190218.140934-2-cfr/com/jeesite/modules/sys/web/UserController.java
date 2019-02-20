/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.codec.DesUtils	
 *  com.jeesite.common.codec.EncodeUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  com.jeesite.common.mapper.JsonMapper	
 *  javax.servlet.http.HttpServletRequest	
 *  javax.servlet.http.HttpServletResponse	
 *  org.apache.shiro.authz.annotation.RequiresPermissions	
 *  org.springframework.beans.factory.annotation.Autowired	
 *  org.springframework.boot.autoconfigure.condition.ConditionalOnProperty	
 *  org.springframework.stereotype.Controller	
 *  org.springframework.ui.Model	
 *  org.springframework.web.bind.annotation.PostMapping	
 *  org.springframework.web.bind.annotation.RequestMapping	
 *  org.springframework.web.bind.annotation.ResponseBody	
 */	
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
@RequestMapping(value={"${adminPath}/sys/user"})	
@ConditionalOnProperty(name={"web.core.enabled"}, havingValue="true", matchIfMissing=true)	
public class UserController	
extends BaseController {	
    @Autowired	
    private UserService userService;	
	
    @RequiresPermissions(value={"user"})	
    @PostMapping(value={"infoSavePqa"})	
    @ResponseBody	
    public String infoSavePqa(User user, String validPassword, String oldPwdQuestionAnswer, String oldPwdQuestionAnswer2, String oldPwdQuestionAnswer3) {	
        boolean bl;	
        User a2 = UserUtils.getUser();	
        String a3 = Global.getProperty("shiro.loginSubmit.secretKey");	
        if (StringUtils.isNotBlank((CharSequence)a3)) {	
            validPassword = DesUtils.decode((String)validPassword, (String)a3);	
            oldPwdQuestionAnswer = DesUtils.decode((String)oldPwdQuestionAnswer, (String)a3);	
            oldPwdQuestionAnswer2 = DesUtils.decode((String)oldPwdQuestionAnswer2, (String)a3);	
            oldPwdQuestionAnswer3 = DesUtils.decode((String)oldPwdQuestionAnswer3, (String)a3);	
            User user2 = user;	
            user2.setPwdQuestionAnswer(DesUtils.decode((String)user2.getPwdQuestionAnswer(), (String)a3));	
            user2.setPwdQuestionAnswer2(DesUtils.decode((String)user2.getPwdQuestionAnswer2(), (String)a3));	
            user2.setPwdQuestionAnswer3(DesUtils.decode((String)user2.getPwdQuestionAnswer3(), (String)a3));	
        }	
        boolean a4 = false;	
        if (StringUtils.isBlank((CharSequence)a2.getPwdQuestion()) && StringUtils.isBlank((CharSequence)a2.getPwdQuestion2()) && StringUtils.isBlank((CharSequence)a2.getPwdQuestion3())) {	
            if (!PwdUtils.validatePassword(validPassword, a2.getPassword())) {	
                return this.renderResult("false", UserController.text("sys.user.passwordError", new String[0]));	
            }	
            bl = a4 = true;	
        } else {	
            if (PwdUtils.validatePassword(oldPwdQuestionAnswer, a2.getPwdQuestionAnswer()) && PwdUtils.validatePassword(oldPwdQuestionAnswer2, a2.getPwdQuestionAnswer2()) && PwdUtils.validatePassword(oldPwdQuestionAnswer3, a2.getPwdQuestionAnswer3())) {	
                a4 = true;	
            }	
            bl = a4;	
        }	
        if (bl) {	
            UserController userController = this;	
            User user3 = a2;	
            User user4 = user;	
            User user5 = a2;	
            User user6 = user;	
            a2.setPwdQuestion(user6.getPwdQuestion());	
            user5.setPwdQuestionAnswer(PwdUtils.encryptPassword(user6.getPwdQuestionAnswer()));	
            user5.setPwdQuestion2(user.getPwdQuestion2());	
            a2.setPwdQuestionAnswer2(PwdUtils.encryptPassword(user4.getPwdQuestionAnswer2()));	
            user3.setPwdQuestion3(user4.getPwdQuestion3());	
            user3.setPwdQuestionAnswer3(PwdUtils.encryptPassword(user.getPwdQuestionAnswer3()));	
            userController.userService.updateQuestion(a2);	
            return userController.renderResult("true", UserController.text("sys.user.pwdQuestionModifySuccess", new String[0]));	
        }	
        return this.renderResult("false", UserController.text("sys.user.pwdQuestioAswerError", new String[0]));	
    }	
	
    @RequiresPermissions(value={"user"})	
    @RequestMapping(value={"userSelect"})	
    public String userSelect(User user, String selectData, Model model) {	
        String a2 = EncodeUtils.decodeUrl((String)selectData);	
        if (JsonMapper.fromJson((String)a2, Map.class) != null) {	
            model.addAttribute("selectData", (Object)a2);	
        }	
        model.addAttribute("user", (Object)user);	
        return "modules/sys/userSelect";	
    }	
	
    @RequiresPermissions(value={"user"})	
    @RequestMapping(value={"info"})	
    public String info(User user, String op, Model model) {	
        if (StringUtils.isBlank((CharSequence)op)) {	
            op = "base";	
        }	
        model.addAttribute("user", (Object)user.getCurrentUser());	
        model.addAttribute("op", (Object)op);	
        return "modules/sys/userInfo";	
    }	
	
    @RequiresPermissions(value={"user"})	
    @PostMapping(value={"infoSaveBase"})	
    @ResponseBody	
    public String infoSaveBase(User user, HttpServletRequest request) {	
        void a2;	
        if (StringUtils.isBlank((CharSequence)user.getUserName())) {	
            return this.renderResult("true", UserController.text("sys.user.userNameNotBlak", new String[0]));	
        }	
        User user2 = UserUtils.getUser();	
        UserController userController = this;	
        void v1 = a2;	
        User user3 = user;	
        void v3 = a2;	
        User user4 = user;	
        a2.setAvatarBase64(user.getAvatarBase64());	
        a2.setUserName(user4.getUserName());	
        v3.setEmail(user4.getEmail());	
        v3.setMobile(user.getMobile());	
        a2.setPhone(user3.getPhone());	
        v1.setSex(user3.getSex());	
        v1.setSign(user.getSign());	
        userController.userService.updateUserInfo((User)a2);	
        return userController.renderResult("true", UserController.text("sys.user.infoSaveSuccess", new String[0]));	
    }	
	
    @RequiresPermissions(value={"user"})	
    @PostMapping(value={"infoSavePwd"})	
    @ResponseBody	
    public String infoSavePwd(User user, String oldPassword, String newPassword, String confirmNewPassword) {	
        User a2 = UserUtils.getUser();	
        String a3 = Global.getProperty("shiro.loginSubmit.secretKey");	
        if (StringUtils.isNotBlank((CharSequence)a3)) {	
            oldPassword = DesUtils.decode((String)oldPassword, (String)a3);	
            newPassword = DesUtils.decode((String)newPassword, (String)a3);	
            confirmNewPassword = DesUtils.decode((String)confirmNewPassword, (String)a3);	
        }	
        if (!PwdUtils.validatePassword(oldPassword, a2.getPassword())) {	
            return this.renderResult("false", UserController.text("sys.user.oldPasswordError", new String[0]));	
        }	
        if (!StringUtils.equals((CharSequence)newPassword, (CharSequence)confirmNewPassword)) {	
            return this.renderResult("false", UserController.text("sys.user.cofirmPasswrodError", new String[0]));	
        }	
        try {	
            this.userService.updatePassword(a2.getUserCode(), confirmNewPassword);	
            return this.renderResult("true", UserController.text("sys.user.passwordModifySuccess", new String[0]));	
        }	
        catch (ServiceException a4) {	
            return this.renderResult("false", a4.getMessage());	
        }	
    }	
	
    @RequiresPermissions(value={"user"})	
    @RequestMapping(value={"checkLoginCode"})	
    @ResponseBody	
    public String checkLoginCode(String oldLoginCode, String loginCode) {	
        return this.userService.checkLoginCode(oldLoginCode, loginCode);	
    }	
	
    @RequiresPermissions(value={"user"})	
    @RequestMapping(value={"listData"})	
    @ResponseBody	
    public Page<User> listData(User user, HttpServletRequest request, HttpServletResponse response) {	
        if ("none".equals(user.getUserType())) {	
            return new Page<User>(request, response);	
        }	
        user.setPage(new Page(request, response));	
        return this.userService.findPage(user);	
    }	
}	
	
