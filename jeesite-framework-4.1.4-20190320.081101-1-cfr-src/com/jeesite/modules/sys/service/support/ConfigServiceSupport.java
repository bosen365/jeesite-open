/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.modules.sys.service.support;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.service.CrudService;	
import com.jeesite.modules.sys.dao.ConfigDao;	
import com.jeesite.modules.sys.entity.Config;	
import com.jeesite.modules.sys.service.ConfigService;	
import org.springframework.transaction.annotation.Transactional;	
	
@Transactional(readOnly=true)	
public class ConfigServiceSupport	
extends CrudService<ConfigDao, Config>	
implements ConfigService {	
    @Transactional(readOnly=false)	
    @Override	
    public void save(Config config) {	
        if (config.getIsNewRecord()) {	
            config.setIsSys("0");	
        }	
        super.save(config);	
        Global.clearCache();	
    }	
	
    @Override	
    public Page<Config> findPage(Config config) {	
        return super.findPage(config);	
    }	
	
    @Override	
    public Config get(Config config) {	
        return super.get(config);	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void delete(Config config) {	
        super.delete(config);	
        Global.clearCache();	
    }	
}	
	
