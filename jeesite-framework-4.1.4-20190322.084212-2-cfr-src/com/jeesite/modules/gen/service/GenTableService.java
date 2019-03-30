/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.modules.gen.service;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.dao.QueryDao;	
import com.jeesite.common.datasource.DataSourceHolder;	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.j2cache.cache.support.utils.J2CacheConfigUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.common.mybatis.mapper.SqlMap;	
import com.jeesite.common.service.CrudService;	
import com.jeesite.modules.gen.dao.GenDataDictDao;	
import com.jeesite.modules.gen.dao.GenTableColumnDao;	
import com.jeesite.modules.gen.dao.GenTableDao;	
import com.jeesite.modules.gen.entity.GenTable;	
import com.jeesite.modules.gen.entity.GenTableColumn;	
import com.jeesite.modules.gen.entity.config.GenConfig;	
import com.jeesite.modules.gen.entity.config.GenTemplate;	
import com.jeesite.modules.gen.utils.GenUtils;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Map;	
import org.hyperic.sigar.pager.SortAttribute;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;	
import org.springframework.stereotype.Service;	
import org.springframework.transaction.annotation.Transactional;	
	
@Service	
@Transactional(readOnly=true)	
@ConditionalOnProperty(name={"gen.enabled"}, havingValue="true", matchIfMissing=true)	
public class GenTableService	
extends CrudService<GenTableDao, GenTable> {	
    @Autowired	
    private GenDataDictDao genDataDictDao;	
    @Autowired	
    private GenTableColumnDao genTableColumnDao;	
	
    public boolean checkTableName(String tableName) {	
        if (StringUtils.isBlank(tableName)) {	
            return true;	
        }	
        return ((GenTableDao)this.dao).get(new GenTable(tableName)) == null;	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    @Transactional(readOnly=false)	
    @Override	
    public void delete(GenTable genTable) {	
        void a;	
        GenTable genTable2 = genTable;	
        super.delete(genTable2);	
        if (StringUtils.isBlank(genTable2.getTableName())) {	
            return;	
        }	
        GenTableColumn genTableColumn = new GenTableColumn();	
        a.setGenTable(new GenTable(genTable.getTableName()));	
        this.genTableColumnDao.deleteByEntity(a);	
    }	
	
    /*	
     * Unable to fully structure code	
     * Enabled aggressive block sorting	
     * Enabled unnecessary exception pruning	
     * Enabled aggressive exception aggregation	
     * Lifted jumps to return sites	
     */	
    public GenTable getFromDb(GenTable genTable) {	
        block17 : {	
            block18 : {	
                if (StringUtils.isNotBlank(genTable.getTableName()) == false) return genTable;	
                a = genTable.getDataSourceName();	
                if (StringUtils.isNotBlank(a)) {	
                    try {	
                        DataSourceHolder.setDataSourceName(a);	
                        v0 = this;	
                    }	
                    catch (Exception a) {	
                        return genTable;	
                    }	
                } else {	
                    v0 = this;	
                }	
                a = v0.genDataDictDao.findTableList(genTable);	
                if (a.size() <= 0) break block17;	
                if (!genTable.getIsNewRecord()) break block18;	
                genTable = a.get(0);	
                if (StringUtils.isNotBlank(a)) {	
                    genTable.setDataSourceName(a);	
                }	
                if (StringUtils.isBlank(genTable.getComments())) {	
                    v1 = genTable;	
                    v1.setComments(v1.getTableName());	
                }	
                v2 = genTable;	
                v2.setFunctionName(v2.getComments());	
                v2.setFunctionNameSimple(v2.getComments());	
                v3 = genTable;	
                if (StringUtils.startsWith(v2.getTableName(), Global.getTablePrefix())) {	
                    a = StringUtils.substringAfter(v3.getTableName(), Global.getTablePrefix());	
                    v4 = genTable;	
                    v5 = v4;	
                    v4.setClassName(StringUtils.capCamelCase((String)a));	
                } else {	
                    v3.setClassName(StringUtils.capCamelCase(genTable.getTableName()));	
                    v5 = genTable;	
                }	
                if (StringUtils.startsWith(v5.getClassName(), "Sys")) {	
                    v6 = genTable;	
                    v6.setClassName(StringUtils.substringAfter(v6.getClassName(), "Sys"));	
                }	
                if (!StringUtils.isBlank(genTable.getPackageName())) ** GOTO lbl43	
                a = Global.getConfig("gen.defaultPackageName");	
                v7 = genTable;	
                if (StringUtils.isNotBlank((CharSequence)a)) {	
                    v7.setPackageName((String)a);	
                    v8 = genTable;	
                } else {	
                    v7.setPackageName("com.jeesite.modules");	
lbl43: // 2 sources:	
                    v8 = genTable;	
                }	
                if (StringUtils.isBlank(v8.getModuleName())) {	
                    a = genTable.getTableName();	
                    if (StringUtils.startsWith((CharSequence)a, Global.getTablePrefix())) {	
                        a = StringUtils.substringAfter((String)a, Global.getTablePrefix());	
                    }	
                    genTable.setModuleName(StringUtils.substringBefore((String)a, "_"));	
                }	
            }	
            a = this.genDataDictDao.findTableColumnList(genTable);	
            var5_6 = a.iterator();	
            while (var5_6.hasNext()) {	
                a = var5_6.next();	
                a = false;	
                for (GenTableColumn a : genTable.getColumnList()) {	
                    if (!a.getColumnName().equals(a.getColumnName())) continue;	
                    a = true;	
                }	
                if (a) continue;	
                genTable.getColumnList().add(a);	
            }	
            for (GenTableColumn a : genTable.getColumnList()) {	
                a = false;	
                var8_9 = a.iterator();	
                while (var8_9.hasNext()) {	
                    a = var8_9.next();	
                    if (!a.getColumnName().equals(a.getColumnName())) continue;	
                    a = true;	
                }	
                if (a) continue;	
                a.setStatus("1");	
            }	
            v9 = genTable;	
            v9.setPkList(this.genDataDictDao.findTablePK(v9));	
            GenUtils.initColumnField(genTable);	
        }	
        if (StringUtils.isNotBlank(a) == false) return genTable;	
        DataSourceHolder.clearDataSourceName();	
        return genTable;	
    }	
	
    @Override	
    public GenTable get(String tableName) {	
        GenTable a = (GenTable)super.get(tableName);	
        if (a != null) {	
            GenTableColumn a2 = new GenTableColumn(a);	
            a.setColumnList(this.genTableColumnDao.findList(a2));	
        }	
        return a;	
    }	
	
    @Override	
    public Page<GenTable> findPage(GenTable genTable) {	
        String a = new StringBuilder().insert(0, "(SELECT count(1) FROM ").append(MapperHelper.getTableName(genTable)).append(" WHERE parent_table_name=a.table_name) AS \"childNum\"").toString();	
        GenTable genTable2 = genTable;	
        genTable2.getSqlMap().add("extColumn", a);	
        return super.findPage(genTable2);	
    }	
	
    public List<GenTable> findListFromDb(GenTable genTable) {	
        GenTableService genTableService;	
        String a = genTable.getDataSourceName();	
        if (StringUtils.isNotBlank(a)) {	
            try {	
                DataSourceHolder.setDataSourceName(a);	
                genTableService = this;	
            }	
            catch (Exception a2) {	
                return ListUtils.newArrayList();	
            }	
        } else {	
            genTableService = this;	
        }	
        List<GenTable> a3 = genTableService.genDataDictDao.findTableList(genTable);	
        if (StringUtils.isNotBlank(a)) {	
            DataSourceHolder.clearDataSourceName();	
        }	
        return a3;	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    public List<GenTable> findGenBaseDirList(GenTable genTable) {	
        void genTable2;	
        genTable2.setPage(new Page(1, 20, -1L));	
        return ((GenTableDao)this.dao).findGenBaseDirList((GenTable)genTable2);	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void save(GenTable genTable) {	
        GenTable genTable2 = genTable;	
        super.save(genTable2);	
        for (GenTableColumn a : genTable2.getColumnList()) {	
            a.setGenTable(genTable);	
            if (StringUtils.isBlank(a.getId())) {	
                this.genTableColumnDao.insert(a);	
                continue;	
            }	
            if ("1".equals(a.getStatus())) {	
                this.genTableColumnDao.delete(a);	
                continue;	
            }	
            this.genTableColumnDao.update(a);	
        }	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    public String generateCode(GenTable genTable) {	
        Iterator<GenTemplate> a;	
        Object a2;	
        Map<String, Object> a3;	
        StringBuilder a4 = new StringBuilder();	
        GenConfig genConfig = GenUtils.getConfig();	
        List<GenTemplate> a5 = GenUtils.getTemplateList(genConfig, genTable.getTplCategory(), false);	
        List<GenTemplate> a6 = GenUtils.getTemplateList(genConfig, genTable.getTplCategory(), true);	
        if (a6.size() > 0) {	
            GenTable genTable2 = new GenTable();	
            GenTable genTable3 = genTable;	
            ((DataEntity)((Object)a3)).setStatus("0");	
            ((GenTable)((Object)a3)).setParentTableName(genTable3.getTableName());	
            genTable3.setChildList(((GenTableDao)this.dao).findList(a3));	
        }	
        if (a6.size() > 0 && genTable.getChildList().size() > 0) {	
            for (GenTable genTable4 : genTable.getChildList()) {	
                Iterator<GenTemplate> iterator = a;	
                ((GenTable)((Object)iterator)).setParent(genTable);	
                ((GenTable)((Object)iterator)).setColumnList(this.genTableColumnDao.findList(new GenTableColumn((GenTable)((Object)a))));	
                a2 = GenUtils.getDataModel(iterator);	
                Iterator<GenTemplate> iterator2 = a6.iterator();	
                while (iterator2.hasNext()) {	
                    void a7;	
                    Iterator<GenTemplate> iterator3;	
                    GenTemplate genTemplate = iterator3.next();	
                    iterator2 = iterator3;	
                    a4.append(GenUtils.generateToFile((GenTemplate)a7, (Map<String, Object>)a2, a));	
                }	
            }	
        }	
        a3 = GenUtils.getDataModel(genTable);	
        Iterator<GenTemplate> iterator = a = a5.iterator();	
        while (iterator.hasNext()) {	
            a2 = a.next();	
            iterator = a;	
            a4.append(GenUtils.generateToFile((GenTemplate)a2, a3, genTable));	
        }	
        return a4.toString();	
    }	
}	
	
