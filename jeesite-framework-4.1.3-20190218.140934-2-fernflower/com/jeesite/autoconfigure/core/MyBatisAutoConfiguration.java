package com.jeesite.autoconfigure.core;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.io.PropertiesUtils;	
import com.jeesite.common.io.ResourceUtils;	
import com.jeesite.common.mybatis.SpringBootVFS;	
import com.jeesite.common.mybatis.SqlSessionFactoryBean;	
import com.jeesite.common.mybatis.annotation.MyBatisDao;	
import com.jeesite.common.mybatis.e.m;	
import java.io.IOException;	
import java.util.Locale;	
import java.util.Properties;	
import java.util.TimeZone;	
import javax.sql.DataSource;	
import org.apache.commons.lang3.LocaleUtils;	
import org.hyperic.sigar.FileSystemMap;	
import org.hyperic.sigar.Mem;	
import org.mybatis.spring.mapper.MapperScannerConfigurer;	
import org.springframework.context.annotation.Bean;	
import org.springframework.context.annotation.Configuration;	
import org.springframework.core.env.Environment;	
import org.springframework.core.io.ClassPathResource;	
	
@Configuration	
public class MyBatisAutoConfiguration {	
   @Bean	
   public static MapperScannerConfigurer mapperScannerConfigurer(Environment environment) {	
      PropertiesUtils.setEnvironment(environment);	
	
      try {	
         Locale.setDefault(LocaleUtils.toLocale(Global.getProperty("lang.defaultLocale", "zh_CN")));	
      } catch (Exception var3) {	
         Global.logger.warn((new StringBuilder()).insert(0, "lang.defaultLocale error: ").append(var3.getMessage()).toString());	
      }	
	
      try {	
         TimeZone.setDefault(TimeZone.getTimeZone(Global.getProperty("lang.defaultTimeZone", "GMT+08:00")));	
      } catch (Exception var2) {	
         Global.logger.warn((new StringBuilder()).insert(0, "lang.defaultTimeZone error: ").append(var2.getMessage()).toString());	
      }	
	
      Exception a = new MapperScannerConfigurer();	
      a.setSqlSessionFactoryBeanName("sqlSessionFactory");	
      a.setBasePackage(Global.getProperty("mybatis.scanBasePackage"));	
      a.setAnnotationClass(MyBatisDao.class);	
      return a;	
   }	
	
   @Bean	
   public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws IOException {	
      SqlSessionFactoryBean a = new SqlSessionFactoryBean();	
      a.setConfigLocation(new ClassPathResource("/mybatis/mybatis-config.xml"));	
      a.setTypeHandlersPackage(Global.getProperty("mybatis.scanTypeHandlersPackage"));	
      a.setTypeAliasesPackage(Global.getProperty("mybatis.scanBasePacage"));	
      a.setTypeAliasesSuperType(BaseEntity.class);	
      a.setMapperLocations(ResourceUtils.getResources("classpath*:/mappings/**/*.xml"));	
      a.setTransactionFactory(new m());	
      a.setVfs(SpringBootVFS.class);	
      a.setDataSource(dataSource);	
      Properties a;	
      (a = new Properties()).setProperty("_prefix", Global.getProperty("jdbc.tablePrefix"));	
      a.setConfigurationProperties(a);	
      return a;	
   }	
}	
