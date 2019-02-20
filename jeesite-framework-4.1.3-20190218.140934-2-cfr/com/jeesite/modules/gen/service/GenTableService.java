/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.collect.ListUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  org.springframework.beans.factory.annotation.Autowired	
 *  org.springframework.boot.autoconfigure.condition.ConditionalOnProperty	
 *  org.springframework.stereotype.Service	
 *  org.springframework.transaction.annotation.Transactional	
 */	
package com.jeesite.modules.gen.service;	
	
import com.jeesite.autoconfigure.core.DataSourceAutoConfiguration;	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.dao.QueryDao;	
import com.jeesite.common.datasource.DataSourceHolder;	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.entity.Page;	
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
import org.hyperic.sigar.CpuPerc;	
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
    private GenTableColumnDao genTableColumnDao;	
    @Autowired	
    private GenDataDictDao genDataDictDao;	
	
    @Transactional(readOnly=false)	
    @Override	
    public void save(GenTable genTable) {	
        GenTable genTable2 = genTable;	
        super.save(genTable2);	
        for (GenTableColumn a2 : genTable2.getColumnList()) {	
            a2.setGenTable(genTable);	
            if (StringUtils.isBlank((CharSequence)a2.getId())) {	
                this.genTableColumnDao.insert(a2);	
                continue;	
            }	
            if ("1".equals(a2.getStatus())) {	
                this.genTableColumnDao.delete(a2);	
                continue;	
            }	
            this.genTableColumnDao.update(a2);	
        }	
    }	
	
    @Override	
    public Page<GenTable> findPage(GenTable genTable) {	
        String a2 = new StringBuilder().insert(0, "(SELECT count(1) FROM ").append(MapperHelper.getTableName(genTable)).append(" WHERE parent_table_name=a.table_name) AS "childNum"").toString();	
        GenTable genTable2 = genTable;	
        genTable2.getSqlMap().add("extColumn", a2);	
        return super.findPage(genTable2);	
    }	
	
    public List<GenTable> findGenBaseDirList(GenTable genTable) {	
        void genTable2;	
        genTable2.setPage(new Page(1, 20, -1L));	
        return ((GenTableDao)this.dao).findGenBaseDirList((GenTable)genTable2);	
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
                if (StringUtils.isNotBlank((CharSequence)genTable.getTableName()) == false) return genTable;	
                a = genTable.getDataSourceName();	
                if (StringUtils.isNotBlank((CharSequence)a)) {	
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
                if (StringUtils.isNotBlank((CharSequence)a)) {	
                    genTable.setDataSourceName(a);	
                }	
                if (StringUtils.isBlank((CharSequence)genTable.getComments())) {	
                    v1 = genTable;	
                    v1.setComments(v1.getTableName());	
                }	
                v2 = genTable;	
                v2.setFunctionName(v2.getComments());	
                v2.setFunctionNameSimple(v2.getComments());	
                v3 = genTable;	
                if (StringUtils.startsWith((CharSequence)v2.getTableName(), (CharSequence)Global.getTablePrefix())) {	
                    a = StringUtils.substringAfter((String)v3.getTableName(), (String)Global.getTablePrefix());	
                    v4 = genTable;	
                    v5 = v4;	
                    v4.setClassName(StringUtils.capCamelCase((String)a));	
                } else {	
                    v3.setClassName(StringUtils.capCamelCase((String)genTable.getTableName()));	
                    v5 = genTable;	
                }	
                if (StringUtils.startsWith((CharSequence)v5.getClassName(), (CharSequence)"Sys")) {	
                    v6 = genTable;	
                    v6.setClassName(StringUtils.substringAfter((String)v6.getClassName(), (String)"Sys"));	
                }	
                if (!StringUtils.isBlank((CharSequence)genTable.getPackageName())) ** GOTO lbl43	
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
                if (StringUtils.isBlank((CharSequence)v8.getModuleName())) {	
                    a = genTable.getTableName();	
                    if (StringUtils.startsWith((CharSequence)a, (CharSequence)Global.getTablePrefix())) {	
                        a = StringUtils.substringAfter((String)a, (String)Global.getTablePrefix());	
                    }	
                    genTable.setModuleName(StringUtils.substringBefore((String)a, (String)"_"));	
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
        if (StringUtils.isNotBlank((CharSequence)a) == false) return genTable;	
        DataSourceHolder.clearDataSourceName();	
        return genTable;	
    }	
	
    public boolean checkTableName(String tableName) {	
        if (StringUtils.isBlank((CharSequence)tableName)) {	
            return true;	
        }	
        return ((GenTableDao)this.dao).get(new GenTable(tableName)) == null;	
    }	
	
    public String generateCode(GenTable genTable) {	
        Iterator<GenTemplate> a2;	
        Object a3;	
        Map<String, Object> a4;	
        StringBuilder a5 = new StringBuilder();	
        GenConfig genConfig = GenUtils.getConfig();	
        List<GenTemplate> a6 = GenUtils.getTemplateList(genConfig, genTable.getTplCategory(), false);	
        List<GenTemplate> a7 = GenUtils.getTemplateList(genConfig, genTable.getTplCategory(), true);	
        if (a7.size() > 0) {	
            GenTable genTable2 = new GenTable();	
            GenTable genTable3 = genTable;	
            ((DataEntity)((Object)a4)).setStatus("0");	
            ((GenTable)((Object)a4)).setParentTableName(genTable3.getTableName());	
            genTable3.setChildList(((GenTableDao)this.dao).findList(a4));	
        }	
        if (a7.size() > 0 && genTable.getChildList().size() > 0) {	
            for (GenTable genTable4 : genTable.getChildList()) {	
                Iterator<GenTemplate> iterator = a2;	
                ((GenTable)((Object)iterator)).setParent(genTable);	
                ((GenTable)((Object)iterator)).setColumnList(this.genTableColumnDao.findList(new GenTableColumn((GenTable)((Object)a2))));	
                a3 = GenUtils.getDataModel(iterator);	
                Iterator<GenTemplate> iterator2 = a7.iterator();	
                while (iterator2.hasNext()) {	
                    void a8;	
                    Iterator<GenTemplate> iterator3;	
                    GenTemplate genTemplate = iterator3.next();	
                    iterator2 = iterator3;	
                    a5.append(GenUtils.generateToFile((GenTemplate)a8, (Map<String, Object>)a3, a2));	
                }	
            }	
        }	
        a4 = GenUtils.getDataModel(genTable);	
        Iterator<GenTemplate> iterator = a2 = a6.iterator();	
        while (iterator.hasNext()) {	
            a3 = a2.next();	
            iterator = a2;	
            a5.append(GenUtils.generateToFile((GenTemplate)a3, a4, genTable));	
        }	
        return a5.toString();	
    }	
	
    @Override	
    public GenTable get(String tableName) {	
        GenTable a2 = (GenTable)super.get(tableName);	
        if (a2 != null) {	
            GenTableColumn a3 = new GenTableColumn(a2);	
            a2.setColumnList(this.genTableColumnDao.findList(a3));	
        }	
        return a2;	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void delete(GenTable genTable) {	
        void a2;	
        GenTable genTable2 = genTable;	
        super.delete(genTable2);	
        if (StringUtils.isBlank((CharSequence)genTable2.getTableName())) {	
            return;	
        }	
        GenTableColumn genTableColumn = new GenTableColumn();	
        a2.setGenTable(new GenTable(genTable.getTableName()));	
        this.genTableColumnDao.deleteByEntity(a2);	
    }	
	
    public List<GenTable> findListFromDb(GenTable genTable) {	
        GenTableService genTableService;	
        String a2 = genTable.getDataSourceName();	
        if (StringUtils.isNotBlank((CharSequence)a2)) {	
            try {	
                DataSourceHolder.setDataSourceName(a2);	
                genTableService = this;	
            }	
            catch (Exception a3) {	
                return ListUtils.newArrayList();	
            }	
        } else {	
            genTableService = this;	
        }	
        List<GenTable> a4 = genTableService.genDataDictDao.findTableList(genTable);	
        if (StringUtils.isNotBlank((CharSequence)a2)) {	
            DataSourceHolder.clearDataSourceName();	
        }	
        return a4;	
    }	
}	
	
