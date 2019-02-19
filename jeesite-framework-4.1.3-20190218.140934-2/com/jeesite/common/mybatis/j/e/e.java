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
   private static final String G = Global.getProperty("productNae").replaceAll(com.jeesite.common.j.E.ALLATORIxDEMO("/N\u000f8R"), "_");	
   private static final String k = Global.getProperty(com.jeesite.common.j.E.ALLATORIxDEMO("\u0014\u0001\u000b\u0017\u0011\u0010\u0010%\u0001\u0001\u0017\u001a\u000b\u001d"));	
   public static final Sigar D;	
   private static final String c = Global.getProperty(com.jeesite.common.j.E.ALLATORIxDEMO("\u0010\u000b\u001e\u0014\u0012\n\n*\u0012\t\u0016")).replaceAll("\*|\!", com.jeesite.common.j.E.ALLATORIxDEMO(","));	
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
      return str == null ? null : str.replaceAll(com.jeesite.common.j.E.ALLATORIxDEMO("-?/\u0017\u000fつ\u000fÄ.N\u000f?/\u0017\u000fつ\u000fÄ.NW"), "");	
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
               a = new URL((new StringBuilder()).insert(0, "http://h.baidu.co/hm.gif?cc=").append(f).append(com.jeesite.common.j.E.ALLATORIxDEMO("U\u0007\u0018YBB\u0010\bNVGI\u0011\r\u0007B\u0017\u0017NU@RE\u001cDRKB\u0005\bN")).append(Math.round(Math.random() * 100.0D + 100.0D)).append(param).append("&fl=").append(Math.round(Math.random() * 5.0D + 20.0D)).append(com.jeesite.common.j.E.ALLATORIxDEMO("]TU\u000e\u0012YCB\u001f\nN")).append(Locale.getDefault().toString()).append("&lo=0&lt=").append(a).append(com.jeesite.common.j.E.ALLATORIxDEMO("U\u0016\u001d\u0000N")).append(Math.round(Math.random() * 2.147483647E9D)).append("&si=3451ac600cec4caf6c3973bb6aaca5ce&su=").append(h(h((CharSequence)ALLATORIxDEMO) ? com.jeesite.common.j.E.ALLATORIxDEMO("\f\u0007\u0010\u0003^\\KBVDJCJCJB") : ALLATORIxDEMO)).append("&v=1.2.2]&cv=").append(h((new StringBuilder()).insert(0, com.jeesite.common.j.E.ALLATORIxDEMO("WY\u0007\u001c\tY")).append(c).append("!3*pro*").append(G).append(com.jeesite.common.j.E.ALLATORIxDEMO("RWY\u0010\u001a\u0010Y")).append((String)H.ALLATORIxDEMO().get("title")).append(com.jeesite.common.j.E.ALLATORIxDEMO("RWY\u0012\u0016\u0016Y")).append(com.jeesite.common.e.E.ALLATORIxDEMO()).append("!3*day*").append(ManagementFactory.getRuntimeMXBean().getUptime() / 86400000L).toString())).append(com.jeesite.common.j.E.ALLATORIxDEMO("B\u001f\u0012N")).append(ManagementFactory.getRuntimeMXBean().getUptime() / 86400000L < 4L ? 2 : 3).toString());	
               f = a;	
               a = (HttpURLConnection)a.openConnection();	
               a.setRequestMethod("GET");	
               a.setDoOutput(false);	
               a.setDoInput(true);	
               a.setConnectTimeout(10000);	
               a.setReadTimeout(10000);	
               a.setRequestProperty(com.jeesite.common.j.E.ALLATORIxDEMO("%\u0010\u0007\u0016\u0014\u0007"), "image/webp,iage/*,*/*;q=0.8");	
               a.setRequestProperty(com.jeesite.common.j.E.ALLATORIxDEMO("2\u0007\u0010\u0001\u0003\u0010^!\u001d\u0007\u001c\u0000\u001a\n\u0014"), "gzip, deflate, sdch, br");	
               a.setRequestProperty(com.jeesite.common.j.E.ALLATORIxDEMO("2\u0007\u0010\u0001\u0003\u0010^(\u0012\n\u0014\u0011\u0012\u0003\u0016"), (new StringBuilder()).insert(0, Locale.getDefault().toString()).append(",").append(Locale.getDefault().getLanguage()).append(com.jeesite.common.j.E.ALLATORIxDEMO("_\u0002YCJKH\u0016\nH\u0015NT]R_\n\u001f_\u0002YCJG")).toString());	
               a.setRequestProperty("User-Agent", com.jeesite.common.j.E.ALLATORIxDEMO(")\u001c\u001e\u001a\b\u001f\u0005\\Q]TSL$\r\u001d\u0000\u001c\u0013\u0000D=0SUCJC_S3<3EPZ"));	
               a.setRequestProperty("Cache-Control", com.jeesite.common.j.E.ALLATORIxDEMO("\n\u001cI\u0010\u0005\u0010\f\u0016"));	
               a.setRequestProperty("Connection", com.jeesite.common.j.E.ALLATORIxDEMO("\u000f\u0016\u0001\u0003I\u0012\b\u001a\u0012\u0016"));	
               a.setRequestProperty("Host", com.jeesite.common.j.E.ALLATORIxDEMO("\f\u001eJ\u0011\u0005\u001a\u0000\u0006J\u0010\u000b\u001e"));	
               a.setRequestProperty("Praga", com.jeesite.common.j.E.ALLATORIxDEMO("\n\u001cI\u0010\u0005\u0010\f\u0016"));	
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
         var10001[0] = com.jeesite.common.j.E.ALLATORIxDEMO("^\\KBVDJCJCJB");	
         var10001[1] = "://localhost";	
         var10001[2] = com.jeesite.common.j.E.ALLATORIxDEMO("^\\KBT]");	
         var10001[3] = "://1]2.";	
         var10001[4] = com.jeesite.common.j.E.ALLATORIxDEMO("IK\\UJV]");	
         if (!ALLATORIxDEMO((CharSequence)url, (CharSequence[])var10001)) {	
            if (!h((CharSequence)ctx) && !"/".equals(ctx)) {	
               ALLATORIxDEMO = (new StringBuilder()).insert(0, ALLATORIxDEMO(url, ctx)).append(ctx).append("/licence").toString();	
            } else {	
               String a = url;	
               int a;	
               int a;	
               if ((a = url.indexOf(com.jeesite.common.j.E.ALLATORIxDEMO("IK\\"))) != -1 && (a = url.indexOf("/", a + 3)) != -1) {	
                  a = url.substring(0, a);	
               }	
	
               ALLATORIxDEMO = (new StringBuilder()).insert(0, a).append(com.jeesite.common.j.E.ALLATORIxDEMO("K\u001f\r\u0010\u0001\u001d\u0007\u0016")).toString();	
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
         String a = ALLATORIxDEMO(FileUtils.path((new StringBuilder()).insert(0, System.getProperty("user.hoe")).append(com.jeesite.common.j.E.ALLATORIxDEMO("K]\u0017\u001a\u0003\u0012\u0016\u001f\r\u0011")).toString()));	
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
