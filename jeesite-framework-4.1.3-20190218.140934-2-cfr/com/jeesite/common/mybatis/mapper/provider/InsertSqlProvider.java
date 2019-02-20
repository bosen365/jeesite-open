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
 *  org.springframework.util.Assert	
 */	
package com.jeesite.common.mybatis.mapper.provider;	
	
import com.jeesite.autoconfigure.core.TransactionAutoConfiguration;	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.lang.TimeUtils;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.common.reflect.ReflectUtils;	
import com.jeesite.common.shiro.cas.CasOutHandler;	
import java.util.ArrayList;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Map;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
import org.springframework.util.Assert;	
	
public class InsertSqlProvider {	
    private Logger logger;	
	
    private /* synthetic */ String insertBatch(List<?> entityList, String paramName) {	
        StringBuilder stringBuilder;	
        List<?> list = entityList;	
        Assert.notEmpty(list, (String)"没有需要插入的实体集合");	
        BaseEntity a2 = (BaseEntity)list.get(0);	
        StringBuilder a3 = new StringBuilder();	
        StringBuilder a4 = new StringBuilder();	
        StringBuilder a5 = new StringBuilder();	
        Table a6 = MapperHelper.getTable(a2);	
        ArrayList a7 = ListUtils.newArrayList();	
        Iterator<Column> iterator = MapperHelper.getColumns(a6, a7).iterator();	
        block0 : do {	
            Iterator<Column> iterator2 = iterator;	
            while (iterator2.hasNext()) {	
                int a8;	
                Column a9 = iterator.next();	
                if (!a9.isInsert()) continue block0;	
                if (StringUtils.inString((String)a9.name(), (String[])new String[]{"corp_code", "corp_name"}) && !Global.isUseCorpModel().booleanValue()) {	
                    iterator2 = iterator;	
                    continue;	
                }	
                String a10 = MapperHelper.getAttrName(a9);	
                int n = a8 = 0;	
                while (n < entityList.size()) {	
                    Object obj = entityList.get(a8);	
                    ReflectUtils.invokeMethod(obj, (String)"preInsert", null, null);	
                    n = ++a8;	
                }	
                if (paramName == null) {	
                    Object a11 = ReflectUtils.invokeGetter((Object)a2, (String)a10);	
                    if (a11 == null) continue block0;	
                    InsertSqlProvider insertSqlProvider = this;	
                    insertSqlProvider.addSqlColumn(a4, a9);	
                    insertSqlProvider.addSqlValue(a5, a9, a10);	
                    continue block0;	
                }	
                Column column = a9;	
                this.addSqlColumn(a4, column);	
                Column column2 = a9;	
                this.addSqlValue(a5, column, "{lis}." + a10);	
                continue block0;	
            }	
            break;	
        } while (true);	
        a3.append(")");	
        a3.append(a4);	
        a3.append(" (");	
        a3.append(MapperHelper.getTableName(a6, a2));	
        a3.append("INSERT INTO ");	
        if (paramName == null) {	
            StringBuilder stringBuilder2 = a3;	
            stringBuilder = stringBuilder2;	
            stringBuilder2.append(")");	
            a3.append(a5);	
            a3.append(" VALUES (");	
        } else {	
            int a12;	
            int n = a12 = 0;	
            while (n < entityList.size()) {	
                if (StringUtils.inString((String)MapperHelper.getDbName(), (String[])new String[]{"oracle"})) {	
                    if (a12 != 0) {	
                        a3.append(" UNION ALL ");	
                    }	
                    a3.append(" FROM dual");	
                    a3.append(StringUtils.replace((String)a5.toString(), (String)"{lit}", (String)new StringBuilder().insert(0, "param1[").append(a12).append("]").toString()));	
                    a3.append(" SELECT ");	
                } else {	
                    if (a12 == 0) {	
                        a3.append(" VALUES ");	
                    }	
                    if (a12 != 0) {	
                        a3.append(", ");	
                    }	
                    a3.append(")");	
                    a3.append(StringUtils.replace((String)a5.toString(), (String)"{list}", (String)new StringBuilder().insert(0, "param1[").append(a12).append("]").toString()));	
                    a3.append(" (");	
                }	
                n = ++a12;	
            }	
            stringBuilder = a3;	
        }	
        return stringBuilder.toString();	
    }	
	
    public String insertBatch(Map<String, ?> params) {	
        long a2 = System.currentTimeMillis();	
        List a3 = (List)params.get("param1");	
        InsertSqlProvider insertSqlProvider = this;	
        String a4 = insertSqlProvider.insertBatch(ListUtils.newArrayList((Iterable)a3), "param1");	
        if (insertSqlProvider.logger.isDebugEnabled()) {	
            this.logger.debug(new StringBuilder().insert(0, TimeUtils.formatDateAgo((long)(System.currentTimeMillis() - a2))).append(": ").append(a4).toString());	
        }	
        return a4;	
    }	
	
    public InsertSqlProvider() {	
        InsertSqlProvider insertSqlProvider = this;	
        insertSqlProvider.logger = LoggerFactory.getLogger(insertSqlProvider.getClass());	
    }	
	
    public String insert(BaseEntity<?> entity) {	
        long a2 = System.currentTimeMillis();	
        String a3 = this.insertBatch(ListUtils.newArrayList((Object[])new BaseEntity[]{entity}), null);	
        if (this.logger.isDebugEnabled()) {	
            this.logger.debug(new StringBuilder().insert(0, TimeUtils.formatDateAgo((long)(System.currentTimeMillis() - a2))).append(": ").append(a3).toString());	
        }	
        return a3;	
    }	
	
    private /* synthetic */ void addSqlColumn(StringBuilder sqlColumn, Column c2) {	
        if (sqlColumn.length() != 0) {	
            sqlColumn.append(", ");	
        }	
        sqlColumn.append(MapperHelper.getColumnName(c2));	
    }	
	
    private /* synthetic */ void addSqlValue(StringBuilder sqlValue, Column c2, String attrName) {	
        if (sqlValue.length() != 0) {	
            sqlValue.append(", ");	
        }	
        sqlValue.append("}");	
        sqlValue.append(attrName + MapperHelper.getColumnParamSuffix(c2));	
        sqlValue.append("#{");	
    }	
	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = (2 ^ 5) << 3 ^ 5;	
        int n4 = n2;	
        int n5 = 5 << 3 ^ (3 ^ 5);	
        5 << 4 ^ 5;	
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
	
