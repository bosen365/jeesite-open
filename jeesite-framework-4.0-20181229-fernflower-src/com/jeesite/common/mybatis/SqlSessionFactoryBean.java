package com.jeesite.common.mybatis;	
	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.modules.sys.utils.ModuleUtils;	
import java.io.IOException;	
import java.sql.SQLException;	
import java.util.Properties;	
import javax.sql.DataSource;	
import org.apache.ibatis.builder.xml.XMLConfigBuilder;	
import org.apache.ibatis.builder.xml.XMLMapperBuilder;	
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
   private Class typeAliasesSuperType;	
   private String environment = SqlSessionFactoryBean.class.getSimpleName();	
   private boolean failFast;	
   private Interceptor[] plugins;	
   private SqlSessionFactory sqlSessionFactory;	
   private String typeAliasesPackage;	
   private ObjectFactory objectFactory;	
   private String typeHandlersPackage;	
   private Configuration configuration;	
   private Resource configLocation;	
   private DataSource dataSource;	
   private Class vfs;	
   private static final Log LOGGER = LogFactory.getLog(SqlSessionFactoryBean.class);	
   private Resource[] mapperLocations;	
   private DatabaseIdProvider databaseIdProvider;	
   private SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();	
   private TypeHandler[] typeHandlers;	
   private Properties configurationProperties;	
   private ObjectWrapperFactory objectWrapperFactory;	
   private Class[] typeAliases;	
   private Cache cache;	
   private TransactionFactory transactionFactory;	
	
   public void setConfiguration(Configuration configuration) {	
      this.configuration = configuration;	
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
               LOGGER.debug("Property 'configuration' or 'configLocation' not specified, using default MyBati Configuration");	
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
               LOGGER.error((new StringBuilder()).insert(0, "Scanned package: '").append(a).append("' for aliases").toString(), var37);	
               throw new NestedIOException((new StringBuilder()).insert(0, "Scanned package: '").append(a).append("' for aliases").toString(), var37);	
            } finally {	
               ErrorContext.instance().reset();	
            }	
	
            if (LOGGER.isDebugEnabled()) {	
               LOGGER.debug((new StringBuilder()).insert(0, "Scanned package: '").append(a).append("' for aliases").toString());	
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
               LOGGER.debug((new StringBuilder()).insert(0, "Scanned package: '").append(a).append("' for type handler").toString());	
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
            throw new NestedIOException("Failed getting a dataaseId", var36);	
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
               LOGGER.debug((new StringBuilder()).insert(0, "Pared configuration file: '").append(this.configLocation).append("'").toString());	
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
      if (!ObjectUtils.isEmpty(this.mapperLocations)) {	
         Resource[] var43;	
         var44 = (var43 = this.mapperLocations).length;	
	
         for(var49 = var6 = 0; var49 < var44; var49 = var6) {	
            Resource a;	
            if ((a = var43[var6]) != null) {	
               try {	
                  (new XMLMapperBuilder(a.getInputStream(), a, a.toString(), a.getSqlFragments())).parse();	
               } catch (Exception var34) {	
                  LOGGER.error((new StringBuilder()).insert(0, "Failed to parse mapping resource: '").append(a).append("'").toString(), var34);	
                  throw new NestedIOException((new StringBuilder()).insert(0, "Failed to parse mapping resource: '").append(a).append("'").toString(), var34);	
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
         LOGGER.debug("Property 'mapperLocations' was not specified or no matching resources found");	
      }	
	
      a.run();	
      return this.sqlSessionFactoryBuilder.build(a);	
   }	
	
   public void setTypeAliasesSuperType(Class typeAliasesSuperType) {	
      this.typeAliasesSuperType = typeAliasesSuperType;	
   }	
	
   public void setTypeHandlers(TypeHandler[] typeHandlers) {	
      this.typeHandlers = typeHandlers;	
   }	
	
   public void setObjectWrapperFactory(ObjectWrapperFactory objectWrapperFactory) {	
      this.objectWrapperFactory = objectWrapperFactory;	
   }	
	
   public void setDataSource(DataSource dataSource) {	
      if (dataSource instanceof TransactionAwareDataSourceProxy) {	
         this.dataSource = ((TransactionAwareDataSourceProxy)dataSource).getTargetDataSource();	
      } else {	
         this.dataSource = dataSource;	
      }	
   }	
	
   public void setTypeAliasesPackage(String typeAliasesPackage) {	
      this.typeAliasesPackage = typeAliasesPackage;	
   }	
	
   public SqlSessionFactory getObject() throws Exception {	
      if (this.sqlSessionFactory == null) {	
         this.afterPropertiesSet();	
      }	
	
      return this.sqlSessionFactory;	
   }	
	
   public void setMapperLocations(Resource[] mapperLocations) {	
      this.mapperLocations = mapperLocations;	
   }	
	
   public void setEnvironment(String environment) {	
      this.environment = environment;	
   }	
	
   public void setObjectFactory(ObjectFactory objectFactory) {	
      this.objectFactory = objectFactory;	
   }	
	
   public boolean isSingleton() {	
      return true;	
   }	
	
   public void setTypeAliases(Class[] typeAliases) {	
      this.typeAliases = typeAliases;	
   }	
	
   public void onApplicationEvent(ApplicationEvent event) {	
      if (this.failFast && event instanceof ContextRefreshedEvent) {	
         this.sqlSessionFactory.getConfiguration().getMappedStatementNames();	
      }	
	
   }	
	
   public DatabaseIdProvider getDatabaseIdProvider() {	
      return this.databaseIdProvider;	
   }	
	
   public Class getVfs() {	
      return this.vfs;	
   }	
	
   public void setVfs(Class vfs) {	
      this.vfs = vfs;	
   }	
	
   public void setTypeHandlersPackage(String typeHandlersPackage) {	
      this.typeHandlersPackage = typeHandlersPackage;	
   }	
	
   public void setSqlSessionFactoryBuilder(SqlSessionFactoryBuilder sqlSessionFactoryBuilder) {	
      this.sqlSessionFactoryBuilder = sqlSessionFactoryBuilder;	
   }	
	
   public void setDatabaseIdProvider(DatabaseIdProvider databaseIdProvider) {	
      this.databaseIdProvider = databaseIdProvider;	
   }	
	
   public void afterPropertiesSet() throws Exception {	
      Assert.notNull(this.dataSource, "Property 'dataSource' is required");	
      Assert.notNull(this.sqlSessionFactoryBuilder, "Property 'sqlSessionFactoryBuilder' is required");	
      Assert.state(this.configuration == null && this.configLocation == null || this.configuration == null || this.configLocation == null, "Property 'configuration' and 'configLocation' can not pecified with together");	
      this.sqlSessionFactory = this.buildSqlSessionFactory();	
   }	
	
   public void setPlugins(Interceptor[] plugins) {	
      this.plugins = plugins;	
   }	
	
   public void setConfigLocation(Resource configLocation) {	
      this.configLocation = configLocation;	
   }	
	
   public void setFailFast(boolean failFast) {	
      this.failFast = failFast;	
   }	
	
   public void setCache(Cache cache) {	
      this.cache = cache;	
   }	
	
   public Class getObjectType() {	
      return this.sqlSessionFactory == null ? SqlSessionFactory.class : this.sqlSessionFactory.getClass();	
   }	
	
   public Cache getCache() {	
      return this.cache;	
   }	
	
   public void setTransactionFactory(TransactionFactory transactionFactory) {	
      this.transactionFactory = transactionFactory;	
   }	
}	
