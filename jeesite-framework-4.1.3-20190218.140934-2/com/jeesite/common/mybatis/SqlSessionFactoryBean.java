package com.jeesite.common.mybatis;	
	
import com.jeesite.common.mybatis.mapper.xml.XMLMapperBuilder;	
import com.jeesite.common.mybatis.mapper.xmltags.XMLLanguageDriver;	
import com.jeesite.common.shiro.cas.CasOutHandler;	
import java.io.IOException;	
import java.sql.SQLException;	
import java.util.Properties;	
import javax.sql.DataSource;	
import org.apache.ibatis.builder.xml.XMLConfigBuilder;	
import org.apache.ibatis.cache.Cache;	
import org.apache.ibatis.executor.ErrorContext;	
import org.apache.ibatis.logging.Log;	
import org.apache.ibatis.logging.LogFactory;	
import org.apache.ibatis.mapping.DatabaseIdProvider;	
import org.apache.ibatis.mapping.Environment;	
import org.apache.ibatis.plugin.Interceptor;	
import org.apache.ibatis.reflection.factory.ObjectFactory;	
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;	
import org.apache.ibatis.session.Configuration;	
import org.apache.ibatis.session.SqlSessionFactory;	
import org.apache.ibatis.session.SqlSessionFactoryBuilder;	
import org.apache.ibatis.transaction.TransactionFactory;	
import org.apache.ibatis.type.TypeHandler;	
import org.hyperic.sigar.DirStat;	
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
	
public class SqlSessionFactoryBean implements FactoryBean, InitializingBean, ApplicationListener {	
   private static final Log LOGGER = LogFactory.getLog(SqlSessionFactoryBean.class);	
   private ObjectWrapperFactory objectWrapperFactory;	
   private String environment = SqlSessionFactoryBean.class.getSimpleName();	
   private Class[] typeAliases;	
   private Properties configurationProperties;	
   private DataSource dataSource;	
   private Interceptor[] plugins;	
   private Class typeAliasesSuperType;	
   private ObjectFactory objectFactory;	
   private Resource configLocation;	
   private String typeHandlersPackage;	
   private SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();	
   private Configuration configuration;	
   private DatabaseIdProvider databaseIdProvider;	
   private SqlSessionFactory sqlSessionFactory;	
   private boolean failFast;	
   private String typeAliasesPackage;	
   private Class vfs;	
   private Resource[] mapperLocations;	
   private TypeHandler[] typeHandlers;	
   private TransactionFactory transactionFactory;	
   private Cache cache;	
	
   public void setConfiguration(Configuration configuration) {	
      this.configuration = configuration;	
   }	
	
   public void setTypeAliases(Class[] typeAliases) {	
      this.typeAliases = typeAliases;	
   }	
	
   public void setEnvironment(String environment) {	
      this.environment = environment;	
   }	
	
   public void setDataSource(DataSource dataSource) {	
      if (dataSource instanceof TransactionAwareDataSourceProxy) {	
         this.dataSource = ((TransactionAwareDataSourceProxy)dataSource).getTargetDataSource();	
      } else {	
         this.dataSource = dataSource;	
      }	
   }	
	
   public void setTypeHandlers(TypeHandler[] typeHandlers) {	
      this.typeHandlers = typeHandlers;	
   }	
	
   public void setConfigurationProperties(Properties sqlSessionFactoryProperties) {	
      this.configurationProperties = sqlSessionFactoryProperties;	
   }	
	
