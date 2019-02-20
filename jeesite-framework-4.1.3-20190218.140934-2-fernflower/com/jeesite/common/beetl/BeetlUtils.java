package com.jeesite.common.beetl;	
	
import com.jeesite.autoconfigure.core.DataSourceAutoConfiguration;	
import com.jeesite.common.beetl.e.D;	
import com.jeesite.common.beetl.e.m;	
import com.jeesite.common.collect.SetUtils;	
import com.jeesite.common.io.PropertiesUtils;	
import com.jeesite.common.io.ResourceUtils;	
import com.jeesite.common.lang.ExceptionUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.shiro.cas.CasOutHandler;	
import java.io.IOException;	
import java.util.Iterator;	
import java.util.Map;	
import java.util.Set;	
import java.util.Map.Entry;	
import org.beetl.core.Configuration;	
import org.beetl.core.GroupTemplate;	
import org.beetl.core.Template;	
import org.beetl.core.misc.BeetlUtil;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
import org.springframework.core.io.Resource;	
	
public class BeetlUtils {	
   private static Logger logger = LoggerFactory.getLogger(BeetlUtils.class);	
   private static Configuration configuration;	
   private static GroupTemplate stringGroupTemplate;	
   private static GroupTemplate resourceGroupTemplate;	
	
   public static String renderFromString(String tplString, Map data) {	
      try {	
         Template var10000 = getStringGroupTemplate().getTemplate(tplString);	
         var10000.binding(data);	
         return var10000.render();	
      } catch (Exception var3) {	
         throw ExceptionUtils.unchecked(var3);	
      }	
   }	
	
   public static synchronized GroupTemplate getStringGroupTemplate() {	
      if (stringGroupTemplate == null) {	
         m a = new m();	
         stringGroupTemplate = new GroupTemplate(a, getConfiguration());	
      }	
	
      return stringGroupTemplate;	
   }	
	
   public static synchronized GroupTemplate getResourceGroupTemplate() {	
      if (resourceGroupTemplate == null) {	
         try {	
            BeetlUtil.getWebRoot();	
         } catch (Exception var1) {	
            BeetlUtil.setWebroot(System.getProperty("uer.dir"));	
         }	
	
         D a = new D();	
         resourceGroupTemplate = new GroupTemplate(a, getConfiguration());	
      }	
	
      return resourceGroupTemplate;	
   }	
	
   public static String renderFromResource(String tplResourcePath, Map data) {	
      try {	
         Template var10000 = getResourceGroupTemplate().getTemplate(tplResourcePath);	
         var10000.binding(data);	
         return var10000.render();	
      } catch (Exception var3) {	
         var3.printStackTrace();	
         throw ExceptionUtils.unchecked(var3);	
      }	
   }	
	
   public static synchronized Configuration getConfiguration() {	
      if (configuration == null) {	
         try {	
            Set a = SetUtils.newLinkedHashSet();	
            Resource[] var2;	
            int var3 = (var2 = ResourceUtils.getResources("classpath*:/config/beetl-*.*")).length;	
	
            int var4;	
            int var10000;	
            for(var10000 = var4 = 0; var10000 < var3; var10000 = var4) {	
               Resource a = var2[var4];	
               StringBuilder var10001 = (new StringBuilder()).append("claspath:/config/");	
               String var10002 = a.getFilename();	
               ++var4;	
               a.add(var10001.append(var10002).toString());	
            }	
	
            a.add("classpath:/config/beetl.properties");	
            logger.debug("Beetl config file: {}", a);	
            String[] var10003 = new String[a.size()];	
            boolean var10005 = true;	
            PropertiesUtils a = new PropertiesUtils((String[])a.toArray(var10003));	
            configuration = new Configuration(a.getProperties());	
            Iterator var15 = a.getProperties().entrySet().iterator();	
	
            while(true) {	
               String a;	
               String a;	
               do {	
                  do {	
                     if (!var15.hasNext()) {	
                        return configuration;	
                     }	
	
                     Entry a;	
                     a = (String)(a = (Entry)var15.next()).getKey();	
                     a = (String)a.getValue();	
                  } while(!StringUtils.startsWithIgnoreCase(a, (new StringBuilder()).insert(0, Configuration.IMPORT_PACKAGE).append("_").toString()));	
               } while(!StringUtils.isNotBlank(a));	
	
               String[] var9;	
               int var10 = (var9 = a.split(";")).length;	
	
               int var11;	
               for(var10000 = var11 = 0; var10000 < var10; var10000 = var11) {	
                  String a = var9[var11];	
                  ++var11;	
                  configuration.getPkgList().add(a);	
               }	
            }	
         } catch (IOException var13) {	
            logger.error(var13.getMessage(), var13);	
         }	
      }	
	
      return configuration;	
   }	
}	
