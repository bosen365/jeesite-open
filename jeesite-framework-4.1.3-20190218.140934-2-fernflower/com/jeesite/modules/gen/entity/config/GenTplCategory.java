package com.jeesite.modules.gen.entity.config;	
	
import java.util.List;	
import javax.xml.bind.annotation.XmlElement;	
import javax.xml.bind.annotation.XmlElementWrapper;	
import javax.xml.bind.annotation.XmlRootElement;	
	
@XmlRootElement(	
   name = "category"	
)	
public class GenTplCategory extends GenDict {	
   private List template;	
   public static String CATEGORY_REF = "category-ref:";	
   private static final long serialVersionUID = 1L;	
   private List childTableTemplate;	
	
   @XmlElementWrapper(	
      name = "childTable"	
   )	
   @XmlElement(	
      name = "template"	
   )	
   public List getChildTableTemplate() {	
      return this.childTableTemplate;	
   }	
	
   public void setChildTableTemplate(List childTableTemplate) {	
      this.childTableTemplate = childTableTemplate;	
   }	
	
   public void setTemplate(List template) {	
      this.template = template;	
   }	
	
   @XmlElement(	
      name = "template"	
   )	
   public List getTemplate() {	
      return this.template;	
   }	
}	
