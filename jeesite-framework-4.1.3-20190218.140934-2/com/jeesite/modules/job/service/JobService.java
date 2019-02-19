package com.jeesite.modules.job.service;	
	
import com.atomikos.jdbc.AtomikosDataSourceBean;	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.datasource.RoutingDataSource;	
import com.jeesite.common.entity.Extend;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.service.CrudService;	
import com.jeesite.common.service.ServiceException;	
import com.jeesite.common.web.j.F;	
import com.jeesite.modules.job.entity.JobEntity;	
import com.jeesite.modules.job.listener.JobDetailListener;	
import com.jeesite.modules.job.listener.JobSchedulerListener;	
import com.jeesite.modules.job.listener.JobTriggerListener;	
import java.util.Iterator;	
import java.util.List;	
import javax.annotation.PostConstruct;	
import javax.annotation.PreDestroy;	
import javax.sql.DataSource;	
import javax.sql.XADataSource;	
import org.hyperic.sigar.FileSystemUsage;	
import org.quartz.CronTrigger;	
import org.quartz.JobDetail;	
import org.quartz.ListenerManager;	
import org.quartz.Scheduler;	
import org.quartz.SchedulerException;	
import org.quartz.Trigger;	
import org.quartz.TriggerKey;	
import org.quartz.impl.matchers.GroupMatcher;	
import org.quartz.impl.triggers.CronTriggerImpl;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;	
import org.springframework.context.annotation.DependsOn;	
import org.springframework.scheduling.quartz.SchedulerFactoryBean;	
import org.springframework.stereotype.Service;	
import org.springframework.transaction.annotation.Transactional;	
	
@Service	
@DependsOn({"dbUpgrade"})	
@Transactional(	
   readOnly = true	
)	
@ConditionalOnProperty(	
   name = {"job.enabled"},	
   havingValue = "true",	
   matchIfMissing = true	
)	
public class JobService extends CrudService {	
   @Autowired	
   private JobLogService jobLogService;	
   private SchedulerFactoryBean scheduler;	
   @Autowired	
   private DataSource dataSource;	
	
