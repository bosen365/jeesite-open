package com.jeesite.common.mybatis.mapper.xml;	
	
import com.jeesite.common.cache.JedisUtils;	
import java.io.InputStream;	
import java.io.Reader;	
import java.util.ArrayList;	
import java.util.Collection;	
import java.util.Collections;	
import java.util.HashMap;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Map;	
import java.util.Properties;	
import org.apache.ibatis.builder.BaseBuilder;	
import org.apache.ibatis.builder.BuilderException;	
import org.apache.ibatis.builder.CacheRefResolver;	
import org.apache.ibatis.builder.IncompleteElementException;	
import org.apache.ibatis.builder.MapperBuilderAssistant;	
import org.apache.ibatis.builder.ResultMapResolver;	
import org.apache.ibatis.builder.xml.XMLStatementBuilder;	
import org.apache.ibatis.executor.ErrorContext;	
import org.apache.ibatis.io.Resources;	
import org.apache.ibatis.mapping.Discriminator;	
import org.apache.ibatis.mapping.ParameterMapping;	
import org.apache.ibatis.mapping.ParameterMode;	
import org.apache.ibatis.mapping.ResultFlag;	
import org.apache.ibatis.mapping.ResultMap;	
import org.apache.ibatis.mapping.ResultMapping;	
import org.apache.ibatis.parsing.XNode;	
import org.apache.ibatis.parsing.XPathParser;	
import org.apache.ibatis.session.Configuration;	
import org.apache.ibatis.type.JdbcType;	
import org.hyperic.sigar.ProcMem;	
	
public class XMLMapperBuilder extends BaseBuilder {	
   private final String resource;	
   private final XPathParser parser;	
   private final MapperBuilderAssistant builderAssistant;	
   private final Map sqlFragments;	
	
   // $FF: synthetic method	
   private ResultMap resultMapElement(XNode resultMapNode) throws Exception {	
      return this.resultMapElement(resultMapNode, Collections.emptyList());	
   }	
	
   // $FF: synthetic method	
   private void sqlElement(List list) throws Exception {	
      if (this.configuration.getDatabaseId() != null) {	
         this.sqlElement(list, this.configuration.getDatabaseId());	
      }	
	
      this.sqlElement(list, (String)null);	
   }	
	
   // $FF: synthetic method	
   private void processConstructorElement(XNode resultChild, Class resultType, List resultMappings) throws Exception {	
      Iterator var5;	
      for(Iterator var10000 = var5 = resultChild.getChildren().iterator(); var10000.hasNext(); var10000 = var5) {	
         XNode a = (XNode)var5.next();	
         ArrayList a = new ArrayList();	
         a.add(ResultFlag.CONSTRUCTOR);	
         if ("idArg".equals(a.getName())) {	
            a.add(ResultFlag.ID);	
         }	
	
         resultMappings.add(this.buildResultMappingFromContext(a, resultType, a));	
      }	
	
   }	
	
   /** @deprecated */	
   @Deprecated	
   public XMLMapperBuilder(Reader reader, Configuration configuration, String resource, Map sqlFragments) {	
      this(new XPathParser(reader, true, configuration.getVariables(), new XMLMapperEntityResolver()), configuration, resource, sqlFragments);	
   }	
	
   // $FF: synthetic method	
   private void parsePendingStatements() {	
      Collection a;	
      synchronized(a = this.configuration.getIncompleteStatements()) {	
         Iterator a;	
         Iterator var10000 = a = a.iterator();	
	
         while(var10000.hasNext()) {	
            try {	
               ((XMLStatementBuilder)a.next()).parseStatementNode();	
               a.remove();	
            } catch (IncompleteElementException var6) {	
               var10000 = a;	
               continue;	
            }	
	
            var10000 = a;	
         }	
	
      }	
   }	
	
   // $FF: synthetic method	
   private void parsePendingCacheRefs() {	
      Collection a;	
      synchronized(a = this.configuration.getIncompleteCacheRefs()) {	
         Iterator a;	
         Iterator var10000 = a = a.iterator();	
	
         while(var10000.hasNext()) {	
            try {	
               ((CacheRefResolver)a.next()).resolveCacheRef();	
               a.remove();	
            } catch (IncompleteElementException var6) {	
               var10000 = a;	
               continue;	
            }	
	
            var10000 = a;	
         }	
	
      }	
   }	
	
