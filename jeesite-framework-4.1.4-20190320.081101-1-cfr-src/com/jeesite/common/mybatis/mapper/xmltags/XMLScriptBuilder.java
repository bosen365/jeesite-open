/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.common.mybatis.mapper.xmltags;	
	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.mybatis.mapper.xmltags.C;	
import com.jeesite.common.mybatis.mapper.xmltags.D;	
import com.jeesite.common.mybatis.mapper.xmltags.DynamicSqlSource;	
import com.jeesite.common.mybatis.mapper.xmltags.L;	
import com.jeesite.common.mybatis.mapper.xmltags.b;	
import com.jeesite.common.mybatis.mapper.xmltags.c;	
import com.jeesite.common.mybatis.mapper.xmltags.e;	
import com.jeesite.common.mybatis.mapper.xmltags.i;	
import com.jeesite.common.mybatis.mapper.xmltags.l;	
import com.jeesite.common.mybatis.mapper.xmltags.m;	
import com.jeesite.modules.sys.utils.ConfigUtils;	
import java.util.ArrayList;	
import java.util.HashMap;	
import java.util.List;	
import java.util.Map;	
import org.apache.ibatis.builder.BaseBuilder;	
import org.apache.ibatis.builder.BuilderException;	
import org.apache.ibatis.mapping.SqlSource;	
import org.apache.ibatis.parsing.XNode;	
import org.apache.ibatis.scripting.defaults.RawSqlSource;	
import org.apache.ibatis.scripting.xmltags.MixedSqlNode;	
import org.apache.ibatis.scripting.xmltags.SqlNode;	
import org.apache.ibatis.scripting.xmltags.StaticTextSqlNode;	
import org.apache.ibatis.scripting.xmltags.TextSqlNode;	
import org.apache.ibatis.session.Configuration;	
import org.w3c.dom.Node;	
import org.w3c.dom.NodeList;	
	
public class XMLScriptBuilder	
extends BaseBuilder {	
    private final XNode context;	
    private boolean isDynamic;	
    private final Map<String, i> nodeHandlerMap;	
    private final Class<?> parameterType;	
	
    protected MixedSqlNode parseDynamicTags(XNode node) {	
        int a;	
        ArrayList<SqlNode> a2 = new ArrayList<SqlNode>();	
        NodeList a3 = node.getNode().getChildNodes();	
        int n = a = 0;	
        while (n < a3.getLength()) {	
            Object a4;	
            String a5;	
            XNode a6 = node.newXNode(a3.item(a));	
            if (a6.getNode().getNodeType() == 4 || a6.getNode().getNodeType() == 3) {	
                a5 = a6.getStringBody("");	
                a4 = new TextSqlNode(a5);	
                if (((TextSqlNode)a4).isDynamic()) {	
                    this.isDynamic = true;	
                    a2.add((SqlNode)a4);	
                } else {	
                    a2.add(new StaticTextSqlNode(a5));	
                }	
            } else if (a6.getNode().getNodeType() == 1) {	
                a5 = a6.getNode().getNodeName();	
                a4 = this.nodeHandlerMap.get(a5);	
                if (a4 == null) {	
                    throw new BuilderException(new StringBuilder().insert(0, com.jeesite.common.d.c.ALLATORIxDEMO ("\t6763/2x949596(x`")).append(a5).append("> in SQL statement.").toString());	
                }	
                a4.ALLATORIxDEMO(a6, a2);	
                this.isDynamic = true;	
            }	
            n = ++a;	
        }	
        return new MixedSqlNode(a2);	
    }	
	
    static /* synthetic */ Configuration access$100(XMLScriptBuilder x0) {	
        return x0.configuration;	
    }	
	
    static /* synthetic */ Configuration access$300(XMLScriptBuilder x0) {	
        return x0.configuration;	
    }	
	
    static /* synthetic */ Configuration access$200(XMLScriptBuilder x0) {	
        return x0.configuration;	
    }	
	
    private /* synthetic */ void initNodeHandlerMap() {	
        this.nodeHandlerMap.put(com.jeesite.common.d.c.ALLATORIxDEMO (":568"), new e(this));	
        this.nodeHandlerMap.put("otherwis", new L(this));	
        this.nodeHandlerMap.put(com.jeesite.common.d.c.ALLATORIxDEMO ("/4=2"), new l(this));	
        this.nodeHandlerMap.put("choos", new b(this));	
        this.nodeHandlerMap.put(com.jeesite.common.d.c.ALLATORIxDEMO ("1:"), new l(this));	
        this.nodeHandlerMap.put("foreach", new D(this));	
        this.nodeHandlerMap.put(com.jeesite.common.d.c.ALLATORIxDEMO ("/=("), new m(this));	
        this.nodeHandlerMap.put("whr", new c(this));	
        this.nodeHandlerMap.put(com.jeesite.common.d.c.ALLATORIxDEMO (",.11"), new C(this));	
    }	
	
    public XMLScriptBuilder(Configuration configuration, XNode context, Class<?> parameterType) {	
        super(configuration);	
        XMLScriptBuilder xMLScriptBuilder = this;	
        xMLScriptBuilder.nodeHandlerMap = new HashMap<String, i>();	
        this.context = context;	
        this.parameterType = parameterType;	
        this.initNodeHandlerMap();	
    }	
	
    public XMLScriptBuilder(Configuration configuration, XNode context) {	
        this(configuration, context, null);	
    }	
	
    static /* synthetic */ Configuration access$000(XMLScriptBuilder x0) {	
        return x0.configuration;	
    }	
	
    static /* synthetic */ Map access$400(XMLScriptBuilder x0) {	
        return x0.nodeHandlerMap;	
    }	
	
    public SqlSource parseScriptNode() {	
        XMLScriptBuilder xMLScriptBuilder = this;	
        MixedSqlNode a = xMLScriptBuilder.parseDynamicTags(xMLScriptBuilder.context);	
        SqlSource a2 = null;	
        if (xMLScriptBuilder.isDynamic) {	
            a2 = new DynamicSqlSource(this.configuration, a);	
            String a3 = this.context.getStringAttribute("wight");	
            if (a3 != null && !"".equals(a3)) {	
                ((DynamicSqlSource)a2).setWeight(ObjectUtils.toInteger(a3));	
            }	
        } else {	
            a2 = new RawSqlSource(this.configuration, a, this.parameterType);	
        }	
        return a2;	
    }	
}	
	
