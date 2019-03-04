package com.jeesite.common.cache;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.collect.SetUtils;	
import com.jeesite.common.l.i.g;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.utils.SpringUtils;	
import com.jeesite.modules.sys.web.ValidCodeController;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Map;	
import java.util.Set;	
import java.util.Map.Entry;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
import redis.clients.jedis.Jedis;	
import redis.clients.jedis.JedisPool;	
import redis.clients.jedis.exceptions.JedisException;	
	
public class JedisUtils {	
   private static Logger logger = LoggerFactory.getLogger(JedisUtils.class);	
   private static JedisPool jedisPool = (JedisPool)SpringUtils.getBean(JedisPool.class);	
	
   public static String mapPut(String key, Map value) {	
      String a = null;	
      Jedis a = null;	
	
      label39: {	
         try {	
            try {	
               a = (a = getResource()).hmset(key, value);	
               logger.debug("mapPut {} = {}", key, value);	
               break label39;	
            } catch (Exception var8) {	
               Logger var10000 = logger;	
               String var10001 = "mpPut {} = {}";	
               Object[] var10002 = new Object[3];	
               boolean var10004 = true;	
               var10002[0] = key;	
               var10002[1] = value;	
               var10002[2] = var8;	
               var10000.warn(var10001, var10002);	
            }	
         } catch (Throwable var9) {	
            returnResource(a);	
            throw var9;	
         }	
	
         returnResource(a);	
         return a;	
      }	
	
      returnResource(a);	
      return a;	
   }	
	
   public static long setObjectList(String key, List value, int cacheSeconds) {	
      long a = 0L;	
      Jedis a = null;	
	
      label78: {	
         try {	
            try {	
               if ((a = getResource()).exists(getBytesKey(key))) {	
                  a.del(key);	
               }	
	
               List a = ListUtils.newArrayList();	
               Iterator var7;	
               Iterator var14 = var7 = value.iterator();	
	
               while(var14.hasNext()) {	
                  var14 = var7;	
                  Object a = var7.next();	
                  a.add(toBytes(a));	
               }	
	
               a = a.rpush(getBytesKey(key), (byte[][])((byte[][])a.toArray()));	
               if (cacheSeconds != 0) {	
                  a.expire(key, cacheSeconds);	
               }	
	
               logger.debug("setObjectList {} = {}", key, value);	
               break label78;	
            } catch (Exception var12) {	
               Logger var10000 = logger;	
               String var10001 = "setObjectList {} = {}";	
               Object[] var10002 = new Object[3];	
               boolean var10004 = true;	
               var10002[0] = key;	
               var10002[1] = value;	
               var10002[2] = var12;	
               var10000.warn(var10001, var10002);	
            }	
         } catch (Throwable var13) {	
            returnResource(a);	
            throw var13;	
         }	
	
         returnResource(a);	
         return a;	
      }	
	
      returnResource(a);	
      return a;	
   }	
	
   public static Object getObjectKey(byte[] key) {	
      try {	
         return ObjectUtils.toString(key);	
      } catch (UnsupportedOperationException var4) {	
         try {	
            return toObject(key);	
         } catch (UnsupportedOperationException var3) {	
            var3.printStackTrace();	
            return null;	
         }	
      }	
   }	
	
   public static Set getSet(String key) {	
      Set a = null;	
      Jedis a = null;	
	
      label47: {	
         try {	
            try {	
               if ((a = getResource()).exists(key)) {	
                  a = a.smembers(key);	
                  logger.debug("getSet {} = {}", key, a);	
               }	
               break label47;	
            } catch (Exception var7) {	
               Logger var10000 = logger;	
               String var10001 = "getSet {} = {}";	
               Object[] var10002 = new Object[3];	
               boolean var10004 = true;	
               var10002[0] = key;	
               var10002[1] = a;	
               var10002[2] = var7;	
               var10000.warn(var10001, var10002);	
            }	
         } catch (Throwable var8) {	
            returnResource(a);	
            throw var8;	
         }	
	
         returnResource(a);	
         return a;	
      }	
	
      returnResource(a);	
      return a;	
   }	
	
