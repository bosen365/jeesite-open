package com.jeesite.common.beetl.view;	
	
import com.jeesite.common.beetl.BeetlUtils;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.j.E;	
import com.jeesite.common.lang.DateUtils;	
import com.jeesite.common.mybatis.mapper.provider.InsertSqlProvider;	
import java.util.Map;	
import org.apache.commons.logging.Log;	
import org.apache.commons.logging.LogFactory;	
import org.beetl.core.GroupTemplate;	
import org.beetl.ext.spring.BeetlSpringView;	
import org.springframework.beans.factory.BeanNameAware;	
import org.springframework.beans.factory.InitializingBean;	
import org.springframework.beans.factory.NoSuchBeanDefinitionException;	
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;	
import org.springframework.web.servlet.view.AbstractTemplateViewResolver;	
import org.springframework.web.servlet.view.AbstractUrlBasedView;	
	
public class BeetlViewResolver extends AbstractTemplateViewResolver implements InitializingBean, BeanNameAware {	
   protected final Log logger = LogFactory.getLog(this.getClass());	
   private String beanName;	
   private GroupTemplate groupTemplate;	
	
   public void setPrefix(String prefix) {	
      super.setPrefix(prefix);	
   }	
	
   protected AbstractUrlBasedView buildView(String viewName) throws Exception {	
      BeetlSpringView a;	
      (a = (BeetlSpringView)super.buildView(viewName)).setGroupTemplate(this.groupTemplate);	
      String a;	
      if ((a = this.getSuffix()) != null && a.length() != 0 && viewName.contains("#")) {	
         String[] a;	
         if ((a = viewName.split("#")).length > 2) {	
            throw new Exception((new StringBuilder()).insert(0, "视图名称有误：").append(viewName).toString());	
         }	
	
         a.setUrl((new StringBuilder()).insert(0, this.getPrefix()).append(a[0]).append(this.getSuffix()).append("#").append(a[1]).toString());	
      }	
	
      return a;	
   }	
	
   public BeetlViewResolver() {	
      this.setViewClass(BeetlView.class);	
   }	
	
   public void setGroupTemplate(GroupTemplate groupTemplate) {	
      this.groupTemplate = groupTemplate;	
   }	
	
   public void setBeanName(String beanName) {	
      this.beanName = beanName;	
   }	
	
   public void afterPropertiesSet() throws NoSuchBeanDefinitionException, NoUniqueBeanDefinitionException, SecurityException, NoSuchFieldException {	
      this.groupTemplate = BeetlUtils.getResourceGroupTemplate();	
      Object a;	
      if ((a = this.groupTemplate.getSharedVars()) == null) {	
         a = MapUtils.newHashMap();	
      }	
	
      String a = this.getServletContext().getContextPath();	
      String a = DateUtils.formatDate(DateUtils.getServerStartDate(), "MMddHHmm");	
      ((Map)a).put("_version", (new StringBuilder()).insert(0, Global.getProperty("productVersion")).append("-").append(a).toString());	
      ((Map)a).put("ctxPath", a);	
      ((Map)a).put("ctxAdmin", (new StringBuilder()).insert(0, a).append(Global.getAdminPath()).toString());	
      ((Map)a).put("ctxFront", (new StringBuilder()).insert(0, a).append(Global.getFrontPath()).toString());	
      ((Map)a).put("ctxStatic", (new StringBuilder()).insert(0, a).append("/static").toString());	
      this.groupTemplate.setSharedVars((Map)a);	
      if (this.getContentType() == null) {	
         String a = this.groupTemplate.getConf().getCharset();	
         this.setContentType("text/htm;charset=" + a);	
      }	
	
   }	
	
   protected Class requiredViewClass() {	
      return BeetlSpringView.class;	
   }	
	
   public String getBeanName() {	
      return this.beanName;	
   }	
}	
