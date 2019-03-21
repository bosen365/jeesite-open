/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.io.ResourceUtils	
 *  org.beetl.core.GroupTemplate	
 *  org.beetl.core.Resource	
 *  org.beetl.core.ResourceLoader	
 *  org.beetl.core.resource.StringTemplateResourceLoader	
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
    public String getResourceId(Resource resource, String id) {	
        if (resource == null) {	
            return id;	
        }	
        return ResourceUtils.getResourceFileContent((String)id);	
    }	
	
    public Resource getResource(String template) {	
        return new l(template, (ResourceLoader)this);	
    }	
	
    public boolean exist(String key) {	
        return true;	
    }	
	
    public void close() {	
    }	
	
    public String getInfo() {	
        return "StringTemplateResourceLoader ";	
    }	
	
    public boolean isModified(Resource key) {	
        return false;	
    }	
	
    public void init(GroupTemplate gt) {	
    }	
}	
	
