/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.common.mybatis.d.d;	
	
import org.hyperic.sigar.DirUsage;	
import org.hyperic.sigar.pager.SortAttribute;	
	
public class i	
implements com.jeesite.common.mybatis.d.i {	
    protected String ALLATORIxDEMO = ", PAGEWITHNOLOCK";	
	
    @Override	
    public String ALLATORIxDEMO(String sql) {	
        return sql.replaceAll(this.ALLATORIxDEMO, " with(nolock)");	
    }	
	
    @Override	
    public String d(String sql) {	
        return sql.replaceAll("((?i)with\s*\(nolock\))", this.ALLATORIxDEMO);	
    }	
}	
	
