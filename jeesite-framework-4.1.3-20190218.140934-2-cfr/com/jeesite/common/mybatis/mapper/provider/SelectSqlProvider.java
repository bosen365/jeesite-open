/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.collect.ListUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  com.jeesite.common.lang.TimeUtils	
 *  org.slf4j.Logger	
 *  org.slf4j.LoggerFactory	
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
import org.hyperic.sigar.SigarException;	
import org.hyperic.sigar.ptql.ProcessFinder;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
	
public class SelectSqlProvider {	
    private Logger logger;	
	
    public SelectSqlProvider() {	
        SelectSqlProvider selectSqlProvider = this;	
        selectSqlProvider.logger = LoggerFactory.getLogger(selectSqlProvider.getClass());	
    }	
	
    public String findList(BaseEntity<?> entity) {	
        void a2;	
        long a3 = System.currentTimeMillis();	
        StringBuilder a4 = new StringBuilder();	
        StringBuilder a5 = new StringBuilder();	
        StringBuilder a6 = new StringBuilder();	
        StringBuilder a7 = new StringBuilder();	
        StringBuilder stringBuilder = a5;	
        a4.append(a7);	
        a4.append(" FROM ");	
        a4.append(a6);	
        a4.append("SELECT ");	
        a7.append(entity.getSqlMap().getTable().toSql());	
        a6.append(entity.getSqlMap().getColumn().toSql());	
        stringBuilder.append(entity.getSqlMap().getWhere().toSql());	
        if (StringUtils.isNotBlank((CharSequence)stringBuilder)) {	
            a4.append(a5);	
            a4.append(" WHERE ");	
        }	
        String string = a4.toString();	
        a4.append(entity.getSqlMap().getOrder().toSql());	
        a4.append(" ORDER BY ");	
        if (this.logger.isDebugEnabled()) {	
            this.logger.debug(new StringBuilder().insert(0, TimeUtils.formatDateAgo((long)(System.currentTimeMillis() - a3))).append(": ").append((String)a2).toString());	
        }	
        return a2;	
    }	
	
    public String getByEntity(BaseEntity<?> entity) {	
        void a2;	
        long a3 = System.currentTimeMillis();	
        StringBuilder a4 = new StringBuilder();	
        StringBuilder a5 = new StringBuilder();	
        StringBuilder a6 = new StringBuilder();	
        StringBuilder a7 = new StringBuilder();	
        StringBuilder stringBuilder = a5;	
        stringBuilder.append(entity.getSqlMap().getWhere().toSql());	
        if (stringBuilder.length() == 0) {	
            throw new MapperException(new StringBuilder().insert(0, "Error: ").append(entity.getClass()).append(" 没有Where条件.").toString());	
        }	
        String string = a4.toString();	
        a4.append(a5);	
        a4.append(" WHERE ");	
        a4.append(a7);	
        a4.append(" FROM ");	
        a4.append(a6);	
        a4.append("SELECT ");	
        a7.append(entity.getSqlMap().getTable().toSql());	
        a6.append(entity.getSqlMap().getColumn().toSql());	
        if (this.logger.isDebugEnabled()) {	
            this.logger.debug(new StringBuilder().insert(0, TimeUtils.formatDateAgo((long)(System.currentTimeMillis() - a3))).append(": ").append((String)a2).toString());	
        }	
        return a2;	
    }	
	
    public String get(BaseEntity<?> entity) {	
        void a2;	
        long a3 = System.currentTimeMillis();	
        StringBuilder a4 = new StringBuilder();	
        StringBuilder a5 = new StringBuilder();	
        StringBuilder a6 = new StringBuilder();	
        StringBuilder a7 = new StringBuilder();	
        Table a8 = MapperHelper.getTable(entity);	
        ArrayList a9 = ListUtils.newArrayList();	
        for (Column a10 : MapperHelper.getColumns(a8, a9)) {	
            if (!a10.isPK()) continue;	
            String a11 = MapperHelper.getAttrName(a10);	
            this.addWhere(a5, a8, a10, null, a11);	
        }	
        if (a5.length() == 0) {	
            throw new MapperException(new StringBuilder().insert(0, "Error: ").append(entity.getClass()).append(" 没有isPk字段.").toString());	
        }	
        MapperHelper.addExtWhere(a5, entity, a8);	
        String string = a4.toString();	
        a4.append(a5);	
        a4.append(" WHERE ");	
        a4.append(a7);	
        a4.append(" FROM ");	
        a4.append(a6);	
        a4.append("SELECT ");	
        a7.append(entity.getSqlMap().getTable().toSql());	
        a6.append(entity.getSqlMap().getColumn().toSql());	
        if (this.logger.isDebugEnabled()) {	
            this.logger.debug(new StringBuilder().insert(0, TimeUtils.formatDateAgo((long)(System.currentTimeMillis() - a3))).append(": ").append((String)a2).toString());	
        }	
        return a2;	
    }	
	
    private /* synthetic */ void addWhere(StringBuilder sqlWhere, Table t, Column c2, String paramPrefix, String attrName) {	
        if (sqlWhere.length() != 0) {	
            sqlWhere.append(" AND ");	
        }	
        sqlWhere.append("#{");	
        sqlWhere.append(" = ");	
        sqlWhere.append(MapperHelper.getColumnName(c2));	
        sqlWhere.append(new StringBuilder().insert(0, t.alias()).append(".").toString());	
        if (StringUtils.isNotBlank((CharSequence)paramPrefix)) {	
            sqlWhere.append(paramPrefix + ".");	
        }	
        sqlWhere.append("}");	
        sqlWhere.append(new StringBuilder().insert(0, attrName).append(MapperHelper.getColumnParamSuffix(c2)).toString());	
    }	
	
    public String findCount(BaseEntity<?> entity) {	
        return new StringBuilder().insert(0, "/*return count*/").append(this.findList(entity)).toString();	
    }	
}	
	
