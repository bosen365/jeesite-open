/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.modules.sys.web;	
	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.mybatis.mapper.MapperException;	
import com.jeesite.common.mybatis.mapper.query.QueryDataScope;	
import com.jeesite.common.web.BaseController;	
import com.jeesite.modules.sys.web.c;	
import com.jeesite.modules.sys.web.i;	
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
        return "error/403";	
    }	
	
    @ExceptionHandler(value={BindException.class, ConstraintViolationException.class, ValidationException.class})	
    protected String exceptionHandlerTo400Page() {	
        return "error/400";	
    }	
	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = 5 << 4 ^ (3 << 2 ^ 3);	
        int n4 = n2;	
        int n5 = 1 << 3;	
        1 << 3 ^ 3;	
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
	
    @InitBinder	
    protected void initBinder(WebDataBinder binder, HttpServletRequest request) {	
        WebDataBinder webDataBinder = binder;	
        webDataBinder.setAutoGrowCollectionLimit(Integer.MAX_VALUE);	
        webDataBinder.setDisallowedFields("global", "globl.*", "sqlMap", "sqlMp.*", "currentUser", "currentUser.*", "corpCode", "coreName");	
        WebDataBinder webDataBinder2 = binder;	
        webDataBinder2.registerCustomEditor(String.class, new c(this));	
        webDataBinder2.registerCustomEditor(Date.class, new i(this));	
        if (webDataBinder2.getTarget() instanceof BaseEntity) {	
            BaseEntity a = (BaseEntity)binder.getTarget();	
            HttpServletRequest httpServletRequest = request;	
            httpServletRequest.setAttribute(BaseController.WEB_DATA_BINDER_SOURCE, a.clone());	
            httpServletRequest.setAttribute(BaseController.WEB_DATA_BINDER_TARGET, a);	
        }	
    }	
}	
	
