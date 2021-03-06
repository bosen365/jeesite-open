/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.modules.sys.web;	
	
import com.jeesite.autoconfigure.sys.FileAutoConfiguration;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.shiro.realm.BaseAuthorizingRealm;	
import com.jeesite.common.web.BaseController;	
import com.jeesite.modules.sys.entity.User;	
import com.jeesite.modules.sys.utils.PwdUtils;	
import com.jeesite.modules.sys.utils.UserUtils;	
import java.io.Serializable;	
import java.util.HashMap;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.hyperic.sigar.ProcCredName;	
import org.slf4j.Logger;	
import org.springframework.stereotype.Controller;	
import org.springframework.ui.Model;	
import org.springframework.web.bind.annotation.RequestMapping;	
import org.springframework.web.bind.annotation.ResponseBody;	
import org.springframework.web.multipart.MultipartFile;	
	
@Controller	
public class LicenceController	
extends BaseController {	
    @RequestMapping(value={"licence/sava"})	
    @ResponseBody	
    public String licenceSave(String username, String password, MultipartFile licFile, HttpServletRequest request, HttpServletResponse response) {	
        Serializable a;	
        if (!UserUtils.getUser().isAdmin()) {	
            if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {	
                return this.renderResult("false", "用户名或密码不能为空");	
            }	
            a = UserUtils.getByLoginCode(username);	
            if (a == null || a != null && (!((User)a).isAdmin() || !PwdUtils.validatePassword(password, ((User)a).getPassword()))) {	
                if (a != null && BaseAuthorizingRealm.isValidCodeLogin(((User)a).getLoginCode(), null, "failed")) {	
                    return this.renderResult("false", "您填写的账号或密码错误次数过多，账号已被锁定");	
                }	
                return this.renderResult("false", "您填写的账号或密码错误");	
            }	
            BaseAuthorizingRealm.isValidCodeLogin(((User)a).getLoginCode(), null, "success");	
        }	
        try {	
            a = new HashMap();	
            if (StringUtils.inString((String)a.get("type"), "0")) {	
                return this.renderResult("true", new StringBuilder().insert(0, "恭喜您，激活成功！<br/>您激活的服务为").append((String)a.get("title")).append("。<br/>请重启服务，开启畅快体验之旅").toString());	
            }	
        }	
        catch (Exception a2) {	
            this.logger.error("Read licence error.", a2);	
        }	
        return this.renderResult("false", "对不起，激活失败！您提交的许可文件不正确。");	
    }	
	
    @RequestMapping(value={"licence"})	
    public String licence(HttpServletRequest request, HttpServletResponse response, Model model) {	
        request.setAttribute("message", "产品许可信息.");	
        return "error/402";	
    }	
}	
	
