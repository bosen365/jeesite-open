package com.jeesite.modules.msg.service;	
	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.service.api.CrudServiceApi;	
import com.jeesite.modules.msg.entity.MsgPush;	
import java.util.List;	
	
public interface MsgPushService extends CrudServiceApi {	
   Page findPage(MsgPush var1);	
	
   MsgPush get(MsgPush var1);	
	
   void delete(MsgPush var1);	
	
   void save(MsgPush var1);	
	
   void updateMsgPush(MsgPush var1);	
	
   List findListByMergePush(MsgPush var1);	
}	
