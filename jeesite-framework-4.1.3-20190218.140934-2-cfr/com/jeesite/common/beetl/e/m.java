/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.io.ResourceUtils	
 *  org.beetl.core.GroupTemplate	
 *  org.beetl.core.Resource	
 *  org.beetl.core.ResourceLoader	
 *  org.beetl.core.resource.StringTemplateResourceLoader	
 */	
package com.jeesite.common.beetl.e;	
	
import com.jeesite.common.beetl.e.H;	
import com.jeesite.common.io.ResourceUtils;	
import org.beetl.core.GroupTemplate;	
import org.beetl.core.Resource;	
import org.beetl.core.ResourceLoader;	
import org.beetl.core.resource.StringTemplateResourceLoader;	
import org.hyperic.sigar.cmd.Watch;	
	
public class m	
extends StringTemplateResourceLoader {	
    public String getResourceId(Resource resource, String id) {	
        if (resource == null) {	
            return id;	
        }	
        return ResourceUtils.getResourceFileContent((String)id);	
    }	
	
    public String getInfo() {	
        return "Strin\rTemplateResourceLoader ";	
    }	
	
    public void init(GroupTemplate gt) {	
    }	
	
    public boolean isModified(Resource key) {	
        return false;	
    }	
	
    public boolean exist(String key) {	
        return true;	
    }	
	
    public Resource getResource(String template) {	
        return new H(template, (ResourceLoader)this);	
    }	
	
    public void close() {	
    }	
}	
	
