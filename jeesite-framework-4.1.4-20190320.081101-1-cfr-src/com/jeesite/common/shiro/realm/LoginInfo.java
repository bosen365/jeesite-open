/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.common.shiro.realm;	
	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.modules.sys.entity.User;	
import java.io.Serializable;	
import java.util.Map;	
import java.util.Objects;	
	
public class LoginInfo	
implements Serializable {	
    private String id;	
    private String name;	
    private Map<String, Object> params;	
    private static final long serialVersionUID = 1L;	
	
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
        LoginInfo a = (LoginInfo)obj;	
        return !(this.getId() == null ? a.getId() != null : !this.getId().equals(a.getId()));	
    }	
	
    public Map<String, Object> getParams() {	
        return this.params;	
    }	
	
    public String toString() {	
        return this.id;	
    }	
	
    public void setName(String name) {	
        this.name = name;	
    }	
	
    public LoginInfo() {	
    }	
	
    public String getId() {	
        return this.id;	
    }	
	
    public void setParam(String key, String value) {	
        if (this.params == null) {	
            this.params = MapUtils.newHashMap();	
        }	
        this.params.put(key, value);	
    }	
	
    public String getParam(String key, String defaultValue) {	
        String a = this.getParam(key);	
        if (StringUtils.isNotBlank(a)) {	
            return a;	
        }	
        return defaultValue;	
    }	
	
    public int hashCode() {	
        return Objects.hashCode(this.id);	
    }	
	
    public void setId(String id) {	
        this.id = id;	
    }	
	
    public LoginInfo(User user, Map<String, Object> params) {	
        LoginInfo loginInfo = this;	
        User user2 = user;	
        this.id = user2.getUserCode();	
        loginInfo.name = user2.getUserName();	
        loginInfo.params = params;	
    }	
	
    public String getParam(String key) {	
        Object a;	
        if (this.params != null && (a = this.params.get(key)) != null) {	
            return a.toString();	
        }	
        return null;	
    }	
	
    public void setParams(Map<String, Object> params) {	
        this.params = params;	
    }	
	
    public String getName() {	
        return this.name;	
    }	
	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = (3 ^ 5) << 4;	
        int n4 = n2;	
        4 << 4;	
        int n5 = (2 ^ 5) << 4 ^ 1 << 1;	
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
}	
	
