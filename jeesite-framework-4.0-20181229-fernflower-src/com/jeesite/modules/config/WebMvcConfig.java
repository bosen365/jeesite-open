package com.jeesite.modules.config;	
	
import com.jeesite.common.beetl.view.BeetlViewResolver;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.l.i.D;	
import com.jeesite.common.mybatis.mapper.query.QueryWhere;	
import com.jeesite.common.web.converter.JsonHttpMessageConverter;	
import com.jeesite.common.web.converter.XmlHttpMessageConverter;	
import com.jeesite.common.web.view.JsonView;	
import com.jeesite.common.web.view.XmlView;	
import com.jeesite.modules.file.entity.FileUploadParms;	
import java.nio.charset.StandardCharsets;	
import java.util.List;	
import javax.servlet.MultipartConfigElement;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.boot.autoconfigure.web.MultipartProperties;	
import org.springframework.boot.context.properties.EnableConfigurationProperties;	
import org.springframework.context.annotation.Bean;	
import org.springframework.context.annotation.Configuration;	
import org.springframework.http.MediaType;	
import org.springframework.http.converter.StringHttpMessageConverter;	
import org.springframework.validation.Validator;	
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;	
import org.springframework.web.servlet.View;	
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;	
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;	
import org.springframework.web.servlet.config.annotation.EnableWebMvc;	
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;	
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;	
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;	
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;	
	
@Configuration	
@EnableWebMvc	
@EnableConfigurationProperties({MultipartProperties.class})	
public class WebMvcConfig extends WebMvcConfigurerAdapter {	
   private final MultipartProperties multipartProperties;	
   @Autowired	
   private LocalValidatorFactoryBean beanValidator;	
	
   public void configureMessageConverters(List converters) {	
      StringHttpMessageConverter a = new StringHttpMessageConverter(StandardCharsets.UTF_8);	
      converters.add(a);	
      JsonHttpMessageConverter a = new JsonHttpMessageConverter();	
      a.setPrettyPrint(false);	
      converters.add(a);	
      XmlHttpMessageConverter a = new XmlHttpMessageConverter();	
      a.setPrettyPrint(false);	
      converters.add(a);	
   }	
	
   public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {	
      configurer.enable();	
   }	
	
   public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {	
      configurer.mediaType("json", MediaType.APPLICATION_JSON);	
      configurer.mediaType("xml", MediaType.APPLICATION_XML);	
      configurer.ignoreAcceptHeader(true);	
      configurer.favorPathExtension(true);	
      configurer.favorParameter(false);	
   }	
	
   @Bean	
   public MultipartConfigElement multipartConfigElement() {	
      String a = String.valueOf((new FileUploadParms()).getMaxFileSize());	
      this.multipartProperties.setMaxFileSize(a);	
      return this.multipartProperties.createMultipartConfig();	
   }	
	
   public Validator getValidator() {	
      return this.beanValidator;	
   }	
	
   public WebMvcConfig(MultipartProperties var1) {	
      this.multipartProperties = var1;	
   }	
	
   public void configureViewResolvers(ViewResolverRegistry registry) {	
      BeetlViewResolver a = new BeetlViewResolver();	
      a.setPrefix("/hemes/" + Global.getProperty("web.view.themeName") + "/");	
      a.setSuffix(".html");	
      a.setOrder(10000);	
      registry.viewResolver(a);	
      BeetlViewResolver a = new BeetlViewResolver();	
      a.setPrefix("/");	
      a.setSuffix(".html");	
      a.setOrder(20000);	
      registry.viewResolver(a);	
      JsonView a;	
      (a = new JsonView()).setPrettyPrint(false);	
      XmlView a = new XmlView();	
      a.setPrettyPrint(false);	
      View[] var10001 = new View[2];	
      boolean var10003 = true;	
      var10001[0] = a;	
      var10001[1] = a;	
      registry.enableContentNegotiation(var10001);	
   }	
	
   public void addResourceHandlers(ResourceHandlerRegistry registry) {	
      String[] var10001 = new String[1];	
      boolean var10003 = true;	
      var10001[0] = "/static/**";	
      ResourceHandlerRegistration var10000 = registry.addResourceHandler(var10001);	
      var10001 = new String[2];	
      var10003 = true;	
      var10001[0] = "/static/";	
      var10001[1] = "classpah:/static/";	
      var10000.addResourceLocations(var10001).setCachePeriod(31536000);	
   }	
}	
