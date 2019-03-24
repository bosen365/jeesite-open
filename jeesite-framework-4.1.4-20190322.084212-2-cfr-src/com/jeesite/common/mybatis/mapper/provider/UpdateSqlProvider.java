/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.mybatis.mapper.provider;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.lang.TimeUtils;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.MapperException;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.common.mybatis.mapper.SqlMap;	
import com.jeesite.common.mybatis.mapper.query.QueryWhere;	
import com.jeesite.common.reflect.ReflectUtils;	
import java.util.ArrayList;	
import java.util.Iterator;	
import java.util.Map;	
import org.hyperic.sigar.DirStat;	
import org.hyperic.sigar.DirUsage;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
	
public class UpdateSqlProvider {	
    private Logger logger;	
	
    private /* synthetic */ void addWhere(StringBuilder sqlWhere, Column c2, String paramPrefix, String attrName) {	
        if (sqlWhere.length() != 0) {	
            sqlWhere.append(" AND ");	
        }	
        sqlWhere.append(MapperHelper.getColumnName(c2));	
        sqlWhere.append(" = ");	
        sqlWhere.append("#{");	
        if (StringUtils.isNotBlank(paramPrefix)) {	
            sqlWhere.append(paramPrefix + ".");	
        }	
        sqlWhere.append(new StringBuilder().insert(0, attrName).append(MapperHelper.getColumnParamSuffix(c2)).toString());	
        sqlWhere.append("}");	
    }	
	
