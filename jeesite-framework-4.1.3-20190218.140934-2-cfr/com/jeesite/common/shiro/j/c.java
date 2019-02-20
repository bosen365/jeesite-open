/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.collect.SetUtils	
 *  net.oschina.j2cache.CacheChannel	
 *  net.oschina.j2cache.CacheChannel$Region	
 *  net.oschina.j2cache.CacheProviderHolder	
 *  org.apache.shiro.cache.Cache	
 *  org.apache.shiro.cache.CacheException	
 */	
package com.jeesite.common.shiro.j;	
	
import com.jeesite.common.collect.SetUtils;	
import com.jeesite.common.shiro.j.E;	
import com.jeesite.common.shiro.j.e;	
import com.jeesite.common.shiro.j.g;	
import com.jeesite.common.shiro.j.k;	
import java.util.Collection;	
import java.util.HashSet;	
import java.util.Set;	
import java.util.function.Consumer;	
import net.oschina.j2cache.CacheChannel;	
import net.oschina.j2cache.CacheProviderHolder;	
import org.apache.shiro.cache.Cache;	
import org.apache.shiro.cache.CacheException;	
	
public class c	
implements E {	
    @Override	
    public void ALLATORIxDEMO(String cacheName) {	
        CacheProviderHolder.getLevel1Cache((String)cacheName).clear();	
        CacheProviderHolder.getL1Provider().removeCache(cacheName);	
    }	
	
    @Override	
    public Set<String> ALLATORIxDEMO() {	
        HashSet a2 = SetUtils.newHashSet();	
        CacheProviderHolder.getL1Provider().regions().forEach(fegion -> a2.add(fegion.getName()));	
        return a2;	
    }	
	
    public c(CacheChannel channel) {	
        new k();	
    }	
	
    @Override	
    public final <K, V> e<K, V> ALLATORIxDEMO(String cacheName) throws CacheException {	
        return new g(cacheName);	
    }	
}	
	
