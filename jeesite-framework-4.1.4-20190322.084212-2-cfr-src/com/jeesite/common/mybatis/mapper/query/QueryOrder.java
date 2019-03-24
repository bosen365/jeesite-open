/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.mybatis.mapper.query;	
	
import com.jeesite.common.codec.EncodeUtils;	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import java.io.Serializable;	
import org.hyperic.sigar.ProcState;	
import org.hyperic.sigar.SysInfo;	
	
public class QueryOrder	
implements Serializable {	
    private static final long serialVersionUID = 1L;	
    private BaseEntity<?> entity;	
    private String orderBy;	
	
    public QueryOrder(BaseEntity<?> entity) {	
        this.entity = entity;	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    public String toSql() {	
        void a;	
        StringBuilder stringBuilder = new StringBuilder();	
        Table a2 = MapperHelper.getTable(this.entity);	
        QueryOrder queryOrder = this;	
        queryOrder.addOrderBy((StringBuilder)a, a2, queryOrder.entity, a2.alias());	
        return a.toString();	
    }	
	
    private /* synthetic */ void addOrderBy(StringBuilder sql, Table t, BaseEntity<?> entity, String tableAlias) {	
        int a;	
        if (entity.getPage() != null && StringUtils.isNotBlank(entity.getPage().getOrderBy())) {	
            sql.append(entity.getPage().getOrderBy());	
            return;	
        }	
        if (StringUtils.isNotBlank(this.getOrderBy())) {	
            sql.append(this.getOrderBy());	
            return;	
        }	
        if (StringUtils.isNotBlank(t.orderBy())) {	
            sql.append(t.orderBy());	
            return;	
        }	
        String[] a2 = StringUtils.split(entity.getIdColumnName(), "#");	
        int n = a = 0;	
        while (n < a2.length) {	
            if (a != 0) {	
                sql.append(", ");	
            }	
            String string = a2[a];	
            sql.append(new StringBuilder().insert(0, tableAlias).append(".").append(string).toString());	
            n = ++a;	
        }	
    }	
	
    public void setOrderBy(String orderBy) {	
        this.orderBy = orderBy;	
    }	
	
    public String getOrderBy() {	
        return EncodeUtils.sqlFilter(this.orderBy);	
    }	
}	
	
