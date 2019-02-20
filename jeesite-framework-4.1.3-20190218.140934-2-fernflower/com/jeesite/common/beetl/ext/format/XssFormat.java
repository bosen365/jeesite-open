package com.jeesite.common.beetl.ext.format;	
	
import com.jeesite.common.codec.EncodeUtils;	
import org.beetl.core.Format;	
	
public class XssFormat implements Format {	
   public Object format(Object data, String pattern) {	
      return data != null && data instanceof String ? EncodeUtils.xssFilter((String)data) : null;	
   }	
}	
