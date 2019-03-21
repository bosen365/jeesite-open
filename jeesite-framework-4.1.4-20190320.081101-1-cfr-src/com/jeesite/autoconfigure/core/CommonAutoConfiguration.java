/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.autoconfigure.core;	
	
import com.jeesite.common.i18n.I18nLocaleResolver;	
import com.jeesite.common.i18n.I18nMessageSource;	
import com.jeesite.common.io.PropertiesUtils;	
import com.jeesite.common.utils.SpringUtils;	
import com.jeesite.common.v.i;	
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
    @Order(value=-2147481648)	
    public SpringUtils springUtils() {	
        return new SpringUtils();	
    }	
	
    @Bean	
    @Lazy(value=false)	
    @DependsOn(value={"springUtils"})	
    @Order(value=-2147480648)	
    public i dbUpgrade() {	
        return new i();	
    }	
	
    @Bean	
    public I18nMessageSource i18nMessageSource() {	
        return new I18nMessageSource();	
    }	
	
    @Bean	
    public I18nLocaleResolver i18nLocaleResolver() {	
        return new I18nLocaleResolver();	
    }	
	
    @Bean	
    @Lazy(value=false)	
    @Order(value=Integer.MIN_VALUE)	
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {	
        PropertySourcesPlaceholderConfigurer a;	
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = a = new PropertySourcesPlaceholderConfigurer();	
        propertySourcesPlaceholderConfigurer.setProperties(PropertiesUtils.getInstance().getProperties());	
        propertySourcesPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders(true);	
        return propertySourcesPlaceholderConfigurer;	
    }	
}	
	
