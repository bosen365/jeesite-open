/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  org.apache.ibatis.builder.BaseBuilder	
 *  org.apache.ibatis.builder.BuilderException	
 *  org.apache.ibatis.builder.CacheRefResolver	
 *  org.apache.ibatis.builder.IncompleteElementException	
 *  org.apache.ibatis.builder.MapperBuilderAssistant	
 *  org.apache.ibatis.builder.ResultMapResolver	
 *  org.apache.ibatis.builder.xml.XMLStatementBuilder	
 *  org.apache.ibatis.cache.Cache	
 *  org.apache.ibatis.executor.ErrorContext	
 *  org.apache.ibatis.io.Resources	
 *  org.apache.ibatis.mapping.Discriminator	
 *  org.apache.ibatis.mapping.ParameterMap	
 *  org.apache.ibatis.mapping.ParameterMapping	
 *  org.apache.ibatis.mapping.ParameterMode	
 *  org.apache.ibatis.mapping.ResultFlag	
 *  org.apache.ibatis.mapping.ResultMap	
 *  org.apache.ibatis.mapping.ResultMapping	
 *  org.apache.ibatis.parsing.XNode	
 *  org.apache.ibatis.parsing.XPathParser	
 *  org.apache.ibatis.session.Configuration	
 *  org.apache.ibatis.type.JdbcType	
 *  org.apache.ibatis.type.TypeAliasRegistry	
 */	
package com.jeesite.common.mybatis.mapper.xml;	
	
import com.jeesite.common.cache.JedisUtils;	
import com.jeesite.common.mybatis.mapper.xml.XMLMapperEntityResolver;	
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
import org.apache.ibatis.cache.Cache;	
import org.apache.ibatis.executor.ErrorContext;	
import org.apache.ibatis.io.Resources;	
import org.apache.ibatis.mapping.Discriminator;	
import org.apache.ibatis.mapping.ParameterMap;	
import org.apache.ibatis.mapping.ParameterMapping;	
import org.apache.ibatis.mapping.ParameterMode;	
import org.apache.ibatis.mapping.ResultFlag;	
import org.apache.ibatis.mapping.ResultMap;	
import org.apache.ibatis.mapping.ResultMapping;	
import org.apache.ibatis.parsing.XNode;	
import org.apache.ibatis.parsing.XPathParser;	
import org.apache.ibatis.session.Configuration;	
import org.apache.ibatis.type.JdbcType;	
import org.apache.ibatis.type.TypeAliasRegistry;	
import org.hyperic.sigar.ProcMem;	
import org.xml.sax.EntityResolver;	
	
