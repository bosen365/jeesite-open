/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.modules.sys.web;	
	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.web.BaseController;	
import com.jeesite.modules.sys.utils.ConfigUtils;	
import com.jeesite.modules.sys.web.h;	
import com.jeesite.modules.sys.web.m;	
import java.beans.PropertyEditor;	
import java.util.Date;	
import javax.servlet.http.HttpServletRequest;	
import javax.validation.ConstraintViolationException;	
import javax.validation.ValidationException;	
import org.apache.shiro.authc.AuthenticationException;	
import org.apache.shiro.authz.UnauthenticatedException;	
import org.apache.shiro.authz.UnauthorizedException;	
import org.hyperic.sigar.ProcFd;	
import org.springframework.validation.BindException;	
import org.springframework.web.bind.WebDataBinder;	
import org.springframework.web.bind.annotation.ControllerAdvice;	
import org.springframework.web.bind.annotation.ExceptionHandler;	
import org.springframework.web.bind.annotation.InitBinder;	
	
@ControllerAdvice	
public class AdviceController {	
    @InitBinder	
    protected void initBinder(WebDataBinder binder, HttpServletRequest request) {	
        WebDataBinder webDataBinder = binder;	
        webDataBinder.setAutoGrowCollectionLimit(Integer.MAX_VALUE);	
        String[] arrstring = new String[8];	
        arrstring[0] = "global";	
        arrstring[1] = "global.*";	
        arrstring[2] = "sqlMap";	
        arrstring[3] = "sqlMap.*";	
        arrstring[4] = "currentUser";	
        arrstring[5] = "currentUser.*";	
        arrstring[6] = "corpCode";	
        arrstring[7] = "coreName";	
        webDataBinder.setDisallowedFields(arrstring);	
        WebDataBinder webDataBinder2 = binder;	
        webDataBinder2.registerCustomEditor(String.class, new h(this));	
        webDataBinder2.registerCustomEditor(Date.class, new m(this));	
        if (webDataBinder2.getTarget() instanceof BaseEntity) {	
            BaseEntity a = (BaseEntity)binder.getTarget();	
            HttpServletRequest httpServletRequest = request;	
            httpServletRequest.setAttribute(BaseController.WEB_DATA_BINDER_SOURCE, a.clone());	
            httpServletRequest.setAttribute(BaseController.WEB_DATA_BINDER_TARGET, a);	
        }	
    }	
	
    @ExceptionHandler(value={UnauthenticatedException.class, UnauthorizedException.class, AuthenticationException.class})	
    protected String exceptionHandlerTo403Page() {	
        return "error/403";	
    }	
	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = (3 ^ 5) << 4 ^ 3 << 1;	
        (3 ^ 5) << 4 ^ 5 << 1;	
        int n4 = n2;	
        int n5 = 5 << 4 ^ 1;	
        while (n4 >= 0) {	
            int n6 = n2--;	
            arrc[n6] = (char)(s.charAt(n6) ^ n5);	
            if (n2 < 0) break;	
            int n7 = n2--;	
            arrc[n7] = (char)(s.charAt(n7) ^ n3);	
            n4 = n2;	
        }	
        return new String(arrc);	
    }	
	
    @ExceptionHandler(value={BindException.class, ConstraintViolationException.class, ValidationException.class})	
    protected String exceptionHandlerTo400Page() {	
        return "error/400";	
    }	
}	
	
