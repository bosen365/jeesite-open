/*	
 * Decompiled with CFR 0.139.	
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
import java.io.PrintWriter;	
import java.lang.annotation.Annotation;	
import java.util.ArrayList;	
import java.util.Collection;	
import java.util.List;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.hyperic.jni.ArchLoaderException;	
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
	
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {	
        Annotation[] a2;	
        int n;	
        int a3;	
        mavContainer.setRequestHandled(true);	
        int n2 = a3 = 0;	
        while (n2 < this.advices.size()) {	
            a2 = this.advices.get(a3);	
            if (a2.supports(returnType, null)) {	
                returnValue = a2.beforeBodyWrite(returnValue, returnType, MediaType.APPLICATION_JSON_UTF8, null, (ServerHttpRequest)new ServletServerHttpRequest((HttpServletRequest)webRequest.getNativeRequest(HttpServletRequest.class)), (ServerHttpResponse)new ServletServerHttpResponse((HttpServletResponse)webRequest.getNativeResponse(HttpServletResponse.class)));	
            }	
            n2 = ++a3;	
        }	
        HttpServletResponse a22 = (HttpServletResponse)webRequest.getNativeResponse(HttpServletResponse.class);	
        a2 = returnType.getMethodAnnotations();	
        ReturnJsonSerializer a4 = new ReturnJsonSerializer();	
        Annotation[] arrannotation = a2;	
        int n3 = arrannotation.length;	
        int n4 = n = 0;	
        while (n4 < n3) {	
            Annotation a5;	
            Annotation a6 = arrannotation[n];	
            if (a6 instanceof JsonField) {	
                a5 = (JsonField)a6;	
                a4.filter((JsonField)a5);	
            } else if (a6 instanceof JsonFields) {	
                int n5;	
                a5 = (JsonFields)a6;	
                JsonField[] arrjsonField = a5.value();	
                int n6 = arrjsonField.length;	
                int n7 = n5 = 0;	
                while (n7 < n6) {	
                    void a7;	
                    JsonField jsonField = arrjsonField[n5];	
                    a4.filter((JsonField)a7);	
                    n7 = ++n5;	
                }	
            }	
            n4 = ++n;	
        }	
        a22.setContentType("application/json");	
        String a8 = a4.toJson(returnValue);	
        a22.getWriter().write(a8);	
    }	
	
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {	
        return bean;	
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
            ReturnJsonHandler a2;	
            ArrayList<ReturnJsonHandler> a3;	
            ReturnJsonHandler returnJsonHandler;	
            block5 : {	
                int a4;	
                a3 = new ArrayList<ReturnJsonHandler>(((RequestMappingHandlerAdapter)bean).getReturnValueHandlers());	
                a2 = null;	
                int n = a4 = 0;	
                while (n < a3.size()) {	
                    HandlerMethodReturnValueHandler a5 = (HandlerMethodReturnValueHandler)a3.get(a4);	
                    if (a5 instanceof ReturnJsonHandler) {	
                        returnJsonHandler = a2 = (ReturnJsonHandler)a5;	
                        break block5;	
                    }	
                    n = ++a4;	
                }	
                returnJsonHandler = a2;	
            }	
            if (returnJsonHandler != null) {	
                a3.add(0, a2);	
                a3.remove(a2);	
                ((RequestMappingHandlerAdapter)bean).setReturnValueHandlers(a3);	
            }	
        }	
        return bean;	
    }	
	
    public boolean supportsReturnType(MethodParameter returnType) {	
        boolean a2 = returnType.getMethodAnnotation(JsonField.class) != null || returnType.getMethodAnnotation(JsonFields.class) != null;	
        return a2;	
    }	
}	
	
