package com.jeesite.common.mybatis.e;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.j2cache.cache.support.redis.ConfigureNotifyKeyspaceEventsAction;	
import com.jeesite.common.shiro.realm.LoginInfo;	
import com.jeesite.common.shiro.session.SessionDAO;	
import com.jeesite.common.utils.SpringUtils;	
import com.jeesite.common.web.http.ServletUtils;	
import com.jeesite.modules.sys.web.AdviceController;	
import java.io.BufferedReader;	
import java.io.InputStream;	
import java.io.InputStreamReader;	
import java.io.UnsupportedEncodingException;	
import java.lang.management.ManagementFactory;	
import java.security.GeneralSecurityException;	
import java.security.KeyFactory;	
import java.security.MessageDigest;	
import java.security.PrivateKey;	
import java.security.PublicKey;	
import java.security.interfaces.RSAPrivateKey;	
import java.security.interfaces.RSAPublicKey;	
import java.security.spec.PKCS8EncodedKeySpec;	
import java.security.spec.X509EncodedKeySpec;	
import java.text.SimpleDateFormat;	
import java.util.Arrays;	
import java.util.Collection;	
import java.util.Collections;	
import java.util.Comparator;	
import java.util.Date;	
import java.util.HashMap;	
import java.util.HashSet;	
import java.util.Iterator;	
import java.util.LinkedHashSet;	
import java.util.List;	
import java.util.Map;	
import java.util.Set;	
import java.util.Map.Entry;	
import javax.crypto.Cipher;	
import javax.crypto.SecretKey;	
import javax.crypto.spec.IvParameterSpec;	
import javax.crypto.spec.SecretKeySpec;	
import javax.servlet.http.HttpServletRequest;	
import org.apache.commons.io.IOUtils;	
import org.apache.shiro.session.Session;	
import org.apache.shiro.subject.PrincipalCollection;	
import org.apache.shiro.subject.support.DefaultSubjectContext;	
import org.hyperic.sigar.CpuInfo;	
import org.hyperic.sigar.FileSystem;	
import org.hyperic.sigar.FileSystemUsage;	
import org.hyperic.sigar.Mem;	
import org.hyperic.sigar.NetInterfaceConfig;	
import org.hyperic.sigar.Sigar;	
import org.hyperic.sigar.pager.PageList;	
	
public final class F {	
   private static Date f = new Date();	
   private static final String G = Global.getProperty("produ\ttName");	
   private static final <undefinedtype> k = new HashMap() {	
      private static boolean ALLATORIxDEMO = false;	
	
      public String ALLATORIxDEMO(String key, String value) {	
         if (ALLATORIxDEMO && !"message".equals(key) && !"title".equals(key) && (!"type".equals(key) || !"0".equals(value)) && !"load'essage".equals(key)) {	
            return null;	
         } else {	
            if ("loadMessage".equals(key)) {	
               ALLATORIxDEMO = true;	
            }	
	
            return (String)super.put(key, value);	
         }	
      }	
	
      public boolean ALLATORIxDEMO() {	
         return ALLATORIxDEMO;	
      }	
	
      public void putAll(Map m) {	
         if (m != null) {	
            Iterator var2;	
            for(Iterator var10000 = var2 = m.entrySet().iterator(); var10000.hasNext(); var10000 = var2) {	
               Entry a = (Entry)var2.next();	
               this.ALLATORIxDEMO((String)a.getKey(), (String)a.getValue());	
            }	
	
         }	
      }	
   };	
   private static final String D = Global.getProperty("companyNae");	
   private static final String[] c;	
   private static SessionDAO ALLATORIxDEMO;	
	
