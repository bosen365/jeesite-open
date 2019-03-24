/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.modules.sys.web;	
	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.shiro.cas.CasOutHandler;	
import com.jeesite.common.validator.ValidatorUtils;	
import com.jeesite.common.web.BaseController;	
import com.jeesite.modules.sys.entity.Config;	
import com.jeesite.modules.sys.entity.User;	
import com.jeesite.modules.sys.service.ConfigService;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.apache.shiro.authz.annotation.RequiresPermissions;	
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
	
    /*	
     * WARNING - void declaration	
     */	
    @RequiresPermissions(value={"sys:config:view"})	
    @RequestMapping(value={"listData"})	
    @ResponseBody	
    public Page<Config> listData(Config config, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {	
        void response;	
        void config2;	
        void request;	
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
	
    @RequiresPermissions(value={"sys:config:edit"})	
    @RequestMapping(value={"delete"})	
    @ResponseBody	
    public String delete(Config config, HttpServletRequest request) {	
        if (StringUtils.isNotBlank(request.getParameter("isSys"))) {	
            return this.renderResult("false", "越权操作，isSys非法参数！");	
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
        model.addAttribute("config", config);	
        return "modules/sys/configForm";	
    }	
	
    @RequiresPermissions(value={"sys:config:view"})	
    @RequestMapping(value={"list"})	
    public String list(Config config, Model model) {	
        return "modules/sys/configList";	
    }	
}	
	
