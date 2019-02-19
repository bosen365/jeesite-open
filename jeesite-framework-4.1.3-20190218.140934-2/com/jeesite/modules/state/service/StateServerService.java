package com.jeesite.modules.state.service;	
	
import com.jeesite.autoconfigure.sys.MsgAutoConfiguration;	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.lang.ByteUtils;	
import com.jeesite.common.lang.DateUtils;	
import com.jeesite.common.lang.TimeUtils;	
import com.jeesite.common.mybatis.j.e.e;	
import com.jeesite.common.service.BaseService;	
import com.jeesite.common.utils.SpringUtils;	
import com.jeesite.modules.gen.entity.config.GenDict;	
import java.lang.management.ManagementFactory;	
import java.lang.management.MemoryMXBean;	
import java.lang.management.MemoryUsage;	
import java.lang.management.OperatingSystemMXBean;	
import java.lang.management.RuntimeMXBean;	
import java.net.InetAddress;	
import java.net.UnknownHostException;	
import java.text.DecimalFormat;	
import java.util.ArrayList;	
import java.util.LinkedHashMap;	
import java.util.List;	
import java.util.Map;	
import org.hyperic.sigar.CpuInfo;	
import org.hyperic.sigar.CpuPerc;	
import org.hyperic.sigar.FileSystem;	
import org.hyperic.sigar.FileSystemUsage;	
import org.hyperic.sigar.Mem;	
import org.hyperic.sigar.SigarException;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;	
import org.springframework.core.env.Environment;	
import org.springframework.stereotype.Service;	
	
@Service	
@ConditionalOnProperty(	
   name = {"state.enabled"},	
   havingValue = "true",	
   matchIfMissing = true	
)	
public class StateServerService extends BaseService {	
   public Map getCpuInfo() {	
      LinkedHashMap a = MapUtils.newLinkedHashMap();	
	
      try {	
         CpuInfo[] var10000 = e.D.getCpuInfoList();	
         CpuInfo a = var10000[0];	
         a.put("vendor", a.getVendor());	
         a.put("model", a.getModel());	
         double a = (double)a.getMhz() / 1024.0D;	
         DecimalFormat a = new DecimalFormat("0.00");	
         a.put("maxGhz", (new StringBuilder()).insert(0, a.format(a)).append("Ghz").toString());	
         CpuPerc a;	
         CpuPerc var10004 = a = e.D.getCpuPerc();	
         double a = var10004.getIdle();	
         double a = var10004.getUser() + a.getSys() + a.getIrq() + a.getNice() + a.getWait() + a.getSoftIrq() + a.getStolen() + a.getCombined();	
         int var14 = var10000.length;	
         a.put("cpuNum", var14 + "个");	
         double a = a / a / (double)var14;	
         a.put("currGhz", (new StringBuilder()).insert(0, a.format(a)).append("Ghz").toString());	
         long a = Math.round(a.getCombined() * 100.0D);	
         a.put("usedPerc", a + "%");	
         a.put("cacheSize", a.getCacheSize());	
         return a;	
      } catch (SigarException var19) {	
         var19.printStackTrace();	
         return a;	
      }	
   }	
	
   public Map getJvmInfo() {	
      Map a = MapUtils.newLinkedHashMap();	
      Runtime var10000 = Runtime.getRuntime();	
      double a = (double)var10000.totalMemory();	
      double a = (double)var10000.freeMemory();	
      double a = a - a;	
      a.put("total", ByteUtils.formatByteSize((long)a));	
      a.put("free", ByteUtils.formatByteSize((long)a));	
      a.put("used", ByteUtils.formatByteSize((long)a));	
      long a = Math.round(a / a * 100.0D);	
      a.put("usedPerc", a + "%");	
      MemoryMXBean a;	
      MemoryUsage a = (a = ManagementFactory.getMemoryMXBean()).getHeapMemoryUsage();	
      a.put("heapInit", ByteUtils.formatByteSize(a.getInit()));	
      a.put("heapMax", ByteUtils.formatByteSize(a.getMax()));	
      a.put("heapUsed", ByteUtils.formatByteSize(a.getUsed()));	
      a.put("heapAvailable", ByteUtils.formatByteSize(a.getMax() - a.getUsed()));	
      MemoryUsage a = a.getNonHeapMemoryUsage();	
      a.put("nonHeapInit", ByteUtils.formatByteSize(a.getInit()));	
      a.put("nonHeapMax", ByteUtils.formatByteSize(a.getMax()));	
      a.put("nonHeapUsed", ByteUtils.formatByteSize(a.getUsed()));	
      a.put("nonHeapAvailable", ByteUtils.formatByteSize(a.getMax() == -1L ? -1L : a.getMax() - a.getUsed()));	
      return a;	
   }	
	
