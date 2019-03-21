/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  org.apache.ibatis.builder.xml.XMLConfigBuilder	
 *  org.apache.ibatis.cache.Cache	
 *  org.apache.ibatis.executor.ErrorContext	
 *  org.apache.ibatis.io.VFS	
 *  org.apache.ibatis.logging.Log	
 *  org.apache.ibatis.logging.LogFactory	
 *  org.apache.ibatis.mapping.DatabaseIdProvider	
 *  org.apache.ibatis.mapping.Environment	
 *  org.apache.ibatis.parsing.XNode	
 *  org.apache.ibatis.plugin.Interceptor	
 *  org.apache.ibatis.reflection.factory.ObjectFactory	
 *  org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory	
 *  org.apache.ibatis.session.Configuration	
 *  org.apache.ibatis.session.SqlSessionFactory	
 *  org.apache.ibatis.session.SqlSessionFactoryBuilder	
 *  org.apache.ibatis.transaction.TransactionFactory	
 *  org.apache.ibatis.type.TypeAliasRegistry	
 *  org.apache.ibatis.type.TypeHandler	
 *  org.apache.ibatis.type.TypeHandlerRegistry	
 *  org.mybatis.spring.transaction.SpringManagedTransactionFactory	
 *  org.springframework.beans.factory.FactoryBean	
 *  org.springframework.beans.factory.InitializingBean	
 *  org.springframework.context.ApplicationEvent	
 *  org.springframework.context.ApplicationListener	
 *  org.springframework.context.event.ContextRefreshedEvent	
 *  org.springframework.core.NestedIOException	
 *  org.springframework.core.io.Resource	
 *  org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy	
 *  org.springframework.util.Assert	
 *  org.springframework.util.ObjectUtils	
 *  org.springframework.util.StringUtils	
 */	
package com.jeesite.common.mybatis;	
	
import com.jeesite.autoconfigure.sys.MsgAutoConfiguration;	
import com.jeesite.common.mybatis.MapperRefresh;	
import com.jeesite.common.mybatis.mapper.query.QueryWhere;	
import com.jeesite.common.mybatis.mapper.xml.XMLMapperBuilder;	
import com.jeesite.common.mybatis.mapper.xmltags.XMLLanguageDriver;	
import java.io.IOException;	
import java.io.InputStream;	
import java.sql.SQLException;	
import java.util.Collection;	
import java.util.Map;	
import java.util.Properties;	
import javax.sql.DataSource;	
import org.apache.ibatis.builder.xml.XMLConfigBuilder;	
import org.apache.ibatis.cache.Cache;	
import org.apache.ibatis.executor.ErrorContext;	
import org.apache.ibatis.io.VFS;	
import org.apache.ibatis.logging.Log;	
import org.apache.ibatis.logging.LogFactory;	
import org.apache.ibatis.mapping.DatabaseIdProvider;	
import org.apache.ibatis.mapping.Environment;	
import org.apache.ibatis.parsing.XNode;	
import org.apache.ibatis.plugin.Interceptor;	
import org.apache.ibatis.reflection.factory.ObjectFactory;	
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;	
import org.apache.ibatis.session.Configuration;	
import org.apache.ibatis.session.SqlSessionFactory;	
import org.apache.ibatis.session.SqlSessionFactoryBuilder;	
import org.apache.ibatis.transaction.TransactionFactory;	
import org.apache.ibatis.type.TypeAliasRegistry;	
import org.apache.ibatis.type.TypeHandler;	
import org.apache.ibatis.type.TypeHandlerRegistry;	
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;	
import org.springframework.beans.factory.FactoryBean;	
import org.springframework.beans.factory.InitializingBean;	
import org.springframework.context.ApplicationEvent;	
import org.springframework.context.ApplicationListener;	
import org.springframework.context.event.ContextRefreshedEvent;	
import org.springframework.core.NestedIOException;	
import org.springframework.core.io.Resource;	
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;	
import org.springframework.util.Assert;	
import org.springframework.util.ObjectUtils;	
import org.springframework.util.StringUtils;	
	
