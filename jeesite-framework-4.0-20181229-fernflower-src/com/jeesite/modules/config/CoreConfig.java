package com.jeesite.modules.config;	
	
import com.jeesite.common.i.C;	
import com.jeesite.common.io.PropertiesUtils;	
import com.jeesite.common.l.i.g;	
import com.jeesite.common.utils.SpringUtils;	
import com.jeesite.modules.file.utils.FileUploadUtils;	
import org.hibernate.validator.HibernateValidator;	
import org.springframework.boot.web.servlet.ErrorPage;	
import org.springframework.boot.web.servlet.ErrorPageRegistrar;	
import org.springframework.boot.web.servlet.ErrorPageRegistry;	
import org.springframework.context.annotation.Bean;	
import org.springframework.context.annotation.Configuration;	
import org.springframework.context.annotation.DependsOn;	
import org.springframework.context.annotation.Lazy;	
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;	
import org.springframework.core.annotation.Order;	
import org.springframework.http.HttpStatus;	
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;	
import org.springframework.web.servlet.LocaleResolver;	
	
@Configuration	
public class CoreConfig {	
   public static String ALLATORIxDEMO(String s) {	
      int var10000 = 2 << 3 ^ 3;	
      int var10001 = 4 << 4 ^ 3 << 2 ^ 3;	
      int var10002 = 4 << 3 ^ 3 ^ 5;	
      int var10003 = s.length();	
      char[] var10004 = new char[var10003];	
      boolean var10006 = true;	
      int var5 = var10003 - 1;	
      var10003 = var10002;	
      int var3;	
      var10002 = var3 = var5;	
      char[] var1 = var10004;	
      int var4 = var10003;	
      var10001 = var10000;	
      var10000 = var10002;	
	
      for(int var2 = var10001; var10000 >= 0; var10000 = var3) {	
         var10001 = var3;	
         char var6 = s.charAt(var3);	
         --var3;	
         var1[var10001] = (char)(var6 ^ var2);	
         if (var3 < 0) {	
            break;	
         }	
	
         var10002 = var3--;	
         var1[var10002] = (char)(s.charAt(var10002) ^ var4);	
      }	
	
      return new String(var1);	
   }	
	
   @Bean	
   @Lazy(false)	
   @DependsOn({"springUtils"})	
   @Order(-2147480648)	
   public com.jeesite.common.d.l dbUpgrade() {	
      return new com.jeesite.common.d.l();	
   }	
	
   @Bean	
   public com.jeesite.common.i.l i18nMessageSource() {	
      return new com.jeesite.common.i.l();	
   }	
	
   @Bean	
   public LocaleResolver i18nLocaleResolver() {	
      return new C();	
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
   public LocalValidatorFactoryBean beanValidator() {	
      LocalValidatorFactoryBean var10000 = new LocalValidatorFactoryBean();	
      var10000.setProviderClass(HibernateValidator.class);	
      return var10000;	
   }	
	
   @Bean	
   public ErrorPageRegistrar errorPageRegistrar() {	
      return new ErrorPageRegistrar() {	
         public void registerErrorPages(ErrorPageRegistry ax) {	
            ErrorPage var2 = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404");	
            ErrorPage var3 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500");	
            ErrorPage[] var10001 = new ErrorPage[2];	
            boolean var10003 = true;	
            var10001[0] = var2;	
            var10001[1] = var3;	
            ax.addErrorPages(var10001);	
         }	
      };	
   }	
}	
