/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.common.shiro.d;	
	
import com.jeesite.common.collect.SetUtils;	
import com.jeesite.common.shiro.d.A;	
import com.jeesite.common.shiro.d.G;	
import com.jeesite.common.shiro.d.c;	
import com.jeesite.common.shiro.d.i;	
import java.util.Collection;	
import java.util.HashSet;	
import java.util.Set;	
import java.util.function.Consumer;	
import net.oschina.j2cache.CacheChannel;	
import net.oschina.j2cache.CacheProviderHolder;	
import org.apache.shiro.cache.Cache;	
import org.apache.shiro.cache.CacheException;	
	
public class h	
implements i {	
    @Override	
    public Set<String> ALLATORIxDEMO() {	
        HashSet<String> a = SetUtils.newHashSet();	
        CacheProviderHolder.getL1Provider().regions().forEach(fegion -> a.add(fegion.getName()));	
        return a;	
    }	
	
    @Override	
    public final <K, V> c<K, V> ALLATORIxDEMO(String cacheName) throws CacheException {	
        return new G(cacheName);	
    }	
	
    public h(CacheChannel channel) {	
        new A();	
    }	
	
    @Override	
    public void ALLATORIxDEMO(String cacheName) {	
        CacheProviderHolder.getLevel1Cache(cacheName).clear();	
        CacheProviderHolder.getL1Provider().removeCache(cacheName);	
    }	
}	
	
