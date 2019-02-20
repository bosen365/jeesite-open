/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.io.PropertiesUtils	
 *  org.springframework.context.annotation.Bean	
 *  org.springframework.context.annotation.ComponentScan	
 *  org.springframework.context.annotation.Configuration	
 *  org.springframework.context.annotation.DependsOn	
 *  org.springframework.context.annotation.Lazy	
 *  org.springframework.context.support.PropertySourcesPlaceholderConfigurer	
 *  org.springframework.core.annotation.Order	
 */	
package com.jeesite.autoconfigure.core;	
	
import com.jeesite.common.e.E;	
import com.jeesite.common.i18n.I18nLocaleResolver;	
import com.jeesite.common.i18n.I18nMessageSource;	
import com.jeesite.common.io.PropertiesUtils;	
import com.jeesite.common.utils.SpringUtils;	
import java.util.Properties;	
import org.springframework.context.annotation.Bean;	
import org.springframework.context.annotation.ComponentScan;	
import org.springframework.context.annotation.Configuration;	
import org.springframework.context.annotation.DependsOn;	
import org.springframework.context.annotation.Lazy;	
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;	
import org.springframework.core.annotation.Order;	
	
@Configuration	
@ComponentScan(value={"com.jeesite.modules"})	
public class CommonAutoConfiguration {	
    @Bean	
    @Lazy(value=false)	
    @DependsOn(value={"springUtils"})	
    @Order(value=-2147480648)	
    public E dbUpgrade() {	
        return new E();	
    }	
	
    @Bean	
    @Lazy(value=false)	
    @Order(value=Integer.MIN_VALUE)	
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {	
        PropertySourcesPlaceholderConfigurer a2;	
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = a2 = new PropertySourcesPlaceholderConfigurer();	
        propertySourcesPlaceholderConfigurer.setProperties(PropertiesUtils.getInstance().getProperties());	
        propertySourcesPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders(true);	
        return propertySourcesPlaceholderConfigurer;	
    }	
	
    @Bean	
    @Lazy(value=false)	
    @Order(value=-2147481648)	
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
	
