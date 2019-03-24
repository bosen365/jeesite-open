/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.mybatis.l.w;	
	
import com.jeesite.common.entity.Extend;	
import com.jeesite.common.mybatis.l.w.m;	
import com.jeesite.common.shiro.l.e;	
import org.apache.ibatis.session.RowBounds;	
import org.hyperic.sigar.FileWatcher;	
	
public class j	
extends m {	
    /*	
     * Enabled aggressive block sorting	
     */	
    @Override	
    public String ALLATORIxDEMO(String sql, RowBounds rowBounds) {	
        StringBuilder stringBuilder;	
        RowBounds rowBounds2 = rowBounds;	
        int a = rowBounds2.getOffset();	
        int a2 = rowBounds2.getOffset() + rowBounds.getLimit();	
        StringBuilder a3 = new StringBuilder(sql.length() + 120);	
        if (a > 0) {	
            a3.append("SELECT * FROM ( ");	
        }	
        if (a2 > 0) {	
            a3.append(" SELECT TMP_PAGE.*, ROWNUM ROW_ID FROM ( ");	
        }	
        a3.append(sql);	
        if (a2 > 0) {	
            a3.append(" ) TMP_PAGE WHERE ROWNUM <= ");	
            a3.append(a2);	
        }	
        if (a > 0) {	
            a3.append(" ) WHERE ROW_ID > ");	
            if (a > 1000 && "9".equals(e.ALLATORIxDEMO().get("type"))) {	
                StringBuilder stringBuilder2 = a3;	
                stringBuilder = stringBuilder2;	
                stringBuilder2.append("1000");	
                return stringBuilder.toString();	
            }	
            a3.append(a);	
        }	
        stringBuilder = a3;	
        return stringBuilder.toString();	
    }	
}	
	