   public static long mapRemove(String key, String mapKey) {	
      long a = 0L;	
      Jedis a = null;	
	
      label39: {	
         try {	
            boolean var10004;	
            try {	
               Jedis var11 = a = getResource();	
               String[] var12 = new String[1];	
               var10004 = true;	
               var12[0] = mapKey;	
               a = var11.hdel(key, var12);	
               logger.debug("mapRemove {}  {}", key, mapKey);	
               break label39;	
            } catch (Exception var9) {	
               Logger var10000 = logger;	
               String var10001 = "mpRemove {}  {}";	
               Object[] var10002 = new Object[3];	
               var10004 = true;	
               var10002[0] = key;	
               var10002[1] = mapKey;	
               var10002[2] = var9;	
               var10000.warn(var10001, var10002);	
            }	
         } catch (Throwable var10) {	
            returnResource(a);	
            throw var10;	
         }	
	
         returnResource(a);	
         return a;	
      }	
	
      returnResource(a);	
      return a;	
   }	
	
   public static List getList(String key) {	
      List a = null;	
      Jedis a = null;	
	
      label47: {	
         try {	
            try {	
               if ((a = getResource()).exists(key)) {	
                  a = a.lrange(key, 0L, -1L);	
                  logger.debug("getList {} = {}", key, a);	
               }	
               break label47;	
            } catch (Exception var7) {	
               Logger var10000 = logger;	
               String var10001 = "getList {} = {}";	
               Object[] var10002 = new Object[3];	
               boolean var10004 = true;	
               var10002[0] = key;	
               var10002[1] = a;	
               var10002[2] = var7;	
               var10000.warn(var10001, var10002);	
            }	
         } catch (Throwable var8) {	
            returnResource(a);	
            throw var8;	
         }	
	
         returnResource(a);	
         return a;	
      }	
	
      returnResource(a);	
      return a;	
   }	
	
   public static Map getMap(String key) {	
      Map a = null;	
      Jedis a = null;	
	
      label47: {	
         try {	
            try {	
               if ((a = getResource()).exists(key)) {	
                  a = a.hgetAll(key);	
                  logger.debug("getMap {} = {}", key, a);	
               }	
               break label47;	
            } catch (Exception var7) {	
               Logger var10000 = logger;	
               String var10001 = "getMap {} = {}";	
               Object[] var10002 = new Object[3];	
               boolean var10004 = true;	
               var10002[0] = key;	
               var10002[1] = a;	
               var10002[2] = var7;	
               var10000.warn(var10001, var10002);	
            }	
         } catch (Throwable var8) {	
            returnResource(a);	
            throw var8;	
         }	
	
         returnResource(a);	
         return a;	
      }	
	
      returnResource(a);	
      return a;	
   }	
	
   public static long setObjectSet(String key, Set value, int cacheSeconds) {	
      long a = 0L;	
      Jedis a = null;	
	
      label78: {	
         try {	
            try {	
               if ((a = getResource()).exists(getBytesKey(key))) {	
                  a.del(key);	
               }	
	
               Set a = SetUtils.newHashSet();	
               Iterator var7;	
               Iterator var14 = var7 = value.iterator();	
	
               while(var14.hasNext()) {	
                  var14 = var7;	
                  Object a = var7.next();	
                  a.add(toBytes(a));	
               }	
	
               a = a.sadd(getBytesKey(key), (byte[][])((byte[][])a.toArray()));	
               if (cacheSeconds != 0) {	
                  a.expire(key, cacheSeconds);	
               }	
	
               logger.debug("setObjectSet {} = {}", key, value);	
               break label78;	
            } catch (Exception var12) {	
               Logger var10000 = logger;	
               String var10001 = "setObjectSet {} = {}";	
               Object[] var10002 = new Object[3];	
               boolean var10004 = true;	
               var10002[0] = key;	
               var10002[1] = value;	
               var10002[2] = var12;	
               var10000.warn(var10001, var10002);	
            }	
         } catch (Throwable var13) {	
            returnResource(a);	
            throw var13;	
         }	
	
         returnResource(a);	
         return a;	
      }	
	
      returnResource(a);	
      return a;	
   }	
	
