package com.jeesite.common.utils;	
	
import com.jeesite.common.io.FileUtils;	
import com.jeesite.common.io.ResourceUtils;	
import com.jeesite.common.j.E;	
import com.jeesite.common.lang.StringUtils;	
import java.io.File;	
import java.io.IOException;	
import java.io.InputStream;	
import org.hyperic.jni.ArchNotSupportedException;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
import org.springframework.beans.factory.DisposableBean;	
import org.springframework.context.ApplicationContext;	
import org.springframework.context.ApplicationContextAware;	
import org.springframework.core.io.Resource;	
	
public class SpringUtils implements ApplicationContextAware, DisposableBean {	
   private static ApplicationContext applicationContext = null;	
   private static Logger logger = LoggerFactory.getLogger(SpringUtils.class);	
	
   public static Object getBean(Class requiredType) {	
      assertContextInjected();	
      return applicationContext.getBean(requiredType);	
   }	
	
   // $FF: synthetic method	
   private static void assertContextInjected() {	
      if (applicationContext == null) {	
         String a = (new StringBuilder()).insert(0, "调用早了 ").append(SpringUtils.class).append(E.ALLATORIxDEMO("S込杙辿蠿剹妸卲ｲ")).toString();	
         throw new IllegalStateException(a);	
      }	
   }	
	
   public void setApplicationContext(ApplicationContext applicationContext) {	
      if (SpringUtils.applicationContext != null) {	
         logger.info((new StringBuilder()).insert(0, "SpringContextHolder中的ApplicationContext被覆盖, 原有ApplicationContext为:").append(SpringUtils.applicationContext).toString());	
      }	
	
      SpringUtils.applicationContext = applicationContext;	
   }	
	
   public static File getLicFile(String licName) {	
      String a = null;	
	
      String var10000;	
      label18: {	
         try {	
            a = ResourceUtils.getResourceLoader().getResource(E.ALLATORIxDEMO("\\")).getFile().getParentFile().getPath();	
         } catch (Exception var3) {	
            var10000 = a;	
            break label18;	
         }	
	
         var10000 = a;	
      }	
	
      if (StringUtils.isBlank(var10000)) {	
         a = System.getProperty("user.dir");	
      }	
	
      a = (new StringBuilder()).insert(0, a).append(File.separator).append(licName).toString();	
      return new File(a);	
   }	
	
   public static void clearHolder() {	
      if (logger.isDebugEnabled()) {	
         logger.debug((new StringBuilder()).insert(0, E.ALLATORIxDEMO("0\b\u0016\u0005\u0001D2\u0014\u0003\b\u001a\u0007\u0012\u0010\u001a\u000b\u001d'\u001c\n\u0007\u0001\u000b\u0010I")).append(applicationContext).toString());	
      }	
	
      applicationContext = null;	
   }	
	
   public static ApplicationContext getApplicationContext() {	
      assertContextInjected();	
      return applicationContext;	
   }	
	
   public void destroy() throws Exception {	
      clearHolder();	
   }	
	
   public static Object getBean(String name) {	
      assertContextInjected();	
      return applicationContext.getBean(name);	
   }	
	
   public static InputStream getInputStream() throws IOException {	
      File a;	
      if ((a = getLicFile("jeesite.lic")).exists()) {	
         return FileUtils.openInputStream(a);	
      } else {	
         Resource a;	
         if ((a = ResourceUtils.getResource(E.ALLATORIxDEMO("\u0019\u0001\u0016\u0017\u001a\u0010\u0016J\u001f\r\u0010"))).exists()) {	
            try {	
               return a.getInputStream();	
            } catch (IOException var3) {	
            }	
         }	
	
         return null;	
      }	
   }	
}	
