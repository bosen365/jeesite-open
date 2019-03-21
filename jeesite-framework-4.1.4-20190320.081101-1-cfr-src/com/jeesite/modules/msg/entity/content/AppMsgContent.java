/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.modules.msg.entity.content;	
	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.modules.msg.entity.content.BaseMsgContent;	
import java.util.Map;	
	
public class AppMsgContent	
extends BaseMsgContent {	
    private Map<String, String> params = MapUtils.newHashMap();	
    private static final long serialVersionUID = 1L;	
	
    public Map<String, String> getParams() {	
        return this.params;	
    }	
	
    @Override	
    public String getMsgType() {	
        return "app";	
    }	
	
    public void setParams(Map<String, String> params) {	
        this.params = params;	
    }	
}	
	
