package com.jeesite.autoconfigure.core;	
	
import com.jeesite.common.i18n.I18nLocaleResolver;	
import com.jeesite.common.i18n.I18nMessageSource;	
import com.jeesite.common.io.PropertiesUtils;	
import com.jeesite.common.utils.SpringUtils;	
import org.springframework.context.annotation.Bean;	
import org.springframework.context.annotation.ComponentScan;	
import org.springframework.context.annotation.Configuration;	
import org.springframework.context.annotation.DependsOn;	
import org.springframework.context.annotation.Lazy;	
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;	
import org.springframework.core.annotation.Order;	
	
@Configuration	
@ComponentScan({"com.jeesite.modules"})	
public class CommonAutoConfiguration {	
   @Bean	
   @Lazy(false)	
   @DependsOn({"springUtils"})	
   @Order(-2147480648)	
   public com.jeesite.common.e.E dbUpgrade() {	
      return new com.jeesite.common.e.E();	
   }	
	
   @Bean	
   @Lazy(false)	
   @Order(Integer.MIN_VALUE)	
   public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {	
      PropertySourcesPlaceholderConfigurer a = new PropertySourcesPlaceholderConfigurer();	
      a.setProperties(PropertiesUtils.getInstance().getProperties());	
      a.setIgnoreUnresolvablePlaceholders(true);	
      return a;	
   }	
	
   @Bean	
   @Lazy(false)	
   @Order(-2147481648)	
   public SpringUtils springUtils() {	
      return new SpringUtils();	
   }	
	
   @Bean	
   public I18nMessageSource i18nMessageSource() {	
      return new I18nMessageSource();	
   }	
	
   @Bean	
   public I18nLocaleResolver i18nLocaleResolver() {	
      return new I18nLocaleResolver();	
   }	
}	
