/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.autoconfigure.core;	
	
import com.jeesite.autoconfigure.core.m;	
import com.jeesite.common.beetl.view.BeetlViewResolver;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.j2cache.cache.support.redis.ConfigureNotifyKeyspaceEventsAction;	
import com.jeesite.common.validator.ValidatorUtils;	
import com.jeesite.common.web.e.e;	
import com.jeesite.common.web.l.I;	
import com.jeesite.common.web.l.l;	
import com.jeesite.modules.file.entity.FileUploadParams;	
import java.nio.charset.Charset;	
import java.nio.charset.StandardCharsets;	
import java.util.List;	
import javax.servlet.MultipartConfigElement;	
import org.hibernate.validator.HibernateValidator;	
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
    public void addResourceHandlers(ResourceHandlerRegistry registry) {	
        String[] arrstring = new String[1];	
        arrstring[0] = "/static/**";	
        String[] arrstring2 = new String[2];	
        arrstring2[0] = "/static/";	
        arrstring2[1] = "classpath:/static/";	
        registry.addResourceHandler(arrstring).addResourceLocations(arrstring2).setCachePeriod(31536000);	
    }	
	
    @Override	
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {	
        configurer.enable();	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
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
        void v2 = a;	
        a.setPrefix("/");	
        v2.setSuffix(".html");	
        v2.setOrder(20000);	
        registry.viewResolver((ViewResolver)v2);	
        I a4 = new I();	
        a4.setPrettyPrint(false);	
        l l2 = new l();	
        a2.setPrettyPrint(false);	
        View[] arrview = new View[2];	
        arrview[0] = a4;	
        arrview[1] = a2;	
        viewResolverRegistry.enableContentNegotiation(arrview);	
    }	
	
    @Override	
    public Validator getValidator() {	
        return this.beanValidator();	
    }	
	
    public WebMvcAutoConfiguration(MultipartProperties multipartProperties) {	
        this.multipartProperties = multipartProperties;	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
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
	
    @Override	
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {	
        configurer.mediaType("json", MediaType.APPLICATION_JSON);	
        configurer.mediaType("xml", MediaType.APPLICATION_XML);	
        configurer.ignoreAcceptHeader(true);	
        configurer.favorPathExtension(true);	
        configurer.favorParameter(false);	
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
        String[] arrstring = new String[1];	
        arrstring[0] = "/*";	
        filterRegistrationBean.addUrlPatterns(arrstring);	
        return a;	
    }	
	
    @Bean	
    @Primary	
    public LocalValidatorFactoryBean beanValidator() {	
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();	
        localValidatorFactoryBean.setProviderClass(HibernateValidator.class);	
        return localValidatorFactoryBean;	
    }	
	
    @Bean	
    public ErrorPageRegistrar errorPageRegistrar() {	
        return new m(this);	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    @Override	
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {	
        void a;	
        e a2;	
        StringHttpMessageConverter a3 = new StringHttpMessageConverter(StandardCharsets.UTF_8);	
        converters.add(a3);	
        e e2 = a2 = new e();	
        e2.setPrettyPrint(false);	
        converters.add(e2);	
        com.jeesite.common.web.e.l l2 = new com.jeesite.common.web.e.l();	
        void v1 = a;	
        v1.setPrettyPrint(false);	
        converters.add((HttpMessageConverter<?>)v1);	
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
}	
	