   public static long listAdd(String key, String... value) {	
      long a = 0L;	
      Jedis a = null;	
	
      label39: {	
         try {	
            try {	
               a = (a = getResource()).rpush(key, value);	
               logger.debug("listAdd {} = {}", key, value);	
               break label39;	
            } catch (Exception var9) {	
               Logger var10000 = logger;	
               String var10001 = "listAdd {} = {}";	
               Object[] var10002 = new Object[3];	
               boolean var10004 = true;	
               var10002[0] = key;	
               var10002[1] = value;	
               var10002[2] = var9;	
               var10000.warn(var10001, var10002);	
            }	
         } catch (Throwable var10) {	
            returnResource(a);	
            throw var10;	
         }	
	
         returnResource(a);	
         return a;	
      }	
	
      returnResource(a);	
      return a;	
   }	
	
   public static List getObjectList(String key) {	
      List a = null;	
      Jedis a = null;	
	
      label68: {	
         try {	
            try {	
               if ((a = getResource()).exists(getBytesKey(key))) {	
                  List a = a.lrange(getBytesKey(key), 0L, -1L);	
                  a = ListUtils.newArrayList();	
                  Iterator var4;	
                  Iterator var11 = var4 = a.iterator();	
	
                  while(var11.hasNext()) {	
                     byte[] a = (byte[])var4.next();	
                     var11 = var4;	
                     a.add(toObject(a));	
                  }	
	
                  logger.debug("getObjectList {} = {}", key, a);	
               }	
               break label68;	
            } catch (Exception var9) {	
               Logger var10000 = logger;	
               String var10001 = "getObjectList {} = {}";	
               Object[] var10002 = new Object[3];	
               boolean var10004 = true;	
               var10002[0] = key;	
               var10002[1] = a;	
               var10002[2] = var9;	
               var10000.warn(var10001, var10002);	
            }	
         } catch (Throwable var10) {	
            returnResource(a);	
            throw var10;	
         }	
	
         returnResource(a);	
         return a;	
      }	
	
      returnResource(a);	
      return a;	
   }	
	
   public static long setSetObjectAdd(String key, Object... value) {	
      long a = 0L;	
      Jedis a = null;	
	
      label57: {	
         try {	
            try {	
               a = getResource();	
               Set a = SetUtils.newHashSet();	
               Object[] var6 = value;	
               int var7 = value.length;	
	
               int var8;	
               for(int var15 = var8 = 0; var15 < var7; var15 = var8) {	
                  Object a = var6[var8];	
                  ++var8;	
                  a.add(toBytes(a));	
               }	
	
               a = a.rpush(getBytesKey(key), (byte[][])((byte[][])a.toArray()));	
               logger.debug("setSetObjectAdd {} = {}", key, value);	
               break label57;	
            } catch (Exception var13) {	
               Logger var10000 = logger;	
               String var10001 = "setSetObjectAdd {} = {}";	
               Object[] var10002 = new Object[3];	
               boolean var10004 = true;	
               var10002[0] = key;	
               var10002[1] = value;	
               var10002[2] = var13;	
               var10000.warn(var10001, var10002);	
            }	
         } catch (Throwable var14) {	
            returnResource(a);	
            throw var14;	
         }	
	
         returnResource(a);	
         return a;	
      }	
	
      returnResource(a);	
      return a;	
   }	
	
