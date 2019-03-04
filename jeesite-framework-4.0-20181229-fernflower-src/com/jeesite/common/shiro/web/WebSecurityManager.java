package com.jeesite.common.shiro.web;	
	
import java.util.Collection;	
import org.apache.shiro.crypto.CryptoException;	
import org.apache.shiro.realm.Realm;	
import org.apache.shiro.subject.PrincipalCollection;	
import org.apache.shiro.subject.SubjectContext;	
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;	
	
public class WebSecurityManager extends DefaultWebSecurityManager {	
   public WebSecurityManager(Realm singleRealm) {	
      super(singleRealm);	
      this.initialize();	
   }	
	
   protected PrincipalCollection getRememberedIdentity(SubjectContext subjectContext) {	
      try {	
         return super.getRememberedIdentity(subjectContext);	
      } catch (CryptoException var3) {	
         return null;	
      }	
   }	
	
   public WebSecurityManager() {	
      this.initialize();	
   }	
	
   public WebSecurityManager(Collection realms) {	
      super(realms);	
      this.initialize();	
   }	
	
   // $FF: synthetic method	
   private void initialize() {	
      this.setRememberMeManager(new RememberMeManager());	
   }	
}	
