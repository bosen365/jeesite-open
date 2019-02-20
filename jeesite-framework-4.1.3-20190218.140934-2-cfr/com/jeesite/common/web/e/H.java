/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.fasterxml.jackson.databind.ObjectMapper	
 *  com.jeesite.common.lang.StringUtils	
 *  com.jeesite.common.mapper.JsonMapper	
 *  com.jeesite.common.web.http.ServletUtils	
 *  org.springframework.http.HttpOutputMessage	
 *  org.springframework.http.MediaType	
 *  org.springframework.http.converter.HttpMessageNotWritableException	
 *  org.springframework.http.converter.json.MappingJackson2HttpMessageConverter	
 *  org.springframework.http.converter.json.MappingJacksonValue	
 */	
package com.jeesite.common.web.e;	
	
import com.fasterxml.jackson.databind.ObjectMapper;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mapper.JsonMapper;	
import com.jeesite.common.web.http.ServletUtils;	
import java.io.IOException;	
import java.lang.reflect.Type;	
import java.nio.charset.Charset;	
import java.util.Arrays;	
import java.util.List;	
import org.hyperic.sigar.NfsClientV3;	
import org.hyperic.sigar.ProcMem;	
import org.springframework.http.HttpOutputMessage;	
import org.springframework.http.MediaType;	
import org.springframework.http.converter.HttpMessageNotWritableException;	
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;	
import org.springframework.http.converter.json.MappingJacksonValue;	
	
public class H	
extends MappingJackson2HttpMessageConverter {	
    protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {	
        String a2 = ServletUtils.getRequest().getParameter("__callback");	
        if (StringUtils.isNotBlank((CharSequence)a2)) {	
            void a3;	
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(object);	
            void v0 = a3;	
            v0.setJsonpFunction(a2);	
            super.writeInternal((Object)v0, type, outputMessage);	
            return;	
        }	
        super.writeInternal(object, type, outputMessage);	
    }	
	
    public H() {	
        H h2 = this;	
        h2.setSupportedMediaTypes(Arrays.asList(new MediaType[]{new MediaType("application", "json", DEFAULT_CHARSET), new MediaType("application", "*+json", DEFAULT_CHARSET)}));	
        this.setObjectMapper((ObjectMapper)JsonMapper.getInstance());	
    }	
}	
	
