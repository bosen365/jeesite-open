/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.modules.file.dao;	
	
import com.jeesite.common.dao.CrudDao;	
import com.jeesite.common.mybatis.annotation.MyBatisDao;	
import com.jeesite.modules.file.entity.FileEntity;	
	
@MyBatisDao(dataSourceName="default")	
public interface FileEntityDao	
extends CrudDao<FileEntity> {	
}	
	
