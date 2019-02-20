/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.lang.ExceptionUtils	
 *  org.quartz.JobDetail	
 *  org.quartz.JobExecutionContext	
 *  org.quartz.JobExecutionException	
 *  org.quartz.JobKey	
 *  org.quartz.listeners.JobListenerSupport	
 *  org.slf4j.Logger	
 */	
package com.jeesite.modules.job.listener;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.lang.ExceptionUtils;	
import com.jeesite.modules.job.entity.JobLog;	
import com.jeesite.modules.job.service.JobLogService;	
import org.hyperic.sigar.ProcFd;	
import org.hyperic.sigar.win32.EventLogRecord;	
import org.quartz.JobDetail;	
import org.quartz.JobExecutionContext;	
import org.quartz.JobExecutionException;	
import org.quartz.JobKey;	
import org.quartz.listeners.JobListenerSupport;	
import org.slf4j.Logger;	
	
public class JobDetailListener	
extends JobListenerSupport {	
    private boolean errorLevel;	
    private JobLogService jobLogService;	
	
    public void jobExecutionVetoed(JobExecutionContext context) {	
        if (!this.errorLevel) {	
            void a2;	
            JobDetailListener jobDetailListener = this;	
            jobDetailListener.getLog().debug(new StringBuilder().insert(0, "jobExecutionVetoed 任务停止  ").append((Object)context.getJobDetail().getKey()).toString());	
            JobLog jobLog = new JobLog();	
            void v1 = a2;	
            void v2 = a2;	
            JobExecutionContext jobExecutionContext = context;	
            a2.setJobName(jobExecutionContext.getJobDetail().getKey().getName());	
            v2.setJobGroup(jobExecutionContext.getJobDetail().getKey().getGroup());	
            v2.setJobType("job");	
            v1.setJobEvent("jobExecutionVetoed");	
            v1.setJobMessage("任务停止");	
            jobDetailListener.jobLogService.save((JobLog)a2);	
        }	
    }	
	
    public void jobToBeExecuted(JobExecutionContext context) {	
        if (!this.errorLevel) {	
            void a2;	
            JobDetailListener jobDetailListener = this;	
            jobDetailListener.getLog().debug(new StringBuilder().insert(0, "jobToBeExecuted 任务执行  ").append((Object)context.getJobDetail().getKey()).toString());	
            JobLog jobLog = new JobLog();	
            void v1 = a2;	
            void v2 = a2;	
            JobExecutionContext jobExecutionContext = context;	
            a2.setJobName(jobExecutionContext.getJobDetail().getKey().getName());	
            v2.setJobGroup(jobExecutionContext.getJobDetail().getKey().getGroup());	
            v2.setJobType("job");	
            v1.setJobEvent("jobToBeExecuted");	
            v1.setJobMessage("任务执行");	
            jobDetailListener.jobLogService.save((JobLog)a2);	
        }	
    }	
	
    public String getName() {	
        return ((Object)((Object)this)).getClass().getName();	
    }	
	
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {	
        if (!this.errorLevel || jobException != null) {	
            void a2;	
            this.getLog().debug(new StringBuilder().insert(0, "jobWaExecuted 任务结束  ").append((Object)context.getJobDetail().getKey()).append(" ").append(jobException != null ? "【有异常】" : "").toString(), (Throwable)jobException);	
            JobLog jobLog = new JobLog();	
            void v0 = a2;	
            void v1 = a2;	
            JobExecutionContext jobExecutionContext = context;	
            a2.setJobName(jobExecutionContext.getJobDetail().getKey().getName());	
            v1.setJobGroup(jobExecutionContext.getJobDetail().getKey().getGroup());	
            v1.setJobType("job");	
            v0.setJobEvent("jobWasExecuted");	
            v0.setJobMessage("任务完成");	
            if (jobException != null) {	
                void v3 = a2;	
                a2.setJobMessage("任务失败");	
                v3.setIsException("1");	
                v3.setExceptionInfo(ExceptionUtils.getStackTraceAsString((Throwable)jobException));	
            }	
            this.jobLogService.save((JobLog)a2);	
        }	
    }	
	
    public JobDetailListener(JobLogService jobLogService) {	
        JobDetailListener jobDetailListener = this;	
        this.errorLevel = false;	
        jobDetailListener.jobLogService = jobLogService;	
        jobDetailListener.errorLevel = Global.getPropertyToBoolean("job.log.jobDetail.errorLevel", "false");	
    }	
}	
	
