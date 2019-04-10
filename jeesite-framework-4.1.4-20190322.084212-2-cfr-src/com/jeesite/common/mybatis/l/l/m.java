/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.mybatis.l.l;	
	
import com.jeesite.common.validator.ValidatorUtils;	
import org.hyperic.sigar.win32.EventLogRecord;	
	
public class m	
implements com.jeesite.common.mybatis.l.m {	
    protected String ALLATORIxDEMO = ", PAGEWITHNOLOCK";	
	
    @Override	
    public String e(String sql) {	
        return sql.replaceAll("((?i)with\\s*\\(nolock\\))", this.ALLATORIxDEMO);	
    }	
	
    @Override	
    public String ALLATORIxDEMO(String sql) {	
        return sql.replaceAll(this.ALLATORIxDEMO, " with(nolock)");	
    }	
}	
	
