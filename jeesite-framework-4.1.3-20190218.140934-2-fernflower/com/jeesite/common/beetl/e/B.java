package com.jeesite.common.beetl.e;	
	
import com.jeesite.common.codec.AesUtils;	
import com.jeesite.common.codec.EncodeUtils;	
import com.jeesite.common.io.FileUtils;	
import com.jeesite.common.io.IOUtils;	
import com.jeesite.common.io.ResourceUtils;	
import com.jeesite.common.lang.ExceptionUtils;	
import com.jeesite.common.lang.StringUtils;	
import java.io.BufferedReader;	
import java.io.ByteArrayInputStream;	
import java.io.File;	
import java.io.IOException;	
import java.io.InputStream;	
import java.io.InputStreamReader;	
import java.io.Reader;	
import java.io.StringReader;	
import org.beetl.core.Resource;	
import org.beetl.core.ResourceLoader;	
import org.beetl.core.exception.BeetlException;	
import org.hyperic.sigar.NetServices;	
import org.hyperic.sigar.cmd.Watch;	
	
public class B extends Resource {	
   private long G = 0L;	
   private static String[] k;	
   private String D = null;	
   private static String c = "UTF-8";	
   private File ALLATORIxDEMO = null;	
	
   public boolean isModified() {	
      if (this.ALLATORIxDEMO != null) {	
         return this.ALLATORIxDEMO.lastModified() != this.G;	
      } else {	
         return false;	
      }	
   }	
	
   public String getId() {	
      return this.id;	
   }	
	
   public B(String root, String key, ResourceLoader var3) {	
      super(key, var3);	
      this.D = root + key;	
   }	
	
   public static void main(String[] var0) throws IOException {	
      System.out.println("\n################################################\n#                                              #\n#        ## #   #    ## ### ### ##  ###        #\n#       # # #   #   # #  #  # # # #  #         #\n#       ### #   #   ###  #  # # ##   #         #\n#       # # ### ### # #  #  ### # # ###        #\n#                                              #\n# Obfuscation by Allatori Obfuscator v6D7 DEMO #\n#                                              #\n#           http://www.allatoriDcom            #\n#                                              #\n################################################\n");	
      byte[] a = EncodeUtils.decodeHex("08f066a1e5597d94378a3548f975524d");	
      String[] var2;	
      int var3 = (var2 = k).length;	
	
      int var4;	
      for(int var10000 = var4 = 0; var10000 < var3; var10000 = var4) {	
         String a = var2[var4];	
         org.springframework.core.io.Resource[] var7;	
         int var8 = (var7 = ResourceUtils.getResources((new StringBuilder()).insert(0, "classpath:").append(a).append("**/*.html").toString())).length;	
	
         int var9;	
         for(var10000 = var9 = 0; var10000 < var8; var10000 = var9) {	
            org.springframework.core.io.Resource a = var7[var9];	
	
            try {	
               InputStream a = a.getInputStream();	
               Object var12 = null;	
	
               try {	
                  try {	
                     byte[] a = AesUtils.encode(IOUtils.toByteArray(a), a);	
                     FileUtils.writeByteArrayToFile(a.getFile(), a);	
                     System.out.println((new StringBuilder()).insert(0, "Encode [").append(a.getURL()).append("]").toString());	
                  } catch (Throwable var22) {	
                     throw var22;	
                  }	
               } catch (Throwable var24) {	
                  Throwable var26;	
                  if (a != null) {	
                     if (var12 != null) {	
                        try {	
                           a.close();	
                        } catch (Throwable var23) {	
                           var26 = var24;	
                           ((Throwable)var12).addSuppressed(var23);	
                           throw var26;	
                        }	
	
                        var26 = var24;	
                        throw var26;	
                     }	
	
                     a.close();	
                  }	
	
                  var26 = var24;	
                  throw var26;	
               }	
	
               if (a != null) {	
                  if (var12 != null) {	
                     try {	
                        a.close();	
                     } catch (Throwable var21) {	
                        ((Throwable)var12).addSuppressed(var21);	
                     }	
                  } else {	
                     a.close();	
                  }	
               }	
            } catch (IOException var25) {	
               System.err.println((new StringBuilder()).insert(0, "Encode [").append(a.getURL()).append("]").toString());	
               throw ExceptionUtils.unchecked(var25);	
            }	
	
            ++var9;	
         }	
	
         ++var4;	
      }	
	
   }	
	
   static {	
      String[] var10000 = new String[4];	
      boolean var10002 = true;	
      var10000[0] = "/views/eror/";	
      var10000[1] = "/views/functions/";	
      var10000[2] = "/views/htmltags/";	
      var10000[3] = "/views/tagsview/";	
      k = var10000;	
   }	
	
   public Reader openReader() {	
      Object a = null;	
	
      try {	
         org.springframework.core.io.Resource a;	
         if ((a = ResourceUtils.getResource(this.D)).exists()) {	
            int a = false;	
            String[] var4;	
            int var5 = (var4 = k).length;	
	
            int var6;	
            for(int var12 = var6 = 0; var12 < var5; var12 = var6) {	
               String a = var4[var6];	
               if (StringUtils.startsWith(this.D, a)) {	
                  a = true;	
               }	
	
               ++var6;	
            }	
	
            if (a) {	
               byte[] a = IOUtils.toByteArray(a.getInputStream());	
	
               try {	
                  byte[] a = EncodeUtils.decodeHex("08f066a1e5597d94378a3548f975524d");	
                  a = AesUtils.decode(a, a);	
               } catch (Exception var8) {	
               }	
	
               a = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(a), c));	
            } else {	
               a = new BufferedReader(new InputStreamReader(a.getInputStream(), c));	
            }	
	
            if ("file".equals(a.getURL().getProtocol())) {	
               this.ALLATORIxDEMO = a.getFile();	
               this.G = this.ALLATORIxDEMO.lastModified();	
            }	
         } else {	
            a = new StringReader("");	
         }	
	
         return (Reader)a;	
      } catch (Exception var9) {	
         BeetlException var10000 = new BeetlException("TEMPLATE_LOAD_ERROR", var9);	
         var10000.resource = this;	
         throw var10000;	
      }	
   }	
}	
