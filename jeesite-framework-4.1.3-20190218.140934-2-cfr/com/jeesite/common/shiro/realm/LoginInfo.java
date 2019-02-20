/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.collect.MapUtils	
 *  com.jeesite.common.lang.StringUtils	
 */	
package com.jeesite.common.shiro.realm;	
	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.modules.sys.entity.User;	
import java.io.Serializable;	
import java.util.HashMap;	
import java.util.Map;	
import java.util.Objects;	
	
public class LoginInfo	
implements Serializable {	
    private Map<String, Object> params;	
    private String id;	
    private static final long serialVersionUID = 1L;	
    private String name;	
	
    public String getName() {	
        return this.name;	
    }	
	
    public String toString() {	
        return this.id;	
    }	
	
    public String getParam(String key, String defaultValue) {	
        String a2 = this.getParam(key);	
        if (StringUtils.isNotBlank((CharSequence)a2)) {	
            return a2;	
        }	
        return defaultValue;	
    }	
	
    public void setName(String name) {	
        this.name = name;	
    }	
	
    public Map<String, Object> getParams() {	
        return this.params;	
    }	
	
    public int hashCode() {	
        return Objects.hashCode(this.id);	
    }	
	
    public void setId(String id) {	
        this.id = id;	
    }	
	
    public void setParam(String key, String value) {	
        if (this.params == null) {	
            this.params = MapUtils.newHashMap();	
        }	
        this.params.put(key, value);	
    }	
	
    public LoginInfo() {	
    }	
	
    public boolean equals(Object obj) {	
        if (this == obj) {	
            return true;	
        }	
        if (obj == null) {	
            return false;	
        }	
        if (this.getClass() != obj.getClass()) {	
            return false;	
        }	
        LoginInfo a2 = (LoginInfo)obj;	
        return !(this.getId() == null ? a2.getId() != null : !this.getId().equals(a2.getId()));	
    }	
	
    public void setParams(Map<String, Object> params) {	
        this.params = params;	
    }	
	
    public String getParam(String key) {	
        Object a2;	
        if (this.params != null && (a2 = this.params.get(key)) != null) {	
            return a2.toString();	
        }	
        return null;	
    }	
	
    public LoginInfo(User user, Map<String, Object> params) {	
        LoginInfo loginInfo = this;	
        User user2 = user;	
        this.id = user2.getUserCode();	
        loginInfo.name = user2.getUserName();	
        loginInfo.params = params;	
    }	
	
    public String getId() {	
        return this.id;	
    }	
}	
	