   public static long setSet(String key, Set value, int cacheSeconds) {	
      long a = 0L;	
      Jedis a = null;	
	
      label58: {	
         try {	
            try {	
               if ((a = getResource()).exists(key)) {	
                  a.del(key);	
               }	
	
               a = a.sadd(key, (String[])((String[])value.toArray()));	
               if (cacheSeconds != 0) {	
                  a.expire(key, cacheSeconds);	
               }	
	
               logger.debug("setSet {} = {}", key, value);	
               break label58;	
            } catch (Exception var10) {	
               Logger var10000 = logger;	
               String var10001 = "setSet {} = {}";	
               Object[] var10002 = new Object[3];	
               boolean var10004 = true;	
               var10002[0] = key;	
               var10002[1] = value;	
               var10002[2] = var10;	
               var10000.warn(var10001, var10002);	
            }	
         } catch (Throwable var11) {	
            returnResource(a);	
            throw var11;	
         }	
	
         returnResource(a);	
         return a;	
      }	
	
      returnResource(a);	
      return a;	
   }	
	
   public static String setMap(String key, Map value, int cacheSeconds) {	
      String a = null;	
      Jedis a = null;	
	
      label58: {	
         try {	
            try {	
               if ((a = getResource()).exists(key)) {	
                  a.del(key);	
               }	
	
               a = a.hmset(key, value);	
               if (cacheSeconds != 0) {	
                  a.expire(key, cacheSeconds);	
               }	
	
               logger.debug("setMap {} = {}", key, value);	
               break label58;	
            } catch (Exception var9) {	
               Logger var10000 = logger;	
               String var10001 = "setMap {} = {}";	
               Object[] var10002 = new Object[3];	
               boolean var10004 = true;	
               var10002[0] = key;	
               var10002[1] = value;	
               var10002[2] = var9;	
               var10000.warn(var10001, var10002);	
            }	
         } catch (Throwable var10) {	
            returnResource(a);	
            throw var10;	
         }	
	
         returnResource(a);	
         return a;	
      }	
	
      returnResource(a);	
      return a;	
   }	
	
   public static long setList(String key, List value, int cacheSeconds) {	
      long a = 0L;	
      Jedis a = null;	
	
      label58: {	
         try {	
            try {	
               if ((a = getResource()).exists(key)) {	
                  a.del(key);	
               }	
	
               a = a.rpush(key, (String[])((String[])value.toArray()));	
               if (cacheSeconds != 0) {	
                  a.expire(key, cacheSeconds);	
               }	
	
               logger.debug("setList {} = {}", key, value);	
               break label58;	
            } catch (Exception var10) {	
               Logger var10000 = logger;	
               String var10001 = "setList {} = {}";	
               Object[] var10002 = new Object[3];	
               boolean var10004 = true;	
               var10002[0] = key;	
               var10002[1] = value;	
               var10002[2] = var10;	
               var10000.warn(var10001, var10002);	
            }	
         } catch (Throwable var11) {	
            returnResource(a);	
            throw var11;	
         }	
	
         returnResource(a);	
         return a;	
      }	
	
      returnResource(a);	
      return a;	
   }	
	
   public static boolean exists(String key) {	
      int a = false;	
      Jedis a = null;	
	
      label39: {	
         try {	
            try {	
               a = (a = getResource()).exists(key);	
               logger.debug("exists {}", key);	
               break label39;	
            } catch (Exception var7) {	
               logger.warn("exists {}", key, var7);	
            }	
         } catch (Throwable var8) {	
            returnResource(a);	
            throw var8;	
         }	
	
         returnResource(a);	
         return a;	
      }	
	
      returnResource(a);	
      return a;	
   }	
	
   public static String set(String key, String value, int cacheSeconds) {	
      String a = null;	
      Jedis a = null;	
	
      label51: {	
         try {	
            try {	
               a = (a = getResource()).set(key, value);	
               if (cacheSeconds != 0) {	
                  a.expire(key, cacheSeconds);	
               }	
	
               logger.debug("set {} = {}", key, value);	
               break label51;	
            } catch (Exception var9) {	
               Logger var10000 = logger;	
               String var10001 = "set {} = {}";	
               Object[] var10002 = new Object[3];	
               boolean var10004 = true;	
               var10002[0] = key;	
               var10002[1] = value;	
               var10002[2] = var9;	
               var10000.warn(var10001, var10002);	
            }	
         } catch (Throwable var10) {	
            returnResource(a);	
            throw var10;	
         }	
	
         returnResource(a);	
         return a;	
      }	
	
      returnResource(a);	
      return a;	
   }	
	
