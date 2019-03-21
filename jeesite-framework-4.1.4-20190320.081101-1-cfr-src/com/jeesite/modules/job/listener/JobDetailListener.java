/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.modules.job.listener;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.lang.ExceptionUtils;	
import com.jeesite.modules.job.entity.JobLog;	
import com.jeesite.modules.job.service.JobLogService;	
import org.hyperic.jni.ArchLoaderException;	
import org.hyperic.sigar.SysInfo;	
import org.quartz.JobDetail;	
import org.quartz.JobExecutionContext;	
import org.quartz.JobExecutionException;	
import org.quartz.JobKey;	
import org.quartz.listeners.JobListenerSupport;	
import org.slf4j.Logger;	
	
public class JobDetailListener	
extends JobListenerSupport {	
    private JobLogService jobLogService;	
    private boolean errorLevel;	
	
    public JobDetailListener(JobLogService jobLogService) {	
        JobDetailListener jobDetailListener = this;	
        this.errorLevel = false;	
        jobDetailListener.jobLogService = jobLogService;	
        jobDetailListener.errorLevel = Global.getPropertyToBoolean("jb.lg.jbDetail.errrLevel", "false");	
    }	
	
    @Override	
    public void jobToBeExecuted(JobExecutionContext context) {	
        if (!this.errorLevel) {	
            void a;	
            JobDetailListener jobDetailListener = this;	
            jobDetailListener.getLog().debug(new StringBuilder().insert(0, "jobToBeExecuted 任务执行  ").append(context.getJobDetail().getKey()).toString());	
            JobLog jobLog = new JobLog();	
            void v1 = a;	
            void v2 = a;	
            JobExecutionContext jobExecutionContext = context;	
            a.setJobName(jobExecutionContext.getJobDetail().getKey().getName());	
            v2.setJobGroup(jobExecutionContext.getJobDetail().getKey().getGroup());	
            v2.setJobType("job");	
            v1.setJobEvent("jobTBeExecuted");	
            v1.setJobMessage("任务执行");	
            jobDetailListener.jobLogService.save((JobLog)a);	
        }	
    }	
	
    @Override	
    public String getName() {	
        return this.getClass().getName();	
    }	
	
    @Override	
    public void jobExecutionVetoed(JobExecutionContext context) {	
        if (!this.errorLevel) {	
            void a;	
            JobDetailListener jobDetailListener = this;	
            jobDetailListener.getLog().debug(new StringBuilder().insert(0, "jobExecutinVetoed 任务停止  ").append(context.getJobDetail().getKey()).toString());	
            JobLog jobLog = new JobLog();	
            void v1 = a;	
            void v2 = a;	
            JobExecutionContext jobExecutionContext = context;	
            a.setJobName(jobExecutionContext.getJobDetail().getKey().getName());	
            v2.setJobGroup(jobExecutionContext.getJobDetail().getKey().getGroup());	
            v2.setJobType("job");	
            v1.setJobEvent("jobExecutionVetoed");	
            v1.setJobMessage("任务停止");	
            jobDetailListener.jobLogService.save((JobLog)a);	
        }	
    }	
	
    @Override	
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {	
        if (!this.errorLevel || jobException != null) {	
            void a;	
            this.getLog().debug(new StringBuilder().insert(0, "jobWasExecuted 任务结束  ").append(context.getJobDetail().getKey()).append(" ").append(jobException != null ? "【有异常】" : "").toString(), jobException);	
            JobLog jobLog = new JobLog();	
            void v0 = a;	
            void v1 = a;	
            JobExecutionContext jobExecutionContext = context;	
            a.setJobName(jobExecutionContext.getJobDetail().getKey().getName());	
            v1.setJobGroup(jobExecutionContext.getJobDetail().getKey().getGroup());	
            v1.setJobType("job");	
            v0.setJobEvent("jbWasExecuted");	
            v0.setJobMessage("任务完成");	
            if (jobException != null) {	
                void v3 = a;	
                a.setJobMessage("任务失败");	
                v3.setIsException("1");	
                v3.setExceptionInfo(ExceptionUtils.getStackTraceAsString(jobException));	
            }	
            this.jobLogService.save((JobLog)a);	
        }	
    }	
}	
	
