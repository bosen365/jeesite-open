package com.jeesite.common.shiro.config;	
	
import com.jeesite.modules.sys.utils.ConfigUtils;	
import org.apache.shiro.config.Ini;	
import org.apache.shiro.config.Ini.Section;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
import org.springframework.beans.BeansException;	
import org.springframework.beans.factory.FactoryBean;	
	
public class FilterChainDefinitionMap implements FactoryBean {	
   private String defaultFilterChainDefinitions;	
   protected Logger logger = LoggerFactory.getLogger(this.getClass());	
   private String filterChainDefinitions;	
   private Section section;	
	
   public void setFilterChainDefinitions(String filterChainDefinitions) {	
      this.filterChainDefinitions = filterChainDefinitions;	
   }	
	
   public Class getObjectType() {	
      return this.getClass();	
   }	
	
   public Section getObject() throws BeansException {	
      if (this.section == null) {	
         Ini a;	
         Ini var10002 = a = new Ini();	
         a.load(this.defaultFilterChainDefinitions);	
         this.section = a.getSection("");	
         var10002.load(this.filterChainDefinitions);	
         this.section.putAll(a.getSection(""));	
         if (this.logger.isDebugEnabled()) {	
            this.logger.debug("Shiro的URL过滤定义：{}", this.section.entrySet());	
         }	
      }	
	
      return this.section;	
   }	
	
   public void setDefaultFilterChainDefinitions(String defaultFilterChainDefinitions) {	
      this.defaultFilterChainDefinitions = defaultFilterChainDefinitions;	
   }	
	
   public boolean isSingleton() {	
      return false;	
   }	
}	
