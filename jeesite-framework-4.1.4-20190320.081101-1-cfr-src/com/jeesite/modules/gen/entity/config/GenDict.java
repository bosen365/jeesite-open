/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.modules.gen.entity.config;	
	
import java.io.Serializable;	
import javax.xml.bind.annotation.XmlAttribute;	
	
public class GenDict	
implements Serializable {	
    private String description;	
    private String label;	
    private String value;	
    private static final long serialVersionUID = 1L;	
    private String type;	
    private String classes;	
	
    @XmlAttribute	
    public String getClasses() {	
        return this.classes;	
    }	
	
    @XmlAttribute	
    public String getType() {	
        return this.type;	
    }	
	
    public void setClasses(String classes) {	
        this.classes = classes;	
    }	
	
    public void setDescription(String description) {	
        this.description = description;	
    }	
	
    public void setType(String type) {	
        this.type = type;	
    }	
	
    @XmlAttribute	
    public String getValue() {	
        return this.value;	
    }	
	
    @XmlAttribute	
    public String getDescription() {	
        return this.description;	
    }	
	
    public GenDict() {	
    }	
	
    @XmlAttribute	
    public String getLabel() {	
        return this.label;	
    }	
	
    public void setLabel(String label) {	
        this.label = label;	
    }	
	
    public String toString() {	
        return this.value;	
    }	
	
    public GenDict(String value, String label) {	
        GenDict genDict = this;	
        genDict.value = value;	
        genDict.label = label;	
    }	
	
    public void setValue(String value) {	
        this.value = value;	
    }	
}	
	