   public static String mapObjectPut(String key, Map value) {	
      String a = null;	
      Jedis a = null;	
	
      label57: {	
         try {	
            try {	
               a = getResource();	
               Map a = MapUtils.newHashMap();	
               Iterator var5;	
               Iterator var12 = var5 = value.entrySet().iterator();	
	
               while(var12.hasNext()) {	
                  Entry a = (Entry)var5.next();	
                  var12 = var5;	
                  a.put(getBytesKey(a.getKey()), toBytes(a.getValue()));	
               }	
	
               a = a.hmset(getBytesKey(key), a);	
               logger.debug("mapObjectPut {} = {}", key, value);	
               break label57;	
            } catch (Exception var10) {	
               Logger var10000 = logger;	
               String var10001 = "mpObjectPut {} = {}";	
               Object[] var10002 = new Object[3];	
               boolean var10004 = true;	
               var10002[0] = key;	
               var10002[1] = value;	
               var10002[2] = var10;	
               var10000.warn(var10001, var10002);	
            }	
         } catch (Throwable var11) {	
            returnResource(a);	
            throw var11;	
         }	
	
         returnResource(a);	
         return a;	
      }	
	
      returnResource(a);	
      return a;	
   }	
	
   public static boolean mapObjectExists(String key, String mapKey) {	
      int a = false;	
      Jedis a = null;	
	
      label39: {	
         try {	
            try {	
               a = (a = getResource()).hexists(getBytesKey(key), getBytesKey(mapKey));	
               logger.debug("mapObjectExists {}  {}", key, mapKey);	
               break label39;	
            } catch (Exception var8) {	
               Logger var10000 = logger;	
               String var10001 = "mpObjectExists {}  {}";	
               Object[] var10002 = new Object[3];	
               boolean var10004 = true;	
               var10002[0] = key;	
               var10002[1] = mapKey;	
               var10002[2] = var8;	
               var10000.warn(var10001, var10002);	
            }	
         } catch (Throwable var9) {	
            returnResource(a);	
            throw var9;	
         }	
	
         returnResource(a);	
         return a;	
      }	
	
      returnResource(a);	
      return a;	
   }	
	
   public static boolean existsObject(String key) {	
      int a = false;	
      Jedis a = null;	
	
      label39: {	
         try {	
            try {	
               a = (a = getResource()).exists(getBytesKey(key));	
               logger.debug("existsObject {}", key);	
               break label39;	
            } catch (Exception var7) {	
               logger.warn("existsObject {}", key, var7);	
            }	
         } catch (Throwable var8) {	
            returnResource(a);	
            throw var8;	
         }	
	
         returnResource(a);	
         return a;	
      }	
	
      returnResource(a);	
      return a;	
   }	
	
   public static String setObjectMap(String key, Map value, int cacheSeconds) {	
      String a = null;	
      Jedis a = null;	
	
      label78: {	
         try {	
            try {	
               if ((a = getResource()).exists(getBytesKey(key))) {	
                  a.del(key);	
               }	
	
               Map a = MapUtils.newHashMap();	
               Iterator var6;	
               Iterator var13 = var6 = value.entrySet().iterator();	
	
               while(var13.hasNext()) {	
                  Entry a = (Entry)var6.next();	
                  var13 = var6;	
                  a.put(getBytesKey(a.getKey()), toBytes(a.getValue()));	
               }	
	
               a = a.hmset(getBytesKey(key), a);	
               if (cacheSeconds != 0) {	
                  a.expire(key, cacheSeconds);	
               }	
	
               logger.debug("setObjectMap {} = {}", key, value);	
               break label78;	
            } catch (Exception var11) {	
               Logger var10000 = logger;	
               String var10001 = "setObjectMap {} = {}";	
               Object[] var10002 = new Object[3];	
               boolean var10004 = true;	
               var10002[0] = key;	
               var10002[1] = value;	
               var10002[2] = var11;	
               var10000.warn(var10001, var10002);	
            }	
         } catch (Throwable var12) {	
            returnResource(a);	
            throw var12;	
         }	
	
         returnResource(a);	
         return a;	
      }	
	
      returnResource(a);	
      return a;	
   }	
	
