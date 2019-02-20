/*	
 * Decompiled with CFR 0.139.	
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
    private ReturnJsonFilter jsonFilter;	
    private JsonMapper jsonMapper;	
	
    public ReturnJsonSerializer() {	
        ReturnJsonSerializer returnJsonSerializer = this;	
        this.jsonMapper = new JsonMapper();	
        returnJsonSerializer.jsonFilter = new ReturnJsonFilter();	
    }	
	
    public String toJson(Object object) throws JsonProcessingException {	
        void object2;	
        ReturnJsonSerializer returnJsonSerializer = this;	
        this.jsonMapper.setFilterProvider((FilterProvider)returnJsonSerializer.jsonFilter);	
        return returnJsonSerializer.jsonMapper.writeValueAsString((Object)object2);	
    }	
	
    public void filter(JsonField json) {	
        this.filter(json.type(), json.include(), json.filter());	
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
}	
	
