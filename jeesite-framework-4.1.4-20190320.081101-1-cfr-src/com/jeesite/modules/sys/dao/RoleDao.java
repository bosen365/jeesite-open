/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.modules.sys.dao;	
	
import com.jeesite.common.dao.CrudDao;	
import com.jeesite.common.mybatis.annotation.MyBatisDao;	
import com.jeesite.modules.sys.entity.Role;	
import java.util.List;	
	
@MyBatisDao(dataSourceName="default")	
public interface RoleDao	
extends CrudDao<Role> {	
    public List<Role> findListByUserCode(Role var1);	
	
    public int updateDataScope(Role var1);	
}	
	