   public static long mapObjectRemove(String key, String mapKey) {	
      long a = 0L;	
      Jedis a = null;	
	
      label39: {	
         try {	
            boolean var10004;	
            try {	
               Jedis var11 = a = getResource();	
               byte[] var12 = getBytesKey(key);	
               byte[][] var13 = new byte[1][];	
               var10004 = true;	
               var13[0] = getBytesKey(mapKey);	
               a = var11.hdel(var12, var13);	
               logger.debug("mapObjectRemove {}  {}", key, mapKey);	
               break label39;	
            } catch (Exception var9) {	
               Logger var10000 = logger;	
               String var10001 = "mpObjectRemove {}  {}";	
               Object[] var10002 = new Object[3];	
               var10004 = true;	
               var10002[0] = key;	
               var10002[1] = mapKey;	
               var10002[2] = var9;	
               var10000.warn(var10001, var10002);	
            }	
         } catch (Throwable var10) {	
            returnResource(a);	
            throw var10;	
         }	
	
         returnResource(a);	
         return a;	
      }	
	
      returnResource(a);	
      return a;	
   }	
	
   public static byte[] toBytes(Object object) {	
      return ObjectUtils.serializeFst(object);	
   }	
	
   public static boolean mapExists(String key, String mapKey) {	
      int a = false;	
      Jedis a = null;	
	
      label39: {	
         try {	
            try {	
               a = (a = getResource()).hexists(key, mapKey);	
               logger.debug("mapExists {}  {}", key, mapKey);	
               break label39;	
            } catch (Exception var8) {	
               Logger var10000 = logger;	
               String var10001 = "mpExists {}  {}";	
               Object[] var10002 = new Object[3];	
               boolean var10004 = true;	
               var10002[0] = key;	
               var10002[1] = mapKey;	
               var10002[2] = var8;	
               var10000.warn(var10001, var10002);	
            }	
         } catch (Throwable var9) {	
            returnResource(a);	
            throw var9;	
         }	
	
         returnResource(a);	
         return a;	
      }	
	
      returnResource(a);	
      return a;	
   }	
	
   public static long setSetAdd(String key, String... value) {	
      long a = 0L;	
      Jedis a = null;	
	
      label39: {	
         try {	
            try {	
               a = (a = getResource()).sadd(key, value);	
               logger.debug("setSetAdd {} = {}", key, value);	
               break label39;	
            } catch (Exception var9) {	
               Logger var10000 = logger;	
               String var10001 = "setSetAdd {} = {}";	
               Object[] var10002 = new Object[3];	
               boolean var10004 = true;	
               var10002[0] = key;	
               var10002[1] = value;	
               var10002[2] = var9;	
               var10000.warn(var10001, var10002);	
            }	
         } catch (Throwable var10) {	
            returnResource(a);	
            throw var10;	
         }	
	
         returnResource(a);	
         return a;	
      }	
	
      returnResource(a);	
      return a;	
   }	
	
   public static byte[] getBytesKey(Object key) {	
      return key instanceof String ? StringUtils.getBytes((String)key) : toBytes(key);	
   }	
	
   public static void returnResource(Jedis jedis) {	
      if (jedis != null) {	
         jedis.close();	
      }	
	
   }	
	