   // $FF: synthetic method	
   private static String ALLATORIxDEMO(String text, String searchString, String replacement, int max, boolean ignoreCase) {	
      if (!ALLATORIxDEMO((CharSequence)text) && !ALLATORIxDEMO((CharSequence)searchString) && replacement != null && max != 0) {	
         String a = text;	
         if (ignoreCase) {	
            a = text.toLowerCase();	
            searchString = searchString.toLowerCase();	
         }	
	
         int a = 0;	
         int a;	
         if ((a = a.indexOf(searchString, a)) == -1) {	
            return text;	
         } else {	
            int a = searchString.length();	
            int a = (a = replacement.length() - a) < 0 ? 0 : a;	
            a *= max < 0 ? 16 : (max > 64 ? 64 : max);	
            StringBuilder a = new StringBuilder(text.length() + a);	
            int var10000 = a;	
	
            StringBuilder var11;	
            while(true) {	
               if (var10000 == -1) {	
                  var11 = a;	
                  break;	
               }	
	
               a.append(text, a, a).append(replacement);	
               --max;	
               a = a + a;	
               if (max == 0) {	
                  var11 = a;	
                  break;	
               }	
	
               var10000 = a = a.indexOf(searchString, a);	
            }	
	
            var11.append(text, a, text.length());	
            return a.toString();	
         }	
      } else {	
         return text;	
      }	
   }	
	
   public static final Map ALLATORIxDEMO(Map info) {	
      Map a = new HashMap();	
      String a = "0";	
      String a = "社区版";	
	
      try {	
         if (!ALLATORIxDEMO((CharSequence)info.get("message"))) {	
            throw new Exception((String)info.get("message"));	
         }	
	
         String a;	
         if (ALLATORIxDEMO((CharSequence)(a = (String)info.get("code"))) || !ALLATORIxDEMO(a.split(","), c) || ALLATORIxDEMO((CharSequence)G) || ALLATORIxDEMO((CharSequence)D) || !ALLATORIxDEMO(G).equals(ALLATORIxDEMO((String)info.get("productNae"))) || !ALLATORIxDEMO(D).equals(ALLATORIxDEMO((String)info.get("companyName")))) {	
            throw new Exception((new StringBuilder()).insert(0, "您当前的版本为").append(a).toString());	
         }	
	
         a = (String)info.get("type");	
         a.put("type", a);	
         Map var10000;	
         if ("1".equals(a)) {	
            a = "个人版";	
            var10000 = info;	
         } else {	
            if ("2".equals(a)) {	
               a = "专业版";	
            }	
	
            var10000 = info;	
         }	
	
         String a = (String)var10000.get("expireDate");	
         if (!"-1".equals(a)) {	
            Date a;	
            long a;	
            if ((a = ((a = (new SimpleDateFormat("yyyy-MM-dd")).parse(a)).getTime() - System.currentTimeMillis()) / 3600000L / 24L) <= 0L) {	
               throw new Exception((new StringBuilder()).insert(0, "您的").append(a).append("许可，于").append((new SimpleDateFormat("yyyy年MM月dd日")).format(a)).append("已到期").toString());	
            }	
	
            HashMap var14;	
            if (a <= 7L) {	
               var14 = a;	
               a.put("message", (new StringBuilder()).insert(0, "您当前的版本为").append(a).append("，许可即将到期，还剩余最后").append(a).append("天。").toString());	
            } else if (a <= 60L) {	
               a.put("message", (new StringBuilder()).insert(0, "您当前的版本为").append(a).append("，许可即将到期，还剩余").append(a).append("天。").toString());	
               var14 = a;	
            } else {	
               a.put("message", (new StringBuilder()).insert(0, "您当前的版本为").append(a).append("，许可到期时间为：").append((new SimpleDateFormat("yyyy年MM月dd日")).format(a)).append("。").toString());	
               var14 = a;	
            }	
	
            var14.put("title", (new StringBuilder()).insert(0, a).append("（剩余").append(a).append("天）").toString());	
         } else {	
            a.put("message", (new StringBuilder()).insert(0, "您当前的版本为").append(a).append("，非常感谢您对我们产品的认可与支持！").toString());	
            a.put("title", a);	
         }	
      } catch (Exception var13) {	
         a.put("message", (new StringBuilder()).insert(0, var13.getMessage()).append("，官方网站：http://jeesite.com").toString());	
         a.put("title", a);	
         a.put("type", a);	
      }	
	
      if ("true".equals(info.get("devlop"))) {	
         a.put("title", "开发版");	
         a.put("type", "9");	
      }	
	
      return a;	
   }	
	
