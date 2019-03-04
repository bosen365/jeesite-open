package com.jeesite.common.l.d;	
	
import com.jeesite.common.l.l.A;	
import com.jeesite.common.validator.ValidatorUtils;	
import com.jeesite.modules.sys.utils.ValidCodeUtils;	
import java.io.File;	
import java.util.Arrays;	
import java.util.Collection;	
import java.util.Map;	
import javax.servlet.http.HttpServletRequest;	
import org.apache.commons.io.FileUtils;	
	
public class K {	
   private String G = null;	
   private String[] d = null;	
   private String c = null;	
   private int ALLATORIxDEMO = 0;	
	
   // $FF: synthetic method	
   private com.jeesite.common.l.l.l ALLATORIxDEMO(HttpServletRequest request, Object[] files) {	
      com.jeesite.common.l.l.K a = new com.jeesite.common.l.l.K(true);	
      A a = null;	
      File a = null;	
      Object[] var6 = files;	
      int var7 = files.length;	
	
      int var8;	
      for(int var10000 = var8 = 0; var10000 < var7; var10000 = var8) {	
         Object a;	
         if ((a = var6[var8]) == null) {	
            return a;	
         }	
	
         a = (File)a;	
         a = new A(true);	
         String a;	
         int a;	
         if ((a = (a = com.jeesite.common.l.l.F(this.ALLATORIxDEMO(a))).indexOf("/userfiles/")) > 0) {	
            a = a.substring(a + "/userfiles/".length());	
         }	
	
         a.ALLATORIxDEMO("url", (new StringBuilder()).insert(0, request.getContextPath()).append("/userfiles/").append(a).toString());	
         ++var8;	
         a.ALLATORIxDEMO(a);	
      }	
	
      return a;	
   }	
	
   public com.jeesite.common.l.l.l ALLATORIxDEMO(HttpServletRequest request, int index) {	
      File a = new File(com.jeesite.common.l.l.H(this.G));	
      com.jeesite.common.l.l.l a = null;	
      if (!a.exists()) {	
         return new A(false, 302);	
      } else if (!a.isDirectory()) {	
         return new A(false, 301);	
      } else {	
         Collection a = FileUtils.listFiles(a, this.d, true);	
         Object var10000;	
         if (index >= 0 && index <= a.size()) {	
            Object[] a = Arrays.copyOfRange(a.toArray(), index, index + this.ALLATORIxDEMO);	
            var10000 = a = this.ALLATORIxDEMO(request, a);	
         } else {	
            var10000 = a = new com.jeesite.common.l.l.K(true);	
         }	
	
         ((com.jeesite.common.l.l.l)var10000).ALLATORIxDEMO("start", (long)index);	
         ((com.jeesite.common.l.l.l)a).ALLATORIxDEMO("total", (long)a.size());	
         return (com.jeesite.common.l.l.l)a;	
      }	
   }	
	
   // $FF: synthetic method	
   private String ALLATORIxDEMO(File file) {	
      return file.getAbsolutePath().replace(this.c, "/");	
   }	
	
   // $FF: synthetic method	
   private String[] ALLATORIxDEMO(Object fileExt) {	
      String[] a = null;	
      String a = null;	
      if (fileExt == null) {	
         String[] var6 = new String[0];	
         boolean var7 = true;	
         return var6;	
      } else {	
         a = (String[])((String[])fileExt);	
         int a = 0;	
         int a = a.length;	
	
         for(int var10000 = a; var10000 < a; var10000 = a) {	
            a = a[a];	
            int var10001 = a;	
            String var10002 = a.replace(".", "");	
            ++a;	
            a[var10001] = var10002;	
         }	
	
         return a;	
      }	
   }	
	
   public K(Map conf) {	
      this.c = (String)conf.get("rootPath");	
      this.G = this.c + (String)conf.get("dir");	
      this.d = this.ALLATORIxDEMO(conf.get("llowFiles"));	
      this.ALLATORIxDEMO = (Integer)conf.get("count");	
   }	
}	
