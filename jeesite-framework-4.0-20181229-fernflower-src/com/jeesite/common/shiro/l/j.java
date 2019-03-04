package com.jeesite.common.shiro.l;	
	
import com.jeesite.common.cache.JedisUtils;	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.collect.SetUtils;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.common.web.http.ServletUtils;	
import com.jeesite.modules.config.CoreConfig;	
import java.util.ArrayList;	
import java.util.Collection;	
import java.util.HashSet;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Set;	
import javax.servlet.http.HttpServletRequest;	
import org.apache.shiro.cache.Cache;	
import org.apache.shiro.cache.CacheException;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
import redis.clients.jedis.Jedis;	
	
public class j implements Cache {	
   private Logger c = LoggerFactory.getLogger(this.getClass());	
   private String ALLATORIxDEMO = null;	
	
   public void clear() throws CacheException {	
      Jedis a = null;	
	
      label38: {	
         try {	
            try {	
               Jedis var10000 = a = JedisUtils.getResource();	
               byte[] var10001 = JedisUtils.getBytesKey(this.ALLATORIxDEMO);	
               byte[][] var10002 = new byte[0][];	
               boolean var10004 = true;	
               var10000.hdel(var10001, var10002);	
               this.c.debug("clear {}", this.ALLATORIxDEMO);	
               break label38;	
            } catch (Exception var6) {	
               this.c.error("clear {}", this.ALLATORIxDEMO, var6);	
            }	
         } catch (Throwable var7) {	
            JedisUtils.returnResource(a);	
            throw var7;	
         }	
	
         JedisUtils.returnResource(a);	
         return;	
      }	
	
      JedisUtils.returnResource(a);	
   }	
	
   public Object remove(Object key) throws CacheException {	
      Object a = null;	
      Jedis a = null;	
	
      label39: {	
         try {	
            boolean var10004;	
            try {	
               Jedis var10 = a = JedisUtils.getResource();	
               a = JedisUtils.toObject(var10.hget(JedisUtils.getBytesKey(this.ALLATORIxDEMO), JedisUtils.getBytesKey(key)));	
               byte[] var11 = JedisUtils.getBytesKey(this.ALLATORIxDEMO);	
               byte[][] var12 = new byte[1][];	
               var10004 = true;	
               var12[0] = JedisUtils.getBytesKey(key);	
               var10.hdel(var11, var12);	
               this.c.debug("remove {} {}", this.ALLATORIxDEMO, key);	
               break label39;	
            } catch (Exception var8) {	
               Logger var10000 = this.c;	
               String var10001 = "remove {} {}";	
               Object[] var10002 = new Object[3];	
               var10004 = true;	
               var10002[0] = this.ALLATORIxDEMO;	
               var10002[1] = key;	
               var10002[2] = var8;	
               var10000.warn(var10001, var10002);	
            }	
         } catch (Throwable var9) {	
            JedisUtils.returnResource(a);	
            throw var9;	
         }	
	
         JedisUtils.returnResource(a);	
         return a;	
      }	
	
      JedisUtils.returnResource(a);	
      return a;	
   }	
	
   public int size() {	
      int a = 0;	
      Jedis a = null;	
	
      int var3;	
      label39: {	
         try {	
            try {	
               int a = (a = JedisUtils.getResource()).hlen(JedisUtils.getBytesKey(this.ALLATORIxDEMO)).intValue();	
               this.c.debug("size {} {} ", this.ALLATORIxDEMO, a);	
               var3 = a;	
               break label39;	
            } catch (Exception var7) {	
               this.c.error("clear {}", this.ALLATORIxDEMO, var7);	
            }	
         } catch (Throwable var8) {	
            JedisUtils.returnResource(a);	
            throw var8;	
         }	
	
         JedisUtils.returnResource(a);	
         return a;	
      }	
	
      JedisUtils.returnResource(a);	
      return var3;	
   }	
	
