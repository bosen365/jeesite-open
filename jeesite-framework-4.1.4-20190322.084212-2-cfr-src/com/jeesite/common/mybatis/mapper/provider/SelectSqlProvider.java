/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.mybatis.mapper.provider;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.lang.TimeUtils;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.MapperException;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.common.mybatis.mapper.SqlMap;	
import com.jeesite.common.mybatis.mapper.query.QueryColumn;	
import com.jeesite.common.mybatis.mapper.query.QueryOrder;	
import com.jeesite.common.mybatis.mapper.query.QueryTable;	
import com.jeesite.common.mybatis.mapper.query.QueryWhere;	
import java.util.ArrayList;	
import org.hyperic.sigar.ProcCredName;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
	
public class SelectSqlProvider {	
    private Logger logger;	
	
    /*	
     * WARNING - void declaration	
     */	
    public String get(BaseEntity<?> entity) {	
        void a;	
        long a2 = System.currentTimeMillis();	
        StringBuilder a3 = new StringBuilder();	
        StringBuilder a4 = new StringBuilder();	
        StringBuilder a5 = new StringBuilder();	
        StringBuilder a6 = new StringBuilder();	
        Table a7 = MapperHelper.getTable(entity);	
        ArrayList<Column> a8 = ListUtils.newArrayList();	
        for (Column a9 : MapperHelper.getColumns(a7, a8)) {	
            if (!a9.isPK()) continue;	
            String a10 = MapperHelper.getAttrName(a9);	
            this.addWhere(a4, a7, a9, null, a10);	
        }	
        if (a4.length() == 0) {	
            throw new MapperException(new StringBuilder().insert(0, "Error: ").append(entity.getClass()).append(" 没有isPk字段.").toString());	
        }	
        MapperHelper.addExtWhere(a4, entity, a7);	
        a5.append(entity.getSqlMap().getColumn().toSql());	
        a6.append(entity.getSqlMap().getTable().toSql());	
        a3.append("SELECT ");	
        a3.append(a5);	
        a3.append(" FROM ");	
        a3.append(a6);	
        a3.append(" WHERE ");	
        a3.append(a4);	
        String string = a3.toString();	
        if (this.logger.isDebugEnabled()) {	
            this.logger.debug(new StringBuilder().insert(0, TimeUtils.formatDateAgo(System.currentTimeMillis() - a2)).append(": ").append((String)a).toString());	
        }	
        return a;	
    }	
	
    private /* synthetic */ void addWhere(StringBuilder sqlWhere, Table t, Column c2, String paramPrefix, String attrName) {	
        if (sqlWhere.length() != 0) {	
            sqlWhere.append(" AND ");	
        }	
        sqlWhere.append(new StringBuilder().insert(0, t.alias()).append(".").toString());	
        sqlWhere.append(MapperHelper.getColumnName(c2));	
        sqlWhere.append(" = ");	
        sqlWhere.append("#{");	
        if (StringUtils.isNotBlank(paramPrefix)) {	
            sqlWhere.append(paramPrefix + ".");	
        }	
        sqlWhere.append(new StringBuilder().insert(0, attrName).append(MapperHelper.getColumnParamSuffix(c2)).toString());	
        sqlWhere.append("}");	
    }	
	
    public SelectSqlProvider() {	
        SelectSqlProvider selectSqlProvider = this;	
        selectSqlProvider.logger = LoggerFactory.getLogger(selectSqlProvider.getClass());	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    public String findList(BaseEntity<?> entity) {	
        void a;	
        long a2 = System.currentTimeMillis();	
        StringBuilder a3 = new StringBuilder();	
        StringBuilder a4 = new StringBuilder();	
        StringBuilder a5 = new StringBuilder();	
        StringBuilder a6 = new StringBuilder();	
        StringBuilder stringBuilder = a4;	
        stringBuilder.append(entity.getSqlMap().getWhere().toSql());	
        a5.append(entity.getSqlMap().getColumn().toSql());	
        a6.append(entity.getSqlMap().getTable().toSql());	
        a3.append("SELECT ");	
        a3.append(a5);	
        a3.append(" FROM ");	
        a3.append(a6);	
        if (StringUtils.isNotBlank(stringBuilder)) {	
            a3.append(" WHERE ");	
            a3.append(a4);	
        }	
        a3.append(" ORDER BY ");	
        a3.append(entity.getSqlMap().getOrder().toSql());	
        String string = a3.toString();	
        if (this.logger.isDebugEnabled()) {	
            this.logger.debug(new StringBuilder().insert(0, TimeUtils.formatDateAgo(System.currentTimeMillis() - a2)).append(": ").append((String)a).toString());	
        }	
        return a;	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    public String getByEntity(BaseEntity<?> entity) {	
        void a;	
        long a2 = System.currentTimeMillis();	
        StringBuilder a3 = new StringBuilder();	
        StringBuilder a4 = new StringBuilder();	
        StringBuilder a5 = new StringBuilder();	
        StringBuilder a6 = new StringBuilder();	
        StringBuilder stringBuilder = a4;	
        stringBuilder.append(entity.getSqlMap().getWhere().toSql());	
        if (stringBuilder.length() == 0) {	
            throw new MapperException(new StringBuilder().insert(0, "Error: ").append(entity.getClass()).append(" 没有Where条件.").toString());	
        }	
        a5.append(entity.getSqlMap().getColumn().toSql());	
        a6.append(entity.getSqlMap().getTable().toSql());	
        a3.append("SELECT ");	
        a3.append(a5);	
        a3.append(" FROM ");	
        a3.append(a6);	
        a3.append(" WHERE ");	
        a3.append(a4);	
        String string = a3.toString();	
        if (this.logger.isDebugEnabled()) {	
            this.logger.debug(new StringBuilder().insert(0, TimeUtils.formatDateAgo(System.currentTimeMillis() - a2)).append(": ").append((String)a).toString());	
        }	
        return a;	
    }	
	
    public String findCount(BaseEntity<?> entity) {	
        return new StringBuilder().insert(0, "/*return count*/").append(this.findList(entity)).toString();	
    }	
}	
	
