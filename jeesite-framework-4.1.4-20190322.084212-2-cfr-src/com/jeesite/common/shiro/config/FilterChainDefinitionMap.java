/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.shiro.config;	
	
import com.jeesite.modules.msg.entity.content.BaseMsgContent;	
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
    private Ini.Section section;	
    private String filterChainDefinitions;	
	
    @Override	
    public Ini.Section getObject() throws BeansException {	
        if (this.section == null) {	
            Ini a = new Ini();	
            FilterChainDefinitionMap filterChainDefinitionMap = this;	
            a.load(this.defaultFilterChainDefinitions);	
            this.section = a.getSection("");	
            a.load(filterChainDefinitionMap.filterChainDefinitions);	
            filterChainDefinitionMap.section.putAll(a.getSection(""));	
            if (filterChainDefinitionMap.logger.isDebugEnabled()) {	
                this.logger.debug("Shiro的URL过滤定义：{}", (Object)this.section.entrySet());	
            }	
        }	
        return this.section;	
    }	
	
    public FilterChainDefinitionMap() {	
        FilterChainDefinitionMap filterChainDefinitionMap = this;	
        filterChainDefinitionMap.logger = LoggerFactory.getLogger(filterChainDefinitionMap.getClass());	
    }	
	
    public void setFilterChainDefinitions(String filterChainDefinitions) {	
        this.filterChainDefinitions = filterChainDefinitions;	
    }	
	
    public void setDefaultFilterChainDefinitions(String defaultFilterChainDefinitions) {	
        this.defaultFilterChainDefinitions = defaultFilterChainDefinitions;	
    }	
	
    @Override	
    public boolean isSingleton() {	
        return false;	
    }	
	
    @Override	
    public Class<?> getObjectType() {	
        return this.getClass();	
    }	
}	
	
