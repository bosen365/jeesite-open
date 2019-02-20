/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.fasterxml.jackson.core.JsonProcessingException	
 *  com.fasterxml.jackson.databind.ObjectWriter	
 *  com.jeesite.common.collect.ListUtils	
 *  com.jeesite.common.collect.MapUtils	
 *  com.jeesite.common.lang.ObjectUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  com.jeesite.common.mapper.JsonMapper	
 *  org.springframework.boot.autoconfigure.condition.ConditionalOnProperty	
 *  org.springframework.stereotype.Service	
 */	
package com.jeesite.modules.state.service;	
	
import com.fasterxml.jackson.core.JsonProcessingException;	
import com.fasterxml.jackson.databind.ObjectWriter;	
import com.jeesite.common.cache.CacheUtils;	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mapper.JsonMapper;	
import com.jeesite.common.service.BaseService;	
import java.util.ArrayList;	
import java.util.Collection;	
import java.util.HashMap;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Map;	
import java.util.Set;	
import org.hyperic.sigar.CpuPerc;	
import org.hyperic.sigar.Swap;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;	
import org.springframework.stereotype.Service;	
	
@Service	
@ConditionalOnProperty(name={"state.enabled"}, havingValue="true", matchIfMissing=true)	
public class StateCacheService	
extends BaseService {	
    public void clearCache(String cacheName, String key) {	
        if (StringUtils.isNotBlank((CharSequence)key)) {	
            CacheUtils.remove(cacheName, key);	
            return;	
        }	
        CacheUtils.removeCache(cacheName);	
    }	
	
    public List<Map<String, String>> getCacheKeyList(String cacheName) {	
        ArrayList a2 = ListUtils.newArrayList();	
        if (StringUtils.isNotBlank((CharSequence)cacheName)) {	
            Iterator iterator;	
            Iterator iterator2 = iterator = CacheUtils.getCache(cacheName).keys().iterator();	
            while (iterator2.hasNext()) {	
                void a3;	
                String a4 = (String)iterator.next();	
                HashMap hashMap = MapUtils.newHashMap();	
                iterator2 = iterator;	
                a2.add(a3);	
                a3.put("cacheName", cacheName);	
                a3.put("id", a4);	
            }	
        }	
        ArrayList arrayList = a2;	
        ListUtils.listOrderBy((List)arrayList, (String)"id");	
        return arrayList;	
    }	
	
    public Map<String, String> getCacheValue(String cacheName, String key) {	
        String a2;	
        HashMap a3 = MapUtils.newHashMap();	
        String string = "";	
        a3.put("key", key);	
        a3.put("cacheName", cacheName);	
        if (StringUtils.isNoneBlank((CharSequence[])new CharSequence[]{cacheName, key})) {	
            Object a4;	
            String string2;	
            block8 : {	
                a4 = CacheUtils.get(cacheName, key);	
                if (!(a4 instanceof Collection)) break block8;	
                a4 = MapUtils.toMapList((Collection)((Collection)a4));	
            }	
            try {	
                if (!(a4 instanceof Map || a4 instanceof CharSequence || a4 instanceof Number)) {	
                    a4 = MapUtils.toMap((Object)a4);	
                }	
            }	
            catch (Exception a5) {	
                a5.printStackTrace();	
            }	
            try {	
                string2 = a2 = new JsonMapper().writerWithDefaultPrettyPrinter().writeValueAsString(a4);	
            }	
            catch (JsonProcessingException a5) {	
                string2 = a2;	
                a5.printStackTrace();	
            }	
            if (string2 == null) {	
                a2 = ObjectUtils.toString((Object)a4);	
            }	
        }	
        HashMap hashMap = a3;	
        hashMap.put("value", StringUtils.abbr((String)a2, (int)512000));	
        return hashMap;	
    }	
	
    public List<Map<String, String>> getCacheNameList() {	
        Iterator<String> iterator;	
        ArrayList a2 = ListUtils.newArrayList();	
        Iterator<String> iterator2 = iterator = CacheUtils.getCacheNames().iterator();	
        while (iterator2.hasNext()) {	
            void a3;	
            String a4 = iterator.next();	
            HashMap hashMap = MapUtils.newHashMap();	
            iterator2 = iterator;	
            a2.add(a3);	
            a3.put("id", a4);	
        }	
        ArrayList arrayList = a2;	
        ListUtils.listOrderBy((List)arrayList, (String)"id");	
        return arrayList;	
    }	
}	
	
