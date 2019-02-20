package com.jeesite.modules.msg.service;	
	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.service.api.CrudServiceApi;	
import com.jeesite.modules.msg.entity.MsgTemplate;	
	
public interface MsgTemplateService extends CrudServiceApi {	
   Page findPage(MsgTemplate var1);	
	
   MsgTemplate get(MsgTemplate var1);	
	
   void updateStatus(MsgTemplate var1);	
	
   void delete(MsgTemplate var1);	
	
   void save(MsgTemplate var1);	
}	
