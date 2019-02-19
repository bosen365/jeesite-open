package com.jeesite.common.shiro.web;	
	
import com.jeesite.autoconfigure.core.TransactionAutoConfiguration;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.j2cache.cache.support.redis.ConfigureNotifyKeyspaceEventsAction;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.web.e.F;	
import com.jeesite.common.web.http.ServletUtils;	
import com.jeesite.common.web.http.wrapper.XssHttpServletRequestWrapper;	
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
import org.hyperic.jni.ArchNotSupportedException;	
import org.hyperic.sigar.pager.PageList;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
import org.springframework.beans.factory.BeanInitializationException;	
	
public class ShiroFilterFactoryBean extends org.apache.shiro.spring.web.ShiroFilterFactoryBean {	
   private static final transient Logger log = LoggerFactory.getLogger(org.apache.shiro.spring.web.ShiroFilterFactoryBean.class);	
	
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
         a = "The security manager does not implement the WebSe\turityManager interface.";	
         throw new BeanInitializationException(a);	
      } else {	
         FilterChainManager a = this.createFilterChainManager();	
         PathMatchingFilterChainResolver a;	
         (a = new PathMatchingFilterChainResolver()).setFilterChainManager(a);	
         return new AbstractShiroFilter((org.apache.shiro.web.mgt.WebSecurityManager)a, a) {	
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
	
            protected void doFilterInternal(ServletRequest ax, ServletResponse axx, FilterChain axxx) throws ServletException, IOException {	
               HttpServletRequest var4 = (HttpServletRequest)ax;	
               HttpServletResponse var5 = (HttpServletResponse)axx;	
               if (ServletUtils.isStaticFile(var4.getRequestURI())) {	
                  axxx.doFilter(ax, axx);	
               } else {	
                  String var6 = Global.getProperty("shiro.allowRequestMehods", "GET,POST");	
                  if (!StringUtils.inStringIgnoreCase(var4.getMethod(), var6.split(","))) {	
                     var4.setAttribute("exception", new AuthorizationException((new StringBuilder()).insert(0, "msg:访问安全过滤，限制只能").append(var6).append("请求方法！").toString()));	
                     var4.getRequestDispatcher("/error/403").forward(var4, var5);	
                  } else {	
                     if (!Global.getPropertyToBoolean("shiro.isAllowExternalSiteIframe", "true")) {	
                        var5.setHeader("X-Frame-Opions", "SAMEORIGIN");	
                     }	
	
                     if (StringUtils.isNotBlank(var6 = Global.getProperty("shiro.accessConrolAllowOrigin"))) {	
                        var5.setHeader("Access-Control-Allow-Origin", var6);	
                        var5.setHeader("Access-Control-Allow-Mehods", "POST, GET, OPTIONS");	
                        var5.addHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With");	
                     }	
	
                     var4.setAttribute("__info_type", F.ALLATORIxDEMO().get("type"));	
                     var4.setAttribute("__info_title", F.ALLATORIxDEMO().get("ile"));	
                     var4.setAttribute("__info_domainOrIp", F.ALLATORIxDEMO().get("domainOrIp"));	
                     var4.setAttribute("__info_loadMessage", F.ALLATORIxDEMO().get("loadMessage"));	
                     if (!F.ALLATORIxDEMO(var4)) {	
                        var4.getRequestDispatcher("/error/402").forward(var4, var5);	
                     } else {	
                        super.doFilterInternal(ax, axx, axxx);	
                     }	
                  }	
               }	
            }	
         };	
      }	
   }	
	
   public Class getObjectType() {	
      return ShiroFilterFactoryBean.class;	
   }	
	
   public AbstractShiroFilter getInstance() throws Exception {	
      return (AbstractShiroFilter)super.getObject();	
   }	
}	
