/*	
 * Decompiled with CFR 0.139.	
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
import com.jeesite.modules.sys.web.AdviceController;	
import org.hyperic.sigar.pager.SortAttribute;	
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
	
    public void triggerPaused(TriggerKey triggerKey) {	
        if (!this.errorLevel) {	
            void a2;	
            JobSchedulerListener jobSchedulerListener = this;	
            jobSchedulerListener.getLog().debug(new StringBuilder().insert(0, "triggerPaused 计划暂停  ").append((Object)triggerKey).toString());	
            JobLog jobLog = new JobLog();	
            void v1 = a2;	
            void v2 = a2;	
            TriggerKey triggerKey2 = triggerKey;	
            a2.setJobName(triggerKey2.getName());	
            v2.setJobGroup(triggerKey2.getGroup());	
            v2.setJobType("scheduler");	
            v1.setJobEvent("triggerPaused");	
            v1.setJobMessage("计划暂停");	
            jobSchedulerListener.jobLogService.save((JobLog)a2);	
        }	
    }	
	
    public JobSchedulerListener(JobLogService jobLogService) {	
        JobSchedulerListener jobSchedulerListener = this;	
        this.errorLevel = false;	
        jobSchedulerListener.jobLogService = jobLogService;	
        jobSchedulerListener.errorLevel = Global.getPropertyToBoolean("job.log.scheduler.errorLevel", "false");	
    }	
	
    public void jobUnscheduled(TriggerKey triggerKey) {	
        if (!this.errorLevel) {	
            void a2;	
            JobSchedulerListener jobSchedulerListener = this;	
            jobSchedulerListener.getLog().debug(new StringBuilder().insert(0, "jobUnscheduled 计划移除  ").append((Object)triggerKey).toString());	
            JobLog jobLog = new JobLog();	
            void v1 = a2;	
            void v2 = a2;	
            TriggerKey triggerKey2 = triggerKey;	
            a2.setJobName(triggerKey2.getName());	
            v2.setJobGroup(triggerKey2.getGroup());	
            v2.setJobType("scheduler");	
            v1.setJobEvent("jobUnscheduled");	
            v1.setJobMessage("计划移除");	
            jobSchedulerListener.jobLogService.save((JobLog)a2);	
        }	
    }	
	
    private static /* synthetic */ Key<Object> getKey(String msg) {	
        String a2 = StringUtils.substringAfter((String)msg, (String)"trigger=");	
        if (StringUtils.isBlank((CharSequence)a2)) {	
            a2 = StringUtils.substringAfter((String)msg, (String)"job=");	
        }	
        boolean a3 = false;	
        String a4 = StringUtils.substringBefore((String)a2, (String)".");	
        if (StringUtils.startsWith((CharSequence)(a4 = StringUtils.trim((String)a4)), (CharSequence)"'")) {	
            a4 = StringUtils.substring((String)a4, (int)1);	
            a3 = true;	
        }	
        String a5 = StringUtils.substringAfter((String)a2, (String)".");	
        if (StringUtils.isBlank((CharSequence)(a5 = StringUtils.substringBefore((String)a5, (String)(a3 ? "'" : " "))))) {	
            a5 = "_error_";	
        }	
        if (StringUtils.isBlank((CharSequence)a4)) {	
            a4 = "_error_";	
        }	
        return new Key(a5, a4);	
    }	
	
    public void schedulerError(String msg, SchedulerException cause) {	
        if (!this.errorLevel || cause != null) {	
            void a2;	
            this.getLog().debug(new StringBuilder().insert(0, "schedulerError 调度错误  ").append(msg).toString(), (Throwable)cause);	
            JobLog a3 = new JobLog();	
            Key<Object> key = JobSchedulerListener.getKey(msg);	
            JobLog jobLog = a3;	
            JobLog jobLog2 = a3;	
            void v2 = a2;	
            a3.setJobName(v2.getName());	
            jobLog2.setJobGroup(v2.getGroup());	
            jobLog2.setJobType("scheduler");	
            jobLog.setJobEvent("schedulerError");	
            jobLog.setJobMessage("调度错误");	
            if (cause != null) {	
                JobLog jobLog3 = a3;	
                jobLog3.setIsException("1");	
                jobLog3.setExceptionInfo(ExceptionUtils.getStackTraceAsString((Throwable)cause));	
            }	
            this.jobLogService.save(a3);	
        }	
    }	
	
    public void triggerResumed(TriggerKey triggerKey) {	
        if (!this.errorLevel) {	
            void a2;	
            JobSchedulerListener jobSchedulerListener = this;	
            jobSchedulerListener.getLog().debug(new StringBuilder().insert(0, "triggerResumed 计划恢复  ").append((Object)triggerKey).toString());	
            JobLog jobLog = new JobLog();	
            void v1 = a2;	
            void v2 = a2;	
            TriggerKey triggerKey2 = triggerKey;	
            a2.setJobName(triggerKey2.getName());	
            v2.setJobGroup(triggerKey2.getGroup());	
            v2.setJobType("scheduler");	
            v1.setJobEvent("triggerResumed");	
            v1.setJobMessage("计划恢复");	
            jobSchedulerListener.jobLogService.save((JobLog)a2);	
        }	
    }	
	
    public void jobScheduled(Trigger trigger) {	
        if (!this.errorLevel) {	
            void a2;	
            JobSchedulerListener jobSchedulerListener = this;	
            jobSchedulerListener.getLog().debug(new StringBuilder().insert(0, "jobScheduled 计划创建  ").append((Object)trigger.getKey()).toString());	
            JobLog jobLog = new JobLog();	
            void v1 = a2;	
            void v2 = a2;	
            Trigger trigger2 = trigger;	
            a2.setJobName(trigger2.getJobKey().getName());	
            v2.setJobGroup(trigger2.getJobKey().getGroup());	
            v2.setJobType("scheduler");	
            v1.setJobEvent("jobScheduled");	
            v1.setJobMessage("计划创建");	
            jobSchedulerListener.jobLogService.save((JobLog)a2);	
        }	
    }	
}	
	
