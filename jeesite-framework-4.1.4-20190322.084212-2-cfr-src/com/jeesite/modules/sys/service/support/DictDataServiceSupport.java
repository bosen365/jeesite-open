/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.modules.sys.service.support;	
	
import com.jeesite.common.dao.QueryDao;	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.entity.Extend;	
import com.jeesite.common.entity.TreeEntity;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.mapper.SqlMap;	
import com.jeesite.common.mybatis.mapper.query.QueryType;	
import com.jeesite.common.mybatis.mapper.query.QueryWhere;	
import com.jeesite.common.service.TreeService;	
import com.jeesite.modules.sys.dao.DictDataDao;	
import com.jeesite.modules.sys.entity.DictData;	
import com.jeesite.modules.sys.service.DictDataService;	
import com.jeesite.modules.sys.utils.CorpUtils;	
import com.jeesite.modules.sys.utils.DictUtils;	
import java.util.List;	
import org.hyperic.sigar.pager.PageList;	
import org.springframework.transaction.annotation.Transactional;	
	
@Transactional(readOnly=true)	
public class DictDataServiceSupport	
extends TreeService<DictDataDao, DictData>	
implements DictDataService {	
    @Override	
    public List<DictData> findList(DictData dictData) {	
        DictData dictData2 = dictData;	
        dictData.getSqlMap().getWhere().disableAutoAddCorpCodeWhere();	
        dictData2.getSqlMap().getWhere().andBracket("is_sys", QueryType.EQ, "1").or("corp_code", QueryType.EQ, CorpUtils.getCurrentCorpCode()).endBracket();	
        return super.findList(dictData2);	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    @Transactional(readOnly=false)	
    @Override	
    public void updateDictTypeByDictType(String newDictType, String oldDictType) {	
        void a;	
        if (StringUtils.isBlank(newDictType) || StringUtils.isBlank(oldDictType)) {	
            return;	
        }	
        DictData a2 = new DictData();	
        a2.setDictType(newDictType);	
        DictData dictData = new DictData();	
        a.setDictType(oldDictType);	
        ((DictDataDao)this.dao).updateByEntity(a2, a);	
        DictUtils.clearDictCache();	
    }	
	
    @Override	
    public DictData get(DictData dictData) {	
        return super.get(dictData);	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void save(DictData dictData) {	
        super.save(dictData);	
        DictUtils.clearDictCache();	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void updateStatus(DictData dictData) {	
        super.updateStatus(dictData);	
        DictUtils.clearDictCache();	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void deleteByDictType(String dictType) {	
        if (StringUtils.isBlank(dictType)) {	
            return;	
        }	
        DictData a = new DictData();	
        a.setDictType(dictType);	
        ((DictDataDao)this.dao).deleteByEntity(a);	
        DictUtils.clearDictCache();	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void delete(DictData dictData) {	
        super.delete(dictData);	
        DictUtils.clearDictCache();	
    }	
}	
	
