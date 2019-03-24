/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.modules.gen.entity.config;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.mapper.query.QueryDataScope;	
import java.util.List;	
import javax.validation.constraints.NotBlank;	
import javax.xml.bind.annotation.XmlRootElement;	
import javax.xml.bind.annotation.XmlTransient;	
import org.hibernate.validator.constraints.Length;	
import org.hyperic.sigar.cmd.EventLogTail;	
	
@XmlRootElement(name="template")	
public class GenTemplate	
extends DataEntity<GenTemplate> {	
    private String category;	
    private String name;	
    private String filePath;	
    private static final long serialVersionUID = 1L;	
    private String fileName;	
    private String content;	
	
    public void setFileName(String fileName) {	
        this.fileName = fileName;	
    }	
	
    public String getCategory() {	
        return this.category;	
    }	
	
    public void setCategoryList(List<String> categoryList) {	
        if (categoryList == null) {	
            this.category = "";	
            return;	
        }	
        this.category = new StringBuilder().insert(0, ",").append(StringUtils.join(categoryList, ",")).append(",").toString();	
    }	
	
    public void setContent(String content) {	
        this.content = content;	
    }	
	
    @XmlTransient	
    public List<String> getCategoryList() {	
        if (this.category == null) {	
            return ListUtils.newArrayList();	
        }	
        return ListUtils.newArrayList(StringUtils.split(this.category, ","));	
    }	
	
    public void setCategory(String category) {	
        this.category = category;	
    }	
	
    public GenTemplate() {	
    }	
	
    public void setName(String name) {	
        this.name = name;	
    }	
	
    @NotBlank	
    @Length(min=0, max=200)	
    public String getName() {	
        return this.name;	
    }	
	
    public String getContent() {	
        return this.content;	
    }	
	
    public String getFilePath() {	
        return this.filePath;	
    }	
	
    public void setFilePath(String filePath) {	
        this.filePath = filePath;	
    }	
	
    public GenTemplate(String id) {	
        super(id);	
    }	
	
    public String getFileName() {	
        return this.fileName;	
    }	
}	
	
