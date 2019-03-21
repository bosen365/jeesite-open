/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  javax.servlet.http.HttpServletRequest	
 *  javax.servlet.http.HttpServletResponse	
 *  org.springframework.beans.BeansException	
 *  org.springframework.beans.factory.config.BeanPostProcessor	
 *  org.springframework.core.MethodParameter	
 *  org.springframework.http.MediaType	
 *  org.springframework.http.server.ServerHttpRequest	
 *  org.springframework.http.server.ServerHttpResponse	
 *  org.springframework.http.server.ServletServerHttpRequest	
 *  org.springframework.http.server.ServletServerHttpResponse	
 *  org.springframework.web.context.request.NativeWebRequest	
 *  org.springframework.web.method.support.HandlerMethodReturnValueHandler	
 *  org.springframework.web.method.support.ModelAndViewContainer	
 *  org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter	
 *  org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice	
 */	
package com.jeesite.common.web.returnjson;	
	
import com.jeesite.common.web.annotation.JsonField;	
import com.jeesite.common.web.annotation.JsonFields;	
import com.jeesite.common.web.returnjson.ReturnJsonSerializer;	
import com.jeesite.modules.sys.utils.ModuleUtils;	
import java.io.PrintWriter;	
import java.lang.annotation.Annotation;	
import java.util.ArrayList;	
import java.util.Collection;	
import java.util.List;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.springframework.beans.BeansException;	
import org.springframework.beans.factory.config.BeanPostProcessor;	
import org.springframework.core.MethodParameter;	
import org.springframework.http.MediaType;	
import org.springframework.http.server.ServerHttpRequest;	
import org.springframework.http.server.ServerHttpResponse;	
import org.springframework.http.server.ServletServerHttpRequest;	
import org.springframework.http.server.ServletServerHttpResponse;	
import org.springframework.web.context.request.NativeWebRequest;	
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;	
import org.springframework.web.method.support.ModelAndViewContainer;	
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;	
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;	
	
public class ReturnJsonHandler	
implements HandlerMethodReturnValueHandler,	
BeanPostProcessor {	
    List<ResponseBodyAdvice<Object>> advices;	
	
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {	
        return bean;	
    }	
	
    public boolean supportsReturnType(MethodParameter returnType) {	
        boolean a = returnType.getMethodAnnotation(JsonField.class) != null || returnType.getMethodAnnotation(JsonFields.class) != null;	
        return a;	
    }	
	
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {	
        Annotation[] a;	
        int a2;	
        int n;	
        mavContainer.setRequestHandled(true);	
        int n2 = a2 = 0;	
        while (n2 < this.advices.size()) {	
            a = this.advices.get(a2);	
            if (a.supports(returnType, null)) {	
                returnValue = a.beforeBodyWrite(returnValue, returnType, MediaType.APPLICATION_JSON_UTF8, null, (ServerHttpRequest)new ServletServerHttpRequest((HttpServletRequest)webRequest.getNativeRequest(HttpServletRequest.class)), (ServerHttpResponse)new ServletServerHttpResponse((HttpServletResponse)webRequest.getNativeResponse(HttpServletResponse.class)));	
            }	
            n2 = ++a2;	
        }	
        HttpServletResponse a22 = (HttpServletResponse)webRequest.getNativeResponse(HttpServletResponse.class);	
        a = returnType.getMethodAnnotations();	
        ReturnJsonSerializer a3 = new ReturnJsonSerializer();	
        Annotation[] arrannotation = a;	
        int n3 = arrannotation.length;	
        int n4 = n = 0;	
        while (n4 < n3) {	
            Annotation a4;	
            Annotation a5 = arrannotation[n];	
            if (a5 instanceof JsonField) {	
                a4 = (JsonField)a5;	
                a3.filter((JsonField)a4);	
            } else if (a5 instanceof JsonFields) {	
                int n5;	
                a4 = (JsonFields)a5;	
                JsonField[] arrjsonField = a4.value();	
                int n6 = arrjsonField.length;	
                int n7 = n5 = 0;	
                while (n7 < n6) {	
                    void a6;	
                    JsonField jsonField = arrjsonField[n5];	
                    a3.filter((JsonField)a6);	
                    n7 = ++n5;	
                }	
            }	
            n4 = ++n;	
        }	
        a22.setContentType("application/json");	
        String a7 = a3.toJson(returnValue);	
        a22.getWriter().write(a7);	
    }	
	
    public ReturnJsonHandler() {	
        ReturnJsonHandler returnJsonHandler = this;	
        returnJsonHandler.advices = new ArrayList<ResponseBodyAdvice<Object>>();	
    }	
	
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {	
        if (bean instanceof ResponseBodyAdvice) {	
            this.advices.add((ResponseBodyAdvice<Object>)((ResponseBodyAdvice)bean));	
            return bean;	
        }	
        if (bean instanceof RequestMappingHandlerAdapter) {	
            ReturnJsonHandler a;	
            ArrayList<ReturnJsonHandler> a2;	
            ReturnJsonHandler returnJsonHandler;	
            block5 : {	
                int a3;	
                a2 = new ArrayList<ReturnJsonHandler>(((RequestMappingHandlerAdapter)bean).getReturnValueHandlers());	
                a = null;	
                int n = a3 = 0;	
                while (n < a2.size()) {	
                    HandlerMethodReturnValueHandler a4 = (HandlerMethodReturnValueHandler)a2.get(a3);	
                    if (a4 instanceof ReturnJsonHandler) {	
                        returnJsonHandler = a = (ReturnJsonHandler)a4;	
                        break block5;	
                    }	
                    n = ++a3;	
                }	
                returnJsonHandler = a;	
            }	
            if (returnJsonHandler != null) {	
                a2.add(0, a);	
                a2.remove(a);	
                ((RequestMappingHandlerAdapter)bean).setReturnValueHandlers(a2);	
            }	
        }	
        return bean;	
    }	
}	
	
