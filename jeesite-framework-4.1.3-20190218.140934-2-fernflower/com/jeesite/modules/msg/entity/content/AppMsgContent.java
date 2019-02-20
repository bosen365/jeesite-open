package com.jeesite.modules.msg.entity.content;	
	
import com.jeesite.common.collect.MapUtils;	
import java.util.Map;	
	
public class AppMsgContent extends BaseMsgContent {	
   private Map params = MapUtils.newHashMap();	
   private static final long serialVersionUID = 1L;	
	
   public void setParams(Map params) {	
      this.params = params;	
   }	
	
   public String getMsgType() {	
      return "app";	
   }	
	
   public Map getParams() {	
      return this.params;	
   }	
}	
