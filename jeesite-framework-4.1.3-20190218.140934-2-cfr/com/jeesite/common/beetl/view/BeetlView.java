/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  javax.servlet.http.HttpServletRequest	
 *  javax.servlet.http.HttpServletResponse	
 *  org.apache.commons.lang3.StringUtils	
 *  org.beetl.ext.spring.BeetlSpringView	
 *  org.springframework.beans.factory.NoSuchBeanDefinitionException	
 *  org.springframework.beans.factory.NoUniqueBeanDefinitionException	
 */	
package com.jeesite.common.beetl.view;	
	
import com.jeesite.common.config.Global;	
import java.util.Map;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.apache.commons.lang3.StringUtils;	
import org.beetl.ext.spring.BeetlSpringView;	
import org.hyperic.sigar.CpuPerc;	
import org.hyperic.sigar.Who;	
import org.springframework.beans.factory.NoSuchBeanDefinitionException;	
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;	
	
public class BeetlView	
extends BeetlSpringView {	
    protected void renderMergedTemplateModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws NoSuchBeanDefinitionException, NoUniqueBeanDefinitionException {	
        String a2;	
        Map<String, Object> map;	
        HttpServletRequest httpServletRequest = request;	
        String a3 = httpServletRequest.getRequestURI();	
        String string = httpServletRequest.getContextPath();	
        if (StringUtils.startsWith((CharSequence)a3, (CharSequence)(a2 + Global.getAdminPath()))) {	
            a2 = new StringBuilder().insert(0, a2).append(Global.getAdminPath()).toString();	
            map = model;	
        } else {	
            if (StringUtils.startsWith((CharSequence)a3, (CharSequence)new StringBuilder().insert(0, a2).append(Global.getFrontPath()).toString())) {	
                a2 = new StringBuilder().insert(0, a2).append(Global.getFrontPath()).toString();	
            }	
            map = model;	
        }	
        map.put("ctx", a2);	
        try {	
            super.renderMergedTemplateModel(model, request, response);	
            return;	
        }	
        catch (IllegalStateException a4) {	
            if (!StringUtils.contains((CharSequence)a4.getMessage(), (CharSequence)"getOutputStreamB) has already been called for this response")) {	
                throw a4;	
            }	
            return;	
        }	
    }	
}	
	
