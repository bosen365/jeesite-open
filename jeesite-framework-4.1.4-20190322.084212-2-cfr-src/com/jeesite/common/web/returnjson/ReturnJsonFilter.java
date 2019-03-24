/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.web.returnjson;	
	
import com.fasterxml.jackson.annotation.JsonFilter;	
import com.fasterxml.jackson.databind.ser.BeanPropertyFilter;	
import com.fasterxml.jackson.databind.ser.FilterProvider;	
import com.fasterxml.jackson.databind.ser.PropertyFilter;	
import com.jeesite.common.web.returnjson.m;	
import java.util.Arrays;	
import java.util.Collection;	
import java.util.HashMap;	
import java.util.HashSet;	
import java.util.Map;	
import java.util.Set;	
import org.hyperic.sigar.cmd.EventLogTail;	
	
@JsonFilter(value="ReturnJsonFilter")	
public class ReturnJsonFilter	
extends FilterProvider {	
    Map<Class<?>, Set<String>> includeMap;	
    Map<Class<?>, Set<String>> filterMap;	
	
    @Override	
    public BeanPropertyFilter findFilter(Object filterId) {	
        throw new UnsupportedOperationException("Acces to deprecated filter not upported");	
    }	
	
    @Override	
    public PropertyFilter findPropertyFilter(Object filterId, Object valueToFilter) {	
        return new m(this);	
    }	
	
    public void filter(Class<?> type, String[] fields) {	
        ReturnJsonFilter returnJsonFilter = this;	
        returnJsonFilter.addToMap(returnJsonFilter.filterMap, type, fields);	
    }	
	
    public void include(Class<?> type, String[] fields) {	
        ReturnJsonFilter returnJsonFilter = this;	
        returnJsonFilter.addToMap(returnJsonFilter.includeMap, type, fields);	
    }	
	
    private /* synthetic */ void addToMap(Map<Class<?>, Set<String>> map, Class<?> type, String[] fields) {	
        Set<String> a = map.get(type);	
        if (a == null) {	
            a = new HashSet<String>();	
        }	
        a.addAll(Arrays.asList(fields));	
        map.put(type, a);	
    }	
	
    public ReturnJsonFilter() {	
        ReturnJsonFilter returnJsonFilter = this;	
        this.includeMap = new HashMap();	
        returnJsonFilter.filterMap = new HashMap();	
    }	
	
    public boolean apply(Class<?> type, String name) {	
        Set<String> a = this.includeMap.get(type);	
        Set<String> a2 = this.filterMap.get(type);	
        if (a != null && a.contains(name)) {	
            return true;	
        }	
        if (a2 != null && !a2.contains(name)) {	
            return true;	
        }	
        return a == null && a2 == null;	
    }	
}	
	
