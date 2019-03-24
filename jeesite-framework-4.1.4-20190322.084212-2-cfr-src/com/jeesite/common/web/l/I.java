/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.web.l;	
	
import com.fasterxml.jackson.databind.ObjectMapper;	
import com.jeesite.common.mapper.JsonMapper;	
import com.jeesite.common.mybatis.mapper.query.QueryWhere;	
import java.util.Arrays;	
import java.util.Collection;	
import java.util.LinkedHashSet;	
import java.util.Set;	
import org.hyperic.sigar.FileWatcher;	
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;	
	
public class I	
extends MappingJackson2JsonView {	
    public I() {	
        I i2 = this;	
        i2.setContentType("application/json");	
        i2.setObjectMapper(JsonMapper.getInstance());	
        String[] arrstring = new String[1];	
        arrstring[0] = "__callback";	
        this.setJsonpParameterNames(new LinkedHashSet<String>(Arrays.asList(arrstring)));	
    }	
}	
	
