/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.collect.MapUtils	
 *  com.jeesite.common.lang.DateUtils	
 *  javax.servlet.ServletContext	
 *  org.apache.commons.logging.Log	
 *  org.apache.commons.logging.LogFactory	
 *  org.beetl.core.Configuration	
 *  org.beetl.core.GroupTemplate	
 *  org.beetl.ext.spring.BeetlSpringView	
 *  org.springframework.beans.factory.BeanNameAware	
 *  org.springframework.beans.factory.InitializingBean	
 *  org.springframework.beans.factory.NoSuchBeanDefinitionException	
 *  org.springframework.beans.factory.NoUniqueBeanDefinitionException	
 *  org.springframework.web.servlet.view.AbstractTemplateViewResolver	
 *  org.springframework.web.servlet.view.AbstractUrlBasedView	
 */	
package com.jeesite.common.beetl.view;	
	
import com.jeesite.common.beetl.BeetlUtils;	
import com.jeesite.common.beetl.view.BeetlView;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.j.E;	
import com.jeesite.common.lang.DateUtils;	
import com.jeesite.common.mybatis.mapper.provider.InsertSqlProvider;	
import java.util.Date;	
import java.util.Map;	
import javax.servlet.ServletContext;	
import org.apache.commons.logging.Log;	
import org.apache.commons.logging.LogFactory;	
import org.beetl.core.Configuration;	
import org.beetl.core.GroupTemplate;	
import org.beetl.ext.spring.BeetlSpringView;	
import org.springframework.beans.factory.BeanNameAware;	
import org.springframework.beans.factory.InitializingBean;	
import org.springframework.beans.factory.NoSuchBeanDefinitionException;	
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;	
import org.springframework.web.servlet.view.AbstractTemplateViewResolver;	
import org.springframework.web.servlet.view.AbstractUrlBasedView;	
	
public class BeetlViewResolver	
extends AbstractTemplateViewResolver	
implements InitializingBean,	
BeanNameAware {	
    protected final Log logger;	
    private String beanName;	
    private GroupTemplate groupTemplate;	
	
    public void setPrefix(String prefix) {	
        super.setPrefix(prefix);	
    }	
	
    protected AbstractUrlBasedView buildView(String viewName) throws Exception {	
        BeetlSpringView a2 = (BeetlSpringView)super.buildView(viewName);	
        BeetlViewResolver beetlViewResolver = this;	
        a2.setGroupTemplate(beetlViewResolver.groupTemplate);	
        String a3 = beetlViewResolver.getSuffix();	
        if (a3 != null && a3.length() != 0 && viewName.contains("#")) {	
            String[] a4 = viewName.split("#");	
            if (a4.length > 2) {	
                throw new Exception(new StringBuilder().insert(0, "视图名称有误：").append(viewName).toString());	
            }	
            a2.setUrl(new StringBuilder().insert(0, this.getPrefix()).append(a4[0]).append(this.getSuffix()).append("#").append(a4[1]).toString());	
        }	
        return a2;	
    }	
	
    public BeetlViewResolver() {	
        BeetlViewResolver beetlViewResolver = this;	
        beetlViewResolver.logger = LogFactory.getLog(((Object)((Object)beetlViewResolver)).getClass());	
        beetlViewResolver.setViewClass(BeetlView.class);	
    }	
	
    public void setGroupTemplate(GroupTemplate groupTemplate) {	
        this.groupTemplate = groupTemplate;	
    }	
	
    public void setBeanName(String beanName) {	
        this.beanName = beanName;	
    }	
	
    public void afterPropertiesSet() throws NoSuchBeanDefinitionException, NoUniqueBeanDefinitionException, SecurityException, NoSuchFieldException {	
        this.groupTemplate = BeetlUtils.getResourceGroupTemplate();	
        Map a2 = this.groupTemplate.getSharedVars();	
        if (a2 == null) {	
            a2 = MapUtils.newHashMap();	
        }	
        BeetlViewResolver beetlViewResolver = this;	
        String a3 = beetlViewResolver.getServletContext().getContextPath();	
        String a4 = DateUtils.formatDate((Date)DateUtils.getServerStartDate(), (String)"MMddHHmm");	
        this.groupTemplate.setSharedVars(a2);	
        a2.put("ctxStatic", new StringBuilder().insert(0, a3).append("/static").toString());	
        a2.put("ctxFront", new StringBuilder().insert(0, a3).append(Global.getFrontPath()).toString());	
        a2.put("ctxAdmin", new StringBuilder().insert(0, a3).append(Global.getAdminPath()).toString());	
        a2.put("ctxPath", a3);	
        a2.put("_version", new StringBuilder().insert(0, Global.getProperty("productVersion")).append("-").append(a4).toString());	
        if (beetlViewResolver.getContentType() == null) {	
            String a5 = this.groupTemplate.getConf().getCharset();	
            this.setContentType("text/htm;charset=" + a5);	
        }	
    }	
	
    protected Class<BeetlSpringView> requiredViewClass() {	
        return BeetlSpringView.class;	
    }	
	
    public String getBeanName() {	
        return this.beanName;	
    }	
}	
	
