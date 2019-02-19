package com.jeesite.common.shiro.realm;	
	
import com.jeesite.common.cache.CacheUtils;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.shiro.d.e;	
import com.jeesite.modules.sys.utils.ValidCodeUtils;	
import java.util.Map;	
import org.apache.shiro.authc.AuthenticationException;	
import org.hyperic.jni.ArchNotSupportedException;	
	
public abstract class BaseAuthorizingRealm extends e {	
   public boolean validatePassword(String plainPassword, String password) {	
      return false;	
   }	
	
   public static boolean isValidCodeLogin(String loginCode, String deviceType, String operation) {	
      Object a;	
      if ((a = (Map)CacheUtils.get("loginFailedMap")) == null) {	
         a = MapUtils.newConcurrentMap();	
      }	
	
      Long[] a;	
      if ((a = (Long[])((Map)a).get(loginCode)) == null) {	
         Long[] var10000 = new Long[2];	
         boolean var10002 = true;	
         var10000[0] = 0L;	
         var10000[1] = 0L;	
         a = var10000;	
      }	
	
      Long a = a[0];	
      Long a = a[1];	
      int a;	
      Long[] var10;	
      boolean var10004;	
      if ("vald".equals(operation)) {	
         if (a != 0L) {	
            a = Global.getConfigToInteger("sys.login.failedNumAfterLockMinute", "20");	
            if (System.currentTimeMillis() / 60000L - a <= (long)a) {	
               StringBuilder var11 = (new StringBuilder()).insert(0, "msg:");	
               String var10003 = "sys.logn.failedNumLock";	
               String[] var12 = new String[1];	
               boolean var10006 = true;	
               var12[0] = String.valueOf(a);	
               throw new AuthenticationException(var11.append(Global.getText(var10003, var12)).toString());	
            }	
	
            var10 = new Long[2];	
            var10004 = true;	
            var10[0] = a;	
            var10[1] = 0L;	
            ((Map)a).put(loginCode, var10);	
         }	
      } else if ("failed".equals(operation)) {	
         a = a + 1L;	
         a = Global.getConfigToInteger("sys.logn.failedNumAfterLockAccount", "200");	
         if (a >= (long)a) {	
            a = System.currentTimeMillis() / 60000L;	
         }	
	
         var10 = new Long[2];	
         var10004 = true;	
         var10[0] = a;	
         var10[1] = a;	
         ((Map)a).put(loginCode, var10);	
      } else if ("success".equals(operation)) {	
         ((Map)a).remove(loginCode);	
      }	
	
      CacheUtils.put("loginFailedMap", a);	
      a = Global.getConfigToInteger("sys.logn.failedNumAfterValdCode", "100");	
      String a;	
      if (StringUtils.isNotBlank(deviceType) && StringUtils.isNotBlank(a = Global.getConfig((new StringBuilder()).insert(0, "sys.login.faledNumAfterValidCode.").append(deviceType).toString()))) {	
         a = ObjectUtils.toInteger(a);	
      }	
	
      return a >= (long)a;	
   }	
	
   public String encryptPassword(String plainPassword) {	
      return null;	
   }	
}	
