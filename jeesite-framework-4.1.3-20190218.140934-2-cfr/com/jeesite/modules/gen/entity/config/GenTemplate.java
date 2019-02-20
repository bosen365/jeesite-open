/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.collect.ListUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  javax.validation.constraints.NotBlank	
 *  org.hibernate.validator.constraints.Length	
 */	
package com.jeesite.modules.gen.entity.config;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.mapper.provider.UpdateSqlProvider;	
import com.jeesite.modules.gen.entity.config.GenConfig;	
import java.util.List;	
import javax.validation.constraints.NotBlank;	
import javax.xml.bind.annotation.XmlRootElement;	
import javax.xml.bind.annotation.XmlTransient;	
import org.hibernate.validator.constraints.Length;	
	
@XmlRootElement(name="template")	
public class GenTemplate	
extends DataEntity<GenTemplate> {	
    private String fileName;	
    private String category;	
    private String name;	
    private String filePath;	
    private String content;	
    private static final long serialVersionUID = 1L;	
	
    public void setCategoryList(List<String> categoryList) {	
        if (categoryList == null) {	
            this.category = "";	
            return;	
        }	
        this.category = new StringBuilder().insert(0, ",").append(StringUtils.join(categoryList, (String)",")).append(",").toString();	
    }	
	
    @NotBlank	
    @Length(min=0, max=200)	
    public String getName() {	
        return this.name;	
    }	
	
    public void setCategory(String category) {	
        this.category = category;	
    }	
	
    public String getFilePath() {	
        return this.filePath;	
    }	
	
    public void setName(String name) {	
        this.name = name;	
    }	
	
    public void setFilePath(String filePath) {	
        this.filePath = filePath;	
    }	
	
    public void setFileName(String fileName) {	
        this.fileName = fileName;	
    }	
	
    public String getCategory() {	
        return this.category;	
    }	
	
    @XmlTransient	
    public List<String> getCategoryList() {	
        if (this.category == null) {	
            return ListUtils.newArrayList();	
        }	
        return ListUtils.newArrayList((Object[])StringUtils.split((String)this.category, (String)","));	
    }	
	
    public String getFileName() {	
        return this.fileName;	
    }	
	
    public GenTemplate(String id) {	
        super(id);	
    }	
	
    public GenTemplate() {	
    }	
	
    public String getContent() {	
        return this.content;	
    }	
	
    public void setContent(String content) {	
        this.content = content;	
    }	
}	
	
