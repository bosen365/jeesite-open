/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  org.apache.shiro.config.Ini	
 *  org.apache.shiro.config.Ini$Section	
 *  org.slf4j.Logger	
 *  org.slf4j.LoggerFactory	
 *  org.springframework.beans.BeansException	
 *  org.springframework.beans.factory.FactoryBean	
 */	
package com.jeesite.common.shiro.config;	
	
import com.jeesite.modules.sys.utils.ConfigUtils;	
import java.util.Map;	
import java.util.Set;	
import org.apache.shiro.config.Ini;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
import org.springframework.beans.BeansException;	
import org.springframework.beans.factory.FactoryBean;	
	
public class FilterChainDefinitionMap	
implements FactoryBean<Ini.Section> {	
    private String defaultFilterChainDefinitions;	
    protected Logger logger;	
    private String filterChainDefinitions;	
    private Ini.Section section;	
	
    public void setFilterChainDefinitions(String filterChainDefinitions) {	
        this.filterChainDefinitions = filterChainDefinitions;	
    }	
	
    public Class<?> getObjectType() {	
        return this.getClass();	
    }	
	
    public Ini.Section getObject() throws BeansException {	
        if (this.section == null) {	
            Ini a2 = new Ini();	
            FilterChainDefinitionMap filterChainDefinitionMap = this;	
            a2.load(this.defaultFilterChainDefinitions);	
            this.section = a2.getSection("");	
            a2.load(filterChainDefinitionMap.filterChainDefinitions);	
            filterChainDefinitionMap.section.putAll((Map)a2.getSection(""));	
            if (filterChainDefinitionMap.logger.isDebugEnabled()) {	
                this.logger.debug("Shiro的URL过滤定义：{}", (Object)this.section.entrySet());	
            }	
        }	
        return this.section;	
    }	
	
    public void setDefaultFilterChainDefinitions(String defaultFilterChainDefinitions) {	
        this.defaultFilterChainDefinitions = defaultFilterChainDefinitions;	
    }	
	
    public FilterChainDefinitionMap() {	
        FilterChainDefinitionMap filterChainDefinitionMap = this;	
        filterChainDefinitionMap.logger = LoggerFactory.getLogger(filterChainDefinitionMap.getClass());	
    }	
	
    public boolean isSingleton() {	
        return false;	
    }	
}	
	
