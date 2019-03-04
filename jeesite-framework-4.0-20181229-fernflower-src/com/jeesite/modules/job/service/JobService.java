package com.jeesite.modules.job.service;	
	
import com.beust.jcommander.internal.Lists;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.datasource.RoutingDataSource;	
import com.jeesite.common.l.i.j;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.mapper.query.QueryWhere;	
import com.jeesite.common.service.BaseService;	
import com.jeesite.common.service.CrudService;	
import com.jeesite.common.service.ServiceException;	
import com.jeesite.modules.job.entity.JobEntity;	
import com.jeesite.modules.job.l.l;	
import com.jeesite.modules.job.listener.JobDetalListener;	
import com.jeesite.modules.job.listener.JobSchedulerListener;	
import com.jeesite.modules.job.listener.JobTriggerListener;	
import java.util.Iterator;	
import java.util.List;	
import javax.annotation.PostConstruct;	
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
import org.springframework.context.annotation.DependsOn;	
import org.springframework.scheduling.quartz.SchedulerFactoryBean;	
import org.springframework.stereotype.Service;	
import org.springframework.transaction.annotation.Transactional;	
	
@Service	
@DependsOn({"dbUpgrade"})	
@Transactional(	
   readOnly = true	
)	
public class JobService extends CrudService {	
   @Autowired	
   private RoutingDataSource dataSource;	
   @Autowired	
   private JobLogService jobLogService;	
   private SchedulerFactoryBean scheduler;	
	
   @Transactional(	
      readOnly = false	
   )	
   public void delete(JobEntity job) {	
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
	
   public boolean isRunning() {	
      try {	
         return !this.getScheduler().isInStandbyMode() && this.getScheduler().isStarted();	
      } catch (Exception var2) {	
         this.logger.error("获取定时器状态时失败！", var2);	
         return false;	
      }	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void save(JobEntity job) {	
      this.save(job, true);	
   }	
	
   public JobEntity get(JobEntity job) {	
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
	
   public boolean stopAll() {	
      if (!ObjectUtils.toBoolean(j.ALLATORIxDEMO().get("fnJob"))) {	
         return false;	
      } else {	
         try {	
            this.scheduler.stop();	
            return true;	
         } catch (Exception var2) {	
            this.logger.error("停止定时器失败！", var2);	
            return false;	
         }	
      }	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void save(JobEntity job, boolean isSaveToDb) {	
      if (!ObjectUtils.toBoolean(j.ALLATORIxDEMO().get("fnJob"))) {	
         throw new ServiceException("当前版本未开放此功能！");	
      } else {	
         Exception var10000;	
         label54: {	
            JobDetail a;	
            CronTrigger a;	
            JobService var10;	
            boolean var10001;	
            label49: {	
               a = l.ALLATORIxDEMO(job, a = l.ALLATORIxDEMO(job));	
               if (isSaveToDb) {	
                  if (job.getIsNewRecord()) {	
                     var10 = this;	
                     super.insert(job);	
                     break label49;	
                  }	
	
                  super.update(job);	
               }	
	
               try {	
                  var10 = this;	
               } catch (Exception var9) {	
                  var10000 = var9;	
                  var10001 = false;	
                  break label54;	
               }	
            }	
	
            label41: {	
               try {	
                  var10.getScheduler().addJob(a, true);	
                  if (this.getScheduler().getTrigger(a.getKey()) != null) {	
                     this.getScheduler().rescheduleJob(a.getKey(), a);	
                     break label41;	
                  }	
               } catch (Exception var8) {	
                  var10000 = var8;	
                  var10001 = false;	
                  break label54;	
               }	
	
               try {	
                  this.getScheduler().scheduleJob(a);	
               } catch (Exception var7) {	
                  var10000 = var7;	
                  var10001 = false;	
                  break label54;	
               }	
            }	
	
            try {	
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
	
   public List findList(JobEntity job) {	
      List a = Lists.newArrayList();	
	
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
	
   public Scheduler getScheduler() {	
      return this.scheduler.getScheduler();	
   }	
	
   public void runOnce(JobEntity job) {	
      try {	
         TriggerKey a = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());	
         Trigger a = this.getScheduler().getTrigger(a);	
         this.getScheduler().triggerJob(a.getJobKey());	
      } catch (SchedulerException var4) {	
         throw new ServiceException("运行一次失败！", var4);	
      }	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void resume(JobEntity job) {	
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
	
   public boolean startAll() {	
      if (!ObjectUtils.toBoolean(j.ALLATORIxDEMO().get("fnJob"))) {	
         return false;	
      } else {	
         try {	
            this.scheduler.setStartupDelay(0);	
            this.scheduler.start();	
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
   public void pause(JobEntity job) {	
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
	
   // $FF: synthetic method	
   @PostConstruct	
   private void initialize() throws Exception {	
      if (!Global.isTestProfileActive()) {	
         this.scheduler = new SchedulerFactoryBean();	
         this.scheduler.setAutoStartup(ObjectUtils.toBoolean(Global.getProperty("job.autoStartup", "true")));	
         this.scheduler.setStartupDelay(ObjectUtils.toInteger(Global.getProperty("job.startupDelay", "60")));	
         if (!ObjectUtils.toBoolean(j.ALLATORIxDEMO().get("fnJob"))) {	
            this.scheduler.afterPropertiesSet();	
         } else {	
            this.scheduler.setQuartzProperties(l.ALLATORIxDEMO());	
            this.scheduler.setDataSource(this.dataSource);	
            this.scheduler.afterPropertiesSet();	
            if (this.scheduler.isAutoStartup()) {	
               this.scheduler.start();	
            }	
	
            try {	
               ListenerManager a;	
               ListenerManager var10000 = a = this.getScheduler().getListenerManager();	
               var10000.addJobListener(new JobDetalListener(this.jobLogService));	
               a.addTriggerListener(new JobTriggerListener(this.jobLogService));	
               var10000.addSchedulerListener(new JobSchedulerListener(this.jobLogService));	
            } catch (SchedulerException var7) {	
               this.logger.error("作业监听事件异常！", var7);	
            }	
	
            JobEntity a = new JobEntity();	
            Iterator var3 = super.findList(a).iterator();	
	
            while(var3.hasNext()) {	
               JobEntity a = (JobEntity)var3.next();	
               if ("1".equals(a.getStatus())) {	
                  this.delete(a);	
               } else {	
                  try {	
                     this.save(a, false);	
                  } catch (Exception var6) {	
                     this.logger.warn(var6.getMessage(), var6);	
                  }	
               }	
            }	
	
         }	
      }	
   }	
}	
