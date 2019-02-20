package com.jeesite.common.shiro.j;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.collect.SetUtils;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.shiro.cas.CasOutHandler;	
import com.jeesite.modules.gen.entity.config.GenConfig;	
import java.util.Collection;	
import java.util.List;	
import java.util.Map;	
import java.util.Set;	
import net.oschina.j2cache.CacheChannel;	
import org.apache.shiro.cache.CacheException;	
	
public class i extends g {	
   private String c;	
   private CacheChannel ALLATORIxDEMO;	
	
   public Object get(Object key) throws CacheException {	
      if (!ObjectUtils.toBoolean(com.jeesite.common.web.j.F.ALLATORIxDEMO().get("fnCluster"))) {	
         return super.get(key);	
      } else {	
         try {	
            CacheChannel var10000 = this.ALLATORIxDEMO;	
            String var10001 = this.c;	
            String var10002 = ObjectUtils.toString(key);	
            boolean[] var10003 = new boolean[0];	
            boolean var10005 = true;	
            return var10000.get(var10001, var10002, var10003).getValue();	
         } catch (Exception var3) {	
            var3.printStackTrace();	
            this.remove(key);	
            return null;	
         }	
      }	
   }	
	
   public Set keys() {	
      if (!ObjectUtils.toBoolean(com.jeesite.common.web.j.F.ALLATORIxDEMO().get("fnCluster"))) {	
         return super.keys();	
      } else {	
         Set a = SetUtils.newHashSet();	
         this.ALLATORIxDEMO.keys(this.c).forEach((key) -> {	
            if (StringUtils.contains(key, ":" + this.c + ":")) {	
               key = StringUtils.substringAfter(key, (new StringBuilder()).insert(0, ":").append(this.c).append(":").toString());	
            }	
	
            a.add(key);	
         });	
         return a;	
      }	
   }	
	
   public void clear() throws CacheException {	
      if (!ObjectUtils.toBoolean(com.jeesite.common.web.j.F.ALLATORIxDEMO().get("fnCluster"))) {	
         super.clear();	
      }	
	
      this.ALLATORIxDEMO.clear(this.c);	
   }	
	
   public Object put(Object key, Object value) throws CacheException {	
      if (!ObjectUtils.toBoolean(com.jeesite.common.web.j.F.ALLATORIxDEMO().get("fnCluster"))) {	
         return super.put(key, value);	
      } else {	
         this.ALLATORIxDEMO.set(this.c, ObjectUtils.toString(key), value);	
         return value;	
      }	
   }	
	
   public i(CacheChannel channel, String cacheName) {	
      super(cacheName);	
      this.ALLATORIxDEMO = channel;	
      this.c = cacheName;	
   }	
	
   public String toString() {	
      return (new StringBuilder()).insert(0, "J2Cache [").append(this.c).append("]").toString();	
   }	
	
   public Collection values() {	
      if (!ObjectUtils.toBoolean(com.jeesite.common.web.j.F.ALLATORIxDEMO().get("fnCluster"))) {	
         return super.values();	
      } else {	
         List a = ListUtils.newArrayList();	
         Collection a = this.keys();	
         Map a;	
         if ((a = this.ALLATORIxDEMO.get(this.c, a)) != null) {	
            a.forEach((key, value) -> {	
               a.add(value.getValue());	
            });	
         }	
	
         return a;	
      }	
   }	
	
   public Object remove(Object key) throws CacheException {	
      if (!ObjectUtils.toBoolean(com.jeesite.common.web.j.F.ALLATORIxDEMO().get("fnCluster"))) {	
         return super.remove(key);	
      } else {	
         CacheChannel var10000 = this.ALLATORIxDEMO;	
         String var10001 = this.c;	
         String[] var10002 = new String[1];	
         boolean var10004 = true;	
         var10002[0] = ObjectUtils.toString(key);	
         var10000.evict(var10001, var10002);	
         return null;	
      }	
   }	
	
   public int size() {	
      return !ObjectUtils.toBoolean(com.jeesite.common.web.j.F.ALLATORIxDEMO().get("fnCluster")) ? super.size() : this.ALLATORIxDEMO.keys(this.c).size();	
   }	
	
   public Object ALLATORIxDEMO(Object key, Object value, long timeToLiveInSeconds) throws CacheException {	
      if (!ObjectUtils.toBoolean(com.jeesite.common.web.j.F.ALLATORIxDEMO().get("fnCluster"))) {	
         return super.put(key, value);	
      } else {	
         this.ALLATORIxDEMO.set(this.c, ObjectUtils.toString(key), value, timeToLiveInSeconds);	
         return value;	
      }	
   }	
}	