   @Transactional(	
      readOnly = false	
   )	
   public void runOnce(JobEntity job) {	
      if (ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnJob"))) {	
         try {	
            TriggerKey a = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());	
            Trigger a = this.getScheduler().getTrigger(a);	
            this.getScheduler().triggerJob(a.getJobKey());	
         } catch (SchedulerException var4) {	
            throw new ServiceException("运行一次失败！", var4);	
         }	
      }	
   }	
	
   public JobEntity get(JobEntity job) {	
      if (!ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnJob"))) {	
         return null;	
      } else {	
         JobEntity a = null;	
	
         try {	
            if (StringUtils.isNotBlank(job.getJobName()) && StringUtils.isNotBlank(job.getJobGroup())) {	
               TriggerKey a = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());	
               Trigger a;	
               if ((a = this.getScheduler().getTrigger(a)) instanceof CronTriggerImpl) {	
                  a = (new JobEntity()).convert(this.getScheduler(), (CronTriggerImpl)a);	
               }	
            }	
         } catch (SchedulerException var5) {	
            this.logger.error("获取失败！", var5);	
         }	
	
         return a;	
      }	
   }	
	
   public boolean isRunning() {	
      if (!ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnJob"))) {	
         return false;	
      } else {	
         try {	
            return !this.getScheduler().isInStandbyMode() && this.getScheduler().isStarted();	
         } catch (Exception var2) {	
            this.logger.error("获取定时器状态时失败！", var2);	
            return false;	
         }	
      }	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void pause(JobEntity job) {	
      if (ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnJob"))) {	
         job.setStatus("2");	
         super.updateStatus(job);	
	
         try {	
            TriggerKey a = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());	
            Trigger a = this.getScheduler().getTrigger(a);	
            this.getScheduler().pauseJob(a.getJobKey());	
            this.getScheduler().pauseTrigger(a.getKey());	
         } catch (SchedulerException var4) {	
            throw new ServiceException("暂停运行失败！", var4);	
         }	
      }	
   }	
	
   public boolean stopAll() {	
      if (!ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnJob"))) {	
         return false;	
      } else {	
         try {	
            this.getFactoryBean().stop();	
            return true;	
         } catch (Exception var2) {	
            this.logger.error("停止定时器失败！", var2);	
            return false;	
         }	
      }	
   }	
	
   // $FF: synthetic method	
   private Scheduler getScheduler() {	
      return this.getFactoryBean().getScheduler();	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void save(JobEntity job) {	
      this.save(job, true);	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void delete(JobEntity job) {	
      if (ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnJob"))) {	
         super.delete(job);	
	
         try {	
            TriggerKey a = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());	
            Trigger a;	
            if ((a = this.getScheduler().getTrigger(a)) != null) {	
               this.getScheduler().deleteJob(a.getJobKey());	
               this.getScheduler().unscheduleJob(a.getKey());	
            }	
         } catch (SchedulerException var4) {	
            throw new ServiceException("删除失败！", var4);	
         }	
      }	
   }	
	
   public boolean startAll() {	
      if (!ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnJob"))) {	
         return false;	
      } else {	
         try {	
            this.getFactoryBean().setStartupDelay(0);	
            this.getFactoryBean().start();	
            if ("9".equals(F.ALLATORIxDEMO().get("type"))) {	
               (new Thread(new Runnable() {	
                  public void run() {	
                     <undefinedtype> var10000;	
                     label13: {	
                        try {	
                           Thread.sleep(1800000L);	
                        } catch (InterruptedException var2) {	
                           var10000 = a;	
                           break label13;	
                        }	
	
                        var10000 = a;	
                     }	
	
                     JobService.this.getFactoryBean().stop();	
                  }	
               })).start();	
            }	
	
            return true;	
         } catch (Exception var2) {	
            this.logger.error("启动定时器失败！", var2);	
            return false;	
         }	
      }	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void save(JobEntity job, boolean isSaveToDb) {	
      if (ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnJob"))) {	
         Exception var10000;	
         label40: {	
            boolean var10001;	
            JobDetail a;	
            CronTrigger a;	
            JobService var8;	
            label39: {	
               a = com.jeesite.modules.job.j.E.ALLATORIxDEMO(job, a = com.jeesite.modules.job.j.E.ALLATORIxDEMO(job));	
               if (isSaveToDb) {	
                  if (job.getIsNewRecord()) {	
                     var8 = this;	
                     super.insert(job);	
                     break label39;	
                  }	
	
                  super.update(job);	
               }	
	
               try {	
                  var8 = this;	
               } catch (Exception var7) {	
                  var10000 = var7;	
                  var10001 = false;	
                  break label40;	
               }	
            }	
	
            try {	
               var8.getScheduler().addJob(a, true);	
               if (this.getScheduler().checkExists(a.getKey())) {	
                  this.getScheduler().pauseTrigger(a.getKey());	
                  this.getScheduler().rescheduleJob(a.getKey(), a);	
               } else {	
                  this.getScheduler().scheduleJob(a);	
               }	
	
               if ("2".equals(job.getStatus())) {	
                  this.getScheduler().pauseJob(a.getJobKey());	
                  this.getScheduler().pauseTrigger(a.getKey());	
                  return;	
               }	
	
               return;	
            } catch (Exception var6) {	
               var10000 = var6;	
               var10001 = false;	
            }	
         }	
	
         Exception a = var10000;	
         throw new ServiceException("添加计划任务失败！", a);	
      }	
   }	
	
   // $FF: synthetic method	
   private SchedulerFactoryBean getFactoryBean() {	
      return this.scheduler;	
   }	
	
   public List findList(JobEntity job) {	
      List a = ListUtils.newArrayList();	
      if (!ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnJob"))) {	
         return a;	
      } else {	
         try {	
            Scheduler a;	
            Iterator var5 = (a = this.getScheduler()).getTriggerGroupNames().iterator();	
	
            while(var5.hasNext()) {	
               GroupMatcher a = GroupMatcher.groupEquals((String)var5.next());	
               Iterator var9 = a.getTriggerKeys(a).iterator();	
	
               while(var9.hasNext()) {	
                  TriggerKey a = (TriggerKey)var9.next();	
                  Trigger a;	
                  if ((a = a.getTrigger(a)) instanceof CronTriggerImpl) {	
                     a.add((new JobEntity()).convert(a, (CronTriggerImpl)a));	
                  }	
               }	
            }	
         } catch (Exception var12) {	
            this.logger.error("查询任务列表失败！", var12);	
         }	
	
         return a;	
      }	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void resume(JobEntity job) {	
      if (ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnJob"))) {	
         job.setStatus("0");	
         super.updateStatus(job);	
	
         try {	
            TriggerKey a = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());	
            Trigger a = this.getScheduler().getTrigger(a);	
            this.getScheduler().resumeJob(a.getJobKey());	
            this.getScheduler().resumeTrigger(a.getKey());	
         } catch (SchedulerException var4) {	
            throw new ServiceException("恢复运行失败！", var4);	
         }	
      }	
   }	
	
   // $FF: synthetic method	
   @PreDestroy	
   private void destroy() throws SchedulerException {	
      if (this.scheduler != null) {	
         this.scheduler.destroy();	
      }	
	
   }	
	
   // $FF: synthetic method	
   @PostConstruct	
   private void initialize() throws Exception {	
      if (!Global.isTestProfileActive()) {	
         if (ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnJob"))) {	
            this.scheduler = new SchedulerFactoryBean();	
            JobService var10000;	
            if (!"9".equals(F.ALLATORIxDEMO().get("type"))) {	
               this.scheduler.setAutoStartup(Global.getPropertyToBoolean("job.autoStartup", "true"));	
               var10000 = this;	
            } else {	
               this.scheduler.setAutoStartup(false);	
               var10000 = this;	
            }	
	
            var10000.scheduler.setStartupDelay(Global.getPropertyToInteger("job.startupDelay", "60"));	
            this.scheduler.setQuartzProperties(com.jeesite.modules.job.j.E.ALLATORIxDEMO());	
            String a = Global.getProperty("job.dataSourceName", "job");	
            if (StringUtils.isNotBlank(Global.getProperty((new StringBuilder()).insert(0, "jdbc.").append(a).append(".type").toString()))) {	
               this.scheduler.setDataSource(RoutingDataSource.createDataSource(a, false));	
               var10000 = this;	
            } else {	
               DataSource a;	
               if ((a = this.dataSource) instanceof RoutingDataSource) {	
                  a = ((RoutingDataSource)a).getTargetDataSource("default");	
               }	
	
               if (!(a instanceof XADataSource) && !(a instanceof AtomikosDataSourceBean)) {	
                  var10000 = this;	
                  this.scheduler.setDataSource(a);	
               } else {	
                  var10000 = this;	
                  this.scheduler.setDataSource(RoutingDataSource.createDataSource(a, false));	
               }	
            }	
	
            var10000.scheduler.afterPropertiesSet();	
            if (!"9".equals(F.ALLATORIxDEMO().get("type")) && this.scheduler.isAutoStartup()) {	
               this.scheduler.start();	
            }	
	
            JobEntity a = new JobEntity();	
            a.setStatus("");	
            Iterator var4 = super.findList(a).iterator();	
	
            while(true) {	
               while(var4.hasNext()) {	
                  JobEntity a = (JobEntity)var4.next();	
                  if ("1".equals(a.getStatus())) {	
                     this.delete(a);	
                  } else {	
                     try {	
                        this.save(a, false);	
                     } catch (Exception var8) {	
                        if (!var8.getMessage().contains("目标字符串")) {	
                           this.logger.warn(var8.getMessage(), var8);	
                        }	
                     }	
                  }	
               }	
	
               if (!"9".equals(F.ALLATORIxDEMO().get("type"))) {	
                  try {	
                     ListenerManager a = this.getScheduler().getListenerManager();	
                     if (Global.getPropertyToBoolean("job.log.scheduler.enabled", "true")) {	
                        a.addSchedulerListener(new JobSchedulerListener(this.jobLogService));	
                     }	
	
                     if (Global.getPropertyToBoolean("job.log.jobDetail.enabled", "true")) {	
                        a.addJobListener(new JobDetailListener(this.jobLogService));	
                     }	
	
                     if (Global.getPropertyToBoolean("job.log.trigger.enabled", "true")) {	
                        a.addTriggerListener(new JobTriggerListener(this.jobLogService));	
                        return;	
                     }	
                  } catch (SchedulerException var7) {	
                     this.logger.error("作业监听事件异常！", var7);	
                  }	
               }	
	
               return;	
            }	
         }	
      }	
   }	
}	
