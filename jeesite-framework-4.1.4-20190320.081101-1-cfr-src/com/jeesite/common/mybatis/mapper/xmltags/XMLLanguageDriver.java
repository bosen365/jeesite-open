/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  org.apache.ibatis.builder.xml.XMLMapperEntityResolver	
 *  org.apache.ibatis.executor.parameter.ParameterHandler	
 *  org.apache.ibatis.mapping.BoundSql	
 *  org.apache.ibatis.mapping.MappedStatement	
 *  org.apache.ibatis.mapping.SqlSource	
 *  org.apache.ibatis.parsing.PropertyParser	
 *  org.apache.ibatis.parsing.XNode	
 *  org.apache.ibatis.parsing.XPathParser	
 *  org.apache.ibatis.scripting.LanguageDriver	
 *  org.apache.ibatis.scripting.defaults.DefaultParameterHandler	
 *  org.apache.ibatis.scripting.defaults.RawSqlSource	
 *  org.apache.ibatis.scripting.xmltags.SqlNode	
 *  org.apache.ibatis.scripting.xmltags.TextSqlNode	
 *  org.apache.ibatis.session.Configuration	
 */	
package com.jeesite.common.mybatis.mapper.xmltags;	
	
import com.jeesite.autoconfigure.sys.FileAutoConfiguration;	
import com.jeesite.common.mybatis.mapper.xmltags.DynamicSqlSource;	
import com.jeesite.common.mybatis.mapper.xmltags.XMLScriptBuilder;	
import java.util.Properties;	
import org.apache.ibatis.builder.xml.XMLMapperEntityResolver;	
import org.apache.ibatis.executor.parameter.ParameterHandler;	
import org.apache.ibatis.mapping.BoundSql;	
import org.apache.ibatis.mapping.MappedStatement;	
import org.apache.ibatis.mapping.SqlSource;	
import org.apache.ibatis.parsing.PropertyParser;	
import org.apache.ibatis.parsing.XNode;	
import org.apache.ibatis.parsing.XPathParser;	
import org.apache.ibatis.scripting.LanguageDriver;	
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;	
import org.apache.ibatis.scripting.defaults.RawSqlSource;	
import org.apache.ibatis.scripting.xmltags.SqlNode;	
import org.apache.ibatis.scripting.xmltags.TextSqlNode;	
import org.apache.ibatis.session.Configuration;	
import org.hyperic.sigar.Tcp;	
import org.xml.sax.EntityResolver;	
	
public class XMLLanguageDriver	
implements LanguageDriver {	
    public SqlSource createSqlSource(Configuration configuration, String script, Class<?> parameterType) {	
        if (script.startsWith("<script>")) {	
            XPathParser a = new XPathParser(script, false, configuration.getVariables(), (EntityResolver)new XMLMapperEntityResolver());	
            return this.createSqlSource(configuration, a.evalNode("/script"), parameterType);	
        }	
        TextSqlNode a = new TextSqlNode(script = PropertyParser.parse((String)script, (Properties)configuration.getVariables()));	
        if (a.isDynamic()) {	
            return new DynamicSqlSource(configuration, (SqlNode)a);	
        }	
        return new RawSqlSource(configuration, script, parameterType);	
    }	
	
    public SqlSource createSqlSource(Configuration configuration, XNode script, Class<?> parameterType) {	
        return new XMLScriptBuilder(configuration, script, parameterType).parseScriptNode();	
    }	
	
    public ParameterHandler createParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql) {	
        return new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);	
    }	
}	
	
