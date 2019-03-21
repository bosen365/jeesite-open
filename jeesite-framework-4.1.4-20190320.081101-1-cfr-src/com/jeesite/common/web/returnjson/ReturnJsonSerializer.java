/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  com.fasterxml.jackson.core.JsonProcessingException	
 *  com.fasterxml.jackson.databind.ObjectMapper	
 *  com.fasterxml.jackson.databind.ser.FilterProvider	
 *  com.jeesite.common.mapper.JsonMapper	
 *  org.apache.commons.lang3.StringUtils	
 */	
package com.jeesite.common.web.returnjson;	
	
import com.fasterxml.jackson.core.JsonProcessingException;	
import com.fasterxml.jackson.databind.ObjectMapper;	
import com.fasterxml.jackson.databind.ser.FilterProvider;	
import com.jeesite.common.mapper.JsonMapper;	
import com.jeesite.common.web.annotation.JsonField;	
import com.jeesite.common.web.returnjson.ReturnJsonFilter;	
import org.apache.commons.lang3.StringUtils;	
	
public class ReturnJsonSerializer {	
    private JsonMapper jsonMapper;	
    private ReturnJsonFilter jsonFilter;	
	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = (3 ^ 5) << 3 ^ 5;	
        int n4 = n2;	
        (3 ^ 5) << 3 ^ 2;	
        int n5 = 5 << 4 ^ (3 << 2 ^ 3);	
        while (n4 >= 0) {	
            int n6 = n2--;	
            arrc[n6] = (char)(s.charAt(n6) ^ n5);	
            if (n2 < 0) break;	
            int n7 = n2--;	
            arrc[n7] = (char)(s.charAt(n7) ^ n3);	
            n4 = n2;	
        }	
        return new String(arrc);	
    }	
	
    public String toJson(Object object) throws JsonProcessingException {	
        void object2;	
        ReturnJsonSerializer returnJsonSerializer = this;	
        this.jsonMapper.setFilterProvider((FilterProvider)returnJsonSerializer.jsonFilter);	
        return returnJsonSerializer.jsonMapper.writeValueAsString((Object)object2);	
    }	
	
    public ReturnJsonSerializer() {	
        ReturnJsonSerializer returnJsonSerializer = this;	
        this.jsonMapper = new JsonMapper();	
        returnJsonSerializer.jsonFilter = new ReturnJsonFilter();	
    }	
	
    public void filter(Class<?> clazz, String[] include, String[] filter) {	
        if (clazz == null) {	
            return;	
        }	
        if (include.length > 1 || include.length == 1 && StringUtils.isNotBlank((CharSequence)include[0])) {	
            this.jsonFilter.include(clazz, include);	
        }	
        if (filter.length > 1 || filter.length == 1 && StringUtils.isNotBlank((CharSequence)filter[0])) {	
            this.jsonFilter.filter(clazz, filter);	
        }	
        this.jsonMapper.addMixIn(clazz, ((Object)((Object)this.jsonFilter)).getClass());	
    }	
	
    public void filter(JsonField json) {	
        this.filter(json.type(), json.include(), json.filter());	
    }	
}	
	
