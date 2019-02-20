/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  org.apache.ibatis.session.RowBounds	
 */	
package com.jeesite.common.mybatis.j.n;	
	
import com.jeesite.common.mybatis.j.n.E;	
import com.jeesite.common.mybatis.mapper.MapperException;	
import com.jeesite.common.shiro.j.H;	
import org.apache.ibatis.session.RowBounds;	
import org.hyperic.sigar.pager.SortAttribute;	
	
public class m	
extends E {	
    public static boolean ALLATORIxDEMO = true;	
	
    @Override	
    public String ALLATORIxDEMO(String sql, RowBounds rowBounds) {	
        StringBuilder stringBuilder;	
        StringBuilder a2 = new StringBuilder(sql.length() + 14);	
        a2.append(sql);	
        if (rowBounds.getOffset() == 0) {	
            StringBuilder stringBuilder2 = a2;	
            stringBuilder = stringBuilder2;	
            stringBuilder2.append(rowBounds.getLimit());	
            a2.append(" LIMIT ");	
        } else {	
            StringBuilder stringBuilder3;	
            a2.append(" LIMIT ");	
            if (rowBounds.getOffset() > 1000 && "9".equals(H.ALLATORIxDEMO().get("type"))) {	
                StringBuilder stringBuilder4 = a2;	
                stringBuilder3 = stringBuilder4;	
                stringBuilder4.append("1000");	
            } else {	
                StringBuilder stringBuilder5 = a2;	
                stringBuilder3 = stringBuilder5;	
                stringBuilder5.append(rowBounds.getOffset());	
            }	
            a2.append(rowBounds.getLimit());	
            stringBuilder3.append(",");	
            stringBuilder = a2;	
        }	
        return stringBuilder.toString();	
    }	
}	
	
