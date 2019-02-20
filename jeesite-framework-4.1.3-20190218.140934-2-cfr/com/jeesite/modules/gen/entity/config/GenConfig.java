/*	
 * Decompiled with CFR 0.139.	
 */	
package com.jeesite.modules.gen.entity.config;	
	
import com.jeesite.modules.gen.entity.config.GenDict;	
import com.jeesite.modules.gen.entity.config.GenTplCategory;	
import java.io.Serializable;	
import java.util.List;	
import javax.xml.bind.annotation.XmlElement;	
import javax.xml.bind.annotation.XmlElementWrapper;	
import javax.xml.bind.annotation.XmlRootElement;	
	
@XmlRootElement(name="config")	
public class GenConfig	
implements Serializable {	
    private List<GenTplCategory> tplCategoryList;	
    private List<GenDict> fieldValidList;	
    private List<GenDict> queryTypeList;	
    private List<GenDict> gridRowColList;	
    private List<GenDict> showTypeList;	
    private List<GenDict> attrTypeList;	
    private static final long serialVersionUID = 1L;	
	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = (3 ^ 5) << 4 ^ (2 << 2 ^ 3);	
        int n4 = n2;	
        1 << 3 ^ 2;	
        int n5 = (2 ^ 5) << 3 ^ (3 ^ 5);	
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
	
    @XmlElementWrapper(name="gridRowCol")	
    @XmlElement(name="dict")	
    public List<GenDict> getGridRowColList() {	
        return this.gridRowColList;	
    }	
	
    @XmlElementWrapper(name="attrType")	
    @XmlElement(name="dict")	
    public List<GenDict> getAttrTypeList() {	
        return this.attrTypeList;	
    }	
	
    @XmlElementWrapper(name="showType")	
    @XmlElement(name="dict")	
    public List<GenDict> getShowTypeList() {	
        return this.showTypeList;	
    }	
	
    @XmlElementWrapper(name="queryType")	
    @XmlElement(name="dict")	
    public List<GenDict> getQueryTypeList() {	
        return this.queryTypeList;	
    }	
	
    public void setGridRowColList(List<GenDict> gridRowColList) {	
        this.gridRowColList = gridRowColList;	
    }	
	
    @XmlElementWrapper(name="tplCategory")	
    @XmlElement(name="category")	
    public List<GenTplCategory> getTplCategoryList() {	
        return this.tplCategoryList;	
    }	
	
    @XmlElementWrapper(name="fieldValid")	
    @XmlElement(name="dict")	
    public List<GenDict> getFieldValidList() {	
        return this.fieldValidList;	
    }	
	
    public void setFieldValidList(List<GenDict> fieldValidList) {	
        this.fieldValidList = fieldValidList;	
    }	
	
    public void setAttrTypeList(List<GenDict> attrTypeList) {	
        this.attrTypeList = attrTypeList;	
    }	
	
    public void setTplCategoryList(List<GenTplCategory> tplCategoryList) {	
        this.tplCategoryList = tplCategoryList;	
    }	
	
    public void setShowTypeList(List<GenDict> showTypeList) {	
        this.showTypeList = showTypeList;	
    }	
	
    public void setQueryTypeList(List<GenDict> queryTypeList) {	
        this.queryTypeList = queryTypeList;	
    }	
}	
	
