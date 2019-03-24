/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.shiro.authc;	
	
import java.util.Map;	
import org.apache.shiro.authc.UsernamePasswordToken;	
	
public class FormToken	
extends UsernamePasswordToken {	
    private String ssoToken;	
    private static final long serialVersionUID = 1L;	
    private String captcha;	
    private Map<String, Object> params;	
	
    public FormToken(String username, String password, boolean rememberMe, String host, String captcha, Map<String, Object> params) {	
        FormToken formToken = this;	
        super(username, password, rememberMe, host);	
        formToken.captcha = captcha;	
        formToken.params = params;	
    }	
	
    public void setPassword(String password) {	
        this.setPassword(password != null ? password.toCharArray() : null);	
    }	
	
    public FormToken() {	
    }	
	
    public void setCaptcha(String captcha) {	
        this.captcha = captcha;	
    }	
	
    public Map<String, Object> getParams() {	
        return this.params;	
    }	
	
    public void setSsoToken(String ssoToken) {	
        this.ssoToken = ssoToken;	
    }	
	
    public String getCaptcha() {	
        return this.captcha;	
    }	
	
    public String getSsoToken() {	
        return this.ssoToken;	
    }	
	
    public void setParams(Map<String, Object> params) {	
        this.params = params;	
    }	
	
    public FormToken(String username, char[] password, boolean rememberMe, String host, String captcha, Map<String, Object> params) {	
        FormToken formToken = this;	
        super(username, password, rememberMe, host);	
        formToken.captcha = captcha;	
        formToken.params = params;	
    }	
}	
	
