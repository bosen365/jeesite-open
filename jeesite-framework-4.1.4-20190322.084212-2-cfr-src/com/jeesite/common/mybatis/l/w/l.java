/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.mybatis.l.w;	
	
import com.jeesite.common.mybatis.l.w.m;	
import com.jeesite.common.shiro.l.e;	
import com.jeesite.modules.msg.entity.content.BaseMsgContent;	
import org.apache.ibatis.session.RowBounds;	
import org.hyperic.sigar.pager.SortAttribute;	
	
public class l	
extends m {	
    public static boolean ALLATORIxDEMO = true;	
	
    @Override	
    public String ALLATORIxDEMO(String sql, RowBounds rowBounds) {	
        StringBuilder stringBuilder;	
        StringBuilder a = new StringBuilder(sql.length() + 14);	
        a.append(sql);	
        if (rowBounds.getOffset() == 0) {	
            StringBuilder stringBuilder2 = a;	
            a.append(" LIMIT ");	
            stringBuilder = stringBuilder2;	
            stringBuilder2.append(rowBounds.getLimit());	
        } else {	
            StringBuilder stringBuilder3;	
            a.append(" LIMIT ");	
            if (rowBounds.getOffset() > 1000 && "9".equals(e.ALLATORIxDEMO().get("type"))) {	
                StringBuilder stringBuilder4 = a;	
                stringBuilder3 = stringBuilder4;	
                stringBuilder4.append("1000");	
            } else {	
                StringBuilder stringBuilder5 = a;	
                stringBuilder3 = stringBuilder5;	
                stringBuilder5.append(rowBounds.getOffset());	
            }	
            stringBuilder3.append(",");	
            a.append(rowBounds.getLimit());	
            stringBuilder = a;	
        }	
        return stringBuilder.toString();	
    }	
}	
	
