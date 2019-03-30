/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.beetl.view;	
	
import com.jeesite.common.config.Global;	
import java.util.Map;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.apache.commons.lang3.StringUtils;	
import org.beetl.ext.spring.BeetlSpringView;	
import org.hyperic.sigar.SysInfo;	
import org.hyperic.sigar.cmd.EventLogTail;	
import org.springframework.beans.factory.NoSuchBeanDefinitionException;	
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;	
	
public class BeetlView	
extends BeetlSpringView {	
    @Override	
    protected void renderMergedTemplateModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws NoSuchBeanDefinitionException, NoUniqueBeanDefinitionException {	
        String a;	
        Map<String, Object> map;	
        HttpServletRequest httpServletRequest = request;	
        String a2 = httpServletRequest.getRequestURI();	
        String string = httpServletRequest.getContextPath();	
        if (StringUtils.startsWith(a2, a + Global.getAdminPath())) {	
            a = new StringBuilder().insert(0, a).append(Global.getAdminPath()).toString();	
            map = model;	
        } else {	
            if (StringUtils.startsWith(a2, new StringBuilder().insert(0, a).append(Global.getFrontPath()).toString())) {	
                a = new StringBuilder().insert(0, a).append(Global.getFrontPath()).toString();	
            }	
            map = model;	
        }	
        map.put("ctx", a);	
        try {	
            super.renderMergedTemplateModel(model, request, response);	
            return;	
        }	
        catch (IllegalStateException a3) {	
            if (!StringUtils.contains((CharSequence)a3.getMessage(), "getOutputStream() has already been called for this response")) {	
                throw a3;	
            }	
            return;	
        }	
    }	
}	
	
