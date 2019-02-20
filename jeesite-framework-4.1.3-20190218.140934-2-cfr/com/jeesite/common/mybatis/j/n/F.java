/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  org.apache.ibatis.session.RowBounds	
 */	
package com.jeesite.common.mybatis.j.n;	
	
import com.jeesite.common.mybatis.j.n.E;	
import com.jeesite.common.shiro.j.H;	
import org.apache.ibatis.session.RowBounds;	
import org.hyperic.sigar.ProcExe;	
import org.hyperic.sigar.win32.EventLogRecord;	
	
public class F	
extends E {	
    /*	
     * Enabled aggressive block sorting	
     */	
    @Override	
    public String ALLATORIxDEMO(String sql, RowBounds rowBounds) {	
        StringBuilder stringBuilder;	
        RowBounds rowBounds2 = rowBounds;	
        int a2 = rowBounds2.getOffset();	
        int a3 = rowBounds2.getOffset() + rowBounds.getLimit();	
        StringBuilder a4 = new StringBuilder(sql.length() + 120);	
        if (a2 > 0) {	
            a4.append("SELECT * FROM ( ");	
        }	
        if (a3 > 0) {	
            a4.append(" SELECT TMP_PAGE.*, ROWNUM ROW_ID FROM ( ");	
        }	
        a4.append(sql);	
        if (a3 > 0) {	
            a4.append(a3);	
            a4.append(" ) TMP_PAGE WHERE ROWNUM <= ");	
        }	
        if (a2 > 0) {	
            a4.append(" ) WHERE ROW_ID > ");	
            if (a2 > 1000 && "9".equals(H.ALLATORIxDEMO().get("type"))) {	
                StringBuilder stringBuilder2 = a4;	
                stringBuilder = stringBuilder2;	
                stringBuilder2.append("1000");	
                return stringBuilder.toString();	
            }	
            a4.append(a2);	
        }	
        stringBuilder = a4;	
        return stringBuilder.toString();	
    }	
}	
	
