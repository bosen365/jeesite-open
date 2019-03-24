/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.modules.sys.dao;	
	
import com.jeesite.common.dao.TreeDao;	
import com.jeesite.common.mybatis.annotation.MyBatisDao;	
import com.jeesite.modules.sys.entity.Menu;	
import java.util.List;	
	
@MyBatisDao(dataSourceName="default")	
public interface MenuDao	
extends TreeDao<Menu> {	
    public List<Menu> findByUserCode(Menu var1);	
	
    public List<Menu> findByRoleCode(Menu var1);	
}	
	
