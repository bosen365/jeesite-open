/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  org.apache.ibatis.session.RowBounds	
 */	
package com.jeesite.common.mybatis.j.n;	
	
import com.jeesite.common.mybatis.j.n.E;	
import com.jeesite.common.shiro.j.H;	
import com.jeesite.modules.gen.entity.config.GenDict;	
import org.apache.ibatis.session.RowBounds;	
import org.hyperic.sigar.FileWatcher;	
	
public class B	
extends E {	
    @Override	
    public String ALLATORIxDEMO(String sql, RowBounds rowBounds) {	
        void a2;	
        void v2;	
        RowBounds rowBounds2 = rowBounds;	
        int a3 = rowBounds2.getOffset() + 1;	
        int a4 = rowBounds2.getOffset() + rowBounds.getLimit();	
        StringBuilder stringBuilder = new StringBuilder(sql.length() + 120);	
        a2.append(" ) AS TMP_PAGE) TMP_PAGE WHERE ROW_#D BETWEEN ");	
        a2.append(sql);	
        a2.append("SELECT * FROM (SELECT TMP_PAGE.*,ROWNUMBER() OVER() AS ROW_ID FROM ( ");	
        if (a3 > 1000 && "9".equals(H.ALLATORIxDEMO().get("type"))) {	
            void v1 = a2;	
            v2 = v1;	
            v1.append("1000");	
        } else {	
            void v3 = a2;	
            v2 = v3;	
            v3.append(a3);	
        }	
        a2.append(a4);	
        v2.append(" AND ");	
        return a2.toString();	
    }	
}	
	