public class XMLMapperBuilder	
extends BaseBuilder {	
    private final String resource;	
    private final XPathParser parser;	
    private final MapperBuilderAssistant builderAssistant;	
    private final Map<String, XNode> sqlFragments;	
	
    private /* synthetic */ ResultMap resultMapElement(XNode resultMapNode) throws Exception {	
        return this.resultMapElement(resultMapNode, Collections.emptyList());	
    }	
	
    private /* synthetic */ void sqlElement(List<XNode> list) throws Exception {	
        if (this.configuration.getDatabaseId() != null) {	
            XMLMapperBuilder xMLMapperBuilder = this;	
            xMLMapperBuilder.sqlElement(list, xMLMapperBuilder.configuration.getDatabaseId());	
        }	
        this.sqlElement(list, null);	
    }	
	
    private /* synthetic */ void processConstructorElement(XNode resultChild, Class<?> resultType, List<ResultMapping> resultMappings) throws Exception {	
        Iterator iterator;	
        Iterator iterator2 = iterator = resultChild.getChildren().iterator();	
        while (iterator2.hasNext()) {	
            void a2;	
            XNode a3 = (XNode)iterator.next();	
            ArrayList arrayList = new ArrayList();	
            a2.add(ResultFlag.CONSTRUCTOR);	
            if ("idArg".equals(a3.getName())) {	
                a2.add(ResultFlag.ID);	
            }	
            resultMappings.add(this.buildResultMappingFromContext(a3, resultType, (List<ResultFlag>)a2));	
            iterator2 = iterator;	
        }	
    }	
	
    @Deprecated	
    public XMLMapperBuilder(Reader reader, Configuration configuration, String string, Map<String, XNode> map) {	
        void configuration2;	
        void sqlFragments;	
        void resource;	
        void reader2;	
        this(new XPathParser((Reader)reader2, true, configuration2.getVariables(), (EntityResolver)new XMLMapperEntityResolver()), (Configuration)configuration2, (String)resource, (Map<String, XNode>)sqlFragments);	
    }	
	
    /*	
     * WARNING - Removed try catching itself - possible behaviour change.	
     * Enabled aggressive block sorting	
     * Enabled unnecessary exception pruning	
     * Enabled aggressive exception aggregation	
     */	
    private /* synthetic */ void parsePendingStatements() {	
        Collection a2;	
        Collection collection = a2 = this.configuration.getIncompleteStatements();	
        synchronized (a2) {	
            Iterator a3;	
            Iterator iterator = a3 = a2.iterator();	
            do {	
                if (!iterator.hasNext()) {	
                    // ** MonitorExit[var2_2] (shouldn't be in output)	
                    return;	
                }	
                try {	
                    ((XMLStatementBuilder)a3.next()).parseStatementNode();	
                    a3.remove();	
                    iterator = a3;	
                }	
                catch (IncompleteElementException incompleteElementException) {	
                    iterator = a3;	
                    continue;	
                }	
                break;	
            } while (true);	
        }	
    }	
	
    /*	
     * WARNING - Removed try catching itself - possible behaviour change.	
     * Enabled aggressive block sorting	
     * Enabled unnecessary exception pruning	
     * Enabled aggressive exception aggregation	
     */	
    private /* synthetic */ void parsePendingCacheRefs() {	
        Collection a2;	
        Collection collection = a2 = this.configuration.getIncompleteCacheRefs();	
        synchronized (a2) {	
            Iterator a3;	
            Iterator iterator = a3 = a2.iterator();	
            do {	
                if (!iterator.hasNext()) {	
                    // ** MonitorExit[var2_2] (shouldn't be in output)	
                    return;	
                }	
                try {	
                    ((CacheRefResolver)a3.next()).resolveCacheRef();	
                    a3.remove();	
                    iterator = a3;	
                }	
                catch (IncompleteElementException incompleteElementException) {	
                    iterator = a3;	
                    continue;	
                }	
                break;	
            } while (true);	
        }	
    }	
	
    private /* synthetic */ boolean databaseIdMatchesCurrent(String id, String databaseId, String requiredDatabaseId) {	
        if (requiredDatabaseId != null) {	
            if (!requiredDatabaseId.equals(databaseId)) {	
                return false;	
            }	
        } else {	
            if (databaseId != null) {	
                return false;	
            }	
            if (this.sqlFragments.containsKey(id) && this.sqlFragments.get(id).getStringAttribute("databaseId") != null) {	
                return false;	
            }	
        }	
        return true;	
    }	
	
    /*	
     * WARNING - Removed try catching itself - possible behaviour change.	
     * Enabled aggressive block sorting	
     * Enabled unnecessary exception pruning	
     * Enabled aggressive exception aggregation	
     */	
    private /* synthetic */ void parsePendingResultMaps() {	
        Collection a2;	
        Collection collection = a2 = this.configuration.getIncompleteResultMaps();	
        synchronized (a2) {	
            Iterator a3;	
            Iterator iterator = a3 = a2.iterator();	
            do {	
                if (!iterator.hasNext()) {	
                    // ** MonitorExit[var2_2] (shouldn't be in output)	
                    return;	
                }	
                try {	
                    ((ResultMapResolver)a3.next()).resolve();	
                    a3.remove();	
                    iterator = a3;	
                }	
                catch (IncompleteElementException incompleteElementException) {	
                    iterator = a3;	
                    continue;	
                }	
                break;	
            } while (true);	
        }	
    }	
	
    private /* synthetic */ void configurationElement(XNode context) {	
        try {	
            String a2 = context.getStringAttribute("namespace");	
            if (a2 == null || a2.equals("")) {	
                throw new BuilderException("Mapper's namespace cannot be empty");	
            }	
            XNode xNode = context;	
            XMLMapperBuilder xMLMapperBuilder = this;	
            XNode xNode2 = context;	
            XMLMapperBuilder xMLMapperBuilder2 = this;	
            xMLMapperBuilder2.builderAssistant.setCurrentNamespace(a2);	
            xMLMapperBuilder2.cacheRefElement(context.evalNode("cache-ref"));	
            this.cacheElement(xNode2.evalNode("cache"));	
            xMLMapperBuilder.parameterMapElement(xNode2.evalNodes("/mapper/parameterMap"));	
            xMLMapperBuilder.resultMapElements(context.evalNodes("/mapper/resultMap"));	
            this.sqlElement(xNode.evalNodes("/mapper/sql"));	
            this.buildStatementFromContext(xNode.evalNodes("select|insert|update|delete"));	
            return;	
        }	
        catch (Exception a3) {	
            throw new BuilderException(new StringBuilder().insert(0, "Error parsing Mapper XML. The XML location is '").append(this.resource).append("'. Cause: ").append(a3).toString(), (Throwable)a3);	
        }	
    }	
	
    private /* synthetic */ ResultMap resultMapElement(XNode resultMapNode, List<ResultMapping> additionalResultMappings) throws Exception {	
        void a2;	
        XNode xNode = resultMapNode;	
        XNode xNode2 = resultMapNode;	
        String string = xNode2.getStringAttribute("id", xNode2.getValueBasedIdentifier());	
        XNode xNode3 = resultMapNode;	
        XNode xNode4 = resultMapNode;	
        String a3 = xNode.getStringAttribute("type", xNode3.getStringAttribute("ofType", xNode4.getStringAttribute("resultType", xNode4.getStringAttribute("javaType"))));	
        String a4 = xNode3.getStringAttribute("extends");	
        Boolean a5 = xNode.getBooleanAttribute("autoMapping");	
        Class a6 = this.resolveClass(a3);	
        Discriminator a7 = null;	
        ArrayList<ResultMapping> a8 = new ArrayList<ResultMapping>();	
        Iterator iterator = resultMapNode.getChildren().iterator();	
        a8.addAll(additionalResultMappings);	
        ErrorContext.instance().activity(new StringBuilder().insert(0, "processing ").append(resultMapNode.getValueBasedIdentifier()).toString());	
        while (iterator.hasNext()) {	
            XNode a9 = (XNode)iterator.next();	
            if ("constructor".equals(a9.getName())) {	
                this.processConstructorElement(a9, a6, a8);	
                continue;	
            }	
            if ("discriminator".equals(a9.getName())) {	
                a7 = this.processDiscriminatorElement(a9, a6, a8);	
                continue;	
            }	
            ArrayList<ResultFlag> a10 = new ArrayList<ResultFlag>();	
            if ("id".equals(a9.getName())) {	
                a10.add(ResultFlag.ID);	
            }	
            a8.add(this.buildResultMappingFromContext(a9, a6, a10));	
        }	
        ResultMapResolver a11 = new ResultMapResolver(this.builderAssistant, (String)a2, a6, a4, a7, a8, a5);	
        try {	
            return a11.resolve();	
        }	
        catch (IncompleteElementException a9) {	
            this.configuration.addIncompleteResultMap(a11);	
            throw a9;	
        }	
    }	
	
    public void parse() {	
        XMLMapperBuilder xMLMapperBuilder = this;	
        if (!xMLMapperBuilder.configuration.isResourceLoaded(xMLMapperBuilder.resource)) {	
            XMLMapperBuilder xMLMapperBuilder2 = this;	
            xMLMapperBuilder2.configurationElement(xMLMapperBuilder2.parser.evalNode("/mapper"));	
            xMLMapperBuilder2.configuration.addLoadedResource(this.resource);	
            xMLMapperBuilder2.bindMapperForNamespace();	
        }	
        XMLMapperBuilder xMLMapperBuilder3 = this;	
        xMLMapperBuilder3.parsePendingResultMaps();	
        xMLMapperBuilder3.parsePendingCacheRefs();	
        xMLMapperBuilder3.parsePendingStatements();	
    }	
	
    private /* synthetic */ void resultMapElements(List<XNode> list) throws Exception {	
        for (XNode a2 : list) {	
            try {	
                this.resultMapElement(a2);	
            }	
            catch (IncompleteElementException incompleteElementException) {}	
        }	
    }	
	
    public XMLMapperBuilder(InputStream inputStream, Configuration configuration, String resource, Map<String, XNode> sqlFragments, String namespace) {	
        XMLMapperBuilder xMLMapperBuilder = this;	
        xMLMapperBuilder(inputStream, configuration, resource, sqlFragments);	
        xMLMapperBuilder.builderAssistant.setCurrentNamespace(namespace);	
    }	
	
    private /* synthetic */ ResultMapping buildResultMappingFromContext(XNode context, Class<?> resultType, List<ResultFlag> flags) throws Exception {	
        String a2;	
        XNode xNode;	
        if (flags.contains((Object)ResultFlag.CONSTRUCTOR)) {	
            XNode xNode2 = context;	
            xNode = xNode2;	
            a2 = xNode2.getStringAttribute("name");	
        } else {	
            XNode xNode3 = context;	
            xNode = xNode3;	
            a2 = xNode3.getStringAttribute("property");	
        }	
        String a3 = xNode.getStringAttribute("column");	
        XNode xNode4 = context;	
        String a4 = xNode4.getStringAttribute("javaType");	
        String a5 = xNode4.getStringAttribute("jdbcType");	
        String a6 = xNode4.getStringAttribute("select");	
        String a7 = xNode4.getStringAttribute("resultMap", this.processNestedResultMappings(context, Collections.emptyList()));	
        String a8 = xNode4.getStringAttribute("notNullColumn");	
        String a9 = xNode4.getStringAttribute("columnPrefix");	
        String a10 = xNode4.getStringAttribute("typeHandler");	
        String a11 = xNode4.getStringAttribute("resultSet");	
        String a12 = xNode4.getStringAttribute("foreignColumn");	
        boolean a13 = "lazy".equals(context.getStringAttribute("fetchType", this.configuration.isLazyLoadingEnabled() ? "lazy" : "eager"));	
        XMLMapperBuilder xMLMapperBuilder = this;	
        Class a14 = xMLMapperBuilder.resolveClass(a4);	
        Class a15 = xMLMapperBuilder.resolveClass(a10);	
        JdbcType a16 = xMLMapperBuilder.resolveJdbcType(a5);	
        return xMLMapperBuilder.builderAssistant.buildResultMapping(resultType, a2, a3, a14, a16, a6, a7, a8, a9, a15, flags, a11, a12, a13);	
    }	
	
    private /* synthetic */ void sqlElement(List<XNode> list, String requiredDatabaseId) throws Exception {	
        for (XNode a2 : list) {	
            String a3 = a2.getStringAttribute("databaseId");	
            String a4 = a2.getStringAttribute("id");	
            XMLMapperBuilder xMLMapperBuilder = this;	
            if (!xMLMapperBuilder.databaseIdMatchesCurrent(a4 = xMLMapperBuilder.builderAssistant.applyCurrentNamespace(a4, false), a3, requiredDatabaseId)) continue;	
            this.sqlFragments.put(a4, a2);	
        }	
    }	
	
    private /* synthetic */ void cacheElement(XNode context) throws Exception {	
        if (context != null) {	
            XNode xNode = context;	
            String a2 = xNode.getStringAttribute("type", "PERPETUAL");	
            Class a3 = this.typeAliasRegistry.resolveAlias(a2);	
            String a4 = xNode.getStringAttribute("eviction", "LRU");	
            Class a5 = this.typeAliasRegistry.resolveAlias(a4);	
            Long a6 = xNode.getLongAttribute("flushInterval");	
            Integer a7 = xNode.getIntAttribute("size");	
            boolean a8 = xNode.getBooleanAttribute("readOnly", Boolean.valueOf(false)) == false;	
            XNode xNode2 = context;	
            boolean a9 = xNode2.getBooleanAttribute("blocking", Boolean.valueOf(false));	
            Properties a10 = xNode2.getChildrenAsProperties();	
            this.builderAssistant.useNewCache(a3, a5, a6, a7, a8, a9, a10);	
        }	
    }	
	
    private /* synthetic */ void buildStatementFromContext(List<XNode> list, String requiredDatabaseId) {	
        for (XNode a2 : list) {	
            XMLMapperBuilder xMLMapperBuilder = this;	
            XMLStatementBuilder a3 = new XMLStatementBuilder(xMLMapperBuilder.configuration, xMLMapperBuilder.builderAssistant, a2, requiredDatabaseId);	
            try {	
                a3.parseStatementNode();	
            }	
            catch (IncompleteElementException a4) {	
                this.configuration.addIncompleteStatement(a3);	
            }	
        }	
    }	
	
    private /* synthetic */ void cacheRefElement(XNode context) {	
        if (context != null) {	
            XMLMapperBuilder xMLMapperBuilder = this;	
            xMLMapperBuilder.configuration.addCacheRef(xMLMapperBuilder.builderAssistant.getCurrentNamespace(), context.getStringAttribute("namespace"));	
            CacheRefResolver a2 = new CacheRefResolver(this.builderAssistant, context.getStringAttribute("namespace"));	
            try {	
                a2.resolveCacheRef();	
                return;	
            }	
            catch (IncompleteElementException a3) {	
                this.configuration.addIncompleteCacheRef(a2);	
            }	
        }	
    }	
	
    private /* synthetic */ void parameterMapElement(List<XNode> list) throws Exception {	
        Iterator<XNode> iterator;	
        Iterator<XNode> iterator2 = iterator = list.iterator();	
        while (iterator2.hasNext()) {	
            XNode xNode = iterator.next();	
            String a2 = xNode.getStringAttribute("id");	
            String a3 = xNode.getStringAttribute("type");	
            Class a4 = this.resolveClass(a3);	
            List a5 = xNode.evalNodes("parameter");	
            ArrayList<void> a6 = new ArrayList<void>();	
            Iterator iterator3 = a5.iterator();	
            while (iterator3.hasNext()) {	
                void a7;	
                Iterator iterator4;	
                XNode xNode2 = (XNode)iterator4.next();	
                String a8 = xNode2.getStringAttribute("property");	
                String a9 = xNode2.getStringAttribute("javaType");	
                String a10 = xNode2.getStringAttribute("jdbcType");	
                String a11 = xNode2.getStringAttribute("resultMap");	
                String a12 = xNode2.getStringAttribute("mode");	
                String a13 = xNode2.getStringAttribute("typeHandler");	
                Integer a14 = xNode2.getIntAttribute("numericScale");	
                XMLMapperBuilder xMLMapperBuilder = this;	
                ParameterMode a15 = xMLMapperBuilder.resolveParameterMode(a12);	
                Class a16 = xMLMapperBuilder.resolveClass(a9);	
                JdbcType a17 = xMLMapperBuilder.resolveJdbcType(a10);	
                Class a18 = xMLMapperBuilder.resolveClass(a13);	
                ParameterMapping parameterMapping = xMLMapperBuilder.builderAssistant.buildParameterMapping(a4, a8, a16, a17, a11, a15, a18, a14);	
                iterator3 = iterator4;	
                a6.add(a7);	
            }	
            this.builderAssistant.addParameterMap(a2, a4, a6);	
            iterator2 = iterator;	
        }	
    }	
	
    private /* synthetic */ String processNestedResultMappings(XNode context, List<ResultMapping> resultMappings) throws Exception {	
        if (("association".equals(context.getName()) || "collection".equals(context.getName()) || "case".equals(context.getName())) && context.getStringAttribute("select") == null) {	
            return this.resultMapElement(context, resultMappings).getId();	
        }	
        return null;	
    }	
	
    private /* synthetic */ void bindMapperForNamespace() {	
        String a2 = this.builderAssistant.getCurrentNamespace();	
        if (a2 != null) {	
            Class class_;	
            Class a3 = null;	
            try {	
                class_ = a3 = Resources.classForName((String)a2);	
            }	
            catch (ClassNotFoundException classNotFoundException) {	
                class_ = a3;	
            }	
            if (class_ != null && !this.configuration.hasMapper(a3)) {	
                XMLMapperBuilder xMLMapperBuilder = this;	
                xMLMapperBuilder.configuration.addLoadedResource(new StringBuilder().insert(0, "namespace:").append(a2).toString());	
                xMLMapperBuilder.configuration.addMapper(a3);	
            }	
        }	
    }	
	
    private /* synthetic */ XMLMapperBuilder(XPathParser parser, Configuration configuration, String resource, Map<String, XNode> sqlFragments) {	
        XMLMapperBuilder xMLMapperBuilder = this;	
        super(configuration);	
        XMLMapperBuilder xMLMapperBuilder2 = this;	
        this.builderAssistant = new MapperBuilderAssistant(configuration, resource);	
        this.parser = parser;	
        xMLMapperBuilder.sqlFragments = sqlFragments;	
        xMLMapperBuilder.resource = resource;	
    }	
	
    private /* synthetic */ void buildStatementFromContext(List<XNode> list) {	
        if (this.configuration.getDatabaseId() != null) {	
            XMLMapperBuilder xMLMapperBuilder = this;	
            xMLMapperBuilder.buildStatementFromContext(list, xMLMapperBuilder.configuration.getDatabaseId());	
        }	
        this.buildStatementFromContext(list, null);	
    }	
	
    public XMLMapperBuilder(InputStream inputStream, Configuration configuration, String string, Map<String, XNode> map) {	
        void configuration2;	
        void sqlFragments;	
        void resource;	
        void inputStream2;	
        this(new XPathParser((InputStream)inputStream2, true, configuration2.getVariables(), (EntityResolver)new XMLMapperEntityResolver()), (Configuration)configuration2, (String)resource, (Map<String, XNode>)sqlFragments);	
    }	
	
    private /* synthetic */ Discriminator processDiscriminatorElement(XNode context, Class<?> resultType, List<ResultMapping> resultMappings) throws Exception {	
        Iterator iterator;	
        XNode xNode = context;	
        String a2 = xNode.getStringAttribute("column");	
        String a3 = xNode.getStringAttribute("javaType");	
        String a4 = xNode.getStringAttribute("jdbcType");	
        String a5 = xNode.getStringAttribute("typeHandler");	
        XMLMapperBuilder xMLMapperBuilder = this;	
        Class a6 = xMLMapperBuilder.resolveClass(a3);	
        Class a7 = xMLMapperBuilder.resolveClass(a5);	
        JdbcType a8 = xMLMapperBuilder.resolveJdbcType(a4);	
        HashMap<String, void> a9 = new HashMap<String, void>();	
        Iterator iterator2 = iterator = xNode.getChildren().iterator();	
        while (iterator2.hasNext()) {	
            void a10;	
            void a11;	
            XNode xNode2 = (XNode)iterator.next();	
            String a12 = xNode2.getStringAttribute("value");	
            void v3 = a11;	
            String string = v3.getStringAttribute("resultMap", this.processNestedResultMappings((XNode)v3, resultMappings));	
            iterator2 = iterator;	
            a9.put(a12, a10);	
        }	
        return this.builderAssistant.buildDiscriminator(resultType, a2, a6, a8, a7, a9);	
    }	
	
    @Deprecated	
    public XMLMapperBuilder(Reader reader, Configuration configuration, String resource, Map<String, XNode> sqlFragments, String namespace) {	
        XMLMapperBuilder xMLMapperBuilder = this;	
        xMLMapperBuilder(reader, configuration, resource, sqlFragments);	
        xMLMapperBuilder.builderAssistant.setCurrentNamespace(namespace);	
    }	
	
    public XNode getSqlFragment(String refid) {	
        return this.sqlFragments.get(refid);	
    }	
}	
	
