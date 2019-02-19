package com.jeesite.common.web.returnjson;	
	
import com.fasterxml.jackson.core.JsonProcessingException;	
import com.jeesite.common.mapper.JsonMapper;	
import com.jeesite.common.web.annotation.JsonField;	
import org.apache.commons.lang3.StringUtils;	
	
public class ReturnJsonSerializer {	
   private ReturnJsonFilter jsonFilter = new ReturnJsonFilter();	
   private JsonMapper jsonMapper = new JsonMapper();	
	
   public String toJson(Object object) throws JsonProcessingException {	
      this.jsonMapper.setFilterProvider(this.jsonFilter);	
      return this.jsonMapper.writeValueAsString(object);	
   }	
	
   public void filter(JsonField json) {	
      this.filter(json.type(), json.include(), json.filter());	
   }	
	
   public void filter(Class clazz, String[] include, String[] filter) {	
      if (clazz != null) {	
         if (include.length > 1 || include.length == 1 && StringUtils.isNotBlank(include[0])) {	
            this.jsonFilter.include(clazz, include);	
         }	
	
         if (filter.length > 1 || filter.length == 1 && StringUtils.isNotBlank(filter[0])) {	
            this.jsonFilter.filter(clazz, filter);	
         }	
	
         this.jsonMapper.addMixIn(clazz, this.jsonFilter.getClass());	
      }	
   }	
}	
