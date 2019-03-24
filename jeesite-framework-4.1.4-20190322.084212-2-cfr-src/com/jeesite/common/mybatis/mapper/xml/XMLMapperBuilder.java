/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.mybatis.mapper.xml;	
	
import com.jeesite.autoconfigure.core.CacheAutoConfiguration;	
import com.jeesite.autoconfigure.sys.MsgAutoConfiguration;	
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
import org.apache.ibatis.type.TypeHandler;	
import org.xml.sax.EntityResolver;	
	
public class XMLMapperBuilder	
extends BaseBuilder {	
    private final XPathParser parser;	
    private final String resource;	
    private final MapperBuilderAssistant builderAssistant;	
    private final Map<String, XNode> sqlFragments;	
	
    /*	
     * WARNING - Removed try catching itself - possible behaviour change.	
     * Enabled aggressive block sorting	
     * Enabled unnecessary exception pruning	
     * Enabled aggressive exception aggregation	
     */	
    private /* synthetic */ void parsePendingCacheRefs() {	
        Collection<CacheRefResolver> a;	
        Collection<CacheRefResolver> collection = a = this.configuration.getIncompleteCacheRefs();	
        synchronized (a) {	
            Iterator<CacheRefResolver> a2;	
            Iterator<CacheRefResolver> iterator = a2 = a.iterator();	
            do {	
                if (!iterator.hasNext()) {	
                    // ** MonitorExit[var2_2] (shouldn't be in output)	
                    return;	
                }	
                try {	
                    a2.next().resolveCacheRef();	
                    a2.remove();	
                    iterator = a2;	
                }	
                catch (IncompleteElementException incompleteElementException) {	
                    iterator = a2;	
                    continue;	
                }	
                break;	
            } while (true);	
        }	
    }	
	
    private /* synthetic */ void bindMapperForNamespace() {	
        String a = this.builderAssistant.getCurrentNamespace();	
        if (a != null) {	
            Class<?> class_;	
            Class<?> a2 = null;	
            try {	
                class_ = a2 = Resources.classForName(a);	
            }	
            catch (ClassNotFoundException classNotFoundException) {	
                class_ = a2;	
            }	
            if (class_ != null && !this.configuration.hasMapper(a2)) {	
                XMLMapperBuilder xMLMapperBuilder = this;	
                xMLMapperBuilder.configuration.addLoadedResource(new StringBuilder().insert(0, "namespace:").append(a).toString());	
                xMLMapperBuilder.configuration.addMapper(a2);	
            }	
        }	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    public XMLMapperBuilder(InputStream inputStream, Configuration configuration, String string, Map<String, XNode> map) {	
        void inputStream2;	
        void resource;	
        void configuration2;	
        void sqlFragments;	
        this(new XPathParser((InputStream)inputStream2, true, configuration2.getVariables(), (EntityResolver)new XMLMapperEntityResolver()), (Configuration)configuration2, (String)resource, (Map<String, XNode>)sqlFragments);	
    }	
	
    private /* synthetic */ void buildStatementFromContext(List<XNode> list, String requiredDatabaseId) {	
        for (XNode a : list) {	
            XMLMapperBuilder xMLMapperBuilder = this;	
            XMLStatementBuilder a2 = new XMLStatementBuilder(xMLMapperBuilder.configuration, xMLMapperBuilder.builderAssistant, a, requiredDatabaseId);	
            try {	
                a2.parseStatementNode();	
            }	
            catch (IncompleteElementException a3) {	
                this.configuration.addIncompleteStatement(a2);	
            }	
        }	
    }	
	
    /*	
     * WARNING - Removed try catching itself - possible behaviour change.	
     * Enabled aggressive block sorting	
     * Enabled unnecessary exception pruning	
     * Enabled aggressive exception aggregation	
     */	
    private /* synthetic */ void parsePendingResultMaps() {	
        Collection<ResultMapResolver> a;	
        Collection<ResultMapResolver> collection = a = this.configuration.getIncompleteResultMaps();	
        synchronized (a) {	
            Iterator<ResultMapResolver> a2;	
            Iterator<ResultMapResolver> iterator = a2 = a.iterator();	
            do {	
                if (!iterator.hasNext()) {	
                    // ** MonitorExit[var2_2] (shouldn't be in output)	
                    return;	
                }	
                try {	
                    a2.next().resolve();	
                    a2.remove();	
                    iterator = a2;	
                }	
                catch (IncompleteElementException incompleteElementException) {	
                    iterator = a2;	
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
            if (this.sqlFragments.containsKey(id) && this.sqlFragments.get(id).getStringAttribute("database#d") != null) {	
                return false;	
            }	
        }	
        return true;	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    private /* synthetic */ void parameterMapElement(List<XNode> list) throws Exception {	
        Iterator<XNode> iterator;	
        Iterator<XNode> iterator2 = iterator = list.iterator();	
        while (iterator2.hasNext()) {	
            XNode xNode = iterator.next();	
            String a = xNode.getStringAttribute("id");	
            String a2 = xNode.getStringAttribute("type");	
            Class a3 = this.resolveClass(a2);	
            List<XNode> a4 = xNode.evalNodes("parameter");	
            ArrayList<ParameterMapping> a5 = new ArrayList<ParameterMapping>();	
            Iterator<XNode> iterator3 = a4.iterator();	
            while (iterator3.hasNext()) {	
                Iterator<XNode> iterator4;	
                void a6;	
                XNode xNode2 = iterator4.next();	
                String a7 = xNode2.getStringAttribute("property");	
                String a8 = xNode2.getStringAttribute("javaType");	
                String a9 = xNode2.getStringAttribute("jdbcType");	
                String a10 = xNode2.getStringAttribute("resultMap");	
                String a11 = xNode2.getStringAttribute("mode");	
                String a12 = xNode2.getStringAttribute("typeHandler");	
                Integer a13 = xNode2.getIntAttribute("numericScale");	
                XMLMapperBuilder xMLMapperBuilder = this;	
                ParameterMode a14 = xMLMapperBuilder.resolveParameterMode(a11);	
                Class a15 = xMLMapperBuilder.resolveClass(a8);	
                JdbcType a16 = xMLMapperBuilder.resolveJdbcType(a9);	
                Class a17 = xMLMapperBuilder.resolveClass(a12);	
                ParameterMapping parameterMapping = xMLMapperBuilder.builderAssistant.buildParameterMapping(a3, a7, a15, a16, a10, a14, a17, a13);	
                iterator3 = iterator4;	
                a5.add((ParameterMapping)a6);	
            }	
            this.builderAssistant.addParameterMap(a, a3, a5);	
            iterator2 = iterator;	
        }	
    }	
	
    private /* synthetic */ void cacheElement(XNode context) throws Exception {	
        if (context != null) {	
            XNode xNode = context;	
            String a = xNode.getStringAttribute("type", "PERPETUAL");	
            Class a2 = this.typeAliasRegistry.resolveAlias(a);	
            String a3 = xNode.getStringAttribute("eviction", "LRU");	
            Class a4 = this.typeAliasRegistry.resolveAlias(a3);	
            Long a5 = xNode.getLongAttribute("flushInterva");	
            Integer a6 = xNode.getIntAttribute("size");	
            boolean a7 = xNode.getBooleanAttribute("readOnly", false) == false;	
            XNode xNode2 = context;	
            boolean a8 = xNode2.getBooleanAttribute("blocking", false);	
            Properties a9 = xNode2.getChildrenAsProperties();	
            this.builderAssistant.useNewCache(a2, a4, a5, a6, a7, a8, a9);	
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
	
    public XMLMapperBuilder(InputStream inputStream, Configuration configuration, String resource, Map<String, XNode> sqlFragments, String namespace) {	
        XMLMapperBuilder xMLMapperBuilder = this;	
        xMLMapperBuilder(inputStream, configuration, resource, sqlFragments);	
        xMLMapperBuilder.builderAssistant.setCurrentNamespace(namespace);	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    private /* synthetic */ Discriminator processDiscriminatorElement(XNode context, Class<?> resultType, List<ResultMapping> resultMappings) throws Exception {	
        Iterator<XNode> iterator;	
        XNode xNode = context;	
        String a = xNode.getStringAttribute("column");	
        String a2 = xNode.getStringAttribute("javaType");	
        String a3 = xNode.getStringAttribute("jdbcType");	
        String a4 = xNode.getStringAttribute("typeHander");	
        XMLMapperBuilder xMLMapperBuilder = this;	
        Class a5 = xMLMapperBuilder.resolveClass(a2);	
        Class a6 = xMLMapperBuilder.resolveClass(a4);	
        JdbcType a7 = xMLMapperBuilder.resolveJdbcType(a3);	
        HashMap<String, String> a8 = new HashMap<String, String>();	
        Iterator<XNode> iterator2 = iterator = xNode.getChildren().iterator();	
        while (iterator2.hasNext()) {	
            void a9;	
            void a10;	
            XNode xNode2 = iterator.next();	
            String a11 = xNode2.getStringAttribute("value");	
            void v3 = a10;	
            String string = v3.getStringAttribute("resutMap", this.processNestedResultMappings((XNode)v3, resultMappings));	
            iterator2 = iterator;	
            a8.put(a11, (String)a9);	
        }	
        return this.builderAssistant.buildDiscriminator(resultType, a, a5, a7, a6, a8);	
    }	
	
    private /* synthetic */ void buildStatementFromContext(List<XNode> list) {	
        if (this.configuration.getDatabaseId() != null) {	
            XMLMapperBuilder xMLMapperBuilder = this;	
            xMLMapperBuilder.buildStatementFromContext(list, xMLMapperBuilder.configuration.getDatabaseId());	
        }	
        this.buildStatementFromContext(list, null);	
    }	
	
    private /* synthetic */ ResultMapping buildResultMappingFromContext(XNode context, Class<?> resultType, List<ResultFlag> flags) throws Exception {	
        String a;	
        XNode xNode;	
        if (flags.contains((Object)ResultFlag.CONSTRUCTOR)) {	
            XNode xNode2 = context;	
            xNode = xNode2;	
            a = xNode2.getStringAttribute("name");	
        } else {	
            XNode xNode3 = context;	
            xNode = xNode3;	
            a = xNode3.getStringAttribute("property");	
        }	
        String a2 = xNode.getStringAttribute("column");	
        XNode xNode4 = context;	
        String a3 = xNode4.getStringAttribute("javaType");	
        String a4 = xNode4.getStringAttribute("jdbcType");	
        String a5 = xNode4.getStringAttribute("select");	
        String a6 = xNode4.getStringAttribute("resultMap", this.processNestedResultMappings(context, Collections.emptyList()));	
        String a7 = xNode4.getStringAttribute("notNulColumn");	
        String a8 = xNode4.getStringAttribute("columnPrefix");	
        String a9 = xNode4.getStringAttribute("typeHander");	
        String a10 = xNode4.getStringAttribute("resultSet");	
        String a11 = xNode4.getStringAttribute("foreignColumn");	
        boolean a12 = "lazy".equals(context.getStringAttribute("fetchType", this.configuration.isLazyLoadingEnabled() ? "lazy" : "eager"));	
        XMLMapperBuilder xMLMapperBuilder = this;	
        Class a13 = xMLMapperBuilder.resolveClass(a3);	
        Class a14 = xMLMapperBuilder.resolveClass(a9);	
        JdbcType a15 = xMLMapperBuilder.resolveJdbcType(a4);	
        return xMLMapperBuilder.builderAssistant.buildResultMapping(resultType, a, a2, a13, a15, a5, a6, a7, a8, a14, flags, a10, a11, a12);	
    }	
	
    private /* synthetic */ void sqlElement(List<XNode> list) throws Exception {	
        if (this.configuration.getDatabaseId() != null) {	
            XMLMapperBuilder xMLMapperBuilder = this;	
            xMLMapperBuilder.sqlElement(list, xMLMapperBuilder.configuration.getDatabaseId());	
        }	
        this.sqlElement(list, null);	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    private /* synthetic */ ResultMap resultMapElement(XNode resultMapNode, List<ResultMapping> additionalResultMappings) throws Exception {	
        void a;	
        ErrorContext.instance().activity(new StringBuilder().insert(0, "processing ").append(resultMapNode.getValueBasedIdentifier()).toString());	
        XNode xNode = resultMapNode;	
        XNode xNode2 = resultMapNode;	
        String string = xNode2.getStringAttribute("id", xNode2.getValueBasedIdentifier());	
        XNode xNode3 = resultMapNode;	
        XNode xNode4 = resultMapNode;	
        String a2 = xNode.getStringAttribute("type", xNode3.getStringAttribute("ofType", xNode4.getStringAttribute("resultType", xNode4.getStringAttribute("javaType"))));	
        String a3 = xNode3.getStringAttribute("extends");	
        Boolean a4 = xNode.getBooleanAttribute("autoMapping");	
        Class a5 = this.resolveClass(a2);	
        Discriminator a6 = null;	
        ArrayList<ResultMapping> a7 = new ArrayList<ResultMapping>();	
        a7.addAll(additionalResultMappings);	
        Iterator<XNode> iterator = resultMapNode.getChildren().iterator();	
        while (iterator.hasNext()) {	
            XNode a8 = iterator.next();	
            if ("constructor".equals(a8.getName())) {	
                this.processConstructorElement(a8, a5, a7);	
                continue;	
            }	
            if ("discriminator".equals(a8.getName())) {	
                a6 = this.processDiscriminatorElement(a8, a5, a7);	
                continue;	
            }	
            ArrayList<ResultFlag> a9 = new ArrayList<ResultFlag>();	
            if ("id".equals(a8.getName())) {	
                a9.add(ResultFlag.ID);	
            }	
            a7.add(this.buildResultMappingFromContext(a8, a5, a9));	
        }	
        ResultMapResolver a10 = new ResultMapResolver(this.builderAssistant, (String)a, a5, a3, a6, a7, a4);	
        try {	
            return a10.resolve();	
        }	
        catch (IncompleteElementException a8) {	
            this.configuration.addIncompleteResultMap(a10);	
            throw a8;	
        }	
    }	
	
    private /* synthetic */ void configurationElement(XNode context) {	
        try {	
            String a = context.getStringAttribute("namespace");	
            if (a == null || a.equals("")) {	
                throw new BuilderException("Mapper's namespace cannot be empty");	
            }	
            XNode xNode = context;	
            XMLMapperBuilder xMLMapperBuilder = this;	
            XNode xNode2 = context;	
            XMLMapperBuilder xMLMapperBuilder2 = this;	
            xMLMapperBuilder2.builderAssistant.setCurrentNamespace(a);	
            xMLMapperBuilder2.cacheRefElement(context.evalNode("cache-ref"));	
            this.cacheElement(xNode2.evalNode("cache"));	
            xMLMapperBuilder.parameterMapElement(xNode2.evalNodes("/mapper/parameterMap"));	
            xMLMapperBuilder.resultMapElements(context.evalNodes("/mapper/resutMap"));	
            this.sqlElement(xNode.evalNodes("/mapper/sql"));	
            this.buildStatementFromContext(xNode.evalNodes("seect|insert|update|delete"));	
            return;	
        }	
        catch (Exception a) {	
            throw new BuilderException(new StringBuilder().insert(0, "Error parsing Mapper XML. The XML location is '").append(this.resource).append("'. Cause: ").append(a).toString(), a);	
        }	
    }	
	
    private /* synthetic */ void resultMapElements(List<XNode> list) throws Exception {	
        for (XNode a : list) {	
            try {	
                this.resultMapElement(a);	
            }	
            catch (IncompleteElementException incompleteElementException) {}	
        }	
    }	
	
    private /* synthetic */ void sqlElement(List<XNode> list, String requiredDatabaseId) throws Exception {	
        for (XNode a : list) {	
            String a2 = a.getStringAttribute("database#d");	
            String a3 = a.getStringAttribute("id");	
            XMLMapperBuilder xMLMapperBuilder = this;	
            if (!xMLMapperBuilder.databaseIdMatchesCurrent(a3 = xMLMapperBuilder.builderAssistant.applyCurrentNamespace(a3, false), a2, requiredDatabaseId)) continue;	
            this.sqlFragments.put(a3, a);	
        }	
    }	
	
    public XNode getSqlFragment(String refid) {	
        return this.sqlFragments.get(refid);	
    }	
	
    private /* synthetic */ ResultMap resultMapElement(XNode resultMapNode) throws Exception {	
        return this.resultMapElement(resultMapNode, Collections.emptyList());	
    }	
	
    @Deprecated	
    public XMLMapperBuilder(Reader reader, Configuration configuration, String resource, Map<String, XNode> sqlFragments, String namespace) {	
        XMLMapperBuilder xMLMapperBuilder = this;	
        xMLMapperBuilder(reader, configuration, resource, sqlFragments);	
        xMLMapperBuilder.builderAssistant.setCurrentNamespace(namespace);	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    @Deprecated	
    public XMLMapperBuilder(Reader reader, Configuration configuration, String string, Map<String, XNode> map) {	
        void resource;	
        void configuration2;	
        void sqlFragments;	
        void reader2;	
        this(new XPathParser((Reader)reader2, true, configuration2.getVariables(), (EntityResolver)new XMLMapperEntityResolver()), (Configuration)configuration2, (String)resource, (Map<String, XNode>)sqlFragments);	
    }	
	
    private /* synthetic */ void cacheRefElement(XNode context) {	
        if (context != null) {	
            XMLMapperBuilder xMLMapperBuilder = this;	
            xMLMapperBuilder.configuration.addCacheRef(xMLMapperBuilder.builderAssistant.getCurrentNamespace(), context.getStringAttribute("namespace"));	
            CacheRefResolver a = new CacheRefResolver(this.builderAssistant, context.getStringAttribute("namespace"));	
            try {	
                a.resolveCacheRef();	
                return;	
            }	
            catch (IncompleteElementException a2) {	
                this.configuration.addIncompleteCacheRef(a);	
            }	
        }	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    private /* synthetic */ void processConstructorElement(XNode resultChild, Class<?> resultType, List<ResultMapping> resultMappings) throws Exception {	
        Iterator<XNode> iterator;	
        Iterator<XNode> iterator2 = iterator = resultChild.getChildren().iterator();	
        while (iterator2.hasNext()) {	
            void a;	
            XNode a2 = iterator.next();	
            ArrayList arrayList = new ArrayList();	
            a.add(ResultFlag.CONSTRUCTOR);	
            if ("idArg".equals(a2.getName())) {	
                a.add(ResultFlag.ID);	
            }	
            resultMappings.add(this.buildResultMappingFromContext(a2, resultType, (List<ResultFlag>)a));	
            iterator2 = iterator;	
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
	
    private /* synthetic */ String processNestedResultMappings(XNode context, List<ResultMapping> resultMappings) throws Exception {	
        if (("association".equals(context.getName()) || "collection".equals(context.getName()) || "case".equals(context.getName())) && context.getStringAttribute("select") == null) {	
            return this.resultMapElement(context, resultMappings).getId();	
        }	
        return null;	
    }	
	
    /*	
     * WARNING - Removed try catching itself - possible behaviour change.	
     * Enabled aggressive block sorting	
     * Enabled unnecessary exception pruning	
     * Enabled aggressive exception aggregation	
     */	
    private /* synthetic */ void parsePendingStatements() {	
        Collection<XMLStatementBuilder> a;	
        Collection<XMLStatementBuilder> collection = a = this.configuration.getIncompleteStatements();	
        synchronized (a) {	
            Iterator<XMLStatementBuilder> a2;	
            Iterator<XMLStatementBuilder> iterator = a2 = a.iterator();	
            do {	
                if (!iterator.hasNext()) {	
                    // ** MonitorExit[var2_2] (shouldn't be in output)	
                    return;	
                }	
                try {	
                    a2.next().parseStatementNode();	
                    a2.remove();	
                    iterator = a2;	
                }	
                catch (IncompleteElementException incompleteElementException) {	
                    iterator = a2;	
                    continue;	
                }	
                break;	
            } while (true);	
        }	
    }	
}	
	
