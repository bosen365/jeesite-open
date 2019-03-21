/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.modules.msg.entity.content;	
	
import com.fasterxml.jackson.annotation.JsonIgnore;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.modules.msg.entity.MsgPush;	
import java.io.Serializable;	
import java.util.Map;	
	
public class BaseMsgContent	
implements Serializable {	
    private MsgPush msgPush;	
    private String tplKey;	
    private String title;	
    private static final long serialVersionUID = 1L;	
    private Map<String, Object> tplData;	
    private String content;	
	
    public String getTplKey() {	
        return this.tplKey;	
    }	
	
    public String getMsgType() {	
        return null;	
    }	
	
    public String getContent() {	
        return this.content;	
    }	
	
    public void setMsgPush(MsgPush msgPush) {	
        this.msgPush = msgPush;	
    }	
	
    public void setTitle(String title) {	
        this.title = title;	
    }	
	
    public void setTplData(Map<String, Object> tplData) {	
        this.tplData = tplData;	
    }	
	
    public void addTplData(String key, Object value) {	
        if (this.tplData == null) {	
            this.tplData = MapUtils.newHashMap();	
        }	
        this.tplData.put(key, value);	
    }	
	
    @JsonIgnore	
    public MsgPush getMsgPush() {	
        return this.msgPush;	
    }	
	
    public Map<String, Object> getTplData() {	
        return this.tplData;	
    }	
	
    public String getTitle() {	
        return this.title;	
    }	
	
    public void setContent(String content) {	
        this.content = content;	
    }	
	
    public void setTplKey(String tplKey) {	
        this.tplKey = tplKey;	
    }	
	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = 4 << 4 ^ (3 << 2 ^ 3);	
        int n4 = n2;	
        int n5 = 5 << 3 ^ (2 ^ 5);	
        2 << 3 ^ 2;	
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
	
