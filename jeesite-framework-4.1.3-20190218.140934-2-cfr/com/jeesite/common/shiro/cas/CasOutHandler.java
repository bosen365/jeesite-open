/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  javax.servlet.http.HttpServletRequest	
 *  javax.servlet.http.HttpSession	
 *  org.apache.commons.logging.Log	
 *  org.apache.commons.logging.LogFactory	
 *  org.apache.shiro.subject.PrincipalCollection	
 *  org.apache.shiro.subject.support.DefaultSubjectContext	
 *  org.jasig.cas.client.session.HashMapBackedSessionMappingStorage	
 *  org.jasig.cas.client.session.SessionMappingStorage	
 *  org.jasig.cas.client.util.CommonUtils	
 *  org.jasig.cas.client.util.XmlUtils	
 */	
package com.jeesite.common.shiro.cas;	
	
import com.jeesite.common.shiro.realm.LoginInfo;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpSession;	
import org.apache.commons.logging.Log;	
import org.apache.commons.logging.LogFactory;	
import org.apache.shiro.subject.PrincipalCollection;	
import org.apache.shiro.subject.support.DefaultSubjectContext;	
import org.hyperic.sigar.Mem;	
import org.hyperic.sigar.NfsClientV3;	
import org.jasig.cas.client.session.HashMapBackedSessionMappingStorage;	
import org.jasig.cas.client.session.SessionMappingStorage;	
import org.jasig.cas.client.util.CommonUtils;	
import org.jasig.cas.client.util.XmlUtils;	
	
public final class CasOutHandler {	
    private String artifactParameterName;	
    private String logoutParameterName;	
    private final Log log;	
    private SessionMappingStorage sessionMappingStorage;	
	
    public SessionMappingStorage getSessionMappingStorage() {	
        return this.sessionMappingStorage;	
    }	
	
    public CasOutHandler() {	
        CasOutHandler casOutHandler = this;	
        CasOutHandler casOutHandler2 = this;	
        casOutHandler2.log = LogFactory.getLog(casOutHandler2.getClass());	
        CasOutHandler casOutHandler3 = this;	
        casOutHandler2.sessionMappingStorage = new HashMapBackedSessionMappingStorage();	
        casOutHandler.artifactParameterName = "ticket";	
        casOutHandler.logoutParameterName = "logoutRequest";	
    }	
	
    public boolean isLogoutRequest(HttpServletRequest request) {	
        return "POST".equals(request.getMethod()) && !this.isMultipartRequest(request) && CommonUtils.isNotBlank((String)CommonUtils.safeGetParameter((HttpServletRequest)request, (String)this.logoutParameterName));	
    }	
	
    public void init() {	
        CasOutHandler casOutHandler = this;	
        CommonUtils.assertNotNull((Object)casOutHandler.artifactParameterName, (String)"artifactParameterName cannot be null.");	
        CommonUtils.assertNotNull((Object)casOutHandler.logoutParameterName, (String)"logoutParameterName cannot be null.");	
        CommonUtils.assertNotNull((Object)casOutHandler.sessionMappingStorage, (String)"sessionMappingStorage cannote be null.");	
    }	
	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = 2 ^ 5;	
        int n4 = n2;	
        int n5 = (2 ^ 5) << 4 ^ (2 << 2 ^ 3);	
        1 << 3 ^ 2;	
        while (n4 >= 0) {	
            int n6 = n2--;	
            arrc[n6] = (char)(s.charAt(n6) ^ n5);	
            if (n2 < 0) break;	
            int n7 = n2--;	
            arrc[n7] = (char)(s.charAt(n7) ^ n3);	
            n4 = n2;	
        }	
        return new String(arrc);	
    }	
	
    public boolean isTokenRequest(HttpServletRequest request) {	
        return CommonUtils.isNotBlank((String)CommonUtils.safeGetParameter((HttpServletRequest)request, (String)this.artifactParameterName));	
    }	
	
    public void recordSession(HttpServletRequest request) {	
        this.recordSession(request, null);	
    }	
	
    private /* synthetic */ boolean isMultipartRequest(HttpServletRequest request) {	
        return request.getContentType() != null && request.getContentType().toLowerCase().startsWith("multipart");	
    }	
	
    /*	
     * Enabled aggressive block sorting	
     * Enabled unnecessary exception pruning	
     * Enabled aggressive exception aggregation	
     */	
    public LoginInfo destroySession(HttpServletRequest request) {	
        HttpSession a2;	
        String a3 = CommonUtils.safeGetParameter((HttpServletRequest)request, (String)this.logoutParameterName);	
        if (this.log.isTraceEnabled()) {	
            this.log.trace((Object)new StringBuilder().insert(0, "Logout request:\n").append(a3).toString());	
        }	
        LoginInfo a4 = null;	
        String a5 = XmlUtils.getTextForElement((String)a3, (String)"SessionIndex");	
        if (CommonUtils.isNotBlank((String)a5) && (a2 = this.sessionMappingStorage.removeSessionByMappingId(a5)) != null) {	
            String a6 = a2.getId();	
            if (this.log.isDebugEnabled()) {	
                this.log.debug((Object)new StringBuilder().insert(0, "Invalidating session [").append(a6).append("] for token [").append(a5).append("]").toString());	
            }	
            try {	
                PrincipalCollection a7 = (PrincipalCollection)a2.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);	
                a4 = a7 != null ? (LoginInfo)a7.getPrimaryPrincipal() : null;	
                a2.invalidate();	
                return a4;	
            }	
            catch (IllegalStateException a8) {	
                this.log.debug((Object)"Error invalidating session.", (Throwable)a8);	
            }	
        }	
        return a4;	
    }	
	
    public void setSessionMappingStorage(SessionMappingStorage storage) {	
        this.sessionMappingStorage = storage;	
    }	
	
    public void recordSession(HttpServletRequest request, String ticket) {	
        CasOutHandler casOutHandler;	
        CasOutHandler casOutHandler2;	
        String a2;	
        HttpSession a3 = request.getSession();	
        if (ticket != null) {	
            String string = ticket;	
            casOutHandler2 = this;	
        } else {	
            a2 = CommonUtils.safeGetParameter((HttpServletRequest)request, (String)this.artifactParameterName);	
            casOutHandler2 = this;	
        }	
        if (casOutHandler2.log.isDebugEnabled()) {	
            this.log.debug((Object)new StringBuilder().insert(0, "Recording session for toen ").append(a2).toString());	
        }	
        try {	
            this.sessionMappingStorage.removeBySessionById(a3.getId());	
            casOutHandler = this;	
        }	
        catch (Exception exception) {	
            casOutHandler = this;	
        }	
        casOutHandler.sessionMappingStorage.addSessionById(a2, a3);	
    }	
	
    public void setLogoutParameterName(String name) {	
        this.logoutParameterName = name;	
    }	
	
    public void setArtifactParameterName(String name) {	
        this.artifactParameterName = name;	
    }	
}	
	
