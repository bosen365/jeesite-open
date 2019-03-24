/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.modules.sys.web;	
	
import com.jeesite.common.codec.DesUtils;	
import com.jeesite.common.codec.EncodeUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mapper.JsonMapper;	
import com.jeesite.common.service.ServiceException;	
import com.jeesite.common.web.BaseController;	
import com.jeesite.modules.sys.entity.User;	
import com.jeesite.modules.sys.service.UserService;	
import com.jeesite.modules.sys.utils.ConfigUtils;	
import com.jeesite.modules.sys.utils.PwdUtils;	
import com.jeesite.modules.sys.utils.UserUtils;	
import java.util.Map;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.apache.shiro.authz.annotation.RequiresPermissions;	
import org.hyperic.sigar.ProcCred;	
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
    @RequestMapping(value={"userSelect"})	
    public String userSelect(User user, String selectData, Model model) {	
        String a = EncodeUtils.decodeUrl(selectData);	
        if (JsonMapper.fromJson(a, Map.class) != null) {	
            model.addAttribute("selectData", a);	
        }	
        model.addAttribute("user", user);	
        return "modules/sys/userSelect";	
    }	
	
    @RequiresPermissions(value={"user"})	
    @PostMapping(value={"infoSavePwd"})	
    @ResponseBody	
    public String infoSavePwd(User user, String oldPassword, String newPassword, String confirmNewPassword) {	
        User a = UserUtils.getUser();	
        String a2 = Global.getProperty("shiro.loginSubmit.secretKey");	
        if (StringUtils.isNotBlank(a2)) {	
            oldPassword = DesUtils.decode(oldPassword, a2);	
            newPassword = DesUtils.decode(newPassword, a2);	
            confirmNewPassword = DesUtils.decode(confirmNewPassword, a2);	
        }	
        if (!PwdUtils.validatePassword(oldPassword, a.getPassword())) {	
            return this.renderResult("false", UserController.text("sys.user.oldPasswordError", new String[0]));	
        }	
        if (!StringUtils.equals(newPassword, confirmNewPassword)) {	
            return this.renderResult("false", UserController.text("sys.user.confirmPasswroError", new String[0]));	
        }	
        try {	
            this.userService.updatePassword(a.getUserCode(), confirmNewPassword);	
            return this.renderResult("true", UserController.text("sys.user.passwordModifySuccess", new String[0]));	
        }	
        catch (ServiceException a3) {	
            return this.renderResult("false", a3.getMessage());	
        }	
    }	
	
    @RequiresPermissions(value={"user"})	
    @RequestMapping(value={"checkLoginCode"})	
    @ResponseBody	
    public String checkLoginCode(String oldLoginCode, String loginCode) {	
        return this.userService.checkLoginCode(oldLoginCode, loginCode);	
    }	
	
    @RequiresPermissions(value={"user"})	
    @PostMapping(value={"infoSavePqa"})	
    @ResponseBody	
    public String infoSavePqa(User user, String validPassword, String oldPwdQuestionAnswer, String oldPwdQuestionAnswer2, String oldPwdQuestionAnswer3) {	
        boolean bl;	
        User a = UserUtils.getUser();	
        String a2 = Global.getProperty("shiro.loginSubmit.secretKey");	
        if (StringUtils.isNotBlank(a2)) {	
            validPassword = DesUtils.decode(validPassword, a2);	
            oldPwdQuestionAnswer = DesUtils.decode(oldPwdQuestionAnswer, a2);	
            oldPwdQuestionAnswer2 = DesUtils.decode(oldPwdQuestionAnswer2, a2);	
            oldPwdQuestionAnswer3 = DesUtils.decode(oldPwdQuestionAnswer3, a2);	
            User user2 = user;	
            user2.setPwdQuestionAnswer(DesUtils.decode(user2.getPwdQuestionAnswer(), a2));	
            user2.setPwdQuestionAnswer2(DesUtils.decode(user2.getPwdQuestionAnswer2(), a2));	
            user2.setPwdQuestionAnswer3(DesUtils.decode(user2.getPwdQuestionAnswer3(), a2));	
        }	
        boolean a3 = false;	
        if (StringUtils.isBlank(a.getPwdQuestion()) && StringUtils.isBlank(a.getPwdQuestion2()) && StringUtils.isBlank(a.getPwdQuestion3())) {	
            if (!PwdUtils.validatePassword(validPassword, a.getPassword())) {	
                return this.renderResult("false", UserController.text("sys.user.passwordError", new String[0]));	
            }	
            bl = a3 = true;	
        } else {	
            if (PwdUtils.validatePassword(oldPwdQuestionAnswer, a.getPwdQuestionAnswer()) && PwdUtils.validatePassword(oldPwdQuestionAnswer2, a.getPwdQuestionAnswer2()) && PwdUtils.validatePassword(oldPwdQuestionAnswer3, a.getPwdQuestionAnswer3())) {	
                a3 = true;	
            }	
            bl = a3;	
        }	
        if (bl) {	
            UserController userController = this;	
            User user3 = a;	
            User user4 = user;	
            User user5 = a;	
            User user6 = user;	
            a.setPwdQuestion(user6.getPwdQuestion());	
            user5.setPwdQuestionAnswer(PwdUtils.encryptPassword(user6.getPwdQuestionAnswer()));	
            user5.setPwdQuestion2(user.getPwdQuestion2());	
            a.setPwdQuestionAnswer2(PwdUtils.encryptPassword(user4.getPwdQuestionAnswer2()));	
            user3.setPwdQuestion3(user4.getPwdQuestion3());	
            user3.setPwdQuestionAnswer3(PwdUtils.encryptPassword(user.getPwdQuestionAnswer3()));	
            userController.userService.updateQuestion(a);	
            return userController.renderResult("true", UserController.text("sys.user.pwQuestionModifySuccess", new String[0]));	
        }	
        return this.renderResult("false", UserController.text("sys.user.pwdQuestionAnswerError", new String[0]));	
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
	
    @RequiresPermissions(value={"user"})	
    @RequestMapping(value={"info"})	
    public String info(User user, String op, Model model) {	
        if (StringUtils.isBlank(op)) {	
            op = "base";	
        }	
        model.addAttribute("op", op);	
        model.addAttribute("user", user.getCurrentUser());	
        return "modules/sys/userInfo";	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    @RequiresPermissions(value={"user"})	
    @PostMapping(value={"infoSaveBase"})	
    @ResponseBody	
    public String infoSaveBase(User user, HttpServletRequest request) {	
        void a;	
        if (StringUtils.isBlank(user.getUserName())) {	
            return this.renderResult("true", UserController.text("sys.user.userNameNotBlank", new String[0]));	
        }	
        User user2 = UserUtils.getUser();	
        UserController userController = this;	
        void v1 = a;	
        User user3 = user;	
        void v3 = a;	
        User user4 = user;	
        a.setAvatarBase64(user.getAvatarBase64());	
        a.setUserName(user4.getUserName());	
        v3.setEmail(user4.getEmail());	
        v3.setMobile(user.getMobile());	
        a.setPhone(user3.getPhone());	
        v1.setSex(user3.getSex());	
        v1.setSign(user.getSign());	
        userController.userService.updateUserInfo((User)a);	
        return userController.renderResult("true", UserController.text("sys.user.infoSaveSuccess", new String[0]));	
    }	
}	
	
