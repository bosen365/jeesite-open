/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.codec.EncodeUtils	
 *  com.jeesite.common.lang.ObjectUtils	
 *  com.jeesite.common.lang.StringUtils	
 */	
package com.jeesite.common.mybatis.mapper.query;	
	
import com.jeesite.common.codec.EncodeUtils;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.common.mybatis.mapper.query.QueryAndor;	
import com.jeesite.common.mybatis.mapper.query.QueryType;	
import java.io.Serializable;	
import java.util.List;	
import java.util.Map;	
import org.hyperic.sigar.CpuPerc;	
import org.hyperic.sigar.ptql.QueryLoadException;	
	
public class QueryWhereEntity	
implements Serializable {	
    private static final long serialVersionUID = 1L;	
    private Object value;	
    private QueryAndor andor;	
    private String columnName;	
    private Object val;	
    private QueryType queryType;	
    private String key;	
	
    public Object getVal() {	
        return this.val;	
    }	
	
    public QueryType getQueryType() {	
        return this.queryType;	
    }	
	
    public QueryAndor getAndor() {	
        return this.andor;	
    }	
	
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
                if (this.getQueryType() == null) ** GOTO lbl59	
                if (this.getQueryType() != QueryType.IS_NULL && this.getQueryType() != QueryType.IS_NOT_NULL) break block14;	
                if (StringUtils.isNotBlank((CharSequence)tableAlias)) {	
                    a.append(tableAlias + ".");	
                }	
                v0 = a;	
                v1 = v0;	
                a.append(new StringBuilder().insert(0, " ").append(this.getQueryType().operator()).toString());	
                v0.append(this.getColumnName());	
                break block15;	
            }	
            a = StringUtils.isNotBlank((CharSequence)ObjectUtils.toString((Object)this.getVal()));	
            if (a && this.getValue() instanceof List) {	
                this.value = ((List)this.getValue()).toArray();	
            }	
            if (a && this.getValue() instanceof Object[]) {	
                v2 = a = ((Object[])this.getValue()).length > 0;	
            }	
            if (!a && !this.getQueryType().isForce().booleanValue()) ** GOTO lbl59	
            if (StringUtils.isNotBlank((CharSequence)tableAlias)) {	
                a.append(new StringBuilder().insert(0, tableAlias).append(".").toString());	
            }	
            a.append(new StringBuilder().insert(0, " ").append(this.getQueryType().operator()).toString());	
            a.append(this.getColumnName());	
            if (this.getValue() instanceof Object[]) {	
                a = (Object[])this.getValue();	
                var8_10 = false;	
                a.append(" (");	
                v3 = a;	
                while (v3 < a.length) {	
                    if (a != false) {	
                        a.append(",");	
                    }	
                    a.append(" #{");	
                    if (StringUtils.isNotBlank((CharSequence)paramPrefix)) {	
                        a.append(new StringBuilder().insert(0, paramPrefix).append(".").toString());	
                    }	
                    a.append(new StringBuilder().insert(0, ".val[").append((int)a).append("]").toString());	
                    a.append(new StringBuilder().insert(0, "sqlMap.where.").append(this.key).toString());	
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
                if (StringUtils.isNotBlank((CharSequence)paramPrefix)) {	
                    a.append(new StringBuilder().insert(0, paramPrefix).append(".").toString());	
                }	
                a.append(".val");	
                a.append(new StringBuilder().insert(0, "sqlMap.where.").append(this.key).toString());	
                a = columnMap.get(this.getColumnName());	
                if (a != null) {	
                    a.append(MapperHelper.getColumnParamSuffix(a));	
                }	
                a.append("}");	
lbl59: // 3 sources:	
                v1 = a;	
            }	
        }	
        a = v1.toString();	
        if (QueryAndor.isOnlyAndor(a) != false) return;	
        sql.append(a);	
    }	
	
    public String getColumnName() {	
        return this.columnName;	
    }	
	
    public Object getValue() {	
        return this.value;	
    }	
	
    public QueryWhereEntity(String string, QueryAndor queryAndor, String string2, QueryType queryType, Object object) {	
        void value;	
        void queryType2;	
        void key;	
        void andor;	
        void columnName;	
        QueryWhereEntity queryWhereEntity = this;	
        QueryWhereEntity queryWhereEntity2 = this;	
        this.key = key;	
        queryWhereEntity2.andor = andor;	
        queryWhereEntity2.columnName = EncodeUtils.sqlFilter((String)columnName);	
        queryWhereEntity.queryType = queryType2;	
        queryWhereEntity.value = value;	
        if (queryType != null && value != null && value instanceof String) {	
            String a2 = "";	
            if (StringUtils.isNotBlank((CharSequence)((String)value))) {	
                if (StringUtils.isNotBlank((CharSequence)queryType2.valuePrefix())) {	
                    a2 = new StringBuilder().insert(0, a2).append(queryType2.valuePrefix()).toString();	
                }	
                a2 = new StringBuilder().insert(0, a2).append(value).toString();	
                if (StringUtils.isNotBlank((CharSequence)queryType2.valueSuffux())) {	
                    a2 = new StringBuilder().insert(0, a2).append(queryType2.valueSuffux()).toString();	
                }	
            }	
            this.val = a2;	
            return;	
        }	
        this.val = value;	
    }	
}	
	
