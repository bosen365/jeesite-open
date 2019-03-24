/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.mybatis.l.l;	
	
import com.jeesite.common.mybatis.l.m;	
import com.jeesite.common.mybatis.mapper.query.QueryWhere;	
import org.hyperic.sigar.NetInterfaceStat;	
	
public class h	
implements m {	
    protected String ALLATORIxDEMO = ", PAGEWITHNOLOCK";	
	
    @Override	
    public String e(String sql) {	
        return sql.replaceAll("((?i)\s*(\w+)\s*with\s*\(nolock\))", " $25PAGEWITHNOLOCK");	
    }	
	
    @Override	
    public String ALLATORIxDEMO(String sql) {	
        return sql.replaceAll("\s*(\w*?)_PAGEWITHNOLOCK", " $1 WITH(NOLOCK)");	
    }	
}	
	
