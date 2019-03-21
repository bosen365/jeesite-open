/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.autoconfigure.core;	
	
import com.jeesite.autoconfigure.core.TransactionAutoConfiguration;	
import com.jeesite.autoconfigure.core.i;	
import com.jeesite.common.beetl.view.BeetlViewResolver;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.web.d.D;	
import com.jeesite.common.web.d.L;	
import com.jeesite.common.web.v.l;	
import com.jeesite.modules.file.entity.FileUploadParams;	
import java.nio.charset.Charset;	
import java.nio.charset.StandardCharsets;	
import java.util.List;	
import javax.servlet.MultipartConfigElement;	
import org.hibernate.validator.HibernateValidator;	
import org.hyperic.sigar.pager.PageFetcher;	
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
	
    @Override	
    public void configureViewResolvers(ViewResolverRegistry registry) {	
        void a;	
        void a2;	
        void a3;	
        BeetlViewResolver beetlViewResolver = new BeetlViewResolver();	
        void v0 = a3;	
        a3.setPrefix("/themes/" + Global.getProperty("web.view.themeName") + "/");	
        v0.setSuffix(".html");	
        v0.setOrder(10000);	
        ViewResolverRegistry viewResolverRegistry = registry;	
        viewResolverRegistry.viewResolver((ViewResolver)a3);	
        BeetlViewResolver beetlViewResolver2 = new BeetlViewResolver();	
        void v2 = a2;	
        a2.setPrefix("/");	
        v2.setSuffix(".html");	
        v2.setOrder(20000);	
        registry.viewResolver((ViewResolver)v2);	
        D a4 = new D();	
        a4.setPrettyPrint(false);	
        L l2 = new L();	
        a.setPrettyPrint(false);	
        viewResolverRegistry.enableContentNegotiation(new View[]{a4, a});	
    }	
	
    @Override	
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {	
        void a;	
        l a2;	
        StringHttpMessageConverter a3 = new StringHttpMessageConverter(StandardCharsets.UTF_8);	
        l l2 = a2 = new l();	
        l2.setPrettyPrint(false);	
        com.jeesite.common.web.v.L l3 = new com.jeesite.common.web.v.L();	
        void v1 = a;	
        v1.setPrettyPrint(false);	
        converters.add((HttpMessageConverter<?>)v1);	
        converters.add(l2);	
        converters.add(a3);	
    }	
	
    @Bean	
    public ErrorPageRegistrar errorPageRegistrar() {	
        return new i(this);	
    }	
	
    @Override	
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {	
        configurer.enable();	
    }	
	
    @Bean	
    @ConditionalOnMissingBean(name={"multipartConfigElement"})	
    public MultipartConfigElement multipartConfigElement() {	
        String a = String.valueOf(new FileUploadParams().initialize().getMaxFileSize());	
        WebMvcAutoConfiguration webMvcAutoConfiguration = this;	
        webMvcAutoConfiguration.multipartProperties.setMaxFileSize(a);	
        webMvcAutoConfiguration.multipartProperties.setMaxRequestSize(a);	
        return webMvcAutoConfiguration.multipartProperties.createMultipartConfig();	
    }	
	
    @Bean	
    @Primary	
    public LocalValidatorFactoryBean beanValidator() {	
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();	
        localValidatorFactoryBean.setProviderClass(HibernateValidator.class);	
        return localValidatorFactoryBean;	
    }	
	
    @Override	
    public void addResourceHandlers(ResourceHandlerRegistry registry) {	
        registry.addResourceHandler("/static/**").addResourceLocations("/static/", "classpath:/static/").setCachePeriod(31536000);	
    }	
	
    @Override	
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {	
        configurer.favorParameter(false);	
        configurer.favorPathExtension(true);	
        configurer.ignoreAcceptHeader(true);	
        configurer.mediaType("xml", MediaType.APPLICATION_XML);	
        configurer.mediaType("json", MediaType.APPLICATION_JSON);	
    }	
	
    @Override	
    public Validator getValidator() {	
        return this.beanValidator();	
    }	
	
    @Bean	
    @Order(value=1000)	
    @ConditionalOnMissingBean(name={"characterEncodingFilter"})	
    public FilterRegistrationBean<CharacterEncodingFilter> characterEncodingFilter() {	
        FilterRegistrationBean<CharacterEncodingFilter> a;	
        FilterRegistrationBean<CharacterEncodingFilter> filterRegistrationBean = a = new FilterRegistrationBean<CharacterEncodingFilter>();	
        FilterRegistrationBean<CharacterEncodingFilter> filterRegistrationBean2 = a;	
        FilterRegistrationBean<CharacterEncodingFilter> filterRegistrationBean3 = a;	
        filterRegistrationBean2.setFilter(new CharacterEncodingFilter());	
        filterRegistrationBean2.addInitParameter("encoding", "UTF-8");	
        filterRegistrationBean.addInitParameter("forceEncoding", "true");	
        filterRegistrationBean.addUrlPatterns("/*");	
        return a;	
    }	
	
    @Bean	
    @Order(value=1000)	
    @ConditionalOnMissingBean(name={"requestContextListener"})	
    public ServletListenerRegistrationBean<RequestContextListener> requestContextListener() {	
        void a;	
        ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();	
        void v0 = a;	
        v0.setListener(new RequestContextListener());	
        return v0;	
    }	
	
    public WebMvcAutoConfiguration(MultipartProperties multipartProperties) {	
        this.multipartProperties = multipartProperties;	
    }	
}	
	
