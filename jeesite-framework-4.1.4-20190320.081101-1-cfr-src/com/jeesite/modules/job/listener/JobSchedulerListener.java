/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.lang.ExceptionUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  org.quartz.JobKey	
 *  org.quartz.SchedulerException	
 *  org.quartz.Trigger	
 *  org.quartz.TriggerKey	
 *  org.quartz.listeners.SchedulerListenerSupport	
 *  org.quartz.utils.Key	
 *  org.slf4j.Logger	
 */	
package com.jeesite.modules.job.listener;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.lang.ExceptionUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.modules.job.entity.JobLog;	
import com.jeesite.modules.job.service.JobLogService;	
import org.hyperic.sigar.NetRoute;	
import org.hyperic.sigar.pager.PageFetcher;	
import org.quartz.JobKey;	
import org.quartz.SchedulerException;	
import org.quartz.Trigger;	
import org.quartz.TriggerKey;	
import org.quartz.listeners.SchedulerListenerSupport;	
import org.quartz.utils.Key;	
import org.slf4j.Logger;	
	
public class JobSchedulerListener	
extends SchedulerListenerSupport {	
    private JobLogService jobLogService;	
    private boolean errorLevel;	
	
    public void jobUnscheduled(TriggerKey triggerKey) {	
        if (!this.errorLevel) {	
            void a;	
            JobSchedulerListener jobSchedulerListener = this;	
            jobSchedulerListener.getLog().debug(new StringBuilder().insert(0, "jobUnscheduled 计划移除  ").append((Object)triggerKey).toString());	
            JobLog jobLog = new JobLog();	
            void v1 = a;	
            void v2 = a;	
            TriggerKey triggerKey2 = triggerKey;	
            a.setJobName(triggerKey2.getName());	
            v2.setJobGroup(triggerKey2.getGroup());	
            v2.setJobType("scheduler");	
            v1.setJobEvent("jobUnscheduled");	
            v1.setJobMessage("计划移除");	
            jobSchedulerListener.jobLogService.save((JobLog)a);	
        }	
    }	
	
    public void jobScheduled(Trigger trigger) {	
        if (!this.errorLevel) {	
            void a;	
            JobSchedulerListener jobSchedulerListener = this;	
            jobSchedulerListener.getLog().debug(new StringBuilder().insert(0, "jobScheduled 计划创建  ").append((Object)trigger.getKey()).toString());	
            JobLog jobLog = new JobLog();	
            void v1 = a;	
            void v2 = a;	
            Trigger trigger2 = trigger;	
            a.setJobName(trigger2.getJobKey().getName());	
            v2.setJobGroup(trigger2.getJobKey().getGroup());	
            v2.setJobType("scheduler");	
            v1.setJobEvent("jobScheduled");	
            v1.setJobMessage("计划创建");	
            jobSchedulerListener.jobLogService.save((JobLog)a);	
        }	
    }	
	
    public void triggerPaused(TriggerKey triggerKey) {	
        if (!this.errorLevel) {	
            void a;	
            JobSchedulerListener jobSchedulerListener = this;	
            jobSchedulerListener.getLog().debug(new StringBuilder().insert(0, "triggerPaused 计划暂停  ").append((Object)triggerKey).toString());	
            JobLog jobLog = new JobLog();	
            void v1 = a;	
            void v2 = a;	
            TriggerKey triggerKey2 = triggerKey;	
            a.setJobName(triggerKey2.getName());	
            v2.setJobGroup(triggerKey2.getGroup());	
            v2.setJobType("scheduler");	
            v1.setJobEvent("triggerPaused");	
            v1.setJobMessage("计划暂停");	
            jobSchedulerListener.jobLogService.save((JobLog)a);	
        }	
    }	
	
    public void triggerResumed(TriggerKey triggerKey) {	
        if (!this.errorLevel) {	
            void a;	
            JobSchedulerListener jobSchedulerListener = this;	
            jobSchedulerListener.getLog().debug(new StringBuilder().insert(0, "triggerResumed 计划恢复  ").append((Object)triggerKey).toString());	
            JobLog jobLog = new JobLog();	
            void v1 = a;	
            void v2 = a;	
            TriggerKey triggerKey2 = triggerKey;	
            a.setJobName(triggerKey2.getName());	
            v2.setJobGroup(triggerKey2.getGroup());	
            v2.setJobType("scheduler");	
            v1.setJobEvent("triggerResumed");	
            v1.setJobMessage("计划恢复");	
            jobSchedulerListener.jobLogService.save((JobLog)a);	
        }	
    }	
	
    private static /* synthetic */ Key<Object> getKey(String msg) {	
        String a = StringUtils.substringAfter((String)msg, (String)"trigger=");	
        if (StringUtils.isBlank((CharSequence)a)) {	
            a = StringUtils.substringAfter((String)msg, (String)"job=");	
        }	
        boolean a2 = false;	
        String a3 = StringUtils.substringBefore((String)a, (String)".");	
        if (StringUtils.startsWith((CharSequence)(a3 = StringUtils.trim((String)a3)), (CharSequence)"'")) {	
            a3 = StringUtils.substring((String)a3, (int)1);	
            a2 = true;	
        }	
        String a4 = StringUtils.substringAfter((String)a, (String)".");	
        if (StringUtils.isBlank((CharSequence)(a4 = StringUtils.substringBefore((String)a4, (String)(a2 ? "'" : " "))))) {	
            a4 = "_error_";	
        }	
        if (StringUtils.isBlank((CharSequence)a3)) {	
            a3 = "_error_";	
        }	
        return new Key(a4, a3);	
    }	
	
    public JobSchedulerListener(JobLogService jobLogService) {	
        JobSchedulerListener jobSchedulerListener = this;	
        this.errorLevel = false;	
        jobSchedulerListener.jobLogService = jobLogService;	
        jobSchedulerListener.errorLevel = Global.getPropertyToBoolean("job.log.scheduler.errorLevel", "false");	
    }	
	
    public void schedulerError(String msg, SchedulerException cause) {	
        if (!this.errorLevel || cause != null) {	
            void a;	
            this.getLog().debug(new StringBuilder().insert(0, "schedulerError 调度错误  ").append(msg).toString(), (Throwable)cause);	
            JobLog a2 = new JobLog();	
            Key<Object> key = JobSchedulerListener.getKey(msg);	
            JobLog jobLog = a2;	
            JobLog jobLog2 = a2;	
            void v2 = a;	
            a2.setJobName(v2.getName());	
            jobLog2.setJobGroup(v2.getGroup());	
            jobLog2.setJobType("scheduler");	
            jobLog.setJobEvent("schedulerError");	
            jobLog.setJobMessage("调度错误");	
            if (cause != null) {	
                JobLog jobLog3 = a2;	
                jobLog3.setIsException("1");	
                jobLog3.setExceptionInfo(ExceptionUtils.getStackTraceAsString((Throwable)cause));	
            }	
            this.jobLogService.save(a2);	
        }	
    }	
}	
	
