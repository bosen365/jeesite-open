/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.collect.ListUtils	
 *  com.jeesite.common.collect.MapUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  javax.servlet.http.HttpServletRequest	
 *  org.beetl.core.BodyContent	
 *  org.beetl.core.ByteWriter	
 *  org.beetl.core.Context	
 *  org.beetl.core.GroupTemplate	
 *  org.beetl.core.Template	
 *  org.beetl.core.statement.Statement	
 *  org.beetl.ext.tag.HTMLTagSupportWrapper	
 */	
package com.jeesite.common.beetl.ext.tag;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.collect.MapUtils;	
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
import org.hyperic.sigar.ProcFd;	
import org.hyperic.sigar.shell.ShellCommandInitException;	
	
public class HTMLTag	
extends HTMLTagSupportWrapper {	
    private LinkedList<HTMLTag> tags;	
    private String tagName;	
    private HttpServletRequest request;	
	
    public HTMLTag getParentByTagName(String tagName) {	
        int a2;	
        int n = a2 = this.tags.size() - 1;	
        while (n >= 0) {	
            HTMLTag a3 = this.tags.get(a2);	
            if (a3.tagName != null && a3.tagName.equals(tagName)) {	
                return a3;	
            }	
            n = --a2;	
        }	
        return null;	
    }	
	
    public void init(Context ctx, Object[] args, Statement st) {	
        HTMLTag hTMLTag = this;	
        super.init(ctx, args, st);	
        hTMLTag.request = (HttpServletRequest)hTMLTag.ctx.getGlobal("request");	
    }	
	
    protected void callHtmlTag(String path) {	
        Iterator<Map.Entry<String, Object>> iterator;	
        Template a2;	
        this.tagName = StringUtils.substringAfterLast((String)((String)this.args[0]), (String)":");	
        HTMLTag hTMLTag = this;	
        Template template = a2 = this.gt.getHtmlFunctionOrTagTemplate(path, hTMLTag.ctx.getResourceId());	
        template.binding(this.ctx.globalVar);	
        template.dynamic(this.ctx.objectKeys);	
        Iterator<Map.Entry<String, Object>> iterator2 = iterator = hTMLTag.getAttrs().entrySet().iterator();	
        while (iterator2.hasNext()) {	
            Map.Entry<String, Object> a3 = iterator.next();	
            a2.binding(a3.getKey(), a3.getValue());	
            iterator2 = iterator;	
        }	
        Template template2 = a2;	
        template2.binding("thisTag", (Object)this);	
        BodyContent a4 = super.getBodyContent();	
        template2.binding("tagBody", (Object)a4);	
        a2.renderTo(this.ctx.byteWriter);	
    }	
	
    public void render() {	
        this.tags = (LinkedList)this.request.getAttribute("__htmlTags");	
        if (this.tags == null) {	
            this.tags = ListUtils.newLinkedList();	
            this.request.setAttribute("__htmlTags", this.tags);	
        }	
        super.render();	
        this.tags.add(this);	
    }	
	
    public Map<String, Object> getAttrs() {	
        if (this.args.length >= 2) {	
            Map a2 = (Map)this.args[1];	
            return a2;	
        }	
        HashMap a3 = MapUtils.newHashMap();	
        return a3;	
    }	
	
    public HTMLTag getParent() {	
        Iterator iterator;	
        HTMLTag a2 = null;	
        Iterator iterator2 = iterator = this.tags.iterator();	
        while (iterator2.hasNext()) {	
            HTMLTag a3 = (HTMLTag)((Object)iterator.next());	
            if (a3 == this) {	
                return a2;	
            }	
            a2 = a3;	
            iterator2 = iterator;	
        }	
        return null;	
    }	
}	
	
