/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.autoconfigure.core;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.io.PropertiesUtils;	
import com.jeesite.common.io.ResourceUtils;	
import com.jeesite.common.mybatis.SpringBootVFS;	
import com.jeesite.common.mybatis.SqlSessionFactoryBean;	
import com.jeesite.common.mybatis.annotation.MyBatisDao;	
import com.jeesite.common.mybatis.e.l;	
import com.jeesite.common.mybatis.mapper.query.QueryDataScope;	
import java.io.IOException;	
import java.lang.annotation.Annotation;	
import java.util.Locale;	
import java.util.Properties;	
import java.util.TimeZone;	
import javax.sql.DataSource;	
import org.apache.commons.lang3.LocaleUtils;	
import org.apache.ibatis.io.VFS;	
import org.apache.ibatis.transaction.TransactionFactory;	
import org.hyperic.sigar.ProcFd;	
import org.mybatis.spring.mapper.MapperScannerConfigurer;	
import org.slf4j.Logger;	
import org.springframework.context.annotation.Bean;	
import org.springframework.context.annotation.Configuration;	
import org.springframework.core.env.Environment;	
import org.springframework.core.io.Resource;	
	
@Configuration	
public class MyBatisAutoConfiguration {	
    @Bean	
    public static MapperScannerConfigurer mapperScannerConfigurer(Environment environment) {	
        Object a;	
        PropertiesUtils.setEnvironment(environment);	
        try {	
            a = Global.getProperty("lang.defaultLocale", "zh_CN");	
            Locale.setDefault(LocaleUtils.toLocale((String)a));	
        }	
        catch (Exception a2) {	
            Global.logger.warn(new StringBuilder().insert(0, "lang.defaultLocale error: ").append(a2.getMessage()).toString());	
        }	
        try {	
            a = Global.getProperty("lang.defaultTimeZone", "GMT+08:00");	
            TimeZone.setDefault(TimeZone.getTimeZone((String)a));	
        }	
        catch (Exception a3) {	
            Global.logger.warn(new StringBuilder().insert(0, "lang.defaultTimeZone error: ").append(a3.getMessage()).toString());	
        }	
        Object object = a = new MapperScannerConfigurer();	
        ((MapperScannerConfigurer)a).setSqlSessionFactoryBeanName("sqlSessionFactory");	
        ((MapperScannerConfigurer)object).setBasePackage(Global.getProperty("mybatis.scanBasePackage"));	
        ((MapperScannerConfigurer)object).setAnnotationClass(MyBatisDao.class);	
        return object;	
    }	
	
    @Bean	
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws IOException {	
        SqlSessionFactoryBean a;	
        SqlSessionFactoryBean sqlSessionFactoryBean = a = new SqlSessionFactoryBean();	
        SqlSessionFactoryBean sqlSessionFactoryBean2 = a;	
        SqlSessionFactoryBean sqlSessionFactoryBean3 = a;	
        a.setConfigLocation(ResourceUtils.getResource("/mybatis/mybatis-config.xml"));	
        sqlSessionFactoryBean3.setTypeHandlersPackage(Global.getProperty("mybatis.scanTypeHandlersPackage"));	
        sqlSessionFactoryBean3.setTypeAliasesPackage(Global.getProperty("mybatis.scanBasePackage"));	
        sqlSessionFactoryBean2.setTypeAliasesSuperType(BaseEntity.class);	
        sqlSessionFactoryBean2.setMapperLocations(ResourceUtils.getResources("classpah*:/mappings/**/*.xml"));	
        SqlSessionFactoryBean sqlSessionFactoryBean4 = a;	
        sqlSessionFactoryBean2.setTransactionFactory(new l());	
        sqlSessionFactoryBean.setVfs(SpringBootVFS.class);	
        sqlSessionFactoryBean.setDataSource(dataSource);	
        Properties a2 = new Properties();	
        a2.setProperty("_prefix", Global.getProperty("jdbc.tablePrefix"));	
        a.setConfigurationProperties(a2);	
        return sqlSessionFactoryBean;	
    }	
}	
	
