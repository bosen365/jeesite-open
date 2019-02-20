package com.jeesite.modules.job.service;	
	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.service.CrudService;	
import com.jeesite.modules.job.entity.JobLog;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;	
import org.springframework.stereotype.Service;	
import org.springframework.transaction.annotation.Transactional;	
	
@Service	
@Transactional(	
   readOnly = true	
)	
@ConditionalOnProperty(	
   name = {"job.enabled"},	
   havingValue = "true",	
   matchIfMissing = true	
)	
public class JobLogService extends CrudService {	
   public JobLog get(JobLog jobLog) {	
      return (JobLog)super.get(jobLog);	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void delete(JobLog jobLog) {	
      super.delete(jobLog);	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void save(JobLog jobLog) {	
      super.save(jobLog);	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void updateStatus(JobLog jobLog) {	
      super.updateStatus(jobLog);	
   }	
	
   public Page findPage(JobLog jobLog) {	
      return super.findPage(jobLog);	
   }	
}	
