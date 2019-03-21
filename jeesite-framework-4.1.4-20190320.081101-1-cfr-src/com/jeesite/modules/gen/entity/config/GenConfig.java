/*	
 * Decompiled with CFR 0.140.	
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
    private List<GenDict> queryTypeList;	
    private List<GenDict> showTypeList;	
    private static final long serialVersionUID = 1L;	
    private List<GenDict> attrTypeList;	
    private List<GenDict> gridRowColList;	
    private List<GenDict> fieldValidList;	
    private List<GenTplCategory> tplCategoryList;	
	
    public void setShowTypeList(List<GenDict> showTypeList) {	
        this.showTypeList = showTypeList;	
    }	
	
    @XmlElementWrapper(name="queryType")	
    @XmlElement(name="dict")	
    public List<GenDict> getQueryTypeList() {	
        return this.queryTypeList;	
    }	
	
    @XmlElementWrapper(name="tplCategory")	
    @XmlElement(name="category")	
    public List<GenTplCategory> getTplCategoryList() {	
        return this.tplCategoryList;	
    }	
	
    public void setQueryTypeList(List<GenDict> queryTypeList) {	
        this.queryTypeList = queryTypeList;	
    }	
	
    public void setGridRowColList(List<GenDict> gridRowColList) {	
        this.gridRowColList = gridRowColList;	
    }	
	
    public void setAttrTypeList(List<GenDict> attrTypeList) {	
        this.attrTypeList = attrTypeList;	
    }	
	
    public void setFieldValidList(List<GenDict> fieldValidList) {	
        this.fieldValidList = fieldValidList;	
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
	
    public void setTplCategoryList(List<GenTplCategory> tplCategoryList) {	
        this.tplCategoryList = tplCategoryList;	
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
}	
	
