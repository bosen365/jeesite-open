/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.collect.MapUtils	
 */	
package com.jeesite.modules.msg.entity.content;	
	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.modules.msg.entity.content.BaseMsgContent;	
import java.util.Map;	
	
public class AppMsgContent	
extends BaseMsgContent {	
    private Map<String, String> params = MapUtils.newHashMap();	
    private static final long serialVersionUID = 1L;	
	
    public void setParams(Map<String, String> params) {	
        this.params = params;	
    }	
	
    @Override	
    public String getMsgType() {	
        return "app";	
    }	
	
    public Map<String, String> getParams() {	
        return this.params;	
    }	
}	
	
