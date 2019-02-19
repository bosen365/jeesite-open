package com.jeesite.common.j2cache.cache.support.utils;	
	
import java.io.IOException;	
import net.oschina.j2cache.util.SerializationUtils;	
import org.springframework.data.redis.serializer.RedisSerializer;	
import org.springframework.data.redis.serializer.SerializationException;	
	
public class J2CacheSerializer implements RedisSerializer {	
   public byte[] serialize(Object t) throws SerializationException {	
      try {	
         return SerializationUtils.serialize(t);	
      } catch (IOException var3) {	
         var3.printStackTrace();	
         return null;	
      }	
   }	
	
   public Object deserialize(byte[] bytes) throws SerializationException {	
      try {	
         return SerializationUtils.deserialize(bytes);	
      } catch (IOException var3) {	
         var3.printStackTrace();	
         return null;	
      }	
   }	
}	
