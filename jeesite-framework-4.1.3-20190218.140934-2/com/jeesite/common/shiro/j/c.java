package com.jeesite.common.shiro.j;	
	
import com.jeesite.autoconfigure.core.TransactionAutoConfiguration;	
import com.jeesite.common.collect.SetUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.io.FileUtils;	
import java.util.Set;	
import java.util.regex.Pattern;	
import net.oschina.j2cache.CacheChannel;	
import net.oschina.j2cache.CacheProviderHolder;	
import org.apache.shiro.cache.CacheException;	
import org.hyperic.sigar.ProcMem;	
import org.slf4j.Logger;	
	
public class c implements E {	
   public void ALLATORIxDEMO(String cacheName) {	
      CacheProviderHolder.getLevel1Cache(cacheName).clear();	
      CacheProviderHolder.getL1Provider().removeCache(cacheName);	
   }	
	
   public Set ALLATORIxDEMO() {	
      Set a = SetUtils.newHashSet();	
      CacheProviderHolder.getL1Provider().regions().forEach((fegion) -> {	
         a.add(fegion.getName());	
      });	
      return a;	
   }	
	
   public c(CacheChannel channel) {	
      new Object() {	
         private static final Pattern ALLATORIxDEMO = Pattern.compile("[一-龥\s]");	
	
         static {	
            ALLATORIxDEMO();	
         }	
	
         // $FF: synthetic method	
         private static boolean ALLATORIxDEMO(String str) {	
            return ALLATORIxDEMO.matcher(str).find();	
         }	
	
         // $FF: synthetic method	
         private static void ALLATORIxDEMO() {	
            StringBuilder a;	
            (a = new StringBuilder()).append("\r\n");	
            a.append("   _____        ___  _   _           ___\r\n");	
            a.append("  |_   _|      / __'(_)_| |_        /   |\r\n");	
            a.append("  _ | | __  __ \___ | |_   _| __   / /| |\r\n");	
            a.append(" ( .| |/__\/__\ ___)| | | |_ /__\ / /_| |_\r\n");	
            a.append("  \___|\__.\__.|____|_| \___)\__.(____   _|  :: JeeSite V" + com.jeesite.common.e.E.ALLATORIxDEMO() + " :: \r\n");	
            a.append("======================================|_|==========================\r\n");	
            a.append((new StringBuilder()).insert(0, "\r\n    欢迎使用 ").append(Global.getProperty("productName")).append(" ").append(Global.getProperty("productVersion")).toString());	
            a.append((new StringBuilder()).insert(0, "\r\n").append((String)com.jeesite.common.web.e.F.ALLATORIxDEMO().get("loadMessage")).append("\r\n").toString());	
            a.append("===================================================================\r\n");	
            Logger ax = Global.logger;	
            if (ax.isInfoEnabled()) {	
               ax.info((new StringBuilder()).insert(0, "user.home: ").append(FileUtils.path(System.getProperty("user.home"))).toString());	
               ax.info((new StringBuilder()).insert(0, "ser.dir: ").append(FileUtils.path(System.getProperty("user.dir"))).toString());	
               a.insert(0, (new StringBuilder()).insert(0, "logPath: ").append(FileUtils.path(System.getProperty("logPath"))).append("\r\n").toString());	
               ax.info(a.toString());	
            } else {	
               System.out.println(a.toString());	
            }	
	
            String axx;	
            if (ALLATORIxDEMO(axx = FileUtils.getProjectPath())) {	
               (a = new StringBuilder()).append("\r\n===================================================================\r\n");	
               a.append("\r\n    严重警告：请将系统部署到不带有 “空格” 或 “中文” 的磁盘路径下！！！\r\n");	
               a.append((new StringBuilder()).insert(0, "\r\n    当前路径：").append(axx).append("\r\n").toString());	
               a.append("\r\n===================================================================");	
               ax.warn(a.toString());	
            }	
	
         }	
      };	
   }	
	
   public final e ALLATORIxDEMO(String cacheName) throws CacheException {	
      return new g(cacheName);	
   }	
}	
