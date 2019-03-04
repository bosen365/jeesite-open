package com.jeesite.common.shiro.web;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.l.j;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.common.validator.ValidatorUtils;	
import com.jeesite.common.web.http.ServletUtils;	
import com.jeesite.common.web.http.wrapper.XssHttpServletRequestWrapper;	
import com.jeesite.modules.file.utils.FileUploadUtils;	
import java.io.IOException;	
import javax.servlet.FilterChain;	
import javax.servlet.ServletException;	
import javax.servlet.ServletRequest;	
import javax.servlet.ServletResponse;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.apache.shiro.authz.AuthorizationException;	
import org.apache.shiro.mgt.SecurityManager;	
import org.apache.shiro.web.filter.mgt.FilterChainManager;	
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;	
import org.apache.shiro.web.servlet.AbstractShiroFilter;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
import org.springframework.beans.factory.BeanInitializationException;	
	
public class ShiroFilterFactoryBean extends org.apache.shiro.spring.web.ShiroFilterFactoryBean {	
   private static final transient Logger log = LoggerFactory.getLogger(org.apache.shiro.spring.web.ShiroFilterFactoryBean.class);	
   private AbstractShiroFilter instance;	
	
   public Object getObject() throws Exception {	
      return this;	
   }	
	
   protected AbstractShiroFilter createInstance() throws Exception {	
      log.debug("Creating Shiro Filter instance.");	
      SecurityManager a;	
      String a;	
      if ((a = this.getSecurityManager()) == null) {	
         a = "SecurityManager property must be set.";	
         throw new BeanInitializationException(a);	
      } else if (!(a instanceof org.apache.shiro.web.mgt.WebSecurityManager)) {	
         a = "The security manager does not implement the WebSecurityManager interface.";	
         throw new BeanInitializationException(a);	
      } else {	
         FilterChainManager a = this.createFilterChainManager();	
         PathMatchingFilterChainResolver a;	
         (a = new PathMatchingFilterChainResolver()).setFilterChainManager(a);	
         return new AbstractShiroFilter((org.apache.shiro.web.mgt.WebSecurityManager)a, a) {	
            protected void doFilterInternal(ServletRequest ax, ServletResponse axx, FilterChain axxx) throws ServletException, IOException {	
               HttpServletRequest var4 = (HttpServletRequest)ax;	
               HttpServletResponse var5 = (HttpServletResponse)axx;	
               if (ServletUtils.isStaticFile(var4.getRequestURI())) {	
                  axxx.doFilter(ax, axx);	
               } else if (!StringUtils.equalsIgnoreCase("GET", var4.getMethod()) && !StringUtils.equalsIgnoreCase("POST", var4.getMethod())) {	
                  var4.setAttribute("exception", new AuthorizationException("msg:访问安全过滤，限制只能GET或POST请求方法！"));	
                  var4.getRequestDispatcher("/error/403").forward(var4, var5);	
               } else {	
                  if (!ObjectUtils.toBoolean(Global.getProperty("shiro.isAllowExternalSiteIframe", "true"))) {	
                     var5.setHeader("X-Frame-Options", "SAMEORIGIN");	
                  }	
	
                  String var6;	
                  if (StringUtils.isNotBlank(var6 = Global.getProperty("shiro.accessControlAllowOrigin"))) {	
                     var5.setHeader("Access-Control-Allow-Origin", var6);	
                     var5.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");	
                     var5.addHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With");	
                  }	
	
                  var4.setAttribute("__info_type", com.jeesite.common.l.d.j.ALLATORIxDEMO().get("type"));	
                  var4.setAttribute("__info_title", com.jeesite.common.l.d.j.ALLATORIxDEMO().get("title"));	
                  var4.setAttribute("__info_domainOrIp", com.jeesite.common.l.d.j.ALLATORIxDEMO().get("domainOrIp"));	
                  var4.setAttribute("__info_loadMessage", com.jeesite.common.l.d.j.ALLATORIxDEMO().get("loadMessage"));	
                  if (!com.jeesite.common.l.d.j.ALLATORIxDEMO(var4)) {	
                     var4.getRequestDispatcher("error/402").forward(var4, var5);	
                  } else {	
                     super.doFilterInternal(ax, axx, axxx);	
                  }	
               }	
            }	
	
            protected {	
               if (ax == null) {	
                  throw new IllegalArgumentException("WebSecurityManager property cannot be null.");	
               } else {	
                  a.setSecurityManager(ax);	
                  if (axx != null) {	
                     a.setFilterChainResolver(axx);	
                  }	
	
               }	
            }	
	
            protected ServletRequest wrapServletRequest(HttpServletRequest ax) {	
               return new XssHttpServletRequestWrapper((HttpServletRequest)super.wrapServletRequest(ax));	
            }	
         };	
      }	
   }	
	
   public Class getObjectType() {	
      return ShiroFilterFactoryBean.class;	
   }	
	
   public AbstractShiroFilter getInstance() throws Exception {	
      if (this.instance == null) {	
         this.instance = this.createInstance();	
      }	
	
      return this.instance;	
   }	
}	
