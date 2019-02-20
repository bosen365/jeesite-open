/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  javax.servlet.Filter	
 *  javax.servlet.MultipartConfigElement	
 *  org.hibernate.validator.HibernateValidator	
 *  org.springframework.boot.autoconfigure.AutoConfigureBefore	
 *  org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean	
 *  org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration	
 *  org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration	
 *  org.springframework.boot.autoconfigure.web.servlet.MultipartProperties	
 *  org.springframework.boot.context.properties.EnableConfigurationProperties	
 *  org.springframework.boot.web.server.ErrorPageRegistrar	
 *  org.springframework.boot.web.servlet.FilterRegistrationBean	
 *  org.springframework.boot.web.servlet.ServletListenerRegistrationBean	
 *  org.springframework.context.annotation.Bean	
 *  org.springframework.context.annotation.Configuration	
 *  org.springframework.context.annotation.Primary	
 *  org.springframework.core.annotation.Order	
 *  org.springframework.http.MediaType	
 *  org.springframework.http.converter.HttpMessageConverter	
 *  org.springframework.http.converter.StringHttpMessageConverter	
 *  org.springframework.validation.Validator	
 *  org.springframework.validation.beanvalidation.LocalValidatorFactoryBean	
 *  org.springframework.web.context.request.RequestContextListener	
 *  org.springframework.web.filter.CharacterEncodingFilter	
 *  org.springframework.web.servlet.View	
 *  org.springframework.web.servlet.ViewResolver	
 *  org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer	
 *  org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer	
 *  org.springframework.web.servlet.config.annotation.EnableWebMvc	
 *  org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration	
 *  org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry	
 *  org.springframework.web.servlet.config.annotation.ViewResolverRegistry	
 *  org.springframework.web.servlet.config.annotation.WebMvcConfigurer	
 */	
package com.jeesite.autoconfigure.core;	
	
import com.jeesite.autoconfigure.core.E;	
import com.jeesite.common.beetl.view.BeetlViewResolver;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.web.e.H;	
import com.jeesite.common.web.j.D;	
import com.jeesite.common.web.j.m;	
import com.jeesite.modules.file.entity.FileUploadParams;	
import com.jeesite.modules.sys.utils.ValidCodeUtils;	
import java.nio.charset.Charset;	
import java.nio.charset.StandardCharsets;	
import java.util.EventListener;	
import java.util.List;	
import javax.servlet.Filter;	
import javax.servlet.MultipartConfigElement;	
import org.hibernate.validator.HibernateValidator;	
import org.hyperic.sigar.DiskUsage;	
import org.springframework.boot.autoconfigure.AutoConfigureBefore;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;	
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;	
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;	
import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;	
import org.springframework.boot.context.properties.EnableConfigurationProperties;	
import org.springframework.boot.web.server.ErrorPageRegistrar;	
import org.springframework.boot.web.servlet.FilterRegistrationBean;	
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;	
import org.springframework.context.annotation.Bean;	
import org.springframework.context.annotation.Configuration;	
import org.springframework.context.annotation.Primary;	
import org.springframework.core.annotation.Order;	
import org.springframework.http.MediaType;	
import org.springframework.http.converter.HttpMessageConverter;	
import org.springframework.http.converter.StringHttpMessageConverter;	
import org.springframework.validation.Validator;	
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;	
import org.springframework.web.context.request.RequestContextListener;	
import org.springframework.web.filter.CharacterEncodingFilter;	
import org.springframework.web.servlet.View;	
import org.springframework.web.servlet.ViewResolver;	
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;	
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;	
import org.springframework.web.servlet.config.annotation.EnableWebMvc;	
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;	
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;	
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;	
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;	
	
