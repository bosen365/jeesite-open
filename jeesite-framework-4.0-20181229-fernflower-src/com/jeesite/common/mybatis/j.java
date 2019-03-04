package com.jeesite.common.mybatis;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.io.FileUtils;	
import com.jeesite.common.io.ResourceUtils;	
import com.jeesite.common.l.i.D;	
import com.jeesite.common.web.http.ServletUtils;	
import com.jeesite.modules.sys.utils.ValidCodeUtils;	
import java.io.File;	
import java.io.InputStream;	
import java.io.UnsupportedEncodingException;	
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
import java.util.List;	
import java.util.Map;	
import javax.crypto.Cipher;	
import javax.crypto.SecretKey;	
import javax.crypto.spec.IvParameterSpec;	
import javax.crypto.spec.SecretKeySpec;	
import javax.servlet.http.HttpServletRequest;	
import org.apache.commons.io.IOUtils;	
import org.hyperic.sigar.CpuInfo;	
import org.hyperic.sigar.NetInterfaceConfig;	
import org.hyperic.sigar.Sigar;	
	
public final class j {	
   private static final String[] B;	
   private static final String G = Global.getProperty("companyName");	
   private static final l$li d = new l$li();	
   private static Date c = new Date();	
   private static final String ALLATORIxDEMO = Global.getProperty("producName");	
	
   // $FF: synthetic method	
   private static String H(String contentBase64, String privateKeyBase64) throws Exception {	
      PrivateKey a = ALLATORIxDEMO(privateKeyBase64);	
      Cipher a;	
      (a = Cipher.getInstance("RSA")).init(2, a);	
      int a = ((RSAPrivateKey)a).getModulus().bitLength() / 8;	
      byte[][] a = ALLATORIxDEMO(ALLATORIxDEMO(H(contentBase64)), a);	
      StringBuffer a = new StringBuffer();	
      byte[][] var8 = a;	
      int var9 = a.length;	
	
      int var10;	
      for(int var10000 = var10 = 0; var10000 < var9; var10000 = var10) {	
         byte[] a = var8[var10];	
         ++var10;	
         a.append(new String(a.doFinal(a)));	
      }	
	
      return H(a.toString());	
   }	
	
   // $FF: synthetic method	
   private static String H(String str, String open, String close) {	
      if (str != null && open != null && close != null) {	
         int a;	
         int a;	
         return (a = str.indexOf(open)) != -1 && (a = str.indexOf(close, a + open.length())) != -1 ? str.substring(a + open.length(), a) : null;	
      } else {	
         return null;	
      }	
   }	
	
