/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.lang.StringUtils	
 *  org.springframework.transaction.annotation.Transactional	
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
import org.hyperic.sigar.ProcTime;	
import org.springframework.transaction.annotation.Transactional;	
	
@Transactional(readOnly=true)	
public class DictDataServiceSupport	
extends TreeService<DictDataDao, DictData>	
implements DictDataService {	
    @Transactional(readOnly=false)	
    @Override	
    public void delete(DictData dictData) {	
        super.delete(dictData);	
        DictUtils.clearDictCache();	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void updateStatus(DictData dictData) {	
        super.updateStatus(dictData);	
        DictUtils.clearDictCache();	
    }	
	
    @Override	
    public DictData get(DictData dictData) {	
        return super.get(dictData);	
    }	
	
    @Override	
    public List<DictData> findList(DictData dictData) {	
        DictData dictData2 = dictData;	
        dictData2.getSqlMap().getWhere().andBracket("is_sys", QueryType.EQ, "1").or("corp_code", QueryType.EQ, CorpUtils.getCurrentCorpCode()).endBracket();	
        dictData.getSqlMap().getWhere().disableAutoAddCorpCodeWhere();	
        return super.findList(dictData2);	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void updateDictTypeByDictType(String newDictType, String oldDictType) {	
        void a2;	
        if (StringUtils.isBlank((CharSequence)newDictType) || StringUtils.isBlank((CharSequence)oldDictType)) {	
            return;	
        }	
        DictData a3 = new DictData();	
        a3.setDictType(newDictType);	
        DictData dictData = new DictData();	
        a2.setDictType(oldDictType);	
        DictUtils.clearDictCache();	
        ((DictDataDao)this.dao).updateByEntity(a3, a2);	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void save(DictData dictData) {	
        super.save(dictData);	
        DictUtils.clearDictCache();	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void deleteByDictType(String dictType) {	
        if (StringUtils.isBlank((CharSequence)dictType)) {	
            return;	
        }	
        DictData a2 = new DictData();	
        a2.setDictType(dictType);	
        DictUtils.clearDictCache();	
        ((DictDataDao)this.dao).deleteByEntity(a2);	
    }	
}	
	
