/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.shiro.l;	
	
import com.jeesite.common.shiro.l.h;	
import java.util.Set;	
import org.apache.shiro.cache.Cache;	
import org.apache.shiro.cache.CacheException;	
import org.apache.shiro.cache.CacheManager;	
	
public interface m	
extends CacheManager {	
    public <K, V> h<K, V> ALLATORIxDEMO(String var1) throws CacheException;	
	
    public Set<String> ALLATORIxDEMO();	
	
    public void ALLATORIxDEMO(String var1);	
}	
	