   public static final int ALLATORIxDEMO() {	
      if (ALLATORIxDEMO == null) {	
         ALLATORIxDEMO = (SessionDAO)SpringUtils.getBean(SessionDAO.class);	
      }	
	
      Set a = new HashSet();	
      com.jeesite.common.mybatis.j.n.D.ALLATORIxDEMO = false;	
      Iterator var1;	
      Iterator var10000 = var1 = ALLATORIxDEMO.getActiveSessions().iterator();	
	
      while(var10000.hasNext()) {	
         Session a = (Session)var1.next();	
         String a = "";	
         Object a;	
         LoginInfo a;	
         if ((a = a.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY)) != null && a instanceof PrincipalCollection && (a = (LoginInfo)((PrincipalCollection)a).getPrimaryPrincipal()) != null) {	
            a = a.getId();	
         }	
	
         if (ALLATORIxDEMO((CharSequence)a)) {	
            a = (String)a.getAttribute("userCode");	
         }	
	
         if (ALLATORIxDEMO((CharSequence)a)) {	
            var10000 = var1;	
         } else {	
            a.add(a);	
            var10000 = var1;	
         }	
      }	
	
      com.jeesite.common.mybatis.j.n.m.ALLATORIxDEMO = false;	
      return a.size();	
   }	
	
   // $FF: synthetic method	
   private static String h(String input) {	
      try {	
         return new String(null.ALLATORIxDEMO(input), "UTF-8");	
      } catch (UnsupportedEncodingException var2) {	
         return "";	
      }	
   }	
	
   // $FF: synthetic method	
   private static boolean ALLATORIxDEMO(String str, String... strs) {	
      if (str != null && strs != null) {	
         String[] var2 = strs;	
         int var3 = strs.length;	
	
         int var4;	
         for(int var10000 = var4 = 0; var10000 < var3; var10000 = var4) {	
            String a = var2[var4];	
            if (ALLATORIxDEMO(str).equals(ALLATORIxDEMO(a))) {	
               return true;	
            }	
	
            ++var4;	
         }	
      }	
	
      return false;	
   }	
	
   // $FF: synthetic method	
   private static String ALLATORIxDEMO(byte[] input) {	
      String a = "";	
      StringBuilder a = new StringBuilder("");	
	
      int a;	
      for(int var10000 = a = 0; var10000 < input.length; var10000 = a) {	
         a = Integer.toHexString(input[a] & 255);	
         a.append(a.length() == 1 ? (new StringBuilder()).insert(0, "0").append(a).toString() : a);	
         ++a;	
      }	
	
      return a.toString().trim();	
   }	
	
   // $FF: synthetic method	
   private static String h(String contentBase64, String privateKeyBase64) throws Exception {	
      PrivateKey a = ALLATORIxDEMO(privateKeyBase64);	
      Cipher a;	
      (a = Cipher.getInstance("RSA")).init(2, a);	
      int a = ((RSAPrivateKey)a).getModulus().bitLength() / 8;	
      byte[][] a = ALLATORIxDEMO(ALLATORIxDEMO(h(contentBase64)), a);	
      StringBuffer a = new StringBuffer();	
      byte[][] var8 = a;	
      int var9 = a.length;	
	
      int var10;	
      for(int var10000 = var10 = 0; var10000 < var9; var10000 = var10) {	
         byte[] a = var8[var10];	
         ++var10;	
         a.append(new String(a.doFinal(a)));	
      }	
	
      return h(a.toString());	
   }	
	
   // $FF: synthetic method	
   private static boolean ALLATORIxDEMO(CharSequence cs, boolean ignoreCase, int thisStart, CharSequence substring, int start, int length) {	
      if (cs instanceof String && substring instanceof String) {	
         return ((String)cs).regionMatches(ignoreCase, thisStart, (String)substring, start, length);	
      } else {	
         int a = thisStart;	
         int a = start;	
         int a = length;	
         int a = cs.length() - thisStart;	
         int a = substring.length() - start;	
         if (thisStart >= 0 && start >= 0 && length >= 0) {	
            if (a >= length && a >= length) {	
               char a;	
               char a;	
               do {	
                  int var10000 = a;	
	
                  while(true) {	
                     --a;	
                     if (var10000 <= 0) {	
                        return true;	
                     }	
	
                     a = cs.charAt(a);	
                     ++a;	
                     a = substring.charAt(a);	
                     ++a;	
                     if (a != a) {	
                        if (!ignoreCase) {	
                           return false;	
                        }	
                        break;	
                     }	
	
                     var10000 = a;	
                  }	
               } while(Character.toUpperCase(a) == Character.toUpperCase(a) || Character.toLowerCase(a) == Character.toLowerCase(a));	
	
               return false;	
            } else {	
               return false;	
            }	
         } else {	
            return false;	
         }	
      }	
   }	
	
   // $FF: synthetic method	
   private static byte[] h(String input) {	
      return null.ALLATORIxDEMO(input);	
   }	
	
   public static final Map ALLATORIxDEMO(InputStream inputStream) {	
      byte[] var10000 = new byte[0];	
      boolean var10002 = true;	
      byte[] a = var10000;	
	
      try {	
         a = IOUtils.toByteArray(inputStream);	
         String[] a;	
         String[] var24 = a = ">>>>大牛你好，能否留个联系方式，合作QQ：78112665，暗号：Licence520<<<<`]Km/KZk46sPt]e5xmn6ZIA==`MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAMMl5nOBVE+QJsNC+Iti3rxgMbTU8NhptURjxslLFCxm94uFUnKUQ1k9KUgfWH2+RlM8DAz0HSDJX94+Pgsgqw3IO]r2uSIXQcqPDVOxGT/qUaSMdscBYuC42cOdiB5F4cNGf/iPdQ6JZMBBTjCGRtLel/sCb8lditqoz6MhJfRAgMBAAECgYB/XSJ6fKa44K1AkFJwqgpPCzENMgWeWgRA]yjOfhC4EDUdaRnTxKKczde9UADWDrbidPHVIcHPST2SHSBCidNgeF/ox+MR]2wQRRWl]hjAUHRGnt8ICiCUpWvz9YPTlVORAJwk2wkBv3zBveUdR2oLFeNQ7ARvXlDLQW1ybAEQJBAPeJyC0I/2JG+BlpUzUgjKlOlQpl5]KfKdIF1sJ9gAuPKk4qHbVftcp6PqQhWEBtzrCsbNlofG7]ziSKt4QRfU8CQQDJ0aZK2hO0UqQDPviAqUX5ZC3S4C6CUTBOiPyLw+LlZBgg+jvyMuMwnxK/m9fK5hfZggQI5Ue3hVIbkstdJDfAkAnlmhmE2dMX080OSzudSsptICPbia04VF93iMvbYS51IaOg5vGsuzO2egEtbR4cVc52Al8Z4Jm+WxJWcnpnCZvAkA9Rz3hvmN]Phh0r9sOXddUSPs7MrSYMp1HhzoZxzzd/81fvfsTqCXZboNn0G]uOX0GWvbUqKFk9MMggirjZgrAkB4j5OC0]ov3aRS9/hwHgSj8iKAThsADOygDRifXeLTHAAmtHBHdS8lCqIvznAMAMHRa5N2v6h2i59LEE2YLO9l`PxXhwSyYKLHQlcx59Fl6Q==`MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCePYuA/B0NP8RG3q8zXjkYrAzcd5IMKbOkDbnwBn5dpwn1Cp0qBSpFKciUtvX3v6+HSoQW9UEVzvhOn0mgsTGArwd5dfVaR11AbPQEfxJjnI649LrjMw4No+AO+l4LhPWu83Mk4eR4FXMWs4vIh2lmWLTMCy4mGKndsTzao2QIDAQAB".split("`");	
         byte[] a = h(var24[3]);	
         byte[] a = h(var24[1]);	
         String a = h(ALLATORIxDEMO(ALLATORIxDEMO(h(new String(ALLATORIxDEMO(a, a, a), "UTF-8"), "\n\n", "\n\n"), "\n", ""), a[4]), a[2]);	
         int a = 96;	
         int a = 96 + 32;	
         byte[] a = ALLATORIxDEMO(a.substring(a, a));	
         int a = a;	
         a += 32;	
         byte[] a = ALLATORIxDEMO(a.substring(a, a));	
         a = a + 160;	
         a = a.length() - 64 - a[0].length();	
         byte[] a = ALLATORIxDEMO(a.substring(a, a));	
         String a = new String(ALLATORIxDEMO(a, a, a), "UTF-8");	
         HashMap a = new HashMap();	
         String[] var14;	
         int var15 = (var14 = a.substring(2, a.length() - 2).split("","")).length;	
	
         int var16;	
         for(int var26 = var16 = 0; var26 < var15; var26 = var16) {	
            String[] a = var14[var16].split("":"");	
            if ("updateCode".equals(a[0])) {	
               a = a.length == 2 ? a[1] : "";	
            } else {	
               a.put(a[0], a.length == 2 ? a[1] : "");	
            }	
	
            ++var16;	
         }	
	
         Collection var27 = a.values();	
         String[] var10001 = new String[a.values().size()];	
         boolean var10003 = true;	
         List a = Arrays.asList(var27.toArray(var10001));	
         Collections.sort(a, new Comparator() {	
            public int ALLATORIxDEMO(String ax, String axx) {	
               return ax.compareTo(axx);	
            }	
         });	
         String a = "";	
	
         Iterator var25;	
         for(Iterator var28 = var25 = a.iterator(); var28.hasNext(); var28 = var25) {	
            String a = (String)var25.next();	
            a = (new StringBuilder()).insert(0, a).append(a).toString();	
         }	
	
         return !ALLATORIxDEMO((String)a, 88).equals(a) ? new HashMap() : a;	
      } catch (Exception var19) {	
         if (a.length == 3232) {	
            HashMap var23 = new HashMap();	
            var23.put("devlop", "true");	
            return var23;	
         } else {	
            return new HashMap();	
         }	
      }	
   }	
	
   // $FF: synthetic method	
   private static byte[][] ALLATORIxDEMO(byte[] bytes, int splitLength) {	
      int a;	
      byte[][] var10000 = new byte[bytes.length % splitLength != 0 ? (a = bytes.length / splitLength + 1) : (a = bytes.length / splitLength)][];	
      boolean var10002 = true;	
      byte[][] a = var10000;	
	
      int a;	
      for(int var7 = a = 0; var7 < a; var7 = a) {	
         byte[] a;	
         byte[] var8;	
         if (a == a - 1 && bytes.length % splitLength != 0) {	
            var8 = new byte[bytes.length % splitLength];	
            var10002 = true;	
            a = var8;	
            System.arraycopy(bytes, a * splitLength, a, 0, bytes.length % splitLength);	
            var10000 = a;	
         } else {	
            var8 = new byte[splitLength];	
            var10002 = true;	
            a = var8;	
            var10000 = a;	
            System.arraycopy(bytes, a * splitLength, a, 0, splitLength);	
         }	
	
         var10000[a++] = a;	
      }	
	
      return a;	
   }	
	
   // $FF: synthetic method	
   private static boolean ALLATORIxDEMO(CharSequence cs, CharSequence... searchCharSequences) {	
      if (!ALLATORIxDEMO(cs) && searchCharSequences != null && searchCharSequences.length > 0) {	
         CharSequence[] var2 = searchCharSequences;	
         int var3 = searchCharSequences.length;	
	
         int var4;	
         for(int var10000 = var4 = 0; var10000 < var3; var10000 = var4) {	
            CharSequence a = var2[var4];	
            if (ALLATORIxDEMO((CharSequence)cs, (CharSequence)ALLATORIxDEMO(a.toString()))) {	
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
   private static byte[] ALLATORIxDEMO(byte[] input, byte[] key, byte[] iv) {	
      try {	
         SecretKey a = new SecretKeySpec(key, "AES");	
         IvParameterSpec a = new IvParameterSpec(iv);	
         Cipher a = Cipher.getInstance("AES/CBC/PKCS5Padding");	
         a.init(2, a, a);	
         return a.doFinal(input);	
      } catch (GeneralSecurityException var6) {	
         throw new RuntimeException(var6);	
      }	
   }	
	
   // $FF: synthetic method	
   private static String ALLATORIxDEMO(String contentBase64, String publicKeyBase64) throws Exception {	
      PublicKey a = ALLATORIxDEMO(publicKeyBase64);	
      Cipher a;	
      (a = Cipher.getInstance("RSA")).init(2, a);	
      int a = ((RSAPublicKey)a).getModulus().bitLength() / 8;	
      byte[][] a = ALLATORIxDEMO(ALLATORIxDEMO(h(contentBase64)), a);	
      StringBuffer a = new StringBuffer();	
      byte[][] var8 = a;	
      int var9 = a.length;	
	
      int var10;	
      for(int var10000 = var10 = 0; var10000 < var9; var10000 = var10) {	
         byte[] a = var8[var10];	
         ++var10;	
         a.append(new String(a.doFinal(a)));	
      }	
	
      return h(a.toString());	
   }	
	
   // $FF: synthetic method	
   private static boolean h(CharSequence cs1, CharSequence cs2) {	
      if (cs1 == cs2) {	
         return true;	
      } else if (cs1 != null && cs2 != null) {	
         if (cs1.length() != cs2.length()) {	
            return false;	
         } else {	
            return cs1 instanceof String && cs2 instanceof String ? cs1.equals(cs2) : ALLATORIxDEMO(cs1, false, 0, cs2, 0, cs1.length());	
         }	
      } else {	
         return false;	
      }	
   }	
	
   public static final <undefinedtype> ALLATORIxDEMO() {	
      long a = System.currentTimeMillis() - f.getTime();	
      if (k.ALLATORIxDEMO() && a / 86400000L <= 0L) {	
         return k;	
      } else {	
         f = new Date();	
         HashMap a = new HashMap();	
	
         try {	
            InputStream a;	
            if ((a = SpringUtils.getInputStream()) == null) {	
               throw new Exception("您当前的版本为社区版");	
            }	
	
            a.putAll(ALLATORIxDEMO(a));	
            if ("true".equals(a.get("devlop"))) {	
               throw new Exception("您当前的版本为开发版");	
            }	
         } catch (Exception var4) {	
            a.put("message", var4.getMessage());	
         }	
	
         a.putAll(ALLATORIxDEMO((Map)a));	
         a.put("modules", ",flow,weixin,");	
         a.put("openModules", (new StringBuilder()).insert(0, ",").append((String)a.get("openModules")).append(",").toString());	
         String var10000 = (String)a.get("type");	
         String[] var10001 = new String[3];	
         boolean var10003 = true;	
         var10001[0] = "1";	
         var10001[1] = "2";	
         var10001[2] = "9";	
         if (ALLATORIxDEMO(var10000, var10001)) {	
            a.put("fnJob", "true");	
            a.put("fnCas", "true");	
         }	
	
         var10000 = (String)a.get("type");	
         var10001 = new String[2];	
         var10003 = true;	
         var10001[0] = "2";	
         var10001[1] = "9";	
         if (ALLATORIxDEMO(var10000, var10001)) {	
            a.put("fnCluster", "true");	
            a.put("fnSaas", "true");	
            a.put("fnI18n", "true");	
            a.put("fnMsg", "true");	
         }	
	
         k.putAll(a);	
         StringBuilder a = new StringBuilder();	
         a.append("\r\n    " + (String)a.get("message") + "\r\n");	
         a.append((new StringBuilder()).insert(0, "\r\n    机器码是：").append(c.length > 0 ? c[0] : "").append("").toString());	
         a.append((new StringBuilder()).insert(0, "\r\n    产品名称：").append(G).append("").toString());	
         a.append((new StringBuilder()).insert(0, "\r\n    公司名称：").append(D).append("").toString());	
         a.append("\r\n");	
         k.ALLATORIxDEMO("loadMessage", a.toString());	
         com.jeesite.common.mybatis.j.e.e.ALLATORIxDEMO();	
         return k;	
      }	
   }	
	
   // $FF: synthetic method	
   private static boolean ALLATORIxDEMO(CharSequence cs) {	
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
   private static String h(String str, String open, String close) {	
      if (str != null && open != null && close != null) {	
         int a;	
         int a;	
         return (a = str.indexOf(open)) != -1 && (a = str.indexOf(close, a + open.length())) != -1 ? str.substring(a + open.length(), a) : null;	
      } else {	
         return null;	
      }	
   }	
	
   // $FF: synthetic method	
   private static PublicKey ALLATORIxDEMO(String publicKey) throws Exception {	
      byte[] a = h(publicKey);	
      X509EncodedKeySpec a = new X509EncodedKeySpec(a);	
      return KeyFactory.getInstance("RSA").generatePublic(a);	
   }	
	
   // $FF: synthetic method	
   private static int ALLATORIxDEMO(CharSequence cs, CharSequence searchChar, int start) {	
      return cs.toString().indexOf(searchChar.toString(), start);	
   }	
	
   // $FF: synthetic method	
   private static byte[] ALLATORIxDEMO(String input) {	
      int a = false;	
      int a = false;	
      int a;	
      byte[] var10000 = new byte[a = input.length() / 2];	
      boolean var10002 = true;	
      byte[] a = var10000;	
	
      int a;	
      for(int var9 = a = 0; var9 < a; var9 = a) {	
         int a;	
         int a = (a = a * 2 + 1) + 1;	
         int a = Integer.decode((new StringBuilder()).insert(0, "0x").append(input.substring(a * 2, a)).append(input.substring(a, a)).toString());	
         int var10001 = a;	
         byte var10 = Byte.valueOf((byte)a);	
         ++a;	
         a[var10001] = var10;	
      }	
	
      return a;	
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
   private static String ALLATORIxDEMO(String str) {	
      return str == null ? "" : str.replaceAll("^[\s|　| ]*|[\s|　| ]*$", "");	
   }	
	
   // $FF: synthetic method	
   private static PrivateKey ALLATORIxDEMO(String privateKey) throws Exception {	
      byte[] a = h(privateKey);	
      PKCS8EncodedKeySpec a = new PKCS8EncodedKeySpec(a);	
      return KeyFactory.getInstance("RSA").generatePrivate(a);	
   }	
	
   // $FF: synthetic method	
   private static final String ALLATORIxDEMO(String input, int iterations) {	
      try {	
         MessageDigest a;	
         byte[] a = (a = MessageDigest.getInstance("MD5")).digest(input.getBytes("UTF-8"));	
	
         int a;	
         for(int var10000 = a = 1; var10000 < iterations; var10000 = a) {	
            ++a;	
            a.reset();	
            a = a.digest(a);	
         }	
	
         return ALLATORIxDEMO(a);	
      } catch (Exception var5) {	
         throw new RuntimeException(var5);	
      }	
   }	
	
   public static final boolean ALLATORIxDEMO(HttpServletRequest request) {	
      if (request == null) {	
         request = ServletUtils.getRequest();	
      }	
	
      if (request != null) {	
         String a = request.getContextPath();	
         String a = request.getRequestURL().toString();	
         com.jeesite.common.mybatis.j.e.e.h(a, a);	
         String[] var10000 = new String[2];	
         boolean var10002 = true;	
         boolean var10005 = false;	
         var10000[0] = a + "/licen\te";	
         var10000[1] = (new StringBuilder()).insert(0, a).append("/licence/save").toString();	
         String[] a = var10000;	
         if (ALLATORIxDEMO(request.getRequestURI(), a)) {	
            return true;	
         }	
	
         if ("9".equals(k.get("type"))) {	
            if (ManagementFactory.getRuntimeMXBean().getUptime() / 86400000L > 1L) {	
               return false;	
            }	
	
            CharSequence[] var10001 = new CharSequence[5];	
            boolean var10003 = true;	
            var10001[0] = "://127.0.0.1";	
            var10001[1] = "://localhost";	
            var10001[2] = "://10.";	
            var10001[3] = "://1]2.";	
            var10001[4] = "://192.";	
            if (!ALLATORIxDEMO((CharSequence)a, (CharSequence[])var10001)) {	
               return false;	
            }	
         }	
	
         String a = (String)k.get("doainOrIp");	
         if (!h((CharSequence)((CharSequence)k.get("type")), (CharSequence)"0") && !ALLATORIxDEMO((CharSequence)a)) {	
            a = (new StringBuilder()).insert(0, "://127.0.0.1,://localhost,://10.,://172.,://192.,").append(a).toString();	
            if (!ALLATORIxDEMO((CharSequence)a, (CharSequence[])a.split(","))) {	
               return false;	
            }	
         }	
      }	
	
      return true;	
   }	
	
   static {	
      LinkedHashSet a = new LinkedHashSet();	
	
      try {	
         Sigar a = com.jeesite.common.mybatis.j.e.e.D;	
         String a = "";	
         CpuInfo[] a = a.getCpuInfoList();	
	
         try {	
            String a;	
            boolean var32;	
            label234: {	
               int a = false;	
               if ("Linux".equals(System.getProperty("os.name"))) {	
                  Process a = Runtime.getRuntime().exec("cat /proc/self/cgroup");	
                  BufferedReader a = new BufferedReader(new InputStreamReader(a.getInputStream()));	
                  a = null;	
	
                  try {	
                     try {	
                        String a = null;	
	
                        while((a = a.readLine()) != null && (a == null || !(a = a.contains("/docker/")))) {	
                        }	
                     } catch (Throwable var18) {	
                        throw var18;	
                     }	
                  } catch (Throwable var19) {	
                     Throwable var10000;	
                     label213: {	
                        if (a != null) {	
                           if (a != null) {	
                              try {	
                                 a.close();	
                              } catch (Throwable var17) {	
                                 var10000 = var19;	
                                 a.addSuppressed(var17);	
                                 break label213;	
                              }	
	
                              var10000 = var19;	
                              break label213;	
                           }	
	
                           a.close();	
                        }	
	
                        var10000 = var19;	
                     }	
	
                     throw var10000;	
                  }	
	
                  if (a != null) {	
                     if (a != null) {	
                        try {	
                           a.close();	
                        } catch (Throwable var20) {	
                           var32 = a;	
                           a.addSuppressed(var20);	
                           break label234;	
                        }	
	
                        var32 = a;	
                        break label234;	
                     }	
	
                     a.close();	
                     var32 = a;	
                     break label234;	
                  }	
               }	
	
               var32 = a;	
            }	
	
            if (var32) {	
               if (a != null && a.length > 0) {	
                  CpuInfo a = a[0];	
                  a = a.length + "|" + a.getVendor() + "|" + a.getModel() + "|" + a.getTotalCores() + "|" + a.getMhz();	
               }	
	
               String a = "";	
               FileSystem[] a = a.getFileSystemList();	
	
               int a;	
               for(int var33 = a = 0; var33 < a.length; var33 = a) {	
                  FileSystem a;	
                  if ((a = a[a]).getType() == 2) {	
                     FileSystemUsage a = a.getFileSystemUsage(a.getDirName());	
                     a = (new StringBuilder()).insert(0, a).append("|").append(a.getDirName()).append("|").append(a.getSysTypeName()).append("|").append(a.getTotal()).toString();	
                  }	
	
                  ++a;	
               }	
	
               a = "";	
               Mem a = a.getMem();	
               a = (new StringBuilder()).insert(0, a).append("|").append(a.getTotal()).toString();	
               a.add(ALLATORIxDEMO((String)(a + a + a), 76));	
            }	
         } catch (Exception var21) {	
         }	
	
         a = a.length > 0 ? a[0].getModel() : "";	
         String[] a = a.getNetInterfaceList();	
	
         for(int a = 0; a < a.length; ++a) {	
            NetInterfaceConfig a;	
            if (((a = a.getNetInterfaceConfig(a[a])).getFlags() & 8L) == 0L && !"127.0.0.1".equals(a.getAddress()) && !"0.0.0.0".equals(a.getAddress()) && !"00:00:00:00:00:00".equals(a.getHwaddr())) {	
               a.add(ALLATORIxDEMO((String)(new StringBuilder()).insert(0, a).append(a.getDescription()).append(a.getHwaddr()).toString(), 76));	
            }	
         }	
      } catch (Throwable var22) {	
      }	
	
      String[] var10001 = new String[a.size()];	
      boolean var10003 = true;	
      c = (String[])a.toArray(var10001);	
   }	
	
   // $FF: synthetic method	
   private static String ALLATORIxDEMO(String text, String searchString, String replacement) {	
      return ALLATORIxDEMO(text, searchString, replacement, -1, false);	
   }	
	
   // $FF: synthetic method	
   private static boolean ALLATORIxDEMO(String[] str, String[] strs) {	
      if (str != null && strs != null) {	
         String[] var2 = str;	
         int var3 = str.length;	
	
         int var4;	
         for(int var10000 = var4 = 0; var10000 < var3; var10000 = var4) {	
            String a = var2[var4];	
            String[] var6 = strs;	
            int var7 = strs.length;	
	
            int var8;	
            for(var10000 = var8 = 0; var10000 < var7; var10000 = var8) {	
               String a = var6[var8];	
               if (ALLATORIxDEMO(a).equals(ALLATORIxDEMO(a))) {	
                  return true;	
               }	
	
               ++var8;	
            }	
	
            ++var4;	
         }	
      }	
	
      return false;	
   }	
}	
