/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.collect.ListUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  com.jeesite.common.lang.TimeUtils	
 *  org.slf4j.Logger	
 *  org.slf4j.LoggerFactory	
 */	
package com.jeesite.common.mybatis.mapper.provider;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.entity.Extend;	
import com.jeesite.common.entity.TreeEntity;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.lang.TimeUtils;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.MapperException;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import java.util.ArrayList;	
import java.util.Iterator;	
import org.hyperic.sigar.DirStat;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
	
public class TreeSqlProvider {	
    private Logger logger;	
	
    public String findByParentCodesLike(TreeEntity<?> entity) {	
        int n;	
        void a;	
        long a2 = System.currentTimeMillis();	
        StringBuilder a3 = new StringBuilder();	
        String a4 = "";	
        String a5 = "";	
        String a6 = "";	
        String a7 = "";	
        Table a8 = MapperHelper.getTable(entity);	
        Object object = a8.columns();	
        int n2 = ((Column[])object).length;	
        int n3 = n = 0;	
        while (n3 < n2) {	
            Column a9 = object[n];	
            if (a9.isPK()) {	
                Column column = a9;	
                a4 = column.name();	
                a5 = MapperHelper.getAttrName(column);	
            }	
            if (a9.isTreeName()) {	
                Column column = a9;	
                a6 = column.name();	
                a7 = MapperHelper.getAttrName(column);	
            }	
            n3 = ++n;	
        }	
        if (StringUtils.isBlank((CharSequence)a6) || StringUtils.isBlank((CharSequence)a7)) {	
            throw new MapperException(new StringBuilder().insert(0, "Error: ").append(entity.getClass()).append(" 没有找到isTreeName为true的列.").toString());	
        }	
        object = a3.toString();	
        a3.append(" ORDER BY a.tree_sorts");	
        a3.append(" WHERE a.parent_codes LIKE #{parentCodes}");	
        a3.append(new StringBuilder().insert(0, " FROM ").append(MapperHelper.getTableName(a8, entity)).append(" a").toString());	
        a3.append(new StringBuilder().insert(0, " a.").append(a6).append(" AS "").append(a7).append(""").toString());	
        a3.append(new StringBuilder().insert(0, " a.").append(a6).append(" AS "treeName",").toString());	
        a3.append(" a.tree_names AS "treeNames",");	
        a3.append(" a.tree_level AS "treeLevel",");	
        a3.append(" a.tree_sorts AS "treeSorts",");	
        a3.append(" a.tree_sort AS "treeSort",");	
        a3.append(" a.parent_codes AS "parentCodes",");	
        a3.append(" a.parent_code AS "parentCode",");	
        a3.append(" a." + a4 + " AS "" + a5 + "",");	
        a3.append("SELECT");	
        if (this.logger.isDebugEnabled()) {	
            this.logger.debug(new StringBuilder().insert(0, TimeUtils.formatDateAgo((long)(System.currentTimeMillis() - a2))).append(": ").append((String)a).toString());	
        }	
        return a;	
    }	
	
    public String updateTreeSort(TreeEntity<?> entity) {	
        void a;	
        long a2 = System.currentTimeMillis();	
        StringBuilder a3 = new StringBuilder();	
        TreeEntity<?> treeEntity = entity;	
        String a4 = treeEntity.getIdColumnName();	
        String a5 = treeEntity.getIdAttrName();	
        Table a6 = MapperHelper.getTable(treeEntity);	
        String string = a3.toString();	
        a3.append(new StringBuilder().insert(0, " ").append(a4).append(" = #{").append(a5).append("}").toString());	
        a3.append(" WHERE");	
        a3.append(" tree_sorts = #{treeSorts}");	
        a3.append(" tree_sort = #{treeSort},");	
        a3.append(" SET");	
        a3.append(" " + MapperHelper.getTableName(a6, entity));	
        a3.append("UPDATE");	
        if (this.logger.isDebugEnabled()) {	
            this.logger.debug(new StringBuilder().insert(0, TimeUtils.formatDateAgo((long)(System.currentTimeMillis() - a2))).append(": ").append((String)a).toString());	
        }	
        return a;	
    }	
	
