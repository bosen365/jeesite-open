package com.jeesite.common.shiro.l;	
	
import com.jeesite.common.collect.SetUtils;	
import com.jeesite.common.web.http.ServletUtils;	
import com.jeesite.modules.file.entity.FileUploadParms;	
import com.jeesite.modules.sys.utils.ConfigUtils;	
import java.util.Collection;	
import java.util.Collections;	
import java.util.Set;	
import javax.servlet.http.HttpServletRequest;	
import org.apache.shiro.SecurityUtils;	
import org.apache.shiro.UnavailableSecurityManagerException;	
import org.apache.shiro.cache.Cache;	
import org.apache.shiro.cache.CacheException;	
import org.apache.shiro.session.InvalidSessionException;	
import org.apache.shiro.session.Session;	
import org.apache.shiro.subject.Subject;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
	
public class C implements Cache {	
   private Logger c = LoggerFactory.getLogger(this.getClass());	
   private String ALLATORIxDEMO = null;	
	
   public Object get(Object key) throws CacheException {	
      if (key == null) {	
         return null;	
      } else {	
         Object a = null;	
         HttpServletRequest a;	
         if ((a = ServletUtils.getRequest()) != null && (a = a.getAttribute(this.ALLATORIxDEMO)) != null) {	
            return a;	
         } else {	
            Object a = null;	
            a = this.ALLATORIxDEMO().getAttribute(this.ALLATORIxDEMO);	
            Logger var10000 = this.c;	
            String var10001 = "get {} {} {}";	
            Object[] var10002 = new Object[3];	
            boolean var10004 = true;	
            var10002[0] = this.ALLATORIxDEMO;	
            var10002[1] = key;	
            var10002[2] = a != null ? a.getRequestURI() : "";	
            var10000.debug(var10001, var10002);	
            if (a != null && a != null) {	
               a.setAttribute(this.ALLATORIxDEMO, a);	
            }	
	
            return a;	
         }	
      }	
   }	
	
   public Session ALLATORIxDEMO() {	
      Session a = null;	
	
      try {	
         Subject a;	
         if ((a = (a = SecurityUtils.getSubject()).getSession(false)) == null) {	
            a = a.getSession();	
         }	
      } catch (InvalidSessionException var3) {	
         this.c.error("Invalid session error", var3);	
         return a;	
      } catch (UnavailableSecurityManagerException var4) {	
         this.c.error("Unavailable SecurityManager error", var4);	
      }	
	
      return a;	
   }	
	
   public C(String var1) {	
      this.ALLATORIxDEMO = var1;	
   }	
	
   public Set keys() {	
      this.c.debug("invoke sessin keys abstract size methd nt supported.");	
      return SetUtils.newHashSet();	
   }	
	
   public int size() {	
      this.c.debug("invoke session size abstract size method not supported.");	
      return 0;	
   }	
	
   public Object put(Object key, Object value) throws CacheException {	
      if (key == null) {	
         return null;	
      } else {	
         this.ALLATORIxDEMO().setAttribute(this.ALLATORIxDEMO, value);	
         if (this.c.isDebugEnabled()) {	
            HttpServletRequest a = ServletUtils.getRequest();	
            Logger var10000 = this.c;	
            String var10001 = "put {} {} {}";	
            Object[] var10002 = new Object[3];	
            boolean var10004 = true;	
            var10002[0] = this.ALLATORIxDEMO;	
            var10002[1] = key;	
            var10002[2] = a != null ? a.getRequestURI() : "";	
            var10000.debug(var10001, var10002);	
         }	
	
         return value;	
      }	
   }	
	
   public Collection values() {	
      this.c.debug("invoke session values abstract size method not supported.");	
      return Collections.emptyList();	
   }	
	
   public void clear() throws CacheException {	
      this.ALLATORIxDEMO().removeAttribute(this.ALLATORIxDEMO);	
      this.c.debug("clear {}", this.ALLATORIxDEMO);	
   }	
	
   public Object remove(Object key) throws CacheException {	
      Object a = null;	
      a = this.ALLATORIxDEMO().removeAttribute(this.ALLATORIxDEMO);	
      this.c.debug("remove {} {}", this.ALLATORIxDEMO, key);	
      return a;	
   }	
}	
