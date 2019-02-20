/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.fasterxml.jackson.annotation.JsonFilter	
 *  com.fasterxml.jackson.databind.ser.BeanPropertyFilter	
 *  com.fasterxml.jackson.databind.ser.FilterProvider	
 *  com.fasterxml.jackson.databind.ser.PropertyFilter	
 */	
package com.jeesite.common.web.returnjson;	
	
import com.fasterxml.jackson.annotation.JsonFilter;	
import com.fasterxml.jackson.databind.ser.BeanPropertyFilter;	
import com.fasterxml.jackson.databind.ser.FilterProvider;	
import com.fasterxml.jackson.databind.ser.PropertyFilter;	
import com.jeesite.common.web.returnjson.E;	
import java.util.Arrays;	
import java.util.Collection;	
import java.util.HashMap;	
import java.util.HashSet;	
import java.util.Map;	
import java.util.Set;	
	
@JsonFilter(value="ReturnJsonFilter")	
public class ReturnJsonFilter	
extends FilterProvider {	
    Map<Class<?>, Set<String>> filterMap;	
    Map<Class<?>, Set<String>> includeMap;	
	
    public BeanPropertyFilter findFilter(Object filterId) {	
        throw new UnsupportedOperationException("Access to deprecated filters not supported");	
    }	
	
    public void filter(Class<?> type, String[] fields) {	
        ReturnJsonFilter returnJsonFilter = this;	
        returnJsonFilter.addToMap(returnJsonFilter.filterMap, type, fields);	
    }	
	
    public PropertyFilter findPropertyFilter(Object filterId, Object valueToFilter) {	
        return new E(this);	
    }	
	
    private /* synthetic */ void addToMap(Map<Class<?>, Set<String>> map, Class<?> type, String[] fields) {	
        Set<String> a2 = map.get(type);	
        if (a2 == null) {	
            a2 = new HashSet<String>();	
        }	
        map.put(type, a2);	
        a2.addAll(Arrays.asList(fields));	
    }	
	
    public ReturnJsonFilter() {	
        ReturnJsonFilter returnJsonFilter = this;	
        this.includeMap = new HashMap();	
        returnJsonFilter.filterMap = new HashMap();	
    }	
	
    public void include(Class<?> type, String[] fields) {	
        ReturnJsonFilter returnJsonFilter = this;	
        returnJsonFilter.addToMap(returnJsonFilter.includeMap, type, fields);	
    }	
	
    public boolean apply(Class<?> type, String name) {	
        Set<String> a2 = this.includeMap.get(type);	
        Set<String> a3 = this.filterMap.get(type);	
        if (a2 != null && a2.contains(name)) {	
            return true;	
        }	
        if (a3 != null && !a3.contains(name)) {	
            return true;	
        }	
        return a2 == null && a3 == null;	
    }	
}	
	
