/*	
 * Decompiled with CFR 0.141.	
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
    private List<GenDict> showTypeList;	
    private List<GenDict> fieldValidList;	
    private List<GenDict> attrTypeList;	
    private List<GenDict> gridRowColList;	
    private static final long serialVersionUID = 1L;	
    private List<GenTplCategory> tplCategoryList;	
    private List<GenDict> queryTypeList;	
	
    @XmlElementWrapper(name="showType")	
    @XmlElement(name="dict")	
    public List<GenDict> getShowTypeList() {	
        return this.showTypeList;	
    }	
	
    public void setTplCategoryList(List<GenTplCategory> tplCategoryList) {	
        this.tplCategoryList = tplCategoryList;	
    }	
	
    @XmlElementWrapper(name="gridRowCol")	
    @XmlElement(name="dict")	
    public List<GenDict> getGridRowColList() {	
        return this.gridRowColList;	
    }	
	
    @XmlElementWrapper(name="fieldValid")	
    @XmlElement(name="dict")	
    public List<GenDict> getFieldValidList() {	
        return this.fieldValidList;	
    }	
	
    @XmlElementWrapper(name="attrType")	
    @XmlElement(name="dict")	
    public List<GenDict> getAttrTypeList() {	
        return this.attrTypeList;	
    }	
	
    public void setGridRowColList(List<GenDict> gridRowColList) {	
        this.gridRowColList = gridRowColList;	
    }	
	
    @XmlElementWrapper(name="queryType")	
    @XmlElement(name="dict")	
    public List<GenDict> getQueryTypeList() {	
        return this.queryTypeList;	
    }	
	
    public void setAttrTypeList(List<GenDict> attrTypeList) {	
        this.attrTypeList = attrTypeList;	
    }	
	
    public void setQueryTypeList(List<GenDict> queryTypeList) {	
        this.queryTypeList = queryTypeList;	
    }	
	
    @XmlElementWrapper(name="tplCategory")	
    @XmlElement(name="category")	
    public List<GenTplCategory> getTplCategoryList() {	
        return this.tplCategoryList;	
    }	
	
    public void setShowTypeList(List<GenDict> showTypeList) {	
        this.showTypeList = showTypeList;	
    }	
	
    public void setFieldValidList(List<GenDict> fieldValidList) {	
        this.fieldValidList = fieldValidList;	
    }	
}	
	
