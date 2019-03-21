/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.common.beetl.v;	
	
import com.jeesite.common.beetl.v.l;	
import com.jeesite.common.io.ResourceUtils;	
import org.beetl.core.GroupTemplate;	
import org.beetl.core.Resource;	
import org.beetl.core.ResourceLoader;	
import org.beetl.core.resource.StringTemplateResourceLoader;	
import org.hyperic.sigar.cmd.EventLogTail;	
	
public class L	
extends StringTemplateResourceLoader {	
    @Override	
    public String getResourceId(Resource resource, String id) {	
        if (resource == null) {	
            return id;	
        }	
        return ResourceUtils.getResourceFileContent(id);	
    }	
	
    @Override	
    public Resource getResource(String template) {	
        return new l(template, this);	
    }	
	
    @Override	
    public boolean exist(String key) {	
        return true;	
    }	
	
    @Override	
    public void close() {	
    }	
	
    @Override	
    public String getInfo() {	
        return "StringTemplateResourceLoader ";	
    }	
	
    @Override	
    public boolean isModified(Resource key) {	
        return false;	
    }	
	
    @Override	
    public void init(GroupTemplate gt) {	
    }	
}	
	
