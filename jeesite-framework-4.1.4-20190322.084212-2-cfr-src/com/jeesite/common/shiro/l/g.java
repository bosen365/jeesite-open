/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.shiro.l;	
	
import com.jeesite.common.collect.SetUtils;	
import com.jeesite.common.shiro.l.M;	
import com.jeesite.common.shiro.l.h;	
import com.jeesite.common.shiro.l.i;	
import com.jeesite.common.shiro.l.m;	
import java.util.Collection;	
import java.util.HashSet;	
import java.util.Set;	
import java.util.function.Consumer;	
import net.oschina.j2cache.CacheChannel;	
import net.oschina.j2cache.CacheProviderHolder;	
import org.apache.shiro.cache.Cache;	
import org.apache.shiro.cache.CacheException;	
	
public class G	
implements m {	
    @Override	
    public void ALLATORIxDEMO(String cacheName) {	
        CacheProviderHolder.getLevel1Cache(cacheName).clear();	
        CacheProviderHolder.getL1Provider().removeCache(cacheName);	
    }	
	
    @Override	
    public Set<String> ALLATORIxDEMO() {	
        HashSet<String> a = SetUtils.newHashSet();	
        CacheProviderHolder.getL1Provider().regions().forEach(fegion -> a.add(fegion.getName()));	
        return a;	
    }	
	
    public G(CacheChannel channel) {	
        new M();	
    }	
	
    @Override	
    public final <K, V> h<K, V> ALLATORIxDEMO(String cacheName) throws CacheException {	
        return new i(cacheName);	
    }	
}	
	
