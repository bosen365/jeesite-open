package com.jeesite.common.tests;	
	
import com.jeesite.common.lang.TimeUtils;	
import com.jeesite.modules.gen.entity.config.GenDict;	
import javax.sql.DataSource;	
import org.hyperic.sigar.SysInfo;	
import org.junit.After;	
import org.junit.Before;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.test.annotation.Rollback;	
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;	
	
@Rollback	
public class BaseSpringContextTests extends AbstractTransactionalJUnit4SpringContextTests {	
   private long startTime;	
   protected DataSource dataSource;	
   private long endTime;	
	
   @Autowired	
   public void setDataSource(DataSource dataSource) {	
      super.setDataSource(dataSource);	
      this.dataSource = dataSource;	
   }	
	
   @Before	
   public void begin() {	
      this.startTime = System.currentTimeMillis();	
      this.logger.info("===== Test Begin ==============================");	
   }	
	
   @After	
   public void after() {	
      this.endTime = System.currentTimeMillis();	
      this.logger.info((new StringBuilder()).insert(0, "===== Test After ============================== 耗时：").append(TimeUtils.formatDateAgo(this.endTime - this.startTime)).toString());	
   }	
}	
