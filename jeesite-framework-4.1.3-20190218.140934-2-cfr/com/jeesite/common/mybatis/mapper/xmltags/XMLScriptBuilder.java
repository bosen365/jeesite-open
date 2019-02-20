/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.lang.ObjectUtils	
 *  org.apache.ibatis.builder.BaseBuilder	
 *  org.apache.ibatis.builder.BuilderException	
 *  org.apache.ibatis.mapping.SqlSource	
 *  org.apache.ibatis.parsing.XNode	
 *  org.apache.ibatis.scripting.defaults.RawSqlSource	
 *  org.apache.ibatis.scripting.xmltags.MixedSqlNode	
 *  org.apache.ibatis.scripting.xmltags.SqlNode	
 *  org.apache.ibatis.scripting.xmltags.StaticTextSqlNode	
 *  org.apache.ibatis.scripting.xmltags.TextSqlNode	
 *  org.apache.ibatis.session.Configuration	
 */	
package com.jeesite.common.mybatis.mapper.xmltags;	
	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.mybatis.mapper.xmltags.A;	
import com.jeesite.common.mybatis.mapper.xmltags.B;	
import com.jeesite.common.mybatis.mapper.xmltags.D;	
import com.jeesite.common.mybatis.mapper.xmltags.DynamicSqlSource;	
import com.jeesite.common.mybatis.mapper.xmltags.E;	
import com.jeesite.common.mybatis.mapper.xmltags.F;	
import com.jeesite.common.mybatis.mapper.xmltags.H;	
import com.jeesite.common.mybatis.mapper.xmltags.M;	
import com.jeesite.common.mybatis.mapper.xmltags.e;	
import com.jeesite.common.mybatis.mapper.xmltags.m;	
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
import org.hyperic.sigar.FileSystem;	
import org.hyperic.sigar.ProcState;	
import org.w3c.dom.Node;	
import org.w3c.dom.NodeList;	
	
public class XMLScriptBuilder	
extends BaseBuilder {	
    private final Class<?> parameterType;	
    private final XNode context;	
    private boolean isDynamic;	
    private final Map<String, E> nodeHandlerMap;	
	
    protected MixedSqlNode parseDynamicTags(XNode node) {	
        int a2;	
        ArrayList<SqlNode> a3 = new ArrayList<SqlNode>();	
        NodeList a4 = node.getNode().getChildNodes();	
        int n = a2 = 0;	
        while (n < a4.getLength()) {	
            Object a5;	
            String a6;	
            XNode a7 = node.newXNode(a4.item(a2));	
            if (a7.getNode().getNodeType() == 4 || a7.getNode().getNodeType() == 3) {	
                a6 = a7.getStringBody("");	
                a5 = new TextSqlNode(a6);	
                if (a5.isDynamic()) {	
                    this.isDynamic = true;	
                    a3.add((SqlNode)a5);	
                } else {	
                    a3.add((SqlNode)new StaticTextSqlNode(a6));	
                }	
            } else if (a7.getNode().getNodeType() == 1) {	
                a6 = a7.getNode().getNodeName();	
                a5 = this.nodeHandlerMap.get(a6);	
                if (a5 == null) {	
                    throw new BuilderException(new StringBuilder().insert(0, "Unknown element <").append(a6).append("> in SQL statement.").toString());	
                }	
                a5.ALLATORIxDEMO(a7, a3);	
                this.isDynamic = true;	
            }	
            n = ++a2;	
        }	
        return new MixedSqlNode(a3);	
    }	
	
    private /* synthetic */ void initNodeHandlerMap() {	
        this.nodeHandlerMap.put("bind", new A(this));	
        this.nodeHandlerMap.put("otherwise", new m(this));	
        this.nodeHandlerMap.put("when", new H(this));	
        this.nodeHandlerMap.put("choose", new B(this));	
        this.nodeHandlerMap.put("if", new H(this));	
        this.nodeHandlerMap.put("foreach", new D(this));	
        this.nodeHandlerMap.put("set", new F(this));	
        this.nodeHandlerMap.put("where", new e(this));	
        this.nodeHandlerMap.put("trim", new M(this));	
    }	
	
    static /* synthetic */ Configuration access$100(XMLScriptBuilder x0) {	
        return x0.configuration;	
    }	
	
    static /* synthetic */ Configuration access$300(XMLScriptBuilder x0) {	
        return x0.configuration;	
    }	
	
    public XMLScriptBuilder(Configuration configuration, XNode context, Class<?> parameterType) {	
        super(configuration);	
        XMLScriptBuilder xMLScriptBuilder = this;	
        xMLScriptBuilder.nodeHandlerMap = new HashMap<String, E>();	
        this.context = context;	
        this.parameterType = parameterType;	
        this.initNodeHandlerMap();	
    }	
	
    static /* synthetic */ Map access$400(XMLScriptBuilder x0) {	
        return x0.nodeHandlerMap;	
    }	
	
    public SqlSource parseScriptNode() {	
        XMLScriptBuilder xMLScriptBuilder = this;	
        MixedSqlNode a2 = xMLScriptBuilder.parseDynamicTags(xMLScriptBuilder.context);	
        DynamicSqlSource a3 = null;	
        if (xMLScriptBuilder.isDynamic) {	
            a3 = new DynamicSqlSource(this.configuration, (SqlNode)a2);	
            String a4 = this.context.getStringAttribute("weight");	
            if (a4 != null && !"".equals(a4)) {	
                a3.setWeight(ObjectUtils.toInteger((Object)a4));	
            }	
        } else {	
            a3 = new RawSqlSource(this.configuration, (SqlNode)a2, this.parameterType);	
        }	
        return a3;	
    }	
	
    static /* synthetic */ Configuration access$000(XMLScriptBuilder x0) {	
        return x0.configuration;	
    }	
	
    public XMLScriptBuilder(Configuration configuration, XNode context) {	
        this(configuration, context, null);	
    }	
	
    static /* synthetic */ Configuration access$200(XMLScriptBuilder x0) {	
        return x0.configuration;	
    }	
}	
	
