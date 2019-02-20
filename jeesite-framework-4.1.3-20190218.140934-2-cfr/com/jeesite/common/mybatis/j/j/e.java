/*	
 * Decompiled with CFR 0.139.	
 */	
package com.jeesite.common.mybatis.j.j;	
	
import com.jeesite.modules.file.entity.FileUploadParams;	
import org.hyperic.sigar.ProcState;	
	
public class E	
implements com.jeesite.common.mybatis.j.E {	
    protected String ALLATORIxDEMO = ", PAGEWITHNOLOCK";	
	
    @Override	
    public String h(String sql) {	
        return sql.replaceAll("((?i)with\s*\(nolock\))", this.ALLATORIxDEMO);	
    }	
	
    @Override	
    public String ALLATORIxDEMO(String sql) {	
        return sql.replaceAll(this.ALLATORIxDEMO, " with(nolock)");	
    }	
}	
	
