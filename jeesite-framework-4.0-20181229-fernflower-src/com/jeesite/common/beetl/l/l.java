package com.jeesite.common.beetl.l;	
	
import com.jeesite.common.io.ResourceUtils;	
import com.jeesite.modules.sys.utils.ConfigUtils;	
import org.beetl.core.GroupTemplate;	
import org.beetl.core.Resource;	
import org.beetl.core.resource.StringTemplateResourceLoader;	
	
public class l extends StringTemplateResourceLoader {	
   public boolean isModified(Resource key) {	
      return false;	
   }	
	
   public String getResourceId(Resource resource, String id) {	
      return resource == null ? id : ResourceUtils.getResourceFileContent(id);	
   }	
	
   public void close() {	
   }	
	
   public String getInfo() {	
      return "StringTemplateResourceLoader ";	
   }	
	
   public boolean exist(String key) {	
      return true;	
   }	
	
   public Resource getResource(String template) {	
      return new C(template, this);	
   }	
	
   public void init(GroupTemplate gt) {	
   }	
}	
