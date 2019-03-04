package com.jeesite.common.l;	
	
import com.jeesite.common.l.i.D;	
import com.jeesite.modules.sys.utils.UserUtils;	
import java.text.SimpleDateFormat;	
import java.util.Date;	
import java.util.regex.Matcher;	
import java.util.regex.Pattern;	
import org.apache.commons.lang3.StringUtils;	
	
public class l {	
   private static final String C = "ss";	
   private static final String i = "yy";	
   private static final String L = "mm";	
   private static final String D = "time";	
   private static final String g = "userid";	
   private static final String I = "hh";	
   private static final String E = "rand";	
   private static final String B = "yyyy";	
   private static Date G = null;	
   private static Pattern d = Pattern.compile("\{([^\}]+)\}", 2);	
   private static final String c = "ii";	
   private static final String ALLATORIxDEMO = "dd";	
	
   // $FF: synthetic method	
   private static String K(String pattern) {	
      String a;	
      return StringUtils.isNotBlank(a = UserUtils.getUser().getId()) ? a : "0";	
   }	
	
   // $FF: synthetic method	
   private static String C() {	
      return (new SimpleDateFormat("HH")).format(G);	
   }	
	
   // $FF: synthetic method	
   private static String c() {	
      return System.currentTimeMillis() + "";	
   }	
	
   // $FF: synthetic method	
   private static String m() {	
      return (new SimpleDateFormat("MM")).format(G);	
   }	
	
   // $FF: synthetic method	
   private static String K() {	
      return (new SimpleDateFormat("yy")).format(G);	
   }	
	
   // $FF: synthetic method	
   private static String F() {	
      return (new SimpleDateFormat("dd")).format(G);	
   }	
	
   public static String F(String input) {	
      return input.replace("\", "/");	
   }	
	
   // $FF: synthetic method	
   private static String e() {	
      return (new SimpleDateFormat("mm")).format(G);	
   }	
	
   public static String ALLATORIxDEMO(String input, String filename) {	
      Matcher a = d.matcher(input);	
      String a = null;	
      G = new Date();	
      StringBuffer a = new StringBuffer();	
      Matcher var10000 = a;	
	
      while(var10000.find()) {	
         if ((a = a.group(1)).indexOf("filename") != -1) {	
            filename = filename.replace("$", "\$").replaceAll("[\/:*?"<>|]", "");	
            var10000 = a;	
            a.appendReplacement(a, filename);	
         } else {	
            var10000 = a;	
            a.appendReplacement(a, ALLATORIxDEMO(a));	
         }	
      }	
	
      a.appendTail(a);	
      return a.toString();	
   }	
	
   // $FF: synthetic method	
   private static String e(String pattern) {	
      int a = false;	
      int a = Integer.parseInt(pattern.split(":")[1].trim());	
      return (Math.random() + "").replace(".", "").substring(0, a);	
   }	
	
   // $FF: synthetic method	
   private static String H() {	
      return (new SimpleDateFormat("ss")).format(G);	
   }	
	
   // $FF: synthetic method	
   private static String ALLATORIxDEMO() {	
      return (new SimpleDateFormat("yyyy")).format(G);	
   }	
	
   public static String H(String input) {	
      Matcher a = d.matcher(input);	
      G = new Date();	
      StringBuffer a = new StringBuffer();	
      Matcher var10000 = a;	
	
      while(var10000.find()) {	
         var10000 = a;	
         a.appendReplacement(a, ALLATORIxDEMO(a.group(1)));	
      }	
	
      a.appendTail(a);	
      return a.toString();	
   }	
	
   // $FF: synthetic method	
   private static String ALLATORIxDEMO(String pattern) {	
      if ((pattern = pattern.toLowerCase()).indexOf("time") != -1) {	
         return c();	
      } else if (pattern.indexOf("yyyy") != -1) {	
         return ALLATORIxDEMO();	
      } else if (pattern.indexOf("yy") != -1) {	
         return K();	
      } else if (pattern.indexOf("mm") != -1) {	
         return m();	
      } else if (pattern.indexOf("dd") != -1) {	
         return F();	
      } else if (pattern.indexOf("hh") != -1) {	
         return C();	
      } else if (pattern.indexOf("ii") != -1) {	
         return e();	
      } else if (pattern.indexOf("ss") != -1) {	
         return H();	
      } else if (pattern.indexOf("rand") != -1) {	
         return e(pattern);	
      } else {	
         return pattern.indexOf("userid") != -1 ? K(pattern) : pattern;	
      }	
   }	
}	
