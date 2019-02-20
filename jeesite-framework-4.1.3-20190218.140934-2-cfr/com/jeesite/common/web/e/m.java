/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.fasterxml.jackson.databind.ObjectMapper	
 *  com.jeesite.common.mapper.XmlMapper	
 *  org.springframework.http.HttpOutputMessage	
 *  org.springframework.http.MediaType	
 *  org.springframework.http.converter.HttpMessageNotWritableException	
 *  org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter	
 */	
package com.jeesite.common.web.e;	
	
import com.fasterxml.jackson.databind.ObjectMapper;	
import com.jeesite.common.entity.Extend;	
import com.jeesite.common.mapper.XmlMapper;	
import com.jeesite.modules.sys.web.AdviceController;	
import java.io.IOException;	
import java.lang.reflect.Type;	
import java.nio.charset.Charset;	
import java.util.Arrays;	
import java.util.List;	
import org.springframework.http.HttpOutputMessage;	
import org.springframework.http.MediaType;	
import org.springframework.http.converter.HttpMessageNotWritableException;	
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;	
	
public class m	
extends MappingJackson2XmlHttpMessageConverter {	
    protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {	
        super.writeInternal(object, type, outputMessage);	
    }	
	
    public m() {	
        m m2 = this;	
        m2.setSupportedMediaTypes(Arrays.asList(new MediaType[]{new MediaType("application", "xml", DEFAULT_CHARSET), new MediaType("text", "xml", DEFAULT_CHARSET), new MediaType("application", "*+xml", DEFAULT_CHARSET)}));	
        this.setObjectMapper((ObjectMapper)XmlMapper.getInstance());	
    }	
}	
	
