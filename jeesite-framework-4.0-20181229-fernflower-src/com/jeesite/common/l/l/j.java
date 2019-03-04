package com.jeesite.common.l.l;	
	
public enum J {	
   c;	
	
   static {	
      J[] var10000 = new J[1];	
      boolean var10002 = true;	
      var10000[0] = c;	
   }	
}	
eyFactory;	
import java.security.PrivateKey;	
import java.security.interfaces.RSAPrivateKey;	
import java.security.spec.PKCS8EncodedKeySpec;	
import javax.crypto.Cipher;	
	
public final class j {	
   public static String ALLATORIxDEMO(String contentBase64, String privateKeyBase64) throws Exception {	
      PrivateKey a = ALLATORIxDEMO(privateKeyBase64);	
      Cipher a;	
      (a = Cipher.getInstance(ConfigUtils.ALLATORIxDEMO ("5]&"))).init(2, a);	
      int a = ((RSAPrivateKey)a).getModulus().bitLength() / 8;	
      byte[][] a = ALLATORIxDEMO(h.H(h.ALLATORIxDEMO(contentBase64)), a);	
      StringBuffer a = new StringBuffer();	
      byte[][] var8 = a;	
      int var9 = a.length;	
	
      int var10;	
      for(int var10000 = var10 = 0; var10000 < var9; var10000 = var10) {	
         byte[] a = var8[var10];	
         ++var10;	
         a.append(new String(a.doFinal(a)));	
      }	
	
      return h.ALLATORIxDEMO(a.toString());	
   }	
	
   public static String ALLATORIxDEMO(String s) {	
      int var10000 = (3 ^ 5) << 3 ^ 3;	
      int var10001 = 4 << 4 ^ (3 ^ 5) << 1;	
      int var10002 = 5 << 3 ^ 3 ^ 5;	
      int var10003 = s.length();	
      char[] var10004 = new char[var10003];	
      boolean var10006 = true;	
      int var5 = var10003 - 1;	
      var10003 = var10002;	
      int var3;	
      var10002 = var3 = var5;	
      char[] var1 = var10004;	
      int var4 = var10003;	
      var10000 = var10002;	
	
      for(int var2 = var10001; var10000 >= 0; var10000 = var3) {	
         var10001 = var3;	
         char var6 = s.charAt(var3);	
         --var3;	
         var1[var10001] = (char)(var6 ^ var2);	
         if (var3 < 0) {	
            break;	
         }	
	
         var10002 = var3--;	
         var1[var10002] = (char)(s.charAt(var10002) ^ var4);	
      }	
	
      return new String(var1);	
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
   private static PrivateKey ALLATORIxDEMO(String privateKey) throws Exception {	
      byte[] a = h.ALLATORIxDEMO(privateKey);	
      PKCS8EncodedKeySpec a = new PKCS8EncodedKeySpec(a);	
      return KeyFactory.getInstance(FileUploadUtils.ALLATORIxDEMO ("qwb")).generatePrivate(a);	
   }	
}	