   public Object get(Object key) throws CacheException {	
      if (key == null) {	
         return null;	
      } else {	
         Object a = null;	
         HttpServletRequest a;	
         if ((a = ServletUtils.getRequest()) != null && (a = a.getAttribute(this.ALLATORIxDEMO + key)) != null) {	
            return a;	
         } else {	
            Object a = null;	
            Jedis a = null;	
	
            HttpServletRequest var12;	
            label98: {	
               label97: {	
                  try {	
                     Logger var10000;	
                     String var10001;	
                     Object[] var10002;	
                     boolean var10004;	
                     try {	
                        a = JedisUtils.toObject((a = JedisUtils.getResource()).hget(JedisUtils.getBytesKey(this.ALLATORIxDEMO), JedisUtils.getBytesKey(key)));	
                        var10000 = this.c;	
                        var10001 = "get {} {} {}";	
                        var10002 = new Object[3];	
                        var10004 = true;	
                        var10002[0] = this.ALLATORIxDEMO;	
                        var10002[1] = key;	
                        var10002[2] = a != null ? a.getRequestURI() : "";	
                        var10000.debug(var10001, var10002);	
                        break label97;	
                     } catch (Exception var10) {	
                        var10000 = this.c;	
                        var10001 = "get {} {} {}";	
                        var10002 = new Object[4];	
                        var10004 = true;	
                        var10002[0] = this.ALLATORIxDEMO;	
                        var10002[1] = key;	
                     }	
	
                     var10002[2] = a != null ? a.getRequestURI() : "";	
                     var10002[3] = var10;	
                     var10000.error(var10001, var10002);	
                  } catch (Throwable var11) {	
                     JedisUtils.returnResource(a);	
                     throw var11;	
                  }	
	
                  var12 = a;	
                  JedisUtils.returnResource(a);	
                  break label98;	
               }	
	
               var12 = a;	
               JedisUtils.returnResource(a);	
            }	
	
            if (var12 != null && a != null) {	
               a.setAttribute((new StringBuilder()).insert(0, this.ALLATORIxDEMO).append(key).toString(), a);	
            }	
	
            return a;	
         }	
      }	
   }	
	
   public Set keys() {	
      Set a = SetUtils.newHashSet();	
      Jedis a = null;	
	
      HashSet var12;	
      label63: {	
         try {	
            try {	
               Iterator var4 = (a = JedisUtils.getResource()).hkeys(JedisUtils.getBytesKey(this.ALLATORIxDEMO)).iterator();	
	
               while(var4.hasNext()) {	
                  Object a;	
                  if ((a = JedisUtils.getObjectKey((byte[])var4.next())) != null) {	
                     a.add(a);	
                  }	
               }	
	
               this.c.debug("keys {} {} ", this.ALLATORIxDEMO, a);	
               var12 = a;	
               break label63;	
            } catch (Exception var10) {	
               this.c.error("keys {}", this.ALLATORIxDEMO, var10);	
            }	
         } catch (Throwable var11) {	
            JedisUtils.returnResource(a);	
            throw var11;	
         }	
	
         JedisUtils.returnResource(a);	
         return a;	
      }	
	
      JedisUtils.returnResource(a);	
      return var12;	
   }	
	
   public j(String var1) {	
      this.ALLATORIxDEMO = var1;	
   }	
	
   public Collection values() {	
      List a = ListUtils.newArrayList();	
      Jedis a = null;	
	
      ArrayList var12;	
      label63: {	
         try {	
            try {	
               Iterator var4 = (a = JedisUtils.getResource()).hvals(JedisUtils.getBytesKey(this.ALLATORIxDEMO)).iterator();	
	
               while(var4.hasNext()) {	
                  Object a;	
                  if ((a = JedisUtils.toObject((byte[])var4.next())) != null) {	
                     a.add(a);	
                  }	
               }	
	
               this.c.debug("values {} {} ", this.ALLATORIxDEMO, a);	
               var12 = a;	
               break label63;	
            } catch (Exception var10) {	
               this.c.error("values {}", this.ALLATORIxDEMO, var10);	
            }	
         } catch (Throwable var11) {	
            JedisUtils.returnResource(a);	
            throw var11;	
         }	
	
         JedisUtils.returnResource(a);	
         return a;	
      }	
	
      JedisUtils.returnResource(a);	
      return var12;	
   }	
	
   public Object put(Object key, Object value) throws CacheException {	
      if (key == null) {	
         return null;	
      } else {	
         Jedis a = null;	
	
         label45: {	
            try {	
               Logger var10000;	
               String var10001;	
               Object[] var10002;	
               boolean var10004;	
               try {	
                  (a = JedisUtils.getResource()).hset(JedisUtils.getBytesKey(this.ALLATORIxDEMO), JedisUtils.getBytesKey(key), JedisUtils.toBytes(value));	
                  var10000 = this.c;	
                  var10001 = "put {} {} = {}";	
                  var10002 = new Object[3];	
                  var10004 = true;	
                  var10002[0] = this.ALLATORIxDEMO;	
                  var10002[1] = key;	
                  var10002[2] = value;	
                  var10000.debug(var10001, var10002);	
                  break label45;	
               } catch (Exception var8) {	
                  var10000 = this.c;	
                  var10001 = "put {} {}";	
                  var10002 = new Object[3];	
                  var10004 = true;	
                  var10002[0] = this.ALLATORIxDEMO;	
                  var10002[1] = key;	
                  var10002[2] = var8;	
                  var10000.error(var10001, var10002);	
               }	
            } catch (Throwable var9) {	
               JedisUtils.returnResource(a);	
               throw var9;	
            }	
	
            JedisUtils.returnResource(a);	
            return value;	
         }	
	
         JedisUtils.returnResource(a);	
         return value;	
      }	
   }	
}	
