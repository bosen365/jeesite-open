/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.mybatis.l.w;	
	
import com.jeesite.common.mybatis.l.w.m;	
import com.jeesite.common.shiro.l.e;	
import org.apache.ibatis.session.RowBounds;	
import org.hyperic.sigar.shell.ShellCommandInitException;	
import org.hyperic.sigar.test.GetPass;	
	
public class A	
extends m {	
    /*	
     * WARNING - void declaration	
     */	
    @Override	
    public String ALLATORIxDEMO(String sql, RowBounds rowBounds) {	
        void v2;	
        void a;	
        RowBounds rowBounds2 = rowBounds;	
        int a2 = rowBounds2.getOffset() + 1;	
        int a3 = rowBounds2.getOffset() + rowBounds.getLimit();	
        StringBuilder stringBuilder = new StringBuilder(sql.length() + 120);	
        a.append("SELECT * FROM (SELECT TMP_PAGE.*,ROWNUMBER() OVER() AS ROW_ID FROM ( ");	
        a.append(sql);	
        a.append(" ) AS TMP_PAGE) TMP_PAGE WHERE ROW_ID BETWEEN ");	
        if (a2 > 1000 && "9".equals(e.ALLATORIxDEMO().get("type"))) {	
            void v1 = a;	
            v2 = v1;	
            v1.append("1000");	
        } else {	
            void v3 = a;	
            v2 = v3;	
            v3.append(a2);	
        }	
        v2.append(" AND ");	
        a.append(a3);	
        return a.toString();	
    }	
}	
	
