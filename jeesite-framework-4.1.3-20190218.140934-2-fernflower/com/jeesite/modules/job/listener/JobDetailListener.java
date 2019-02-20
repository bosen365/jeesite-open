package com.jeesite.modules.job.listener;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.lang.ExceptionUtils;	
import com.jeesite.modules.job.entity.JobLog;	
import com.jeesite.modules.job.service.JobLogService;	
import org.hyperic.sigar.ProcFd;	
import org.hyperic.sigar.win32.EventLogRecord;	
import org.quartz.JobExecutionContext;	
import org.quartz.JobExecutionException;	
import org.quartz.listeners.JobListenerSupport;	
	
public class JobDetailListener extends JobListenerSupport {	
   private boolean errorLevel = false;	
   private JobLogService jobLogService;	
	
   public void jobExecutionVetoed(JobExecutionContext context) {	
      if (!this.errorLevel) {	
         this.getLog().debug((new StringBuilder()).insert(0, "jobExecutionVetoed 任务停止  ").append(context.getJobDetail().getKey()).toString());	
         JobLog a = new JobLog();	
         a.setJobName(context.getJobDetail().getKey().getName());	
         a.setJobGroup(context.getJobDetail().getKey().getGroup());	
         a.setJobType("job");	
         a.setJobEvent("jobExecutionVetoed");	
         a.setJobMessage("任务停止");	
         this.jobLogService.save(a);	
      }	
	
   }	
	
   public void jobToBeExecuted(JobExecutionContext context) {	
      if (!this.errorLevel) {	
         this.getLog().debug((new StringBuilder()).insert(0, "jobToBeExecuted 任务执行  ").append(context.getJobDetail().getKey()).toString());	
         JobLog a = new JobLog();	
         a.setJobName(context.getJobDetail().getKey().getName());	
         a.setJobGroup(context.getJobDetail().getKey().getGroup());	
         a.setJobType("job");	
         a.setJobEvent("jobToBeExecuted");	
         a.setJobMessage("任务执行");	
         this.jobLogService.save(a);	
      }	
	
   }	
	
   public String getName() {	
      return this.getClass().getName();	
   }	
	
   public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {	
      if (!this.errorLevel || jobException != null) {	
         this.getLog().debug((new StringBuilder()).insert(0, "jobWaExecuted 任务结束  ").append(context.getJobDetail().getKey()).append(" ").append(jobException != null ? "【有异常】" : "").toString(), jobException);	
         JobLog a = new JobLog();	
         a.setJobName(context.getJobDetail().getKey().getName());	
         a.setJobGroup(context.getJobDetail().getKey().getGroup());	
         a.setJobType("job");	
         a.setJobEvent("jobWasExecuted");	
         a.setJobMessage("任务完成");	
         if (jobException != null) {	
            a.setJobMessage("任务失败");	
            a.setIsException("1");	
            a.setExceptionInfo(ExceptionUtils.getStackTraceAsString(jobException));	
         }	
	
         this.jobLogService.save(a);	
      }	
	
   }	
	
   public JobDetailListener(JobLogService jobLogService) {	
      this.jobLogService = jobLogService;	
      this.errorLevel = Global.getPropertyToBoolean("job.log.jobDetail.errorLevel", "false");	
   }	
}	
