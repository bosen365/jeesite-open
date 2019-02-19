package com.jeesite.common.mybatis.mapper.xmltags;	
	
import com.jeesite.common.j2cache.autoconfigure.J2CacheAutoConfiguration;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.mybatis.mapper.SqlMap;	
import java.util.ArrayList;	
import java.util.HashMap;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Map;	
import org.apache.ibatis.builder.BaseBuilder;	
import org.apache.ibatis.builder.BuilderException;	
import org.apache.ibatis.mapping.SqlSource;	
import org.apache.ibatis.parsing.XNode;	
import org.apache.ibatis.scripting.defaults.RawSqlSource;	
import org.apache.ibatis.scripting.xmltags.ChooseSqlNode;	
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;	
import org.apache.ibatis.scripting.xmltags.IfSqlNode;	
import org.apache.ibatis.scripting.xmltags.MixedSqlNode;	
import org.apache.ibatis.scripting.xmltags.SetSqlNode;	
import org.apache.ibatis.scripting.xmltags.SqlNode;	
import org.apache.ibatis.scripting.xmltags.StaticTextSqlNode;	
import org.apache.ibatis.scripting.xmltags.TextSqlNode;	
import org.apache.ibatis.scripting.xmltags.TrimSqlNode;	
import org.apache.ibatis.scripting.xmltags.VarDeclSqlNode;	
import org.apache.ibatis.scripting.xmltags.WhereSqlNode;	
import org.apache.ibatis.session.Configuration;	
import org.hyperic.sigar.CpuPerc;	
import org.hyperic.sigar.FileSystem;	
import org.hyperic.sigar.FileSystemMap;	
import org.hyperic.sigar.FileSystemUsage;	
import org.hyperic.sigar.ProcState;	
import org.hyperic.sigar.Tcp;	
import org.w3c.dom.NodeList;	
	
public class XMLScriptBuilder extends BaseBuilder {	
   private final Class parameterType;	
   private final XNode context;	
   private boolean isDynamic;	
   private final Map nodeHandlerMap;	
	
   protected MixedSqlNode parseDynamicTags(XNode node) {	
      List a = new ArrayList();	
      NodeList a = node.getNode().getChildNodes();	
	
      int a;	
      for(int var10000 = a = 0; var10000 < a.getLength(); var10000 = a) {	
         XNode a;	
         String a;	
         if ((a = node.newXNode(a.item(a))).getNode().getNodeType() != 4 && a.getNode().getNodeType() != 3) {	
            if (a.getNode().getNodeType() == 1) {	
               a = a.getNode().getNodeName();	
               <undefinedtype> a;	
               if ((a = (<undefinedtype>)this.nodeHandlerMap.get(a)) == null) {	
                  throw new BuilderException((new StringBuilder()).insert(0, "Unknown element <").append(a).append("> in SQL statement.").toString());	
               }	
	
               a.ALLATORIxDEMO(a, a);	
               this.isDynamic = true;	
            }	
         } else {	
            a = a.getStringBody("");	
            TextSqlNode a;	
            if ((a = new TextSqlNode(a)).isDynamic()) {	
               a.add(a);	
               this.isDynamic = true;	
            } else {	
               a.add(new StaticTextSqlNode(a));	
            }	
         }	
	
         ++a;	
      }	
	
      return new MixedSqlNode(a);	
   }	
	
