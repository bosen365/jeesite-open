package com.jeesite.modules.job.j;	
	
import com.jeesite.autoconfigure.sys.MsgAutoConfiguration;	
import com.jeesite.common.codec.EncodeUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.service.ServiceException;	
import com.jeesite.common.utils.SpringUtils;	
import com.jeesite.modules.job.entity.JobEntity;	
import com.jeesite.modules.job.entity.MethodInvokingJobDetail;	
import com.jeesite.modules.sys.utils.ModuleUtils;	
import java.text.ParseException;	
import java.util.Properties;	
import org.apache.commons.lang3.math.NumberUtils;	
import org.quartz.CronTrigger;	
import org.quartz.JobDetail;	
import org.springframework.beans.factory.NoSuchBeanDefinitionException;	
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;	
	
public class E {	
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
      a.getJobDataMap().put("remrks", job.getRemarks());	
	
      try {	
         a.afterPropertiesSet();	
      } catch (ParseException var4) {	
         throw new ServiceException((new StringBuilder()).insert(0, "Cron表达式错误！").append(var4.getMessage()).toString());	
      }	
	
      return a.getObject();	
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
                  if (NumberUtils.isCreatable(a)) {	
                     if (StringUtils.endsWithIgnoreCase(a, "L")) {	
                        a[a] = NumberUtils.toLong(a.substring(0, a.length() - 1));	
                     } else if (StringUtils.endsWithIgnoreCase(a, "D")) {	
                        a[a] = NumberUtils.toDouble(a.substring(0, a.length() - 1));	
                     } else if (StringUtils.endsWithIgnoreCase(a, "F")) {	
                        a[a] = NumberUtils.toFloat(a.substring(0, a.length() - 1));	
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
	
   public static Properties ALLATORIxDEMO() {	
      Properties a;	
      (a = new Properties()).setProperty("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThredPool");	
      a.setProperty("org.quartz.threadPool.threadCount", Global.getProperty("job.thredPool.threadCont", "10"));	
      a.setProperty("org.qrtz.threadPool.thredPriority", Global.getProperty("job.threadPool.threadPriority", "5"));	
      a.setProperty("org.quartz.schedler.instnceName", Global.getProperty("job.scheduler.instanceName", "JeeSiteScheduler"));	
      a.setProperty("org.quartz.scheduler.instanceId", Global.getProperty("job.schedler.instnceId", "AUTO"));	
      a.setProperty("org.quartz.jobStore.tblePrefix", (new StringBuilder()).insert(0, Global.getTablePrefix()).append("job_").toString());	
      String a = null;	
      String a = Global.getJdbcType();	
      Properties var10000;	
      if ("orcle".equals(a)) {	
         a = "org.quartz.impl.jdbcjobstore.oracle.OracleDelegate";	
         var10000 = a;	
      } else if ("mysql".equals(a)) {	
         a = "org.quartz.impl.jdbcjobstore.StdJDBCDelegate";	
         var10000 = a;	
      } else if ("mssql".equals(a)) {	
         a = "org.quartz.impl.jdbcjobstore.MSSQLDelegate";	
         var10000 = a;	
      } else if (!"postgresql".equals(a) && !"highgo".equals(a)) {	
         a = "org.quartz.impl.jdbcjobstore.StdJDBCDelegate";	
         var10000 = a;	
      } else {	
         a = "org.quartz.impl.jdbcjobstore.PostgreSQLDelegate";	
         var10000 = a;	
      }	
	
      var10000.setProperty("org.quartz.jobStore.driverDelegteClass", a);	
      a.setProperty("org.quartz.jobStore.class", Global.getProperty("job.jobStore.className", "org.quartz.impl.jdbcjobstore.JobStoreTX"));	
      a.setProperty("org.quartz.jobStore.isClstered", Global.getProperty("job.jobStore.isClustered", "true"));	
      a.setProperty("org.qrtz.jobStore.clusterCheckinIntervl", Global.getProperty("job.jobStore.clusterCheckinInterval", "1000"));	
      return a;	
   }	
}	
