/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.mybatis.l.w;	
	
import com.jeesite.common.j2cache.autoconfigure.J2CacheSpringRedisAutoConfiguration;	
import com.jeesite.common.mybatis.l.w.m;	
import org.apache.ibatis.session.RowBounds;	
import org.hyperic.sigar.FileWatcher;	
	
public class e	
extends m {	
    /*	
     * Unable to fully structure code	
     * Enabled aggressive block sorting	
     * Lifted jumps to return sites	
     */	
    @Override	
    public String ALLATORIxDEMO(String sql, RowBounds rowBounds) {	
        a = new StringBuilder(sql.length() + 40);	
        a.append("SELECT ");	
        if (rowBounds.getOffset() <= 0) ** GOTO lbl14	
        a.append(" SKIP ");	
        if (rowBounds.getOffset() > 1000 && "9".equals(com.jeesite.common.shiro.l.e.ALLATORIxDEMO().get("type"))) {	
            v0 = rowBounds;	
            a.append("1000");	
        } else {	
            a.append(rowBounds.getOffset());	
lbl14: // 2 sources:	
            v0 = rowBounds;	
        }	
        if (v0.getLimit() > 0) {	
            a.append(" FIRST ");	
            a.append(rowBounds.getLimit());	
        }	
        v1 = a;	
        v1.append(" * FROM ( ");	
        a.append(sql);	
        a.append(" ) TEMP_T");	
        return v1.toString();	
    }	
}	
	
