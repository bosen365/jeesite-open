/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.modules.job.entity;	
	
import com.fasterxml.jackson.annotation.JsonFormat;	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.query.QueryType;	
import com.jeesite.modules.sys.utils.ModuleUtils;	
import java.util.Date;	
import org.hibernate.validator.constraints.Length;	
import org.hyperic.sigar.ProcCredName;	
import org.quartz.JobDataMap;	
import org.quartz.Scheduler;	
import org.quartz.SchedulerException;	
import org.quartz.Trigger;	
import org.quartz.TriggerKey;	
import org.quartz.impl.triggers.CronTriggerImpl;	
	
@Table(name="${_prefix}sys_job", alias="a", columns={@Column(includeEntity=DataEntity.class), @Column(name="job_name", attrName="jobName", label="\u4efb\u52a1\u540d\u79f0", isPK=true), @Column(name="job_group", attrName="jobGroup", label="\u4efb\u52a1\u5206\u7ec4", isPK=true), @Column(name="description", attrName="description", label="\u4efb\u52a1\u63cf\u8ff0"), @Column(name="invoke_target", attrName="invokeTarget", label="\u8c03\u7528\u76ee\u6807\u5b57\u7b26\u4e32", queryType=QueryType.LIKE), @Column(name="cron_expression", attrName="cronExpression", label="Cron\u6267\u884c\u8868\u8fbe\u5f0f"), @Column(name="misfire_instruction", attrName="misfireInstruction", label="\u8ba1\u5212\u6267\u884c\u9519\u8bef\u7b56\u7565"), @Column(name="concurrent", attrName="concurrent", label="\u662f\u5426\u5e76\u53d1\u6267\u884c"), @Column(name="instance_name", attrName="instanceName", label="\u96c6\u7fa4\u7684\u5b9e\u4f8b\u540d\u5b57")}, orderBy="a.job_group, a.job_name")	
public class JobEntity	
extends DataEntity<JobEntity> {	
    private String concurrent;	
    private Date prevFireTime;	
    public static final String STATUS_DELETE = "1";	
    public static final String STATUS_BLOCKED = "5";	
    private Integer misfireInstruction;	
    private String instanceName;	
    private String invokeTarget;	
    private String jobGroup;	
    private static final long serialVersionUID = 1L;	
    public static final String STATUS_COMPLETE = "3";	
    public static final String STATUS_NORMAL = "0";	
    private Date nextFireTime;	
    private String cronExpression;	
    public static final String STATUS_PAUSED = "2";	
    private String jobName;	
    private String description;	
    public static final String STATUS_ERROR = "4";	
	
    public void setMisfireInstruction(Integer misfireInstruction) {	
        this.misfireInstruction = misfireInstruction;	
    }	
	
    public JobEntity(String jobName, String jobGroup) {	
        JobEntity jobEntity = this;	
        jobEntity.jobName = jobName;	
        jobEntity.jobGroup = jobGroup;	
    }	
	
    public void setJobName(String jobName) {	
        this.jobName = jobName;	
    }	
	
    public JobEntity convert(Scheduler scheduler, CronTriggerImpl trigger) {	
        Trigger.TriggerState a;	
        JobEntity a2;	
        block12 : {	
            block11 : {	
                JobEntity jobEntity = a2 = this;	
                CronTriggerImpl cronTriggerImpl = trigger;	
                a2.setJobName(trigger.getName());	
                a2.setJobGroup(cronTriggerImpl.getGroup());	
                jobEntity.setDescription(cronTriggerImpl.getDescription());	
                jobEntity.setInvokeTarget((String)trigger.getJobDataMap().get("invokeTarget"));	
                JobEntity jobEntity2 = a2;	
                CronTriggerImpl cronTriggerImpl2 = trigger;	
                a2.setCronExpression(cronTriggerImpl2.getCronExpression());	
                jobEntity2.setMisfireInstruction(cronTriggerImpl2.getMisfireInstruction());	
                jobEntity2.setConcurrent((String)trigger.getJobDataMap().get("concurrent"));	
                a2.setRemarks((String)trigger.getJobDataMap().get("remarks"));	
                a = scheduler.getTriggerState(trigger.getKey());	
                if (!Trigger.TriggerState.NORMAL.equals((Object)a)) break block11;	
                a2.setStatus(STATUS_NORMAL);	
            }	
            if (!Trigger.TriggerState.NONE.equals((Object)a)) break block12;	
            a2.setStatus(STATUS_DELETE);	
        }	
        try {	
            if (Trigger.TriggerState.PAUSED.equals((Object)a)) {	
                a2.setStatus(STATUS_PAUSED);	
            } else if (Trigger.TriggerState.COMPLETE.equals((Object)a)) {	
                a2.setStatus(STATUS_COMPLETE);	
            } else if (Trigger.TriggerState.ERROR.equals((Object)a)) {	
                a2.setStatus(STATUS_ERROR);	
            } else if (Trigger.TriggerState.BLOCKED.equals((Object)a)) {	
                a2.setStatus(STATUS_BLOCKED);	
            }	
        }	
        catch (SchedulerException a3) {	
            a2.setStatus(STATUS_DELETE);	
        }	
        JobEntity jobEntity = a2;	
        jobEntity.setPrevFireTime(trigger.getPreviousFireTime());	
        jobEntity.setNextFireTime(trigger.getNextFireTime());	
        return a2;	
    }	
	
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")	
    public Date getPrevFireTime() {	
        return this.prevFireTime;	
    }	
	
    public String getJobName() {	
        return this.jobName;	
    }	
	
    public String getInstanceName() {	
        return this.instanceName;	
    }	
	
    public void setConcurrent(String concurrent) {	
        this.concurrent = concurrent;	
    }	
	
    public String getJobGroup() {	
        return this.jobGroup;	
    }	
	
    public void setCronExpression(String cronExpression) {	
        this.cronExpression = cronExpression;	
    }	
	
    @Length(min=0, max=255, message="Cron\u6267\u884c\u8868\u8fbe\u5f0f\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 255 \u4e2a\u5b57\u7b26")	
    public String getCronExpression() {	
        return this.cronExpression;	
    }	
	
    public void setNextFireTime(Date nextFireTime) {	
        this.nextFireTime = nextFireTime;	
    }	
	
    public void setPrevFireTime(Date prevFireTime) {	
        this.prevFireTime = prevFireTime;	
    }	
	
    public Integer getMisfireInstruction() {	
        return this.misfireInstruction;	
    }	
	
    @Length(min=0, max=1, message="\u662f\u5426\u5e76\u53d1\u6267\u884c\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 1 \u4e2a\u5b57\u7b26")	
    public String getConcurrent() {	
        return this.concurrent;	
    }	
	
    public void setJobGroup(String jobGroup) {	
        this.jobGroup = jobGroup;	
    }	
	
    @Length(min=0, max=1000, message="\u8c03\u7528\u76ee\u6807\u5b57\u7b26\u4e32\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 1000 \u4e2a\u5b57\u7b26")	
    public String getInvokeTarget() {	
        return this.invokeTarget;	
    }	
	
    public void setInstanceName(String instanceName) {	
        this.instanceName = instanceName;	
    }	
	
    public JobEntity() {	
    }	
	
    @Length(min=0, max=100, message="\u4efb\u52a1\u63cf\u8ff0\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 100 \u4e2a\u5b57\u7b26")	
    public String getDescription() {	
        return this.description;	
    }	
	
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")	
    public Date getNextFireTime() {	
        return this.nextFireTime;	
    }	
	
    public void setDescription(String description) {	
        this.description = description;	
    }	
	
    public void setInvokeTarget(String invokeTarget) {	
        this.invokeTarget = invokeTarget;	
    }	
}	
	
