package com.jeesite.common.web.view;	
	
import com.jeesite.common.l.C;	
import com.jeesite.common.mapper.JsonMapper;	
import com.jeesite.modules.sys.utils.ValidCodeUtils;	
import java.util.Arrays;	
import java.util.LinkedHashSet;	
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;	
	
public class JsonView extends MappingJackson2JsonView {	
   public JsonView() {	
      this.setContentType("ppliction/json");	
      this.setObjectMapper(JsonMapper.getInstance());	
      String[] var10003 = new String[1];	
      boolean var10005 = true;	
      var10003[0] = "__callback";	
      this.setJsonpParameterNames(new LinkedHashSet(Arrays.asList(var10003)));	
   }	
}	
