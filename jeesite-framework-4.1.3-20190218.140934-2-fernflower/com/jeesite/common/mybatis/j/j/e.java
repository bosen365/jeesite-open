package com.jeesite.common.mybatis.j.j;	
	
import com.jeesite.modules.file.entity.FileUploadParams;	
import org.hyperic.sigar.ProcState;	
	
public class E implements com.jeesite.common.mybatis.j.E {	
   protected String ALLATORIxDEMO = ", PAGEWITHNOLOCK";	
	
   public String h(String sql) {	
      return sql.replaceAll("((?i)with\s*\(nolock\))", this.ALLATORIxDEMO);	
   }	
	
   public String ALLATORIxDEMO(String sql) {	
      return sql.replaceAll(this.ALLATORIxDEMO, " with(nolock)");	
   }	
}	
u001d\u00155\u000eC!A\u0013\u0006\u0011\u0006\u001e\u0002!@T"), NetServices.ALLATORIxDEMO ("\u0006^\u0014%v;a?q3r2h5j5e1"));	
   }	
}	