    public String delete(BaseEntity<?> entity) {	
        StringBuilder stringBuilder;	
        long a = System.currentTimeMillis();	
        StringBuilder a2 = new StringBuilder();	
        StringBuilder a3 = new StringBuilder();	
        StringBuilder a4 = new StringBuilder();	
        BaseEntity<?> baseEntity = entity;	
        Table a5 = MapperHelper.getTable(baseEntity);	
        if (baseEntity instanceof DataEntity) {	
            ((DataEntity)entity).setStatus("1");	
        }	
        this.addUpdateStatus(a5, entity, a4, a3, null);	
        if (a3.length() == 0) {	
            throw new MapperException(new StringBuilder().insert(0, "Error: ").append(entity.getClass()).append(" 没有isPk字段.").toString());	
        }	
        MapperHelper.addExtWhere(a3, entity, a5);	
        if (a4.length() == 0) {	
            StringBuilder stringBuilder2 = a2;	
            a2.append(MapperHelper.getTableName(a5, entity));	
            a2.append("DELETE FROM ");	
            stringBuilder = stringBuilder2;	
            stringBuilder2.append(" WHERE ");	
            a2.append(a3);	
        } else {	
            StringBuilder stringBuilder3 = a2;	
            stringBuilder = stringBuilder3;	
            stringBuilder3.append("UPDATE ");	
            a2.append(MapperHelper.getTableName(a5, entity));	
            a2.append(" SET ");	
            a2.append(a4);	
            a2.append(" WHERE ");	
            a2.append(a3);	
        }	
        String a6 = stringBuilder.toString();	
        if (this.logger.isDebugEnabled()) {	
            this.logger.debug(new StringBuilder().insert(0, TimeUtils.formatDateAgo(System.currentTimeMillis() - a)).append(": ").append(a6).toString());	
        }	
        return a6;	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    public String phyDelete(BaseEntity<?> entity) {	
        void a;	
        void a2;	
        long a3 = System.currentTimeMillis();	
        StringBuilder a4 = new StringBuilder();	
        StringBuilder a5 = new StringBuilder();	
        Table table = MapperHelper.getTable(entity);	
        this.addUpdateStatus((Table)a2, entity, null, a5, null);	
        if (a5.length() == 0) {	
            throw new MapperException(new StringBuilder().insert(0, "Error: ").append(entity.getClass()).append(" 没有isPk字段.").toString());	
        }	
        MapperHelper.addExtWhere(a5, entity, (Table)a2);	
        a4.append("DELETE FROM ");	
        a4.append(MapperHelper.getTableName((Table)a2, entity));	
        a4.append(" WHERE ");	
        a4.append(a5);	
        String string = a4.toString();	
        if (this.logger.isDebugEnabled()) {	
            this.logger.debug(new StringBuilder().insert(0, TimeUtils.formatDateAgo(System.currentTimeMillis() - a3)).append(": ").append((String)a).toString());	
        }	
        return a;	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    public String updateByEntity(Map<String, ?> params) {	
        void a;	
        long a2 = System.currentTimeMillis();	
        BaseEntity a3 = (BaseEntity)params.get("param1");	
        BaseEntity a4 = (BaseEntity)params.get("param2");	
        StringBuilder a5 = new StringBuilder();	
        StringBuilder a6 = new StringBuilder();	
        StringBuilder a7 = new StringBuilder();	
        StringBuilder stringBuilder = a6;	
        stringBuilder.append(a4.getSqlMap().getWhere().toSql(null, "param2"));	
        if (stringBuilder.length() == 0) {	
            throw new MapperException(new StringBuilder().insert(0, "Error: ").append(a3.getClass()).append(" 没有Where字段.").toString());	
        }	
        Table a8 = MapperHelper.getTable(a3);	
        ArrayList<Column> a9 = ListUtils.newArrayList();	
        Object object = MapperHelper.getColumns(a8, a9).iterator();	
        block0 : do {	
            Iterator<Column> iterator = object;	
            while (iterator.hasNext()) {	
                Column a10 = object.next();	
                String a11 = MapperHelper.getAttrName(a10);	
                if (StringUtils.equals(a10.name(), "update_by")) {	
                    ReflectUtils.invokeMethod(a3, "preUpdate", null, null);	
                }	
                if (a10.isPK()) {	
                    iterator = object;	
                    continue;	
                }	
                if (!a10.isUpdate()) continue block0;	
                Object a12 = ReflectUtils.invokeGetter(a3, a11);	
                this.addColumn(a7, a10, "param1", a11, a12);	
                continue block0;	
            }	
            break;	
        } while (true);	
        if (a7.length() == 0) {	
            throw new MapperException(new StringBuilder().insert(0, "Error: ").append(a3.getClass()).append(" 没有isUpdate字段.").toString());	
        }	
        a5.append("UPDATE ");	
        a5.append(MapperHelper.getTableName(a8, a3));	
        a5.append(" SET ");	
        a5.append(a7);	
        a5.append(" WHERE ");	
        a5.append(a6);	
        object = a5.toString();	
        if (this.logger.isDebugEnabled()) {	
            this.logger.debug(new StringBuilder().insert(0, TimeUtils.formatDateAgo(System.currentTimeMillis() - a2)).append(": ").append((String)a).toString());	
        }	
        return a;	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    public String updateStatusByEntity(Map<String, ?> params) {	
        void a;	
        long a2 = System.currentTimeMillis();	
        BaseEntity a3 = (BaseEntity)params.get("param1");	
        BaseEntity a4 = (BaseEntity)params.get("param2");	
        StringBuilder a5 = new StringBuilder();	
        StringBuilder a6 = new StringBuilder();	
        StringBuilder a7 = new StringBuilder();	
        Table a8 = MapperHelper.getTable(a3);	
        StringBuilder stringBuilder = a6;	
        stringBuilder.append(a4.getSqlMap().getWhere().toSql(null, "param2"));	
        if (stringBuilder.length() == 0) {	
            throw new MapperException(new StringBuilder().insert(0, "Error: ").append(a3.getClass()).append(" 没有Where条件.").toString());	
        }	
        this.addUpdateStatus(a8, a3, a7, null, "param1");	
        if (a7.length() == 0) {	
            throw new MapperException(new StringBuilder().insert(0, "Error: ").append(a3.getClass()).append(" 没有isUpdate字段.").toString());	
        }	
        a5.append("UPDATE ");	
        a5.append(MapperHelper.getTableName(a8, a3));	
        a5.append(" SET ");	
        a5.append(a7);	
        a5.append(" WHERE ");	
        a5.append(a6);	
        String string = a5.toString();	
        if (this.logger.isDebugEnabled()) {	
            this.logger.debug(new StringBuilder().insert(0, TimeUtils.formatDateAgo(System.currentTimeMillis() - a2)).append(": ").append((String)a).toString());	
        }	
        return a;	
    }	
	
    public UpdateSqlProvider() {	
        UpdateSqlProvider updateSqlProvider = this;	
        updateSqlProvider.logger = LoggerFactory.getLogger(updateSqlProvider.getClass());	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    public String updateStatus(BaseEntity<?> entity) {	
        void a;	
        void a2;	
        long a3 = System.currentTimeMillis();	
        StringBuilder a4 = new StringBuilder();	
        StringBuilder a5 = new StringBuilder();	
        StringBuilder a6 = new StringBuilder();	
        Table table = MapperHelper.getTable(entity);	
        this.addUpdateStatus((Table)a, entity, a6, a5, null);	
        if (a5.length() == 0) {	
            throw new MapperException(new StringBuilder().insert(0, "Error: ").append(entity.getClass()).append(" 没有is:k字段.").toString());	
        }	
        MapperHelper.addExtWhere(a5, entity, (Table)a);	
        if (a6.length() == 0) {	
            throw new MapperException(new StringBuilder().insert(0, "Error: ").append(entity.getClass()).append(" 没有isUpdate字段.").toString());	
        }	
        a4.append("UPDATE ");	
        a4.append(MapperHelper.getTableName((Table)a, entity));	
        a4.append(" SET ");	
        a4.append(a6);	
        a4.append(" WHERE ");	
        a4.append(a5);	
        String string = a4.toString();	
        if (this.logger.isDebugEnabled()) {	
            this.logger.debug(new StringBuilder().insert(0, TimeUtils.formatDateAgo(System.currentTimeMillis() - a3)).append(": ").append((String)a2).toString());	
        }	
        return a2;	
    }	
	
    private /* synthetic */ void addColumn(StringBuilder sqlColumn, Column c2, String paramPrefix, String attrName, Object attrValue) {	
        if (attrValue != null || c2.isUpdateForce()) {	
            if (sqlColumn.length() != 0) {	
                sqlColumn.append(", ");	
            }	
            sqlColumn.append(MapperHelper.getColumnName(c2));	
            sqlColumn.append(" = ");	
            sqlColumn.append("#{");	
            if (StringUtils.isNotBlank(paramPrefix)) {	
                sqlColumn.append(paramPrefix + ".");	
            }	
            sqlColumn.append(new StringBuilder().insert(0, attrName).append(MapperHelper.getColumnParamSuffix(c2)).toString());	
            sqlColumn.append("}");	
        }	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    private /* synthetic */ void addUpdateStatus(Table t, BaseEntity<?> entity, StringBuilder sqlColumn, StringBuilder sqlWhere, String paramPrefix) {	
        String[] a;	
        boolean a2 = false;	
        StringBuilder a3 = new StringBuilder();	
        ArrayList<Column> a4 = ListUtils.newArrayList();	
        for (Column a5 : MapperHelper.getColumns(t, a4)) {	
            void a6;	
            String a7 = MapperHelper.getAttrName(a5);	
            if (StringUtils.equals(a5.name(), "update_by")) {	
                ReflectUtils.invokeMethod(entity, "preUpdate", null, null);	
            }	
            if (a5.isPK()) {	
                if (sqlWhere == null) continue;	
                this.addWhere(sqlWhere, a5, paramPrefix, a7);	
                continue;	
            }	
            String[] arrstring = new String[3];	
            arrstring[0] = "update_by";	
            arrstring[1] = "update_date";	
            arrstring[2] = "status";	
            if (!StringUtils.inString(a5.name(), arrstring) || sqlColumn == null) continue;	
            Object e2 = ReflectUtils.invokeGetter(entity, a7);	
            this.addColumn(a3, a5, paramPrefix, a7, a6);	
            if (!"status".equals(a5.name())) continue;	
            a2 = true;	
        }	
        if (a2) {	
            sqlColumn.append(a3);	
        }	
        if (sqlWhere != null && (a = entity.getId_in()) != null && a.length > 0) {	
            sqlWhere = new StringBuilder();	
            sqlWhere.append(entity.getIdColumnName());	
            sqlWhere.append(" IN (");	
            int a5 = 0;	
            int n = a5;	
            while (n < a.length) {	
                if (a5 != 0) {	
                    sqlWhere.append(", ");	
                }	
                sqlWhere.append("#{sqlMap.where.");	
                sqlWhere.append(entity.getIdColumnName());	
                StringBuilder stringBuilder = new StringBuilder().append("#IN1.val[").append(a5);	
                sqlWhere.append(stringBuilder.append("]}").toString());	
                n = ++a5;	
            }	
            sqlWhere.append(")");	
        }	
    }	
	
    public String update(BaseEntity<?> entity) {	
        long a = System.currentTimeMillis();	
        StringBuilder a2 = new StringBuilder();	
        StringBuilder a3 = new StringBuilder();	
        StringBuilder a4 = new StringBuilder();	
        Table a5 = MapperHelper.getTable(entity);	
        ArrayList<Column> a6 = ListUtils.newArrayList();	
        for (Column a7 : MapperHelper.getColumns(a5, a6)) {	
            String a8 = MapperHelper.getAttrName(a7);	
            if (StringUtils.equals(a7.name(), "update_by")) {	
                ReflectUtils.invokeMethod(entity, "preUpdate", null, null);	
            }	
            if (a7.isPK()) {	
                this.addWhere(a3, a7, null, a8);	
                continue;	
            }	
            if (!a7.isUpdate() && !a7.isUpdateForce()) continue;	
            Object a9 = ReflectUtils.invokeGetter(entity, a8);	
            this.addColumn(a4, a7, null, a8, a9);	
        }	
        String[] a10 = entity.getId_in();	
        if (a10 != null && a10.length > 0) {	
            a3 = new StringBuilder();	
            a3.append(entity.getIdColumnName());	
            a3.append(" IN (");	
            int a7 = 0;	
            int n = a7;	
            while (n < a10.length) {	
                if (a7 != 0) {	
                    a3.append(", ");	
                }	
                a3.append("#{sqlMap.where.");	
                a3.append(entity.getIdColumnName());	
                StringBuilder stringBuilder = new StringBuilder().append("#IN1.val[").append(a7);	
                a3.append(stringBuilder.append("]}").toString());	
                n = ++a7;	
            }	
            a3.append(")");	
        }	
        if (a3.length() == 0) {	
            throw new MapperException(new StringBuilder().insert(0, "Error: ").append(entity.getClass()).append(" 没有is:k字段.").toString());	
        }	
        MapperHelper.addExtWhere(a3, entity, a5);	
        if (a4.length() == 0) {	
            throw new MapperException(new StringBuilder().insert(0, "Error: ").append(entity.getClass()).append(" 没有isUpdate字段.").toString());	
        }	
        a2.append("UPDATE ");	
        a2.append(MapperHelper.getTableName(a5, entity));	
        a2.append(" SET ");	
        a2.append(a4);	
        a2.append(" WHERE ");	
        a2.append(a3);	
        String a11 = a2.toString();	
        if (this.logger.isDebugEnabled()) {	
            this.logger.debug(new StringBuilder().insert(0, TimeUtils.formatDateAgo(System.currentTimeMillis() - a)).append(": ").append(a11).toString());	
        }	
        return a11;	
    }	
	
    public String deleteByEntity(BaseEntity<?> entity) {	
        StringBuilder stringBuilder;	
        long a = System.currentTimeMillis();	
        StringBuilder a2 = new StringBuilder();	
        StringBuilder a3 = new StringBuilder();	
        StringBuilder a4 = new StringBuilder();	
        Table a5 = MapperHelper.getTable(entity);	
        StringBuilder stringBuilder2 = a3;	
        stringBuilder2.append(entity.getSqlMap().getWhere().toSql(null, null));	
        if (stringBuilder2.length() == 0) {	
            throw new MapperException(new StringBuilder().insert(0, "Error: ").append(entity.getClass()).append(" 没有Where条件.").toString());	
        }	
        if (entity instanceof DataEntity) {	
            ((DataEntity)entity).setStatus("1");	
        }	
        this.addUpdateStatus(a5, entity, a4, null, null);	
        if (a4.length() == 0) {	
            StringBuilder stringBuilder3 = a2;	
            a2.append(MapperHelper.getTableName(a5, entity));	
            a2.append("DELETE FROM ");	
            stringBuilder = stringBuilder3;	
            stringBuilder3.append(" WHERE ");	
            a2.append(a3);	
        } else {	
            StringBuilder stringBuilder4 = a2;	
            stringBuilder = stringBuilder4;	
            stringBuilder4.append("UPDATE ");	
            a2.append(MapperHelper.getTableName(a5, entity));	
            a2.append(" SET ");	
            a2.append(a4);	
            a2.append(" WHERE ");	
            a2.append(a3);	
        }	
        String a6 = stringBuilder.toString();	
        if (this.logger.isDebugEnabled()) {	
            this.logger.debug(new StringBuilder().insert(0, TimeUtils.formatDateAgo(System.currentTimeMillis() - a)).append(": ").append(a6).toString());	
        }	
        return a6;	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    public String phyDeleteByEntity(BaseEntity<?> entity) {	
        void a;	
        long a2 = System.currentTimeMillis();	
        StringBuilder a3 = new StringBuilder();	
        StringBuilder a4 = new StringBuilder();	
        Table a5 = MapperHelper.getTable(entity);	
        StringBuilder stringBuilder = a4;	
        stringBuilder.append(entity.getSqlMap().getWhere().toSql(null, null));	
        if (stringBuilder.length() == 0) {	
            throw new MapperException(new StringBuilder().insert(0, "Error: ").append(entity.getClass()).append(" 没有Where条件.").toString());	
        }	
        a3.append("DELETE FROM ");	
        a3.append(MapperHelper.getTableName(a5, entity));	
        a3.append(" WHERE ");	
        a3.append(a4);	
        String string = a3.toString();	
        if (this.logger.isDebugEnabled()) {	
            this.logger.debug(new StringBuilder().insert(0, TimeUtils.formatDateAgo(System.currentTimeMillis() - a2)).append(": ").append((String)a).toString());	
        }	
        return a;	
    }	
}	
	
