/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.io.ResourceUtils	
 *  org.json.JSONArray	
 *  org.json.JSONObject	
 */	
package com.jeesite.common.d;	
	
import com.jeesite.common.io.ResourceUtils;	
import com.jeesite.common.shiro.realm.LoginInfo;	
import com.jeesite.modules.msg.entity.content.BaseMsgContent;	
import java.io.FileNotFoundException;	
import java.io.IOException;	
import java.util.HashMap;	
import java.util.Map;	
import org.json.JSONArray;	
import org.json.JSONObject;	
	
/*	
 * Duplicate member names - consider using --renamedupmembers true	
 */	
public final class i {	
    private final String g;	
    private static final String J = "config/ueditor.json";	
    private static final String i = "scrawl";	
    private static final String b = "remote";	
    private JSONObject ALLATORIxDEMO = null;	
	
    private /* synthetic */ i(String string, String string2, String string3) throws FileNotFoundException, IOException {	
        String rootPath;	
        this.g = rootPath = string.replace("\\", "/");	
        this.ALLATORIxDEMO();	
    }	
	
    private /* synthetic */ String[] ALLATORIxDEMO(String key) {	
        JSONArray a = this.ALLATORIxDEMO.getJSONArray(key);	
        String[] a2 = new String[a.length()];	
        int a3 = 0;	
        int a4 = a.length();	
        int n = a3;	
        while (n < a4) {	
            int n2 = a3++;	
            a2[n2] = a.getString(n2);	
            n = a3;	
        }	
        return a2;	
    }	
	
    public JSONObject ALLATORIxDEMO() {	
        return this.ALLATORIxDEMO;	
    }	
	
    public Map<String, Object> ALLATORIxDEMO(int type) {	
        HashMap<String, Object> hashMap;	
        HashMap<String, Object> a = new HashMap<String, Object>();	
        String a2 = null;	
        switch (type) {	
            case 4: {	
                HashMap<String, Object> hashMap2 = a;	
                hashMap = hashMap2;	
                a2 = this.ALLATORIxDEMO.getString("filePathFormat");	
                a.put("fieldName", this.ALLATORIxDEMO.getString("fileFieldName"));	
                a.put("allowFiles", this.ALLATORIxDEMO("fileAllowFiles"));	
                a.put("maxSize", this.ALLATORIxDEMO.getLong("fileMaxSize"));	
                hashMap2.put("isBase64", "false");	
                break;	
            }	
            case 1: {	
                HashMap<String, Object> hashMap3 = a;	
                while (false) {	
                }	
                hashMap = hashMap3;	
                a2 = this.ALLATORIxDEMO.getString("imagePathFormat");	
                a.put("imageCompressBorder", this.ALLATORIxDEMO.getInt("imageCompressBorder"));	
                a.put("imageCompressEnable", this.ALLATORIxDEMO.getBoolean("imageCompressEnable"));	
                a.put("fieldName", this.ALLATORIxDEMO.getString("imageFieldName"));	
                a.put("allowFiles", this.ALLATORIxDEMO("imageAllowFiles"));	
                a.put("maxSize", this.ALLATORIxDEMO.getLong("imageMaxSize"));	
                hashMap3.put("isBase64", "false");	
                break;	
            }	
            case 3: {	
                HashMap<String, Object> hashMap4 = a;	
                hashMap = hashMap4;	
                a2 = this.ALLATORIxDEMO.getString("videoPathFormat");	
                a.put("fieldName", this.ALLATORIxDEMO.getString("videoFieldName"));	
                a.put("allowFiles", this.ALLATORIxDEMO("videoAllowFiles"));	
                hashMap4.put("maxSize", this.ALLATORIxDEMO.getLong("videoMaxSize"));	
                break;	
            }	
            case 2: {	
                HashMap<String, Object> hashMap5 = a;	
                hashMap = hashMap5;	
                a2 = this.ALLATORIxDEMO.getString("scrawlPathFormat");	
                a.put("isBase64", "true");	
                a.put("fieldName", this.ALLATORIxDEMO.getString("scrawlFieldName"));	
                a.put("maxSize", this.ALLATORIxDEMO.getLong("scrawlMaxSize"));	
                hashMap5.put("filename", i);	
                break;	
            }	
            case 5: {	
                HashMap<String, Object> hashMap6 = a;	
                hashMap = hashMap6;	
                a2 = this.ALLATORIxDEMO.getString("catcherPathFormat");	
                a.put("fieldName", new StringBuilder().insert(0, this.ALLATORIxDEMO.getString("catcherFieldName")).append("[]").toString());	
                a.put("allowFiles", this.ALLATORIxDEMO("catcherAllowFiles"));	
                a.put("maxSize", this.ALLATORIxDEMO.getLong("catcherMaxSize"));	
                a.put("filter", this.ALLATORIxDEMO("catcherLocalDomain"));	
                hashMap6.put("filename", b);	
                break;	
            }	
            case 7: {	
                HashMap<String, Object> hashMap7 = a;	
                hashMap = hashMap7;	
                a.put("count", this.ALLATORIxDEMO.getInt("imageManagerListSize"));	
                a.put("dir", this.ALLATORIxDEMO.getString("imageManagerListPath"));	
                hashMap7.put("allowFiles", this.ALLATORIxDEMO("imageManagerAllowFiles"));	
                break;	
            }	
            case 6: {	
                a.put("count", this.ALLATORIxDEMO.getInt("fileManagerListSize"));	
                a.put("dir", this.ALLATORIxDEMO.getString("fileManagerListPath"));	
                a.put("allowFiles", this.ALLATORIxDEMO("fileManagerAllowFiles"));	
            }	
            default: {	
                hashMap = a;	
            }	
        }	
        a.put("rootPath", this.g);	
        a.put("savePath", a2);	
        hashMap.put("actionCode", type);	
        return a;	
    }	
	
    private /* synthetic */ void ALLATORIxDEMO() throws FileNotFoundException, IOException {	
        String a = ResourceUtils.getResourceFileContent((String)J).replaceAll("/\*[\s\S]*?\*/", "");	
        try {	
            JSONObject a2;	
            this.ALLATORIxDEMO = a2 = new JSONObject(a);	
            return;	
        }	
        catch (Exception a3) {	
            this.ALLATORIxDEMO = null;	
            return;	
        }	
    }	
	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = 1 << 3 ^ 4;	
        int n4 = n2;	
        int n5 = (2 ^ 5) << 4 ^ (2 ^ 5);	
        (3 ^ 5) << 3;	
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
	
    public static i ALLATORIxDEMO(String rootPath, String contextPath, String uri) {	
        try {	
            return new i(rootPath, contextPath, uri);	
        }	
        catch (Exception a) {	
            a.printStackTrace();	
            return null;	
        }	
    }	
	
    public boolean ALLATORIxDEMO() {	
        return this.ALLATORIxDEMO != null;	
    }	
}	
	
