package com.jeesite.common.mybatis;	
	
import com.jeesite.common.cache.JedisUtils;	
import com.jeesite.common.io.ResourceUtils;	
import java.io.IOException;	
import java.net.URI;	
import java.net.URL;	
import java.util.ArrayList;	
import java.util.List;	
import org.apache.ibatis.io.VFS;	
import org.hyperic.sigar.ProcMem;	
import org.springframework.core.io.Resource;	
	
public class SpringBootVFS extends VFS {	
   protected List list(URL url, String path) throws IOException {	
      Resource[] a = ResourceUtils.getResources((new StringBuilder()).insert(0, "classpath*:").append(path).append("/**/*.*").toString());	
      List a = new ArrayList();	
      Resource[] var5 = a;	
      int var6 = a.length;	
	
      int var7;	
      for(int var10000 = var7 = 0; var10000 < var6; var10000 = var7) {	
         Resource a = var5[var7];	
         URI var10001 = a.getURI();	
         ++var7;	
         a.add(preserveSubpackageName(var10001, path));	
      }	
	
      return a;	
   }	
	
   public boolean isValid() {	
      return true;	
   }	
	
   // $FF: synthetic method	
   private static String preserveSubpackageName(URI uri, String rootPath) {	
      String var10000 = uri.toString();	
      return var10000.substring(var10000.indexOf(rootPath));	
   }	
}	
