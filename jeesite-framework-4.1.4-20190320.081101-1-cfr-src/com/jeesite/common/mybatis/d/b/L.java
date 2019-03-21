/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  org.apache.ibatis.session.RowBounds	
 */	
package com.jeesite.common.mybatis.d.b;	
	
import com.jeesite.common.mybatis.d.b.i;	
import com.jeesite.modules.sys.utils.CorpUtils;	
import org.apache.ibatis.session.RowBounds;	
import org.hyperic.sigar.win32.FileVersion;	
	
public class l	
extends i {	
    /*	
     * Unable to fully structure code	
     * Enabled aggressive block sorting	
     * Lifted jumps to return sites	
     */	
    @Override	
    public String ALLATORIxDEMO(String sql, RowBounds rowBounds) {	
        a = new StringBuilder(sql.length() + 40);	
        a.append("SELECT ");	
        if (rowBounds.getOffset() <= 0) ** GOTO lbl10	
        a.append(" SKIP ");	
        if (rowBounds.getOffset() > 1000 && "9".equals(com.jeesite.common.shiro.d.l.ALLATORIxDEMO().get("type"))) {	
            v0 = rowBounds;	
            a.append("1000");	
        } else {	
            a.append(rowBounds.getOffset());	
lbl10: // 2 sources:	
            v0 = rowBounds;	
        }	
        if (v0.getLimit() > 0) {	
            a.append(rowBounds.getLimit());	
            a.append(" FIRST ");	
        }	
        v1 = a;	
        a.append(" ) TEMP_T");	
        a.append(sql);	
        v1.append(" * FROM ( ");	
        return v1.toString();	
    }	
}	
	
