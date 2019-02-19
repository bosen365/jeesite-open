package com.jeesite.common.web.returnjson;	
	
import com.jeesite.common.web.annotation.JsonField;	
import com.jeesite.common.web.annotation.JsonFields;	
import java.lang.annotation.Annotation;	
import java.util.ArrayList;	
import java.util.List;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.hyperic.jni.ArchLoaderException;	
import org.springframework.beans.BeansException;	
import org.springframework.beans.factory.config.BeanPostProcessor;	
import org.springframework.core.MethodParameter;	
import org.springframework.http.MediaType;	
import org.springframework.http.server.ServletServerHttpRequest;	
import org.springframework.http.server.ServletServerHttpResponse;	
import org.springframework.web.context.request.NativeWebRequest;	
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;	
import org.springframework.web.method.support.ModelAndViewContainer;	
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;	
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;	
	
public class ReturnJsonHandler implements HandlerMethodReturnValueHandler, BeanPostProcessor {	
   List advices = new ArrayList();	
	
   public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {	
      int var10000 = 0;	
      mavContainer.setRequestHandled(true);	
	
      for(int a = 0; var10000 < this.advices.size(); var10000 = a) {	
         ResponseBodyAdvice a;	
         if ((a = (ResponseBodyAdvice)this.advices.get(a)).supports(returnType, (Class)null)) {	
            Object var10007 = null;	
            returnValue = a.beforeBodyWrite(returnValue, returnType, MediaType.APPLICATION_JSON_UTF8, (Class)null, new ServletServerHttpRequest((HttpServletRequest)webRequest.getNativeRequest(HttpServletRequest.class)), new ServletServerHttpResponse((HttpServletResponse)webRequest.getNativeResponse(HttpServletResponse.class)));	
         }	
	
         ++a;	
      }	
	
      HttpServletResponse a = (HttpServletResponse)webRequest.getNativeResponse(HttpServletResponse.class);	
      Annotation[] a = returnType.getMethodAnnotations();	
      ReturnJsonSerializer a = new ReturnJsonSerializer();	
      Annotation[] var8 = a;	
      int var9 = a.length;	
	
      int var10;	
      for(var10000 = var10 = 0; var10000 < var9; var10000 = var10) {	
         Annotation a;	
         if ((a = var8[var10]) instanceof JsonField) {	
            JsonField a = (JsonField)a;	
            a.filter(a);	
         } else if (a instanceof JsonFields) {	
            JsonFields a;	
            JsonField[] var13;	
            int var14 = (var13 = (a = (JsonFields)a).value()).length;	
	
            int var15;	
            for(var10000 = var15 = 0; var10000 < var14; var10000 = var15) {	
               JsonField a = var13[var15];	
               ++var15;	
               a.filter(a);	
            }	
         }	
	
         ++var10;	
      }	
	
      a.setContentType("application/json");	
      String a = a.toJson(returnValue);	
      a.getWriter().write(a);	
   }	
	
   public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {	
      return bean;	
   }	
	
   public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {	
      if (bean instanceof ResponseBodyAdvice) {	
         this.advices.add((ResponseBodyAdvice)bean);	
         return bean;	
      } else {	
         if (bean instanceof RequestMappingHandlerAdapter) {	
            List a = new ArrayList(((RequestMappingHandlerAdapter)bean).getReturnValueHandlers());	
            ReturnJsonHandler a = null;	
            int a;	
            int var10000 = a = 0;	
	
            ReturnJsonHandler var7;	
            while(true) {	
               if (var10000 >= a.size()) {	
                  var7 = a;	
                  break;	
               }	
	
               HandlerMethodReturnValueHandler a;	
               if ((a = (HandlerMethodReturnValueHandler)a.get(a)) instanceof ReturnJsonHandler) {	
                  var7 = a = (ReturnJsonHandler)a;	
                  break;	
               }	
	
               ++a;	
               var10000 = a;	
            }	
	
            if (var7 != null) {	
               a.remove(a);	
               a.add(0, a);	
               ((RequestMappingHandlerAdapter)bean).setReturnValueHandlers(a);	
            }	
         }	
	
         return bean;	
      }	
   }	
	
   public boolean supportsReturnType(MethodParameter returnType) {	
      int a = returnType.getMethodAnnotation(JsonField.class) != null || returnType.getMethodAnnotation(JsonFields.class) != null;	
      return a;	
   }	
}	
