/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  com.alibaba.druid.filter.FilterAdapter	
 *  com.alibaba.druid.pool.DruidDataSource	
 *  com.alibaba.druid.proxy.jdbc.DataSourceProxy	
 *  com.alibaba.druid.support.logging.Log	
 *  com.alibaba.druid.support.logging.LogFactory	
 *  com.jeesite.common.lang.ObjectUtils	
 */	
package com.jeesite.common.datasource.d;	
	
import com.alibaba.druid.filter.FilterAdapter;	
import com.alibaba.druid.pool.DruidDataSource;	
import com.alibaba.druid.proxy.jdbc.DataSourceProxy;	
import com.alibaba.druid.support.logging.Log;	
import com.alibaba.druid.support.logging.LogFactory;	
import com.jeesite.autoconfigure.sys.MsgAutoConfiguration;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.modules.sys.utils.ModuleUtils;	
	
public class i	
extends FilterAdapter {	
    private String b;	
    private static Log ALLATORIxDEMO = LogFactory.getLog(i.class);	
	
    public i(String jdbcPrefix) {	
        this.b = jdbcPrefix;	
    }	
	
    public void init(DataSourceProxy dataSourceProxy) {	
        String a;	
        if (!(dataSourceProxy instanceof DruidDataSource)) {	
            ALLATORIxDEMO.error("ConfigFilter only support DruidDataSource");	
        }	
        DruidDataSource a2 = (DruidDataSource)dataSourceProxy;	
        String a3 = "72d1af0aec0114d3300ddb40cc17e90b";	
        if (ObjectUtils.toBoolean((Object)Global.getProperty(new StringBuilder().insert(0, this.b).append(".encrypt.username").toString())).booleanValue()) {	
            DruidDataSource druidDataSource = a2;	
            String string = druidDataSource.getUsername();	
            a = Global.getPropertyDecodeAndEncode(a3, this.b + ".username", a);	
            druidDataSource.setUsername(a);	
        }	
        if (ObjectUtils.toBoolean((Object)Global.getProperty(new StringBuilder().insert(0, this.b).append(".encrypt.password").toString())).booleanValue()) {	
            DruidDataSource druidDataSource = a2;	
            a = druidDataSource.getPassword();	
            a = Global.getPropertyDecodeAndEncode(a3, new StringBuilder().insert(0, this.b).append(".password").toString(), a);	
            druidDataSource.setPassword(a);	
        }	
    }	
}	
	
