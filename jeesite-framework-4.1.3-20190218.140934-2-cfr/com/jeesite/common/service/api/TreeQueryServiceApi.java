/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  org.springframework.transaction.annotation.Transactional	
 */	
package com.jeesite.common.service.api;	
	
import com.jeesite.common.entity.TreeEntity;	
import com.jeesite.common.service.api.QueryServiceApi;	
import java.util.List;	
import org.springframework.transaction.annotation.Transactional;	
	
@Transactional(readOnly=true)	
public interface TreeQueryServiceApi<T extends TreeEntity<T>>	
extends QueryServiceApi<T> {	
    public T getLastByParentCode(T var1);	
	
    public void convertChildList(List<T> var1, List<T> var2, String var3);	
	
    public void listTreeSort(List<T> var1, List<T> var2, String var3);	
}	
	
