package com.jeesite.common.l.l;	
	
import com.jeesite.common.lang.ExceptionUtils;	
import com.jeesite.common.mybatis.mapper.query.QueryOrder;	
import java.io.UnsupportedEncodingException;	
import java.security.GeneralSecurityException;	
import java.security.MessageDigest;	
import org.apache.commons.codec.DecoderException;	
import org.apache.commons.codec.binary.Base64;	
import org.apache.commons.codec.binary.Hex;	
	
public final class h {	
   public static String ALLATORIxDEMO(String input) {	
      try {	
         return new String(Base64.decodeBase64(input.getBytes()), "UTF-8");	
      } catch (UnsupportedEncodingException var2) {	
         return "";	
      }	
   }	
	
   public static byte[] H(String input) {	
      try {	
         return Hex.decodeHex(input.toCharArray());	
      } catch (DecoderException var2) {	
         throw ExceptionUtils.unchecked(var2);	
      }	
   }	
	
   public static final String ALLATORIxDEMO(String input, int iterations) {	
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
      } catch (GeneralSecurityException var5) {	
         throw ExceptionUtils.unchecked(var5);	
      } catch (UnsupportedEncodingException var6) {	
         var6.printStackTrace();	
         return "";	
      }	
   }	
	
   public static byte[] ALLATORIxDEMO(String input) {	
      return Base64.decodeBase64(input.getBytes());	
   }	
	
   public static String ALLATORIxDEMO(byte[] input) {	
      return new String(Hex.encodeHex(input));	
   }	
}	
