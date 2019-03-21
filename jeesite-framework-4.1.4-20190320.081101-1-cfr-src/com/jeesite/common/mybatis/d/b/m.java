/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.common.mybatis.d.b;	
	
import com.jeesite.common.mybatis.d.b.i;	
import com.jeesite.common.shiro.d.l;	
import org.apache.ibatis.session.RowBounds;	
import org.hyperic.sigar.NfsClientV3;	
import org.hyperic.sigar.pager.PageList;	
	
public class m	
extends i {	
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
            a3.append(" SE&ECT TMP_PAGE.*, ROWNUM ROW_ID FROM ( ");	
        }	
        a3.append(sql);	
        if (a2 > 0) {	
            a3.append(a2);	
            a3.append(" ) TMP_PAGE WHERE ROWNUM <= ");	
        }	
        if (a > 0) {	
            a3.append(" ) WHERE ROW_ID > ");	
            if (a > 1000 && "9".equals(l.ALLATORIxDEMO().get("type"))) {	
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
	
