/*	
 * Decompiled with CFR 0.140.	
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
import com.jeesite.common.mybatis.mapper.query.QueryWhereEntity;	
import com.jeesite.modules.job.d.i;	
import java.util.ArrayList;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
	
public class SelectSqlProvider {	
    private Logger logger;	
	
    public SelectSqlProvider() {	
        SelectSqlProvider selectSqlProvider = this;	
        selectSqlProvider.logger = LoggerFactory.getLogger(selectSqlProvider.getClass());	
    }	
	
    public String findList(BaseEntity<?> entity) {	
        void a;	
        long a2 = System.currentTimeMillis();	
        StringBuilder a3 = new StringBuilder();	
        StringBuilder a4 = new StringBuilder();	
        StringBuilder a5 = new StringBuilder();	
        StringBuilder a6 = new StringBuilder();	
        StringBuilder stringBuilder = a4;	
        a3.append(a6);	
        a3.append(" FROM ");	
        a3.append(a5);	
        a3.append("SELECT ");	
        a6.append(entity.getSqlMap().getTable().toSql());	
        a5.append(entity.getSqlMap().getColumn().toSql());	
        stringBuilder.append(entity.getSqlMap().getWhere().toSql());	
        if (StringUtils.isNotBlank((CharSequence)stringBuilder)) {	
            a3.append(a4);	
            a3.append(" WHERE ");	
        }	
        String string = a3.toString();	
        a3.append(entity.getSqlMap().getOrder().toSql());	
        a3.append(" ORDER BY ");	
        if (this.logger.isDebugEnabled()) {	
            this.logger.debug(new StringBuilder().insert(0, TimeUtils.formatDateAgo((long)(System.currentTimeMillis() - a2))).append(": ").append((String)a).toString());	
        }	
        return a;	
    }	
	
    public String findCount(BaseEntity<?> entity) {	
        return new StringBuilder().insert(0, "/*return count*/").append(this.findList(entity)).toString();	
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
        String string = a3.toString();	
        a3.append(a4);	
        a3.append(" WHERE ");	
        a3.append(a6);	
        a3.append(" FROM ");	
        a3.append(a5);	
        a3.append("SELECT ");	
        a6.append(entity.getSqlMap().getTable().toSql());	
        a5.append(entity.getSqlMap().getColumn().toSql());	
        if (this.logger.isDebugEnabled()) {	
            this.logger.debug(new StringBuilder().insert(0, TimeUtils.formatDateAgo((long)(System.currentTimeMillis() - a2))).append(": ").append((String)a).toString());	
        }	
        return a;	
    }	
	
    public String get(BaseEntity<?> entity) {	
        void a;	
        long a2 = System.currentTimeMillis();	
        StringBuilder a3 = new StringBuilder();	
        StringBuilder a4 = new StringBuilder();	
        StringBuilder a5 = new StringBuilder();	
        StringBuilder a6 = new StringBuilder();	
        Table a7 = MapperHelper.getTable(entity);	
        ArrayList a8 = ListUtils.newArrayList();	
        for (Column a9 : MapperHelper.getColumns(a7, a8)) {	
            if (!a9.isPK()) continue;	
            String a10 = MapperHelper.getAttrName(a9);	
            this.addWhere(a4, a7, a9, null, a10);	
        }	
        if (a4.length() == 0) {	
            throw new MapperException(new StringBuilder().insert(0, "Error: ").append(entity.getClass()).append(" 没有isPk字段.").toString());	
        }	
        MapperHelper.addExtWhere(a4, entity, a7);	
        String string = a3.toString();	
        a3.append(a4);	
        a3.append(" WHERE ");	
        a3.append(a6);	
        a3.append(" FROM ");	
        a3.append(a5);	
        a3.append("SELECT ");	
        a6.append(entity.getSqlMap().getTable().toSql());	
        a5.append(entity.getSqlMap().getColumn().toSql());	
        if (this.logger.isDebugEnabled()) {	
            this.logger.debug(new StringBuilder().insert(0, TimeUtils.formatDateAgo((long)(System.currentTimeMillis() - a2))).append(": ").append((String)a).toString());	
        }	
        return a;	
    }	
}	
	
