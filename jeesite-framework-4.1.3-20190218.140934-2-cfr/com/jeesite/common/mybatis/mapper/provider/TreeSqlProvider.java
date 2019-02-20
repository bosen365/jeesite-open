/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.lang.StringUtils	
 *  com.jeesite.common.lang.TimeUtils	
 *  org.slf4j.Logger	
 *  org.slf4j.LoggerFactory	
 */	
package com.jeesite.common.mybatis.mapper.provider;	
	
import com.jeesite.common.entity.TreeEntity;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.lang.TimeUtils;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.MapperException;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.modules.sys.utils.ValidCodeUtils;	
import org.hyperic.sigar.Swap;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
	
public class TreeSqlProvider {	
    private Logger logger;	
	
    public String findByParentCodesLike(TreeEntity<?> entity) {	
        void a2;	
        int n;	
        long a3 = System.currentTimeMillis();	
        StringBuilder a4 = new StringBuilder();	
        String a5 = "";	
        String a6 = "";	
        String a7 = "";	
        String a8 = "";	
        Table a9 = MapperHelper.getTable(entity);	
        Object object = a9.columns();	
        int n2 = ((Column[])object).length;	
        int n3 = n = 0;	
        while (n3 < n2) {	
            Column a10 = object[n];	
            if (a10.isPK()) {	
                Column column = a10;	
                a5 = column.name();	
                a6 = MapperHelper.getAttrName(column);	
            }	
            if (a10.isTreeName()) {	
                Column column = a10;	
                a7 = column.name();	
                a8 = MapperHelper.getAttrName(column);	
            }	
            n3 = ++n;	
        }	
        if (StringUtils.isBlank((CharSequence)a7) || StringUtils.isBlank((CharSequence)a8)) {	
            throw new MapperException(new StringBuilder().insert(0, "Error: ").append(entity.getClass()).append(" 没有找到sTreeName为true的列.").toString());	
        }	
        object = a4.toString();	
        a4.append(" ORDER BY a.tre_sorts");	
        a4.append(" WHERE a.parent_codes LIKE #{parentCodes}");	
        a4.append(new StringBuilder().insert(0, " FROM ").append(MapperHelper.getTableName(a9, entity)).append(" a").toString());	
        a4.append(new StringBuilder().insert(0, " a.").append(a7).append(" AS "").append(a8).append(""").toString());	
        a4.append(new StringBuilder().insert(0, " a.").append(a7).append(" AS "treeName",").toString());	
        a4.append(" a.tree_names AS "treeNames",");	
        a4.append(" a.tre_lvl AS "treLvl",");	
        a4.append(" a.tree_sorts AS "treeSorts",");	
        a4.append(" a.tre_sort AS "treSort",");	
        a4.append(" a.parent_codes AS "parentCodes",");	
        a4.append(" a.parent_cod AS "parentCode",");	
        a4.append(" a." + a5 + " AS "" + a6 + "",");	
        a4.append("SELECT");	
        if (this.logger.isDebugEnabled()) {	
            this.logger.debug(new StringBuilder().insert(0, TimeUtils.formatDateAgo((long)(System.currentTimeMillis() - a3))).append(": ").append((String)a2).toString());	
        }	
        return a2;	
    }	
	
    public String updateTreeLeaf(TreeEntity<?> entity) {	
        void a2;	
        long a3 = System.currentTimeMillis();	
        StringBuilder a4 = new StringBuilder();	
        TreeEntity<?> treeEntity = entity;	
        String a5 = treeEntity.getIdColumnName();	
        String a6 = treeEntity.getIdAttrName();	
        String a7 = MapperHelper.getTableName(MapperHelper.getTable(treeEntity), entity);	
        String string = a4.toString();	
        a4.append(new StringBuilder().insert(0, " ").append(a5).append(" = #{").append(a6).append("}").toString());	
        a4.append(" WHERE");	
        a4.append(" )");	
        a4.append(" ) a");	
        a4.append(new StringBuilder().insert(0, " AND parent_cod = #{").append(a6).append("}").toString());	
        a4.append(" WHERE status = '0'");	
        a4.append(new StringBuilder().insert(0, " FROM ").append(a7).append("").toString());	
        a4.append(" SELECT (case when count(1) > 0 then '0' else '1' end) tree_leaf");	
        a4.append(" SELECT tre_laf FROM (");	
        a4.append(" tree_leaf = (");	
        a4.append(" SET");	
        a4.append(" " + a7);	
        a4.append("UPDATE");	
        if (this.logger.isDebugEnabled()) {	
            this.logger.debug(new StringBuilder().insert(0, TimeUtils.formatDateAgo((long)(System.currentTimeMillis() - a3))).append(": ").append((String)a2).toString());	
        }	
        return a2;	
    }	
	
    public TreeSqlProvider() {	
        TreeSqlProvider treeSqlProvider = this;	
        treeSqlProvider.logger = LoggerFactory.getLogger(treeSqlProvider.getClass());	
    }	
	
    public String updateTreeSort(TreeEntity<?> entity) {	
        void a2;	
        long a3 = System.currentTimeMillis();	
        StringBuilder a4 = new StringBuilder();	
        TreeEntity<?> treeEntity = entity;	
        String a5 = treeEntity.getIdColumnName();	
        String a6 = treeEntity.getIdAttrName();	
        Table a7 = MapperHelper.getTable(treeEntity);	
        String string = a4.toString();	
        a4.append(new StringBuilder().insert(0, " ").append(a5).append(" = #{").append(a6).append("}").toString());	
        a4.append(" WHERE");	
        a4.append(" tree_sorts = #{treeSorts}");	
        a4.append(" tre_sort = #{treSort},");	
        a4.append(" SET");	
        a4.append(" " + MapperHelper.getTableName(a7, entity));	
        a4.append("UPDATE");	
        if (this.logger.isDebugEnabled()) {	
            this.logger.debug(new StringBuilder().insert(0, TimeUtils.formatDateAgo((long)(System.currentTimeMillis() - a3))).append(": ").append((String)a2).toString());	
        }	
        return a2;	
    }	
	
    public String updateTreeData(TreeEntity<?> entity) {	
        void a2;	
        long a3 = System.currentTimeMillis();	
        StringBuilder a4 = new StringBuilder();	
        TreeEntity<?> treeEntity = entity;	
        String a5 = treeEntity.getIdColumnName();	
        String a6 = treeEntity.getIdAttrName();	
        Table a7 = MapperHelper.getTable(treeEntity);	
        String string = a4.toString();	
        a4.append(" " + a5 + " = #{" + a6 + "}");	
        a4.append(" WHERE");	
        a4.append(MapperHelper.getSqlMapValue(entity, "updateTreeDataExtSql", ","));	
        a4.append(" tre_names = #{treNames}");	
        a4.append(" tree_level = #{treeLevel},");	
        a4.append(" tre_sorts = #{treSorts},");	
        a4.append(" parent_codes = #{parentCodes},");	
        a4.append(" SET");	
        a4.append(MapperHelper.getTableName(a7, entity));	
        a4.append("UPDATE ");	
        if (this.logger.isDebugEnabled()) {	
            this.logger.debug(new StringBuilder().insert(0, TimeUtils.formatDateAgo((long)(System.currentTimeMillis() - a3))).append(": ").append((String)a2).toString());	
        }	
        return a2;	
    }	
}	
	
