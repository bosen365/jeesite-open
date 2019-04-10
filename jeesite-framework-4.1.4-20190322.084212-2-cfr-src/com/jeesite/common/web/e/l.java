/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.web.e;	
	
import com.fasterxml.jackson.databind.ObjectMapper;	
import com.jeesite.common.mapper.XmlMapper;	
import com.jeesite.common.web.returnjson.ReturnJsonSerializer;	
import java.io.IOException;	
import java.lang.reflect.Type;	
import java.nio.charset.Charset;	
import java.util.Arrays;	
import java.util.List;	
import org.hyperic.sigar.Mem;	
import org.springframework.http.HttpOutputMessage;	
import org.springframework.http.MediaType;	
import org.springframework.http.converter.HttpMessageNotWritableException;	
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;	
	
public class l	
extends MappingJackson2XmlHttpMessageConverter {	
    @Override	
    protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {	
        super.writeInternal(object, type, outputMessage);	
    }	
	
    public l() {	
        l l2 = this;	
        MediaType[] arrmediaType = new MediaType[3];	
        arrmediaType[0] = new MediaType("application", "xml", DEFAULT_CHARSET);	
        arrmediaType[1] = new MediaType("text", "xml", DEFAULT_CHARSET);	
        arrmediaType[2] = new MediaType("application", "*+xml", DEFAULT_CHARSET);	
        l2.setSupportedMediaTypes(Arrays.asList(arrmediaType));	
        this.setObjectMapper(XmlMapper.getInstance());	
    }	
}	
	
