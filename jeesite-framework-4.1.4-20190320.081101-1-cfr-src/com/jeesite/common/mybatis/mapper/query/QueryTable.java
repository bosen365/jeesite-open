/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.common.mybatis.mapper.query;	
	
import com.jeesite.common.cache.JedisUtils;	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.mybatis.annotation.JoinTable;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.common.shiro.cas.CasOutHandler;	
import java.io.Serializable;	
	
public class QueryTable	
implements Serializable {	
    private static final long serialVersionUID = 1L;	
    private BaseEntity<?> entity;	
	
    public String toSql() {	
        void a;	
        int n;	
        StringBuilder a2 = new StringBuilder();	
        Table table = MapperHelper.getTable(this.entity);	
        JoinTable[] arrjoinTable = a.joinTable();	
        a2.append(MapperHelper.getTableName((Table)a, this.entity) + " " + a.alias());	
        int n2 = arrjoinTable.length;	
        int n3 = n = 0;	
        while (n3 < n2) {	
            JoinTable a3 = arrjoinTable[n];	
            Table a4 = MapperHelper.getTable(a3.entity());	
            a2.append(new StringBuilder().insert(0, " ON ").append(a3.on()).toString());	
            a2.append(new StringBuilder().insert(0, MapperHelper.getTableName(a4, this.entity)).append(" ").append(a3.alias()).toString());	
            a2.append(new StringBuilder().insert(0, " ").append(a3.type().value()).append(" ").toString());	
            n3 = ++n;	
        }	
        StringBuilder stringBuilder = a2;	
        stringBuilder.append(MapperHelper.getSqlMapValue(this.entity, a.extFromKeys()));	
        return stringBuilder.toString();	
    }	
	
    public QueryTable(BaseEntity<?> entity) {	
        this.entity = entity;	
    }	
}	
	