   public Map getServerInfo() {	
      LinkedHashMap a = MapUtils.newLinkedHashMap();	
	
      try {	
         InetAddress a = InetAddress.getLocalHost();	
         a.put("hostAddress", a.getHostAddress());	
         a.put("hostName", a.getHostName());	
      } catch (UnknownHostException var5) {	
         var5.printStackTrace();	
      }	
	
      OperatingSystemMXBean a = ManagementFactory.getOperatingSystemMXBean();	
      a.put("osName", a.getName());	
      a.put("osVersion", a.getVersion());	
      a.put("osProcessors", a.getAvailableProcessors());	
      a.put("osArch", a.getArch());	
      RuntimeMXBean a = ManagementFactory.getRuntimeMXBean();	
      a.put("javaName", System.getProperty("java.vm.name"));	
      a.put("javaVersion", (new StringBuilder()).insert(0, System.getProperty("java.runtime.version")).append(" / ").append(System.getProperty("java.vm.version")).toString());	
      a.put("javaVendor", System.getProperty("java.vm.vendor"));	
      a.put("javaHome", System.getProperty("java.home"));	
      a.put("javaArgs", a.getInputArguments());	
      a.put("startTime", DateUtils.formatDate(a.getStartTime(), "yyyy-MM-dd HH:mm:ss"));	
      a.put("uptime", TimeUtils.formatDateAgo(a.getUptime()));	
	
      LinkedHashMap var10000;	
      label27: {	
         try {	
            Environment a = (Environment)SpringUtils.getBean(Environment.class);	
            a.put("activeProfiles", a.getActiveProfiles());	
         } catch (IllegalStateException var6) {	
            var10000 = a;	
            break label27;	
         }	
	
         var10000 = a;	
      }	
	
      var10000.put("userDir", System.getProperty("user.dir"));	
      a.put("logPath", System.getProperty("logPath"));	
      a.put("userfilesDir", Global.getUserfilesBaseDir(""));	
      if (Global.getPropertyToBoolean("demoMode", "false")) {	
         String a = "*** 演示模式，不展示数据 ***";	
         a.put("hostAddress", a);	
         a.put("javaHome", a);	
         String var10001 = "javaArgs";	
         String[] var10002 = new String[1];	
         boolean var10004 = true;	
         var10002[0] = a;	
         a.put(var10001, ListUtils.newArrayList(var10002));	
         a.put("userDir", a);	
         a.put("logPath", a);	
         a.put("userfilesDir", a);	
      }	
	
      return a;	
   }	
	
   public List getDiskListInfo() {	
      ArrayList a = ListUtils.newArrayList();	
	
      try {	
         FileSystem[] a = e.D.getFileSystemList();	
         int a = 0;	
	
         while(a < a.length) {	
            LinkedHashMap a;	
            label50: {	
               FileSystem a = a[a];	
               (a = MapUtils.newLinkedHashMap()).put("devName", a.getDevName());	
               a.put("dirName", a.getDirName());	
               a.put("sysTypeName", a.getSysTypeName());	
               a.put("typeName", a.getTypeName());	
               FileSystemUsage a = e.D.getFileSystemUsage(a.getDirName());	
               switch(a.getType()) {	
               case 0:	
               case 1:	
               case 3:	
               case 4:	
               case 5:	
               case 6:	
               default:	
                  break label50;	
               case 2:	
               }	
	
               while(false) {	
               }	
	
               a.put("total", ByteUtils.formatByteSize(a.getTotal() * 1024L));	
               a.put("free", ByteUtils.formatByteSize(a.getFree() * 1024L));	
               a.put("avail", ByteUtils.formatByteSize(a.getAvail() * 1024L));	
               a.put("used", ByteUtils.formatByteSize(a.getUsed() * 1024L));	
               long a = Math.round(a.getUsePercent() * 100.0D);	
               a.put("usedPerc", a + "%");	
            }	
	
            if (Global.getPropertyToBoolean("demoMode", "false")) {	
               a.add(a);	
               break;	
            }	
	
            ++a;	
            a.add(a);	
         }	
      } catch (SigarException var9) {	
         var9.printStackTrace();	
      }	
	
      return a;	
   }	
	
   public void gc() {	
      System.gc();	
   }	
	
   public Map getMemInfo() {	
      LinkedHashMap a = MapUtils.newLinkedHashMap();	
	
      try {	
         Mem a;	
         double a = (double)(a = e.D.getMem()).getTotal();	
         a.put("total", ByteUtils.formatByteSize((long)a));	
         double a = (double)a.getFree();	
         a.put("free", ByteUtils.formatByteSize((long)a));	
         double a = (double)a.getUsed();	
         a.put("used", ByteUtils.formatByteSize((long)a));	
         long a = Math.round(a / a * 100.0D);	
         a.put("usedPerc", a + "%");	
         return a;	
      } catch (SigarException var11) {	
         var11.printStackTrace();	
         return a;	
      }	
   }	
}	