   public static Set getObjectSet(String key) {	
      Set a = null;	
      Jedis a = null;	
	
      label68: {	
         try {	
            try {	
               if ((a = getResource()).exists(getBytesKey(key))) {	
                  a = SetUtils.newHashSet();	
                  Iterator var4;	
                  Iterator var11 = var4 = a.smembers(getBytesKey(key)).iterator();	
	
                  while(var11.hasNext()) {	
                     byte[] a = (byte[])var4.next();	
                     var11 = var4;	
                     a.add(toObject(a));	
                  }	
	
                  logger.debug("getObjectSet {} = {}", key, a);	
               }	
               break label68;	
            } catch (Exception var9) {	
               Logger var10000 = logger;	
               String var10001 = "getObjectSet {} = {}";	
               Object[] var10002 = new Object[3];	
               boolean var10004 = true;	
               var10002[0] = key;	
               var10002[1] = a;	
               var10002[2] = var9;	
               var10000.warn(var10001, var10002);	
            }	
         } catch (Throwable var10) {	
            returnResource(a);	
            throw var10;	
         }	
	
         returnResource(a);	
         return a;	
      }	
	
      returnResource(a);	
      return a;	
   }	
	
   public static long del(String key) {	
      long a = 0L;	
      Jedis a = null;	
	
      Jedis var10000;	
      label59: {	
         label58: {	
            try {	
               try {	
                  if ((a = getResource()).exists(key)) {	
                     var10000 = a;	
                     a = a.del(key);	
                     logger.debug("del {}", key);	
                     break label59;	
                  }	
	
                  logger.debug("del {} not exists", key);	
                  break label58;	
               } catch (Exception var8) {	
                  logger.warn("del {}", key, var8);	
               }	
            } catch (Throwable var9) {	
               returnResource(a);	
               throw var9;	
            }	
	
            returnResource(a);	
            return a;	
         }	
	
         var10000 = a;	
      }	
	
      returnResource(var10000);	
      return a;	
   }	
	
   public static Object toObject(byte[] bytes) {	
      return ObjectUtils.unserializeFst(bytes);	
   }	
	
   public static String get(String key) {	
      String a = null;	
      Jedis a = null;	
	
      label68: {	
         try {	
            try {	
               if ((a = getResource()).exists(key)) {	
                  a = StringUtils.isNotBlank(a = a.get(key)) && !"nil".equalsIgnoreCase(a) ? a : null;	
                  logger.debug("get {} = {}", key, a);	
               }	
               break label68;	
            } catch (Exception var7) {	
               Logger var10000 = logger;	
               String var10001 = "get {} = {}";	
               Object[] var10002 = new Object[3];	
               boolean var10004 = true;	
               var10002[0] = key;	
               var10002[1] = a;	
               var10002[2] = var7;	
               var10000.warn(var10001, var10002);	
            }	
         } catch (Throwable var8) {	
            returnResource(a);	
            throw var8;	
         }	
	
         returnResource(a);	
         return a;	
      }	
	
      returnResource(a);	
      return a;	
   }	
	
   public static String setObject(String key, Object value, int cacheSeconds) {	
      String a = null;	
      Jedis a = null;	
	
      label51: {	
         try {	
            try {	
               a = (a = getResource()).set(getBytesKey(key), toBytes(value));	
               if (cacheSeconds != 0) {	
                  a.expire(key, cacheSeconds);	
               }	
	
               logger.debug("setObject {} = {}", key, value);	
               break label51;	
            } catch (Exception var9) {	
               Logger var10000 = logger;	
               String var10001 = "setObject {} = {}";	
               Object[] var10002 = new Object[3];	
               boolean var10004 = true;	
               var10002[0] = key;	
               var10002[1] = value;	
               var10002[2] = var9;	
               var10000.warn(var10001, var10002);	
            }	
         } catch (Throwable var10) {	
            returnResource(a);	
            throw var10;	
         }	
	
         returnResource(a);	
         return a;	
      }	
	
      returnResource(a);	
      return a;	
   }	
	
   public static Object getObject(String key) {	
      Object a = null;	
      Jedis a = null;	
	
      label47: {	
         try {	
            try {	
               if ((a = getResource()).exists(getBytesKey(key))) {	
                  a = toObject(a.get(getBytesKey(key)));	
                  logger.debug("getObject {} = {}", key, a);	
               }	
               break label47;	
            } catch (Exception var7) {	
               Logger var10000 = logger;	
               String var10001 = "getObject {} = {}";	
               Object[] var10002 = new Object[3];	
               boolean var10004 = true;	
               var10002[0] = key;	
               var10002[1] = a;	
               var10002[2] = var7;	
               var10000.warn(var10001, var10002);	
            }	
         } catch (Throwable var8) {	
            returnResource(a);	
            throw var8;	
         }	
	
         returnResource(a);	
         return a;	
      }	
	
      returnResource(a);	
      return a;	
   }	
	
