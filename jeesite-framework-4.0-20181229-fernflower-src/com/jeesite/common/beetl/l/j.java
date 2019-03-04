package com.jeesite.common.beetl.l;	
	
import com.jeesite.common.codec.AesUtils;	
import com.jeesite.common.codec.EncodeUtils;	
import com.jeesite.common.io.FileUtils;	
import com.jeesite.common.io.IOUtils;	
import com.jeesite.common.io.ResourceUtils;	
import com.jeesite.common.lang.ExceptionUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.common.service.BaseService;	
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
	
public class j extends Resource {	
   private static String B = "UTF-8";	
   private long G = 0L;	
   private File d = null;	
   private String c = null;	
   private static String[] ALLATORIxDEMO;	
	
   public boolean isModified() {	
      if (this.d != null) {	
         return this.d.lastModified() != this.G;	
      } else {	
         return false;	
      }	
   }	
	
   public Reader openReader() {	
      Object a = null;	
	
      try {	
         org.springframework.core.io.Resource a;	
         if ((a = ResourceUtils.getResource(this.c)).exists()) {	
            int a = false;	
            String[] var4;	
            int var5 = (var4 = ALLATORIxDEMO).length;	
	
            int var6;	
            for(int var12 = var6 = 0; var12 < var5; var12 = var6) {	
               String a = var4[var6];	
               if (StringUtils.startsWith(this.c, a)) {	
                  a = true;	
               }	
	
               ++var6;	
            }	
	
            if (a) {	
               byte[] a = IOUtils.toByteArray(a.getInputStream());	
	
               try {	
                  byte[] a = EncodeUtils.decodeHex("2decd4017bab5a37c17324f5fb296485");	
                  a = AesUtils.decode(a, a);	
               } catch (Exception var8) {	
               }	
	
               a = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(a), B));	
            } else {	
               a = new BufferedReader(new InputStreamReader(a.getInputStream(), B));	
            }	
	
            if ("file".equals(a.getURL().getProtocol())) {	
               this.d = a.getFile();	
               this.G = this.d.lastModified();	
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
	
   public j(String key, ResourceLoader resourceLoader) {	
      super(key, resourceLoader);	
      this.c = key;	
   }	
	
   static {	
      String[] var10000 = new String[4];	
      boolean var10002 = true;	
      var10000[0] = "/views/error/";	
      var10000[1] = "/views/functions/";	
      var10000[2] = "/views/htmltags/";	
      var10000[3] = "/views/tagsview/";	
      ALLATORIxDEMO = var10000;	
   }	
	
   public static void main(String[] var0) throws IOException {	
      System.out.println("\n################################################\n#                                              #\n#        ## #   #    ## ### ### ##  ###        #\n#       # # #   #   # #  #  # # # #  #         #\n#       ### #   #   ###  #  # # ##   #         #\n#       # # ### ### # #  #  ### # # ###        #\n#                                              #\n# Obfuscation by Allatori Obfuscator v6.5 DEMO #\n#                                              #\n#           http://www.allatori.com            #\n#                                              #\n################################################\n");	
      byte[] a = EncodeUtils.decodeHex("2decd4017bab5a37c17324f5fb296485");	
      String[] var2;	
      int var3 = (var2 = ALLATORIxDEMO).length;	
	
      int var4;	
      for(int var10000 = var4 = 0; var10000 < var3; var10000 = var4) {	
         String a = var2[var4];	
         org.springframework.core.io.Resource[] var7;	
         int var8 = (var7 = ResourceUtils.getResources((new StringBuilder()).insert(0, "classpath:").append(a).append("**/*.html").toString())).length;	
	
         int var9;	
         for(var10000 = var9 = 0; var10000 < var8; var10000 = var9) {	
            org.springframework.core.io.Resource a = var7[var9];	
            InputStream a = null;	
	
            try {	
               try {	
                  byte[] a = AesUtils.encode(IOUtils.toByteArray(a = a.getInputStream()), a);	
                  FileUtils.writeByteArrayToFile(a.getFile(), a);	
                  System.out.println((new StringBuilder()).insert(0, "Encode [").append(a.getURL()).append("]").toString());	
               } catch (IOException var16) {	
                  System.err.println((new StringBuilder()).insert(0, "Encode [").append(a.getURL()).append("]").toString());	
                  throw ExceptionUtils.unchecked(var16);	
               }	
            } catch (Throwable var17) {	
               IOUtils.closeQuietly(a);	
               throw var17;	
            }	
	
            IOUtils.closeQuietly(a);	
            ++var9;	
         }	
	
         ++var4;	
      }	
	
   }	
	
   public String getId() {	
      return this.id;	
   }	
}	
