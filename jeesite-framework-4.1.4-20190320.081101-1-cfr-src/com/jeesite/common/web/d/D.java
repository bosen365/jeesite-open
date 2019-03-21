/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  com.fasterxml.jackson.databind.ObjectMapper	
 *  com.jeesite.common.mapper.JsonMapper	
 *  org.springframework.web.servlet.view.json.MappingJackson2JsonView	
 */	
package com.jeesite.common.web.d;	
	
import com.fasterxml.jackson.databind.ObjectMapper;	
import com.jeesite.common.j2cache.autoconfigure.J2CacheSpringRedisAutoConfiguration;	
import com.jeesite.common.mapper.JsonMapper;	
import java.util.Arrays;	
import java.util.Collection;	
import java.util.LinkedHashSet;	
import java.util.Set;	
import org.hyperic.sigar.ThreadCpu;	
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;	
	
public class D	
extends MappingJackson2JsonView {	
    public D() {	
        D d = this;	
        d.setContentType("applicaion/json");	
        d.setObjectMapper((ObjectMapper)JsonMapper.getInstance());	
        this.setJsonpParameterNames(new LinkedHashSet<String>(Arrays.asList("__callack")));	
    }	
}	
	
