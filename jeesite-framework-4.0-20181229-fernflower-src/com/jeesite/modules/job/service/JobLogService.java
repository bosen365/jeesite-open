package com.jeesite.modules.job.service;	
	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.service.CrudService;	
import com.jeesite.modules.job.entity.JobLog;	
import org.springframework.stereotype.Service;	
import org.springframework.transaction.annotation.Transactional;	
	
@Service	
@Transactional(	
   readOnly = true	
)	
public class JobLogService extends CrudService {	
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
	
   public JobLog get(JobLog jobLog) {	
      return (JobLog)super.get(jobLog);	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void delete(JobLog jobLog) {	
      super.delete(jobLog);	
   }	
	
   public Page findPage(Page page, JobLog jobLog) {	
      return super.findPage(page, jobLog);	
   }	
}	
