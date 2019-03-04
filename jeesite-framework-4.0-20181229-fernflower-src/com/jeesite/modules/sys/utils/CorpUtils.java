package com.jeesite.modules.sys.utils;	
	
import com.jeesite.common.cache.CacheUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.l.d.j;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.shiro.realm.LoginInfo;	
import com.jeesite.common.web.http.ServletUtils;	
import javax.servlet.http.HttpServletRequest;	
	
public class CorpUtils {	
   public static final String DEFAULT_CORP_CODE = "0";	
   public static final String DEFAULT_CORP_NAME = "JeeSite";	
   private static final String CORP_CACHE = "corpCache";	
	
   public static String getCurrentCorpName() {	
      String a = "JeeSite";	
      if (Global.isUseCorpModel()) {	
         HttpServletRequest a;	
         if (StringUtils.isBlank((a = ServletUtils.getRequest()) != null ? (a = (String)a.getAttribute("corpName__")) : (a = ""))) {	
            a = UserUtils.getUser().getCorpName_();	
         }	
	
         if (StringUtils.isBlank(a)) {	
            a = "JeeSite";	
         }	
	
         if (a != null) {	
            a.setAttribute(ValidCodeUtils.ALLATORIxDEMO ("\ng\u001bx'i\u0004m6W"), a);	
         }	
      }	
	
      return ObjectUtils.toStringIgnoreNull(a);	
   }	
	
   public static void putCache(String key, Object value) {	
      CacheUtils.put("corpCache", (new StringBuilder()).insert(0, key).append("__").append(getCurrentCorpCode()).toString(), value);	
   }	
	
   public static String getCurrentCorpCode() {	
      String a = "0";	
      if (Global.isUseCorpModel() && ObjectUtils.toBoolean(j.ALLATORIxDEMO().get(ValidCodeUtils.ALLATORIxDEMO ("\u000ff:i\b{")))) {	
         HttpServletRequest a;	
         if (StringUtils.isBlank((a = ServletUtils.getRequest()) != null ? (a = (String)a.getAttribute("corpCode__")) : (a = ""))) {	
            a = UserUtils.getUser().getCorpCode_();	
         }	
	
         if (StringUtils.isBlank(a)) {	
            a = "0";	
         }	
	
         if (a != null) {	
            a.setAttribute(ValidCodeUtils.ALLATORIxDEMO ("\ng\u001bx*g\rm6W"), a);	
         }	
      }	
	
      return ObjectUtils.toStringIgnoreNull(a);	
   }	
	
   public static Object getCache(String key) {	
      return CacheUtils.get("corpCache", (new StringBuilder()).insert(0, key).append("__").append(getCurrentCorpCode()).toString());	
   }	
	
   public static void removeCache(String key) {	
      CacheUtils.removeByKeyPrefix("corpCache", (new StringBuilder()).insert(0, key).append(ValidCodeUtils.ALLATORIxDEMO ("6W")).toString());	
   }	
	
   public static Object getCache(String key, Object defaultValue) {	
      Object a;	
      return (a = getCache(key)) != null ? a : defaultValue;	
   }	
}	