    public String updateTreeLeaf(TreeEntity<?> entity) {	
        Object object;	
        long a;	
        StringBuilder a2;	
        StringBuilder stringBuilder;	
        void a3;	
        String a4;	
        String a5;	
        block2 : {	
            a = System.currentTimeMillis();	
            a2 = new StringBuilder();	
            TreeEntity<?> treeEntity = entity;	
            a5 = treeEntity.getIdColumnName();	
            a4 = treeEntity.getIdAttrName();	
            Table a6 = MapperHelper.getTable(treeEntity);	
            String a7 = MapperHelper.getTableName(a6, entity);	
            ArrayList a8 = ListUtils.newArrayList();	
            object = MapperHelper.getColumns(a6, a8).iterator();	
            a2.append(new StringBuilder().insert(0, " FROM ").append(a7).append("").toString());	
            a2.append(" SELECT (case when count(1) > 0 then '0' else '1' end) tree_leaf");	
            a2.append(" SELECT tree_leaf FROM (");	
            a2.append(" tree_leaf = (");	
            a2.append(" SET");	
            a2.append(" " + a7);	
            a2.append("UPDATE");	
            while (object.hasNext()) {	
                Column a9 = object.next();	
                if (!"status".equals(a9.name())) continue;	
                StringBuilder stringBuilder2 = a2;	
                stringBuilder = stringBuilder2;	
                stringBuilder2.append(" WHERE status = '0'");	
                break block2;	
            }	
            stringBuilder = a2;	
        }	
        object = a2.toString();	
        a2.append(new StringBuilder().insert(0, " ").append(a5).append(" = #{").append(a4).append("}").toString());	
        a2.append(" WHERE");	
        a2.append(" )");	
        a2.append(" ) a");	
        stringBuilder.append(new StringBuilder().insert(0, " AND parent_code = #{").append(a4).append("}").toString());	
        if (this.logger.isDebugEnabled()) {	
            this.logger.debug(new StringBuilder().insert(0, TimeUtils.formatDateAgo((long)(System.currentTimeMillis() - a))).append(": ").append((String)a3).toString());	
        }	
        return a3;	
    }	
	
    public TreeSqlProvider() {	
        TreeSqlProvider treeSqlProvider = this;	
        treeSqlProvider.logger = LoggerFactory.getLogger(treeSqlProvider.getClass());	
    }	
	
    public String updateTreeData(TreeEntity<?> entity) {	
        void a;	
        long a2 = System.currentTimeMillis();	
        StringBuilder a3 = new StringBuilder();	
        TreeEntity<?> treeEntity = entity;	
        String a4 = treeEntity.getIdColumnName();	
        String a5 = treeEntity.getIdAttrName();	
        Table a6 = MapperHelper.getTable(treeEntity);	
        String string = a3.toString();	
        a3.append(" " + a4 + " = #{" + a5 + "}");	
        a3.append(" WHERE");	
        a3.append(MapperHelper.getSqlMapValue(entity, "updateTreeDataExtSql", ","));	
        a3.append(" tree_names = #{treeNames}");	
        a3.append(" tree_level = #{treeLevel},");	
        a3.append(" tree_sorts = #{treeSorts},");	
        a3.append(" parent_codes = #{parentCodes},");	
        a3.append(" SET");	
        a3.append(MapperHelper.getTableName(a6, entity));	
        a3.append("UPDATE ");	
        if (this.logger.isDebugEnabled()) {	
            this.logger.debug(new StringBuilder().insert(0, TimeUtils.formatDateAgo((long)(System.currentTimeMillis() - a2))).append(": ").append((String)a).toString());	
        }	
        return a;	
    }	
}	
	
