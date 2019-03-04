package com.jeesite.common.l;	
	
import com.jeesite.common.io.ResourceUtils;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.modules.sys.utils.ModuleUtils;	
import java.io.FileNotFoundException;	
import java.io.IOException;	
import java.util.HashMap;	
import java.util.Map;	
import org.json.JSONArray;	
import org.json.JSONObject;	
	
public final class I {	
   private JSONObject B = null;	
   private static final String G = "config/ueditor.json";	
   private static final String d = "scrawl";	
   private static final String c = "remote";	
   private final String ALLATORIxDEMO;	
	
   // $FF: synthetic method	
   private void ALLATORIxDEMO() throws FileNotFoundException, IOException {	
      String a = ResourceUtils.getResourceFileContent("config/ueditor.json").replaceAll("/\*[\s\S]*?\*/", "");	
	
      try {	
         JSONObject a = new JSONObject(a);	
         this.B = a;	
      } catch (Exception var3) {	
         this.B = null;	
      }	
   }	
	
   public Map ALLATORIxDEMO(int type) {	
      Map a = new HashMap();	
      String a = null;	
      HashMap var10000;	
      switch(type) {	
      case 1:	
         var10000 = a;	
	
         while(false) {	
         }	
	
         a.put("isBase64", "false");	
         a.put("maxSize", this.B.getLong("imageMaxSize"));	
         a.put("allowFiles", this.ALLATORIxDEMO("imageAllowFiles"));	
         a.put("fieldName", this.B.getString("imageFieldName"));	
         a.put("imageCompressEnable", this.B.getBoolean("imageCompressEnable"));	
         a.put("imageCompressBorder", this.B.getInt("imageCompressBorder"));	
         a = this.B.getString("imagePathFormat");	
         break;	
      case 2:	
         var10000 = a;	
         a.put("filename", "scrawl");	
         a.put("maxSize", this.B.getLong("scrawlMaxSize"));	
         a.put("fieldName", this.B.getString("scrawlFieldName"));	
         a.put("isBase64", "true");	
         a = this.B.getString("scrawlPathFormat");	
         break;	
      case 3:	
         var10000 = a;	
         a.put("maxSize", this.B.getLong("videoMaxSize"));	
         a.put("allowFiles", this.ALLATORIxDEMO("videoAllowFiles"));	
         a.put("fieldName", this.B.getString("videoFieldName"));	
         a = this.B.getString("videoPathFormat");	
         break;	
      case 4:	
         var10000 = a;	
         a.put("isBase64", "false");	
         a.put("maxSize", this.B.getLong("fileMaxSize"));	
         a.put("allowFiles", this.ALLATORIxDEMO("fileAllowFiles"));	
         a.put("fieldName", this.B.getString("fileFieldName"));	
         a = this.B.getString("filePathFormat");	
         break;	
      case 5:	
         var10000 = a;	
         a.put("filename", "remote");	
         a.put("filter", this.ALLATORIxDEMO("catcherLocalDomain"));	
         a.put("maxSize", this.B.getLong("catcherMaxSize"));	
         a.put("allowFiles", this.ALLATORIxDEMO("catcherAllowFiles"));	
         a.put("fieldName", (new StringBuilder()).insert(0, this.B.getString("catcherFieldName")).append("[]").toString());	
         a = this.B.getString("catcherPathFormat");	
         break;	
      case 6:	
         a.put("allowFiles", this.ALLATORIxDEMO("fileManagerAllowFiles"));	
         a.put("dir", this.B.getString("fileManagerListPath"));	
         a.put("count", this.B.getInt("fileManagerListSize"));	
      default:	
         var10000 = a;	
         break;	
      case 7:	
         var10000 = a;	
         a.put("allowFiles", this.ALLATORIxDEMO("imageManagerAllowFiles"));	
         a.put("dir", this.B.getString("imageManagerListPath"));	
         a.put("count", this.B.getInt("imageManagerListSize"));	
      }	
	
      var10000.put("actionCode", type);	
      a.put("savePath", a);	
      a.put("rootPath", this.ALLATORIxDEMO);	
      return a;	
   }	
	
   public static I ALLATORIxDEMO(String rootPath, String contextPath, String uri) {	
      try {	
         return new I(rootPath, contextPath, uri);	
      } catch (Exception var4) {	
         var4.printStackTrace();	
         return null;	
      }	
   }	
	
   // $FF: synthetic method	
   private String[] ALLATORIxDEMO(String key) {	
      JSONArray a;	
      String[] var10000 = new String[(a = this.B.getJSONArray(key)).length()];	
      boolean var10002 = true;	
      String[] a = var10000;	
      int a = 0;	
      int a = a.length();	
	
      for(int var6 = a; var6 < a; var6 = a) {	
         int var10001 = a;	
         String var7 = a.getString(a);	
         ++a;	
         a[var10001] = var7;	
      }	
	
      return a;	
   }	
	
   public boolean ALLATORIxDEMO() {	
      return this.B != null;	
   }	
	
   public JSONObject ALLATORIxDEMO() {	
      return this.B;	
   }	
	
   // $FF: synthetic method	
   private I(String var1, String var2, String var3) throws FileNotFoundException, IOException {	
      this.ALLATORIxDEMO = var1.replace("\", "/");	
      this.ALLATORIxDEMO();	
   }	
}	
