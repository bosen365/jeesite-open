/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.l;	
	
import com.jeesite.common.io.ResourceUtils;	
import com.jeesite.common.mybatis.mapper.query.QueryWhere;	
import java.io.FileNotFoundException;	
import java.io.IOException;	
import java.util.HashMap;	
import java.util.Map;	
import org.hyperic.sigar.ProcCredName;	
import org.json.JSONArray;	
import org.json.JSONObject;	
	
/*	
 * Duplicate member names - consider using --renamedupmembers true	
 */	
public final class m {	
    private static final String l = "remote";	
    private JSONObject h = null;	
    private static final String J = "config/ueditor.json";	
    private final String c;	
    private static final String ALLATORIxDEMO = "scrawl";	
	
    private /* synthetic */ void ALLATORIxDEMO() throws FileNotFoundException, IOException {	
        String a = ResourceUtils.getResourceFileContent(J).replaceAll("/\\*[\\s\\S]*?\\*/", "");	
        try {	
            JSONObject a2;	
            this.h = a2 = new JSONObject(a);	
            return;	
        }	
        catch (Exception a3) {	
            this.h = null;	
            return;	
        }	
    }	
	
    public Map<String, Object> ALLATORIxDEMO(int type) {	
        HashMap<String, Object> hashMap;	
        HashMap<String, Object> a = new HashMap<String, Object>();	
        String a2 = null;	
        switch (type) {	
            case 4: {	
                HashMap<String, Object> hashMap2 = a;	
                hashMap = hashMap2;	
                hashMap2.put("isBase64", "false");	
                a.put("maxSize", this.h.getLong("fileMaxSize"));	
                a.put("allowFiles", this.ALLATORIxDEMO("fileAllowFiles"));	
                a.put("fieldName", this.h.getString("fileFieldName"));	
                a2 = this.h.getString("filePathFormat");	
                break;	
            }	
            case 1: {	
                HashMap<String, Object> hashMap3 = a;	
                while (false) {	
                }	
                hashMap = hashMap3;	
                hashMap3.put("isBase64", "false");	
                a.put("maxSize", this.h.getLong("imageMaxSize"));	
                a.put("allowFiles", this.ALLATORIxDEMO("imageAllowFiles"));	
                a.put("fieldName", this.h.getString("imageFieldName"));	
                a.put("imageCompressEnable", this.h.getBoolean("imageCompressEnable"));	
                a.put("imageCompressBorder", this.h.getInt("imageCompressBorder"));	
                a2 = this.h.getString("imagePathFormat");	
                break;	
            }	
            case 3: {	
                HashMap<String, Object> hashMap4 = a;	
                hashMap = hashMap4;	
                hashMap4.put("maxSize", this.h.getLong("videoMaxSize"));	
                a.put("allowFiles", this.ALLATORIxDEMO("videoAllowFiles"));	
                a.put("fieldName", this.h.getString("videoFieldName"));	
                a2 = this.h.getString("videoPathFormat");	
                break;	
            }	
            case 2: {	
                HashMap<String, Object> hashMap5 = a;	
                hashMap = hashMap5;	
                hashMap5.put("filename", ALLATORIxDEMO);	
                a.put("maxSize", this.h.getLong("scrawlMaxSize"));	
                a.put("fieldName", this.h.getString("scrawlFieldName"));	
                a.put("isBase64", "true");	
                a2 = this.h.getString("scrawlPathFormat");	
                break;	
            }	
            case 5: {	
                HashMap<String, Object> hashMap6 = a;	
                hashMap = hashMap6;	
                hashMap6.put("filename", l);	
                a.put("filter", this.ALLATORIxDEMO("catcherLocalDomain"));	
                a.put("maxSize", this.h.getLong("catcherMaxSize"));	
                a.put("allowFiles", this.ALLATORIxDEMO("catcherAllowFiles"));	
                a.put("fieldName", new StringBuilder().insert(0, this.h.getString("catcherFieldName")).append("[]").toString());	
                a2 = this.h.getString("catcherPathFormat");	
                break;	
            }	
            case 7: {	
                HashMap<String, Object> hashMap7 = a;	
                hashMap = hashMap7;	
                hashMap7.put("allowFiles", this.ALLATORIxDEMO("imageManagerAllowFiles"));	
                a.put("dir", this.h.getString("imageManagerListPath"));	
                a.put("count", this.h.getInt("imageManagerListSize"));	
                break;	
            }	
            case 6: {	
                a.put("allowFiles", this.ALLATORIxDEMO("fileManagerAllowFiles"));	
                a.put("dir", this.h.getString("fileManagerListPath"));	
                a.put("count", this.h.getInt("fileManagerListSize"));	
            }	
            default: {	
                hashMap = a;	
            }	
        }	
        hashMap.put("actionCode", type);	
        a.put("savePath", a2);	
        a.put("rootPath", this.c);	
        return a;	
    }	
	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = (2 ^ 5) << 4 ^ 4 << 1;	
        (3 ^ 5) << 3 ^ 2;	
        int n4 = n2;	
        int n5 = (2 ^ 5) << 4 ^ (2 << 2 ^ 3);	
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
	
    private /* synthetic */ m(String string, String string2, String string3) throws FileNotFoundException, IOException {	
        String rootPath;	
        this.c = rootPath = string.replace("\\", "/");	
        this.ALLATORIxDEMO();	
    }	
	
    public boolean ALLATORIxDEMO() {	
        return this.h != null;	
    }	
	
    public JSONObject ALLATORIxDEMO() {	
        return this.h;	
    }	
	
    private /* synthetic */ String[] ALLATORIxDEMO(String key) {	
        JSONArray a = this.h.getJSONArray(key);	
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
	
    public static m ALLATORIxDEMO(String rootPath, String contextPath, String uri) {	
        try {	
            return new m(rootPath, contextPath, uri);	
        }	
        catch (Exception a) {	
            a.printStackTrace();	
            return null;	
        }	
    }	
}	
	
