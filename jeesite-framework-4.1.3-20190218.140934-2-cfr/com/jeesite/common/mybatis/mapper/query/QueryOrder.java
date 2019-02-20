/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.codec.EncodeUtils	
 *  com.jeesite.common.lang.StringUtils	
 */	
package com.jeesite.common.mybatis.mapper.query;	
	
import com.jeesite.autoconfigure.core.DataSourceAutoConfiguration;	
import com.jeesite.common.codec.EncodeUtils;	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.j2cache.autoconfigure.J2CacheAutoConfiguration;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import java.io.Serializable;	
	
public class QueryOrder	
implements Serializable {	
    private static final long serialVersionUID = 1L;	
    private String orderBy;	
    private BaseEntity<?> entity;	
	
    private /* synthetic */ void addOrderBy(StringBuilder sql, Table t, BaseEntity<?> entity, String tableAlias) {	
        int a2;	
        if (entity.getPage() != null && StringUtils.isNotBlank((CharSequence)entity.getPage().getOrderBy())) {	
            sql.append(entity.getPage().getOrderBy());	
            return;	
        }	
        if (StringUtils.isNotBlank((CharSequence)this.getOrderBy())) {	
            sql.append(this.getOrderBy());	
            return;	
        }	
        if (StringUtils.isNotBlank((CharSequence)t.orderBy())) {	
            sql.append(t.orderBy());	
            return;	
        }	
        String[] a3 = StringUtils.split((String)entity.getIdColumnName(), (String)"#");	
        int n = a2 = 0;	
        while (n < a3.length) {	
            if (a2 != 0) {	
                sql.append(", ");	
            }	
            String string = a3[a2];	
            sql.append(new StringBuilder().insert(0, tableAlias).append(".").append(string).toString());	
            n = ++a2;	
        }	
    }	
	
    public String getOrderBy() {	
        return EncodeUtils.sqlFilter((String)this.orderBy);	
    }	
	
    public String toSql() {	
        void a2;	
        StringBuilder stringBuilder = new StringBuilder();	
        Table a3 = MapperHelper.getTable(this.entity);	
        QueryOrder queryOrder = this;	
        queryOrder.addOrderBy((StringBuilder)a2, a3, queryOrder.entity, a3.alias());	
        return a2.toString();	
    }	
	
    public void setOrderBy(String orderBy) {	
        this.orderBy = orderBy;	
    }	
	
    public QueryOrder(BaseEntity<?> entity) {	
        this.entity = entity;	
    }	
}	
	
