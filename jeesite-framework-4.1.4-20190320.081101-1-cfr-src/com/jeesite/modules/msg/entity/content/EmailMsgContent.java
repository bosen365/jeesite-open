/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.modules.msg.entity.content;	
	
import com.jeesite.modules.msg.entity.content.BaseMsgContent;	
	
public class EmailMsgContent	
extends BaseMsgContent {	
    private String cc;	
    private static final long serialVersionUID = 1L;	
    private String bcc;	
	
    public String getBcc() {	
        return this.bcc;	
    }	
	
    public void setCc(String cc) {	
        this.cc = cc;	
    }	
	
    public void setBcc(String bcc) {	
        this.bcc = bcc;	
    }	
	
    public String getCc() {	
        return this.cc;	
    }	
	
    @Override	
    public String getMsgType() {	
        return "email";	
    }	
}	
	
