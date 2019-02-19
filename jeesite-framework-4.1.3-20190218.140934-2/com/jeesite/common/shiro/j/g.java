package com.jeesite.common.shiro.j;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.mapper.query.QueryColumn;	
import java.util.Collection;	
import java.util.List;	
import java.util.Map;	
import java.util.Set;	
import net.oschina.j2cache.CacheProviderHolder;	
import net.oschina.j2cache.Level1Cache;	
import org.apache.shiro.cache.CacheException;	
	
public class g implements e {	
   private String ALLATORIxDEMO;	
	
   public int size() {	
      return CacheProviderHolder.getLevel1Cache(this.ALLATORIxDEMO).keys().size();	
   }	
	
   public g(String var1) {	
      this.ALLATORIxDEMO = var1;	
   }	
	
   public void clear() throws CacheException {	
      CacheProviderHolder.getLevel1Cache(this.ALLATORIxDEMO).clear();	
   }	
	
   public Object get(Object key) throws CacheException {	
      return CacheProviderHolder.getLevel1Cache(this.ALLATORIxDEMO).get(ObjectUtils.toString(key));	
   }	
	
   public String toString() {	
      return (new StringBuilder()).insert(0, "J1Cache [").append(this.ALLATORIxDEMO).append(com.jeesite.common.j.E.ALLATORIxDEMO(".")).toString();	
   }	
	
   public Object remove(Object key) throws CacheException {	
      Level1Cache var10000 = CacheProviderHolder.getLevel1Cache(this.ALLATORIxDEMO);	
      String[] var10001 = new String[1];	
      boolean var10003 = true;	
      var10001[0] = ObjectUtils.toString(key);	
      var10000.evict(var10001);	
      return null;	
   }	
	
   public Object put(Object key, Object value) throws CacheException {	
      Level1Cache a;	
      String var10000 = (a = CacheProviderHolder.getLevel1Cache(this.ALLATORIxDEMO)).getClass().getName();	
      CharSequence[] var10001 = new CharSequence[2];	
      boolean var10003 = true;	
      var10001[0] = "caffeine";	
      var10001[1] = com.jeesite.common.j.E.ALLATORIxDEMO("\u0016\f\u0010\u0005\u0010\f\u0016");	
      if (StringUtils.containsAny(var10000, var10001)) {	
         a.put(ObjectUtils.toString(key), value);	
      }	
	
      return value;	
   }	
	
   public Collection values() {	
      List a = ListUtils.newArrayList();	
      Collection a = this.keys();	
      Map a;	
      if ((a = CacheProviderHolder.getLevel1Cache(this.ALLATORIxDEMO).get(a)) != null) {	
         a.forEach((key, value) -> {	
            a.add(value);	
         });	
      }	
	
      return a;	
   }	
	
   public Set keys() {	
      return (Set)CacheProviderHolder.getLevel1Cache(this.ALLATORIxDEMO).keys();	
   }	
	
   public Object ALLATORIxDEMO(Object key, Object value, long timeToLiveInSeconds) throws CacheException {	
      Level1Cache a;	
      String var10000 = (a = CacheProviderHolder.getLevel1Cache(this.ALLATORIxDEMO, timeToLiveInSeconds)).getClass().getName();	
      CharSequence[] var10001 = new CharSequence[2];	
      boolean var10003 = true;	
      var10001[0] = "caffeine";	
      var10001[1] = com.jeesite.common.j.E.ALLATORIxDEMO("\u0016\f\u0010\u0005\u0010\f\u0016");	
      if (StringUtils.containsAny(var10000, var10001)) {	
         a.put(ObjectUtils.toString(key), value);	
      }	
	
      return value;	
   }	
}	
