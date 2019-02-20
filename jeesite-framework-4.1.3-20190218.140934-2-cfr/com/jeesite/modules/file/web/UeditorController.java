/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  javax.servlet.http.HttpServletRequest	
 *  javax.servlet.http.HttpServletResponse	
 *  org.apache.shiro.authz.annotation.RequiresPermissions	
 *  org.springframework.boot.autoconfigure.condition.ConditionalOnProperty	
 *  org.springframework.stereotype.Controller	
 *  org.springframework.web.bind.annotation.PathVariable	
 *  org.springframework.web.bind.annotation.RequestMapping	
 *  org.springframework.web.bind.annotation.ResponseBody	
 */	
package com.jeesite.modules.file.web;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.j.e;	
import com.jeesite.common.web.BaseController;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.apache.shiro.authz.annotation.RequiresPermissions;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;	
import org.springframework.stereotype.Controller;	
import org.springframework.web.bind.annotation.PathVariable;	
import org.springframework.web.bind.annotation.RequestMapping;	
import org.springframework.web.bind.annotation.ResponseBody;	
	
@Controller	
@RequestMapping(value={"${adminPath}/file/ueditor"})	
@ConditionalOnProperty(name={"file.enabled", "web.core.enabled"}, havingValue="true", matchIfMissing=true)	
public class UeditorController	
extends BaseController {	
    @RequiresPermissions(value={"user"})	
    @RequestMapping(value={"{action}"})	
    @ResponseBody	
    public String upload(@PathVariable String action, HttpServletRequest request, HttpServletResponse response) {	
        String a2 = Global.getUserfilesBaseDir(null);	
        return new e(request, a2, action).ALLATORIxDEMO();	
    }	
	
    @RequiresPermissions(value={"user"})	
    @RequestMapping(value={""})	
    @ResponseBody	
    public String upload(HttpServletRequest request, HttpServletResponse response) {	
        return this.upload(null, request, response);	
    }	
}	
	
