/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.common.shiro.web;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.shiro.web.RememberMeManager;	
import com.jeesite.common.shiro.web.i;	
import java.util.ArrayList;	
import java.util.Collection;	
import java.util.Iterator;	
import org.apache.shiro.SecurityUtils;	
import org.apache.shiro.authc.Authenticator;	
import org.apache.shiro.authc.pam.AuthenticationStrategy;	
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;	
import org.apache.shiro.authz.Authorizer;	
import org.apache.shiro.authz.ModularRealmAuthorizer;	
import org.apache.shiro.crypto.CryptoException;	
import org.apache.shiro.realm.Realm;	
import org.apache.shiro.subject.PrincipalCollection;	
import org.apache.shiro.subject.SubjectContext;	
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;	
	
public class WebSecurityManager	
extends DefaultWebSecurityManager {	
    public WebSecurityManager(Collection<Realm> realms) {	
        WebSecurityManager webSecurityManager = this;	
        super(realms);	
        webSecurityManager.initialize();	
    }	
	
    @Override	
    protected PrincipalCollection getRememberedIdentity(SubjectContext subjectContext) {	
        try {	
            return super.getRememberedIdentity(subjectContext);	
        }	
        catch (CryptoException cryptoException) {	
            return null;	
        }	
    }	
	
    @Override	
    public void setRealms(Collection<Realm> realms) {	
        WebSecurityManager webSecurityManager = this;	
        super.setRealms(realms);	
        if (webSecurityManager.getAuthorizer() instanceof ModularRealmAuthorizer) {	
            ModularRealmAuthorizer a = (ModularRealmAuthorizer)this.getAuthorizer();	
            ArrayList<Realm> a2 = ListUtils.newArrayList();	
            a.setRealms(a2);	
            a2.add(realms.iterator().next());	
        }	
    }	
	
    public WebSecurityManager(Realm singleRealm) {	
        WebSecurityManager webSecurityManager = this;	
        super(singleRealm);	
        webSecurityManager.initialize();	
    }	
	
    private /* synthetic */ void initialize() {	
        WebSecurityManager webSecurityManager = this;	
        webSecurityManager.setRememberMeManager(new RememberMeManager());	
        ((ModularRealmAuthenticator)webSecurityManager.getAuthenticator()).setAuthenticationStrategy(new i(this));	
        SecurityUtils.setSecurityManager(this);	
    }	
	
    public WebSecurityManager() {	
        WebSecurityManager webSecurityManager = this;	
        webSecurityManager.initialize();	
    }	
}	
	
