/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  javax.servlet.http.HttpServletRequest	
 *  javax.validation.ConstraintViolationException	
 *  javax.validation.ValidationException	
 *  org.apache.shiro.authc.AuthenticationException	
 *  org.apache.shiro.authz.UnauthenticatedException	
 *  org.apache.shiro.authz.UnauthorizedException	
 *  org.springframework.validation.BindException	
 *  org.springframework.web.bind.WebDataBinder	
 *  org.springframework.web.bind.annotation.ControllerAdvice	
 *  org.springframework.web.bind.annotation.ExceptionHandler	
 *  org.springframework.web.bind.annotation.InitBinder	
 */	
package com.jeesite.modules.sys.web;	
	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.mybatis.mapper.query.QueryColumn;	
import com.jeesite.common.web.BaseController;	
import com.jeesite.modules.sys.web.E;	
import com.jeesite.modules.sys.web.e;	
import java.beans.PropertyEditor;	
import java.util.Date;	
import javax.servlet.http.HttpServletRequest;	
import javax.validation.ConstraintViolationException;	
import javax.validation.ValidationException;	
import org.apache.shiro.authc.AuthenticationException;	
import org.apache.shiro.authz.UnauthenticatedException;	
import org.apache.shiro.authz.UnauthorizedException;	
import org.springframework.validation.BindException;	
import org.springframework.web.bind.WebDataBinder;	
import org.springframework.web.bind.annotation.ControllerAdvice;	
import org.springframework.web.bind.annotation.ExceptionHandler;	
import org.springframework.web.bind.annotation.InitBinder;	
	
@ControllerAdvice	
public class AdviceController {	
    @ExceptionHandler(value={UnauthenticatedException.class, UnauthorizedException.class, AuthenticationException.class})	
    protected String exceptionHandlerTo403Page() {	
        return AdviceController.ALLATORIxDEMO (" 47)7iqvv");	
    }	
	
    @InitBinder	
    protected void initBinder(WebDataBinder binder, HttpServletRequest request) {	
        WebDataBinder webDataBinder = binder;	
        webDataBinder.setAutoGrowCollectionLimit(Integer.MAX_VALUE);	
        webDataBinder.setDisallowedFields(new String[]{"global", AdviceController.ALLATORIxDEMO ("!))'')ho"), "sqlMa", AdviceController.ALLATORIxDEMO ("54*\b'5ho"), "currentUser", AdviceController.ALLATORIxDEMO ("&374 (1\u00136#7ho"), "corCode", AdviceController.ALLATORIxDEMO ("%*4 \b$+ ")});	
        WebDataBinder webDataBinder2 = binder;	
        webDataBinder2.registerCustomEditor(String.class, (PropertyEditor)new e(this));	
        webDataBinder2.registerCustomEditor(Date.class, (PropertyEditor)new E(this));	
        if (webDataBinder2.getTarget() instanceof BaseEntity) {	
            BaseEntity a2 = (BaseEntity)binder.getTarget();	
            HttpServletRequest httpServletRequest = request;	
            httpServletRequest.setAttribute(BaseController.WEB_DATA_BINDER_SOURCE, a2.clone());	
            httpServletRequest.setAttribute(BaseController.WEB_DATA_BINDER_TARGET, (Object)a2);	
        }	
    }	
	
    @ExceptionHandler(value={BindException.class, ConstraintViolationException.class, ValidationException.class})	
    protected String exceptionHandlerTo400Page() {	
        return "error/400";	
    }	
	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = 4 << 4 ^ 3 << 1;	
        int n4 = n2;	
        int n5 = 4 << 4 ^ 5;	
        4 << 4 ^ (3 ^ 5) << 1;	
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
}	
	
