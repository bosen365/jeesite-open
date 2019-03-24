/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.modules.gen.entity.config;	
	
import java.io.Serializable;	
import javax.xml.bind.annotation.XmlAttribute;	
	
public class GenDict	
implements Serializable {	
    private String value;	
    private String label;	
    private String description;	
    private String type;	
    private String classes;	
    private static final long serialVersionUID = 1L;	
	
    public void setClasses(String classes) {	
        this.classes = classes;	
    }	
	
    @XmlAttribute	
    public String getLabel() {	
        return this.label;	
    }	
	
    public GenDict(String value, String label) {	
        GenDict genDict = this;	
        genDict.value = value;	
        genDict.label = label;	
    }	
	
    public String toString() {	
        return this.value;	
    }	
	
    public GenDict() {	
    }	
	
    @XmlAttribute	
    public String getType() {	
        return this.type;	
    }	
	
    @XmlAttribute	
    public String getClasses() {	
        return this.classes;	
    }	
	
    public void setLabel(String label) {	
        this.label = label;	
    }	
	
    public void setType(String type) {	
        this.type = type;	
    }	
	
    @XmlAttribute	
    public String getDescription() {	
        return this.description;	
    }	
	
    @XmlAttribute	
    public String getValue() {	
        return this.value;	
    }	
	
    public void setDescription(String description) {	
        this.description = description;	
    }	
	
    public void setValue(String value) {	
        this.value = value;	
    }	
}	
	
