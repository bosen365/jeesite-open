/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.modules.job.service;	
	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.service.CrudService;	
import com.jeesite.modules.job.dao.JobLogDao;	
import com.jeesite.modules.job.entity.JobLog;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;	
import org.springframework.stereotype.Service;	
import org.springframework.transaction.annotation.Transactional;	
	
@Service	
@Transactional(readOnly=true)	
@ConditionalOnProperty(name={"job.enabled"}, havingValue="true", matchIfMissing=true)	
public class JobLogService	
extends CrudService<JobLogDao, JobLog> {	
    @Override	
    public Page<JobLog> findPage(JobLog jobLog) {	
        return super.findPage(jobLog);	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void updateStatus(JobLog jobLog) {	
        super.updateStatus(jobLog);	
    }	
	
    @Override	
    public JobLog get(JobLog jobLog) {	
        return super.get(jobLog);	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void delete(JobLog jobLog) {	
        super.delete(jobLog);	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void save(JobLog jobLog) {	
        super.save(jobLog);	
    }	
}	
	
