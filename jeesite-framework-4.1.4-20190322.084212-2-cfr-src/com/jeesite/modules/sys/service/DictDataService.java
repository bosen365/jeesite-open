/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.modules.sys.service;	
	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.entity.TreeEntity;	
import com.jeesite.common.service.api.TreeServiceApi;	
import com.jeesite.modules.sys.entity.DictData;	
import java.util.List;	
	
public interface DictDataService	
extends TreeServiceApi<DictData> {	
    @Override	
    public List<DictData> findList(DictData var1);	
	
    @Override	
    public void save(DictData var1);	
	
    public void deleteByDictType(String var1);	
	
    @Override	
    public DictData get(DictData var1);	
	
    public void updateDictTypeByDictType(String var1, String var2);	
	
    @Override	
    public void updateStatus(DictData var1);	
	
    @Override	
    public void delete(DictData var1);	
}	
	
