package com.jeesite.common.web.e;	
	
import com.jeesite.common.entity.Extend;	
import com.jeesite.common.mapper.XmlMapper;	
import com.jeesite.modules.sys.web.AdviceController;	
import java.io.IOException;	
import java.lang.reflect.Type;	
import java.util.Arrays;	
import org.springframework.http.HttpOutputMessage;	
import org.springframework.http.MediaType;	
import org.springframework.http.converter.HttpMessageNotWritableException;	
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;	
	
public class m extends MappingJackson2XmlHttpMessageConverter {	
   protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {	
      super.writeInternal(object, type, outputMessage);	
   }	
	
   public m() {	
      MediaType[] var10001 = new MediaType[3];	
      boolean var10003 = true;	
      boolean var10006 = false;	
      var10001[0] = new MediaType("application", "xml", DEFAULT_CHARSET);	
      var10001[1] = new MediaType("text", "xml", DEFAULT_CHARSET);	
      var10001[2] = new MediaType("application", "*+xml", DEFAULT_CHARSET);	
      this.setSupportedMediaTypes(Arrays.asList(var10001));	
      this.setObjectMapper(XmlMapper.getInstance());	
   }	
}	
