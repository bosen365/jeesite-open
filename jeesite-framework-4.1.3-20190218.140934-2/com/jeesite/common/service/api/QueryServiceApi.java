package com.jeesite.common.service.api;	
	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.entity.Page;	
import java.util.List;	
	
public interface QueryServiceApi extends BaseServiceApi {	
   Page findPage(DataEntity var1);	
	
   DataEntity get(Class[] var1, Object[] var2, boolean var3);	
	
   /** @deprecated */	
   Page findPage(Page var1, DataEntity var2);	
	
   void genId(DataEntity var1, String var2);	
	
   DataEntity get(DataEntity var1);	
	
   DataEntity get(String var1, boolean var2);	
	
   void addDataScopeFilter(DataEntity var1, String var2);	
	
   void genIdAndValid(DataEntity var1, String var2);	
	
   void addDataScopeFilter(DataEntity var1);	
	
   DataEntity get(String var1);	
	
   List findList(DataEntity var1);	
	
   void genIdAndValid(DataEntity var1, String var2, String var3);	
	
   long findCount(DataEntity var1);	
}	
