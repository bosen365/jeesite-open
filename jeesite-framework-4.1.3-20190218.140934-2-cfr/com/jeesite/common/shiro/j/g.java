/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.collect.ListUtils	
 *  com.jeesite.common.lang.ObjectUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  net.oschina.j2cache.CacheProviderHolder	
 *  net.oschina.j2cache.Level1Cache	
 *  org.apache.shiro.cache.CacheException	
 */	
package com.jeesite.common.shiro.j;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.j.E;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.mapper.query.QueryColumn;	
import com.jeesite.common.shiro.j.e;	
import java.util.ArrayList;	
import java.util.Collection;	
import java.util.List;	
import java.util.Map;	
import java.util.Set;	
import java.util.function.BiConsumer;	
import net.oschina.j2cache.CacheProviderHolder;	
import net.oschina.j2cache.Level1Cache;	
import org.apache.shiro.cache.CacheException;	
	
public class g<K, V>	
implements e<K, V> {	
    private String ALLATORIxDEMO;	
	
    public int size() {	
        return CacheProviderHolder.getLevel1Cache((String)this.ALLATORIxDEMO).keys().size();	
    }	
	
    public g(String cacheName) {	
        this.ALLATORIxDEMO = cacheName;	
    }	
	
    public void clear() throws CacheException {	
        CacheProviderHolder.getLevel1Cache((String)this.ALLATORIxDEMO).clear();	
    }	
	
    public V get(K key) throws CacheException {	
        return (V)CacheProviderHolder.getLevel1Cache((String)this.ALLATORIxDEMO).get(ObjectUtils.toString(key));	
    }	
	
    public String toString() {	
        return new StringBuilder().insert(0, "J1Cache [").append(this.ALLATORIxDEMO).append("]").toString();	
    }	
	
    public V remove(K key) throws CacheException {	
        CacheProviderHolder.getLevel1Cache((String)this.ALLATORIxDEMO).evict(new String[]{ObjectUtils.toString(key)});	
        return null;	
    }	
	
    public V put(K key, V value) throws CacheException {	
        Level1Cache a2 = CacheProviderHolder.getLevel1Cache((String)this.ALLATORIxDEMO);	
        if (StringUtils.containsAny((CharSequence)a2.getClass().getName(), (CharSequence[])new CharSequence[]{"caffeine", "ehcache"})) {	
            a2.put(ObjectUtils.toString(key), value);	
        }	
        return value;	
    }	
	
    public Collection<V> values() {	
        ArrayList a2 = ListUtils.newArrayList();	
        g g2 = this;	
        Set<K> a3 = g2.keys();	
        Map a4 = CacheProviderHolder.getLevel1Cache((String)g2.ALLATORIxDEMO).get(a3);	
        if (a4 != null) {	
            a4.forEach((key, value) -> a2.add(value));	
        }	
        return a2;	
    }	
	
    public Set<K> keys() {	
        return (Set)CacheProviderHolder.getLevel1Cache((String)this.ALLATORIxDEMO).keys();	
    }	
	
    @Override	
    public V ALLATORIxDEMO(K key, V value, long timeToLiveInSeconds) throws CacheException {	
        Level1Cache a2 = CacheProviderHolder.getLevel1Cache((String)this.ALLATORIxDEMO, (long)timeToLiveInSeconds);	
        if (StringUtils.containsAny((CharSequence)a2.getClass().getName(), (CharSequence[])new CharSequence[]{"caffeine", "ehcache"})) {	
            a2.put(ObjectUtils.toString(key), value);	
        }	
        return value;	
    }	
}	
	
