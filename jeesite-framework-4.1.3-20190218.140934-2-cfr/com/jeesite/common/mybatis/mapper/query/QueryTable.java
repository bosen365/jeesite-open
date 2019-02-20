/*	
 * Decompiled with CFR 0.139.	
 */	
package com.jeesite.common.mybatis.mapper.query;	
	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.j2cache.autoconfigure.J2CacheAutoConfiguration;	
import com.jeesite.common.mybatis.annotation.JoinTable;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import java.io.Serializable;	
import org.hyperic.sigar.ProcState;	
	
public class QueryTable	
implements Serializable {	
    private static final long serialVersionUID = 1L;	
    private BaseEntity<?> entity;	
	
    public QueryTable(BaseEntity<?> entity) {	
        this.entity = entity;	
    }	
	
    public String toSql() {	
        int n;	
        void a2;	
        StringBuilder a3 = new StringBuilder();	
        Table table = MapperHelper.getTable(this.entity);	
        JoinTable[] arrjoinTable = a2.joinTable();	
        a3.append(MapperHelper.getTableName((Table)a2, this.entity) + " " + a2.alias());	
        int n2 = arrjoinTable.length;	
        int n3 = n = 0;	
        while (n3 < n2) {	
            JoinTable a4 = arrjoinTable[n];	
            Table a5 = MapperHelper.getTable(a4.entity());	
            a3.append(new StringBuilder().insert(0, " ON ").append(a4.on()).toString());	
            a3.append(new StringBuilder().insert(0, MapperHelper.getTableName(a5, this.entity)).append(" ").append(a4.alias()).toString());	
            a3.append(new StringBuilder().insert(0, " ").append(a4.type().value()).append(" ").toString());	
            n3 = ++n;	
        }	
        StringBuilder stringBuilder = a3;	
        stringBuilder.append(MapperHelper.getSqlMapValue(this.entity, a2.extFromKeys()));	
        return stringBuilder.toString();	
    }	
}	
	
