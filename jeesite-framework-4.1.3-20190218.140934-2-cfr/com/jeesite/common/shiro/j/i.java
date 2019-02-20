/*	
 * Decompiled with CFR 0.139.	
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
package com.jeesite.common.shiro.j;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.collect.SetUtils;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.shiro.cas.CasOutHandler;	
import com.jeesite.common.shiro.j.g;	
import com.jeesite.common.web.j.F;	
import com.jeesite.modules.gen.entity.config.GenConfig;	
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
	
public class i<K, V>	
extends g<K, V> {	
    private String c;	
    private CacheChannel ALLATORIxDEMO;	
	
    @Override	
    public V get(K key) throws CacheException {	
        if (!ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnCluster")).booleanValue()) {	
            return super.get(key);	
        }	
        try {	
            i i2 = this;	
            return (V)i2.ALLATORIxDEMO.get(i2.c, ObjectUtils.toString(key), new boolean[0]).getValue();	
        }	
        catch (Exception exception) {	
            void a2;	
            a2.printStackTrace();	
            this.remove(key);	
            return null;	
        }	
    }	
	
    @Override	
    public Set<K> keys() {	
        if (!ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnCluster")).booleanValue()) {	
            return super.keys();	
        }	
        HashSet a2 = SetUtils.newHashSet();	
        i i2 = this;	
        i2.ALLATORIxDEMO.keys(i2.c).forEach(string -> {	
            String key;	
            void keys;	
            if (StringUtils.contains((CharSequence)key, (CharSequence)(":" + this.c + ":"))) {	
                key = StringUtils.substringAfter((String)key, (String)new StringBuilder().insert(0, ":").append(this.c).append(":").toString());	
            }	
            keys.add(key);	
        });	
        return a2;	
    }	
	
    @Override	
    public void clear() throws CacheException {	
        if (!ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnCluster")).booleanValue()) {	
            super.clear();	
        }	
        i i2 = this;	
        i2.ALLATORIxDEMO.clear(i2.c);	
    }	
	
    @Override	
    public V put(K key, V value) throws CacheException {	
        if (!ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnCluster")).booleanValue()) {	
            return super.put(key, value);	
        }	
        i i2 = this;	
        i2.ALLATORIxDEMO.set(i2.c, ObjectUtils.toString(key), value);	
        return value;	
    }	
	
    public i(CacheChannel channel, String cacheName) {	
        i i2 = this;	
        super(cacheName);	
        i2.ALLATORIxDEMO = channel;	
        i2.c = cacheName;	
    }	
	
    @Override	
    public String toString() {	
        return new StringBuilder().insert(0, "J2Cache [").append(this.c).append("]").toString();	
    }	
	
    @Override	
    public Collection<V> values() {	
        if (!ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnCluster")).booleanValue()) {	
            return super.values();	
        }	
        ArrayList a2 = ListUtils.newArrayList();	
        i i2 = this;	
        Set<K> a3 = i2.keys();	
        Map a4 = i2.ALLATORIxDEMO.get(this.c, a3);	
        if (a4 != null) {	
            a4.forEach((key, value) -> a2.add(value.getValue()));	
        }	
        return a2;	
    }	
	
    @Override	
    public V remove(K key) throws CacheException {	
        if (!ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnCluster")).booleanValue()) {	
            return super.remove(key);	
        }	
        i i2 = this;	
        i2.ALLATORIxDEMO.evict(i2.c, new String[]{ObjectUtils.toString(key)});	
        return null;	
    }	
	
    @Override	
    public int size() {	
        if (!ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnCluster")).booleanValue()) {	
            return super.size();	
        }	
        i i2 = this;	
        return i2.ALLATORIxDEMO.keys(i2.c).size();	
    }	
	
    @Override	
    public V ALLATORIxDEMO(K key, V value, long timeToLiveInSeconds) throws CacheException {	
        if (!ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnCluster")).booleanValue()) {	
            return super.put(key, value);	
        }	
        i i2 = this;	
        i2.ALLATORIxDEMO.set(i2.c, ObjectUtils.toString(key), value, timeToLiveInSeconds);	
        return value;	
    }	
}	
	
