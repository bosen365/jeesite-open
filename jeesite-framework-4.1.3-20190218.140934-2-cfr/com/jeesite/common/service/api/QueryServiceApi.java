/*	
 * Decompiled with CFR 0.139.	
 */	
package com.jeesite.common.service.api;	
	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.service.api.BaseServiceApi;	
import java.util.List;	
	
public interface QueryServiceApi<T extends DataEntity<?>>	
extends BaseServiceApi {	
    public Page<T> findPage(T var1);	
	
    public T get(Class<?>[] var1, Object[] var2, boolean var3);	
	
    public Page<T> findPage(Page<T> var1, T var2);	
	
    public void genId(T var1, String var2);	
	
    public T get(T var1);	
	
    public T get(String var1, boolean var2);	
	
    public void addDataScopeFilter(T var1, String var2);	
	
    public void genIdAndValid(T var1, String var2);	
	
    public void addDataScopeFilter(T var1);	
	
    public T get(String var1);	
	
    public List<T> findList(T var1);	
	
    public void genIdAndValid(T var1, String var2, String var3);	
	
    public long findCount(T var1);	
}	
	
