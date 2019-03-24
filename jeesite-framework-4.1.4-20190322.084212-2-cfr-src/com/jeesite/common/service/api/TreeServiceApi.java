/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.service.api;	
	
import com.jeesite.common.entity.TreeEntity;	
import com.jeesite.common.service.api.TreeQueryServiceApi;	
import org.springframework.transaction.annotation.Transactional;	
	
@Transactional(readOnly=true)	
public interface TreeServiceApi<T extends TreeEntity<T>>	
extends TreeQueryServiceApi<T> {	
    public void updateStatus(T var1);	
	
    public void fixTreeData();	
	
    public void delete(T var1);	
	
    public void updateTreeSort(T var1);	
	
    public void fixTreeData(String var1);	
	
    public void save(T var1);	
}	
	