   public static long delObject(String key) {	
      long a = 0L;	
      Jedis a = null;	
	
      Jedis var10000;	
      label59: {	
         label58: {	
            try {	
               try {	
                  if ((a = getResource()).exists(getBytesKey(key))) {	
                     var10000 = a;	
                     a = a.del(getBytesKey(key));	
                     logger.debug("delObject {}", key);	
                     break label59;	
                  }	
	
                  logger.debug("delObject {} not exists", key);	
                  break label58;	
               } catch (Exception var8) {	
                  logger.warn("delObject {}", key, var8);	
               }	
            } catch (Throwable var9) {	
               returnResource(a);	
               throw var9;	
            }	
	
            returnResource(a);	
            return a;	
         }	
	
         var10000 = a;	
      }	
	
      returnResource(var10000);	
      return a;	
   }	
	
   public static Map getObjectMap(String key) {	
      Map a = null;	
      Jedis a = null;	
	
      label68: {	
         try {	
            try {	
               if ((a = getResource()).exists(getBytesKey(key))) {	
                  a = MapUtils.newHashMap();	
	
                  Iterator var4;	
                  for(Iterator var11 = var4 = a.hgetAll(getBytesKey(key)).entrySet().iterator(); var11.hasNext(); var11 = var4) {	
                     Entry a = (Entry)var4.next();	
                     a.put(ObjectUtils.toString(a.getKey()), toObject((byte[])a.getValue()));	
                  }	
	
                  logger.debug("getObjectMap {} = {}", key, a);	
               }	
               break label68;	
            } catch (Exception var9) {	
               Logger var10000 = logger;	
               String var10001 = "getObjectMap {} = {}";	
               Object[] var10002 = new Object[3];	
               boolean var10004 = true;	
               var10002[0] = key;	
               var10002[1] = a;	
               var10002[2] = var9;	
               var10000.warn(var10001, var10002);	
            }	
         } catch (Throwable var10) {	
            returnResource(a);	
            throw var10;	
         }	
	
         returnResource(a);	
         return a;	
      }	
	
      returnResource(a);	
      return a;	
   }	
	
   public static long listObjectAdd(String key, Object... value) {	
      long a = 0L;	
      Jedis a = null;	
	
      label57: {	
         try {	
            try {	
               a = getResource();	
               List a = ListUtils.newArrayList();	
               Object[] var6 = value;	
               int var7 = value.length;	
	
               int var8;	
               for(int var15 = var8 = 0; var15 < var7; var15 = var8) {	
                  Object a = var6[var8];	
                  ++var8;	
                  a.add(toBytes(a));	
               }	
	
               a = a.rpush(getBytesKey(key), (byte[][])((byte[][])a.toArray()));	
               logger.debug("listObjectAdd {} = {}", key, value);	
               break label57;	
            } catch (Exception var13) {	
               Logger var10000 = logger;	
               String var10001 = "listObjectAdd {} = {}";	
               Object[] var10002 = new Object[3];	
               boolean var10004 = true;	
               var10002[0] = key;	
               var10002[1] = value;	
               var10002[2] = var13;	
               var10000.warn(var10001, var10002);	
            }	
         } catch (Throwable var14) {	
            returnResource(a);	
            throw var14;	
         }	
	
         returnResource(a);	
         return a;	
      }	
	
      returnResource(a);	
      return a;	
   }	
	
   public static Jedis getResource() throws JedisException {	
      Jedis a = null;	
	
      try {	
         a = jedisPool.getResource();	
         return a;	
      } catch (JedisException var2) {	
         logger.warn("getResource.", var2);	
         returnResource(a);	
         throw var2;	
      }	
   }	
}	
