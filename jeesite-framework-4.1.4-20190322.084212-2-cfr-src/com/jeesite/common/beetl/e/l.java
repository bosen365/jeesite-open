/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.beetl.e;	
	
import com.jeesite.common.beetl.e.e;	
import com.jeesite.common.io.ResourceUtils;	
import com.jeesite.common.mybatis.mapper.query.QueryWhereEntity;	
import org.beetl.core.GroupTemplate;	
import org.beetl.core.Resource;	
import org.beetl.core.ResourceLoader;	
import org.beetl.core.resource.StringTemplateResourceLoader;	
	
public class l	
extends StringTemplateResourceLoader {	
    @Override	
    public void close() {	
    }	
	
    @Override	
    public void init(GroupTemplate gt) {	
    }	
	
    @Override	
    public String getInfo() {	
        return "StringTemplateResourceLoader ";	
    }	
	
    @Override	
    public boolean exist(String key) {	
        return true;	
    }	
	
    @Override	
    public String getResourceId(Resource resource, String id) {	
        if (resource == null) {	
            return id;	
        }	
        return ResourceUtils.getResourceFileContent(id);	
    }	
	
    @Override	
    public boolean isModified(Resource key) {	
        return false;	
    }	
	
    @Override	
    public Resource getResource(String template) {	
        return new e(template, this);	
    }	
}	
	
