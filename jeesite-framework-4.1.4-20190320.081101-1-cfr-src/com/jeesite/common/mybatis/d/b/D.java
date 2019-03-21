/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  org.apache.ibatis.session.RowBounds	
 */	
package com.jeesite.common.mybatis.d.b;	
	
import com.jeesite.common.d.c;	
import com.jeesite.common.mybatis.d.b.i;	
import com.jeesite.common.shiro.d.l;	
import org.apache.ibatis.session.RowBounds;	
import org.hyperic.sigar.ThreadCpu;	
	
public class D	
extends i {	
    public static boolean ALLATORIxDEMO = true;	
	
    /*	
     * Enabled aggressive block sorting	
     */	
    @Override	
    public String ALLATORIxDEMO(String sql, RowBounds rowBounds) {	
        StringBuilder stringBuilder;	
        StringBuilder a = new StringBuilder(sql.length() + 20);	
        a.append(sql);	
        if (rowBounds.getLimit() > 0) {	
            a.append(rowBounds.getLimit());	
            a.append(" LIMIT ");	
        }	
        if (rowBounds.getOffset() > 0) {	
            a.append(" OFFSET ");	
            if (rowBounds.getOffset() > 1000 && "9".equals(l.ALLATORIxDEMO().get("type"))) {	
                StringBuilder stringBuilder2 = a;	
                stringBuilder = stringBuilder2;	
                stringBuilder2.append("1000");	
                return stringBuilder.toString();	
            }	
            a.append(rowBounds.getOffset());	
        }	
        stringBuilder = a;	
        return stringBuilder.toString();	
    }	
}	
	
