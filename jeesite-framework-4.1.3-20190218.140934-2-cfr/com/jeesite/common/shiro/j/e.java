/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  org.apache.shiro.cache.Cache	
 *  org.apache.shiro.cache.CacheException	
 *  org.apache.shiro.cache.CacheManager	
 */	
package com.jeesite.common.shiro.j;	
	
import com.jeesite.common.shiro.j.e;	
import java.util.Set;	
import org.apache.shiro.cache.Cache;	
import org.apache.shiro.cache.CacheException;	
import org.apache.shiro.cache.CacheManager;	
	
public interface E	
extends CacheManager {	
    public <K, V> e<K, V> ALLATORIxDEMO(String var1) throws CacheException;	
	
    public void ALLATORIxDEMO(String var1);	
	
    public Set<String> ALLATORIxDEMO();	
}	
	
