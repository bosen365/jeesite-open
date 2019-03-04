package com.jeesite.modules.job.listener;	
	
import com.jeesite.common.lang.ExceptionUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.mapper.query.QueryDataScope;	
import com.jeesite.modules.job.entity.JobLog;	
import com.jeesite.modules.job.l.l;	
import com.jeesite.modules.job.service.JobLogService;	
import org.quartz.SchedulerException;	
import org.quartz.Trigger;	
import org.quartz.TriggerKey;	
import org.quartz.listeners.SchedulerListenerSupport;	
import org.quartz.utils.Key;	
	
public class JobSchedulerListener extends SchedulerListenerSupport {	
   private JobLogService jobLogService;	
	
   public void jobUnscheduled(TriggerKey triggerKey) {	
      this.getLog().debug((new StringBuilder()).insert(0, "jobUnscheduled 计划移除  ").append(triggerKey).toString());	
      JobLog a = new JobLog();	
      a.setJobName(triggerKey.getName());	
      a.setJobGroup(triggerKey.getGroup());	
      a.setJobType("scheduler");	
      a.setJobEvent("jobUnscheduled");	
      a.setJobMessage("计划移除");	
      this.jobLogService.save(a);	
   }	
	
   // $FF: synthetic method	
   private static Key getKey(String msg) {	
      String a;	
      if (StringUtils.isBlank(a = StringUtils.substringAfter(msg, "trigger="))) {	
         a = StringUtils.substringAfter(msg, "job=");	
      }	
	
      int a = false;	
      String a;	
      if (StringUtils.startsWith(a = StringUtils.trim(StringUtils.substringBefore(a, ".")), "M")) {	
         a = StringUtils.substring(a, 1);	
         a = true;	
      }	
	
      String a;	
      if (StringUtils.isBlank(a = StringUtils.substringBefore(StringUtils.substringAfter(a, "."), a ? "M" : " "))) {	
         a = "_error_";	
      }	
	
      if (StringUtils.isBlank(a)) {	
         a = "_error_";	
      }	
	
      return new Key(a, a);	
   }	
	
   public JobSchedulerListener(JobLogService var1) {	
      this.jobLogService = var1;	
   }	
	
   public void jobScheduled(Trigger trigger) {	
      this.getLog().debug((new StringBuilder()).insert(0, "jobScheduled 计划创建  ").append(trigger.getKey()).toString());	
      JobLog a = new JobLog();	
      a.setJobName(trigger.getJobKey().getName());	
      a.setJobGroup(trigger.getJobKey().getGroup());	
      a.setJobType("scheduler");	
      a.setJobEvent("jobScheduled");	
      a.setJobMessage("计划创建");	
      this.jobLogService.save(a);	
   }	
	
   public void schedulerError(String msg, SchedulerException cause) {	
      this.getLog().debug((new StringBuilder()).insert(0, "schedulerError 调度错误  ").append(msg).toString(), cause);	
      JobLog a = new JobLog();	
      Key a = getKey(msg);	
      a.setJobName(a.getName());	
      a.setJobGroup(a.getGroup());	
      a.setJobType("scheduler");	
      a.setJobEvent("schedulerError");	
      a.setJobMessage("调度错误");	
      if (cause != null) {	
         a.setIsException("1");	
         a.setExceptionInfo(ExceptionUtils.getStackTraceAsString(cause));	
      }	
	
      this.jobLogService.save(a);	
   }	
	
   public void triggerPaused(TriggerKey triggerKey) {	
      this.getLog().debug((new StringBuilder()).insert(0, "triggerPaused 计划暂停  ").append(triggerKey).toString());	
      JobLog a = new JobLog();	
      a.setJobName(triggerKey.getName());	
      a.setJobGroup(triggerKey.getGroup());	
      a.setJobType("scheduler");	
      a.setJobEvent("triggerPaused");	
      a.setJobMessage("计划暂停");	
      this.jobLogService.save(a);	
   }	
	
   public void triggerResumed(TriggerKey triggerKey) {	
      this.getLog().debug((new StringBuilder()).insert(0, "triggerResumed 计划恢复  ").append(triggerKey).toString());	
      JobLog a = new JobLog();	
      a.setJobName(triggerKey.getName());	
      a.setJobGroup(triggerKey.getGroup());	
      a.setJobType("scheduler");	
      a.setJobEvent("triggerResumed");	
      a.setJobMessage("计划恢复");	
      this.jobLogService.save(a);	
   }	
}	