   // $FF: synthetic method	
   private static boolean H(CharSequence cs1, CharSequence cs2) {	
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
	
   // $FF: synthetic method	
   private static PublicKey ALLATORIxDEMO(String publicKey) throws Exception {	
      byte[] a = H(publicKey);	
      X509EncodedKeySpec a = new X509EncodedKeySpec(a);	
      return KeyFactory.getInstance("RSA").generatePublic(a);	
   }	
	
   public static final boolean ALLATORIxDEMO(HttpServletRequest request) {	
      if (request == null) {	
         request = ServletUtils.getRequest();	
      }	
	
      if (request != null) {	
         String a = request.getContextPath();	
         String a = request.getRequestURL().toString();	
         com.jeesite.common.l.l.I.ALLATORIxDEMO(a, a);	
         String[] var10000 = new String[2];	
         boolean var10002 = true;	
         boolean var10005 = false;	
         var10000[0] = a + "/licence";	
         var10000[1] = (new StringBuilder()).insert(0, a).append("/licence/save").toString();	
         String[] a = var10000;	
         if (ALLATORIxDEMO(request.getRequestURI(), a)) {	
            return true;	
         }	
	
         String a = (String)d.get("domainOrIp");	
         if (!H((CharSequence)((CharSequence)d.get("type")), (CharSequence)"0") && !ALLATORIxDEMO((CharSequence)a)) {	
            a = (new StringBuilder()).insert(0, "://127.0.0.1,://localhost,").append(a).toString();	
            if (!ALLATORIxDEMO((CharSequence)a, (CharSequence[])a.split(","))) {	
               return false;	
            }	
         }	
      }	
	
      return true;	
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
               if (a.equals(ALLATORIxDEMO(a))) {	
                  return true;	
               }	
	
               ++var8;	
            }	
	
            ++var4;	
         }	
      }	
	
      return false;	
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
	
   public static final l$li ALLATORIxDEMO() {	
      long a = System.currentTimeMillis() - c.getTime();	
      if (d.isLoaded() && a / 86400000L <= 0L) {	
         return d;	
      } else {	
         c = new Date();	
         HashMap a = new HashMap();	
	
         try {	
            InputStream a = null;	
            String a = (new StringBuilder()).insert(0, ResourceUtils.getResource("config/jeesite.yml").getFile().getParentFile().getParentFile().getParent()).append(File.separator).append("jeesite.lic").toString();	
            File a;	
            if ((a = new File(a)).exists()) {	
               a = FileUtils.openInputStream(a);	
            }	
	
            if (a == null) {	
               throw new Exception("您当前的版本为社区版");	
            }	
	
            a.putAll(ALLATORIxDEMO((InputStream)a));	
         } catch (Exception var6) {	
            a.put("message", var6.getMessage());	
         }	
	
         a.putAll(ALLATORIxDEMO((Map)a));	
         a.put("modules", ",flow,weixin,");	
         a.put("openModules", (new StringBuilder()).insert(0, ",").append((String)a.get("openModules")).append(",").toString());	
         String var10000 = (String)a.get("type");	
         String[] var10001 = new String[2];	
         boolean var10003 = true;	
         var10001[0] = "1";	
         var10001[1] = "2";	
         if (ALLATORIxDEMO(var10000, var10001)) {	
            a.put("fnJob", "true");	
            a.put("fnCs", "true");	
         }	
	
         var10000 = (String)a.get("type");	
         var10001 = new String[1];	
         var10003 = true;	
         var10001[0] = "2";	
         if (ALLATORIxDEMO(var10000, var10001)) {	
            a.put("fnCluster", "true");	
            a.put("fnSaas", "true");	
            a.put("fnI18n", "true");	
            a.put("fnMsg", "true");	
         }	
	
         d.putAll(a);	
         StringBuilder a = new StringBuilder();	
         a.append("\r\n    " + (String)a.get("message") + "\r\n");	
         a.append((new StringBuilder()).insert(0, "\r\n    机器码是：").append(B.length > 0 ? B[0] : "").append("").toString());	
         a.append((new StringBuilder()).insert(0, "\r\n    产品名称：").append(ALLATORIxDEMO).append("").toString());	
         a.append((new StringBuilder()).insert(0, "\r\n    公司名称：").append(G).append("").toString());	
         a.append("\r\n");	
         d.put("loadMessage", a.toString());	
         com.jeesite.common.l.l.I.ALLATORIxDEMO();	
         return d;	
      }	
   }	
	
   public static final Map ALLATORIxDEMO(InputStream inputStream) {	
      byte[] var10000 = new byte[0];	
      boolean var10002 = true;	
	
      try {	
         byte[] a = IOUtils.toByteArray(inputStream);	
         String[] a = ">>>>大牛你好，能否留个联系方式，合作QQ：78112665，暗号：Licence520<<<<`7Km/KZk46sPt7e5xmn6ZIA==`MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAMMl5nOBVE+QJsNC+Iti3rxgMbTU8NhptURjxslLFCxm94uFUnKUQ1k9KUgfWH2+RlM8DAz0HSDJX94+Pgsgqw3IO7r2uSIXQcqPDVOxGT/qUaSMdscBYuC42cOdiB5F4cNGf/iPdQm6JZMBBTjCGRtLel/sCb8lditqoz6MhJfRAgMBAAECgYB/mXSJ6fKa44K1AkFJwqgpPCzENMgWeWgRA7yjOfhC4EDUdaRnTxKKczde9UADWDrbidPHVIcHPST2SHSBCidNgeF/ox+MR72wQRRWl7hjmAUHRGnt8ICiCUpWvz9YPTlVORAJwk2wkBv3zBveUdR2oLFeNQ7ARvXlDLQW1ybAEQJBAPeJyC0I/2JG+BlpUzUgjKlOlQpl57KfKdIF1sJ9gAuPKk4qHbVftcp6PqQhWEBtzrCsbNlofG77ziSKt4QRfU8CQQDJ0aZK2hO0UqQDPviAqUX5ZC3S4C6CUTBOiPyLw+mLlZBgg+jvyMuMwnxK/m9fK5hfZggQI5Ue3hVIbkstdJDfAkAnlmhmE2dMX080OSzudSsptICPbi04VF93iMvbYS51IOg5vGsuzO2egEtbR4cVc52Al8Z4Jm+WxJWcnpnCZvAkA9Rz3hvmN7Phh0r9sOXddUSPms7MrSYMp1HhzoZxzzd/81fvfsTqCXZboNn0G7uOX0GWvbUqKFk9MMggirjZgrAkB4j5OC07ov3aRS9/hwHgSj8iKAThsADOygDRifXeLTHAAmtHBHdS8lCqIvznAMAMHR5N2v6h2i59LEE2YLO9l`PxXhwSyYKLHQlmcx59Fl6Q==`MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCePYuA/B0NP8RG3q8zXjmkYrAzcd5IMKbOkDbnwBn5dpwn1Cp0qBSpFKciUtvX3v6+HSoQW9UEVzvhOn0mgsTGArwd5dfVR11AbPQEfxJjnI649LrjMmw4No+AO+l4LhPWu83Mk4eR4FXMWs4vIh2lmWLTMCy4mGKndsTzamo2QIDAQAB".split("`");	
         String[] a = "f3872c937e1652955cbee3619b864e91,7cf38063084e3765a827c2312c4f4e2f,784dec4e53fecd6df47684178958d72,34274c64b0b20f2f2973999496248,44e2e93b7b4d8b6339609592b88a1906,46862bd120c8bdacdd709291c538f7d1,fd08746352f716bacd46e9f19b778c,17036e08c569df026e2d7d042b1b4eb,34b10939ff23bde7d45286b7fd9d4ed9,fc20a2b20d13ed8b26e25d8fc47bbcf3,2e1bc7e75fd42ec57d7694d8e02bbd24,3bd556a5a38b1f07a274e950ce7c9fc2,e7b96e008884f1c6e57e1c75f6578136,f9f3046b26ef875d897450dccb97c1f,6e11f9bd5833fa0214bd61150f61e9a,0d8935c3af2a2274fa2e13541466c05,530a75f7621f23f9675841239e9e61,6410e44f0e79b2c2d052644bc5c397d8,6579d12404a4e4d944de2ad87e4485,7464ae3ef8c1614e5de36760acf29259,3c163fcb8749503e9747650adc876c,0eed2e3d997bb03cfe149d19c4845d5,101606de6ab16a83d419663533169c5,2e0608555913296cbfdf55f4ed9855,e4c360304307e166869f6008cb7c926,e8f190975228b615e4a8a7108dd65,5d13220e30cdb4e96c265ebb32c8ada,829cf34d79fcd6760b51e4b00e1197de,cf4cbeb9cdd1d9f675414959b62c9b32,c9d0508f132de9db8854fc511be7d0b,6d286b0834c67d94c809debd5a4192d9,a8fb72a5afe72feed739dbb8839390af,99ba5b0815cfe65f9db60dc3a317c0e,6d5d5b0cdc27c613b82f9e963c739ef,0b78085931c256e70c21dfa88f16882,7ba67a12c3be5e0b8ea964b26656f,1a0fbd4129cd3a99d49561789700ae8,edb5031797a3ae68df3384fb0b02efe,c9560e7bcc87c1d081478261f7de8,32f93189594bd8ac90a5ac125f8799ab,b2637d676b91403dcb6c14cf9739745,d6528f47c786878cde410e56e9b7e2d,9f705cb2182511540d7c691874a698f,1e9f02b60b19334c61928b569f36db,927e6199368a1d8637e7fd1b743c58,2c2c917f18844510d6db6d0783fa0cb,2e01686dc904c3fd8499ef567ea1a5f,2e0e19500995007072c2869567c0832,d1f2fffa4702e051fd82c526fbc7d,3873c5614b476f7b1ebcc634a41822,0b57437996ba989dd23015fa8f55205a,6c7b1e63590fb942dc181daeac049,dd93f99734cb619bc329e1b5dc043b,66a3179cce9737ce0e183deed031d8a4,e8d90395b648935a84282e8c22fffe9c,96cd1ff72db8fcf59ec062f210ee196,5c190cfd1a64a72be683bc0801d59a,f466fce7053a07b8c51a91da40cd55f,8c3911092477502f020f258751e10c7f,990659ac44c4a97ea276d48da6e843d0,b59711b57fe1f9c73cce060154ffd44a,b749321770d103185b4e16cac3d649e,887492dc8664555b9370b0ac508ee28,50ce95409e7fe587cf9f5f168fcf02,e93a836abccb4cf73289b6f2163f70,4e9b6e594805de41b055cd58f71c3fad,835d2d8d709b5e0c2574730fc55434,c8e693954deba3cdc9a4b690f599e238,fbb4fb8567010e47cc21d42361f4405b,7e0dd95c80d0b0433662c98edd8d6db9,c33262bf14714a79c41b4b3822d2d5dc,8c334c7f3727b0705118435b13523d41,9ce0434e018b52fedb0ec063bd531352,19df606d25750a2de57eea57476bb59,650623562e4d4636d8ac8b9c589268c,48decf243dbb452253e0ebbf3c99d3f5,11d3669d14c7ba7d9539c85cf0ac39,9e42db6b5c76b6b2350e0669918b6f,655ac624500649e61d834ccab740c618,08f6879615120ef4ec4a150070d65b6,9c93d110f471d0124606efa5eff966b,bbfd12e058c6f73d049602574cd4e1c0,32cb0116077bf671af6dc81856474c0,89ce60bbdd0e5ed8bd872b5434176b99,30584c1cbe5ef40e994b94ef7cde7792,b32fb3bd5b3ce01d98bb41fb3efb096,e4f0bb6adfe5e60bc0ed3cb95b7b3b5,eefd0f9dffa9a7712859f16b2bdec4e,efde748ae38a1d5b17726c5a09c8ccd,1235a6817ca2ed4db4a76fa8e21c76c7,57fde00de77b36444c131d3ce4056fd,8de783ce896510c22eda25db76bd92f,b77d8f71051331f5dadb2d3af0f8f3fd,3677d6f3dc39a059f987a6bc3453c6,6d83830e6ddfbb951c8bc51fde378e79,b6afd665cbca9a59535c160655674e8,6882d5deb16b4c94ce690e6448af5c1,null,267870ec3d40d84dd317b663b3f,9ddc552533b617091902837065520547,9758cf96387bb6abcbab2d80c6e999,f04cf2a475c71569eb08dd6d6014df,635140c894ef382be7df98e51cf0946,6ffcffce3489bebea33fd065191478e2,ed54ef3b4c8e6399ba611e716b545e92,b65f64fe39991f936770d310734f4867,d0668f0353fb8bd250f674389fdd3d,cf983b5adee29d22c15dfb686838300,e997b17c8f0d5cf5262d385866c87f75,1dd23801eb2d3648732761740e0e96f,9af4484c141b3a999ef2123124c11,43b1d6a9e60d20ce770f1b911fefb8c9,5f51095ea42ebe5e1de6a80898c8bc9,375e6489c389e1b763e56c4d9d705f80,ea353839618004c9813ff2e59212213,1251dceb238f53e9ac6f2e798cbafe9,80ef1d8a8bd60d4f7fd1e3301c93d02,b0143899e17f7dd6a097e47d6205749,2e99ef3b18ecd0c04151e3ddefd65a9c,d969ed18130d1520387c0dd5befcbe,4683cb6eabf57e21ed6c282de353bbf,4ca9bb95d51c2572e82e4473a1c3996f,24037453cddfb05816b34f0464012e8,9213488371b0a3653c1f3005c3f691a8,a78ee11ed3d70f173e706cf9752da,20240f43a0529271a06ef192a7b98d93,61e0c8fed541eedbc46ac08e8d085,05313bc6063dacdb1c54fe297e94863,d476dcbcf59bd9af3b4f7e89f079dc,c981cfe39130ce965eb9bd29b58f44c,8ffd37574675947314234c743a7e7015,98c8f77bc868a8ad4b0295d1d9ab435d,7a004876ce7982cf7465e4f94eebee7,2ca78df870e04899ec7fee562b8e540,9828febf6ac433ccf1322399cce9a,6b8368129412f11b32139ad56dbecb,03ff1e70c5cc4bb0e2752b1659d00c8,cbf7b16b3b7610d9eb7b16235b805de,43893996227d59c0307891cd95a540a,25c1354b89c85f4b0fdbd761ee8f3bb,2e5fbeb9f05d7ed65a7065d2458348b3,c64dd506580586635d661091ef39cc,e7149243057534beed682fc8c060946,88bbbb318344a96535c1f077c8110,0bc9c7ed8a5c861587cb99f715cf2377,b0028f0d5107ddf839f730c8a4bb49ce,c7f38686009299db6fed8b8d165c46,a632df82c5751e34658bd4795b6649c4,d8818b26673dc38054995de5571fa25,a3c05f318cb93d94607d55ac587ff004,c5580b6cddfba8801d8892d16348825,ebf2161b8dca5dcb56e43cb8093526,15f6b2de550a15de1966b7b0b3d3c52,0b905fbc84978e9988262ccc5a7a82,79d76d58e14e8e794f66a114f13642b,971ed3ef7b0b92c3a8db8fe6fc7ee918,2fcd0a070c029c19171d044469bbd98,77dc0c19a07649e8fcfb9f9e99e2f1d,33eb48792577ec555899732391e433cd,29f00a4f565b0718c5c5483ec90e58,b371b6bd7908d55d9dc090cf5ec1f5,cf19052b43d2d57bfa772bd5725bff1,4dd3ddc011d31063da8e4c47076c96a,47f554a0f782e9d210c0752f26cb,b9d9d832f4fe237f5f0b13118bb096fc,bf02c05c214e8441bb786d30785e380f,b7c5dc3c2280f9b401cac8de79664c1,3d1b091f207913b17c58f92bcd7650c,30f0b54cf11e8c7b5a0e4bf6d4d37f2,b2aba8ad8634dff25222917fb4e05,c8e48ae8ed34d332b57deb8f6a25bd6,c0f46d22031225835212cfc9f1385cf5,958dc944885f463c97b83ea238417f8,43ec007196d0f8cad297127f62c4b1,5d9c4b13da77d221e704e88e422ce4d3,dec2f945bedcc1f68f557048610df80,74d69d3bf02c3951861eed47362e72,a0063f6dfeb7a1664ed6ac74476b6412,f9e990383b31fff54f84784282bfb14,01b7c87ff438461dbf75b22de16dd6,60648f83db958c68f8a85295d23586b,456e9b1c7e7cd73e31673135525f782,cfdfd748c1fbc4e3948db1e4bd861db3,4f6dd1ec4b5bcf4f73b33e65e206308,c0fb8c7c68d5698b163e5bba108470b1,18b73bdfcd474765809b1e2530fd1867,3a3716022c3020c9e73c2be2fc7933,bd9b0f12386470f3219f34d19c65d933,20e27033ac45f99fcf8482a4a61f07b,9794e1423d2087a199711ebbf0619c5e,89e419e1edbefb80ff2f82d722d7ddc,1c7b34b62cfd2b5e728f5b6238ddb86,0c851e3d2d6932e7586c52e91b58df,9e8402f066b683530bb5112339f19fb2,935b47eb965b9ab9e2963d5f1c23e8b5,e77b0dbcd6ee832c7e9db35662d909,682d0686ccce2c120cdc450f922c23,076be3a8c6b0e35f2020185bdc41102b,3b8aef4e26d71cfdfb626c74f8ce43c5,b6700062a040de25c131a7b2b29e67fe,ff89649a71f38510e8b33ee366184d4b,a39fac22c9fe61d145fe1d481fbd0551,094e8216537543bdd5548556e6f167,d2e45f8e7f731b257422cf2ee6d91d06,2619d235f85b941d15c8e6d1cb72b38,700bd5483f07ba85d87ff99ec727425,ba5f5a4d26f540d1ff6e3e8426655b2,86b8115280b29e32b4f0f5761baef8,7f822cdae87c40315a90621f6642872b,917bb243f661d9f906586678d3c23529,e7291cd8c768d3cb55280a80eb9b3f,18a6eba9eda916d996e7eb69deb1bb2b,11f003fe6813634ef9b365df4d36dd,b3ab095783aeffd040a1bf9c2dd2fce,0c850dfe8d49f7ef6cb153411461389,74bcbbdf221544096c8cb430d54b1,374f19152765f5702f730c7d7acd01fd,59a784671a281b046edbc13b2d6142,55b45851ee89bf67e24db4e2d79fb,a690a759bbecfdcb2b387bec48e31d62,8c659973752b32dc4d84d3cce5de,5749e23066444387e7274d3837d120,0a0f8131b3fa69c4263fc4adedaff4f,82301b9075b23352bb8e86665f442c,21b1d0c8d241d6325471292bf00ac6e6,76f16c22d41f62828a6600eb7163076,f39c3bef336dff06ce3732cfe78c72,74b89f62bf298b908b50fb168cc15cd,04c33cd59d63242f5e4e8f382e6d5,076de0ab224284f49e94883bc40b8eee,14ca5a1e01b2d2c9c78f762c5f19799,9da79fb65c6c535bbbf305d2cdec701,30b731008f91a3ab71b7c801c9179,d7c1172fb007cbcef81619abd4e87851,55f549b20d5ab4c3c572227b81da5d3b,50dd020e06d4f36f8b6049d550c6992,2996e101928abf65dbf591d1736c3b93,354705312601c0fb425811ef427b80,e088418926ecb5632399c2f5c900acb,41f938be07b8153a4b2f063564a3fb,f230827cf1229197b85228e25fb1,d2f00cc9db64dde9f2a56938fb3de2b3,5339676192502cb1c6e57b3874beee57,4fb55195b08550ca45e0d349453c58,b43c4d0ed551335a0139ef03533cc4e7,4bc983497f59b5962ce669d29b8d0e3,435cde3dbf1239dc7466f77d4ecbc,3c1c1d5d8692784806d48c171c7fd77,997cf0c385ec402cfb5feb1860c2d6f,c68fa1767fc6d0e5f2fd00eb6549ee,b801d6b0b140d043799933f82dff8c2,357136ce9bf8c4c1d8e50132096384d,5d5c107dd1200a1d8f83d36d145190c,20872bb877eb54d67967d1c39c07117,dad0be2f7075800ce6e6dd33ac2266e,9dd1c76e7f94e88ed5e07ed6565f3bae,16344466b48577ed129c09e03bdb145,4d3586c521994e771c07648bbf76b8ef,2fb9e605674381e229ad43244340592,f46085296b01662f4b812bfda5035727,014125881113631f487cc015e1e6069,5fa833a7d4b53d8af41bfa15652e709,994f7f3e7a5b909a46b5045e1cbfafa,a01d9d26b29d91a33c9c16ce2dc0884,47f14c6cfcca591a12846055583c180,b287086298b26f3c162640a3133c2696,41e2ca5dc14574cfde58550c21c1fe68".split(",");	
         byte[] a = H(a[3]);	
         byte[] a = H(a[1]);	
         String a = ALLATORIxDEMO(H(new String(ALLATORIxDEMO(a, a, a), "UTF-8"), "\n\n", "\n\n"), "\n", "");	
	
         try {	
            a = H(ALLATORIxDEMO(a, a[4]), a[2]);	
         } catch (Exception var20) {	
            if (ALLATORIxDEMO(B, a)) {	
               a = H(a, a[2]);	
            }	
         }	
	
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
         String[] var15;	
         int var16 = (var15 = a.substring(2, a.length() - 2).split("","")).length;	
	
         int var17;	
         for(int var24 = var17 = 0; var24 < var16; var24 = var17) {	
            String[] a = var15[var17].split("":"");	
            if ("updateCode".equals(a[0])) {	
               a = a.length == 2 ? a[1] : "";	
            } else {	
               a.put(a[0], a.length == 2 ? a[1] : "");	
            }	
	
            ++var17;	
         }	
	
         Collection var26 = a.values();	
         String[] var10001 = new String[a.values().size()];	
         boolean var10003 = true;	
         List a = Arrays.asList(var26.toArray(var10001));	
         Collections.sort(a, new Comparator() {	
            public int ALLATORIxDEMO(String ax, String axx) {	
               return ax.compareTo(axx);	
            }	
         });	
         String a = "";	
	
         Iterator var28;	
         for(Iterator var27 = var28 = a.iterator(); var27.hasNext(); var27 = var28) {	
            String a = (String)var28.next();	
            a = (new StringBuilder()).insert(0, a).append(a).toString();	
         }	
	
         return !ALLATORIxDEMO((String)a, 88).equals(a) ? new HashMap() : a;	
      } catch (Exception var21) {	
         return new HashMap();	
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
            if (str.equals(ALLATORIxDEMO(a))) {	
               return true;	
            }	
	
            ++var4;	
         }	
      }	
	
      return false;	
   }	
	
   // $FF: synthetic method	
   private static String H(String input) {	
      try {	
         return new String(null.ALLATORIxDEMO(input), "UTF-8");	
      } catch (UnsupportedEncodingException var2) {	
         return "";	
      }	
   }	
	
   // $FF: synthetic method	
   private static String ALLATORIxDEMO(String str) {	
      return str == null ? null : str.replaceAll("^[\s|　| ]*|[\s|　| ]*$", "");	
   }	
	
   // $FF: synthetic method	
   private static byte[] H(String input) {	
      return null.ALLATORIxDEMO(input);	
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
   private static PrivateKey ALLATORIxDEMO(String privateKey) throws Exception {	
      byte[] a = H(privateKey);	
      PKCS8EncodedKeySpec a = new PKCS8EncodedKeySpec(a);	
      return KeyFactory.getInstance("RSA").generatePrivate(a);	
   }	
	
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
   private static String ALLATORIxDEMO(String text, String searchString, String replacement) {	
      return ALLATORIxDEMO(text, searchString, replacement, -1, false);	
   }	
	
   // $FF: synthetic method	
   private static int ALLATORIxDEMO(CharSequence cs, CharSequence searchChar, int start) {	
      return cs.toString().indexOf(searchChar.toString(), start);	
   }	
	
   static {	
      HashSet a = new HashSet();	
	
      try {	
         Sigar a;	
         CpuInfo[] a;	
         String a = (a = (a = com.jeesite.common.l.l.I.B).getCpuInfoList()).length > 0 ? a[0].getModel() : "";	
         String[] a = a.getNetInterfaceList();	
	
         for(int a = 0; a < a.length; ++a) {	
            NetInterfaceConfig a;	
            if (((a = a.getNetInterfaceConfig(a[a])).getFlags() & 8L) == 0L && !"127.0.0.1".equals(a.getAddress()) && !"0.0.0.0".equals(a.getAddress()) && !"00:00:00:00:00:00".equals(a.getHwaddr())) {	
               a.add(ALLATORIxDEMO((String)(new StringBuilder()).insert(0, a).append(a.getDescription()).append(a.getHwaddr()).toString(), 76));	
            }	
         }	
      } catch (Throwable var7) {	
      }	
	
      String[] var10001 = new String[a.size()];	
      boolean var10003 = true;	
      B = (String[])a.toArray(var10001);	
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
	
   public static final Map ALLATORIxDEMO(Map info) {	
      Map a = new HashMap();	
      String a = "0";	
      String a = "社区版";	
	
      try {	
         if (!ALLATORIxDEMO((CharSequence)info.get("message"))) {	
            throw new Exception((String)info.get("message"));	
         }	
	
         int a = false;	
         String[] var5;	
         int var6 = (var5 = B).length;	
	
         int var7;	
         for(int var10000 = var7 = 0; var10000 < var6; var10000 = var7) {	
            if (var5[var7].equals(ALLATORIxDEMO((String)info.get("code")))) {	
               a = true;	
            }	
	
            ++var7;	
         }	
	
         if (!a || ALLATORIxDEMO((CharSequence)ALLATORIxDEMO) || ALLATORIxDEMO((CharSequence)G) || !ALLATORIxDEMO(ALLATORIxDEMO).equals(ALLATORIxDEMO((String)info.get("productName"))) || !ALLATORIxDEMO(G).equals(ALLATORIxDEMO((String)info.get("companyName")))) {	
            throw new Exception((new StringBuilder()).insert(0, "您当前的版本为").append(a).toString());	
         }	
	
         a = (String)info.get("type");	
         a.put("type", a);	
         Map var16;	
         if ("1".equals(a)) {	
            a = "个人版";	
            var16 = info;	
         } else {	
            if ("2".equals(a)) {	
               a = "专业版";	
            }	
	
            var16 = info;	
         }	
	
         String a = (String)var16.get("expireDate");	
         if (!"-1".equals(a)) {	
            long a;	
            Date a;	
            if ((a = ((a = (new SimpleDateFormat("yyyy-MM-dd")).parse(a)).getTime() - System.currentTimeMillis()) / 3600000L / 24L) <= 0L) {	
               throw new Exception((new StringBuilder()).insert(0, "您的").append(a).append("许可，于").append((new SimpleDateFormat("yyyy年MM月dd日")).format(a)).append("已到期").toString());	
            }	
	
            HashMap var17;	
            if (a <= 7L) {	
               var17 = a;	
               a.put("message", (new StringBuilder()).insert(0, "您当前的版本为").append(a).append("，许可即将到期，还剩余最后").append(a).append("天。").toString());	
            } else if (a <= 60L) {	
               a.put("message", (new StringBuilder()).insert(0, "您当前的版本为").append(a).append("，许可即将到期，还剩余").append(a).append("天。").toString());	
               var17 = a;	
            } else {	
               a.put("message", (new StringBuilder()).insert(0, "您当前的版本为").append(a).append("，许可到期时间为：").append((new SimpleDateFormat("yyyy年MM月dd日")).format(a)).append("。").toString());	
               var17 = a;	
            }	
	
            var17.put("title", (new StringBuilder()).insert(0, a).append("（剩余").append(a).append("天）").toString());	
         } else {	
            a.put("message", (new StringBuilder()).insert(0, "您当前的版本为").append(a).append("，非常感谢您对我们产品的认可与支持！").toString());	
            a.put("title", a);	
         }	
      } catch (Exception var13) {	
         a.put("message", (new StringBuilder()).insert(0, var13.getMessage()).append("，官方网站：htp://jeesite.com").toString());	
         a.put("title", a);	
         a.put("type", a);	
      }	
	
      return a;	
   }	
	
   // $FF: synthetic method	
   private static String ALLATORIxDEMO(String contentBase64, String publicKeyBase64) throws Exception {	
      PublicKey a = ALLATORIxDEMO(publicKeyBase64);	
      Cipher a;	
      (a = Cipher.getInstance("RSA")).init(2, a);	
      int a = ((RSAPublicKey)a).getModulus().bitLength() / 8;	
      byte[][] a = ALLATORIxDEMO(ALLATORIxDEMO(H(contentBase64)), a);	
      StringBuffer a = new StringBuffer();	
      byte[][] var8 = a;	
      int var9 = a.length;	
	
      int var10;	
      for(int var10000 = var10 = 0; var10000 < var9; var10000 = var10) {	
         byte[] a = var8[var10];	
         ++var10;	
         a.append(new String(a.doFinal(a)));	
      }	
	
      return H(a.toString());	
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
}	
