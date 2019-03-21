/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.collect.ListUtils	
 *  com.jeesite.common.collect.SetUtils	
 *  com.jeesite.common.lang.ObjectUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  net.oschina.j2cache.CacheChannel	
 *  net.oschina.j2cache.CacheObject	
 *  org.apache.shiro.cache.CacheException	
 */	
package com.jeesite.common.shiro.d;	
	
import com.jeesite.autoconfigure.sys.FileAutoConfiguration;	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.collect.SetUtils;	
import com.jeesite.common.j2cache.autoconfigure.J2CacheAutoConfiguration;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.shiro.d.G;	
import com.jeesite.common.web.d.m;	
import java.util.ArrayList;	
import java.util.Collection;	
import java.util.HashSet;	
import java.util.List;	
import java.util.Map;	
import java.util.Set;	
import java.util.function.BiConsumer;	
import java.util.function.Consumer;	
import net.oschina.j2cache.CacheChannel;	
import net.oschina.j2cache.CacheObject;	
import org.apache.shiro.cache.CacheException;	
	
public class k<K, V>	
extends G<K, V> {	
    private String b;	
    private CacheChannel ALLATORIxDEMO;	
	
    @Override	
    public Set<K> keys() {	
        if (!ObjectUtils.toBoolean(m.ALLATORIxDEMO().get("fnCluster")).booleanValue()) {	
            return super.keys();	
        }	
        HashSet a = SetUtils.newHashSet();	
        k k2 = this;	
        k2.ALLATORIxDEMO.keys(k2.b).forEach(string -> {	
            String key;	
            void keys;	
            if (StringUtils.contains((CharSequence)key, (CharSequence)(":" + this.b + ":"))) {	
                key = StringUtils.substringAfter((String)key, (String)new StringBuilder().insert(0, ":").append(this.b).append(":").toString());	
            }	
            keys.add(key);	
        });	
        return a;	
    }	
	
    @Override	
    public V remove(K key) throws CacheException {	
        if (!ObjectUtils.toBoolean(m.ALLATORIxDEMO().get("fnCluster")).booleanValue()) {	
            return super.remove(key);	
        }	
        k k2 = this;	
        k2.ALLATORIxDEMO.evict(k2.b, new String[]{ObjectUtils.toString(key)});	
        return null;	
    }	
	
    @Override	
    public V get(K key) throws CacheException {	
        if (!ObjectUtils.toBoolean(m.ALLATORIxDEMO().get("fnCluster")).booleanValue()) {	
            return super.get(key);	
        }	
        try {	
            k k2 = this;	
            return (V)k2.ALLATORIxDEMO.get(k2.b, ObjectUtils.toString(key), new boolean[0]).getValue();	
        }	
        catch (Exception exception) {	
            void a;	
            a.printStackTrace();	
            this.remove(key);	
            return null;	
        }	
    }	
	
    @Override	
    public Collection<V> values() {	
        if (!ObjectUtils.toBoolean(m.ALLATORIxDEMO().get("fnCluster")).booleanValue()) {	
            return super.values();	
        }	
        ArrayList a = ListUtils.newArrayList();	
        k k2 = this;	
        Set<K> a2 = k2.keys();	
        Map a3 = k2.ALLATORIxDEMO.get(this.b, a2);	
        if (a3 != null) {	
            a3.forEach((key, value) -> a.add(value.getValue()));	
        }	
        return a;	
    }	
	
    @Override	
    public int size() {	
        if (!ObjectUtils.toBoolean(m.ALLATORIxDEMO().get("fnCluster")).booleanValue()) {	
            return super.size();	
        }	
        k k2 = this;	
        return k2.ALLATORIxDEMO.keys(k2.b).size();	
    }	
	
    @Override	
    public void clear() throws CacheException {	
        if (!ObjectUtils.toBoolean(m.ALLATORIxDEMO().get("fnCluster")).booleanValue()) {	
            super.clear();	
        }	
        k k2 = this;	
        k2.ALLATORIxDEMO.clear(k2.b);	
    }	
	
    @Override	
    public V ALLATORIxDEMO(K key, V value, long timeToLiveInSeconds) throws CacheException {	
        if (!ObjectUtils.toBoolean(m.ALLATORIxDEMO().get("fnCluster")).booleanValue()) {	
            return super.put(key, value);	
        }	
        k k2 = this;	
        k2.ALLATORIxDEMO.set(k2.b, ObjectUtils.toString(key), value, timeToLiveInSeconds);	
        return value;	
    }	
	
    public k(CacheChannel channel, String cacheName) {	
        k k2 = this;	
        super(cacheName);	
        k2.ALLATORIxDEMO = channel;	
        k2.b = cacheName;	
    }	
	
    @Override	
    public V put(K key, V value) throws CacheException {	
        if (!ObjectUtils.toBoolean(m.ALLATORIxDEMO().get("fnCluster")).booleanValue()) {	
            return super.put(key, value);	
        }	
        k k2 = this;	
        k2.ALLATORIxDEMO.set(k2.b, ObjectUtils.toString(key), value);	
        return value;	
    }	
	
    @Override	
    public String toString() {	
        return new StringBuilder().insert(0, "J2Cache [").append(this.b).append("7").toString();	
    }	
}	
	
