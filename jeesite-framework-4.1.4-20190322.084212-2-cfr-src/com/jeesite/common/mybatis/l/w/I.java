/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.mybatis.l.w;	
	
import com.jeesite.common.entity.Extend;	
import com.jeesite.common.mybatis.l.w.m;	
import com.jeesite.common.shiro.l.e;	
import com.jeesite.common.web.returnjson.ReturnJsonSerializer;	
import org.apache.ibatis.session.RowBounds;	
	
public class I	
extends m {	
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
            a.append(" LIMIT ");	
            a.append(rowBounds.getLimit());	
        }	
        if (rowBounds.getOffset() > 0) {	
            a.append(" OFFSET ");	
            if (rowBounds.getOffset() > 1000 && "9".equals(e.ALLATORIxDEMO().get("type"))) {	
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
	
