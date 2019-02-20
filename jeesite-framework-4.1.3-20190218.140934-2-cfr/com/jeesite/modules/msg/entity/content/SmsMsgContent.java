/*	
 * Decompiled with CFR 0.139.	
 */	
package com.jeesite.modules.msg.entity.content;	
	
import com.jeesite.modules.msg.entity.content.BaseMsgContent;	
	
public class SmsMsgContent	
extends BaseMsgContent {	
    private static final long serialVersionUID = 1L;	
	
    @Override	
    public String getMsgType() {	
        return "sms";	
    }	
}	
	
