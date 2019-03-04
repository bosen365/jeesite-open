package com.jeesite.modules.job.entity;	
	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.query.QueryType;	
import org.hibernate.validator.constraints.Length;	
import org.hibernate.validator.constraints.NotBlank;	
	
@Table(	
   name = "${_prefix}sys_job_log",	
   alias = "a",	
   columns = {@Column(	
   name = "id",	
   attrName = "id",	
   label = "编号",	
   isPK = true	
), @Column(	
   name = "job_name",	
   attrName = "jobName",	
   label = "任务名称",	
   queryType = QueryType.LIKE	
), @Column(	
   name = "job_group",	
   attrName = "jobGroup",	
   label = "任务组名"	
), @Column(	
   name = "job_type",	
   attrName = "jobType",	
   label = "日志类型"	
), @Column(	
   name = "job_event",	
   attrName = "jobEvent",	
   label = "日志事件"	
), @Column(	
   name = "job_message",	
   attrName = "jobMessage",	
   label = "日志信息",	
   queryType = QueryType.LIKE	
), @Column(	
   name = "is_exception",	
   attrName = "isException",	
   label = "是否异常"	
), @Column(	
   name = "exception_info",	
   attrName = "exceptionInfo",	
   label = "异常信息",	
   isQuery = false	
), @Column(	
   name = "create_date",	
   attrName = "createDate",	
   label = "创建时间",	
   isUpdate = false,	
   isQuery = false	
)},	
   orderBy = "a.create_date DESC"	
)	
public class JobLog extends DataEntity {	
   private String jobName;	
   private String jobMessage;	
   private static final long serialVersionUID = 1L;	
   public static final String JOB_TYPE_JOB = "job";	
   private String jobEvent;	
   private String exceptionInfo;	
   private String jobType;	
   public static final String JOB_TYPE_SCHEDULER = "scheduler";	
   private String jobGroup;	
   private String isException;	
   public static final String JOB_TYPE_TRIGGER = "trigger";	
	
   public void setJobMessage(String jobMessage) {	
      this.jobMessage = jobMessage;	
   }	
	
   @Length(	
      min = 0,	
      max = 500,	
      message = "日志信息长度不能超过 500 个字符"	
   )	
   public String getJobMessage() {	
      return this.jobMessage;	
   }	
	
   public void setExceptionInfo(String exceptionInfo) {	
      this.exceptionInfo = exceptionInfo;	
   }	
	
   public void setJobType(String jobType) {	
      this.jobType = jobType;	
   }	
	
   public void preInsert() {	
      super.preInsert();	
      if (this.isException == null) {	
         this.isException = "0";	
      }	
	
   }	
	
   @NotBlank(	
      message = "任务名称不能为空"	
   )	
   @Length(	
      min = 0,	
      max = 64,	
      message = "任务名称长度不能超过 64 个字符"	
   )	
   public String getJobName() {	
      return this.jobName;	
   }	
	
   public String getIsException() {	
      return this.isException;	
   }	
	
   public JobLog(String id) {	
      super(id);	
   }	
	
   @Length(	
      min = 0,	
      max = 200,	
      message = "日志事件长度不能超过 200 个字符"	
   )	
   public String getJobEvent() {	
      return this.jobEvent;	
   }	
	
   @Length(	
      min = 0,	
      max = 50,	
      message = "日志类型长度不能超过 50 个字符"	
   )	
   public String getJobType() {	
      return this.jobType;	
   }	
	
   @NotBlank(	
      message = "任务组名不能为空"	
   )	
   @Length(	
      min = 0,	
      max = 64,	
      message = "任务组名长度不能超过 64 个字符"	
   )	
   public String getJobGroup() {	
      return this.jobGroup;	
   }	
	
   public void setJobGroup(String jobGroup) {	
      this.jobGroup = jobGroup;	
   }	
	
   public String getExceptionInfo() {	
      return this.exceptionInfo;	
   }	
	
   public void setJobName(String jobName) {	
      this.jobName = jobName;	
   }	
	
   public void setJobEvent(String jobEvent) {	
      this.jobEvent = jobEvent;	
   }	
	
   public JobLog() {	
      this((String)null);	
   }	
	
   public void setIsException(String isException) {	
      this.isException = isException;	
   }	
}	