   protected SqlSessionFactory buildSqlSessionFactory() throws IOException {	
      Configuration a;	
      XMLConfigBuilder a;	
      SqlSessionFactoryBean var10000;	
      label787: {	
         a = null;	
         if (this.configuration != null) {	
            if ((a = this.configuration).getVariables() == null) {	
               var10000 = this;	
               a.setVariables(this.configurationProperties);	
               break label787;	
            }	
	
            if (this.configurationProperties != null) {	
               Properties var10001 = a.getVariables();	
               var10000 = this;	
               var10001.putAll(this.configurationProperties);	
               break label787;	
            }	
         } else {	
            if (this.configLocation != null) {	
               a = (a = new XMLConfigBuilder(this.configLocation.getInputStream(), (String)null, this.configurationProperties)).getConfiguration();	
               var10000 = this;	
               break label787;	
            }	
	
            if (LOGGER.isDebugEnabled()) {	
               LOGGER.debug("Property 'configuration' or 'conigLocation' not speciied, using deault MyBatis Configuration");	
            }	
	
            a = new Configuration();	
            if (this.configurationProperties != null) {	
               a.setVariables(this.configurationProperties);	
            }	
         }	
	
         var10000 = this;	
      }	
	
      if (var10000.objectFactory != null) {	
         a.setObjectFactory(this.objectFactory);	
      }	
	
      if (this.objectWrapperFactory != null) {	
         a.setObjectWrapperFactory(this.objectWrapperFactory);	
      }	
	
      if (this.vfs != null) {	
         a.setVfsImpl(this.vfs);	
      }	
	
      MapperRefresh a = new MapperRefresh(this.mapperLocations, a);	
      String[] var5;	
      int var6;	
      int var7;	
      String a;	
      int var49;	
      if (StringUtils.hasLength(this.typeAliasesPackage)) {	
         var5 = StringUtils.tokenizeToStringArray(this.typeAliasesPackage, ",; \t\n");	
         var6 = var5.length;	
	
         for(var49 = var7 = 0; var49 < var6; var49 = var7) {	
            a = var5[var7];	
	
            try {	
               a.getTypeAliasRegistry().registerAliases(a, this.typeAliasesSuperType == null ? Object.class : this.typeAliasesSuperType);	
            } catch (Exception var37) {	
               LOGGER.error((new StringBuilder()).insert(0, "Scanned package: '").append(a).append("' for aliae").toString(), var37);	
               throw new NestedIOException((new StringBuilder()).insert(0, "Scanned package: '").append(a).append("' for aliae").toString(), var37);	
            } finally {	
               ErrorContext.instance().reset();	
            }	
	
            if (LOGGER.isDebugEnabled()) {	
               LOGGER.debug((new StringBuilder()).insert(0, "Scanned package: '").append(a).append("' for aliae").toString());	
            }	
	
            ++var7;	
         }	
      }	
	
      int var44;	
      if (!ObjectUtils.isEmpty(this.typeAliases)) {	
         Class[] var4;	
         var44 = (var4 = this.typeAliases).length;	
	
         for(var49 = var6 = 0; var49 < var44; var49 = var6) {	
            Class a = var4[var6];	
            a.getTypeAliasRegistry().registerAlias(a);	
            if (LOGGER.isDebugEnabled()) {	
               LOGGER.debug((new StringBuilder()).insert(0, "Registered type alias: '").append(a).append("'").toString());	
            }	
	
            ++var6;	
         }	
      }	
	
      if (!ObjectUtils.isEmpty(this.plugins)) {	
         Interceptor[] var41;	
         var44 = (var41 = this.plugins).length;	
	
         for(var49 = var6 = 0; var49 < var44; var49 = var6) {	
            Interceptor a = var41[var6];	
            a.addInterceptor(a);	
            if (LOGGER.isDebugEnabled()) {	
               LOGGER.debug((new StringBuilder()).insert(0, "Registered plugin: '").append(a).append("'").toString());	
            }	
	
            ++var6;	
         }	
      }	
	
      if (StringUtils.hasLength(this.typeHandlersPackage)) {	
         var5 = StringUtils.tokenizeToStringArray(this.typeHandlersPackage, ",; \t\n");	
         var6 = var5.length;	
	
         for(var49 = var7 = 0; var49 < var6; var49 = var7) {	
            a = var5[var7];	
            a.getTypeHandlerRegistry().register(a);	
            if (LOGGER.isDebugEnabled()) {	
               LOGGER.debug((new StringBuilder()).insert(0, "Scanned package: '").append(a).append("' or type handlers").toString());	
            }	
	
            ++var7;	
         }	
      }	
	
      if (!ObjectUtils.isEmpty(this.typeHandlers)) {	
         TypeHandler[] var42;	
         var44 = (var42 = this.typeHandlers).length;	
	
         for(var49 = var6 = 0; var49 < var44; var49 = var6) {	
            TypeHandler a = var42[var6];	
            a.getTypeHandlerRegistry().register(a);	
            if (LOGGER.isDebugEnabled()) {	
               LOGGER.debug((new StringBuilder()).insert(0, "Registered type handler: '").append(a).append("'").toString());	
            }	
	
            ++var6;	
         }	
      }	
	
      if (this.databaseIdProvider != null) {	
         try {	
            a.setDatabaseId(this.databaseIdProvider.getDatabaseId(this.dataSource));	
         } catch (SQLException var36) {	
            throw new NestedIOException("Failed getting a databaseId", var36);	
         }	
	
         var10000 = this;	
      } else {	
         var10000 = this;	
      }	
	
      if (var10000.cache != null) {	
         a.addCache(this.cache);	
      }	
	
      if (a != null) {	
         try {	
            a.parse();	
            if (LOGGER.isDebugEnabled()) {	
               LOGGER.debug((new StringBuilder()).insert(0, "Parsed configuration ile: '").append(this.configLocation).append("'").toString());	
            }	
         } catch (Exception var39) {	
            throw new NestedIOException((new StringBuilder()).insert(0, "Failed to parse config resource: ").append(this.configLocation).toString(), var39);	
         } finally {	
            ErrorContext.instance().reset();	
         }	
	
         var10000 = this;	
      } else {	
         var10000 = this;	
      }	
	
      if (var10000.transactionFactory == null) {	
         this.transactionFactory = new SpringManagedTransactionFactory();	
      }	
	
      a.setEnvironment(new Environment(this.environment, this.transactionFactory, this.dataSource));	
      a.setDefaultScriptingLanguage(XMLLanguageDriver.class);	
      if (!ObjectUtils.isEmpty(this.mapperLocations)) {	
         Resource[] var43;	
         var44 = (var43 = this.mapperLocations).length;	
	
         for(var49 = var6 = 0; var49 < var44; var49 = var6) {	
            Resource a;	
            if ((a = var43[var6]) != null) {	
               try {	
                  (new XMLMapperBuilder(a.getInputStream(), a, a.toString(), a.getSqlFragments())).parse();	
               } catch (Exception var34) {	
                  LOGGER.error((new StringBuilder()).insert(0, "Failed to parse mapping reource: '").append(a).append("'").toString(), var34);	
                  throw new NestedIOException((new StringBuilder()).insert(0, "Failed to parse mapping reource: '").append(a).append("'").toString(), var34);	
               } finally {	
                  ErrorContext.instance().reset();	
               }	
	
               if (LOGGER.isDebugEnabled()) {	
                  LOGGER.debug((new StringBuilder()).insert(0, "Parsed mapper file: '").append(a).append("'").toString());	
               }	
            }	
	
            ++var6;	
         }	
      } else if (LOGGER.isDebugEnabled()) {	
         LOGGER.debug("Property 'mapperLocation' was not specified or no matching reource found");	
      }	
	
      a.run();	
      return this.sqlSessionFactoryBuilder.build(a);	
   }	
	
