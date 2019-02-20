/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.collect.ListUtils	
 *  com.jeesite.common.collect.MapUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  com.jeesite.common.reflect.ReflectUtils	
 *  org.apache.commons.lang3.BooleanUtils	
 */	
package com.jeesite.common.mybatis.mapper.query;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.entity.TreeEntity;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.JoinTable;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.MapperException;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.common.mybatis.mapper.SqlMap;	
import com.jeesite.common.mybatis.mapper.query.QueryAndor;	
import com.jeesite.common.mybatis.mapper.query.QueryType;	
import com.jeesite.common.mybatis.mapper.query.QueryWhereEntity;	
import com.jeesite.common.reflect.ReflectUtils;	
import com.jeesite.common.shiro.cas.CasOutHandler;	
import java.util.ArrayList;	
import java.util.HashMap;	
import java.util.Iterator;	
import java.util.LinkedHashMap;	
import java.util.Map;	
import java.util.Set;	
import org.apache.commons.lang3.BooleanUtils;	
import org.hyperic.sigar.FileSystemMap;	
	
public class QueryWhere	
extends LinkedHashMap<String, QueryWhereEntity> {	
    private boolean disableAutoAddStatusWhere;	
    private boolean disableAutoAddCorpCodeWhere;	
    private BaseEntity<?> entity;	
    private static final long serialVersionUID = 1L;	
	
    public QueryWhere andBracket(String columnName, QueryType queryType, Object value) {	
        QueryWhere queryWhere = this;	
        queryWhere.add(QueryAndor.AND_BRACKET, columnName, queryType, value, 1);	
        return queryWhere;	
    }	
	
    public QueryWhere or(String columnName, QueryType queryType, Object value) {	
        QueryWhere queryWhere = this;	
        queryWhere.add(QueryAndor.OR, columnName, queryType, value, 1);	
        return queryWhere;	
    }	
	
    private /* synthetic */ QueryWhere add(QueryAndor andor, String columnName, QueryType queryType, Object value, Integer num) {	
        if (andor == null) {	
            return this;	
        }	
        String a2 = new StringBuilder().insert(0, columnName).append("#").append((Object)queryType).append(num).toString();	
        QueryWhere queryWhere = this;	
        String string = a2;	
        String string2 = a2;	
        queryWhere.put(string2, new QueryWhereEntity(string2, andor, columnName, queryType, value));	
        return queryWhere;	
    }	
	
    public QueryWhere and(String columnName, QueryType queryType, Object value, Integer num) {	
        QueryWhere queryWhere = this;	
        queryWhere.add(QueryAndor.AND, columnName, queryType, value, num);	
        return queryWhere;	
    }	
	
    private /* synthetic */ Map<String, Column> addEntityWhere(BaseEntity<?> entity, Object table, StringBuilder sql, String tableAlias, String paramPrefix, boolean isMainEntity) {	
        HashMap a2;	
        block22 : {	
            a2 = MapUtils.newHashMap();	
            if (entity == null) break block22;	
            boolean a3 = false;	
            ArrayList a4 = ListUtils.newArrayList();	
            Iterator<Column> iterator = MapperHelper.getColumns(table, a4).iterator();	
            block0 : do {	
                Iterator<Column> iterator2 = iterator;	
                while (iterator2.hasNext()) {	
                    boolean bl;	
                    String a5;	
                    boolean a6;	
                    Column a7;	
                    Object a8;	
                    QueryType a9;	
                    block25 : {	
                        block24 : {	
                            block23 : {	
                                Column column = a7 = iterator.next();	
                                a2.put(a7.name(), column);	
                                if (!column.isQuery()) continue block0;	
                                if (StringUtils.equals((CharSequence)a7.name(), (CharSequence)"corp_code")) {	
                                    if (!Global.isUseCorpModel().booleanValue()) {	
                                        iterator2 = iterator;	
                                        continue;	
                                    }	
                                    if (this.disableAutoAddCorpCodeWhere) {	
                                        iterator2 = iterator;	
                                        continue;	
                                    }	
                                }	
                                if (StringUtils.equals((CharSequence)a7.name(), (CharSequence)"corp_name")) {	
                                    iterator2 = iterator;	
                                    continue;	
                                }	
                                Column column2 = a7;	
                                a5 = MapperHelper.getAttrName(column2);	
                                a8 = ReflectUtils.invokeGetter(entity, (String)a5);	
                                a6 = false;	
                                a9 = column2.queryType();	
                                if (!(a8 instanceof String)) break block23;	
                                if (!StringUtils.isNotBlank((CharSequence)((String)a8)) && !a9.isForce().booleanValue()) break block24;	
                                a6 = true;	
                                bl = isMainEntity;	
                                break block25;	
                            }	
                            if (a8 != null) {	
                                a6 = true;	
                            }	
                        }	
                        bl = isMainEntity;	
                    }	
                    if (bl && "status".equals(a7.name()) && !a6 && !this.disableAutoAddStatusWhere) {	
                        if (sql.length() != 0) {	
                            sql.append(" AND ");	
                        }	
                        if (StringUtils.isNotBlank((CharSequence)tableAlias)) {	
                            sql.append(tableAlias + ".");	
                        }	
                        sql.append(new StringBuilder().insert(0, a7.name()).append(" != #{").toString());	
                        if (StringUtils.isNotBlank((CharSequence)paramPrefix)) {	
                            sql.append(new StringBuilder().insert(0, paramPrefix).append(".").toString());	
                        }	
                        sql.append("STATUS_DELETE}");	
                    }	
                    if (a7.isPK() && entity instanceof TreeEntity && BooleanUtils.toBoolean((Boolean)((TreeEntity)entity).getIsQueryChildren())) {	
                        a3 = true;	
                    }	
                    if (!a6) continue block0;	
                    if (QueryType.IN == a9 || QueryType.NOT_IN == a9 || QueryType.IS_NULL == a9 || QueryType.IS_NOT_NULL == a9) {	
                        throw new MapperException("@Column(queryType=暂时不支持IN、NOT_IN、IS_NULL、IS_NOT_NULL，请使用sqlMap.getWhere()实现。)");	
                    }	
                    if (a8 instanceof String) {	
                        String a10 = "";	
                        if (StringUtils.isNotBlank((CharSequence)a9.valuePrefix())) {	
                            a10 = new StringBuilder().insert(0, a10).append(a9.valuePrefix()).toString();	
                        }	
                        a10 = new StringBuilder().insert(0, a10).append(a8).toString();	
                        if (StringUtils.isNotBlank((CharSequence)a9.valueSuffux())) {	
                            a10 = new StringBuilder().insert(0, a10).append(a9.valueSuffux()).toString();	
                        }	
                        a5 = new StringBuilder().insert(0, "where#").append(a7.name()).append("#").append((Object)a9).append("1").toString();	
                        Object object = entity.getSqlMap().add(a5, a10);	
                        a5 = new StringBuilder().insert(0, "qlMap.").append(a5).toString();	
                    }	
                    if (sql.length() != 0) {	
                        sql.append(" AND ");	
                    }	
                    if (a7.isPK() && a3) {	
                        sql.append("(");	
                    }	
                    if (StringUtils.isNotBlank((CharSequence)tableAlias)) {	
                        sql.append(new StringBuilder().insert(0, tableAlias).append(".").toString());	
                    }	
                    sql.append("#{");	
                    sql.append(new StringBuilder().insert(0, a9.operator()).append(" ").toString());	
                    sql.append(new StringBuilder().insert(0, a7.name()).append(" ").toString());	
                    if (StringUtils.isNotBlank((CharSequence)paramPrefix)) {	
                        sql.append(new StringBuilder().insert(0, paramPrefix).append(".").toString());	
                    }	
                    sql.append("}");	
                    sql.append(new StringBuilder().insert(0, a5).append(MapperHelper.getColumnParamSuffix(a7)).toString());	
                    if (!a7.isPK() || !a3) continue block0;	
                    a5 = new StringBuilder().insert(0, "where#").append(a7.name()).append("#").append((Object)a9).append("1#ISQC").toString();	
                    Object object = entity.getSqlMap().add(a5, new StringBuilder().insert(0, "%,").append(a8).append(",%").toString());	
                    a5 = new StringBuilder().insert(0, "qlMap.").append(a5).toString();	
                    sql.append(" OR ");	
                    if (StringUtils.isNotBlank((CharSequence)tableAlias)) {	
                        sql.append(new StringBuilder().insert(0, tableAlias).append(".parent_code LIKE ").toString());	
                    }	
                    sql.append("#{");	
                    if (StringUtils.isNotBlank((CharSequence)paramPrefix)) {	
                        sql.append(new StringBuilder().insert(0, paramPrefix).append(".").toString());	
                    }	
                    sql.append(")");	
                    sql.append("}");	
                    sql.append(new StringBuilder().insert(0, a5).append(MapperHelper.getColumnParamSuffix(a7)).toString());	
                    continue block0;	
                }	
                break;	
            } while (true);	
        }	
        return a2;	
    }	
	
    public String toSql() {	
        QueryWhere queryWhere = this;	
        Table table = MapperHelper.getTable(queryWhere.entity);	
        return queryWhere.addWhere(table, table.alias(), null);	
    }	
	
    public QueryWhere andBracket(String columnName, QueryType queryType, Object value, Integer num) {	
        QueryWhere queryWhere = this;	
        queryWhere.add(QueryAndor.AND_BRACKET, columnName, queryType, value, num);	
        return queryWhere;	
    }	
	
    public QueryWhere endBracket() {	
        QueryWhere queryWhere = this;	
        queryWhere.add(QueryAndor.END_BRACKET, null, null, null, 1);	
        return queryWhere;	
    }	
	
    public String toSql(String tableAlias, String paramPrefix) {	
        QueryWhere queryWhere = this;	
        return queryWhere.addWhere(MapperHelper.getTable(queryWhere.entity), tableAlias, paramPrefix);	
    }	
	
    public <E> E getValue(String string, QueryType queryType, Integer n) {	
        void num;	
        void columnName;	
        void queryType2;	
        QueryWhereEntity a2 = (QueryWhereEntity)this.get((String)columnName + "#" + queryType2 + num);	
        if (a2 != null) {	
            return (E)a2.getValue();	
        }	
        return null;	
    }	
	
    public <E> E getValue(String columnName, QueryType queryType) {	
        return this.getValue(columnName, queryType, 1);	
    }	
	
    public QueryWhere disableAutoAddCorpCodeWhere() {	
        this.disableAutoAddCorpCodeWhere = true;	
        return this;	
    }	
	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = 5 << 4 ^ (2 ^ 5) << 1;	
        int n4 = n2;	
        5 << 3 ^ 1;	
        int n5 = (3 ^ 5) << 4 ^ 5;	
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
	
    public QueryWhere and(String columnName, QueryType queryType, Object value) {	
        QueryWhere queryWhere = this;	
        queryWhere.add(QueryAndor.AND, columnName, queryType, value, 1);	
        return queryWhere;	
    }	
	
    public QueryWhere disableAutoAddStatusWhere() {	
        this.disableAutoAddStatusWhere = true;	
        return this;	
    }	
	
    public QueryWhere orBracket(String columnName, QueryType queryType, Object value) {	
        QueryWhere queryWhere = this;	
        queryWhere.add(QueryAndor.OR_BRACKET, columnName, queryType, value, 1);	
        return queryWhere;	
    }	
	
    private /* synthetic */ String addWhere(Table t, String tableAlias, String paramPrefix) {	
        JoinTable[] a2;	
        int n;	
        Iterator iterator;	
        JoinTable[] a3;	
        Map<String, Column> a4 = null;	
        StringBuilder a5 = new StringBuilder();	
        QueryWhere queryWhere = this;	
        a4 = queryWhere.addEntityWhere(queryWhere.entity, t, a5, tableAlias, paramPrefix, true);	
        Iterator iterator2 = iterator = queryWhere.entrySet().iterator();	
        while (iterator2.hasNext()) {	
            void a6;	
            a2 = iterator.next();	
            QueryWhereEntity queryWhereEntity = (QueryWhereEntity)a2.getValue();	
            iterator2 = iterator;	
            a6.addSql(a5, paramPrefix, tableAlias, a4);	
        }	
        a2 = a3 = t.joinTable();	
        int a6 = a2.length;	
        int n2 = n = 0;	
        while (n2 < a6) {	
            BaseEntity baseEntity;	
            JoinTable a7 = a2[n];	
            String a8 = MapperHelper.getAttrName(a7);	
            BaseEntity a9 = null;	
            if ("this".equals(a8)) {	
                a8 = "";	
                baseEntity = a9 = this.entity;	
            } else {	
                baseEntity = a9 = (BaseEntity)ReflectUtils.invokeGetter(this.entity, (String)a8);	
            }	
            if (baseEntity != null) {	
                if (StringUtils.isNotBlank((CharSequence)paramPrefix)) {	
                    a8 = new StringBuilder().insert(0, paramPrefix).append(".").append(a8).toString();	
                }	
                StringBuilder a10 = new StringBuilder();	
                JoinTable joinTable = a7;	
                a4 = this.addEntityWhere(a9, joinTable, a10, joinTable.alias(), a8, false);	
                Iterator iterator3 = a9.getSqlMap().getWhere().entrySet().iterator();	
                while (iterator3.hasNext()) {	
                    Iterator iterator4;	
                    ((QueryWhereEntity)iterator4.next().getValue()).addSql(a10, a8, a7.alias(), a4);	
                    iterator3 = iterator4;	
                }	
                if (StringUtils.isNotBlank((CharSequence)a10)) {	
                    if (StringUtils.isNotBlank((CharSequence)a5.toString())) {	
                        a5.append(" AND ");	
                    }	
                    a5.append(a10);	
                }	
            }	
            n2 = ++n;	
        }	
        StringBuilder stringBuilder = a5;	
        MapperHelper.addExtWhere(stringBuilder, this.entity, t);	
        return stringBuilder.toString();	
    }	
	
    public QueryWhere or(String columnName, QueryType queryType, Object value, Integer num) {	
        QueryWhere queryWhere = this;	
        queryWhere.add(QueryAndor.OR, columnName, queryType, value, num);	
        return queryWhere;	
    }	
	
    public QueryWhere endBracket(Integer num) {	
        QueryWhere queryWhere = this;	
        queryWhere.add(QueryAndor.END_BRACKET, null, null, null, num);	
        return queryWhere;	
    }	
	
    public QueryWhere(BaseEntity<?> entity) {	
        this.entity = entity;	
    }	
	
    public QueryWhere orBracket(String columnName, QueryType queryType, Object value, Integer num) {	
        QueryWhere queryWhere = this;	
        queryWhere.add(QueryAndor.OR_BRACKET, columnName, queryType, value, num);	
        return queryWhere;	
    }	
}	
	
