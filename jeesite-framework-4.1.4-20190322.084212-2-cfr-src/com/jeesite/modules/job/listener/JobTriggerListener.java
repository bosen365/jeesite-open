/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.modules.job.listener;	
	
import com.jeesite.modules.job.entity.JobLog;	
import com.jeesite.modules.job.service.JobLogService;	
import org.hyperic.sigar.DirStat;	
import org.hyperic.sigar.cmd.Watch;	
import org.quartz.JobExecutionContext;	
import org.quartz.Trigger;	
import org.quartz.TriggerKey;	
import org.quartz.listeners.TriggerListenerSupport;	
import org.slf4j.Logger;	
	
public class JobTriggerListener	
extends TriggerListenerSupport {	
    private JobLogService jobLogService;	
	
    public JobTriggerListener(JobLogService jobLogService) {	
        this.jobLogService = jobLogService;	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    @Override	
    public void triggerComplete(Trigger trigger, JobExecutionContext context, Trigger.CompletedExecutionInstruction triggerInstructionCode) {	
        void a;	
        JobTriggerListener jobTriggerListener = this;	
        jobTriggerListener.getLog().debug(new StringBuilder().insert(0, "triggerComplete 触发完成  ").append(trigger.getKey()).toString());	
        JobLog jobLog = new JobLog();	
        void v1 = a;	
        void v2 = a;	
        Trigger trigger2 = trigger;	
        a.setJobName(trigger2.getKey().getName());	
        v2.setJobGroup(trigger2.getKey().getGroup());	
        v2.setJobType("trigger");	
        v1.setJobEvent("triggerComplete");	
        v1.setJobMessage("触发完成");	
        jobTriggerListener.jobLogService.save((JobLog)a);	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    @Override	
    public void triggerFired(Trigger trigger, JobExecutionContext context) {	
        void a;	
        JobTriggerListener jobTriggerListener = this;	
        jobTriggerListener.getLog().debug(new StringBuilder().insert(0, "triggerFired 触发计划  ").append(trigger.getKey()).toString());	
        JobLog jobLog = new JobLog();	
        void v1 = a;	
        void v2 = a;	
        Trigger trigger2 = trigger;	
        a.setJobName(trigger2.getKey().getName());	
        v2.setJobGroup(trigger2.getKey().getGroup());	
        v2.setJobType("trigger");	
        v1.setJobEvent("triggerFired");	
        v1.setJobMessage("触发计划");	
        jobTriggerListener.jobLogService.save((JobLog)a);	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    @Override	
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {	
        void a;	
        boolean a2;	
        this.getLog().debug(new StringBuilder().insert(0, "vetoJobExecution 触发验证  ").append(trigger.getKey()).append(" ").append((a2 = false) ? "驳回" : "通过").toString());	
        JobLog jobLog = new JobLog();	
        void v0 = a;	
        void v1 = a;	
        v1.setJobName(trigger.getKey().getName());	
        v1.setJobGroup(trigger.getKey().getGroup());	
        v0.setJobType("trigger");	
        v0.setJobEvent("vetoJobExecution");	
        void v2 = a;	
        v0.setJobMessage("触发验证" + (a2 ? "驳回" : "通过"));	
        this.jobLogService.save((JobLog)a);	
        return a2;	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    @Override	
    public void triggerMisfired(Trigger trigger) {	
        void a;	
        JobTriggerListener jobTriggerListener = this;	
        jobTriggerListener.getLog().debug(new StringBuilder().insert(0, "triggerMisfired 触发错过  ").append(trigger.getKey()).toString());	
        JobLog jobLog = new JobLog();	
        void v1 = a;	
        void v2 = a;	
        Trigger trigger2 = trigger;	
        a.setJobName(trigger2.getKey().getName());	
        v2.setJobGroup(trigger2.getKey().getGroup());	
        v2.setJobType("trigger");	
        v1.setJobEvent("triggerMisfired");	
        v1.setJobMessage("触发错过");	
        jobTriggerListener.jobLogService.save((JobLog)a);	
    }	
	
    @Override	
    public String getName() {	
        return this.getClass().getName();	
    }	
}	
	
