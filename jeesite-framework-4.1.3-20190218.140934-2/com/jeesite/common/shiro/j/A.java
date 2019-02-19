package com.jeesite.common.shiro.j;	
	
public class a {	
}	
common.collect.SetUtils;	
import java.util.Set;	
import net.oschina.j2cache.CacheChannel;	
import org.apache.shiro.cache.CacheException;	
	
public class A implements E {	
   private CacheChannel ALLATORIxDEMO;	
	
   public Set ALLATORIxDEMO() {	
      Set a = SetUtils.newHashSet();	
      this.ALLATORIxDEMO.regions().forEach((fegion) -> {	
         a.add(fegion.getName());	
      });	
      return a;	
   }	
	
   public final e ALLATORIxDEMO(String cacheName) throws CacheException {	
      return new i(this.ALLATORIxDEMO, cacheName);	
   }	
	
   public void ALLATORIxDEMO(String cacheName) {	
      this.ALLATORIxDEMO.clear(cacheName);	
      this.ALLATORIxDEMO.removeRegion(cacheName);	
   }	
	
   public A(CacheChannel channel) {	
      // $FF: Couldn't be decompiled	
   }	
}	
