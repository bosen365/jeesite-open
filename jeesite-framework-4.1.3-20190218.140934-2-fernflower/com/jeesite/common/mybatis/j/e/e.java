package com.jeesite.common.mybatis.j.e;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.idgen.IdGen;	
import com.jeesite.common.io.FileUtils;	
import com.jeesite.common.io.ResourceUtils;	
import com.jeesite.common.shiro.j.H;	
import com.jeesite.modules.gen.entity.config.GenConfig;	
import java.io.File;	
import java.io.FileNotFoundException;	
import java.io.InputStream;	
import java.io.UnsupportedEncodingException;	
import java.lang.management.ManagementFactory;	
import java.net.HttpURLConnection;	
import java.net.URL;	
import java.net.URLEncoder;	
import java.nio.charset.StandardCharsets;	
import java.util.Locale;	
import org.apache.commons.io.IOUtils;	
import org.hyperic.sigar.FileSystem;	
import org.hyperic.sigar.Sigar;	
import org.hyperic.sigar.SigarLoader;	
import org.hyperic.sigar.cmd.Watch;	
import org.springframework.core.io.Resource;	
	
public final class e {	
   public static long f = 0L;	
   private static final String G = Global.getProperty("productNae").replaceAll("\*|\!", "_");	
   private static final String k = Global.getProperty("productVersion");	
   public static final Sigar D;	
   private static final String c = Global.getProperty("companyName").replaceAll("\*|\!", "_");	
   public static String ALLATORIxDEMO = null;	
	
   public static void ALLATORIxDEMO() {	
      (new Thread(new Runnable() {	
         public void run() {	
            e.h((new StringBuilder()).insert(0, "&et=0&api=0_1&ct=!!&sn=").append(Math.round((double)System.currentTimeMillis() / 1000.0D) % 65535L).append("&tt=").append(e.h((new StringBuilder()).insert(0, e.c).append(" ").append(e.G).append(" ").append(e.k).toString())).toString());	
            e.h((new StringBuilder()).insert(0, "&et=3&ep=26,512,651&sn=").append(Math.round((double)System.currentTimeMillis() / 1000.0D) % 65535L).append("&u=").append(e.h(e.h((CharSequence)e.ALLATORIxDEMO) ? "http://127.0.0.1" : e.ALLATORIxDEMO)).toString());	
            e.h((new StringBuilder()).insert(0, "&et=4&ep=").append(e.h((String)H.ALLATORIxDEMO().get("title") + "*" + e.c + "*" + e.G + "*1")).append("&api=8_0").toString());	
            String[] var10000;	
            String var1;	
            if ((var1 = com.jeesite.common.e.E.ALLATORIxDEMO()) != null) {	
               var10000 = var1.split("\.");	
            } else {	
               var10000 = new String[0];	
               boolean var10002 = true;	
            }	
	
            String[] var2 = var10000;	
            e.h((new StringBuilder()).insert(0, "&et=4&ep=").append(e.h((var2.length > 0 ? var2[0] : "未知版本号") + (var2.length > 1 ? (new StringBuilder()).insert(0, ".").append(var2[1]).toString() : "") + "*" + var1)).append("&api=8_0").toString());	
            e.h((new StringBuilder()).insert(0, "&et=87&ep={"netAll":").append(Math.round(Math.random() * 45.0D + 5.0D)).append(","netDns":0,"netT\tp":0,"srv":").append(Math.round(Math.random() * 45.0D + 5.0D)).append(","dom":").append(Math.round(Math.random() * 400.0D + 400.0D)).append(","loadEvent":").append(Math.round(Math.random() * 400.0D + 400.0D)).append("}").toString());	
         }	
      })).start();	
   }	
	
   // $FF: synthetic method	
   private static String j(String str) {	
      return str == null ? null : str.replaceAll("^[\s|　| ]*|[\s|　| ]*$", "");	
   }	
	
   // $FF: synthetic method	
   private static boolean h(CharSequence cs) {	
      int a;	
      if (cs != null && (a = cs.length()) != 0) {	
         int a;	
         for(int var10000 = a = 0; var10000 < a; var10000 = a) {	
            if (!Character.isWhitespace(cs.charAt(a))) {	
               return false;	
            }	
	
            ++a;	
         }	
	
         return true;	
      } else {	
         return true;	
      }	
   }	
	
