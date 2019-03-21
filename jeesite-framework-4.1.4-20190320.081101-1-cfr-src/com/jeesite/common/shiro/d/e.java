/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.collect.SetUtils	
 *  net.oschina.j2cache.CacheChannel	
 *  net.oschina.j2cache.CacheChannel$Region	
 *  org.apache.shiro.cache.Cache	
 *  org.apache.shiro.cache.CacheException	
 */	
package com.jeesite.common.shiro.d;	
	
import com.jeesite.common.collect.SetUtils;	
import com.jeesite.common.shiro.d.A;	
import com.jeesite.common.shiro.d.c;	
import com.jeesite.common.shiro.d.i;	
import com.jeesite.common.shiro.d.k;	
import java.util.Collection;	
import java.util.HashSet;	
import java.util.Set;	
import java.util.function.Consumer;	
import net.oschina.j2cache.CacheChannel;	
import org.apache.shiro.cache.Cache;	
import org.apache.shiro.cache.CacheException;	
	
public class e	
implements i {	
    private CacheChannel ALLATORIxDEMO;	
	
    @Override	
    public void ALLATORIxDEMO(String cacheName) {	
        e e2 = this;	
        e2.ALLATORIxDEMO.clear(cacheName);	
        e2.ALLATORIxDEMO.removeRegion(cacheName);	
    }	
	
    public e(CacheChannel channel) {	
        this.ALLATORIxDEMO = channel;	
        new A();	
    }	
	
    @Override	
    public final <K, V> c<K, V> ALLATORIxDEMO(String cacheName) throws CacheException {	
        return new k(this.ALLATORIxDEMO, cacheName);	
    }	
	
    @Override	
    public Set<String> ALLATORIxDEMO() {	
        HashSet a = SetUtils.newHashSet();	
        this.ALLATORIxDEMO.regions().forEach(fegion -> a.add(fegion.getName()));	
        return a;	
    }	
}	
	
