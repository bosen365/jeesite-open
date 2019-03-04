package com.jeesite.common.utils;	
	
import com.jeesite.common.mybatis.mapper.query.QueryDataScope;	
import com.jeesite.modules.sys.web.ValidCodeController;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
import org.springframework.beans.factory.DisposableBean;	
import org.springframework.context.ApplicationContext;	
import org.springframework.context.ApplicationContextAware;	
	
public class SpringUtils implements ApplicationContextAware, DisposableBean {	
   private static ApplicationContext applicationContext = null;	
   private static Logger logger = LoggerFactory.getLogger(SpringUtils.class);	
	
   // $FF: synthetic method	
   private static void assertContextInjected() {	
      if (applicationContext == null) {	
         Exception a = new IllegalStateException((new StringBuilder()).insert(0, "调用早了 ").append(SpringUtils.class).append(" 还未进行初始化！").toString());	
         logger.info(a.getMessage(), a);	
         throw new IllegalStateException(a);	
      }	
   }	
	
   public void setApplicationContext(ApplicationContext applicationContext) {	
      if (SpringUtils.applicationContext != null) {	
         logger.info((new StringBuilder()).insert(0, "SpringContextHolder中的ApplicationContext被覆盖, 原有ApplicationContext为:").append(SpringUtils.applicationContext).toString());	
      }	
	
      SpringUtils.applicationContext = applicationContext;	
   }	
	
   public static ApplicationContext getApplicationContext() {	
      assertContextInjected();	
      return applicationContext;	
   }	
	
   public void destroy() throws Exception {	
      clearHolder();	
   }	
	
   public static Object getBean(Class requiredType) {	
      assertContextInjected();	
      return applicationContext.getBean(requiredType);	
   }	
	
   public static void clearHolder() {	
      if (logger.isDebugEnabled()) {	
         logger.debug((new StringBuilder()).insert(0, "Clear ApplicationContext:").append(applicationContext).toString());	
      }	
	
      applicationContext = null;	
   }	
	
   public static Object getBean(String name) {	
      assertContextInjected();	
      return applicationContext.getBean(name);	
   }	
}	
