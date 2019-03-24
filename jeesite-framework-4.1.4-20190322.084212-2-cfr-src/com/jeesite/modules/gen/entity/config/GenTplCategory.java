/*	
 * Decompiled with CFR 0.141.	
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
    private List<String> childTableTemplate;	
    private List<String> template;	
    public static String CATEGORY_REF = "category-ref:";	
    private static final long serialVersionUID = 1L;	
	
    public void setTemplate(List<String> template) {	
        this.template = template;	
    }	
	
    @XmlElement(name="template")	
    public List<String> getTemplate() {	
        return this.template;	
    }	
	
    @XmlElementWrapper(name="childTable")	
    @XmlElement(name="template")	
    public List<String> getChildTableTemplate() {	
        return this.childTableTemplate;	
    }	
	
    public void setChildTableTemplate(List<String> childTableTemplate) {	
        this.childTableTemplate = childTableTemplate;	
    }	
}	
	
