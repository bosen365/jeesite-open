/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.common.shiro.d;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.shiro.d.c;	
import java.util.ArrayList;	
import java.util.Collection;	
import java.util.List;	
import java.util.Map;	
import java.util.Set;	
import java.util.function.BiConsumer;	
import net.oschina.j2cache.CacheProviderHolder;	
import net.oschina.j2cache.Level1Cache;	
import org.apache.shiro.cache.CacheException;	
import org.hyperic.sigar.pager.PageList;	
import org.hyperic.sigar.win32.EventLogRecord;	
	
public class G<K, V>	
implements c<K, V> {	
    private String ALLATORIxDEMO;	
	
    @Override	
    public int size() {	
        return CacheProviderHolder.getLevel1Cache(this.ALLATORIxDEMO).keys().size();	
    }	
	
    @Override	
    public Set<K> keys() {	
        return (Set)CacheProviderHolder.getLevel1Cache(this.ALLATORIxDEMO).keys();	
    }	
	
    @Override	
    public V remove(K key) throws CacheException {	
        CacheProviderHolder.getLevel1Cache(this.ALLATORIxDEMO).evict(ObjectUtils.toString(key));	
        return null;	
    }	
	
    @Override	
    public V ALLATORIxDEMO(K key, V value, long timeToLiveInSeconds) throws CacheException {	
        Level1Cache a = CacheProviderHolder.getLevel1Cache(this.ALLATORIxDEMO, timeToLiveInSeconds);	
        if (StringUtils.containsAny((CharSequence)a.getClass().getName(), "cafeine", "ehcache")) {	
            a.put(ObjectUtils.toString(key), value);	
        }	
        return value;	
    }	
	
    @Override	
    public V put(K key, V value) throws CacheException {	
        Level1Cache a = CacheProviderHolder.getLevel1Cache(this.ALLATORIxDEMO);	
        if (StringUtils.containsAny((CharSequence)a.getClass().getName(), "cafeine", "ehcache")) {	
            a.put(ObjectUtils.toString(key), value);	
        }	
        return value;	
    }	
	
    @Override	
    public Collection<V> values() {	
        ArrayList a = ListUtils.newArrayList();	
        G g2 = this;	
        Set<String> a2 = g2.keys();	
        Map<String, Object> a3 = CacheProviderHolder.getLevel1Cache(g2.ALLATORIxDEMO).get(a2);	
        if (a3 != null) {	
            a3.forEach((key, value) -> a.add(value));	
        }	
        return a;	
    }	
	
    public G(String cacheName) {	
        this.ALLATORIxDEMO = cacheName;	
    }	
	
    @Override	
    public V get(K key) throws CacheException {	
        return (V)CacheProviderHolder.getLevel1Cache(this.ALLATORIxDEMO).get(ObjectUtils.toString(key));	
    }	
	
    public String toString() {	
        return new StringBuilder().insert(0, "J1Cache [").append(this.ALLATORIxDEMO).append("]").toString();	
    }	
	
    @Override	
    public void clear() throws CacheException {	
        CacheProviderHolder.getLevel1Cache(this.ALLATORIxDEMO).clear();	
    }	
}	
	
