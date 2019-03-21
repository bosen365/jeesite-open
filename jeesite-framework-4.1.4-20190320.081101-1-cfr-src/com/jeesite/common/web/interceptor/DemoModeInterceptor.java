/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  javax.servlet.http.HttpServletRequest	
 *  javax.servlet.http.HttpServletResponse	
 *  org.springframework.web.servlet.HandlerInterceptor	
 *  org.springframework.web.servlet.ModelAndView	
 */	
package com.jeesite.common.web.interceptor;	
	
import com.jeesite.common.service.BaseService;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.springframework.web.servlet.HandlerInterceptor;	
import org.springframework.web.servlet.ModelAndView;	
	
public class DemoModeInterceptor	
extends BaseService	
implements HandlerInterceptor {	
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {	
        return true;	
    }	
	
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {	
    }	
	
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {	
    }	
}	
	
