package com.jeesite.common.shiro.web;	
	
import com.jeesite.common.collect.ListUtils;	
import java.util.ArrayList;	
import java.util.Collection;	
import org.apache.shiro.SecurityUtils;	
import org.apache.shiro.authc.AuthenticationException;	
import org.apache.shiro.authc.AuthenticationInfo;	
import org.apache.shiro.authc.AuthenticationToken;	
import org.apache.shiro.authc.pam.AllSuccessfulStrategy;	
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;	
import org.apache.shiro.authz.ModularRealmAuthorizer;	
import org.apache.shiro.crypto.CryptoException;	
import org.apache.shiro.realm.Realm;	
import org.apache.shiro.subject.PrincipalCollection;	
import org.apache.shiro.subject.SubjectContext;	
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;	
	
public class WebSecurityManager extends DefaultWebSecurityManager {	
   public void setRealms(Collection realms) {	
      super.setRealms(realms);	
      if (this.getAuthorizer() instanceof ModularRealmAuthorizer) {	
         ModularRealmAuthorizer a = (ModularRealmAuthorizer)this.getAuthorizer();	
         ArrayList a;	
         (a = ListUtils.newArrayList()).add(realms.iterator().next());	
         a.setRealms(a);	
      }	
	
   }	
	
   public WebSecurityManager(Realm singleRealm) {	
      super(singleRealm);	
      this.initialize();	
   }	
	
   public WebSecurityManager(Collection realms) {	
      super(realms);	
      this.initialize();	
   }	
	
   // $FF: synthetic method	
   private void initialize() {	
      this.setRememberMeManager(new RememberMeManager());	
      ((ModularRealmAuthenticator)this.getAuthenticator()).setAuthenticationStrategy(new AllSuccessfulStrategy() {	
         public AuthenticationInfo beforeAttempt(Realm a1, AuthenticationToken a2, AuthenticationInfo ax) throws AuthenticationException {	
            return ax;	
         }	
      });	
      SecurityUtils.setSecurityManager(this);	
   }	
	
   public WebSecurityManager() {	
      this.initialize();	
   }	
	
   protected PrincipalCollection getRememberedIdentity(SubjectContext subjectContext) {	
      try {	
         return super.getRememberedIdentity(subjectContext);	
      } catch (CryptoException var3) {	
         return null;	
      }	
   }	
}	
