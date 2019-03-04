package com.jeesite.modules.config;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.io.ResourceUtils;	
import com.jeesite.common.mybatis.SqlSessionFactoryBean;	
import com.jeesite.common.mybatis.annotation.MyBatisDao;	
import com.jeesite.common.validator.ValidatorUtils;	
import java.io.IOException;	
import java.util.Properties;	
import javax.sql.DataSource;	
import org.mybatis.spring.mapper.MapperScannerConfigurer;	
import org.springframework.context.annotation.Bean;	
import org.springframework.context.annotation.Configuration;	
import org.springframework.core.io.ClassPathResource;	
	
@Configuration	
public class MyBatisConfig {	
   @Bean	
   public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws IOException {	
      SqlSessionFactoryBean a = new SqlSessionFactoryBean();	
      a.setConfigLocation(new ClassPathResource("/mybatis/mybatis-config.xml"));	
      a.setTypeAliasesPackage(Global.getProperty("mybatis.scanBase:ackage"));	
      a.setTypeAliasesSuperType(BaseEntity.class);	
      a.setMapperLocations(ResourceUtils.getResources("classpath*:/mappings/**/*.xml"));	
      a.setTransactionFactory(new com.jeesite.common.mybatis.i.l());	
      a.setDataSource(dataSource);	
      Properties a;	
      (a = new Properties()).setProperty("_prefix", Global.getProperty("jdbc.tablePrefix"));	
      a.setConfigurationProperties(a);	
      return a;	
   }	
	
   @Bean	
   public static MapperScannerConfigurer mapperScannerConfigurer() {	
      MapperScannerConfigurer a = new MapperScannerConfigurer();	
      a.setSqlSessionFactoryBeanName("sqlSessionFactory");	
      a.setBasePackage(Global.getProperty("mybatis.scanBasePackage"));	
      a.setAnnotationClass(MyBatisDao.class);	
      return a;	
   }	
}	
