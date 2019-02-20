package com.jeesite.autoconfigure.core;	
	
import com.jeesite.common.beetl.view.BeetlViewResolver;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.web.e.H;	
import com.jeesite.common.web.e.m;	
import com.jeesite.common.web.j.D;	
import com.jeesite.modules.file.entity.FileUploadParams;	
import com.jeesite.modules.sys.utils.ValidCodeUtils;	
import java.nio.charset.StandardCharsets;	
import java.util.List;	
import javax.servlet.MultipartConfigElement;	
import org.hibernate.validator.HibernateValidator;	
import org.hyperic.jni.ArchLoaderException;	
import org.hyperic.sigar.DiskUsage;	
import org.hyperic.sigar.SysInfo;	
import org.springframework.boot.autoconfigure.AutoConfigureBefore;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;	
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;	
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;	
import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;	
import org.springframework.boot.context.properties.EnableConfigurationProperties;	
import org.springframework.boot.web.server.ErrorPage;	
import org.springframework.boot.web.server.ErrorPageRegistrar;	
import org.springframework.boot.web.server.ErrorPageRegistry;	
import org.springframework.boot.web.servlet.FilterRegistrationBean;	
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;	
import org.springframework.context.annotation.Bean;	
import org.springframework.context.annotation.Configuration;	
import org.springframework.context.annotation.Primary;	
import org.springframework.core.annotation.Order;	
import org.springframework.http.HttpStatus;	
import org.springframework.http.MediaType;	
import org.springframework.http.converter.StringHttpMessageConverter;	
import org.springframework.validation.Validator;	
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;	
import org.springframework.web.context.request.RequestContextListener;	
import org.springframework.web.filter.CharacterEncodingFilter;	
import org.springframework.web.servlet.View;	
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;	
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;	
import org.springframework.web.servlet.config.annotation.EnableWebMvc;	
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;	
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;	
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;	
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;	
	
@Configuration	
@AutoConfigureBefore({ValidationAutoConfiguration.class, MultipartAutoConfiguration.class})	
@EnableWebMvc	
@EnableConfigurationProperties({MultipartProperties.class})	
public class WebMvcAutoConfiguration implements WebMvcConfigurer {	
   private final MultipartProperties multipartProperties;	
	
   @Bean	
   @Order(1000)	
   @ConditionalOnMissingBean(	
      name = {"characterEncodingFilter"}	
   )	
   public FilterRegistrationBean characterEncodingFilter() {	
      FilterRegistrationBean a = new FilterRegistrationBean();	
      a.setFilter(new CharacterEncodingFilter());	
      a.addInitParameter("encoding", "UTF-8");	
      a.addInitParameter("forceEncoding", "true");	
      String[] var10001 = new String[1];	
      boolean var10003 = true;	
      var10001[0] = "/*";	
      a.addUrlPatterns(var10001);	
      return a;	
   }	
	
   @Bean	
   @ConditionalOnMissingBean(	
      name = {"multipartConfigElement"}	
   )	
   public MultipartConfigElement multipartConfigElement() {	
      String a = String.valueOf((new FileUploadParams()).initialize().getMaxFileSize());	
      this.multipartProperties.setMaxFileSize(a);	
      this.multipartProperties.setMaxRequestSize(a);	
      return this.multipartProperties.createMultipartConfig();	
   }	
	
   public void addResourceHandlers(ResourceHandlerRegistry registry) {	
      String[] var10001 = new String[1];	
      boolean var10003 = true;	
      var10001[0] = "/static/**";	
      ResourceHandlerRegistration var10000 = registry.addResourceHandler(var10001);	
      var10001 = new String[2];	
      var10003 = true;	
      var10001[0] = "/static/";	
      var10001[1] = "classpath:/static/";	
      var10000.addResourceLocations(var10001).setCachePeriod(31536000);	
   }	
	
   @Bean	
   @Order(1000)	
   @ConditionalOnMissingBean(	
      name = {"requestContextListener"}	
   )	
   public ServletListenerRegistrationBean requestContextListener() {	
      ServletListenerRegistrationBean a = new ServletListenerRegistrationBean();	
      a.setListener(new RequestContextListener());	
      return a;	
   }	
	
   public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {	
      configurer.enable();	
   }	
	
   @Bean	
   public ErrorPageRegistrar errorPageRegistrar() {	
      return new ErrorPageRegistrar() {	
         public void registerErrorPages(ErrorPageRegistry ax) {	
            ErrorPage var2 = new ErrorPage(HttpStatus.BAD_REQUEST, "/error/400");	
            ErrorPage var3 = new ErrorPage(HttpStatus.UNAUTHORIZED, "/error/403");	
            ErrorPage var4 = new ErrorPage(HttpStatus.FORBIDDEN, "/error/403");	
            ErrorPage var5 = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404");	
            ErrorPage var6 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500");	
            ErrorPage var7 = new ErrorPage(Throwable.class, "/error/500");	
            ErrorPage[] var10001 = new ErrorPage[6];	
            boolean var10003 = true;	
            var10001[0] = var2;	
            var10001[1] = var3;	
            var10001[2] = var4;	
            var10001[3] = var5;	
            var10001[4] = var6;	
            var10001[5] = var7;	
            ax.addErrorPages(var10001);	
         }	
      };	
   }	
	
   public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {	
      configurer.mediaType("json", MediaType.APPLICATION_JSON);	
      configurer.mediaType("xml", MediaType.APPLICATION_XML);	
      configurer.ignoreAcceptHeader(true);	
      configurer.favorPathExtension(true);	
      configurer.favorParameter(false);	
   }	
	
   public Validator getValidator() {	
      return this.beanValidator();	
   }	
	
   public void configureMessageConverters(List converters) {	
      StringHttpMessageConverter a = new StringHttpMessageConverter(StandardCharsets.UTF_8);	
      converters.add(a);	
      H a = new H();	
      a.setPrettyPrint(false);	
      converters.add(a);	
      m a = new m();	
      a.setPrettyPrint(false);	
      converters.add(a);	
   }	
	
   public WebMvcAutoConfiguration(MultipartProperties var1) {	
      this.multipartProperties = var1;	
   }	
	
   public void configureViewResolvers(ViewResolverRegistry registry) {	
      BeetlViewResolver a = new BeetlViewResolver();	
      a.setPrefix("/themes/" + Global.getProperty("web.view.themeName") + "/");	
      a.setSuffix(".html");	
      a.setOrder(10000);	
      registry.viewResolver(a);	
      BeetlViewResolver a = new BeetlViewResolver();	
      a.setPrefix("/");	
      a.setSuffix(".html");	
      a.setOrder(20000);	
      registry.viewResolver(a);	
      D a;	
      (a = new D()).setPrettyPrint(false);	
      com.jeesite.common.web.j.m a = new com.jeesite.common.web.j.m();	
      a.setPrettyPrint(false);	
      View[] var10001 = new View[2];	
      boolean var10003 = true;	
      var10001[0] = a;	
      var10001[1] = a;	
      registry.enableContentNegotiation(var10001);	
   }	
	
   @Bean	
   @Primary	
   public LocalValidatorFactoryBean beanValidator() {	
      LocalValidatorFactoryBean var10000 = new LocalValidatorFactoryBean();	
      var10000.setProviderClass(HibernateValidator.class);	
      return var10000;	
   }	
}	