   // $FF: synthetic method	
   private boolean databaseIdMatchesCurrent(String id, String databaseId, String requiredDatabaseId) {	
      if (requiredDatabaseId != null) {	
         if (!requiredDatabaseId.equals(databaseId)) {	
            return false;	
         }	
      } else {	
         if (databaseId != null) {	
            return false;	
         }	
	
         if (this.sqlFragments.containsKey(id) && ((XNode)this.sqlFragments.get(id)).getStringAttribute("databaseId") != null) {	
            return false;	
         }	
      }	
	
      return true;	
   }	
	
   // $FF: synthetic method	
   private void parsePendingResultMaps() {	
      Collection a;	
      synchronized(a = this.configuration.getIncompleteResultMaps()) {	
         Iterator a;	
         Iterator var10000 = a = a.iterator();	
	
         while(var10000.hasNext()) {	
            try {	
               ((ResultMapResolver)a.next()).resolve();	
               a.remove();	
            } catch (IncompleteElementException var6) {	
               var10000 = a;	
               continue;	
            }	
	
            var10000 = a;	
         }	
	
      }	
   }	
	
   // $FF: synthetic method	
   private void configurationElement(XNode context) {	
      try {	
         String a;	
         if ((a = context.getStringAttribute("namespace")) != null && !a.equals("")) {	
            this.builderAssistant.setCurrentNamespace(a);	
            this.cacheRefElement(context.evalNode("cache-ref"));	
            this.cacheElement(context.evalNode("cache"));	
            this.parameterMapElement(context.evalNodes("/mapper/parameterMap"));	
            this.resultMapElements(context.evalNodes("/mapper/resultMap"));	
            this.sqlElement(context.evalNodes("/mapper/sql"));	
            this.buildStatementFromContext(context.evalNodes("select|insert|update|delete"));	
         } else {	
            throw new BuilderException("Mapper's namespace cannot be empty");	
         }	
      } catch (Exception var3) {	
         throw new BuilderException((new StringBuilder()).insert(0, "Error parsing Mapper XML. The XML location is '").append(this.resource).append("'. Cause: ").append(var3).toString(), var3);	
      }	
   }	
	
   // $FF: synthetic method	
   private ResultMap resultMapElement(XNode resultMapNode, List additionalResultMappings) throws Exception {	
      ErrorContext.instance().activity((new StringBuilder()).insert(0, "processing ").append(resultMapNode.getValueBasedIdentifier()).toString());	
      String a = resultMapNode.getStringAttribute("id", resultMapNode.getValueBasedIdentifier());	
      String a = resultMapNode.getStringAttribute("type", resultMapNode.getStringAttribute("ofType", resultMapNode.getStringAttribute("resultType", resultMapNode.getStringAttribute("javaType"))));	
      String a = resultMapNode.getStringAttribute("extends");	
      Boolean a = resultMapNode.getBooleanAttribute("autoMapping");	
      Class a = this.resolveClass(a);	
      Discriminator a = null;	
      ArrayList a;	
      (a = new ArrayList()).addAll(additionalResultMappings);	
      Iterator var11 = resultMapNode.getChildren().iterator();	
	
      while(var11.hasNext()) {	
         XNode a = (XNode)var11.next();	
         if ("constructor".equals(a.getName())) {	
            this.processConstructorElement(a, a, a);	
         } else if ("discriminator".equals(a.getName())) {	
            a = this.processDiscriminatorElement(a, a, a);	
         } else {	
            List a = new ArrayList();	
            if ("id".equals(a.getName())) {	
               a.add(ResultFlag.ID);	
            }	
	
            a.add(this.buildResultMappingFromContext(a, a, a));	
         }	
      }	
	
      ResultMapResolver a = new ResultMapResolver(this.builderAssistant, a, a, a, a, a, a);	
	
      try {	
         return a.resolve();	
      } catch (IncompleteElementException var14) {	
         this.configuration.addIncompleteResultMap(a);	
         throw var14;	
      }	
   }	
	
   public void parse() {	
      if (!this.configuration.isResourceLoaded(this.resource)) {	
         this.configurationElement(this.parser.evalNode("/mapper"));	
         this.configuration.addLoadedResource(this.resource);	
         this.bindMapperForNamespace();	
      }	
	
      this.parsePendingResultMaps();	
      this.parsePendingCacheRefs();	
      this.parsePendingStatements();	
   }	
	
   // $FF: synthetic method	
   private void resultMapElements(List list) throws Exception {	
      Iterator var2 = list.iterator();	
	
      while(var2.hasNext()) {	
         XNode a = (XNode)var2.next();	
	
         try {	
            this.resultMapElement(a);	
         } catch (IncompleteElementException var5) {	
         }	
      }	
	
   }	
	
