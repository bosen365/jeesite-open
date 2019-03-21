/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.common.mybatis.d.d;	
	
import com.jeesite.common.mybatis.d.i;	
import com.jeesite.common.validator.ValidatorUtils;	
import org.hyperic.sigar.DirUsage;	
	
public class c	
implements i {	
    protected String ALLATORIxDEMO = ", PAGEWITHNOLOCK";	
	
    @Override	
    public String ALLATORIxDEMO(String sql) {	
        return sql.replaceAll("\s*(\w*?)_PAGEWITHNOLOCK", " $1 WITH(NOLOCK)");	
    }	
	
    @Override	
    public String d(String sql) {	
        return sql.replaceAll("((?i)\s*(\w+)\s*with\s*\(nolock\))", " $2_PAGEWITHNOLOCK");	
    }	
}	
	
