/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.io.PropertiesUtils	
 *  com.jeesite.common.io.ResourceUtils	
 *  org.apache.commons.lang3.LocaleUtils	
 *  org.apache.ibatis.io.VFS	
 *  org.apache.ibatis.transaction.TransactionFactory	
 *  org.mybatis.spring.mapper.MapperScannerConfigurer	
 *  org.slf4j.Logger	
 *  org.springframework.context.annotation.Bean	
 *  org.springframework.context.annotation.Configuration	
 *  org.springframework.core.env.Environment	
 *  org.springframework.core.io.Resource	
 */	
package com.jeesite.autoconfigure.core;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.io.PropertiesUtils;	
import com.jeesite.common.io.ResourceUtils;	
import com.jeesite.common.mybatis.SpringBootVFS;	
import com.jeesite.common.mybatis.SqlSessionFactoryBean;	
import com.jeesite.common.mybatis.annotation.MyBatisDao;	
import com.jeesite.common.mybatis.v.L;	
import java.io.IOException;	
import java.util.Locale;	
import java.util.Properties;	
import java.util.TimeZone;	
import javax.sql.DataSource;	
import org.apache.commons.lang3.LocaleUtils;	
import org.apache.ibatis.io.VFS;	
import org.apache.ibatis.transaction.TransactionFactory;	
import org.hyperic.sigar.cmd.Watch;	
import org.hyperic.sigar.pager.PageFetcher;	
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
        String a;	
        PropertiesUtils.setEnvironment((Environment)environment);	
        try {	
            a = Global.getProperty("lang.defaultLocale", "zh_CN");	
            Locale.setDefault(LocaleUtils.toLocale((String)a));	
        }	
        catch (Exception a2) {	
            Global.logger.warn(new StringBuilder().insert(0, "lang.defaultLocale error: ").append(a2.getMessage()).toString());	
        }	
        try {	
            a = Global.getProperty("lang.defaultTimeZone", "GMT+08:00");	
            TimeZone.setDefault(TimeZone.getTimeZone(a));	
        }	
        catch (Exception a3) {	
            Global.logger.warn(new StringBuilder().insert(0, "lang.defaultTimeZone error: ").append(a3.getMessage()).toString());	
        }	
        String string = a = new MapperScannerConfigurer();	
        a.setSqlSessionFactoryBeanName("sqlSessionFactory");	
        string.setBasePackage(Global.getProperty("mybatis.scanBasePackage"));	
        string.setAnnotationClass(MyBatisDao.class);	
        return string;	
    }	
	
    @Bean	
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws IOException {	
        SqlSessionFactoryBean a;	
        SqlSessionFactoryBean sqlSessionFactoryBean = a = new SqlSessionFactoryBean();	
        SqlSessionFactoryBean sqlSessionFactoryBean2 = a;	
        SqlSessionFactoryBean sqlSessionFactoryBean3 = a;	
        a.setConfigLocation(ResourceUtils.getResource((String)"/mybatis/mybatis-config.xml"));	
        sqlSessionFactoryBean3.setTypeHandlersPackage(Global.getProperty("mybatis.scanTypeHandlersPackage"));	
        sqlSessionFactoryBean3.setTypeAliasesPackage(Global.getProperty("mybatis.scanBasePackage"));	
        sqlSessionFactoryBean2.setTypeAliasesSuperType(BaseEntity.class);	
        sqlSessionFactoryBean2.setMapperLocations(ResourceUtils.getResources((String)"classpath*:/mappings/**/*.xml"));	
        SqlSessionFactoryBean sqlSessionFactoryBean4 = a;	
        sqlSessionFactoryBean2.setTransactionFactory((TransactionFactory)new L());	
        sqlSessionFactoryBean.setVfs(SpringBootVFS.class);	
        sqlSessionFactoryBean.setDataSource(dataSource);	
        Properties a2 = new Properties();	
        a.setConfigurationProperties(a2);	
        a2.setProperty("_prefix", Global.getProperty("jdbc.tablePrefix"));	
        return sqlSessionFactoryBean;	
    }	
}	
	