   public XMLMapperBuilder(InputStream inputStream, Configuration configuration, String resource, Map sqlFragments, String namespace) {	
      this(inputStream, configuration, resource, sqlFragments);	
      this.builderAssistant.setCurrentNamespace(namespace);	
   }	
	
   // $FF: synthetic method	
   private ResultMapping buildResultMappingFromContext(XNode context, Class resultType, List flags) throws Exception {	
      String a;	
      XNode var10000;	
      if (flags.contains(ResultFlag.CONSTRUCTOR)) {	
         var10000 = context;	
         a = context.getStringAttribute("name");	
      } else {	
         var10000 = context;	
         a = context.getStringAttribute("property");	
      }	
	
      String a = var10000.getStringAttribute("column");	
      String a = context.getStringAttribute("javaType");	
      String a = context.getStringAttribute("jdbcType");	
      String a = context.getStringAttribute("select");	
      String a = context.getStringAttribute("resultMap", this.processNestedResultMappings(context, Collections.emptyList()));	
      String a = context.getStringAttribute("notNullColumn");	
      String a = context.getStringAttribute("columnPrefix");	
      String a = context.getStringAttribute("typeHandler");	
      String a = context.getStringAttribute("resultSet");	
      String a = context.getStringAttribute("foreignColumn");	
      int a = "lazy".equals(context.getStringAttribute("fetchType", this.configuration.isLazyLoadingEnabled() ? "lazy" : "eager"));	
      Class a = this.resolveClass(a);	
      Class a = this.resolveClass(a);	
      JdbcType a = this.resolveJdbcType(a);	
      return this.builderAssistant.buildResultMapping(resultType, a, a, a, a, a, a, a, a, a, flags, a, a, a);	
   }	
	
   // $FF: synthetic method	
   private void sqlElement(List list, String requiredDatabaseId) throws Exception {	
      Iterator var3 = list.iterator();	
	
      while(var3.hasNext()) {	
         XNode a;	
         XNode var10000 = a = (XNode)var3.next();	
         String a = var10000.getStringAttribute("databaseId");	
         String a = var10000.getStringAttribute("id");	
         if (this.databaseIdMatchesCurrent(a = this.builderAssistant.applyCurrentNamespace(a, false), a, requiredDatabaseId)) {	
            this.sqlFragments.put(a, a);	
         }	
      }	
	
   }	
	
   // $FF: synthetic method	
   private void cacheElement(XNode context) throws Exception {	
      if (context != null) {	
         String a = context.getStringAttribute("type", "PERPETUAL");	
         Class a = this.typeAliasRegistry.resolveAlias(a);	
         String a = context.getStringAttribute("eviction", "LRU");	
         Class a = this.typeAliasRegistry.resolveAlias(a);	
         Long a = context.getLongAttribute("flushInterval");	
         Integer a = context.getIntAttribute("size");	
         int a = !context.getBooleanAttribute("readOnly", false);	
         int a = context.getBooleanAttribute("blocking", false);	
         Properties a = context.getChildrenAsProperties();	
         this.builderAssistant.useNewCache(a, a, a, a, a, a, a);	
      }	
	
   }	
	
   // $FF: synthetic method	
   private void buildStatementFromContext(List list, String requiredDatabaseId) {	
      Iterator var3 = list.iterator();	
	
      while(var3.hasNext()) {	
         XNode a = (XNode)var3.next();	
         XMLStatementBuilder a = new XMLStatementBuilder(this.configuration, this.builderAssistant, a, requiredDatabaseId);	
	
         try {	
            a.parseStatementNode();	
         } catch (IncompleteElementException var7) {	
            this.configuration.addIncompleteStatement(a);	
         }	
      }	
	
   }	
	
   // $FF: synthetic method	
   private void cacheRefElement(XNode context) {	
      if (context != null) {	
         this.configuration.addCacheRef(this.builderAssistant.getCurrentNamespace(), context.getStringAttribute("namespace"));	
         CacheRefResolver a = new CacheRefResolver(this.builderAssistant, context.getStringAttribute("namespace"));	
	
         try {	
            a.resolveCacheRef();	
            return;	
         } catch (IncompleteElementException var4) {	
            this.configuration.addIncompleteCacheRef(a);	
         }	
      }	
	
   }	
	
