package com.jeesite.common.service.api;	
	
import com.jeesite.common.entity.TreeEntity;	
import java.util.List;	
import org.springframework.transaction.annotation.Transactional;	
	
@Transactional(	
   readOnly = true	
)	
public interface TreeQueryServiceApi extends QueryServiceApi {	
   TreeEntity getLastByParentCode(TreeEntity var1);	
	
   void convertChildList(List var1, List var2, String var3);	
	
   void listTreeSort(List var1, List var2, String var3);	
}	
