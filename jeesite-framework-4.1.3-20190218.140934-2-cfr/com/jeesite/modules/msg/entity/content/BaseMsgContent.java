/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.collect.MapUtils	
 */	
package com.jeesite.modules.msg.entity.content;	
	
import com.jeesite.common.collect.MapUtils;	
import java.io.Serializable;	
import java.util.HashMap;	
import java.util.Map;	
	
public class BaseMsgContent	
implements Serializable {	
    private String title;	
    private String tplKey;	
    private String content;	
    private Map<String, Object> tplData;	
    private static final long serialVersionUID = 1L;	
	
    public String getContent() {	
        return this.content;	
    }	
	
    public Map<String, Object> getTplData() {	
        return this.tplData;	
    }	
	
    public String getMsgType() {	
        return null;	
    }	
	
    public void setTplData(Map<String, Object> tplData) {	
        this.tplData = tplData;	
    }	
	
    public void setTitle(String title) {	
        this.title = title;	
    }	
	
    public String getTitle() {	
        return this.title;	
    }	
	
    public void setTplKey(String tplKey) {	
        this.tplKey = tplKey;	
    }	
	
    public void addTplData(String key, Object value) {	
        if (this.tplData == null) {	
            this.tplData = MapUtils.newHashMap();	
        }	
        this.tplData.put(key, value);	
    }	
	
    public void setContent(String content) {	
        this.content = content;	
    }	
	
    public String getTplKey() {	
        return this.tplKey;	
    }	
}	
	
