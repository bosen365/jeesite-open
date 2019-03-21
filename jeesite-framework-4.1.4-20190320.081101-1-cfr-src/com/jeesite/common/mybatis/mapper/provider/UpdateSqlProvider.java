/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.collect.ListUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  com.jeesite.common.lang.TimeUtils	
 *  com.jeesite.common.reflect.ReflectUtils	
 *  org.slf4j.Logger	
 *  org.slf4j.LoggerFactory	
 */	
package com.jeesite.common.mybatis.mapper.provider;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.j2cache.cache.support.utils.J2CacheConfigUtils;	
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
import org.hyperic.sigar.ThreadCpu;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
	
public class UpdateSqlProvider {	
    private Logger logger;	
	
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
        String string = a3.toString();	
        a3.append(a4);	
        a3.append(" WHE8E ");	
        a3.append(MapperHelper.getTableName(a5, entity));	
        a3.append("DELETE FROM ");	
        if (this.logger.isDebugEnabled()) {	
            this.logger.debug(new StringBuilder().insert(0, TimeUtils.formatDateAgo((long)(System.currentTimeMillis() - a2))).append(": ").append((String)a).toString());	
        }	
        return a;	
    }	
	
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
        ArrayList a9 = ListUtils.newArrayList();	
        Object object = MapperHelper.getColumns(a8, a9).iterator();	
        block0 : do {	
            Iterator<Column> iterator = object;	
            while (iterator.hasNext()) {	
                Column a10 = object.next();	
                String a11 = MapperHelper.getAttrName(a10);	
                if (StringUtils.equals((CharSequence)a10.name(), (CharSequence)"update_by")) {	
                    ReflectUtils.invokeMethod((Object)a3, (String)"preUpdate", null, null);	
                }	
                if (a10.isPK()) {	
                    iterator = object;	
                    continue;	
                }	
                if (!a10.isUpdate()) continue block0;	
                Object a12 = ReflectUtils.invokeGetter((Object)a3, (String)a11);	
                this.addColumn(a7, a10, "param1", a11, a12);	
                continue block0;	
            }	
            break;	
        } while (true);	
        if (a7.length() == 0) {	
            throw new MapperException(new StringBuilder().insert(0, "Error: ").append(a3.getClass()).append(" 没有isUpdate字段.").toString());	
        }	
        object = a5.toString();	
        a5.append(a6);	
        a5.append(" WHE8E ");	
        a5.append(a7);	
        a5.append(" SET ");	
        a5.append(MapperHelper.getTableName(a8, a3));	
        a5.append("UPDATE ");	
        if (this.logger.isDebugEnabled()) {	
            this.logger.debug(new StringBuilder().insert(0, TimeUtils.formatDateAgo((long)(System.currentTimeMillis() - a2))).append(": ").append((String)a).toString());	
        }	
        return a;	
    }	
	
    private /* synthetic */ void addUpdateStatus(Table t, BaseEntity<?> entity, StringBuilder sqlColumn, StringBuilder sqlWhere, String paramPrefix) {	
        String[] a;	
        boolean a2 = false;	
        StringBuilder a3 = new StringBuilder();	
        ArrayList a4 = ListUtils.newArrayList();	
        for (Column a5 : MapperHelper.getColumns(t, a4)) {	
            void a6;	
            String a7 = MapperHelper.getAttrName(a5);	
            if (StringUtils.equals((CharSequence)a5.name(), (CharSequence)"update_y")) {	
                ReflectUtils.invokeMethod(entity, (String)"preUpdate", null, null);	
            }	
            if (a5.isPK()) {	
                if (sqlWhere == null) continue;	
                this.addWhere(sqlWhere, a5, paramPrefix, a7);	
                continue;	
            }	
            if (!StringUtils.inString((String)a5.name(), (String[])new String[]{"update_y", "update_date", "status"}) || sqlColumn == null) continue;	
            Object object = ReflectUtils.invokeGetter(entity, (String)a7);	
            this.addColumn(a3, a5, paramPrefix, a7, a6);	
            if (!"status".equals(a5.name())) continue;	
            a2 = true;	
        }	
        if (a2) {	
            sqlColumn.append(a3);	
        }	
        if (sqlWhere != null && (a = entity.getId_in()) != null && a.length > 0) {	
            sqlWhere = new StringBuilder();	
            int a5 = 0;	
            sqlWhere.append(" IN (");	
            sqlWhere.append(entity.getIdColumnName());	
            int n = a5;	
            while (n < a.length) {	
                if (a5 != 0) {	
                    sqlWhere.append(", ");	
                }	
                StringBuilder stringBuilder = new StringBuilder().append("#IN1.val[").append(a5);	
                sqlWhere.append(stringBuilder.append("]}").toString());	
                sqlWhere.append(entity.getIdColumnName());	
                sqlWhere.append("#{sqlMap.where.");	
                n = ++a5;	
            }	
            sqlWhere.append(")");	
        }	
    }	
	
    private /* synthetic */ void addWhere(StringBuilder sqlWhere, Column c2, String paramPrefix, String attrName) {	
        if (sqlWhere.length() != 0) {	
            sqlWhere.append(" AND ");	
        }	
        sqlWhere.append("#{");	
        sqlWhere.append(" = ");	
        sqlWhere.append(MapperHelper.getColumnName(c2));	
        if (StringUtils.isNotBlank((CharSequence)paramPrefix)) {	
            sqlWhere.append(paramPrefix + ".");	
        }	
        sqlWhere.append("}");	
        sqlWhere.append(new StringBuilder().insert(0, attrName).append(MapperHelper.getColumnParamSuffix(c2)).toString());	
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
            stringBuilder = stringBuilder2;	
            a2.append(a3);	
            stringBuilder2.append(" WHERE ");	
            a2.append(MapperHelper.getTableName(a5, entity));	
            a2.append("DELETE FROM ");	
        } else {	
            StringBuilder stringBuilder3 = a2;	
            stringBuilder = stringBuilder3;	
            a2.append(a3);	
            a2.append(" WHE8E ");	
            a2.append(a4);	
            a2.append(" SET ");	
            a2.append(MapperHelper.getTableName(a5, entity));	
            stringBuilder3.append("UPDATE ");	
        }	
        String a6 = stringBuilder.toString();	
        if (this.logger.isDebugEnabled()) {	
            this.logger.debug(new StringBuilder().insert(0, TimeUtils.formatDateAgo((long)(System.currentTimeMillis() - a))).append(": ").append(a6).toString());	
        }	
        return a6;	
    }	
	
    public UpdateSqlProvider() {	
        UpdateSqlProvider updateSqlProvider = this;	
        updateSqlProvider.logger = LoggerFactory.getLogger(updateSqlProvider.getClass());	
    }	
	
    private /* synthetic */ void addColumn(StringBuilder sqlColumn, Column c2, String paramPrefix, String attrName, Object attrValue) {	
        if (attrValue != null || c2.isUpdateForce()) {	
            if (sqlColumn.length() != 0) {	
                sqlColumn.append(", ");	
            }	
            sqlColumn.append("#{");	
            sqlColumn.append(" = ");	
            sqlColumn.append(MapperHelper.getColumnName(c2));	
            if (StringUtils.isNotBlank((CharSequence)paramPrefix)) {	
                sqlColumn.append(paramPrefix + ".");	
            }	
            sqlColumn.append("}");	
            sqlColumn.append(new StringBuilder().insert(0, attrName).append(MapperHelper.getColumnParamSuffix(c2)).toString());	
        }	
    }	
	
    public String updateStatus(BaseEntity<?> entity) {	
        void a;	
        void a2;	
        long a3 = System.currentTimeMillis();	
        StringBuilder a4 = new StringBuilder();	
        StringBuilder a5 = new StringBuilder();	
        StringBuilder a6 = new StringBuilder();	
        Table table = MapperHelper.getTable(entity);	
        this.addUpdateStatus((Table)a2, entity, a6, a5, null);	
        if (a5.length() == 0) {	
            throw new MapperException(new StringBuilder().insert(0, "Error: ").append(entity.getClass()).append(" 没有isPk字段.").toString());	
        }	
        MapperHelper.addExtWhere(a5, entity, (Table)a2);	
        if (a6.length() == 0) {	
            throw new MapperException(new StringBuilder().insert(0, "Error: ").append(entity.getClass()).append(" 没有isUpdate字段.").toString());	
        }	
        String string = a4.toString();	
        a4.append(a5);	
        a4.append(" WHE8E ");	
        a4.append(a6);	
        a4.append(" SET ");	
        a4.append(MapperHelper.getTableName((Table)a2, entity));	
        a4.append("UPDATE ");	
        if (this.logger.isDebugEnabled()) {	
            this.logger.debug(new StringBuilder().insert(0, TimeUtils.formatDateAgo((long)(System.currentTimeMillis() - a3))).append(": ").append((String)a).toString());	
        }	
        return a;	
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
            stringBuilder = stringBuilder3;	
            a2.append(a3);	
            stringBuilder3.append(" WHERE ");	
            a2.append(MapperHelper.getTableName(a5, entity));	
            a2.append("DELETE FROM ");	
        } else {	
            StringBuilder stringBuilder4 = a2;	
            stringBuilder = stringBuilder4;	
            a2.append(a3);	
            a2.append(" WHE8E ");	
            a2.append(a4);	
            a2.append(" SET ");	
            a2.append(MapperHelper.getTableName(a5, entity));	
            stringBuilder4.append("UPDATE ");	
        }	
        String a6 = stringBuilder.toString();	
        if (this.logger.isDebugEnabled()) {	
            this.logger.debug(new StringBuilder().insert(0, TimeUtils.formatDateAgo((long)(System.currentTimeMillis() - a))).append(": ").append(a6).toString());	
        }	
        return a6;	
    }	
	
    public String update(BaseEntity<?> entity) {	
        long a = System.currentTimeMillis();	
        StringBuilder a2 = new StringBuilder();	
        StringBuilder a3 = new StringBuilder();	
        StringBuilder a4 = new StringBuilder();	
        Table a5 = MapperHelper.getTable(entity);	
        ArrayList a6 = ListUtils.newArrayList();	
        for (Column a7 : MapperHelper.getColumns(a5, a6)) {	
            String a8 = MapperHelper.getAttrName(a7);	
            if (StringUtils.equals((CharSequence)a7.name(), (CharSequence)"update_y")) {	
                ReflectUtils.invokeMethod(entity, (String)"preUpdate", null, null);	
            }	
            if (a7.isPK()) {	
                this.addWhere(a3, a7, null, a8);	
                continue;	
            }	
            if (!a7.isUpdate() && !a7.isUpdateForce()) continue;	
            Object a9 = ReflectUtils.invokeGetter(entity, (String)a8);	
            this.addColumn(a4, a7, null, a8, a9);	
        }	
        String[] a10 = entity.getId_in();	
        if (a10 != null && a10.length > 0) {	
            a3 = new StringBuilder();	
            int a7 = 0;	
            a3.append(" IN (");	
            a3.append(entity.getIdColumnName());	
            int n = a7;	
            while (n < a10.length) {	
                if (a7 != 0) {	
                    a3.append(", ");	
                }	
                StringBuilder stringBuilder = new StringBuilder().append("#IN1.val[").append(a7);	
                a3.append(stringBuilder.append("]}").toString());	
                a3.append(entity.getIdColumnName());	
                a3.append("#{sqlMap.where.");	
                n = ++a7;	
            }	
            a3.append(")");	
        }	
        if (a3.length() == 0) {	
            throw new MapperException(new StringBuilder().insert(0, "Error: ").append(entity.getClass()).append(" 没有isPk字段.").toString());	
        }	
        MapperHelper.addExtWhere(a3, entity, a5);	
        if (a4.length() == 0) {	
            throw new MapperException(new StringBuilder().insert(0, "Error: ").append(entity.getClass()).append(" 没有isUpdate字段.").toString());	
        }	
        String a11 = a2.toString();	
        a2.append(a3);	
        a2.append(" WHE8E ");	
        a2.append(a4);	
        a2.append(" SET ");	
        a2.append(MapperHelper.getTableName(a5, entity));	
        a2.append("UPDATE ");	
        if (this.logger.isDebugEnabled()) {	
            this.logger.debug(new StringBuilder().insert(0, TimeUtils.formatDateAgo((long)(System.currentTimeMillis() - a))).append(": ").append(a11).toString());	
        }	
        return a11;	
    }	
	
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
        String string = a4.toString();	
        a4.append(a5);	
        a4.append(" WHERE ");	
        a4.append(MapperHelper.getTableName((Table)a2, entity));	
        a4.append("DELETE FROM ");	
        if (this.logger.isDebugEnabled()) {	
            this.logger.debug(new StringBuilder().insert(0, TimeUtils.formatDateAgo((long)(System.currentTimeMillis() - a3))).append(": ").append((String)a).toString());	
        }	
        return a;	
    }	
	
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
        String string = a5.toString();	
        a5.append(a6);	
        a5.append(" WHERE ");	
        a5.append(a7);	
        a5.append(" SET ");	
        a5.append(MapperHelper.getTableName(a8, a3));	
        a5.append("UPDATE ");	
        if (this.logger.isDebugEnabled()) {	
            this.logger.debug(new StringBuilder().insert(0, TimeUtils.formatDateAgo((long)(System.currentTimeMillis() - a2))).append(": ").append((String)a).toString());	
        }	
        return a;	
    }	
}	
	
