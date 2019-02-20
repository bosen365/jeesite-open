/*	
 * Decompiled with CFR 0.139.	
 */	
package com.jeesite.modules.gen.entity.config;	
	
import java.io.Serializable;	
import javax.xml.bind.annotation.XmlAttribute;	
	
public class GenDict	
implements Serializable {	
    private String description;	
    private static final long serialVersionUID = 1L;	
    private String type;	
    private String value;	
    private String label;	
    private String classes;	
	
    @XmlAttribute	
    public String getLabel() {	
        return this.label;	
    }	
	
    public GenDict(String value, String label) {	
        GenDict genDict = this;	
        genDict.value = value;	
        genDict.label = label;	
    }	
	
    public void setDescription(String description) {	
        this.description = description;	
    }	
	
    public GenDict() {	
    }	
	
    public String toString() {	
        return this.value;	
    }	
	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = (2 ^ 5) << 3 ^ (2 ^ 5);	
        int n4 = n2;	
        (3 ^ 5) << 4 ^ (2 ^ 5);	
        int n5 = 4 << 4 ^ 5 << 1;	
        while (n4 >= 0) {	
            int n6 = n2--;	
            arrc[n6] = (char)(s.charAt(n6) ^ n5);	
            if (n2 < 0) break;	
            int n7 = n2--;	
            arrc[n7] = (char)(s.charAt(n7) ^ n3);	
            n4 = n2;	
        }	
        return new String(arrc);	
    }	
	
    public void setValue(String value) {	
        this.value = value;	
    }	
	
    @XmlAttribute	
    public String getType() {	
        return this.type;	
    }	
	
    @XmlAttribute	
    public String getDescription() {	
        return this.description;	
    }	
	
    public void setType(String type) {	
        this.type = type;	
    }	
	
    public void setClasses(String classes) {	
        this.classes = classes;	
    }	
	
    @XmlAttribute	
    public String getValue() {	
        return this.value;	
    }	
	
    public void setLabel(String label) {	
        this.label = label;	
    }	
	
    @XmlAttribute	
    public String getClasses() {	
        return this.classes;	
    }	
}	
	
