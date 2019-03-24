/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.modules.msg.task;	
	
import com.jeesite.modules.msg.entity.MsgPush;	
	
public interface MsgPushTask {	
    public void execute();	
	
    public boolean executeMsgPush(MsgPush var1);	
}	
	
