package com.jeesite.common.shiro.web;	
	
import com.jeesite.common.shiro.realm.LoginInfo;	
import org.apache.shiro.codec.Base64;	
import org.apache.shiro.web.mgt.CookieRememberMeManager;	
	
public class RememberMeManager extends CookieRememberMeManager {	
   public RememberMeManager() {	
      this.setCipherKey(Base64.decode("r0e3c16IdVkouZgk1TKVMg=="));	
   }	
}	
