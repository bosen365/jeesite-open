/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.fasterxml.jackson.databind.ObjectMapper	
 *  com.jeesite.common.mapper.JsonMapper	
 *  org.springframework.web.servlet.view.json.MappingJackson2JsonView	
 */	
package com.jeesite.common.web.j;	
	
import com.fasterxml.jackson.databind.ObjectMapper;	
import com.jeesite.common.mapper.JsonMapper;	
import com.jeesite.modules.file.entity.FileUploadParams;	
import java.util.Arrays;	
import java.util.Collection;	
import java.util.LinkedHashSet;	
import java.util.Set;	
import org.hyperic.sigar.CpuPerc;	
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;	
	
public class D	
extends MappingJackson2JsonView {	
    public D() {	
        D d = this;	
        d.setContentType("application/json");	
        d.setObjectMapper((ObjectMapper)JsonMapper.getInstance());	
        this.setJsonpParameterNames(new LinkedHashSet<String>(Arrays.asList("__callback")));	
    }	
}	
	