public class SqlSessionFactoryBean	
implements FactoryBean<SqlSessionFactory>,	
InitializingBean,	
ApplicationListener<ApplicationEvent> {	
    private String typeAliasesPackage;	
    private ObjectFactory objectFactory;	
    private ObjectWrapperFactory objectWrapperFactory;	
    private Configuration configuration;	
    private Resource[] mapperLocations;	
    private SqlSessionFactory sqlSessionFactory;	
    private Class<?> typeAliasesSuperType;	
    private DatabaseIdProvider databaseIdProvider;	
    private TypeHandler<?>[] typeHandlers;	
    private SqlSessionFactoryBuilder sqlSessionFactoryBuilder;	
    private String typeHandlersPackage;	
    private Properties configurationProperties;	
    private TransactionFactory transactionFactory;	
    private String environment;	
    private static final Log LOGGER = LogFactory.getLog(SqlSessionFactoryBean.class);	
    private DataSource dataSource;	
    private Class<?>[] typeAliases;	
    private Interceptor[] plugins;	
    private boolean failFast;	
    private Cache cache;	
    private Resource configLocation;	
    private Class<? extends VFS> vfs;	
	
    public SqlSessionFactoryBean() {	
        SqlSessionFactoryBean sqlSessionFactoryBean = this;	
        this.sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();	
        this.environment = SqlSessionFactoryBean.class.getSimpleName();	
    }	
	
    public void setConfigurationProperties(Properties sqlSessionFactoryProperties) {	
        this.configurationProperties = sqlSessionFactoryProperties;	
    }	
	
    public void setTypeHandlers(TypeHandler<?>[] typeHandlers) {	
        this.typeHandlers = typeHandlers;	
    }	
	
    public Class<? extends VFS> getVfs() {	
        return this.vfs;	
    }	
	
    public void setObjectFactory(ObjectFactory objectFactory) {	
        this.objectFactory = objectFactory;	
    }	
	
    public void setTransactionFactory(TransactionFactory transactionFactory) {	
        this.transactionFactory = transactionFactory;	
    }	
	
    /*	
     * Unable to fully structure code	
     * Enabled aggressive block sorting	
     * Enabled unnecessary exception pruning	
     * Enabled aggressive exception aggregation	
     * Lifted jumps to return sites	
     */	
    protected SqlSessionFactory buildSqlSessionFactory() throws IOException {	
        block56 : {	
            block54 : {	
                block55 : {	
                    a = null;	
                    if (this.configuration == null) break block54;	
                    a = this.configuration;	
                    if (a.getVariables() != null) break block55;	
                    v0 = this;	
                    v1 = v0;	
                    a.setVariables(v0.configurationProperties);	
                    break block56;	
                }	
                if (this.configurationProperties == null) ** GOTO lbl26	
                v2 = this;	
                v1 = v2;	
                a.getVariables().putAll(v2.configurationProperties);	
                break block56;	
            }	
            if (this.configLocation != null) {	
                a = new XMLConfigBuilder(this.configLocation.getInputStream(), null, this.configurationProperties);	
                a = a.getConfiguration();	
                v1 = this;	
            } else {	
                if (SqlSessionFactoryBean.LOGGER.isDebugEnabled()) {	
                    SqlSessionFactoryBean.LOGGER.debug("Property 'coniguration' or 'configLocation' not specified, using default MyBatis Coniguration");	
                }	
                a = new Configuration();	
                if (this.configurationProperties != null) {	
                    a.setVariables(this.configurationProperties);	
                }	
lbl26: // 4 sources:	
                v1 = this;	
            }	
        }	
        if (v1.objectFactory != null) {	
            a.setObjectFactory(this.objectFactory);	
        }	
        if (this.objectWrapperFactory != null) {	
            a.setObjectWrapperFactory(this.objectWrapperFactory);	
        }	
        if (this.vfs != null) {	
            a.setVfsImpl(this.vfs);	
        }	
        a = new MapperRefresh(this.mapperLocations, a);	
        if (StringUtils.hasLength((String)this.typeAliasesPackage)) {	
            a = StringUtils.tokenizeToStringArray((String)this.typeAliasesPackage, (String)",; \t\n");	
            var5_7 = a;	
            var6_12 = var5_7.length;	
            v3 = var7_13 = 0;	
            while (v3 < var6_12) {	
                a = var5_7[var7_13];	
                try {	
                    a.getTypeAliasRegistry().registerAliases(a, this.typeAliasesSuperType == null ? Object.class : this.typeAliasesSuperType);	
                }	
                catch (Exception a) {	
                    SqlSessionFactoryBean.LOGGER.error(new StringBuilder().insert(0, "Scanned package: '").append(a).append("' for aliases").toString(), (Throwable)a);	
                    throw new NestedIOException(new StringBuilder().insert(0, "Scanned package: '").append(a).append("' for aliases").toString(), (Throwable)a);	
                }	
                finally {	
                    ErrorContext.instance().reset();	
                }	
                if (SqlSessionFactoryBean.LOGGER.isDebugEnabled()) {	
                    SqlSessionFactoryBean.LOGGER.debug(new StringBuilder().insert(0, "Scanned package: '").append(a).append("' for aliases").toString());	
                }	
                v3 = ++var7_13;	
            }	
        }	
        if (!ObjectUtils.isEmpty((Object[])this.typeAliases)) {	
            a = this.typeAliases;	
            var5_8 = a.length;	
            v4 = var6_12 = 0;	
            while (v4 < var5_8) {	
                a = a[var6_12];	
                a.getTypeAliasRegistry().registerAlias((Class)a);	
                if (SqlSessionFactoryBean.LOGGER.isDebugEnabled()) {	
                    SqlSessionFactoryBean.LOGGER.debug(new StringBuilder().insert(0, "Registered type alias: '").append(a).append("'").toString());	
                }	
                v4 = ++var6_12;	
            }	
        }	
        if (!ObjectUtils.isEmpty((Object[])this.plugins)) {	
            a = this.plugins;	
            var5_9 = a.length;	
            v5 = var6_12 = 0;	
            while (v5 < var5_9) {	
                a = a[var6_12];	
                a.addInterceptor((Interceptor)a);	
                if (SqlSessionFactoryBean.LOGGER.isDebugEnabled()) {	
                    SqlSessionFactoryBean.LOGGER.debug(new StringBuilder().insert(0, "Registered plugin: '").append(a).append("'").toString());	
                }	
                v5 = ++var6_12;	
            }	
        }	
        if (StringUtils.hasLength((String)this.typeHandlersPackage)) {	
            a = StringUtils.tokenizeToStringArray((String)this.typeHandlersPackage, (String)",; \t\n");	
            var5_7 = a;	
            var6_12 = var5_7.length;	
            v6 = a = 0;	
            while (v6 < var6_12) {	
                a = var5_7[a];	
                a.getTypeHandlerRegistry().register(a);	
                if (SqlSessionFactoryBean.LOGGER.isDebugEnabled()) {	
                    SqlSessionFactoryBean.LOGGER.debug(new StringBuilder().insert(0, "Scanned package: '").append(a).append("' for type handlers").toString());	
                }	
                v6 = ++a;	
            }	
        }	
        if (!ObjectUtils.isEmpty((Object[])this.typeHandlers)) {	
            a = this.typeHandlers;	
            var5_10 = a.length;	
            v7 = var6_12 = 0;	
            while (v7 < var5_10) {	
                a = a[var6_12];	
                a.getTypeHandlerRegistry().register((TypeHandler)a);	
                if (SqlSessionFactoryBean.LOGGER.isDebugEnabled()) {	
                    SqlSessionFactoryBean.LOGGER.debug(new StringBuilder().insert(0, "Registered type handler: '").append(a).append("'").toString());	
                }	
                v7 = ++var6_12;	
            }	
        }	
        if (this.databaseIdProvider != null) {	
            try {	
                v8 = this;	
                a.setDatabaseId(v8.databaseIdProvider.getDatabaseId(v8.dataSource));	
                v9 = this;	
            }	
            catch (SQLException a) {	
                throw new NestedIOException("Failed getting a databaseId", (Throwable)a);	
            }	
        } else {	
            v9 = this;	
        }	
        if (v9.cache != null) {	
            a.addCache(this.cache);	
        }	
        if (a != null) {	
            try {	
                a.parse();	
                if (SqlSessionFactoryBean.LOGGER.isDebugEnabled()) {	
                    SqlSessionFactoryBean.LOGGER.debug(new StringBuilder().insert(0, "Parsed coniguration file: '").append((Object)this.configLocation).append("'").toString());	
                }	
                v10 = this;	
            }	
            catch (Exception a) {	
                throw new NestedIOException(new StringBuilder().insert(0, "Failed to parse conig resource: ").append((Object)this.configLocation).toString(), (Throwable)a);	
            }	
            finally {	
                ErrorContext.instance().reset();	
            }	
        } else {	
            v10 = this;	
        }	
        if (v10.transactionFactory == null) {	
            v11 = this;	
            v11.transactionFactory = new SpringManagedTransactionFactory();	
        }	
        v12 = this;	
        a.setEnvironment(new Environment(v12.environment, v12.transactionFactory, this.dataSource));	
        a.setDefaultScriptingLanguage(XMLLanguageDriver.class);	
        if (ObjectUtils.isEmpty((Object[])this.mapperLocations)) {	
            if (SqlSessionFactoryBean.LOGGER.isDebugEnabled()) {	
                SqlSessionFactoryBean.LOGGER.debug("Property 'mapperLocations' was not specified or no matching resources found");	
            }	
        } else {	
            a = this.mapperLocations;	
            var5_11 = a.length;	
            v13 = var6_12 = 0;	
            while (v13 < var5_11) {	
                a = a[var6_12];	
                if (a != null) {	
                    try {	
                        v14 = a;	
                        a = new XMLMapperBuilder(a.getInputStream(), v14, a.toString(), (Map<String, XNode>)v14.getSqlFragments());	
                        a.parse();	
                    }	
                    catch (Exception a) {	
                        SqlSessionFactoryBean.LOGGER.error(new StringBuilder().insert(0, "Failed to parse mapping resource: '").append(a).append("'").toString(), (Throwable)a);	
                        throw new NestedIOException(new StringBuilder().insert(0, "Failed to parse mapping resource: '").append(a).append("'").toString(), (Throwable)a);	
                    }	
                    finally {	
                        ErrorContext.instance().reset();	
                    }	
                    if (SqlSessionFactoryBean.LOGGER.isDebugEnabled()) {	
                        SqlSessionFactoryBean.LOGGER.debug(new StringBuilder().insert(0, "Parsed mapper file: '").append(a).append("'").toString());	
                    }	
                }	
                v13 = ++var6_12;	
            }	
        }	
        a.run();	
        return this.sqlSessionFactoryBuilder.build(a);	
    }	
	
    public void setTypeHandlersPackage(String typeHandlersPackage) {	
        this.typeHandlersPackage = typeHandlersPackage;	
    }	
	
    public void setEnvironment(String environment) {	
        this.environment = environment;	
    }	
	
    public void setObjectWrapperFactory(ObjectWrapperFactory objectWrapperFactory) {	
        this.objectWrapperFactory = objectWrapperFactory;	
    }	
	
    public void setConfiguration(Configuration configuration) {	
        this.configuration = configuration;	
    }	
	
    public void onApplicationEvent(ApplicationEvent event) {	
        if (this.failFast && event instanceof ContextRefreshedEvent) {	
            this.sqlSessionFactory.getConfiguration().getMappedStatementNames();	
        }	
    }	
	
    public void setPlugins(Interceptor[] plugins) {	
        this.plugins = plugins;	
    }	
	
    public boolean isSingleton() {	
        return true;	
    }	
	
    public Cache getCache() {	
        return this.cache;	
    }	
	
    public void setSqlSessionFactoryBuilder(SqlSessionFactoryBuilder sqlSessionFactoryBuilder) {	
        this.sqlSessionFactoryBuilder = sqlSessionFactoryBuilder;	
    }	
	
    public void setTypeAliasesPackage(String typeAliasesPackage) {	
        this.typeAliasesPackage = typeAliasesPackage;	
    }	
	
    public void setConfigLocation(Resource configLocation) {	
        this.configLocation = configLocation;	
    }	
	
    public void setVfs(Class<? extends VFS> vfs) {	
        this.vfs = vfs;	
    }	
	
    public SqlSessionFactory getObject() throws Exception {	
        if (this.sqlSessionFactory == null) {	
            this.afterPropertiesSet();	
        }	
        return this.sqlSessionFactory;	
    }	
	
    public void setDatabaseIdProvider(DatabaseIdProvider databaseIdProvider) {	
        this.databaseIdProvider = databaseIdProvider;	
    }	
	
    public Class<? extends SqlSessionFactory> getObjectType() {	
        if (this.sqlSessionFactory == null) {	
            return SqlSessionFactory.class;	
        }	
        return this.sqlSessionFactory.getClass();	
    }	
	
    public void setCache(Cache cache) {	
        this.cache = cache;	
    }	
	
    public DatabaseIdProvider getDatabaseIdProvider() {	
        return this.databaseIdProvider;	
    }	
	
    public void setTypeAliases(Class<?>[] typeAliases) {	
        this.typeAliases = typeAliases;	
    }	
	
    public void setFailFast(boolean failFast) {	
        this.failFast = failFast;	
    }	
	
    public void setDataSource(DataSource dataSource) {	
        if (dataSource instanceof TransactionAwareDataSourceProxy) {	
            this.dataSource = ((TransactionAwareDataSourceProxy)dataSource).getTargetDataSource();	
            return;	
        }	
        this.dataSource = dataSource;	
    }	
	
    public void afterPropertiesSet() throws Exception {	
        SqlSessionFactoryBean sqlSessionFactoryBean = this;	
        Assert.notNull((Object)sqlSessionFactoryBean.dataSource, (String)"Property 'dataSource' is required");	
        Assert.notNull((Object)sqlSessionFactoryBean.sqlSessionFactoryBuilder, (String)"Property 'sqlSessionFactoryBuilder' is required");	
        Assert.state((boolean)(sqlSessionFactoryBean.configuration == null && this.configLocation == null || this.configuration == null || this.configLocation == null), (String)"Property 'coniguration' and 'conigLocation' can not speciied with together");	
        this.sqlSessionFactory = this.buildSqlSessionFactory();	
    }	
	
    public void setTypeAliasesSuperType(Class<?> typeAliasesSuperType) {	
        this.typeAliasesSuperType = typeAliasesSuperType;	
    }	
	
    public void setMapperLocations(Resource[] mapperLocations) {	
        this.mapperLocations = mapperLocations;	
    }	
}	
	
