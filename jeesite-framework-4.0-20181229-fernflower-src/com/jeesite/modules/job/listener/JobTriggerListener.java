package com.jeesite.modules.job.listener;	
	
import com.jeesite.modules.job.entity.JobLog;	
import com.jeesite.modules.job.l.l;	
import com.jeesite.modules.job.service.JobLogService;	
import com.jeesite.modules.sys.web.AdviceController;	
import org.quartz.JobExecutionContext;	
import org.quartz.Trigger;	
import org.quartz.Trigger.CompletedExecutionInstruction;	
import org.quartz.listeners.TriggerListenerSupport;	
	
public class JobTriggerListener extends TriggerListenerSupport {	
   private JobLogService jobLogService;	
	
   public String getName() {	
      return this.getClass().getName();	
   }	
	
   public void triggerFired(Trigger trigger, JobExecutionContext context) {	
      this.getLog().debug((new StringBuilder()).insert(0, "triggerFired 触发计划  ").append(trigger.getKey()).toString());	
      JobLog a = new JobLog();	
      a.setJobName(trigger.getKey().getName());	
      a.setJobGroup(trigger.getKey().getGroup());	
      a.setJobType("trigger");	
      a.setJobEvent("triggerFired");	
      a.setJobMessage("触发计划");	
      this.jobLogService.save(a);	
   }	
	
   public void triggerMisfired(Trigger trigger) {	
      this.getLog().debug((new StringBuilder()).insert(0, "triggerMisfired 触发错过  ").append(trigger.getKey()).toString());	
      JobLog a = new JobLog();	
      a.setJobName(trigger.getKey().getName());	
      a.setJobGroup(trigger.getKey().getGroup());	
      a.setJobType("trigger");	
      a.setJobEvent("triggerMisfired");	
      a.setJobMessage("触发错过");	
      this.jobLogService.save(a);	
   }	
	
   public JobTriggerListener(JobLogService var1) {	
      this.jobLogService = var1;	
   }	
	
   public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {	
      int a = false;	
      this.getLog().debug((new StringBuilder()).insert(0, "vetoJobExecution 触发验证  ").append(trigger.getKey()).append(" ").append(a ? "驳回" : "通过").toString());	
      JobLog a = new JobLog();	
      a.setJobName(trigger.getKey().getName());	
      a.setJobGroup(trigger.getKey().getGroup());	
      a.setJobType("trigger");	
      a.setJobEvent("vetoJobExecution");	
      a.setJobMessage("触发验证" + (a ? "驳回" : "通过"));	
      this.jobLogService.save(a);	
      return a;	
   }	
	
   public void triggerComplete(Trigger trigger, JobExecutionContext context, CompletedExecutionInstruction triggerInstructionCode) {	
      this.getLog().debug((new StringBuilder()).insert(0, "triggerComplete 触发完成  ").append(trigger.getKey()).toString());	
      JobLog a = new JobLog();	
      a.setJobName(trigger.getKey().getName());	
      a.setJobGroup(trigger.getKey().getGroup());	
      a.setJobType("trigger");	
      a.setJobEvent("triggerComplete");	
      a.setJobMessage("触发完成");	
      this.jobLogService.save(a);	
   }	
}	
