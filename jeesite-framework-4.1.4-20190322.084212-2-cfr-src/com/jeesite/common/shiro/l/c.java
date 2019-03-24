/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.shiro.l;	
	
import com.jeesite.common.collect.SetUtils;	
import com.jeesite.common.shiro.l.M;	
import com.jeesite.common.shiro.l.g;	
import com.jeesite.common.shiro.l.h;	
import com.jeesite.common.shiro.l.m;	
import java.util.Collection;	
import java.util.HashSet;	
import java.util.Set;	
import java.util.function.Consumer;	
import net.oschina.j2cache.CacheChannel;	
import org.apache.shiro.cache.Cache;	
import org.apache.shiro.cache.CacheException;	
	
public class c	
implements m {	
    private CacheChannel ALLATORIxDEMO;	
	
    public c(CacheChannel channel) {	
        new M();	
        this.ALLATORIxDEMO = channel;	
    }	
	
    @Override	
    public void ALLATORIxDEMO(String cacheName) {	
        c c2 = this;	
        c2.ALLATORIxDEMO.clear(cacheName);	
        c2.ALLATORIxDEMO.removeRegion(cacheName);	
    }	
	
    @Override	
    public Set<String> ALLATORIxDEMO() {	
        HashSet<String> a = SetUtils.newHashSet();	
        this.ALLATORIxDEMO.regions().forEach(fegion -> a.add(fegion.getName()));	
        return a;	
    }	
	
    @Override	
    public final <K, V> h<K, V> ALLATORIxDEMO(String cacheName) throws CacheException {	
        return new g(this.ALLATORIxDEMO, cacheName);	
    }	
}	
	
