/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  org.apache.ibatis.session.RowBounds	
 */	
package com.jeesite.common.mybatis.d.b;	
	
import com.jeesite.common.mybatis.d.b.i;	
import com.jeesite.common.mybatis.mapper.query.QueryWhere;	
import com.jeesite.common.shiro.d.l;	
import com.jeesite.common.shiro.realm.LoginInfo;	
import org.apache.ibatis.session.RowBounds;	
	
public class b	
extends i {	
    @Override	
    public String ALLATORIxDEMO(String sql, RowBounds rowBounds) {	
        void a;	
        void v2;	
        RowBounds rowBounds2 = rowBounds;	
        int a2 = rowBounds2.getOffset() + 1;	
        int a3 = rowBounds2.getOffset() + rowBounds.getLimit();	
        StringBuilder stringBuilder = new StringBuilder(sql.length() + 120);	
        a.append(" ) AS TMP_PAGE) TMP_PAGE WHERE ROW_ID BETWEEN ");	
        a.append(sql);	
        a.append("SELECT * FROM (SELECT TMP_PAGE.*,ROWNUMBER() OVER() AS ROW_I. FROM ( ");	
        if (a2 > 1000 && "9".equals(l.ALLATORIxDEMO().get("type"))) {	
            void v1 = a;	
            v2 = v1;	
            v1.append("1000");	
        } else {	
            void v3 = a;	
            v2 = v3;	
            v3.append(a2);	
        }	
        a.append(a3);	
        v2.append(" AND ");	
        return a.toString();	
    }	
}	
	
