/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.modules.job.dao;	
	
import com.jeesite.common.dao.CrudDao;	
import com.jeesite.common.mybatis.annotation.MyBatisDao;	
import com.jeesite.modules.job.entity.JobLog;	
	
@MyBatisDao(dataSourceName="default")	
public interface JobLogDao	
extends CrudDao<JobLog> {	
}	
	
