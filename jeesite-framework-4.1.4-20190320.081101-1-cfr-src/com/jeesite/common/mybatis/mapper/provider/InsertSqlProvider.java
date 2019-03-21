/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.common.mybatis.mapper.provider;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.lang.TimeUtils;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.common.reflect.ReflectUtils;	
import com.jeesite.modules.msg.entity.content.BaseMsgContent;	
import java.util.ArrayList;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Map;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
import org.springframework.util.Assert;	
	
public class InsertSqlProvider {	
    private Logger logger;	
	
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
	
    public String insertBatch(Map<String, ?> params) {	
        long a = System.currentTimeMillis();	
        List a2 = (List)params.get("param1");	
        InsertSqlProvider insertSqlProvider = this;	
        String a3 = insertSqlProvider.insertBatch(ListUtils.newArrayList(a2), "param1");	
        if (insertSqlProvider.logger.isDebugEnabled()) {	
            this.logger.debug(new StringBuilder().insert(0, TimeUtils.formatDateAgo(System.currentTimeMillis() - a)).append(": ").append(a3).toString());	
        }	
        return a3;	
    }	
	
    public InsertSqlProvider() {	
        InsertSqlProvider insertSqlProvider = this;	
        insertSqlProvider.logger = LoggerFactory.getLogger(insertSqlProvider.getClass());	
    }	
	
    private /* synthetic */ String insertBatch(List<?> entityList, String paramName) {	
        StringBuilder stringBuilder;	
        List<?> list = entityList;	
        Assert.notEmpty(list, "没有需要插入的实体集合");	
        BaseEntity a = (BaseEntity)list.get(0);	
        StringBuilder a2 = new StringBuilder();	
        StringBuilder a3 = new StringBuilder();	
        StringBuilder a4 = new StringBuilder();	
        Table a5 = MapperHelper.getTable(a);	
        ArrayList<Column> a6 = ListUtils.newArrayList();	
        Iterator<Column> iterator = MapperHelper.getColumns(a5, a6).iterator();	
        block0 : do {	
            Iterator<Column> iterator2 = iterator;	
            while (iterator2.hasNext()) {	
                int a7;	
                Column a8 = iterator.next();	
                if (!a8.isInsert()) continue block0;	
                if (StringUtils.inString(a8.name(), "corp_code", "corp_name") && !Global.isUseCorpModel().booleanValue()) {	
                    iterator2 = iterator;	
                    continue;	
                }	
                String a9 = MapperHelper.getAttrName(a8);	
                int n = a7 = 0;	
                while (n < entityList.size()) {	
                    Object obj = entityList.get(a7);	
                    ReflectUtils.invokeMethod(obj, "preInsert", null, null);	
                    n = ++a7;	
                }	
                if (paramName == null) {	
                    Object a10 = ReflectUtils.invokeGetter(a, a9);	
                    if (a10 == null) continue block0;	
                    InsertSqlProvider insertSqlProvider = this;	
                    insertSqlProvider.addSqlColumn(a3, a8);	
                    insertSqlProvider.addSqlValue(a4, a8, a9);	
                    continue block0;	
                }	
                Column column = a8;	
                this.addSqlColumn(a3, column);	
                Column column2 = a8;	
                this.addSqlValue(a4, column, "{list}." + a9);	
                continue block0;	
            }	
            break;	
        } while (true);	
        a2.append(")");	
        a2.append(a3);	
        a2.append(" (");	
        a2.append(MapperHelper.getTableName(a5, a));	
        a2.append("INSERT INTO ");	
        if (paramName == null) {	
            StringBuilder stringBuilder2 = a2;	
            stringBuilder = stringBuilder2;	
            stringBuilder2.append(")");	
            a2.append(a4);	
            a2.append(" VALUES (");	
        } else {	
            int a11;	
            int n = a11 = 0;	
            while (n < entityList.size()) {	
                if (StringUtils.inString(MapperHelper.getDbName(), "oracle")) {	
                    if (a11 != 0) {	
                        a2.append(" UNION ALL ");	
                    }	
                    a2.append(" FROM dual");	
                    a2.append(StringUtils.replace(a4.toString(), "{list}", new StringBuilder().insert(0, "param1[").append(a11).append("]").toString()));	
                    a2.append(" SELECT ");	
                } else {	
                    if (a11 == 0) {	
                        a2.append(" VALUES ");	
                    }	
                    if (a11 != 0) {	
                        a2.append(", ");	
                    }	
                    a2.append(")");	
                    a2.append(StringUtils.replace(a4.toString(), "{list}", new StringBuilder().insert(0, "param1[").append(a11).append("]").toString()));	
                    a2.append(" (");	
                }	
                n = ++a11;	
            }	
            stringBuilder = a2;	
        }	
        return stringBuilder.toString();	
    }	
	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = (3 ^ 5) << 4 ^ 2 << 1;	
        int n4 = n2;	
        (3 ^ 5) << 4 ^ (2 << 2 ^ 3);	
        int n5 = (3 ^ 5) << 3 ^ 4;	
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
	
    public String insert(BaseEntity<?> entity) {	
        long a = System.currentTimeMillis();	
        String a2 = this.insertBatch(ListUtils.newArrayList(entity), null);	
        if (this.logger.isDebugEnabled()) {	
            this.logger.debug(new StringBuilder().insert(0, TimeUtils.formatDateAgo(System.currentTimeMillis() - a)).append(": ").append(a2).toString());	
        }	
        return a2;	
    }	
}	
	