@Configuration	
@AutoConfigureBefore(value={ValidationAutoConfiguration.class, MultipartAutoConfiguration.class})	
@EnableWebMvc	
@EnableConfigurationProperties(value={MultipartProperties.class})	
public class WebMvcAutoConfiguration	
implements WebMvcConfigurer {	
    private final MultipartProperties multipartProperties;	
	
    @Bean	
    @Order(value=1000)	
    @ConditionalOnMissingBean(name={"characterEncodingFilter"})	
    public FilterRegistrationBean<CharacterEncodingFilter> characterEncodingFilter() {	
        FilterRegistrationBean a2;	
        FilterRegistrationBean filterRegistrationBean = a2 = new FilterRegistrationBean();	
        FilterRegistrationBean filterRegistrationBean2 = a2;	
        FilterRegistrationBean filterRegistrationBean3 = a2;	
        filterRegistrationBean2.setFilter((Filter)new CharacterEncodingFilter());	
        filterRegistrationBean2.addInitParameter("encoding", "UTF-8");	
        filterRegistrationBean.addInitParameter("forceEncoding", "true");	
        filterRegistrationBean.addUrlPatterns(new String[]{"/*"});	
        return a2;	
    }	
	
    @Bean	
    @ConditionalOnMissingBean(name={"multipartConfigElement"})	
    public MultipartConfigElement multipartConfigElement() {	
        String a2 = String.valueOf(new FileUploadParams().initialize().getMaxFileSize());	
        WebMvcAutoConfiguration webMvcAutoConfiguration = this;	
        webMvcAutoConfiguration.multipartProperties.setMaxFileSize(a2);	
        webMvcAutoConfiguration.multipartProperties.setMaxRequestSize(a2);	
        return webMvcAutoConfiguration.multipartProperties.createMultipartConfig();	
    }	
	
    public void addResourceHandlers(ResourceHandlerRegistry registry) {	
        registry.addResourceHandler(new String[]{"/static/**"}).addResourceLocations(new String[]{"/static/", "classpath:/static/"}).setCachePeriod(Integer.valueOf(31536000));	
    }	
	
    @Bean	
    @Order(value=1000)	
    @ConditionalOnMissingBean(name={"requestContextListener"})	
    public ServletListenerRegistrationBean<RequestContextListener> requestContextListener() {	
        void a2;	
        ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();	
        void v0 = a2;	
        v0.setListener((EventListener)new RequestContextListener());	
        return v0;	
    }	
	
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {	
        configurer.enable();	
    }	
	
    @Bean	
    public ErrorPageRegistrar errorPageRegistrar() {	
        return new E(this);	
    }	
	
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {	
        configurer.favorParameter(false);	
        configurer.favorPathExtension(true);	
        configurer.ignoreAcceptHeader(true);	
        configurer.mediaType("xml", MediaType.APPLICATION_XML);	
        configurer.mediaType("json", MediaType.APPLICATION_JSON);	
    }	
	
    public Validator getValidator() {	
        return this.beanValidator();	
    }	
	
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {	
        void a2;	
        H a3;	
        StringHttpMessageConverter a4 = new StringHttpMessageConverter(StandardCharsets.UTF_8);	
        H h2 = a3 = new H();	
        h2.setPrettyPrint(false);	
        com.jeesite.common.web.e.m m2 = new com.jeesite.common.web.e.m();	
        void v1 = a2;	
        v1.setPrettyPrint(false);	
        converters.add((HttpMessageConverter<?>)v1);	
        converters.add((HttpMessageConverter<?>)h2);	
        converters.add((HttpMessageConverter<?>)a4);	
    }	
	
    public WebMvcAutoConfiguration(MultipartProperties multipartProperties) {	
        this.multipartProperties = multipartProperties;	
    }	
	
    public void configureViewResolvers(ViewResolverRegistry registry) {	
        void a2;	
        void a3;	
        void a4;	
        BeetlViewResolver beetlViewResolver = new BeetlViewResolver();	
        void v0 = a2;	
        a2.setPrefix("/themes/" + Global.getProperty("web.view.themeName") + "/");	
        v0.setSuffix(".html");	
        v0.setOrder(10000);	
        ViewResolverRegistry viewResolverRegistry = registry;	
        viewResolverRegistry.viewResolver((ViewResolver)a2);	
        BeetlViewResolver beetlViewResolver2 = new BeetlViewResolver();	
        void v2 = a4;	
        a4.setPrefix("/");	
        v2.setSuffix(".html");	
        v2.setOrder(20000);	
        registry.viewResolver((ViewResolver)v2);	
        D a5 = new D();	
        a5.setPrettyPrint(false);	
        m m2 = new m();	
        a3.setPrettyPrint(false);	
        viewResolverRegistry.enableContentNegotiation(new View[]{a5, a3});	
    }	
	
    @Bean	
    @Primary	
    public LocalValidatorFactoryBean beanValidator() {	
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();	
        localValidatorFactoryBean.setProviderClass(HibernateValidator.class);	
        return localValidatorFactoryBean;	
    }	
}	
	
