package com.jeesite.modules.msg.task;	
	
import com.jeesite.modules.msg.entity.MsgPush;	
	
public interface MsgPushTask {	
   void execute();	
	
   boolean executeMsgPush(MsgPush var1);	
}	
