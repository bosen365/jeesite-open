/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.common.mybatis.d.b;	
	
import com.jeesite.common.mybatis.d.b.c;	
import com.jeesite.common.shiro.d.l;	
import com.jeesite.modules.sys.utils.ModuleUtils;	
import org.apache.ibatis.session.RowBounds;	
import org.hyperic.sigar.NetInterfaceStat;	
	
public class C	
extends c {	
    @Override	
    public String ALLATORIxDEMO(String sql, RowBounds rowBounds) {	
        StringBuilder stringBuilder;	
        StringBuilder a = new StringBuilder(sql.length() + 14);	
        a.append(" OFFSET ");	
        a.append(sql);	
        if (rowBounds.getOffset() > 1000 && "9".equals(l.ALLATORIxDEMO().get("type"))) {	
            StringBuilder stringBuilder2 = a;	
            stringBuilder = stringBuilder2;	
            stringBuilder2.append("1000");	
        } else {	
            StringBuilder stringBuilder3 = a;	
            stringBuilder = stringBuilder3;	
            stringBuilder3.append(rowBounds.getOffset());	
        }	
        a.append(" ROWS ONLY");	
        a.append(rowBounds.getLimit());	
        a.append(" FETCH NEXT ");	
        stringBuilder.append(" ROWS ");	
        return a.toString();	
    }	
}	
	
