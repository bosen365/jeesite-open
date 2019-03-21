/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.modules.sys.service;	
	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.entity.TreeEntity;	
import com.jeesite.common.service.api.TreeServiceApi;	
import com.jeesite.modules.sys.entity.Menu;	
import java.util.List;	
	
public interface MenuService	
extends TreeServiceApi<Menu> {	
    public void disableByModuleCodes(String var1);	
	
    @Override	
    public void updateTreeSort(Menu var1);	
	
    @Override	
    public List<Menu> findList(Menu var1);	
	
    public void enableByModuleCodes(String var1);	
	
    public String getMenuNamePath(String var1, String var2);	
	
    @Override	
    public Menu get(Menu var1);	
	
    @Override	
    public void save(Menu var1);	
	
    @Override	
    public void delete(Menu var1);	
	
    public List<Menu> findByRoleCode(Menu var1);	
	
    public List<Menu> findByUserCode(Menu var1);	
}	
	
