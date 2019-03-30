/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.modules.sys.web;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.common.shiro.realm.BaseAuthorizingRealm;	
import com.jeesite.common.utils.SpringUtils;	
import com.jeesite.common.web.BaseController;	
import com.jeesite.common.web.http.ServletUtils;	
import com.jeesite.modules.sys.entity.User;	
import com.jeesite.modules.sys.utils.ModuleUtils;	
import com.jeesite.modules.sys.utils.PwdUtils;	
import com.jeesite.modules.sys.utils.UserUtils;	
import java.io.File;	
import java.io.IOException;	
import java.io.InputStream;	
import java.util.Map;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.hyperic.sigar.NetInterfaceStat;	
import org.slf4j.Logger;	
import org.springframework.stereotype.Controller;	
import org.springframework.ui.Model;	
import org.springframework.web.bind.annotation.PathVariable;	
import org.springframework.web.bind.annotation.RequestMapping;	
import org.springframework.web.bind.annotation.ResponseBody;	
import org.springframework.web.multipart.MultipartFile;	
	
@Controller	
public class GlobalController	
extends BaseController {	
    @RequestMapping(value={"licence/save"})	
    @ResponseBody	
    public String abcdefg(String username, String password, MultipartFile licFile, HttpServletRequest request, HttpServletResponse response) {	
        Object a;	
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
            a = licFile.getInputStream();	
            Map<String, String> a2 = MapperHelper.ck((InputStream)a);	
            String[] arrstring = new String[2];	
            arrstring[0] = "1";	
            arrstring[1] = "2";	
            if (StringUtils.inString(a2.get("type"), arrstring)) {	
                File a3 = SpringUtils.getLicFile("jeesite.lic");	
                if (a3.exists()) {	
                    a3.delete();	
                }	
                licFile.transferTo(a3);	
                return this.renderResult("true", new StringBuilder().insert(0, "恭喜您，激活成功！<br/>您激活的服务为").append(a2.get("title")).append("<br/>请重启服务，开启畅快体验之旅").toString());	
            }	
        }	
        catch (IOException a4) {	
            this.logger.error("Read licence error.");	
        }	
        return this.renderResult("false", "对不起，激活失败！您提交的许可文件不正确。");	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    @RequestMapping(value={"/global.min.js"})	
    @ResponseBody	
    public String globalJs(String ctx, HttpServletRequest request, Model model) {	
        void a;	
        StringBuilder a2 = new StringBuilder();	
        String string = request.getContextPath();	
        StringBuilder stringBuilder = a2;	
        stringBuilder.append("lang='" + Global.getLang() + "',");	
        stringBuilder.append(new StringBuilder().insert(0, "ctx='").append((String)StringUtils.defaultIfBlank(ctx, a)).append("',").toString());	
        a2.append(new StringBuilder().insert(0, "ctxPath='").append((String)a).append("',").toString());	
        a2.append(new StringBuilder().insert(0, "ctxAdmin='").append((String)a).append(Global.getAdminPath()).append("',").toString());	
        a2.append(new StringBuilder().insert(0, "ctxFront='").append((String)a).append(Global.getFrontPath()).append("',").toString());	
        a2.append(new StringBuilder().insert(0, "ctxStatic='").append((String)a).append("/static',").toString());	
        a2.append(new StringBuilder().insert(0, "Global={").append(Global.getConst("Global.Fields")).append("}").toString());	
        return a2.toString();	
    }	
	
    @RequestMapping(value={"error/{errorCode}"})	
    public String error(@PathVariable String errorCode) {	
        return new StringBuilder().insert(0, "error/").append(errorCode).toString();	
    }	
	
    @RequestMapping(value={"lang/{lang}"})	
    public String lang(@PathVariable String lang, HttpServletRequest request, HttpServletResponse response) {	
        if (StringUtils.isNotBlank(lang)) {	
            Global.setLang(lang, request, response);	
        }	
        if (ServletUtils.isAjaxRequest(request)) {	
            return this.renderResult("1", "切换成功！");	
        }	
        return new StringBuilder().insert(0, "redirect:").append(this.adminPath).append("/index").toString();	
    }	
	
    @RequestMapping(value={""})	
    public String defaultPath() {	
        return new StringBuilder().insert(0, "redirect:").append(Global.getProperty("shiro.defaultPath")).toString();	
    }	
}	
	
