/*	
 * Decompiled with CFR 0.139.	
 */	
package com.jeesite.common.service.api;	
	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.service.api.QueryServiceApi;	
	
public interface CrudServiceApi<T extends DataEntity<?>>	
extends QueryServiceApi<T> {	
    public void update(T var1);	
	
    public void save(T var1);	
	
    public void updateStatus(T var1);	
	
    public void insert(T var1);	
	
    public void delete(T var1);	
}	
	
