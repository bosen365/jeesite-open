/*	
 * Decompiled with CFR 0.140.	
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
import org.hyperic.sigar.DirUsage;	
import org.hyperic.sigar.Tcp;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;	
import org.springframework.stereotype.Service;	
	
@Service	
@ConditionalOnProperty(name={"state.enabled"}, havingValue="true", matchIfMissing=true)	
public class StateCacheService	
extends BaseService {	
    public List<Map<String, String>> getCacheNameList() {	
        Iterator<String> iterator;	
        ArrayList<Map<String, String>> a = ListUtils.newArrayList();	
        Iterator<String> iterator2 = iterator = CacheUtils.getCacheNames().iterator();	
        while (iterator2.hasNext()) {	
            void a2;	
            String a3 = iterator.next();	
            HashMap hashMap = MapUtils.newHashMap();	
            iterator2 = iterator;	
            a.add((Map<String, String>)a2);	
            a2.put("id", a3);	
        }	
        ArrayList<Map<String, String>> arrayList = a;	
        ListUtils.listOrderBy(arrayList, "id");	
        return arrayList;	
    }	
	
    public List<Map<String, String>> getCacheKeyList(String cacheName) {	
        ArrayList<Map<String, String>> a = ListUtils.newArrayList();	
        if (StringUtils.isNotBlank(cacheName)) {	
            Iterator iterator;	
            Iterator iterator2 = iterator = CacheUtils.getCache(cacheName).keys().iterator();	
            while (iterator2.hasNext()) {	
                void a2;	
                String a3 = (String)iterator.next();	
                HashMap hashMap = MapUtils.newHashMap();	
                iterator2 = iterator;	
                a.add((Map<String, String>)a2);	
                a2.put("cacheName", cacheName);	
                a2.put("id", a3);	
            }	
        }	
        ArrayList<Map<String, String>> arrayList = a;	
        ListUtils.listOrderBy(arrayList, "id");	
        return arrayList;	
    }	
	
    public void clearCache(String cacheName, String key) {	
        if (StringUtils.isNotBlank(key)) {	
            CacheUtils.remove(cacheName, key);	
            return;	
        }	
        CacheUtils.removeCache(cacheName);	
    }	
	
    public Map<String, String> getCacheValue(String cacheName, String key) {	
        String a;	
        HashMap<String, String> a2 = MapUtils.newHashMap();	
        String string = "";	
        a2.put("key", key);	
        a2.put("cacheName", cacheName);	
        if (StringUtils.isNoneBlank(cacheName, key)) {	
            Object a3;	
            String string2;	
            block8 : {	
                a3 = CacheUtils.get(cacheName, key);	
                if (!(a3 instanceof Collection)) break block8;	
                a3 = MapUtils.toMapList((Collection)a3);	
            }	
            try {	
                if (!(a3 instanceof Map || a3 instanceof CharSequence || a3 instanceof Number)) {	
                    a3 = MapUtils.toMap(a3);	
                }	
            }	
            catch (Exception a4) {	
                a4.printStackTrace();	
            }	
            try {	
                string2 = a = new JsonMapper().writerWithDefaultPrettyPrinter().writeValueAsString(a3);	
            }	
            catch (JsonProcessingException a4) {	
                string2 = a;	
                a4.printStackTrace();	
            }	
            if (string2 == null) {	
                a = ObjectUtils.toString(a3);	
            }	
        }	
        HashMap<String, String> hashMap = a2;	
        hashMap.put("value", StringUtils.abbr(a, 512000));	
        return hashMap;	
    }	
}	
	
