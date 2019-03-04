package com.jeesite.common.l.d;	
	
import com.jeesite.common.l.l.A;	
import com.jeesite.common.l.l.g;	
import com.jeesite.modules.sys.utils.ConfigUtils;	
import com.jeesite.modules.sys.web.ValidCodeController;	
import java.net.HttpURLConnection;	
import java.net.InetAddress;	
import java.net.URL;	
import java.net.UnknownHostException;	
import java.util.Arrays;	
import java.util.List;	
import java.util.Map;	
import javax.servlet.http.HttpServletRequest;	
	
public class h {	
   private String I = null;	
   private HttpServletRequest E = null;	
   private long B = -1L;	
   private String G = null;	
   private String d = null;	
   private List c = null;	
   private List ALLATORIxDEMO = null;	
	
   public com.jeesite.common.l.l.l ALLATORIxDEMO(String[] list) {	
      com.jeesite.common.l.l.K a = new com.jeesite.common.l.l.K(true);	
      String[] var3 = list;	
      int var4 = list.length;	
	
      int var5;	
      for(int var10000 = var5 = 0; var10000 < var4; var10000 = var5) {	
         String a = var3[var5];	
         ++var5;	
         a.ALLATORIxDEMO(this.ALLATORIxDEMO(a));	
      }	
	
      return a;	
   }	
	
   public h(HttpServletRequest request, Map conf) {	
      this.E = request;	
      this.G = (String)conf.get("filename");	
      this.d = (String)conf.get("savePath");	
      this.I = (String)conf.get("rootPath");	
      this.B = (Long)conf.get("maxSize");	
      this.c = Arrays.asList((String[])((String[])conf.get("allowFiles")));	
      this.ALLATORIxDEMO = Arrays.asList((String[])((String[])conf.get("filter")));	
   }	
	
   // $FF: synthetic method	
   private boolean H(String type) {	
      return this.c.contains(type);	
   }	
	
   // $FF: synthetic method	
   private boolean H(int code) {	
      return 200 == code;	
   }	
	
   public com.jeesite.common.l.l.l ALLATORIxDEMO(String urlStr) {	
      HttpURLConnection a = null;	
      URL a = null;	
      String a = null;	
	
      try {	
         a = new URL(urlStr);	
         if (!this.ALLATORIxDEMO(a.getHost())) {	
            return new A(false, 201);	
         } else {	
            a = (HttpURLConnection)a.openConnection();	
            a.setInstanceFollowRedirects(true);	
            a.setUseCaches(true);	
            if (!this.H(a.getResponseCode())) {	
               return new A(false, 202);	
            } else {	
               a = g.ALLATORIxDEMO(a.getContentType());	
               if (!this.H(a)) {	
                  return new A(false, 8);	
               } else if (!this.ALLATORIxDEMO(a.getContentLength())) {	
                  return new A(false, 1);	
               } else {	
                  String a = this.ALLATORIxDEMO(this.d, this.G, a);	
                  String a = (new StringBuilder()).insert(0, this.I).append(a).toString();	
                  com.jeesite.common.l.l.l a;	
                  if ((a = com.jeesite.common.l.i.K.ALLATORIxDEMO(a.getInputStream(), a)).ALLATORIxDEMO()) {	
                     a.ALLATORIxDEMO("url", (new StringBuilder()).insert(0, this.E.getContextPath()).append(com.jeesite.common.l.l.F(a)).toString());	
                     a.ALLATORIxDEMO("surce", urlStr);	
                  }	
	
                  return a;	
               }	
            }	
         }	
      } catch (Exception var8) {	
         return new A(false, 203);	
      }	
   }	
	
   // $FF: synthetic method	
   private boolean ALLATORIxDEMO(int size) {	
      return (long)size < this.B;	
   }	
	
   // $FF: synthetic method	
   private boolean ALLATORIxDEMO(String hostname) {	
      try {	
         if (InetAddress.getByName(hostname).isSiteLocalAddress()) {	
            return false;	
         }	
      } catch (UnknownHostException var3) {	
         return false;	
      }	
	
      return !this.ALLATORIxDEMO.contains(hostname);	
   }	
	
   // $FF: synthetic method	
   private String ALLATORIxDEMO(String savePath, String filename, String suffix) {	
      return com.jeesite.common.l.l.ALLATORIxDEMO((new StringBuilder()).insert(0, savePath).append(suffix).toString(), filename);	
   }	
}	
