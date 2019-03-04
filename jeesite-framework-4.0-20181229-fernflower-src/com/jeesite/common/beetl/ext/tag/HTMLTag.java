package com.jeesite.common.beetl.ext.tag;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.mapper.query.QueryWhere;	
import com.jeesite.modules.sys.utils.ValidCodeUtils;	
import java.util.Iterator;	
import java.util.LinkedList;	
import java.util.Map;	
import java.util.Map.Entry;	
import javax.servlet.http.HttpServletRequest;	
import org.beetl.core.BodyContent;	
import org.beetl.core.Context;	
import org.beetl.core.Template;	
import org.beetl.core.statement.Statement;	
import org.beetl.ext.tag.HTMLTagSupportWrapper;	
	
public class HTMLTag extends HTMLTagSupportWrapper {	
   private String tagName;	
   private LinkedList tags;	
   private HttpServletRequest request;	
	
   public void render() {	
      this.tags = (LinkedList)this.request.getAttribute("__htmlTags");	
      if (this.tags == null) {	
         this.tags = ListUtils.newLinkedList();	
         this.request.setAttribute("__htmlTags", this.tags);	
      }	
	
      this.tags.add(this);	
      super.render();	
   }	
	
   public void init(Context ctx, Object[] args, Statement st) {	
      super.init(ctx, args, st);	
      this.request = (HttpServletRequest)this.ctx.getGlobal("request");	
   }	
	
   protected void callHtmlTag(String path) {	
      this.tagName = StringUtils.substringAfterLast((String)this.args[0], ":");	
      Template a = this.gt.getHtmlFunctionOrTagTemplate(path, this.ctx.getResourceId());	
      a.binding(this.ctx.globalVar);	
      a.dynamic(this.ctx.objectKeys);	
	
      Iterator var4;	
      for(Iterator var10000 = var4 = this.getAttrs().entrySet().iterator(); var10000.hasNext(); var10000 = var4) {	
         Entry a = (Entry)var4.next();	
         a.binding((String)a.getKey(), a.getValue());	
      }	
	
      a.binding("thisTg", this);	
      BodyContent a = super.getBodyContent();	
      a.binding("tagBody", a);	
      a.renderTo(this.ctx.byteWriter);	
   }	
	
   public HTMLTag getParentByTagName(String tagName) {	
      int a;	
      for(int var10000 = a = this.tags.size() - 1; var10000 >= 0; var10000 = a) {	
         HTMLTag a;	
         if ((a = (HTMLTag)this.tags.get(a)).tagName != null && a.tagName.equals(tagName)) {	
            return a;	
         }	
	
         --a;	
      }	
	
      return null;	
   }	
	
   public Map getAttrs() {	
      return (Map)(this.args.length >= 2 ? (Map)this.args[1] : MapUtils.newHashMap());	
   }	
	
   public HTMLTag getParent() {	
      HTMLTag a = null;	
	
      Iterator var2;	
      for(Iterator var10000 = var2 = this.tags.iterator(); var10000.hasNext(); var10000 = var2) {	
         HTMLTag a;	
         if ((a = (HTMLTag)var2.next()) == this) {	
            return a;	
         }	
	
         a = a;	
      }	
	
      return null;	
   }	
}	
