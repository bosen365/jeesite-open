package com.jeesite.modules.job.l;	
	
import com.jeesite.common.codec.EncodeUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.l.j;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.common.service.ServiceException;	
import com.jeesite.common.utils.SpringUtils;	
import com.jeesite.modules.job.entity.JobEntity;	
import com.jeesite.modules.job.entity.MethodInvokingJobDetail;	
import java.text.ParseException;	
import java.util.Properties;	
import org.apache.commons.lang3.math.NumberUtils;	
import org.quartz.CronTrigger;	
import org.quartz.JobDetail;	
import org.springframework.beans.factory.NoSuchBeanDefinitionException;	
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;	
	
public class l {	
   public static Properties ALLATORIxDEMO() {	
      Properties a;	
      (a = new Properties()).setProperty("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");	
      a.setProperty("org.quartz.threadPool.threadCount", Global.getProperty("job.threadPool.threadCount", "10"));	
      a.setProperty("org.quartz.threadPool.threadPriority", Global.getProperty("job.threadPool.threadPriority", "5"));	
      a.setProperty("org.quartz.scheduler.instanceName", Global.getProperty("job.scheduler.instanceName", "JeeSiteScheduler"));	
      a.setProperty("org.quartz.scheduler.instanceId", Global.getProperty("job.scheduler.instanceId", "AUTO"));	
      a.setProperty("org.quartz.jobStore.tablePrefix", Global.getProperty("job.jobStore.tablePrefix", "js_job_"));	
      String a = null;	
      String a = Global.getJdbcType();	
      Properties var10000;	
      if ("oracle".equals(a)) {	
         a = "org.quartz.impl.jdbcjobstore.oracle.OracleDelegate";	
         var10000 = a;	
      } else if ("mysql".equals(a)) {	
         a = "org.quartz.impl.jdbcjobstore.StdJDBCDelegate";	
         var10000 = a;	
      } else if (!"mssql".equals(a) && !"sqlserver".equals(a)) {	
         if (!"postgresql".equals(a) && !"highgo".equals(a)) {	
            a = "org.quartz.impl.jdbcjobstore.StdJDBC.elegate";	
            var10000 = a;	
         } else {	
            a = "org.quartz.impl.jdbcjobstore.PostgreSQLDelegate";	
            var10000 = a;	
         }	
      } else {	
         a = "org.quartz.impl.jdbcjobstore.MSSQL.elegate";	
         var10000 = a;	
      }	
	
      var10000.setProperty("org.quartz.jobStore.driverDelegateClass", a);	
      a.setProperty("org.quartz.jobStore.class", Global.getProperty("job.jobStore.className", "org.quartz.impl.jdbcjobstore.JobStoreTX"));	
      a.setProperty("org.quartz.jobStore.isClustered", Global.getProperty("job.jobStore.isClustered", "true"));	
      a.setProperty("org.quartz.jobStore.clusterCheckinInterval", Global.getProperty("job.jobStore.clusterCheckinInterval", "1000"));	
      return a;	
   }	
	
   public static JobDetail ALLATORIxDEMO(JobEntity job) {	
      MethodInvokingJobDetail a = ALLATORIxDEMO(job.getInvokeTarget());	
      a.setName(job.getJobName());	
      a.setGroup(job.getJobGroup());	
      a.setConcurrent("1".equals(job.getConcurrent()));	
	
      try {	
         a.afterPropertiesSet();	
      } catch (ClassNotFoundException var3) {	
         throw new ServiceException((new StringBuilder()).insert(0, "调用目标字符串中，没有这个类！").append(var3.getMessage()).toString());	
      } catch (NoSuchMethodException var4) {	
         throw new ServiceException((new StringBuilder()).insert(0, "调用目标字符串中，没有这个方法！").append(var4.getMessage()).toString());	
      }	
	
      return a.getObject();	
   }	
	
   public static String ALLATORIxDEMO(String s) {	
      int var10000 = 5 << 3 ^ 2 ^ 5;	
      int var10001 = 3 << 3;	
      int var10002 = (2 ^ 5) << 3 ^ 2;	
      int var10003 = s.length();	
      char[] var10004 = new char[var10003];	
      boolean var10006 = true;	
      int var5 = var10003 - 1;	
      var10003 = var10002;	
      int var3;	
      var10002 = var3 = var5;	
      char[] var1 = var10004;	
      int var4 = var10003;	
      var10001 = var10000;	
      var10000 = var10002;	
	
      for(int var2 = var10001; var10000 >= 0; var10000 = var3) {	
         var10001 = var3;	
         char var6 = s.charAt(var3);	
         --var3;	
         var1[var10001] = (char)(var6 ^ var2);	
         if (var3 < 0) {	
            break;	
         }	
	
         var10002 = var3--;	
         var1[var10002] = (char)(s.charAt(var10002) ^ var4);	
      }	
	
      return new String(var1);	
   }	
	