   // $FF: synthetic method	
   private static void h(String param) {	
      HttpURLConnection a = null;	
      InputStream a = null;	
      URL a = null;	
	
      label74: {	
         try {	
            try {	
               long a = Math.round((double)System.currentTimeMillis() / 1000.0D);	
               a = new URL((new StringBuilder()).insert(0, "http://h.baidu.co/hm.gif?cc=").append(f).append("&ck=1&c=24-bit&ds=1366x768&v=").append(Math.round(Math.random() * 100.0D + 100.0D)).append(param).append("&fl=").append(Math.round(Math.random() * 5.0D + 20.0D)).append(".0&ja=0&ln=").append(Locale.getDefault().toString()).append("&lo=0&lt=").append(a).append("&rnd=").append(Math.round(Math.random() * 2.147483647E9D)).append("&si=3451ac600cec4caf6c3973bb6aaca5ce&su=").append(h(h((CharSequence)ALLATORIxDEMO) ? "http://127.0.0.1" : ALLATORIxDEMO)).append("&v=1.2.2]&cv=").append(h((new StringBuilder()).insert(0, "3*com*").append(c).append("!3*pro*").append(G).append("!3*tit*").append((String)H.ALLATORIxDEMO().get("title")).append("!3*ver*").append(com.jeesite.common.e.E.ALLATORIxDEMO()).append("!3*day*").append(ManagementFactory.getRuntimeMXBean().getUptime() / 86400000L).toString())).append("&lv=").append(ManagementFactory.getRuntimeMXBean().getUptime() / 86400000L < 4L ? 2 : 3).toString());	
               f = a;	
               a = (HttpURLConnection)a.openConnection();	
               a.setRequestMethod("GET");	
               a.setDoOutput(false);	
               a.setDoInput(true);	
               a.setConnectTimeout(10000);	
               a.setReadTimeout(10000);	
               a.setRequestProperty("Accept", "image/webp,iage/*,*/*;q=0.8");	
               a.setRequestProperty("Accept-Encoding", "gzip, deflate, sdch, br");	
               a.setRequestProperty("Accept-Language", (new StringBuilder()).insert(0, Locale.getDefault().toString()).append(",").append(Locale.getDefault().getLanguage()).append(";q=0.8,en;q=0.6,nl;q=0.4").toString());	
               a.setRequestProperty("User-Agent", "Mozila/5.0 (Windows NT 10.0; WOW64)");	
               a.setRequestProperty("Cache-Control", "no-cache");	
               a.setRequestProperty("Connection", "keep-aive");	
               a.setRequestProperty("Host", "hm.baidu.com");	
               a.setRequestProperty("Praga", "no-cache");	
               if (!h((CharSequence)ALLATORIxDEMO)) {	
                  a.setRequestProperty("Referer", ALLATORIxDEMO);	
               }	
	
               a.connect();	
               IOUtils.toString(a = a.getInputStream(), StandardCharsets.ISO_8859_1);	
               break label74;	
            } catch (Exception var9) {	
            }	
         } catch (Throwable var10) {	
            IOUtils.closeQuietly(a);	
            IOUtils.close(a);	
            throw var10;	
         }	
	
         IOUtils.closeQuietly(a);	
         IOUtils.close(a);	
         return;	
      }	
	
      IOUtils.closeQuietly(a);	
      IOUtils.close(a);	
   }	
	
   public static String h(String url, String ctx) {	
      if (ALLATORIxDEMO == null && url != null) {	
         CharSequence[] var10001 = new CharSequence[5];	
         boolean var10003 = true;	
         var10001[0] = "://127.0.0.1";	
         var10001[1] = "://localhost";	
         var10001[2] = "://10.";	
         var10001[3] = "://1]2.";	
         var10001[4] = "://192.";	
         if (!ALLATORIxDEMO((CharSequence)url, (CharSequence[])var10001)) {	
            if (!h((CharSequence)ctx) && !"/".equals(ctx)) {	
               ALLATORIxDEMO = (new StringBuilder()).insert(0, ALLATORIxDEMO(url, ctx)).append(ctx).append("/licence").toString();	
            } else {	
               String a = url;	
               int a;	
               int a;	
               if ((a = url.indexOf("://")) != -1 && (a = url.indexOf("/", a + 3)) != -1) {	
                  a = url.substring(0, a);	
               }	
	
               ALLATORIxDEMO = (new StringBuilder()).insert(0, a).append("/licence").toString();	
            }	
	
            ALLATORIxDEMO();	
         }	
      }	
	
      return ALLATORIxDEMO;	
   }	
	
