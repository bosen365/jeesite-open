package com.jeesite.modules.job.entity;	
	
import com.fasterxml.jackson.annotation.JsonFormat;	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.j.e;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.query.QueryType;	
import java.util.Date;	
import org.hibernate.validator.constraints.Length;	
import org.hyperic.sigar.ProcTime;	
import org.quartz.Scheduler;	
import org.quartz.SchedulerException;	
import org.quartz.Trigger.TriggerState;	
import org.quartz.impl.triggers.CronTriggerImpl;	
	
@Table(	
   name = "${_prefix}sys_job",	
   alias = "a",	
   columns = {@Column(	
   includeEntity = DataEntity.class	
), @Column(	
   name = "job_name",	
   attrName = "jobName",	
   label = "任务名称",	
   isPK = true	
), @Column(	
   name = "job_group",	
   attrName = "jobGroup",	
   label = "任务分组",	
   isPK = true	
), @Column(	
   name = "description",	
   attrName = "description",	
   label = "任务描述"	
), @Column(	
   name = "invoke_target",	
   attrName = "invokeTarget",	
   label = "调用目标字符串",	
   queryType = QueryType.LIKE	
), @Column(	
   name = "cron_expression",	
   attrName = "cronExpression",	
   label = "Cron执行表达式"	
), @Column(	
   name = "misfire_instruction",	
   attrName = "misfireInstruction",	
   label = "计划执行错误策略"	
), @Column(	
   name = "concurrent",	
   attrName = "concurrent",	
   label = "是否并发执行"	
)},	
   orderBy = "a.job_group, a.job_name"	
)	
public class JobEntity extends DataEntity {	
   private static final long serialVersionUID = 1L;	
   public static final String STATUS_DELETE = "1";	
   private String invokeTarget;	
   private String description;	
   public static final String STATUS_COMPLETE = "3";	
   public static final String STATUS_PAUSED = "2";	
   public static final String STATUS_NORMAL = "0";	
   private String cronExpression;	
   private String concurrent;	
   public static final String STATUS_ERROR = "4";	
   private Date prevFireTime;	
   private Integer misfireInstruction;	
   private String jobGroup;	
   public static final String STATUS_BLOCKED = "5";	
   private String jobName;	
   private Date nextFireTime;	
	
   public void setMisfireInstruction(Integer misfireInstruction) {	
      this.misfireInstruction = misfireInstruction;	
   }	
	
   public void setInvokeTarget(String invokeTarget) {	
      this.invokeTarget = invokeTarget;	
   }	
	
   public void setCronExpression(String cronExpression) {	
      this.cronExpression = cronExpression;	
   }	
	
   public String getJobName() {	
      return this.jobName;	
   }	
	
   public JobEntity convert(Scheduler scheduler, CronTriggerImpl trigger) {	
      JobEntity a = this;	
      this.setJobName(trigger.getName());	
      this.setJobGroup(trigger.getGroup());	
      this.setDescription(trigger.getDescription());	
      this.setInvokeTarget((String)trigger.getJobDataMap().get("invokeTarget"));	
      this.setCronExpression(trigger.getCronExpression());	
      this.setMisfireInstruction(trigger.getMisfireInstruction());	
      this.setConcurrent((String)trigger.getJobDataMap().get("concurrent"));	
      this.setRemarks((String)trigger.getJobDataMap().get("remarks"));	
	
      try {	
         TriggerState a = scheduler.getTriggerState(trigger.getKey());	
         if (TriggerState.NORMAL.equals(a)) {	
            a.setStatus("0");	
         } else if (TriggerState.NONE.equals(a)) {	
            a.setStatus("1");	
         } else if (TriggerState.PAUSED.equals(a)) {	
            a.setStatus("2");	
         } else if (TriggerState.COMPLETE.equals(a)) {	
            a.setStatus("3");	
         } else if (TriggerState.ERROR.equals(a)) {	
            a.setStatus("4");	
         } else if (TriggerState.BLOCKED.equals(a)) {	
            a.setStatus("5");	
         }	
      } catch (SchedulerException var5) {	
         this.setStatus("1");	
      }	
	
      this.setPrevFireTime(trigger.getPreviousFireTime());	
      this.setNextFireTime(trigger.getNextFireTime());	
      return this;	
   }	
	
   public JobEntity(String jobName, String var2) {	
      this.jobName = jobName;	
      this.jobGroup = var2;	
   }	
	
   @Length(	
      min = 0,	
      max = 100,	
      message = "任务描述长度不能超过 100 个字符"	
   )	
   public String getDescription() {	
      return this.description;	
   }	
	
   public Integer getMisfireInstruction() {	
      return this.misfireInstruction;	
   }	
	
   @Length(	
      min = 0,	
      max = 1,	
      message = "是否并发执行长度不能超过 1 个字符"	
   )	
   public String getConcurrent() {	
      return this.concurrent;	
   }	
	
   public void setJobName(String jobName) {	
      this.jobName = jobName;	
   }	
	
   @JsonFormat(	
      pattern = "yyyy-MM-dd HH:mm:ss"	
   )	
   public Date getPrevFireTime() {	
      return this.prevFireTime;	
   }	
	
   public void setDescription(String description) {	
      this.description = description;	
   }	
	
   public void setNextFireTime(Date nextFireTime) {	
      this.nextFireTime = nextFireTime;	
   }	
	
   public void setPrevFireTime(Date prevFireTime) {	
      this.prevFireTime = prevFireTime;	
   }	
	
   public void setJobGroup(String jobGroup) {	
      this.jobGroup = jobGroup;	
   }	
	
   public JobEntity() {	
   }	
	
   public String getJobGroup() {	
      return this.jobGroup;	
   }	
	
   @Length(	
      min = 0,	
      max = 1000,	
      message = "调用目标字符串长度不能超过 1000 个字符"	
   )	
   public String getInvokeTarget() {	
      return this.invokeTarget;	
   }	
	
   public void setConcurrent(String concurrent) {	
      this.concurrent = concurrent;	
   }	
	
   @JsonFormat(	
      pattern = "yyyy-MM-dd HH:mm:ss"	
   )	
   public Date getNextFireTime() {	
      return this.nextFireTime;	
   }	
	
   @Length(	
      min = 0,	
      max = 255,	
      message = "Cron执行表达式长度不能超过 255 个字符"	
   )	
   public String getCronExpression() {	
      return this.cronExpression;	
   }	
}	
