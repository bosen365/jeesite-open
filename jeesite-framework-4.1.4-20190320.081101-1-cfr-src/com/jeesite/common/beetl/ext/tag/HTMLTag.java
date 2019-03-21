/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.common.beetl.ext.tag;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.d.i;	
import com.jeesite.common.lang.StringUtils;	
import java.util.HashMap;	
import java.util.Iterator;	
import java.util.LinkedList;	
import java.util.Map;	
import java.util.Set;	
import javax.servlet.http.HttpServletRequest;	
import org.beetl.core.BodyContent;	
import org.beetl.core.ByteWriter;	
import org.beetl.core.Context;	
import org.beetl.core.GroupTemplate;	
import org.beetl.core.Template;	
import org.beetl.core.statement.Statement;	
import org.beetl.ext.tag.HTMLTagSupportWrapper;	
import org.hyperic.sigar.ProcState;	
	
public class HTMLTag	
extends HTMLTagSupportWrapper {	
    private String tagName;	
    private LinkedList<HTMLTag> tags;	
    private HttpServletRequest request;	
	
    @Override	
    protected void callHtmlTag(String path) {	
        Iterator<Map.Entry<String, Object>> iterator;	
        Template a;	
        this.tagName = StringUtils.substringAfterLast((String)this.args[0], ":");	
        HTMLTag hTMLTag = this;	
        Template template = a = this.gt.getHtmlFunctionOrTagTemplate(path, hTMLTag.ctx.getResourceId());	
        template.binding(this.ctx.globalVar);	
        template.dynamic(this.ctx.objectKeys);	
        Iterator<Map.Entry<String, Object>> iterator2 = iterator = hTMLTag.getAttrs().entrySet().iterator();	
        while (iterator2.hasNext()) {	
            Map.Entry<String, Object> a2 = iterator.next();	
            a.binding(a2.getKey(), a2.getValue());	
            iterator2 = iterator;	
        }	
        Template template2 = a;	
        template2.binding("thisTag", this);	
        BodyContent a3 = super.getBodyContent();	
        template2.binding("tagBody", a3);	
        a.renderTo(this.ctx.byteWriter);	
    }	
	
    @Override	
    public void render() {	
        this.tags = (LinkedList)this.request.getAttribute("__htmlTags");	
        if (this.tags == null) {	
            this.tags = ListUtils.newLinkedList();	
            this.request.setAttribute("__htmlTags", this.tags);	
        }	
        super.render();	
        this.tags.add(this);	
    }	
	
    @Override	
    public void init(Context ctx, Object[] args, Statement st) {	
        HTMLTag hTMLTag = this;	
        super.init(ctx, args, st);	
        hTMLTag.request = (HttpServletRequest)hTMLTag.ctx.getGlobal("request");	
    }	
	
    public HTMLTag getParent() {	
        Iterator iterator;	
        HTMLTag a = null;	
        Iterator iterator2 = iterator = this.tags.iterator();	
        while (iterator2.hasNext()) {	
            HTMLTag a2 = (HTMLTag)iterator.next();	
            if (a2 == this) {	
                return a;	
            }	
            a = a2;	
            iterator2 = iterator;	
        }	
        return null;	
    }	
	
    public HTMLTag getParentByTagName(String tagName) {	
        int a;	
        int n = a = this.tags.size() - 1;	
        while (n >= 0) {	
            HTMLTag a2 = this.tags.get(a);	
            if (a2.tagName != null && a2.tagName.equals(tagName)) {	
                return a2;	
            }	
            n = --a;	
        }	
        return null;	
    }	
	
    public Map<String, Object> getAttrs() {	
        if (this.args.length >= 2) {	
            Map a = (Map)this.args[1];	
            return a;	
        }	
        HashMap<String, Object> a = MapUtils.newHashMap();	
        return a;	
    }	
}	
	
