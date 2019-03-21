/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  org.apache.shiro.authc.UsernamePasswordToken	
 */	
package com.jeesite.common.shiro.authc;	
	
import java.util.Map;	
import org.apache.shiro.authc.UsernamePasswordToken;	
	
public class FormToken	
extends UsernamePasswordToken {	
    private String ssoToken;	
    private Map<String, Object> params;	
    private String captcha;	
    private static final long serialVersionUID = 1L;	
	
    public void setPassword(String password) {	
        this.setPassword(password != null ? password.toCharArray() : null);	
    }	
	
    public FormToken(String username, char[] password, boolean rememberMe, String host, String captcha, Map<String, Object> params) {	
        FormToken formToken = this;	
        super(username, password, rememberMe, host);	
        formToken.captcha = captcha;	
        formToken.params = params;	
    }	
	
    public void setCaptcha(String captcha) {	
        this.captcha = captcha;	
    }	
	
    public String getSsoToken() {	
        return this.ssoToken;	
    }	
	
    public void setParams(Map<String, Object> params) {	
        this.params = params;	
    }	
	
    public FormToken() {	
    }	
	
    public FormToken(String username, String password, boolean rememberMe, String host, String captcha, Map<String, Object> params) {	
        FormToken formToken = this;	
        super(username, password, rememberMe, host);	
        formToken.captcha = captcha;	
        formToken.params = params;	
    }	
	
    public String getCaptcha() {	
        return this.captcha;	
    }	
	
    public void setSsoToken(String ssoToken) {	
        this.ssoToken = ssoToken;	
    }	
	
    public Map<String, Object> getParams() {	
        return this.params;	
    }	
}	
	
