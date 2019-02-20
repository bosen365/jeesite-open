package com.jeesite.modules.sys.utils;	
	
import com.jeesite.common.cache.CacheUtils;	
import com.jeesite.common.cache.JedisUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.web.http.ServletUtils;	
import com.jeesite.modules.gen.entity.config.GenDict;	
import javax.servlet.http.HttpServletRequest;	
import org.apache.shiro.session.Session;	
	
public class CorpUtils {	
   public static final String DEFAULT_CORP_CODE = "0";	
   private static final String CORP_CACHE = "corpCache";	
   public static final String DEFAULT_CORP_NAME = "JeeSite";	
	
   public static Object getCache(String key, Object defaultValue) {	
      Object a;	
      return (a = getCache(key)) != null ? a : defaultValue;	
   }	
	
   public static String getCurrentCorpName() {	
      String a = "JeeSite";	
      if (Global.isUseCorpModel()) {	
         HttpServletRequest a;	
         Session a;	
         if (StringUtils.isBlank((a = ServletUtils.getRequest()) != null ? (a = (String)a.getAttribute("corpName__")) : (a = "")) && (a = UserUtils.getSubject().getSession(false)) != null) {	
            a = ObjectUtils.toString(a.getAttribute("corpName"));	
         }	
	
         if (StringUtils.isBlank(a)) {	
            a = "JeeSite";	
         }	
	
         if (a != null) {	
            a.setAttribute("corpName__", a);	
         }	
      }	
	
      return ObjectUtils.toStringIgnoreNull(a);	
   }	
	
   public static void removeCache(String key) {	
      CacheUtils.removeByKeyPrefix("corpCache", (new StringBuilder()).insert(0, key).append("__").toString());	
   }	
	
   public static Object getCache(String key) {	
      return CacheUtils.get("corpCache", (new StringBuilder()).insert(0, key).append("__").append(getCurrentCorpCode()).toString());	
   }	
	
   public static void putCache(String key, Object value) {	
      CacheUtils.put("corpCache", (new StringBuilder()).insert(0, key).append("__").append(getCurrentCorpCode()).toString(), value);	
   }	
	
   public static String getCurrentCorpCode() {	
      String a = "0";	
      if (Global.isUseCorpModel() && ObjectUtils.toBoolean(com.jeesite.common.shiro.j.H.ALLATORIxDEMO().get("fnSaas"))) {	
         HttpServletRequest a;	
         Session a;	
         if (StringUtils.isBlank((a = ServletUtils.getRequest()) != null ? (a = (String)a.getAttribute("corpCode__")) : (a = "")) && (a = UserUtils.getSubject().getSession(false)) != null) {	
            a = ObjectUtils.toString(a.getAttribute("corpCode"));	
         }	
	
         if (StringUtils.isBlank(a)) {	
            a = "0";	
         }	
	
         if (a != null) {	
            a.setAttribute("corpCode__", a);	
         }	
      }	
	
      return ObjectUtils.toStringIgnoreNull(a);	
   }	
	
   public static String ALLATORIxDEMO(String s) {	
      int var10000 = 2 << 3;	
      int var10001 = 4 << 4 ^ 2 << 1;	
      int var10002 = (2 ^ 5) << 4 ^ 4 << 1;	
      int var10003 = (s = (String)s).length();	
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
}	
