package com.jeesite.common.web.j;	
	
import com.jeesite.common.mapper.JsonMapper;	
import com.jeesite.modules.file.entity.FileUploadParams;	
import java.util.Arrays;	
import java.util.LinkedHashSet;	
import org.hyperic.sigar.CpuPerc;	
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;	
	
public class D extends MappingJackson2JsonView {	
   public D() {	
      this.setContentType("application/json");	
      this.setObjectMapper(JsonMapper.getInstance());	
      String[] var10003 = new String[1];	
      boolean var10005 = true;	
      var10003[0] = "__callback";	
      this.setJsonpParameterNames(new LinkedHashSet(Arrays.asList(var10003)));	
   }	
}	
