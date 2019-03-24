/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.shiro.cas;	
	
import com.jeesite.common.shiro.realm.LoginInfo;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpSession;	
import org.apache.commons.logging.Log;	
import org.apache.commons.logging.LogFactory;	
import org.apache.shiro.subject.PrincipalCollection;	
import org.apache.shiro.subject.support.DefaultSubjectContext;	
import org.hyperic.sigar.DirStat;	
import org.hyperic.sigar.win32.FileVersion;	
import org.jasig.cas.client.session.HashMapBackedSessionMappingStorage;	
import org.jasig.cas.client.session.SessionMappingStorage;	
import org.jasig.cas.client.util.CommonUtils;	
import org.jasig.cas.client.util.XmlUtils;	
	
public final class CasOutHandler {	
    private final Log log;	
    private String artifactParameterName;	
    private String logoutParameterName;	
    private SessionMappingStorage sessionMappingStorage;	
	
    public void setLogoutParameterName(String name) {	
        this.logoutParameterName = name;	
    }	
	
    public boolean isTokenRequest(HttpServletRequest request) {	
        return CommonUtils.isNotBlank(CommonUtils.safeGetParameter(request, this.artifactParameterName));	
    }	
	
    public boolean isLogoutRequest(HttpServletRequest request) {	
        return "POST".equals(request.getMethod()) && !this.isMultipartRequest(request) && CommonUtils.isNotBlank(CommonUtils.safeGetParameter(request, this.logoutParameterName));	
    }	
	
    public void setSessionMappingStorage(SessionMappingStorage storage) {	
        this.sessionMappingStorage = storage;	
    }	
	
    private /* synthetic */ boolean isMultipartRequest(HttpServletRequest request) {	
        return request.getContentType() != null && request.getContentType().toLowerCase().startsWith("multipart");	
    }	
	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = 3 << 3 ^ 3;	
        3 << 3 ^ 2;	
        int n4 = n2;	
        int n5 = 5 << 4 ^ (2 << 2 ^ 1);	
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
	
    public SessionMappingStorage getSessionMappingStorage() {	
        return this.sessionMappingStorage;	
    }	
	
    public void recordSession(HttpServletRequest request, String ticket) {	
        CasOutHandler casOutHandler;	
        CasOutHandler casOutHandler2;	
        String a;	
        HttpSession a2 = request.getSession();	
        if (ticket != null) {	
            String string = ticket;	
            casOutHandler2 = this;	
        } else {	
            a = CommonUtils.safeGetParameter(request, this.artifactParameterName);	
            casOutHandler2 = this;	
        }	
        if (casOutHandler2.log.isDebugEnabled()) {	
            this.log.debug(new StringBuilder().insert(0, "Recording session for token ").append(a).toString());	
        }	
        try {	
            this.sessionMappingStorage.removeBySessionById(a2.getId());	
            casOutHandler = this;	
        }	
        catch (Exception exception) {	
            casOutHandler = this;	
        }	
        casOutHandler.sessionMappingStorage.addSessionById(a, a2);	
    }	
	
    public void init() {	
        CasOutHandler casOutHandler = this;	
        CommonUtils.assertNotNull(casOutHandler.artifactParameterName, "artifactParameterName cannot be null.");	
        CommonUtils.assertNotNull(casOutHandler.logoutParameterName, "logoutParameterNme cannot be null.");	
        CommonUtils.assertNotNull(casOutHandler.sessionMappingStorage, "sessionMappingStorage cannote be null.");	
    }	
	
    public void setArtifactParameterName(String name) {	
        this.artifactParameterName = name;	
    }	
	
    /*	
     * Enabled aggressive block sorting	
     * Enabled unnecessary exception pruning	
     * Enabled aggressive exception aggregation	
     */	
    public LoginInfo destroySession(HttpServletRequest request) {	
        HttpSession a;	
        String a2 = CommonUtils.safeGetParameter(request, this.logoutParameterName);	
        if (this.log.isTraceEnabled()) {	
            this.log.trace(new StringBuilder().insert(0, "Logout request:\n").append(a2).toString());	
        }	
        LoginInfo a3 = null;	
        String a4 = XmlUtils.getTextForElement(a2, "SessionIndex");	
        if (CommonUtils.isNotBlank(a4) && (a = this.sessionMappingStorage.removeSessionByMappingId(a4)) != null) {	
            String a5 = a.getId();	
            if (this.log.isDebugEnabled()) {	
                this.log.debug(new StringBuilder().insert(0, "Invlidting session [").append(a5).append("] for token [").append(a4).append("]").toString());	
            }	
            try {	
                PrincipalCollection a6 = (PrincipalCollection)a.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);	
                a3 = a6 != null ? (LoginInfo)a6.getPrimaryPrincipal() : null;	
                a.invalidate();	
                return a3;	
            }	
            catch (IllegalStateException a7) {	
                this.log.debug("Error invalidating session.", a7);	
            }	
        }	
        return a3;	
    }	
	
    public void recordSession(HttpServletRequest request) {	
        this.recordSession(request, null);	
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
}	
	
