package com.jeesite.modules.msg.utils;	
	
import com.jeesite.common.cache.CacheUtils;	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.modules.msg.entity.MsgPush;	
import java.util.List;	
	
public class MsgPcPoolUtils {	
   private static final String MSG_PC_POOL_CACHE = "msgPcPoolCache";	
	
   public static void removeCache(String key) {	
      CacheUtils.remove("msgPcPoolCache", key);	
   }	
	
   public static List getPool(String userCode) {	
      Object a;	
      if ((a = (List)getCache(userCode)) == null) {	
         a = ListUtils.newArrayList();	
      }	
	
      return (List)a;	
   }	
	
   public static Object getCache(String key) {	
      return CacheUtils.get("msgPcPoolCache", key);	
   }	
	
   public static Object getCache(String key, Object defaultValue) {	
      Object a;	
      return (a = getCache(key)) != null ? a : defaultValue;	
   }	
	
   public static void putPool(String userCode, List msgPush) {	
      putCache(userCode, msgPush);	
   }	
	
   public static void putCache(String key, Object value) {	
      CacheUtils.put("msgPcPoolCache", key, value);	
   }	
	
   public static void putPool(String userCode, MsgPush msgPush) {	
      Object a;	
      if ((a = getPool(userCode)) == null) {	
         a = ListUtils.newArrayList();	
      }	
	
      ((List)a).add(msgPush);	
      putCache(userCode, a);	
   }	
}	
