/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.web.e;	
	
import com.fasterxml.jackson.databind.ObjectMapper;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mapper.JsonMapper;	
import com.jeesite.common.mybatis.mapper.query.QueryWhere;	
import com.jeesite.common.web.http.ServletUtils;	
import java.io.IOException;	
import java.lang.reflect.Type;	
import java.nio.charset.Charset;	
import java.util.Arrays;	
import java.util.List;	
import org.hyperic.sigar.win32.EventLogRecord;	
import org.springframework.http.HttpOutputMessage;	
import org.springframework.http.MediaType;	
import org.springframework.http.converter.HttpMessageNotWritableException;	
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;	
import org.springframework.http.converter.json.MappingJacksonValue;	
	
public class e	
extends MappingJackson2HttpMessageConverter {	
    /*	
     * WARNING - void declaration	
     */	
    @Override	
    protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {	
        String a = ServletUtils.getRequest().getParameter("__callback");	
        if (StringUtils.isNotBlank(a)) {	
            void a2;	
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(object);	
            void v0 = a2;	
            v0.setJsonpFunction(a);	
            super.writeInternal(v0, type, outputMessage);	
            return;	
        }	
        super.writeInternal(object, type, outputMessage);	
    }	
	
    public e() {	
        e e2 = this;	
        MediaType[] arrmediaType = new MediaType[2];	
        arrmediaType[0] = new MediaType("application", "json", DEFAULT_CHARSET);	
        arrmediaType[1] = new MediaType("application", "*+json", DEFAULT_CHARSET);	
        e2.setSupportedMediaTypes(Arrays.asList(arrmediaType));	
        this.setObjectMapper(JsonMapper.getInstance());	
    }	
}	
	
