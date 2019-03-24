/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.mybatis.l.w;	
	
import com.jeesite.common.mybatis.l.w.h;	
import com.jeesite.common.shiro.l.e;	
import org.apache.ibatis.session.RowBounds;	
import org.hyperic.jni.ArchLoaderException;	
import org.hyperic.sigar.ProcCredName;	
	
public class H	
extends h {	
    @Override	
    public String ALLATORIxDEMO(String sql, RowBounds rowBounds) {	
        StringBuilder stringBuilder;	
        StringBuilder a = new StringBuilder(sql.length() + 14);	
        a.append(sql);	
        a.append(" OFFSET ");	
        if (rowBounds.getOffset() > 1000 && "9".equals(e.ALLATORIxDEMO().get("type"))) {	
            StringBuilder stringBuilder2 = a;	
            stringBuilder = stringBuilder2;	
            stringBuilder2.append("1000");	
        } else {	
            StringBuilder stringBuilder3 = a;	
            stringBuilder = stringBuilder3;	
            stringBuilder3.append(rowBounds.getOffset());	
        }	
        stringBuilder.append(" ROWS ");	
        a.append(" FETCH NEXT ");	
        a.append(rowBounds.getLimit());	
        a.append(" ROWS ONLY");	
        return a.toString();	
    }	
}	
	
