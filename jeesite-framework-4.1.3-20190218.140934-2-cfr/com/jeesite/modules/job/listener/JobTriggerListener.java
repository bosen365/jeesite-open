/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  org.quartz.JobExecutionContext	
 *  org.quartz.Trigger	
 *  org.quartz.Trigger$CompletedExecutionInstruction	
 *  org.quartz.TriggerKey	
 *  org.quartz.listeners.TriggerListenerSupport	
 *  org.slf4j.Logger	
 */	
package com.jeesite.modules.job.listener;	
	
import com.jeesite.modules.job.entity.JobLog;	
import com.jeesite.modules.job.service.JobLogService;	
import org.hyperic.sigar.ProcMem;	
import org.hyperic.sigar.shell.ShellCommandUsageException;	
import org.quartz.JobExecutionContext;	
import org.quartz.Trigger;	
import org.quartz.TriggerKey;	
import org.quartz.listeners.TriggerListenerSupport;	
import org.slf4j.Logger;	
	
public class JobTriggerListener	
extends TriggerListenerSupport {	
    private JobLogService jobLogService;	
	
    public void triggerComplete(Trigger trigger, JobExecutionContext context, Trigger.CompletedExecutionInstruction triggerInstructionCode) {	
        void a2;	
        JobTriggerListener jobTriggerListener = this;	
        jobTriggerListener.getLog().debug(new StringBuilder().insert(0, "triggerComplete 触发完成  ").append((Object)trigger.getKey()).toString());	
        JobLog jobLog = new JobLog();	
        void v1 = a2;	
        void v2 = a2;	
        Trigger trigger2 = trigger;	
        a2.setJobName(trigger2.getKey().getName());	
        v2.setJobGroup(trigger2.getKey().getGroup());	
        v2.setJobType("trigger");	
        v1.setJobEvent("triggerComplete");	
        v1.setJobMessage("触发完成");	
        jobTriggerListener.jobLogService.save((JobLog)a2);	
    }	
	
    public String getName() {	
        return ((Object)((Object)this)).getClass().getName();	
    }	
	
    public JobTriggerListener(JobLogService jobLogService) {	
        this.jobLogService = jobLogService;	
    }	
	
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {	
        void a2;	
        boolean a3;	
        this.getLog().debug(new StringBuilder().insert(0, "vetoJobExecution 触发验证  ").append((Object)trigger.getKey()).append(" ").append((a3 = false) ? "驳回" : "通过").toString());	
        JobLog jobLog = new JobLog();	
        void v0 = a2;	
        void v1 = a2;	
        v1.setJobName(trigger.getKey().getName());	
        v1.setJobGroup(trigger.getKey().getGroup());	
        v0.setJobType("trigger");	
        v0.setJobEvent("vetoJobExecution");	
        void v2 = a2;	
        v0.setJobMessage("触发验证" + (a3 ? "驳回" : "通过"));	
        this.jobLogService.save((JobLog)a2);	
        return a3;	
    }	
	
    public void triggerMisfired(Trigger trigger) {	
        void a2;	
        JobTriggerListener jobTriggerListener = this;	
        jobTriggerListener.getLog().debug(new StringBuilder().insert(0, "triggerMisfired 触发错过  ").append((Object)trigger.getKey()).toString());	
        JobLog jobLog = new JobLog();	
        void v1 = a2;	
        void v2 = a2;	
        Trigger trigger2 = trigger;	
        a2.setJobName(trigger2.getKey().getName());	
        v2.setJobGroup(trigger2.getKey().getGroup());	
        v2.setJobType("trigger");	
        v1.setJobEvent("triggerMisfired");	
        v1.setJobMessage("触发错过");	
        jobTriggerListener.jobLogService.save((JobLog)a2);	
    }	
	
    public void triggerFired(Trigger trigger, JobExecutionContext context) {	
        void a2;	
        JobTriggerListener jobTriggerListener = this;	
        jobTriggerListener.getLog().debug(new StringBuilder().insert(0, "triggerFired 触发计划  ").append((Object)trigger.getKey()).toString());	
        JobLog jobLog = new JobLog();	
        void v1 = a2;	
        void v2 = a2;	
        Trigger trigger2 = trigger;	
        a2.setJobName(trigger2.getKey().getName());	
        v2.setJobGroup(trigger2.getKey().getGroup());	
        v2.setJobType("trigger");	
        v1.setJobEvent("triggerFired");	
        v1.setJobMessage("触发计划");	
        jobTriggerListener.jobLogService.save((JobLog)a2);	
    }	
}	
	
