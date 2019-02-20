package com.jeesite.common.mybatis.mapper.xmltags;	
	
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
import org.apache.ibatis.scripting.xmltags.TextSqlNode;	
import org.apache.ibatis.session.Configuration;	
import org.hyperic.sigar.FileSystemUsage;	
import org.hyperic.sigar.Swap;	
	
public class XMLLanguageDriver implements LanguageDriver {	
   public ParameterHandler createParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql) {	
      return new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);	
   }	
	
   public SqlSource createSqlSource(Configuration configuration, XNode script, Class parameterType) {	
      return (new XMLScriptBuilder(configuration, script, parameterType)).parseScriptNode();	
   }	
	
   public SqlSource createSqlSource(Configuration configuration, String script, Class parameterType) {	
      if (script.startsWith("<script>")) {	
         XPathParser a = new XPathParser(script, false, configuration.getVariables(), new XMLMapperEntityResolver());	
         return this.createSqlSource(configuration, a.evalNode("/script"), parameterType);	
      } else {	
         script = PropertyParser.parse(script, configuration.getVariables());	
         TextSqlNode a;	
         return (SqlSource)((a = new TextSqlNode(script)).isDynamic() ? new DynamicSqlSource(configuration, a) : new RawSqlSource(configuration, script, parameterType));	
      }	
   }	
}	
