/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.mybatis.mapper.provider;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.entity.TreeEntity;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.lang.TimeUtils;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.MapperException;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import java.util.ArrayList;	
import java.util.Iterator;	
import org.hyperic.sigar.ProcState;	
import org.hyperic.sigar.SudoFileInputStream;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
	
public class TreeSqlProvider {	
    private Logger logger;	
	
    /*	
     * WARNING - void declaration	
     */	
    public String updateTreeSort(TreeEntity<?> entity) {	
        void a;	
        long a2 = System.currentTimeMillis();	
        StringBuilder a3 = new StringBuilder();	
        TreeEntity<?> treeEntity = entity;	
        String a4 = treeEntity.getIdColumnName();	
        String a5 = treeEntity.getIdAttrName();	
        Table a6 = MapperHelper.getTable(treeEntity);	
        a3.append("UPDATE");	
        a3.append(" " + MapperHelper.getTableName(a6, entity));	
        a3.append(" SET");	
        a3.append(" tree_sort = #{treeSort},");	
        a3.append(" tree_sorts = #{treeSorts}");	
        a3.append(" WHERE");	
        a3.append(new StringBuilder().insert(0, " ").append(a4).append(" = #{").append(a5).append("}").toString());	
        String string = a3.toString();	
        if (this.logger.isDebugEnabled()) {	
            this.logger.debug(new StringBuilder().insert(0, TimeUtils.formatDateAgo(System.currentTimeMillis() - a2)).append(": ").append((String)a).toString());	
        }	
        return a;	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    public String updateTreeData(TreeEntity<?> entity) {	
        void a;	
        long a2 = System.currentTimeMillis();	
        StringBuilder a3 = new StringBuilder();	
        TreeEntity<?> treeEntity = entity;	
        String a4 = treeEntity.getIdColumnName();	
        String a5 = treeEntity.getIdAttrName();	
        Table a6 = MapperHelper.getTable(treeEntity);	
        a3.append("UPDATE ");	
        a3.append(MapperHelper.getTableName(a6, entity));	
        a3.append(" SET");	
        a3.append(" parent_codes = #{parentCodes},");	
        a3.append(" tree_sorts = #{treeSorts},");	
        a3.append(" tree_level = #{treeLevel},");	
        a3.append(" tree_names = #{treeNames}");	
        a3.append(MapperHelper.getSqlMapValue(entity, "updateTreeDataExtSql", ","));	
        a3.append(" WHERE");	
        a3.append(" " + a4 + " = #{" + a5 + "}");	
        String string = a3.toString();	
        if (this.logger.isDebugEnabled()) {	
            this.logger.debug(new StringBuilder().insert(0, TimeUtils.formatDateAgo(System.currentTimeMillis() - a2)).append(": ").append((String)a).toString());	
        }	
        return a;	
    }	
	
    public TreeSqlProvider() {	
        TreeSqlProvider treeSqlProvider = this;	
        treeSqlProvider.logger = LoggerFactory.getLogger(treeSqlProvider.getClass());	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    public String updateTreeLeaf(TreeEntity<?> entity) {	
        Object object;	
        StringBuilder a;	
        void a2;	
        long a3;	
        StringBuilder stringBuilder;	
        String a4;	
        String a5;	
        block2 : {	
            a3 = System.currentTimeMillis();	
            a = new StringBuilder();	
            TreeEntity<?> treeEntity = entity;	
            a5 = treeEntity.getIdColumnName();	
            a4 = treeEntity.getIdAttrName();	
            Table a6 = MapperHelper.getTable(treeEntity);	
            String a7 = MapperHelper.getTableName(a6, entity);	
            a.append("UPDATE");	
            a.append(" " + a7);	
            a.append(" SET");	
            a.append(" tree_leaf = (");	
            a.append(" SELECT tree_leaf FROM (");	
            a.append(" SELECT (case when count(1) > 0 then '0' else '1' end) tree_leaf");	
            a.append(new StringBuilder().insert(0, " FROM ").append(a7).append("").toString());	
            ArrayList<Column> a8 = ListUtils.newArrayList();	
            object = MapperHelper.getColumns(a6, a8).iterator();	
            while (object.hasNext()) {	
                Column a9 = object.next();	
                if (!"status".equals(a9.name())) continue;	
                StringBuilder stringBuilder2 = a;	
                stringBuilder = stringBuilder2;	
                stringBuilder2.append(" WHERE status = '0'");	
                break block2;	
            }	
            stringBuilder = a;	
        }	
        stringBuilder.append(new StringBuilder().insert(0, " AND parent_code = #{").append(a4).append("}").toString());	
        a.append(" ) a");	
        a.append(" )");	
        a.append(" WHERE");	
        a.append(new StringBuilder().insert(0, " ").append(a5).append(" = #{").append(a4).append("}").toString());	
        object = a.toString();	
        if (this.logger.isDebugEnabled()) {	
            this.logger.debug(new StringBuilder().insert(0, TimeUtils.formatDateAgo(System.currentTimeMillis() - a3)).append(": ").append((String)a2).toString());	
        }	
        return a2;	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
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
        if (StringUtils.isBlank(a6) || StringUtils.isBlank(a7)) {	
            throw new MapperException(new StringBuilder().insert(0, "Error: ").append(entity.getClass()).append(" 没有找到isTreeName为true的列.").toString());	
        }	
        a3.append("SELECT");	
        a3.append(" a." + a4 + " AS \"" + a5 + "\",");	
        a3.append(" a.parent_code AS \"parentCode\",");	
        a3.append(" a.parent_codes AS \"parentCodes\",");	
        a3.append(" a.tree_sort AS \"treeSort\",");	
        a3.append(" a.tree_sorts AS \"treeSorts\",");	
        a3.append(" a.tree_level AS \"treeLevel\",");	
        a3.append(" a.tree_names AS \"treeNames\",");	
        a3.append(new StringBuilder().insert(0, " a.").append(a6).append(" AS \"treeName\",").toString());	
        a3.append(new StringBuilder().insert(0, " a.").append(a6).append(" AS \"").append(a7).append("\"").toString());	
        a3.append(new StringBuilder().insert(0, " FROM ").append(MapperHelper.getTableName(a8, entity)).append(" a").toString());	
        a3.append(" WHERE a.parent_codes LIKE #{parentCodes}");	
        a3.append(" ORDER BY a.tree_sorts");	
        object = a3.toString();	
        if (this.logger.isDebugEnabled()) {	
            this.logger.debug(new StringBuilder().insert(0, TimeUtils.formatDateAgo(System.currentTimeMillis() - a2)).append(": ").append((String)a).toString());	
        }	
        return a;	
    }	
}	
	
