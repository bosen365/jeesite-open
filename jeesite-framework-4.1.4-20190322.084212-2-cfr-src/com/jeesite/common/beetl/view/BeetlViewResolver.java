/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.beetl.view;	
	
import com.jeesite.common.beetl.BeetlUtils;	
import com.jeesite.common.beetl.view.BeetlView;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.lang.DateUtils;	
import com.jeesite.modules.sys.utils.ModuleUtils;	
import java.util.Map;	
import javax.servlet.ServletContext;	
import org.apache.commons.logging.Log;	
import org.apache.commons.logging.LogFactory;	
import org.beetl.core.Configuration;	
import org.beetl.core.GroupTemplate;	
import org.beetl.ext.spring.BeetlSpringView;	
import org.hyperic.sigar.NfsServerV2;	
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
    private String beanName;	
    private GroupTemplate groupTemplate;	
    protected final Log logger;	
	
    @Override	
    public void setBeanName(String beanName) {	
        this.beanName = beanName;	
    }	
	
    public BeetlViewResolver() {	
        BeetlViewResolver beetlViewResolver = this;	
        beetlViewResolver.logger = LogFactory.getLog(beetlViewResolver.getClass());	
        beetlViewResolver.setViewClass(BeetlView.class);	
    }	
	
    public void setGroupTemplate(GroupTemplate groupTemplate) {	
        this.groupTemplate = groupTemplate;	
    }	
	
    protected Class<BeetlSpringView> requiredViewClass() {	
        return BeetlSpringView.class;	
    }	
	
    @Override	
    public void setPrefix(String prefix) {	
        super.setPrefix(prefix);	
    }	
	
    @Override	
    public void afterPropertiesSet() throws NoSuchBeanDefinitionException, NoUniqueBeanDefinitionException, SecurityException, NoSuchFieldException {	
        this.groupTemplate = BeetlUtils.getResourceGroupTemplate();	
        Map<String, Object> a = this.groupTemplate.getSharedVars();	
        if (a == null) {	
            a = MapUtils.newHashMap();	
        }	
        BeetlViewResolver beetlViewResolver = this;	
        String a2 = beetlViewResolver.getServletContext().getContextPath();	
        String a3 = DateUtils.formatDate(DateUtils.getServerStartDate(), "MMddHHmm");	
        a.put("_version", new StringBuilder().insert(0, Global.getProperty("productVersion")).append("-").append(a3).toString());	
        a.put("ctxPath", a2);	
        a.put("ctxAdmin", new StringBuilder().insert(0, a2).append(Global.getAdminPath()).toString());	
        a.put("ctxFront", new StringBuilder().insert(0, a2).append(Global.getFrontPath()).toString());	
        a.put("ctxStatic", new StringBuilder().insert(0, a2).append("/static").toString());	
        this.groupTemplate.setSharedVars(a);	
        if (beetlViewResolver.getContentType() == null) {	
            String a4 = this.groupTemplate.getConf().getCharset();	
            this.setContentType("text/html;charset=" + a4);	
        }	
    }	
	
    @Override	
    protected AbstractUrlBasedView buildView(String viewName) throws Exception {	
        BeetlSpringView a = (BeetlSpringView)super.buildView(viewName);	
        BeetlViewResolver beetlViewResolver = this;	
        a.setGroupTemplate(beetlViewResolver.groupTemplate);	
        String a2 = beetlViewResolver.getSuffix();	
        if (a2 != null && a2.length() != 0 && viewName.contains("#")) {	
            String[] a3 = viewName.split("#");	
            if (a3.length > 2) {	
                throw new Exception(new StringBuilder().insert(0, "视图名称有误：").append(viewName).toString());	
            }	
            a.setUrl(new StringBuilder().insert(0, this.getPrefix()).append(a3[0]).append(this.getSuffix()).append("#").append(a3[1]).toString());	
        }	
        return a;	
    }	
	
    public String getBeanName() {	
        return this.beanName;	
    }	
}	
	
