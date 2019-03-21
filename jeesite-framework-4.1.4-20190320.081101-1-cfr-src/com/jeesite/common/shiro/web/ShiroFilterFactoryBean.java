/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.common.shiro.web;	
	
import com.jeesite.common.shiro.web.c;	
import org.apache.shiro.mgt.SecurityManager;	
import org.apache.shiro.web.filter.mgt.FilterChainManager;	
import org.apache.shiro.web.filter.mgt.FilterChainResolver;	
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;	
import org.apache.shiro.web.mgt.WebSecurityManager;	
import org.apache.shiro.web.servlet.AbstractShiroFilter;	
import org.hyperic.sigar.Tcp;	
import org.hyperic.sigar.pager.PageFetcher;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
import org.springframework.beans.factory.BeanInitializationException;	
	
public class ShiroFilterFactoryBean	
extends org.apache.shiro.spring.web.ShiroFilterFactoryBean {	
    private static final transient Logger log = LoggerFactory.getLogger(org.apache.shiro.spring.web.ShiroFilterFactoryBean.class);	
	
    public AbstractShiroFilter getInstance() throws Exception {	
        return (AbstractShiroFilter)super.getObject();	
    }	
	
    @Override	
    protected AbstractShiroFilter createInstance() throws Exception {	
        log.debug("Creating Shiro Fiter instance.");	
        SecurityManager a = this.getSecurityManager();	
        if (a == null) {	
            String a2 = "SecurityManager property must be set.";	
            throw new BeanInitializationException(a2);	
        }	
        if (!(a instanceof WebSecurityManager)) {	
            String a3 = "The security manager does not impement the WebSecurityManager interface.";	
            throw new BeanInitializationException(a3);	
        }	
        FilterChainManager a4 = this.createFilterChainManager();	
        PathMatchingFilterChainResolver a5 = new PathMatchingFilterChainResolver();	
        a5.setFilterChainManager(a4);	
        return new c((WebSecurityManager)a, a5);	
    }	
	
    @Override	
    public Object getObject() throws Exception {	
        return this;	
    }	
	
    @Override	
    public Class<?> getObjectType() {	
        return ShiroFilterFactoryBean.class;	
    }	
}	
	