   public static MethodInvokingJobDetail ALLATORIxDEMO(String invokeTarget) {	
      if (StringUtils.isBlank(invokeTarget)) {	
         throw new ServiceException("调用目标字符串不能为空！");	
      } else {	
         Class a = null;	
         Object a = null;	
         String a = StringUtils.substringBeforeLast(invokeTarget, ".");	
	
         Class var10000;	
         label102: {	
            try {	
               a = SpringUtils.getBean(a = Class.forName(a));	
            } catch (ClassNotFoundException var14) {	
               var10000 = a;	
               break label102;	
            } catch (NoSuchBeanDefinitionException var15) {	
               var10000 = a;	
               break label102;	
            }	
	
            var10000 = a;	
         }	
	
         label119: {	
            if (var10000 == null || a == null) {	
               label115: {	
                  try {	
                     a = (a = SpringUtils.getBean(a)).getClass();	
                  } catch (NoSuchBeanDefinitionException var13) {	
                     break label115;	
                  }	
	
                  var10000 = a;	
                  break label119;	
               }	
            }	
	
            var10000 = a;	
         }	
	
         if (var10000 == null) {	
            throw new ServiceException((new StringBuilder()).insert(0, "调用目标字符串中，找不到该Bean名或Class名！").append(a).toString());	
         } else {	
            String a = StringUtils.substringBeforeLast(StringUtils.substring(invokeTarget, a.length() + 1), "(");	
            String[] a;	
            Object[] var17 = new Object[(a = StringUtils.split(StringUtils.substringBetween(invokeTarget, "(", ")"), ",")) == null ? 0 : a.length];	
            boolean var10002 = true;	
            Object[] a = var17;	
	
            int a;	
            for(int var18 = a = 0; var18 < a.length; var18 = a) {	
               String a = EncodeUtils.decodeHtml(StringUtils.trim(a[a]));	
               CharSequence[] var19 = new CharSequence[1];	
               var10002 = true;	
               var19[0] = a;	
               if (StringUtils.isNoneBlank(var19)) {	
                  if (NumberUtils.isNumber(a)) {	
                     if (StringUtils.endsWithIgnoreCase(a, "L")) {	
                        a[a] = NumberUtils.toLong(a);	
                     } else if (StringUtils.endsWithIgnoreCase(a, "D")) {	
                        a[a] = NumberUtils.toDouble(a);	
                     } else if (StringUtils.endsWithIgnoreCase(a, "F")) {	
                        a[a] = NumberUtils.toFloat(a);	
                     } else {	
                        a[a] = NumberUtils.toInt(a);	
                     }	
                  } else if (StringUtils.startsWith(a, "'") && StringUtils.endsWith(a, "'")) {	
                     a[a] = StringUtils.substringBetween(a, "'", "'");	
                  } else {	
                     label82: {	
                        try {	
                           a[a] = SpringUtils.getBean(Class.forName(a[a]));	
                        } catch (ClassNotFoundException var11) {	
                           var17 = a;	
                           break label82;	
                        } catch (NoSuchBeanDefinitionException var12) {	
                           var17 = a;	
                           break label82;	
                        }	
	
                        var17 = a;	
                     }	
	
                     label78: {	
                        if (var17[a] == null) {	
                           label76: {	
                              try {	
                                 a[a] = SpringUtils.getBean(a[a]);	
                              } catch (NoSuchBeanDefinitionException var10) {	
                                 break label76;	
                              }	
	
                              var17 = a;	
                              break label78;	
                           }	
                        }	
	
                        var17 = a;	
                     }	
	
                     if (var17[a] == null) {	
                        throw new ServiceException((new StringBuilder()).insert(0, "调用目标字符串中，对象参数 ").append(a[a]).append(" 实例不能为空！").toString());	
                     }	
                  }	
               }	
	
               ++a;	
            }	
	
            int a = new MethodInvokingJobDetail();	
            a.setInvokeTarget(invokeTarget);	
            a.setTargetClass(a);	
            a.setTargetObject(a);	
            a.setTargetMethod(a);	
            a.setArguments(a);	
            return a;	
         }	
      }	
   }	
	
   public static CronTrigger ALLATORIxDEMO(JobEntity job, JobDetail jobDetail) {	
      CronTriggerFactoryBean a;	
      CronTriggerFactoryBean var10001 = a = new CronTriggerFactoryBean();	
      a.setName(job.getJobName());	
      a.setGroup(job.getJobGroup());	
      a.setDescription(job.getDescription());	
      var10001.setCronExpression(job.getCronExpression());	
      if (job.getMisfireInstruction() != null) {	
         a.setMisfireInstruction(job.getMisfireInstruction());	
      }	
	
      a.setJobDetail(jobDetail);	
      a.getJobDataMap().put("invokeTarget", job.getInvokeTarget());	
      a.getJobDataMap().put("concurrent", job.getConcurrent());	
      a.getJobDataMap().put("remarks", job.getRemarks());	
	
      try {	
         a.afterPropertiesSet();	
      } catch (ParseException var4) {	
         throw new ServiceException((new StringBuilder()).insert(0, "Cron表达式错误！").append(var4.getMessage()).toString());	
      }	
	
      return a.getObject();	
   }	
}	