   // $FF: synthetic method	
   private void parameterMapElement(List list) throws Exception {	
      Iterator var2;	
      for(Iterator var10000 = var2 = list.iterator(); var10000.hasNext(); var10000 = var2) {	
         XNode var23 = (XNode)var2.next();	
         String a = var23.getStringAttribute("id");	
         String a = var23.getStringAttribute("type");	
         Class a = this.resolveClass(a);	
         List a = var23.evalNodes("parameter");	
         List a = new ArrayList();	
         Iterator var9;	
         var10000 = var9 = a.iterator();	
	
         while(var10000.hasNext()) {	
            var23 = (XNode)var9.next();	
            String a = var23.getStringAttribute("property");	
            String a = var23.getStringAttribute("javaType");	
            String a = var23.getStringAttribute("jdbcType");	
            String a = var23.getStringAttribute("resultMap");	
            String a = var23.getStringAttribute("mode");	
            String a = var23.getStringAttribute("typeHandler");	
            Integer a = var23.getIntAttribute("numericScale");	
            ParameterMode a = this.resolveParameterMode(a);	
            Class a = this.resolveClass(a);	
            JdbcType a = this.resolveJdbcType(a);	
            Class a = this.resolveClass(a);	
            ParameterMapping a = this.builderAssistant.buildParameterMapping(a, a, a, a, a, a, a, a);	
            var10000 = var9;	
            a.add(a);	
         }	
	
         this.builderAssistant.addParameterMap(a, a, a);	
      }	
	
   }	
	
   // $FF: synthetic method	
   private String processNestedResultMappings(XNode context, List resultMappings) throws Exception {	
      return ("association".equals(context.getName()) || "collection".equals(context.getName()) || "case".equals(context.getName())) && context.getStringAttribute("select") == null ? this.resultMapElement(context, resultMappings).getId() : null;	
   }	
	
   // $FF: synthetic method	
   private void bindMapperForNamespace() {	
      String a;	
      if ((a = this.builderAssistant.getCurrentNamespace()) != null) {	
         Class a = null;	
	
         Class var10000;	
         label20: {	
            try {	
               a = Resources.classForName(a);	
            } catch (ClassNotFoundException var4) {	
               var10000 = a;	
               break label20;	
            }	
	
            var10000 = a;	
         }	
	
         if (var10000 != null && !this.configuration.hasMapper(a)) {	
            this.configuration.addLoadedResource((new StringBuilder()).insert(0, "namespace:").append(a).toString());	
            this.configuration.addMapper(a);	
         }	
      }	
	
   }	
	
   // $FF: synthetic method	
   private XMLMapperBuilder(XPathParser parser, Configuration configuration, String resource, Map sqlFragments) {	
      super(configuration);	
      this.builderAssistant = new MapperBuilderAssistant(configuration, resource);	
      this.parser = parser;	
      this.sqlFragments = sqlFragments;	
      this.resource = resource;	
   }	
	
   // $FF: synthetic method	
   private void buildStatementFromContext(List list) {	
      if (this.configuration.getDatabaseId() != null) {	
         this.buildStatementFromContext(list, this.configuration.getDatabaseId());	
      }	
	
      this.buildStatementFromContext(list, (String)null);	
   }	
	
   public XMLMapperBuilder(InputStream inputStream, Configuration configuration, String resource, Map sqlFragments) {	
      this(new XPathParser(inputStream, true, configuration.getVariables(), new XMLMapperEntityResolver()), configuration, resource, sqlFragments);	
   }	
	
   // $FF: synthetic method	
   private Discriminator processDiscriminatorElement(XNode context, Class resultType, List resultMappings) throws Exception {	
      String a = context.getStringAttribute("column");	
      String a = context.getStringAttribute("javaType");	
      String a = context.getStringAttribute("jdbcType");	
      String a = context.getStringAttribute("typeHandler");	
      Class a = this.resolveClass(a);	
      Class a = this.resolveClass(a);	
      JdbcType a = this.resolveJdbcType(a);	
      Map a = new HashMap();	
      Iterator var12;	
      Iterator var10000 = var12 = context.getChildren().iterator();	
	
      while(var10000.hasNext()) {	
         XNode a;	
         String a = (a = (XNode)var12.next()).getStringAttribute("value");	
         String a = a.getStringAttribute("resultMap", this.processNestedResultMappings(a, resultMappings));	
         var10000 = var12;	
         a.put(a, a);	
      }	
	
      return this.builderAssistant.buildDiscriminator(resultType, a, a, a, a, a);	
   }	
	
   /** @deprecated */	
   @Deprecated	
   public XMLMapperBuilder(Reader reader, Configuration configuration, String resource, Map sqlFragments, String namespace) {	
      this(reader, configuration, resource, sqlFragments);	
      this.builderAssistant.setCurrentNamespace(namespace);	
   }	
	
   public XNode getSqlFragment(String refid) {	
      return (XNode)this.sqlFragments.get(refid);	
   }	
}	
