/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.service;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.service.api.BaseServiceApi;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
	
public abstract class BaseService	
implements BaseServiceApi {	
    protected Logger logger;	
	
    @Override	
    public String text(String code, String ... params) {	
        return Global.getText(code, params);	
    }	
	
    public BaseService() {	
        BaseService baseService = this;	
        baseService.logger = LoggerFactory.getLogger(baseService.getClass());	
    }	
}	
	
