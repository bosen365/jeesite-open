package com.jeesite.common.beetl.e;	
	
import com.jeesite.common.io.ResourceUtils;	
import org.beetl.core.GroupTemplate;	
import org.beetl.core.Resource;	
import org.beetl.core.resource.StringTemplateResourceLoader;	
import org.hyperic.sigar.cmd.Watch;	
	
public class m extends StringTemplateResourceLoader {	
   public String getResourceId(Resource resource, String id) {	
      return resource == null ? id : ResourceUtils.getResourceFileContent(id);	
   }	
	
   public String getInfo() {	
      return "Strin\rTemplateResourceLoader ";	
   }	
	
   public void init(GroupTemplate gt) {	
   }	
	
   public boolean isModified(Resource key) {	
      return false;	
   }	
	
   public boolean exist(String key) {	
      return true;	
   }	
	
   public Resource getResource(String template) {	
      return new H(template, this);	
   }	
	
   public void close() {	
   }	
}	
