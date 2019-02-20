package com.jeesite.common.web.e;	
	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mapper.JsonMapper;	
import com.jeesite.common.web.http.ServletUtils;	
import java.io.IOException;	
import java.lang.reflect.Type;	
import java.util.Arrays;	
import org.hyperic.sigar.NfsClientV3;	
import org.hyperic.sigar.ProcMem;	
import org.springframework.http.HttpOutputMessage;	
import org.springframework.http.MediaType;	
import org.springframework.http.converter.HttpMessageNotWritableException;	
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;	
import org.springframework.http.converter.json.MappingJacksonValue;	
	
public class H extends MappingJackson2HttpMessageConverter {	
   protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {	
      String a;	
      if (StringUtils.isNotBlank(a = ServletUtils.getRequest().getParameter("__callback"))) {	
         MappingJacksonValue a = new MappingJacksonValue(object);	
         a.setJsonpFunction(a);	
         super.writeInternal(a, type, outputMessage);	
      } else {	
         super.writeInternal(object, type, outputMessage);	
      }	
   }	
	
   public H() {	
      MediaType[] var10001 = new MediaType[2];	
      boolean var10003 = true;	
      boolean var10006 = false;	
      var10001[0] = new MediaType("application", "json", DEFAULT_CHARSET);	
      var10001[1] = new MediaType("application", "*+json", DEFAULT_CHARSET);	
      this.setSupportedMediaTypes(Arrays.asList(var10001));	
      this.setObjectMapper(JsonMapper.getInstance());	
   }	
}	
