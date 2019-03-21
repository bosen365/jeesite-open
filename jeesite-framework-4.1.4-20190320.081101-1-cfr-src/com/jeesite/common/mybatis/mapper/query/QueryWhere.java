/*	
 * Decompiled with CFR 0.140.	
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
import com.jeesite.modules.sys.utils.CorpUtils;	
import java.util.ArrayList;	
import java.util.HashMap;	
import java.util.Iterator;	
import java.util.LinkedHashMap;	
import java.util.Map;	
import java.util.Set;	
import org.apache.commons.lang3.BooleanUtils;	
import org.hyperic.sigar.ProcState;	
	
public class QueryWhere	
extends LinkedHashMap<String, QueryWhereEntity> {	
    private static final long serialVersionUID = 1L;	
    private boolean disableAutoAddStatusWhere;	
    private boolean disableAutoAddCorpCodeWhere;	
    private BaseEntity<?> entity;	
	
    private /* synthetic */ QueryWhere add(QueryAndor andor, String columnName, QueryType queryType, Object value, Integer num) {	
        if (andor == null) {	
            return this;	
        }	
        String a = new StringBuilder().insert(0, columnName).append("#").append((Object)queryType).append(num).toString();	
        QueryWhere queryWhere = this;	
        String string = a;	
        String string2 = a;	
        queryWhere.put(string2, new QueryWhereEntity(string2, andor, columnName, queryType, value));	
        return queryWhere;	
    }	
	
    public QueryWhere orBracket(String columnName, QueryType queryType, Object value, Integer num) {	
        QueryWhere queryWhere = this;	
        queryWhere.add(QueryAndor.OR_BRACKET, columnName, queryType, value, num);	
        return queryWhere;	
    }	
	
    private /* synthetic */ Map<String, Column> addEntityWhere(BaseEntity<?> entity, Object table, StringBuilder sql, String tableAlias, String paramPrefix, boolean isMainEntity) {	
        HashMap<String, Column> a;	
        block22 : {	
            a = MapUtils.newHashMap();	
            if (entity == null) break block22;	
            boolean a2 = false;	
            ArrayList<Column> a3 = ListUtils.newArrayList();	
            Iterator<Column> iterator = MapperHelper.getColumns(table, a3).iterator();	
            block0 : do {	
                Iterator<Column> iterator2 = iterator;	
                while (iterator2.hasNext()) {	
                    String a4;	
                    Column a5;	
                    boolean bl;	
                    boolean a6;	
                    QueryType a7;	
                    Object a8;	
                    block25 : {	
                        block24 : {	
                            block23 : {	
                                Column column = a5 = iterator.next();	
                                a.put(a5.name(), column);	
                                if (!column.isQuery()) continue block0;	
                                if (StringUtils.equals(a5.name(), "corp_code")) {	
                                    if (!Global.isUseCorpModel().booleanValue()) {	
                                        iterator2 = iterator;	
                                        continue;	
                                    }	
                                    if (this.disableAutoAddCorpCodeWhere) {	
                                        iterator2 = iterator;	
                                        continue;	
                                    }	
                                }	
                                if (StringUtils.equals(a5.name(), "corp_name")) {	
                                    iterator2 = iterator;	
                                    continue;	
                                }	
                                Column column2 = a5;	
                                a4 = MapperHelper.getAttrName(column2);	
                                a8 = ReflectUtils.invokeGetter(entity, a4);	
                                a6 = false;	
                                a7 = column2.queryType();	
                                if (!(a8 instanceof String)) break block23;	
                                if (!StringUtils.isNotBlank((String)a8) && !a7.isForce().booleanValue()) break block24;	
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
                    if (bl && "status".equals(a5.name()) && !a6 && !this.disableAutoAddStatusWhere) {	
                        if (sql.length() != 0) {	
                            sql.append(" AND ");	
                        }	
                        if (StringUtils.isNotBlank(tableAlias)) {	
                            sql.append(tableAlias + ".");	
                        }	
                        sql.append(new StringBuilder().insert(0, a5.name()).append(" != #{").toString());	
                        if (StringUtils.isNotBlank(paramPrefix)) {	
                            sql.append(new StringBuilder().insert(0, paramPrefix).append(".").toString());	
                        }	
                        sql.append("STATUS_DELETE}");	
                    }	
                    if (a5.isPK() && entity instanceof TreeEntity && BooleanUtils.toBoolean(((TreeEntity)entity).getIsQueryChildren())) {	
                        a2 = true;	
                    }	
                    if (!a6) continue block0;	
                    if (QueryType.IN == a7 || QueryType.NOT_IN == a7 || QueryType.IS_NULL == a7 || QueryType.IS_NOT_NULL == a7) {	
                        throw new MapperException("@Column(queryType=暂时不支持IN、NOT_IN、IS_NULL、IS_NOT_NULL，请使用sqlMap.getWhere()实现。)");	
                    }	
                    if (a8 instanceof String) {	
                        String a9 = "";	
                        if (StringUtils.isNotBlank(a7.valuePrefix())) {	
                            a9 = new StringBuilder().insert(0, a9).append(a7.valuePrefix()).toString();	
                        }	
                        a9 = new StringBuilder().insert(0, a9).append(a8).toString();	
                        if (StringUtils.isNotBlank(a7.valueSuffux())) {	
                            a9 = new StringBuilder().insert(0, a9).append(a7.valueSuffux()).toString();	
                        }	
                        a4 = new StringBuilder().insert(0, "where#").append(a5.name()).append("#").append((Object)a7).append("1").toString();	
                        Object object = entity.getSqlMap().add(a4, a9);	
                        a4 = new StringBuilder().insert(0, "sqlMap.").append(a4).toString();	
                    }	
                    if (sql.length() != 0) {	
                        sql.append(" AND ");	
                    }	
                    if (a5.isPK() && a2) {	
                        sql.append("(");	
                    }	
                    if (StringUtils.isNotBlank(tableAlias)) {	
                        sql.append(new StringBuilder().insert(0, tableAlias).append(".").toString());	
                    }	
                    sql.append("#{");	
                    sql.append(new StringBuilder().insert(0, a7.operator()).append(" ").toString());	
                    sql.append(new StringBuilder().insert(0, a5.name()).append(" ").toString());	
                    if (StringUtils.isNotBlank(paramPrefix)) {	
                        sql.append(new StringBuilder().insert(0, paramPrefix).append(".").toString());	
                    }	
                    sql.append("}");	
                    sql.append(new StringBuilder().insert(0, a4).append(MapperHelper.getColumnParamSuffix(a5)).toString());	
                    if (!a5.isPK() || !a2) continue block0;	
                    a4 = new StringBuilder().insert(0, "where#").append(a5.name()).append("#").append((Object)a7).append("1#ISQC").toString();	
                    Object object = entity.getSqlMap().add(a4, new StringBuilder().insert(0, "%,").append(a8).append(",%").toString());	
                    a4 = new StringBuilder().insert(0, "sqlMap.").append(a4).toString();	
                    sql.append(" OR ");	
                    if (StringUtils.isNotBlank(tableAlias)) {	
                        sql.append(new StringBuilder().insert(0, tableAlias).append(".parent_codes LIKE ").toString());	
                    }	
                    sql.append("#{");	
                    if (StringUtils.isNotBlank(paramPrefix)) {	
                        sql.append(new StringBuilder().insert(0, paramPrefix).append(".").toString());	
                    }	
                    sql.append(")");	
                    sql.append("}");	
                    sql.append(new StringBuilder().insert(0, a4).append(MapperHelper.getColumnParamSuffix(a5)).toString());	
                    continue block0;	
                }	
                break;	
            } while (true);	
        }	
        return a;	
    }	
	
    public String toSql(String tableAlias, String paramPrefix) {	
        QueryWhere queryWhere = this;	
        return queryWhere.addWhere(MapperHelper.getTable(queryWhere.entity), tableAlias, paramPrefix);	
    }	
	
    public QueryWhere andBracket(String columnName, QueryType queryType, Object value) {	
        QueryWhere queryWhere = this;	
        queryWhere.add(QueryAndor.AND_BRACKET, columnName, queryType, value, 1);	
        return queryWhere;	
    }	
	
    public QueryWhere(BaseEntity<?> entity) {	
        this.entity = entity;	
    }	
	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = (3 ^ 5) << 4 ^ (2 ^ 5) << 1;	
        int n4 = n2;	
        int n5 = 4 << 4 ^ (3 ^ 5) << 1;	
        2 ^ 5;	
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
	
    public QueryWhere disableAutoAddCorpCodeWhere() {	
        this.disableAutoAddCorpCodeWhere = true;	
        return this;	
    }	
	
    public QueryWhere andBracket(String columnName, QueryType queryType, Object value, Integer num) {	
        QueryWhere queryWhere = this;	
        queryWhere.add(QueryAndor.AND_BRACKET, columnName, queryType, value, num);	
        return queryWhere;	
    }	
	
    public QueryWhere and(String columnName, QueryType queryType, Object value, Integer num) {	
        QueryWhere queryWhere = this;	
        queryWhere.add(QueryAndor.AND, columnName, queryType, value, num);	
        return queryWhere;	
    }	
	
    public QueryWhere orBracket(String columnName, QueryType queryType, Object value) {	
        QueryWhere queryWhere = this;	
        queryWhere.add(QueryAndor.OR_BRACKET, columnName, queryType, value, 1);	
        return queryWhere;	
    }	
	
    public QueryWhere or(String columnName, QueryType queryType, Object value) {	
        QueryWhere queryWhere = this;	
        queryWhere.add(QueryAndor.OR, columnName, queryType, value, 1);	
        return queryWhere;	
    }	
	
    public QueryWhere endBracket() {	
        QueryWhere queryWhere = this;	
        queryWhere.add(QueryAndor.END_BRACKET, null, null, null, 1);	
        return queryWhere;	
    }	
	
    public <E> E getValue(String columnName, QueryType queryType) {	
        return this.getValue(columnName, queryType, 1);	
    }	
	
    public QueryWhere endBracket(Integer num) {	
        QueryWhere queryWhere = this;	
        queryWhere.add(QueryAndor.END_BRACKET, null, null, null, num);	
        return queryWhere;	
    }	
	
    public <E> E getValue(String string, QueryType queryType, Integer n) {	
        void columnName;	
        void num;	
        void queryType2;	
        QueryWhereEntity a = (QueryWhereEntity)this.get((String)columnName + "#" + queryType2 + num);	
        if (a != null) {	
            return (E)a.getValue();	
        }	
        return null;	
    }	
	
    public String toSql() {	
        QueryWhere queryWhere = this;	
        Table table = MapperHelper.getTable(queryWhere.entity);	
        return queryWhere.addWhere(table, table.alias(), null);	
    }	
	
    public QueryWhere or(String columnName, QueryType queryType, Object value, Integer num) {	
        QueryWhere queryWhere = this;	
        queryWhere.add(QueryAndor.OR, columnName, queryType, value, num);	
        return queryWhere;	
    }	
	
    public QueryWhere disableAutoAddStatusWhere() {	
        this.disableAutoAddStatusWhere = true;	
        return this;	
    }	
	
    private /* synthetic */ String addWhere(Table t, String tableAlias, String paramPrefix) {	
        int n;	
        JoinTable[] a;	
        Iterator iterator;	
        JoinTable[] a2;	
        Map<String, Column> a3 = null;	
        StringBuilder a4 = new StringBuilder();	
        QueryWhere queryWhere = this;	
        a3 = queryWhere.addEntityWhere(queryWhere.entity, t, a4, tableAlias, paramPrefix, true);	
        Iterator iterator2 = iterator = queryWhere.entrySet().iterator();	
        while (iterator2.hasNext()) {	
            void a5;	
            a = iterator.next();	
            QueryWhereEntity queryWhereEntity = (QueryWhereEntity)a.getValue();	
            iterator2 = iterator;	
            a5.addSql(a4, paramPrefix, tableAlias, a3);	
        }	
        a = a2 = t.joinTable();	
        int a5 = a.length;	
        int n2 = n = 0;	
        while (n2 < a5) {	
            BaseEntity baseEntity;	
            JoinTable a6 = a[n];	
            String a7 = MapperHelper.getAttrName(a6);	
            BaseEntity a8 = null;	
            if ("this".equals(a7)) {	
                a7 = "";	
                baseEntity = a8 = this.entity;	
            } else {	
                baseEntity = a8 = (BaseEntity)ReflectUtils.invokeGetter(this.entity, a7);	
            }	
            if (baseEntity != null) {	
                if (StringUtils.isNotBlank(paramPrefix)) {	
                    a7 = new StringBuilder().insert(0, paramPrefix).append(".").append(a7).toString();	
                }	
                StringBuilder a9 = new StringBuilder();	
                JoinTable joinTable = a6;	
                a3 = this.addEntityWhere(a8, joinTable, a9, joinTable.alias(), a7, false);	
                Iterator iterator3 = a8.getSqlMap().getWhere().entrySet().iterator();	
                while (iterator3.hasNext()) {	
                    Iterator iterator4;	
                    ((QueryWhereEntity)iterator4.next().getValue()).addSql(a9, a7, a6.alias(), a3);	
                    iterator3 = iterator4;	
                }	
                if (StringUtils.isNotBlank(a9)) {	
                    if (StringUtils.isNotBlank(a4.toString())) {	
                        a4.append(" AND ");	
                    }	
                    a4.append(a9);	
                }	
            }	
            n2 = ++n;	
        }	
        StringBuilder stringBuilder = a4;	
        MapperHelper.addExtWhere(stringBuilder, this.entity, t);	
        return stringBuilder.toString();	
    }	
}	
	
