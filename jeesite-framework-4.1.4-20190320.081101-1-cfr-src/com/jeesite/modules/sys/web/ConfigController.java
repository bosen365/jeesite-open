/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.lang.StringUtils	
 *  javax.servlet.http.HttpServletRequest	
 *  javax.servlet.http.HttpServletResponse	
 *  org.apache.shiro.authz.annotation.RequiresPermissions	
 *  org.springframework.beans.factory.annotation.Autowired	
 *  org.springframework.boot.autoconfigure.condition.ConditionalOnProperty	
 *  org.springframework.stereotype.Controller	
 *  org.springframework.ui.Model	
 *  org.springframework.validation.annotation.Validated	
 *  org.springframework.web.bind.annotation.ModelAttribute	
 *  org.springframework.web.bind.annotation.PostMapping	
 *  org.springframework.web.bind.annotation.RequestMapping	
 *  org.springframework.web.bind.annotation.ResponseBody	
 */	
package com.jeesite.modules.sys.web;	
	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.service.ServiceException;	
import com.jeesite.common.web.BaseController;	
import com.jeesite.modules.sys.entity.Config;	
import com.jeesite.modules.sys.entity.User;	
import com.jeesite.modules.sys.service.ConfigService;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.apache.shiro.authz.annotation.RequiresPermissions;	
import org.hyperic.sigar.FileSystemUsage;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;	
import org.springframework.stereotype.Controller;	
import org.springframework.ui.Model;	
import org.springframework.validation.annotation.Validated;	
import org.springframework.web.bind.annotation.ModelAttribute;	
import org.springframework.web.bind.annotation.PostMapping;	
import org.springframework.web.bind.annotation.RequestMapping;	
import org.springframework.web.bind.annotation.ResponseBody;	
	
@Controller	
@RequestMapping(value={"${adminPath}/sys/config"})	
@ConditionalOnProperty(name={"web.core.enabled"}, havingValue="true", matchIfMissing=true)	
public class ConfigController	
extends BaseController {	
    @Autowired	
    private ConfigService configService;	
	
    @RequiresPermissions(value={"sys:config:view"})	
    @RequestMapping(value={"listData"})	
    @ResponseBody	
    public Page<Config> listData(Config config, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {	
        void config2;	
        void request;	
        void response;	
        config2.setPage(new Page((HttpServletRequest)request, (HttpServletResponse)response));	
        return this.configService.findPage((Config)config2);	
    }	
	
    @RequiresPermissions(value={"sys:config:edit"})	
    @PostMapping(value={"save"})	
    @ResponseBody	
    public String save(@Validated Config config, HttpServletRequest request) {	
        Config a = (Config)super.getWebDataBinderSource(request);	
        if (a != null && "1".equals(a.getIsSys()) && !config.getCurrentUser().isSuperAdmin()) {	
            return this.renderResult("false", "越权操作，只有超级管理员才能修改系统数据！");	
        }	
        if (!config.getCurrentUser().isSuperAdmin()) {	
            Config config2 = config;	
            config2.setConfigName(a.getConfigName());	
            config2.setConfigKey(a.getConfigKey());	
        }	
        this.configService.save(config);	
        return this.renderResult("true", "保存参数成功！");	
    }	
	
    @ModelAttribute	
    public Config get(String id, boolean isNewRecord) {	
        return (Config)this.configService.get(id, isNewRecord);	
    }	
	
    @RequiresPermissions(value={"sys:config:edit"})	
    @RequestMapping(value={"checkConfigKey"})	
    @ResponseBody	
    public String checkConfigKey(String oldConfigKey, String configKey) {	
        Config a = new Config();	
        String string = configKey;	
        a.setConfigKey(string);	
        if (string != null && configKey.equals(oldConfigKey)) {	
            return "true";	
        }	
        if (configKey != null && this.configService.findCount(a) == 0L) {	
            return "true";	
        }	
        return "false";	
    }	
	
    @RequiresPermissions(value={"sys:config:view"})	
    @RequestMapping(value={"list"})	
    public String list(Config config, Model model) {	
        return "module/y/configLit";	
    }	
	
    @RequiresPermissions(value={"sys:config:edit"})	
    @RequestMapping(value={"delete"})	
    @ResponseBody	
    public String delete(Config config, HttpServletRequest request) {	
        if (StringUtils.isNotBlank((CharSequence)request.getParameter("isSys"))) {	
            return this.renderResult("false", "越权操作，isSy非法参数！");	
        }	
        Config a = (Config)super.getWebDataBinderSource(request);	
        if (a != null && "1".equals(a.getIsSys()) && !config.getCurrentUser().isSuperAdmin()) {	
            return this.renderResult("false", "越权操作，只有超级管理员才能修改系统数据！");	
        }	
        this.configService.delete(config);	
        return this.renderResult("true", "删除参数成功！");	
    }	
	
    @RequiresPermissions(value={"sys:config:view"})	
    @RequestMapping(value={"form"})	
    public String form(Config config, Model model) {	
        model.addAttribute("config", (Object)config);	
        return "module/y/configForm";	
    }	
}	
	
