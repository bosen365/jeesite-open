package com.jeesite.common.l.i;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.idgen.IdGen;	
import com.jeesite.common.io.FileUtils;	
import com.jeesite.common.l.l.A;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.service.BaseService;	
import com.jeesite.modules.file.utils.FileUploadUtils;	
import java.io.BufferedInputStream;	
import java.io.BufferedOutputStream;	
import java.io.File;	
import java.io.FileOutputStream;	
import java.io.IOException;	
import java.io.InputStream;	
	
public class K {	
   public static final int ALLATORIxDEMO = 8192;	
	
   public static com.jeesite.common.l.l.l ALLATORIxDEMO(InputStream is, String path) {	
      com.jeesite.common.l.l.l a = null;	
      File a = ALLATORIxDEMO();	
      byte[] var10000 = new byte[2048];	
      boolean var10002 = true;	
      byte[] a = var10000;	
      BufferedInputStream a = new BufferedInputStream(is, 8192);	
	
      try {	
         BufferedOutputStream a = new BufferedOutputStream(new FileOutputStream(a), 8192);	
         int a = false;	
         BufferedInputStream var9 = a;	
	
         int a;	
         while((a = var9.read(a)) != -1) {	
            var9 = a;	
            a.write(a, 0, a);	
         }	
	
         a.flush();	
         a.close();	
         if (!(a = ALLATORIxDEMO(a, path)).ALLATORIxDEMO()) {	
            a.delete();	
         }	
	
         return a;	
      } catch (IOException var8) {	
         return new A(false, 4);	
      }	
   }	
	
   public static com.jeesite.common.l.l.l ALLATORIxDEMO(byte[] data, String path) {	
      File a;	
      com.jeesite.common.l.l.l a;	
      if (!(a = ALLATORIxDEMO(a = new File(path))).ALLATORIxDEMO()) {	
         return a;	
      } else {	
         try {	
            BufferedOutputStream var10000 = new BufferedOutputStream(new FileOutputStream(a));	
            var10000.write(data);	
            var10000.flush();	
            var10000.close();	
         } catch (IOException var5) {	
            return new A(false, 4);	
         }	
	
         String a;	
         if (StringUtils.isNotBlank(a = Global.getConfig("file.allowContentTypes")) && !StringUtils.inString(FileUtils.getRealContentType(a), a.split(","))) {	
            a.delete();	
            return new A(false, 8);	
         } else {	
            A a;	
            (a = new A(true, a.getAbsolutePath())).ALLATORIxDEMO("size", (long)data.length);	
            a.ALLATORIxDEMO("title", a.getName());	
            return a;	
         }	
      }	
   }	
	
   // $FF: synthetic method	
   private static File ALLATORIxDEMO() {	
      File a = new File(System.getProperty("java.io.tmpdir"));	
      return new File(a, IdGen.randomBase62(10));	
   }	
	
   // $FF: synthetic method	
   private static com.jeesite.common.l.l.l ALLATORIxDEMO(File tmpFile, String path) {	
      com.jeesite.common.l.l.l a = null;	
      File a;	
      if ((a = new File(path)).canWrite()) {	
         return new A(false, 2);	
      } else {	
         try {	
            FileUtils.moveFile(tmpFile, a);	
         } catch (IOException var5) {	
            return new A(false, 4);	
         }	
	
         a = new A(true);	
         a.ALLATORIxDEMO("size", a.length());	
         a.ALLATORIxDEMO("title", a.getName());	
         return a;	
      }	
   }	
	
   // $FF: synthetic method	
   private static com.jeesite.common.l.l.l ALLATORIxDEMO(File file) {	
      File a;	
      if (!(a = file.getParentFile()).exists() && !a.mkdirs()) {	
         return new A(false, 3);	
      } else {	
         return !a.canWrite() ? new A(false, 2) : new A(true);	
      }	
   }	
	
   public static com.jeesite.common.l.l.l ALLATORIxDEMO(InputStream is, String path, long maxSize) {	
      com.jeesite.common.l.l.l a = null;	
      File a = ALLATORIxDEMO();	
      byte[] var10000 = new byte[2048];	
      boolean var10002 = true;	
      byte[] a = var10000;	
      BufferedInputStream a = new BufferedInputStream(is, 8192);	
	
      try {	
         BufferedOutputStream a = new BufferedOutputStream(new FileOutputStream(a), 8192);	
         int a = false;	
         BufferedInputStream var13 = a;	
	
         int a;	
         while((a = var13.read(a)) != -1) {	
            var13 = a;	
            a.write(a, 0, a);	
         }	
	
         a.flush();	
         a.close();	
         if (a.length() > maxSize) {	
            a.delete();	
            return new A(false, 1);	
         } else {	
            String a;	
            if (StringUtils.isNotBlank(a = Global.getConfig("file.allowContentTypes")) && !StringUtils.inString(FileUtils.getRealContentType(a), a.split(","))) {	
               a.delete();	
               return new A(false, 8);	
            } else {	
               if (!(a = ALLATORIxDEMO(a, path)).ALLATORIxDEMO()) {	
                  a.delete();	
               }	
	
               return a;	
            }	
         }	
      } catch (IOException var11) {	
         return new A(false, 4);	
      }	
   }	
}	