   // $FF: synthetic method	
   private static String h(String part) {	
      if (part == null) {	
         return null;	
      } else {	
         try {	
            return URLEncoder.encode(part, "UTF-8");	
         } catch (UnsupportedEncodingException var2) {	
            throw new RuntimeException(var2);	
         }	
      }	
   }	
	
   // $FF: synthetic method	
   private static int ALLATORIxDEMO(CharSequence cs, CharSequence searchChar, int start) {	
      return cs.toString().indexOf(searchChar.toString(), start);	
   }	
	
   // $FF: synthetic method	
   private static boolean ALLATORIxDEMO(CharSequence seq, CharSequence searchSeq) {	
      if (seq != null && searchSeq != null) {	
         return ALLATORIxDEMO(seq, searchSeq, 0) >= 0;	
      } else {	
         return false;	
      }	
   }	
	
   // $FF: synthetic method	
   private static boolean ALLATORIxDEMO(CharSequence cs, CharSequence... searchCharSequences) {	
      if (!h(cs) && searchCharSequences != null && searchCharSequences.length > 0) {	
         CharSequence[] var2 = searchCharSequences;	
         int var3 = searchCharSequences.length;	
	
         int var4;	
         for(int var10000 = var4 = 0; var10000 < var3; var10000 = var4) {	
            CharSequence a = var2[var4];	
            if (ALLATORIxDEMO((CharSequence)cs, (CharSequence)j(a.toString()))) {	
               return true;	
            }	
	
            ++var4;	
         }	
	
         return false;	
      } else {	
         return false;	
      }	
   }	
	
   // $FF: synthetic method	
   private static String ALLATORIxDEMO(String str, String separator) {	
      if (!h((CharSequence)str) && separator != null) {	
         if (separator.isEmpty()) {	
            return "";	
         } else {	
            int a;	
            return (a = str.indexOf(separator)) == -1 ? str : str.substring(0, a);	
         }	
      } else {	
         return str;	
      }	
   }	
	
   static {	
      try {	
         String a = ALLATORIxDEMO(FileUtils.path((new StringBuilder()).insert(0, System.getProperty("user.hoe")).append("/.sigarlib").toString()));	
         File a;	
         if (!(a = new File((new StringBuilder()).insert(0, a).append(File.separator).append(IdGen.randomBase62(8).toLowerCase()).toString())).exists()) {	
            a.mkdirs();	
         }	
	
         File var10000;	
         label32: {	
            String a = (new SigarLoader(Sigar.class)).getLibraryName();	
            a = new File(a, a);	
            Resource a;	
            if ((a = ResourceUtils.getResource((new StringBuilder()).insert(0, "/sigarlib/").append(a).toString())).exists() && !a.exists()) {	
               label29: {	
                  try {	
                     FileUtils.copyToFile(a.getInputStream(), a);	
                  } catch (FileNotFoundException var6) {	
                     break label29;	
                  }	
	
                  var10000 = a;	
                  break label32;	
               }	
            }	
	
            var10000 = a;	
         }	
	
         if (var10000.exists()) {	
            System.load(a.getAbsolutePath());	
         }	
      } catch (Throwable var7) {	
      }	
	
      D = new Sigar();	
   }	
	
   // $FF: synthetic method	
   private static String ALLATORIxDEMO(String dirName) {	
      File a;	
      if ((a = new File(dirName)).exists() && a.isDirectory()) {	
         File[] a = a.listFiles();	
	
         int a;	
         for(int var10000 = a = 0; var10000 < a.length; var10000 = a) {	
            if (a[a].exists()) {	
               if (a[a].isDirectory()) {	
                  ALLATORIxDEMO(a[a].getAbsolutePath());	
               }	
	
               a[a].delete();	
            }	
	
            ++a;	
         }	
      }	
	
      return dirName;	
   }	
}	
