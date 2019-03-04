package com.jeesite.common.mybatis.mapper.query;	
	
import com.jeesite.common.lang.StringUtils;	
	
public enum QueryAndor {	
   OR_BRACKET(QueryWhere.ALLATORIxDEMO ("\u0004pk\n")),	
   AND(QueryWhere.ALLATORIxDEMO ("c\u0005f"));	
	
   private final String value;	
   AND_BRACKET(QueryWhere.ALLATORIxDEMO ("c\u0005fk\n")),	
   OR(QueryWhere.ALLATORIxDEMO ("\u0004p")),	
   END_BRACKET(QueryWhere.ALLATORIxDEMO ("\u000b"));	
	
   public static boolean isLastBracket(String sql) {	
      String a;	
      return StringUtils.endsWithIgnoreCase(a = StringUtils.trim(sql), AND_BRACKET.value) || StringUtils.endsWithIgnoreCase(a, OR_BRACKET.value);	
   }	
	
   public static boolean isOnlyAndor(String sql) {	
      String a;	
      return StringUtils.equalsIgnoreCase(a = StringUtils.trim(sql), AND.value) || StringUtils.equalsIgnoreCase(a, OR.value);	
   }	
	
   String value() {	
      return this.value;	
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
	
   // $FF: synthetic method	
   private QueryAndor(String var3) {	
      this.value = var3;	
   }	
	
   public static void removeLastBracket(StringBuilder sql) {	
      String a;	
      if (StringUtils.endsWithIgnoreCase(a = StringUtils.trim(sql.toString()), AND_BRACKET.value)) {	
         sql.replace(a.length() - AND_BRACKET.value.length(), a.length(), "");	
      }	
	
      if (StringUtils.endsWithIgnoreCase(a, OR_BRACKET.value)) {	
         sql.replace(a.length() - OR_BRACKET.value.length(), a.length(), "");	
      }	
	
   }	
}	
