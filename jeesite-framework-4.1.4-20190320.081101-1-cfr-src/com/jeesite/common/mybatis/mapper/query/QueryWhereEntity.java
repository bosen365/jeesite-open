/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.common.mybatis.mapper.query;	
	
import com.jeesite.common.codec.EncodeUtils;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.common.mybatis.mapper.query.QueryAndor;	
import com.jeesite.common.mybatis.mapper.query.QueryType;	
import com.jeesite.modules.sys.web.AdviceController;	
import java.io.Serializable;	
import java.util.List;	
import java.util.Map;	
import org.hyperic.sigar.FileSystemUsage;	
	
public class QueryWhereEntity	
implements Serializable {	
    private QueryAndor andor;	
    private String columnName;	
    private static final long serialVersionUID = 1L;	
    private Object val;	
    private QueryType queryType;	
    private String key;	
    private Object value;	
	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = (3 ^ 5) << 3 ^ 3;	
        int n4 = n2;	
        5 << 3 ^ 4;	
        int n5 = 5 << 4 ^ (3 ^ 5) << 1;	
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
	
    public QueryType getQueryType() {	
        return this.queryType;	
    }	
	
    public QueryWhereEntity(String string, QueryAndor queryAndor, String string2, QueryType queryType, Object object) {	
        void columnName;	
        void value;	
        void key;	
        void queryType2;	
        void andor;	
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
	
    public String getColumnName() {	
        return this.columnName;	
    }	
	
    public Object getVal() {	
        return this.val;	
    }	
	
    public Object getValue() {	
        return this.value;	
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
                if (StringUtils.isNotBlank(tableAlias)) {	
                    a.append(tableAlias + ".");	
                }	
                v0 = a;	
                v1 = v0;	
                a.append(new StringBuilder().insert(0, " ").append(this.getQueryType().operator()).toString());	
                v0.append(this.getColumnName());	
                break block15;	
            }	
            a = StringUtils.isNotBlank(ObjectUtils.toString(this.getVal()));	
            if (a && this.getValue() instanceof List) {	
                this.value = ((List)this.getValue()).toArray();	
            }	
            if (a && this.getValue() instanceof Object[]) {	
                v2 = a = ((Object[])this.getValue()).length > 0;	
            }	
            if (!a && !this.getQueryType().isForce().booleanValue()) ** GOTO lbl59	
            if (StringUtils.isNotBlank(tableAlias)) {	
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
                    if (StringUtils.isNotBlank(paramPrefix)) {	
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
                if (StringUtils.isNotBlank(paramPrefix)) {	
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
	
    public QueryAndor getAndor() {	
        return this.andor;	
    }	
}	
	
