/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.modules.gen.entity.config;	
	
import com.jeesite.modules.gen.entity.config.GenDict;	
import java.util.List;	
import javax.xml.bind.annotation.XmlElement;	
import javax.xml.bind.annotation.XmlElementWrapper;	
import javax.xml.bind.annotation.XmlRootElement;	
	
@XmlRootElement(name="category")	
public class GenTplCategory	
extends GenDict {	
    private List<String> template;	
    private List<String> childTableTemplate;	
    private static final long serialVersionUID = 1L;	
    public static String CATEGORY_REF = "category-ref:";	
	
    public void setTemplate(List<String> template) {	
        this.template = template;	
    }	
	
    @XmlElementWrapper(name="childTable")	
    @XmlElement(name="template")	
    public List<String> getChildTableTemplate() {	
        return this.childTableTemplate;	
    }	
	
    @XmlElement(name="template")	
    public List<String> getTemplate() {	
        return this.template;	
    }	
	
    public void setChildTableTemplate(List<String> childTableTemplate) {	
        this.childTableTemplate = childTableTemplate;	
    }	
}	
	
