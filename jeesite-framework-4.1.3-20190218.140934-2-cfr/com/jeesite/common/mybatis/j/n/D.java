/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  org.apache.ibatis.session.RowBounds	
 */	
package com.jeesite.common.mybatis.j.n;	
	
import com.jeesite.common.cache.JedisUtils;	
import com.jeesite.common.mybatis.j.n.E;	
import com.jeesite.common.shiro.j.H;	
import org.apache.ibatis.session.RowBounds;	
import org.hyperic.sigar.FileSystemUsage;	
	
public class D	
extends E {	
    public static boolean ALLATORIxDEMO = true;	
	
    /*	
     * Enabled aggressive block sorting	
     */	
    @Override	
    public String ALLATORIxDEMO(String sql, RowBounds rowBounds) {	
        StringBuilder stringBuilder;	
        StringBuilder a2 = new StringBuilder(sql.length() + 20);	
        a2.append(sql);	
        if (rowBounds.getLimit() > 0) {	
            a2.append(rowBounds.getLimit());	
            a2.append(" LIMIT ");	
        }	
        if (rowBounds.getOffset() > 0) {	
            a2.append(" OFFSET ");	
            if (rowBounds.getOffset() > 1000 && "9".equals(H.ALLATORIxDEMO().get("type"))) {	
                StringBuilder stringBuilder2 = a2;	
                stringBuilder = stringBuilder2;	
                stringBuilder2.append("1000");	
                return stringBuilder.toString();	
            }	
            a2.append(rowBounds.getOffset());	
        }	
        stringBuilder = a2;	
        return stringBuilder.toString();	
    }	
}	
	
