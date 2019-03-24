/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.mybatis.mapper.query;	
	
import com.jeesite.common.codec.EncodeUtils;	
import com.jeesite.common.j2cache.autoconfigure.J2CacheAutoConfiguration;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.common.mybatis.mapper.query.QueryAndor;	
import com.jeesite.common.mybatis.mapper.query.QueryType;	
import com.jeesite.common.shiro.l.I;	
import java.io.Serializable;	
import java.util.List;	
import java.util.Map;	
	
public class QueryWhereEntity	
implements Serializable {	
    private QueryAndor andor;	
    private Object value;	
    private Object val;	
    private String key;	
    private QueryType queryType;	
    private static final long serialVersionUID = 1L;	
    private String columnName;	
	
    /*	
     * Unable to fully structure code	
     * Enabled aggressive block sorting	
     * Lifted jumps to return sites	
     */	
    public void addSql(StringBuilder sql, String paramPrefix, String tableAlias, Map<String, Column> columnMap) {	
        block15 : {	
            block14 : {	
                a = new StringBuilder();	
                if (QueryAndor.isLastBracket(sql.toString()) && QueryAndor.END_BRACKET == this.getAndor()) {	
                    QueryAndor.removeLastBracket(sql);	
                    return;	
                }	
                if (!QueryAndor.isLastBracket(sql.toString())) {	
                    QueryAndor.addAndor(sql, a, this.andor);	
                }	
                if (this.getQueryType() == null) ** GOTO lbl82	
                if (this.getQueryType() != QueryType.IS_NULL && this.getQueryType() != QueryType.IS_NOT_NULL) break block14;	
                if (StringUtils.isNotBlank(tableAlias)) {	
                    a.append(tableAlias + ".");	
                }	
                v0 = a;	
                v1 = v0;	
                v0.append(this.getColumnName());	
                a.append(new StringBuilder().insert(0, " ").append(this.getQueryType().operator()).toString());	
                break block15;	
            }	
            a = StringUtils.isNotBlank(ObjectUtils.toString(this.getVal()));	
            if (a && this.getValue() instanceof List) {	
                this.value = ((List)this.getValue()).toArray();	
            }	
            if (a && this.getValue() instanceof Object[]) {	
                v2 = a = ((Object[])this.getValue()).length > 0;	
            }	
            if (!a && !this.getQueryType().isForce().booleanValue()) ** GOTO lbl82	
            if (StringUtils.isNotBlank(tableAlias)) {	
                a.append(new StringBuilder().insert(0, tableAlias).append(".").toString());	
            }	
            a.append(this.getColumnName());	
            a.append(new StringBuilder().insert(0, " ").append(this.getQueryType().operator()).toString());	
            if (this.getValue() instanceof Object[]) {	
                a = (Object[])this.getValue();	
                a.append(" (");	
                var8_10 = false;	
                v3 = a;	
                while (v3 < a.length) {	
                    if (a != false) {	
                        a.append(",");	
                    }	
                    a.append(" #{");	
                    if (StringUtils.isNotBlank(paramPrefix)) {	
                        a.append(new StringBuilder().insert(0, paramPrefix).append(".").toString());	
                    }	
                    a.append(new StringBuilder().insert(0, "sqlMap.where.").append(this.key).toString());	
                    a.append(new StringBuilder().insert(0, ".val[").append((int)a).append("]").toString());	
                    a = columnMap.get(this.getColumnName());	
                    if (a != null) {	
                        a.append(MapperHelper.getColumnParamSuffix(a));	
                    }	
                    a.append("}");	
                    v3 = ++a;	
                }	
                v4 = a;	
                v1 = v4;	
                v4.append(" )");	
            } else {	
                a.append(" #{");	
                if (StringUtils.isNotBlank(paramPrefix)) {	
                    a.append(new StringBuilder().insert(0, paramPrefix).append(".").toString());	
                }	
                a.append(new StringBuilder().insert(0, "sqlMap.where.").append(this.key).toString());	
                a.append(".val");	
                a = columnMap.get(this.getColumnName());	
                if (a != null) {	
                    a.append(MapperHelper.getColumnParamSuffix(a));	
                }	
                a.append("}");	
lbl82: // 3 sources:	
                v1 = a;	
            }	
        }	
        a = v1.toString();	
        if (QueryAndor.isOnlyAndor(a) != false) return;	
        sql.append(a);	
    }	
	
    public Object getValue() {	
        return this.value;	
    }	
	
    public String getColumnName() {	
        return this.columnName;	
    }	
	
    public QueryAndor getAndor() {	
        return this.andor;	
    }	
	
    public Object getVal() {	
        return this.val;	
    }	
	
    public QueryType getQueryType() {	
        return this.queryType;	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    public QueryWhereEntity(String string, QueryAndor queryAndor, String string2, QueryType queryType, Object object) {	
        void andor;	
        void queryType2;	
        void columnName;	
        void key;	
        void value;	
        QueryWhereEntity queryWhereEntity = this;	
        QueryWhereEntity queryWhereEntity2 = this;	
        this.key = key;	
        queryWhereEntity2.andor = andor;	
        queryWhereEntity2.columnName = EncodeUtils.sqlFilter((String)columnName);	
        queryWhereEntity.queryType = queryType2;	
        queryWhereEntity.value = value;	
        if (queryType != null && value != null && value instanceof String) {	
            String a = "";	
            if (StringUtils.isNotBlank((String)value)) {	
                if (StringUtils.isNotBlank(queryType2.valuePrefix())) {	
                    a = new StringBuilder().insert(0, a).append(queryType2.valuePrefix()).toString();	
                }	
                a = new StringBuilder().insert(0, a).append(value).toString();	
                if (StringUtils.isNotBlank(queryType2.valueSuffux())) {	
                    a = new StringBuilder().insert(0, a).append(queryType2.valueSuffux()).toString();	
                }	
            }	
            this.val = a;	
            return;	
        }	
        this.val = value;	
    }	
	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = (3 ^ 5) << 4 ^ 3 << 1;	
        (3 ^ 5) << 4 ^ (2 ^ 5) << 1;	
        int n4 = n2;	
        int n5 = (2 ^ 5) << 4;	
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
}	
	
