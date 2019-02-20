/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.web.http.ServletUtils	
 *  javax.servlet.http.HttpServletRequest	
 *  javax.servlet.http.HttpServletResponse	
 *  org.slf4j.Logger	
 *  org.slf4j.LoggerFactory	
 *  org.springframework.beans.factory.annotation.Value	
 *  org.springframework.ui.Model	
 *  org.springframework.web.bind.WebDataBinder	
 *  org.springframework.web.servlet.mvc.support.RedirectAttributes	
 */	
package com.jeesite.common.web;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.j.E;	
import com.jeesite.common.web.http.ServletUtils;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.hyperic.sigar.Tcp;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
import org.springframework.beans.factory.annotation.Value;	
import org.springframework.ui.Model;	
import org.springframework.web.bind.WebDataBinder;	
import org.springframework.web.servlet.mvc.support.RedirectAttributes;	
	
public abstract class BaseController {	
    protected static final String FORWARD = "forward:";	
    protected Logger logger;	
    public static final String WEB_DATA_BINDER_SOURCE = new StringBuilder().insert(0, WebDataBinder.class.getName()).append(".SOURCE").toString();	
    protected static final String REDIRECT = "redirect:";	
    @Value(value="${adminPath}")	
    protected String adminPath;	
    @Value(value="${frontPath}")	
    protected String frontPath;	
    public static final String WEB_DATA_BINDER_TARGET = new StringBuilder().insert(0, WebDataBinder.class.getName()).append(".T+RGET").toString();	
	
    protected String renderResult(String result, String message, Object data) {	
        return ServletUtils.renderResult((String)result, (String)message, (Object)data);	
    }	
	
    protected <E> E getWebDataBinderSource(HttpServletRequest request) {	
        return (E)request.getAttribute(WEB_DATA_BINDER_SOURCE);	
    }	
	
    protected <E> E getWebDataBinderTarget(HttpServletRequest request) {	
        return (E)request.getAttribute(WEB_DATA_BINDER_TARGET);	
    }	
	
    protected String renderResult(String result, String message) {	
        return this.renderResult(result, message, null);	
    }	
	
    protected String renderResult(HttpServletResponse response, String result, String message, Object data) {	
        return ServletUtils.renderResult((HttpServletResponse)response, (String)result, (String)message, (Object)data);	
    }	
	
    protected /* varargs */ void addMessage(RedirectAttributes redirectAttributes, String ... messages) {	
        int n;	
        StringBuilder a2 = new StringBuilder();	
        String[] arrstring = messages;	
        int n2 = arrstring.length;	
        int n3 = n = 0;	
        while (n3 < n2) {	
            String a3 = arrstring[n];	
            a2.append(a3).append(messages.length > 1 ? "<br/>" : "");	
            n3 = ++n;	
        }	
        redirectAttributes.addFlashAttribute("message", (Object)a2.toString());	
    }	
	
    public BaseController() {	
        BaseController baseController = this;	
        baseController.logger = LoggerFactory.getLogger(baseController.getClass());	
    }	
	
    protected /* varargs */ void addMessage(Model model, String ... messages) {	
        int n;	
        StringBuilder a2 = new StringBuilder();	
        String[] arrstring = messages;	
        int n2 = arrstring.length;	
        int n3 = n = 0;	
        while (n3 < n2) {	
            String a3 = arrstring[n];	
            a2.append(a3).append(messages.length > 1 ? "<br/>" : "");	
            n3 = ++n;	
        }	
        model.addAttribute("message", (Object)a2.toString());	
    }	
	
    protected String renderResult(String result, StringBuilder message) {	
        return this.renderResult(result, message != null ? message.toString() : "");	
    }	
	
    protected String renderResult(HttpServletResponse response, String result, String message) {	
        return ServletUtils.renderResult((HttpServletResponse)response, (String)result, (String)message, null);	
    }	
	
    public static /* varargs */ String text(String code, String ... params) {	
        return Global.getText(code, params);	
    }	
}	
	
