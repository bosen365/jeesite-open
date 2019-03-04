package com.jeesite.common.tests;	
	
import com.jeesite.common.lang.TimeUtils;	
import com.jeesite.common.mybatis.mapper.query.QueryDataScope;	
import com.jeesite.modules.config.TransactionConfig;	
import javax.sql.DataSource;	
import org.junit.After;	
import org.junit.Before;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.test.annotation.Rollback;	
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;	
	
@Rollback	
public class BaseSpringContextTests extends AbstractTransactionalJUnit4SpringContextTests {	
   private long endTime;	
   private long startTime;	
   protected DataSource dataSource;	
	
   @Autowired	
   public void setDataSource(DataSource dataSource) {	
      super.setDataSource(dataSource);	
      this.dataSource = dataSource;	
   }	
	
   @Before	
   public void begin() {	
      this.startTime = System.currentTimeMillis();	
      this.logger.info("\n\n========================= Test Begin ==============================\n");	
   }	
	
   @After	
   public void after() {	
      this.endTime = System.currentTimeMillis();	
      this.logger.info((new StringBuilder()).insert(0, "\n\n========================= Test After ============================== 耗时：").append(TimeUtils.formatDateAgo(this.endTime - this.startTime)).append("\n").toString());	
   }	
}	
