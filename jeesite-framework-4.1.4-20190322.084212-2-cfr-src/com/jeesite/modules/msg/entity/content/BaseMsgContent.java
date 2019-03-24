/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.modules.msg.entity.content;	
	
import com.fasterxml.jackson.annotation.JsonIgnore;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.modules.msg.entity.MsgPush;	
import java.io.Serializable;	
import java.util.Map;	
	
public class BaseMsgContent	
implements Serializable {	
    private static final long serialVersionUID = 1L;	
    private String tplKey;	
    private String content;	
    private String title;	
    private MsgPush msgPush;	
    private Map<String, Object> tplData;	
	
    public void setTplData(Map<String, Object> tplData) {	
        this.tplData = tplData;	
    }	
	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = 4 << 4 ^ (2 ^ 5);	
        1 << 3;	
        int n4 = n2;	
        int n5 = 2 << 3 ^ (2 ^ 5);	
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
	
    public void setTitle(String title) {	
        this.title = title;	
    }	
	
    public Map<String, Object> getTplData() {	
        return this.tplData;	
    }	
	
    public String getContent() {	
        return this.content;	
    }	
	
    public void addTplData(String key, Object value) {	
        if (this.tplData == null) {	
            this.tplData = MapUtils.newHashMap();	
        }	
        this.tplData.put(key, value);	
    }	
	
    public String getMsgType() {	
        return null;	
    }	
	
    public String getTitle() {	
        return this.title;	
    }	
	
    public void setMsgPush(MsgPush msgPush) {	
        this.msgPush = msgPush;	
    }	
	
    @JsonIgnore	
    public MsgPush getMsgPush() {	
        return this.msgPush;	
    }	
	
    public void setContent(String content) {	
        this.content = content;	
    }	
	
    public String getTplKey() {	
        return this.tplKey;	
    }	
	
    public void setTplKey(String tplKey) {	
        this.tplKey = tplKey;	
    }	
}	
	