   public void setObjectWrapperFactory(ObjectWrapperFactory objectWrapperFactory) {	
      this.objectWrapperFactory = objectWrapperFactory;	
   }	
	
   public void setSqlSessionFactoryBuilder(SqlSessionFactoryBuilder sqlSessionFactoryBuilder) {	
      this.sqlSessionFactoryBuilder = sqlSessionFactoryBuilder;	
   }	
	
   public void setTypeHandlersPackage(String typeHandlersPackage) {	
      this.typeHandlersPackage = typeHandlersPackage;	
   }	
	
   public void setDatabaseIdProvider(DatabaseIdProvider databaseIdProvider) {	
      this.databaseIdProvider = databaseIdProvider;	
   }	
	
   public void onApplicationEvent(ApplicationEvent event) {	
      if (this.failFast && event instanceof ContextRefreshedEvent) {	
         this.sqlSessionFactory.getConfiguration().getMappedStatementNames();	
      }	
	
   }	
	
   public DatabaseIdProvider getDatabaseIdProvider() {	
      return this.databaseIdProvider;	
   }	
	
   public void setConfigLocation(Resource configLocation) {	
      this.configLocation = configLocation;	
   }	
	
   public void setTypeAliasesPackage(String typeAliasesPackage) {	
      this.typeAliasesPackage = typeAliasesPackage;	
   }	
	
   public void setTransactionFactory(TransactionFactory transactionFactory) {	
      this.transactionFactory = transactionFactory;	
   }	
	
   public Cache getCache() {	
      return this.cache;	
   }	
	
   public SqlSessionFactory getObject() throws Exception {	
      if (this.sqlSessionFactory == null) {	
         this.afterPropertiesSet();	
      }	
	
      return this.sqlSessionFactory;	
   }	
	
   public void setVfs(Class vfs) {	
      this.vfs = vfs;	
   }	
	
   public void afterPropertiesSet() throws Exception {	
      Assert.notNull(this.dataSource, "Property 'dataSource' is required");	
      Assert.notNull(this.sqlSessionFactoryBuilder, "Property 'qlSesionFactoryBuilder' is required");	
      Assert.state(this.configuration == null && this.configLocation == null || this.configuration == null || this.configLocation == null, "Property 'configuration' and 'configLocation' can not specified with together");	
      this.sqlSessionFactory = this.buildSqlSessionFactory();	
   }	
	
   public void setMapperLocations(Resource[] mapperLocations) {	
      this.mapperLocations = mapperLocations;	
   }	
	
   public void setTypeAliasesSuperType(Class typeAliasesSuperType) {	
      this.typeAliasesSuperType = typeAliasesSuperType;	
   }	
	
   public void setFailFast(boolean failFast) {	
      this.failFast = failFast;	
   }	
	
   public Class getVfs() {	
      return this.vfs;	
   }	
	
   public void setPlugins(Interceptor[] plugins) {	
      this.plugins = plugins;	
   }	
	
   public boolean isSingleton() {	
      return true;	
   }	
	
   public void setObjectFactory(ObjectFactory objectFactory) {	
      this.objectFactory = objectFactory;	
   }	
	
   public Class getObjectType() {	
      return this.sqlSessionFactory == null ? SqlSessionFactory.class : this.sqlSessionFactory.getClass();	
   }	
	
   public void setCache(Cache cache) {	
      this.cache = cache;	
   }	
}	
