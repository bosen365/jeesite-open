/*	
 * Decompiled with CFR 0.139.	
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
import com.jeesite.common.j.E;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.lang.TimeUtils;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.MapperException;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.common.mybatis.mapper.SqlMap;	
import com.jeesite.common.mybatis.mapper.query.QueryWhere;	
import com.jeesite.common.reflect.ReflectUtils;	
import com.jeesite.modules.gen.entity.config.GenDict;	
import java.util.ArrayList;	
import java.util.Iterator;	
import java.util.Map;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
	
public class UpdateSqlProvider {	
    private Logger logger;	
	
    public String updateByEntity(Map<String, ?> params) {	
        void a2;	
        long a3 = System.currentTimeMillis();	
        BaseEntity a4 = (BaseEntity)params.get("param1");	
        BaseEntity a5 = (BaseEntity)params.get("param2");	
        StringBuilder a6 = new StringBuilder();	
        StringBuilder a7 = new StringBuilder();	
        StringBuilder a8 = new StringBuilder();	
        StringBuilder stringBuilder = a7;	
        stringBuilder.append(a5.getSqlMap().getWhere().toSql(null, "param2"));	
        if (stringBuilder.length() == 0) {	
            throw new MapperException(new StringBuilder().insert(0, "Error: ").append(a4.getClass()).append(" 没有Where字段.").toString());	
        }	
        Table a9 = MapperHelper.getTable(a4);	
        ArrayList a10 = ListUtils.newArrayList();	
        Object object = MapperHelper.getColumns(a9, a10).iterator();	
        block0 : do {	
            Iterator<Column> iterator = object;	
            while (iterator.hasNext()) {	
                Column a11 = object.next();	
                String a12 = MapperHelper.getAttrName(a11);	
                if (StringUtils.equals((CharSequence)a11.name(), (CharSequence)"update_by")) {	
                    ReflectUtils.invokeMethod((Object)a4, (String)"preUpdate", null, null);	
                }	
                if (a11.isPK()) {	
                    iterator = object;	
                    continue;	
                }	
                if (!a11.isUpdate()) continue block0;	
                Object a13 = ReflectUtils.invokeGetter((Object)a4, (String)a12);	
                this.addColumn(a8, a11, "param1", a12, a13);	
                continue block0;	
            }	
            break;	
        } while (true);	
        if (a8.length() == 0) {	
            throw new MapperException(new StringBuilder().insert(0, "Error: ").append(a4.getClass()).append(" 没有isUpdate字段.").toString());	
        }	
        object = a6.toString();	
        a6.append(a7);	
        a6.append(" WHERE ");	
        a6.append(a8);	
        a6.append(" SET ");	
        a6.append(MapperHelper.getTableName(a9, a4));	
        a6.append("UPDATE ");	
        if (this.logger.isDebugEnabled()) {	
            this.logger.debug(new StringBuilder().insert(0, TimeUtils.formatDateAgo((long)(System.currentTimeMillis() - a3))).append(": ").append((String)a2).toString());	
        }	
        return a2;	
    }	
	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = 2 << 3 ^ 3;	
        int n4 = n2;	
        int n5 = 5 << 3 ^ 1;	
        (3 ^ 5) << 4 ^ 5 << 1;	
        while (n4 >= 0) {	
            int n6 = n2--;	
            arrc[n6] = (char)(s.charAt(n6) ^ n5);	
            if (n2 < 0) break;	
            int n7 = n2--;	
            arrc[n7] = (char)(s.charAt(n7) ^ n3);	
            n4 = n2;	
        }	
        return new String(arrc);	
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
	
    public String updateStatusByEntity(Map<String, ?> params) {	
        void a2;	
        long a3 = System.currentTimeMillis();	
        BaseEntity a4 = (BaseEntity)params.get("param1");	
        BaseEntity a5 = (BaseEntity)params.get("param2");	
        StringBuilder a6 = new StringBuilder();	
        StringBuilder a7 = new StringBuilder();	
        StringBuilder a8 = new StringBuilder();	
        Table a9 = MapperHelper.getTable(a4);	
        StringBuilder stringBuilder = a7;	
        stringBuilder.append(a5.getSqlMap().getWhere().toSql(null, "param2"));	
        if (stringBuilder.length() == 0) {	
            throw new MapperException(new StringBuilder().insert(0, "Error: ").append(a4.getClass()).append(" 没有Where条件.").toString());	
        }	
        this.addUpdateStatus(a9, a4, a8, null, "param1");	
        if (a8.length() == 0) {	
            throw new MapperException(new StringBuilder().insert(0, "Error: ").append(a4.getClass()).append(" 没有isUpdate字段.").toString());	
        }	
        String string = a6.toString();	
        a6.append(a7);	
        a6.append(" WHERE ");	
        a6.append(a8);	
        a6.append(" SET ");	
        a6.append(MapperHelper.getTableName(a9, a4));	
        a6.append("UPDATE ");	
        if (this.logger.isDebugEnabled()) {	
            this.logger.debug(new StringBuilder().insert(0, TimeUtils.formatDateAgo((long)(System.currentTimeMillis() - a3))).append(": ").append((String)a2).toString());	
        }	
        return a2;	
    }	
	
    public String phyDelete(BaseEntity<?> entity) {	
        void a2;	
        void a3;	
        long a4 = System.currentTimeMillis();	
        StringBuilder a5 = new StringBuilder();	
        StringBuilder a6 = new StringBuilder();	
        Table table = MapperHelper.getTable(entity);	
        this.addUpdateStatus((Table)a3, entity, null, a6, null);	
        if (a6.length() == 0) {	
            throw new MapperException(new StringBuilder().insert(0, "Error: ").append(entity.getClass()).append(" 没有isPk字段.").toString());	
        }	
        MapperHelper.addExtWhere(a6, entity, (Table)a3);	
        String string = a5.toString();	
        a5.append(a6);	
        a5.append(" WHERE ");	
        a5.append(MapperHelper.getTableName((Table)a3, entity));	
        a5.append("DELETE FROM ");	
        if (this.logger.isDebugEnabled()) {	
            this.logger.debug(new StringBuilder().insert(0, TimeUtils.formatDateAgo((long)(System.currentTimeMillis() - a4))).append(": ").append((String)a2).toString());	
        }	
        return a2;	
    }	
	
    public String deleteByEntity(BaseEntity<?> entity) {	
        StringBuilder stringBuilder;	
        long a2 = System.currentTimeMillis();	
        StringBuilder a3 = new StringBuilder();	
        StringBuilder a4 = new StringBuilder();	
        StringBuilder a5 = new StringBuilder();	
        Table a6 = MapperHelper.getTable(entity);	
        StringBuilder stringBuilder2 = a4;	
        stringBuilder2.append(entity.getSqlMap().getWhere().toSql(null, null));	
        if (stringBuilder2.length() == 0) {	
            throw new MapperException(new StringBuilder().insert(0, "Error: ").append(entity.getClass()).append(" 没有Where条件.").toString());	
        }	
        if (entity instanceof DataEntity) {	
            ((DataEntity)entity).setStatus("1");	
        }	
        this.addUpdateStatus(a6, entity, a5, null, null);	
        if (a5.length() == 0) {	
            StringBuilder stringBuilder3 = a3;	
            stringBuilder = stringBuilder3;	
            a3.append(a4);	
            stringBuilder3.append(" WHERE ");	
            a3.append(MapperHelper.getTableName(a6, entity));	
            a3.append("DELETE FROM ");	
        } else {	
            StringBuilder stringBuilder4 = a3;	
            stringBuilder = stringBuilder4;	
            a3.append(a4);	
            a3.append(" WHERE ");	
            a3.append(a5);	
            a3.append(" SET ");	
            a3.append(MapperHelper.getTableName(a6, entity));	
            stringBuilder4.append("UPDATE ");	
        }	
        String a7 = stringBuilder.toString();	
        if (this.logger.isDebugEnabled()) {	
            this.logger.debug(new StringBuilder().insert(0, TimeUtils.formatDateAgo((long)(System.currentTimeMillis() - a2))).append(": ").append(a7).toString());	
        }	
        return a7;	
    }	
	
    private /* synthetic */ void addUpdateStatus(Table t, BaseEntity<?> entity, StringBuilder sqlColumn, StringBuilder sqlWhere, String paramPrefix) {	
        String[] a2;	
        boolean a3 = false;	
        StringBuilder a4 = new StringBuilder();	
        ArrayList a5 = ListUtils.newArrayList();	
        for (Column a6 : MapperHelper.getColumns(t, a5)) {	
            void a7;	
            String a8 = MapperHelper.getAttrName(a6);	
            if (StringUtils.equals((CharSequence)a6.name(), (CharSequence)"update_by")) {	
                ReflectUtils.invokeMethod(entity, (String)"preUpdate", null, null);	
            }	
            if (a6.isPK()) {	
                if (sqlWhere == null) continue;	
                this.addWhere(sqlWhere, a6, paramPrefix, a8);	
                continue;	
            }	
            if (!StringUtils.inString((String)a6.name(), (String[])new String[]{"update_by", "update_date", "status"}) || sqlColumn == null) continue;	
            Object object = ReflectUtils.invokeGetter(entity, (String)a8);	
            this.addColumn(a4, a6, paramPrefix, a8, a7);	
            if (!"status".equals(a6.name())) continue;	
            a3 = true;	
        }	
        if (a3) {	
            sqlColumn.append(a4);	
        }	
        if (sqlWhere != null && (a2 = entity.getId_in()) != null && a2.length > 0) {	
            sqlWhere = new StringBuilder();	
            int a6 = 0;	
            sqlWhere.append(" IN (");	
            sqlWhere.append(entity.getIdColumnName());	
            int n = a6;	
            while (n < a2.length) {	
                if (a6 != 0) {	
                    sqlWhere.append(", ");	
                }	
                StringBuilder stringBuilder = new StringBuilder().append("#IN1.val[").append(a6);	
                sqlWhere.append(stringBuilder.append("]}").toString());	
                sqlWhere.append(entity.getIdColumnName());	
                sqlWhere.append("#{sqlMap.where.");	
                n = ++a6;	
            }	
            sqlWhere.append(")");	
        }	
    }	
	
    public String delete(BaseEntity<?> entity) {	
        StringBuilder stringBuilder;	
        long a2 = System.currentTimeMillis();	
        StringBuilder a3 = new StringBuilder();	
        StringBuilder a4 = new StringBuilder();	
        StringBuilder a5 = new StringBuilder();	
        BaseEntity<?> baseEntity = entity;	
        Table a6 = MapperHelper.getTable(baseEntity);	
        if (baseEntity instanceof DataEntity) {	
            ((DataEntity)entity).setStatus("1");	
        }	
        this.addUpdateStatus(a6, entity, a5, a4, null);	
        if (a4.length() == 0) {	
            throw new MapperException(new StringBuilder().insert(0, "Error: ").append(entity.getClass()).append(" 没有isPk字段.").toString());	
        }	
        MapperHelper.addExtWhere(a4, entity, a6);	
        if (a5.length() == 0) {	
            StringBuilder stringBuilder2 = a3;	
            stringBuilder = stringBuilder2;	
            a3.append(a4);	
            stringBuilder2.append(" WHERE ");	
            a3.append(MapperHelper.getTableName(a6, entity));	
            a3.append("DELETE FROM ");	
        } else {	
            StringBuilder stringBuilder3 = a3;	
            stringBuilder = stringBuilder3;	
            a3.append(a4);	
            a3.append(" WHERE ");	
            a3.append(a5);	
            a3.append(" SET ");	
            a3.append(MapperHelper.getTableName(a6, entity));	
            stringBuilder3.append("UPDATE ");	
        }	
        String a7 = stringBuilder.toString();	
        if (this.logger.isDebugEnabled()) {	
            this.logger.debug(new StringBuilder().insert(0, TimeUtils.formatDateAgo((long)(System.currentTimeMillis() - a2))).append(": ").append(a7).toString());	
        }	
        return a7;	
    }	
	
    private /* synthetic */ void addColumn(StringBuilder sqlColumn, Column c2, String paramPrefix, String attrName, Object attrValue) {	
        if (attrValue != null || c2.isUpdateForce()) {	
            if (sqlColumn.length() != 0) {	
                sqlColumn.append(", ");	
            }	
            sqlColumn.append("#");	
            sqlColumn.append(" = ");	
            sqlColumn.append(MapperHelper.getColumnName(c2));	
            if (StringUtils.isNotBlank((CharSequence)paramPrefix)) {	
                sqlColumn.append(paramPrefix + ".");	
            }	
            sqlColumn.append("}");	
            sqlColumn.append(new StringBuilder().insert(0, attrName).append(MapperHelper.getColumnParamSuffix(c2)).toString());	
        }	
    }	
	
    public String update(BaseEntity<?> entity) {	
        long a2 = System.currentTimeMillis();	
        StringBuilder a3 = new StringBuilder();	
        StringBuilder a4 = new StringBuilder();	
        StringBuilder a5 = new StringBuilder();	
        Table a6 = MapperHelper.getTable(entity);	
        ArrayList a7 = ListUtils.newArrayList();	
        for (Column a8 : MapperHelper.getColumns(a6, a7)) {	
            String a9 = MapperHelper.getAttrName(a8);	
            if (StringUtils.equals((CharSequence)a8.name(), (CharSequence)"update_by")) {	
                ReflectUtils.invokeMethod(entity, (String)"preUpdate", null, null);	
            }	
            if (a8.isPK()) {	
                this.addWhere(a4, a8, null, a9);	
                continue;	
            }	
            if (!a8.isUpdate() && !a8.isUpdateForce()) continue;	
            Object a10 = ReflectUtils.invokeGetter(entity, (String)a9);	
            this.addColumn(a5, a8, null, a9, a10);	
        }	
        String[] a11 = entity.getId_in();	
        if (a11 != null && a11.length > 0) {	
            a4 = new StringBuilder();	
            int a8 = 0;	
            a4.append(" IN (");	
            a4.append(entity.getIdColumnName());	
            int n = a8;	
            while (n < a11.length) {	
                if (a8 != 0) {	
                    a4.append(", ");	
                }	
                StringBuilder stringBuilder = new StringBuilder().append("#IN1.val[").append(a8);	
                a4.append(stringBuilder.append("]}").toString());	
                a4.append(entity.getIdColumnName());	
                a4.append("#{sqlMap.where.");	
                n = ++a8;	
            }	
            a4.append(")");	
        }	
        if (a4.length() == 0) {	
            throw new MapperException(new StringBuilder().insert(0, "Error: ").append(entity.getClass()).append(" 没有isPk字段.").toString());	
        }	
        MapperHelper.addExtWhere(a4, entity, a6);	
        if (a5.length() == 0) {	
            throw new MapperException(new StringBuilder().insert(0, "Error: ").append(entity.getClass()).append(" 没有isUpdate字段.").toString());	
        }	
        String a12 = a3.toString();	
        a3.append(a4);	
        a3.append(" WHERE ");	
        a3.append(a5);	
        a3.append(" SET ");	
        a3.append(MapperHelper.getTableName(a6, entity));	
        a3.append("UPDATE ");	
        if (this.logger.isDebugEnabled()) {	
            this.logger.debug(new StringBuilder().insert(0, TimeUtils.formatDateAgo((long)(System.currentTimeMillis() - a2))).append(": ").append(a12).toString());	
        }	
        return a12;	
    }	
	
    public UpdateSqlProvider() {	
        UpdateSqlProvider updateSqlProvider = this;	
        updateSqlProvider.logger = LoggerFactory.getLogger(updateSqlProvider.getClass());	
    }	
	
    public String updateStatus(BaseEntity<?> entity) {	
        void a2;	
        void a3;	
        long a4 = System.currentTimeMillis();	
        StringBuilder a5 = new StringBuilder();	
        StringBuilder a6 = new StringBuilder();	
        StringBuilder a7 = new StringBuilder();	
        Table table = MapperHelper.getTable(entity);	
        this.addUpdateStatus((Table)a3, entity, a7, a6, null);	
        if (a6.length() == 0) {	
            throw new MapperException(new StringBuilder().insert(0, "Error: ").append(entity.getClass()).append(" 没有isPk字段.").toString());	
        }	
        MapperHelper.addExtWhere(a6, entity, (Table)a3);	
        if (a7.length() == 0) {	
            throw new MapperException(new StringBuilder().insert(0, "Error: ").append(entity.getClass()).append(" 没有isUpdate字段.").toString());	
        }	
        String string = a5.toString();	
        a5.append(a6);	
        a5.append(" WHERE ");	
        a5.append(a7);	
        a5.append(" SET ");	
        a5.append(MapperHelper.getTableName((Table)a3, entity));	
        a5.append("UPDATE ");	
        if (this.logger.isDebugEnabled()) {	
            this.logger.debug(new StringBuilder().insert(0, TimeUtils.formatDateAgo((long)(System.currentTimeMillis() - a4))).append(": ").append((String)a2).toString());	
        }	
        return a2;	
    }	
	
    public String phyDeleteByEntity(BaseEntity<?> entity) {	
        void a2;	
        long a3 = System.currentTimeMillis();	
        StringBuilder a4 = new StringBuilder();	
        StringBuilder a5 = new StringBuilder();	
        Table a6 = MapperHelper.getTable(entity);	
        StringBuilder stringBuilder = a5;	
        stringBuilder.append(entity.getSqlMap().getWhere().toSql(null, null));	
        if (stringBuilder.length() == 0) {	
            throw new MapperException(new StringBuilder().insert(0, "Error: ").append(entity.getClass()).append(" 没有Where条件.").toString());	
        }	
        String string = a4.toString();	
        a4.append(a5);	
        a4.append(" WHERE ");	
        a4.append(MapperHelper.getTableName(a6, entity));	
        a4.append("DELETE FROM ");	
        if (this.logger.isDebugEnabled()) {	
            this.logger.debug(new StringBuilder().insert(0, TimeUtils.formatDateAgo((long)(System.currentTimeMillis() - a3))).append(": ").append((String)a2).toString());	
        }	
        return a2;	
    }	
}	
	
