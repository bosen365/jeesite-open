package com.jeesite.modules.job.listener;	
	
import com.jeesite.common.lang.ExceptionUtils;	
import com.jeesite.common.mybatis.mapper.query.QueryDataScope;	
import com.jeesite.modules.config.TransactionConfig;	
import com.jeesite.modules.job.entity.JobLog;	
import com.jeesite.modules.job.service.JobLogService;	
import org.quartz.JobExecutionContext;	
import org.quartz.JobExecutionException;	
import org.quartz.listeners.JobListenerSupport;	
	
public class JobDetalListener extends JobListenerSupport {	
   private JobLogService jobLogService;	
	
   public void jobToBeExecuted(JobExecutionContext context) {	
      this.getLog().debug((new StringBuilder()).insert(0, "jobToBeExecuted 任务执行  ").append(context.getJobDetail().getKey()).toString());	
      JobLog a = new JobLog();	
      a.setJobName(context.getJobDetail().getKey().getName());	
      a.setJobGroup(context.getJobDetail().getKey().getGroup());	
      a.setJobType("job");	
      a.setJobEvent("jobToBeExecuted");	
      a.setJobMessage("任务执行");	
      this.jobLogService.save(a);	
   }	
	
   public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {	
      this.getLog().debug((new StringBuilder()).insert(0, "jobWasExecuted 任务结束  ").append(context.getJobDetail().getKey()).append(" ").append(jobException != null ? "【有异常】" : "").toString(), jobException);	
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
	
   public void jobExecutionVetoed(JobExecutionContext context) {	
      this.getLog().debug((new StringBuilder()).insert(0, "jobExecutionVetoed 任务停止  ").append(context.getJobDetail().getKey()).toString());	
      JobLog a = new JobLog();	
      a.setJobName(context.getJobDetail().getKey().getName());	
      a.setJobGroup(context.getJobDetail().getKey().getGroup());	
      a.setJobType("job");	
      a.setJobEvent("jobExecutionVetoed");	
      a.setJobMessage("任务停止");	
      this.jobLogService.save(a);	
   }	
	
   public JobDetalListener(JobLogService var1) {	
      this.jobLogService = var1;	
   }	
	
   public String getName() {	
      return this.getClass().getName();	
   }	
}	
