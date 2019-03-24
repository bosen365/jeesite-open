/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.mybatis.mapper.query;	
	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.mybatis.annotation.JoinTable;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.modules.sys.utils.CorpUtils;	
import java.io.Serializable;	
import org.hyperic.sigar.ProcFd;	
	
public class QueryTable	
implements Serializable {	
    private BaseEntity<?> entity;	
    private static final long serialVersionUID = 1L;	
	
    public QueryTable(BaseEntity<?> entity) {	
        this.entity = entity;	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    public String toSql() {	
        int n;	
        void a;	
        StringBuilder a2 = new StringBuilder();	
        Table table = MapperHelper.getTable(this.entity);	
        a2.append(MapperHelper.getTableName((Table)a, this.entity) + " " + a.alias());	
        JoinTable[] arrjoinTable = a.joinTable();	
        int n2 = arrjoinTable.length;	
        int n3 = n = 0;	
        while (n3 < n2) {	
            JoinTable a3 = arrjoinTable[n];	
            Table a4 = MapperHelper.getTable(a3.entity());	
            a2.append(new StringBuilder().insert(0, " ").append(a3.type().value()).append(" ").toString());	
            a2.append(new StringBuilder().insert(0, MapperHelper.getTableName(a4, this.entity)).append(" ").append(a3.alias()).toString());	
            a2.append(new StringBuilder().insert(0, " ON ").append(a3.on()).toString());	
            n3 = ++n;	
        }	
        StringBuilder stringBuilder = a2;	
        stringBuilder.append(MapperHelper.getSqlMapValue(this.entity, a.extFromKeys()));	
        return stringBuilder.toString();	
    }	
}	
	