   // $FF: synthetic method	
   private void initNodeHandlerMap() {	
      this.nodeHandlerMap.put("trim", new <undefinedtype>() {	
         public void ALLATORIxDEMO(XNode ax, List axx) {	
            MixedSqlNode var3 = XMLScriptBuilder.this.parseDynamicTags(ax);	
            String var4 = ax.getStringAttribute("prefix");	
            String var5 = ax.getStringAttribute("prefixOverrides");	
            String var6 = ax.getStringAttribute("suffix");	
            String var7 = ax.getStringAttribute("suffixOverrides");	
            TrimSqlNode var8 = new TrimSqlNode(XMLScriptBuilder.this.configuration, var3, var4, var5, var6, var7);	
            axx.add(var8);	
         }	
      });	
      this.nodeHandlerMap.put("where", new <undefinedtype>() {	
         public void ALLATORIxDEMO(XNode ax, List axx) {	
            MixedSqlNode var3 = XMLScriptBuilder.this.parseDynamicTags(ax);	
            WhereSqlNode var4 = new WhereSqlNode(XMLScriptBuilder.this.configuration, var3);	
            axx.add(var4);	
         }	
      });	
      this.nodeHandlerMap.put("set", new <undefinedtype>() {	
         public void ALLATORIxDEMO(XNode ax, List axx) {	
            MixedSqlNode var3 = XMLScriptBuilder.this.parseDynamicTags(ax);	
            SetSqlNode var4 = new SetSqlNode(XMLScriptBuilder.this.configuration, var3);	
            axx.add(var4);	
         }	
      });	
      this.nodeHandlerMap.put("foreach", new <undefinedtype>() {	
         public void ALLATORIxDEMO(XNode ax, List axx) {	
            MixedSqlNode var3 = XMLScriptBuilder.this.parseDynamicTags(ax);	
            String var4 = ax.getStringAttribute("collection");	
            String var5 = ax.getStringAttribute("item");	
            String var6 = ax.getStringAttribute("index");	
            String var7 = ax.getStringAttribute("open");	
            String var8 = ax.getStringAttribute("close");	
            String var9 = ax.getStringAttribute("separator");	
            ForEachSqlNode var10 = new ForEachSqlNode(XMLScriptBuilder.this.configuration, var3, var4, var6, var5, var7, var8, var9);	
            axx.add(var10);	
         }	
      });	
      this.nodeHandlerMap.put("if", new <undefinedtype>() {	
         public void ALLATORIxDEMO(XNode ax, List axx) {	
            MixedSqlNode var3 = XMLScriptBuilder.this.parseDynamicTags(ax);	
            String var4 = ax.getStringAttribute("test");	
            IfSqlNode var5 = new IfSqlNode(var3, var4);	
            axx.add(var5);	
         }	
      });	
      this.nodeHandlerMap.put("choose", new <undefinedtype>() {	
         // $FF: synthetic method	
         private SqlNode ALLATORIxDEMO(List ax) {	
            Object var2 = null;	
            if (ax.size() == 1) {	
               return (SqlNode)ax.get(0);	
            } else if (ax.size() > 1) {	
               throw new BuilderException("Too many default (otherwise) elements in choose statement.");	
            } else {	
               return (SqlNode)var2;	
            }	
         }	
	
         // $FF: synthetic method	
         private void ALLATORIxDEMO(XNode ax, List axx, List axxx) {	
            Iterator var4 = ax.getChildren().iterator();	
	
            while(var4.hasNext()) {	
               XNode var5;	
               String var6 = (var5 = (XNode)var4.next()).getNode().getNodeName();	
               <undefinedtype> var7;	
               if ((var7 = (<undefinedtype>)XMLScriptBuilder.this.nodeHandlerMap.get(var6)) instanceof <undefinedtype>) {	
                  var7.ALLATORIxDEMO(var5, axx);	
               } else if (var7 instanceof <undefinedtype>) {	
                  var7.ALLATORIxDEMO(var5, axxx);	
               }	
            }	
	
         }	
	
         public void ALLATORIxDEMO(XNode ax, List axx) {	
            ArrayList var3 = new ArrayList();	
            ArrayList var4;	
            ArrayList var10001 = var4 = new ArrayList();	
            a.ALLATORIxDEMO(ax, var3, var4);	
            SqlNode var6 = a.ALLATORIxDEMO(var10001);	
            ChooseSqlNode var5 = new ChooseSqlNode(var3, var6);	
            axx.add(var5);	
         }	
      });	
      this.nodeHandlerMap.put("when", new <undefinedtype>() {	
         public void ALLATORIxDEMO(XNode ax, List axx) {	
            MixedSqlNode var3 = XMLScriptBuilder.this.parseDynamicTags(ax);	
            String var4 = ax.getStringAttribute("test");	
            IfSqlNode var5 = new IfSqlNode(var3, var4);	
            axx.add(var5);	
         }	
      });	
      this.nodeHandlerMap.put("otherwise", new <undefinedtype>() {	
         public void ALLATORIxDEMO(XNode ax, List axx) {	
            MixedSqlNode var3 = XMLScriptBuilder.this.parseDynamicTags(ax);	
            axx.add(var3);	
         }	
      });	
      this.nodeHandlerMap.put("bind", new <undefinedtype>() {	
         public void ALLATORIxDEMO(XNode ax, List axx) {	
            String var3 = ax.getStringAttribute("name");	
            String var4 = ax.getStringAttribute("value");	
            VarDeclSqlNode var5 = new VarDeclSqlNode(var3, var4);	
            axx.add(var5);	
         }	
      });	
   }	
	
   public XMLScriptBuilder(Configuration configuration, XNode context, Class var3) {	
      super(configuration);	
      this.nodeHandlerMap = new HashMap();	
      this.context = context;	
      this.parameterType = var3;	
      this.initNodeHandlerMap();	
   }	
	
   public SqlSource parseScriptNode() {	
      MixedSqlNode a = this.parseDynamicTags(this.context);	
      SqlSource a = null;	
      if (this.isDynamic) {	
         a = new DynamicSqlSource(this.configuration, a);	
         String a;	
         if ((a = this.context.getStringAttribute("weight")) != null && !"".equals(a)) {	
            ((DynamicSqlSource)a).setWeight(ObjectUtils.toInteger(a));	
         }	
      } else {	
         a = new RawSqlSource(this.configuration, a, this.parameterType);	
      }	
	
      return (SqlSource)a;	
   }	
	
   public XMLScriptBuilder(Configuration configuration, XNode context) {	
      this(configuration, context, (Class)null);	
   }	
}	
