/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.modules.job.entity;	
	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.query.QueryType;	
import javax.validation.constraints.NotBlank;	
import org.hibernate.validator.constraints.Length;	
	
@Table(name="${_prefix}sys_job_log", alias="a", columns={@Column(name="id", attrName="id", label="\u7f16\u53f7", isPK=true), @Column(name="job_name", attrName="jobName", label="\u4efb\u52a1\u540d\u79f0", queryType=QueryType.LIKE), @Column(name="job_group", attrName="jobGroup", label="\u4efb\u52a1\u7ec4\u540d"), @Column(name="job_type", attrName="jobType", label="\u65e5\u5fd7\u7c7b\u578b"), @Column(name="job_event", attrName="jobEvent", label="\u65e5\u5fd7\u4e8b\u4ef6"), @Column(name="job_message", attrName="jobMessage", label="\u65e5\u5fd7\u4fe1\u606f", queryType=QueryType.LIKE), @Column(name="is_exception", attrName="isException", label="\u662f\u5426\u5f02\u5e38"), @Column(name="exception_info", attrName="exceptionInfo", label="\u5f02\u5e38\u4fe1\u606f", isQuery=false), @Column(name="create_date", attrName="createDate", label="\u521b\u5efa\u65f6\u95f4", isUpdate=false, isQuery=false)}, orderBy="a.create_date DESC")	
public class JobLog	
extends DataEntity<JobLog> {	
    private String jobMessage;	
    private static final long serialVersionUID = 1L;	
    private String jobType;	
    private String jobName;	
    public static final String JOB_TYPE_JOB = "job";	
    private String isException;	
    private String jobEvent;	
    private String jobGroup;	
    private String exceptionInfo;	
    public static final String JOB_TYPE_SCHEDULER = "scheduler";	
    public static final String JOB_TYPE_TRIGGER = "trigger";	
	
    public JobLog(String id) {	
        super(id);	
    }	
	
    public void setJobType(String jobType) {	
        this.jobType = jobType;	
    }	
	
    @Length(min=0, max=50, message="\u65e5\u5fd7\u7c7b\u578b\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 50 \u4e2a\u5b57\u7b26")	
    public String getJobType() {	
        return this.jobType;	
    }	
	
    public String getExceptionInfo() {	
        return this.exceptionInfo;	
    }	
	
    public void setJobName(String jobName) {	
        this.jobName = jobName;	
    }	
	
    @NotBlank(message="\u4efb\u52a1\u7ec4\u540d\u4e0d\u80fd\u4e3a\u7a7a")	
    @Length(min=0, max=64, message="\u4efb\u52a1\u7ec4\u540d\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 64 \u4e2a\u5b57\u7b26")	
    public String getJobGroup() {	
        return this.jobGroup;	
    }	
	
    @Length(min=0, max=200, message="\u65e5\u5fd7\u4e8b\u4ef6\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 200 \u4e2a\u5b57\u7b26")	
    public String getJobEvent() {	
        return this.jobEvent;	
    }	
	
    public void setIsException(String isException) {	
        this.isException = isException;	
    }	
	
    public void setJobEvent(String jobEvent) {	
        this.jobEvent = jobEvent;	
    }	
	
    public void setExceptionInfo(String exceptionInfo) {	
        this.exceptionInfo = exceptionInfo;	
    }	
	
    public void setJobGroup(String jobGroup) {	
        this.jobGroup = jobGroup;	
    }	
	
    public JobLog() {	
        this(null);	
    }	
	
    public void setJobMessage(String jobMessage) {	
        this.jobMessage = jobMessage;	
    }	
	
    @NotBlank(message="\u4efb\u52a1\u540d\u79f0\u4e0d\u80fd\u4e3a\u7a7a")	
    @Length(min=0, max=64, message="\u4efb\u52a1\u540d\u79f0\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 64 \u4e2a\u5b57\u7b26")	
    public String getJobName() {	
        return this.jobName;	
    }	
	
    public String getIsException() {	
        return this.isException;	
    }	
	
    @Override	
    public void preInsert() {	
        JobLog jobLog = this;	
        super.preInsert();	
        if (jobLog.isException == null) {	
            this.isException = "0";	
        }	
    }	
	
    @Length(min=0, max=500, message="\u65e5\u5fd7\u4fe1\u606f\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 500 \u4e2a\u5b57\u7b26")	
    public String getJobMessage() {	
        return this.jobMessage;	
    }	
}	
	
