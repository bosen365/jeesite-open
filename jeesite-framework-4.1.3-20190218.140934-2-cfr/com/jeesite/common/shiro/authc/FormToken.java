/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  org.apache.shiro.authc.UsernamePasswordToken	
 */	
package com.jeesite.common.shiro.authc;	
	
import java.util.Map;	
import org.apache.shiro.authc.UsernamePasswordToken;	
	
public class FormToken	
extends UsernamePasswordToken {	
    private static final long serialVersionUID = 1L;	
    private Map<String, Object> params;	
    private String ssoToken;	
    private String captcha;	
	
    public Map<String, Object> getParams() {	
        return this.params;	
    }	
	
    public FormToken() {	
    }	
	
    public void setPassword(String password) {	
        this.setPassword(password != null ? password.toCharArray() : null);	
    }	
	
    public FormToken(String username, String password, boolean rememberMe, String host, String captcha, Map<String, Object> params) {	
        FormToken formToken = this;	
        super(username, password, rememberMe, host);	
        formToken.captcha = captcha;	
        formToken.params = params;	
    }	
	
    public void setParams(Map<String, Object> params) {	
        this.params = params;	
    }	
	
    public void setSsoToken(String ssoToken) {	
        this.ssoToken = ssoToken;	
    }	
	
    public String getSsoToken() {	
        return this.ssoToken;	
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
	
    public String getCaptcha() {	
        return this.captcha;	
    }	
}	
	
