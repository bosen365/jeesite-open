package com.jeesite.common.mybatis.mapper.query;	
	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.validator.ValidatorUtils;	
import org.hyperic.jni.ArchLoaderException;	
	
public enum QueryAndor {	
   OR("OR"),	
   OR_BRACKET("OR ("),	
   END_BRACKET(")");	
	
   private final String value;	
   AND_BRACKET("AND ("),	
   AND("AND");	
	
   // $FF: synthetic method	
   private QueryAndor(String var3) {	
      this.value = var3;	
   }	
	
   static {	
      QueryAndor[] var10000 = new QueryAndor[5];	
      boolean var10002 = true;	
      var10000[0] = AND;	
      var10000[1] = AND_BRACKET;	
      var10000[2] = OR;	
      var10000[3] = OR_BRACKET;	
      var10000[4] = END_BRACKET;	
   }	
	
   String value() {	
      return this.value;	
   }	
	
   public static void removeLastBracket(StringBuilder sql) {	
      String a;	
      if (StringUtils.endsWith(a = StringUtils.trim(sql.toString()), AND_BRACKET.value)) {	
         a = a.substring(0, a.length() - AND_BRACKET.value.length());	
      }	
	
      if (StringUtils.endsWith(a, OR_BRACKET.value)) {	
         a = a.substring(0, a.length() - OR_BRACKET.value.length());	
      }	
	
      if (StringUtils.endsWith(a, "(")) {	
         a = a.substring(0, a.length() - "(".length());	
      }	
	
      sql.replace(0, sql.length(), a);	
   }	
	
   public static void addAndor(StringBuilder sql, StringBuilder sqlWhere, QueryAndor andor) {	
      if (sql.length() != 0) {	
         sqlWhere.append(" " + andor.value());	
         if (andor != END_BRACKET) {	
            sqlWhere.append(" ");	
            return;	
         }	
      } else if (andor == AND_BRACKET || andor == OR_BRACKET) {	
         sqlWhere.append("( ");	
      }	
	
   }	
	
   public static boolean isLastBracket(String sql) {	
      String a;	
      return StringUtils.endsWith(a = StringUtils.trim(sql), AND_BRACKET.value) || StringUtils.endsWith(a, OR_BRACKET.value) || StringUtils.endsWith(a, "(");	
   }	
	
   public static boolean isOnlyAndor(String sql) {	
      String a;	
      return StringUtils.equalsIgnoreCase(a = StringUtils.trim(sql), AND.value) || StringUtils.equalsIgnoreCase(a, OR.value);	
   }	
}	
