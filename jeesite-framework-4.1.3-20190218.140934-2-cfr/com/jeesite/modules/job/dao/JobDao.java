/*	
 * Decompiled with CFR 0.139.	
 */	
package com.jeesite.modules.job.dao;	
	
import com.jeesite.common.dao.CrudDao;	
import com.jeesite.common.mybatis.annotation.MyBatisDao;	
import com.jeesite.modules.job.entity.JobEntity;	
	
@MyBatisDao(dataSourceName="default")	
public interface JobDao	
extends CrudDao<JobEntity> {	
}	
	
