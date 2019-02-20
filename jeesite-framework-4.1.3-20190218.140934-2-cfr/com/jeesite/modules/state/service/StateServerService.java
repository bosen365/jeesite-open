/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.collect.ListUtils	
 *  com.jeesite.common.collect.MapUtils	
 *  com.jeesite.common.lang.ByteUtils	
 *  com.jeesite.common.lang.DateUtils	
 *  com.jeesite.common.lang.TimeUtils	
 *  org.springframework.boot.autoconfigure.condition.ConditionalOnProperty	
 *  org.springframework.core.env.Environment	
 *  org.springframework.stereotype.Service	
 */	
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
import org.hyperic.sigar.Mem;	
import org.hyperic.sigar.Sigar;	
import org.hyperic.sigar.SigarException;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;	
import org.springframework.core.env.Environment;	
import org.springframework.stereotype.Service;	
	
@Service	
@ConditionalOnProperty(name={"state.enabled"}, havingValue="true", matchIfMissing=true)	
public class StateServerService	
extends BaseService {	
    public Map<String, Object> getCpuInfo() {	
        LinkedHashMap a2 = MapUtils.newLinkedHashMap();	
        try {	
            CpuInfo[] a3 = e.D.getCpuInfoList();	
            CpuInfo a4 = a3[0];	
            double a5 = (double)a4.getMhz() / 1024.0;	
            DecimalFormat a6 = new DecimalFormat("0.00");	
            CpuPerc a7 = e.D.getCpuPerc();	
            double a8 = a7.getIdle();	
            double a9 = a7.getUser();	
            a9 += a7.getSys();	
            a9 += a7.getIrq();	
            a9 += a7.getNice();	
            a9 += a7.getWait();	
            a9 += a7.getSoftIrq();	
            a9 += a7.getStolen();	
            a2.put("maxGhz", new StringBuilder().insert(0, a6.format(a5)).append("Ghz").toString());	
            a2.put("model", a4.getModel());	
            a2.put("vendor", a4.getVendor());	
            int n = a3.length;	
            double a10 = a8 / (a9 += a7.getCombined()) / (double)n;	
            LinkedHashMap linkedHashMap = a2;	
            long a11 = Math.round(a7.getCombined() * 100.0);	
            linkedHashMap.put("usedPerc", a11 + "%");	
            a2.put("currGhz", new StringBuilder().insert(0, a6.format(a10)).append("Ghz").toString());	
            a2.put("cpuNum", n + "个");	
            linkedHashMap.put("cacheSize", a4.getCacheSize());	
            return a2;	
        }	
        catch (SigarException a12) {	
            a12.printStackTrace();	
            return a2;	
        }	
    }	
	
    public Map<String, Object> getJvmInfo() {	
        void a2;	
        LinkedHashMap a3 = MapUtils.newLinkedHashMap();	
        Runtime runtime = Runtime.getRuntime();	
        double a4 = runtime.totalMemory();	
        double a5 = runtime.freeMemory();	
        double d = a4 - a5;	
        LinkedHashMap linkedHashMap = a3;	
        long a6 = Math.round((double)(a2 / a4 * 100.0));	
        MemoryMXBean a7 = ManagementFactory.getMemoryMXBean();	
        MemoryUsage a8 = a7.getHeapMemoryUsage();	
        MemoryUsage a9 = a7.getNonHeapMemoryUsage();	
        a3.put("nonHeapUsed", ByteUtils.formatByteSize((long)a9.getUsed()));	
        a3.put("nonHeapMax", ByteUtils.formatByteSize((long)a9.getMax()));	
        a3.put("nonHeapInit", ByteUtils.formatByteSize((long)a9.getInit()));	
        a3.put("heapAvailable", ByteUtils.formatByteSize((long)(a8.getMax() - a8.getUsed())));	
        a3.put("heapUsed", ByteUtils.formatByteSize((long)a8.getUsed()));	
        a3.put("heapMax", ByteUtils.formatByteSize((long)a8.getMax()));	
        a3.put("heapInit", ByteUtils.formatByteSize((long)a8.getInit()));	
        a3.put("usedPerc", a6 + "%");	
        linkedHashMap.put("used", ByteUtils.formatByteSize((long)((long)a2)));	
        a3.put("free", ByteUtils.formatByteSize((long)((long)a5)));	
        a3.put("total", ByteUtils.formatByteSize((long)((long)a4)));	
        linkedHashMap.put("nonHeapAvailable", ByteUtils.formatByteSize((long)(a9.getMax() == -1L ? -1L : a9.getMax() - a9.getUsed())));	
        return a3;	
    }	
	
    public Map<String, Object> getServerInfo() {	
        LinkedHashMap linkedHashMap;	
        OperatingSystemMXBean a2;	
        Object a32;	
        LinkedHashMap a4 = MapUtils.newLinkedHashMap();	
        try {	
            InetAddress inetAddress = InetAddress.getLocalHost();	
            LinkedHashMap linkedHashMap2 = a4;	
            linkedHashMap2.put("hostAddress", ((InetAddress)((Object)a2)).getHostAddress());	
            linkedHashMap2.put("hostName", ((InetAddress)((Object)a2)).getHostName());	
        }	
        catch (UnknownHostException a5) {	
            a5.printStackTrace();	
        }	
        a2 = ManagementFactory.getOperatingSystemMXBean();	
        RuntimeMXBean a6 = ManagementFactory.getRuntimeMXBean();	
        a4.put("uptime", TimeUtils.formatDateAgo((long)a6.getUptime()));	
        a4.put("startTime", DateUtils.formatDate((long)a6.getStartTime(), (String)"yyyy-MM-dd HH:mm:ss"));	
        a4.put("javaArgs", a6.getInputArguments());	
        a4.put("javaHome", System.getProperty("java.home"));	
        a4.put("javaVendor", System.getProperty("java.vm.vendor"));	
        a4.put("javaVersion", new StringBuilder().insert(0, System.getProperty("java.runtime.version")).append(" / ").append(System.getProperty("java.vm.version")).toString());	
        a4.put("javaName", System.getProperty("java.vm.name"));	
        a4.put("osArch", a2.getArch());	
        a4.put("osProcessors", a2.getAvailableProcessors());	
        a4.put("osVersion", a2.getVersion());	
        a4.put("osName", a2.getName());	
        try {	
            a32 = SpringUtils.getBean(Environment.class);	
            a4.put("activeProfiles", a32.getActiveProfiles());	
            linkedHashMap = a4;	
        }	
        catch (IllegalStateException a32) {	
            linkedHashMap = a4;	
        }	
        a4.put("userfilesDir", Global.getUserfilesBaseDir(""));	
        a4.put("logPath", System.getProperty("logPath"));	
        linkedHashMap.put("userDir", System.getProperty("user.dir"));	
        if (Global.getPropertyToBoolean("demoMode", "false").booleanValue()) {	
            a32 = "*** 演示模式，不展示数据 ***";	
            LinkedHashMap linkedHashMap3 = a4;	
            linkedHashMap3.put("javaHome", a32);	
            a4.put("hostAddress", a32);	
            a4.put("userfilesDir", a32);	
            a4.put("logPath", a32);	
            a4.put("userDir", a32);	
            linkedHashMap3.put("javaArgs", ListUtils.newArrayList((Object[])new String[]{a32}));	
        }	
        return a4;	
    }	
	
    /*	
     * Exception decompiling	
     */	
    public List<Map<String, Object>> getDiskListInfo() {	
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.	
        // org.benf.cfr.reader.util.ConfusedCFRException: Extractable last case doesn't follow previous	
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.examineSwitchContiguity(SwitchReplacer.java:478)	
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.replaceRawSwitches(SwitchReplacer.java:61)	
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:372)	
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:182)	
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:127)	
        // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:96)	
        // org.benf.cfr.reader.entities.Method.analyse(Method.java:396)	
        // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:890)	
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:792)	
        // org.benf.cfr.reader.Driver.doJar(Driver.java:128)	
        // org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:63)	
        // org.benf.cfr.reader.Main.main(Main.java:48)	
        throw new IllegalStateException("Decompilation failed");	
    }	
	
    public void gc() {	
        System.gc();	
    }	
	
    public Map<String, Object> getMemInfo() {	
        LinkedHashMap a2 = MapUtils.newLinkedHashMap();	
        try {	
            void a3;	
            Mem a4 = e.D.getMem();	
            double d = a4.getTotal();	
            double a5 = a4.getFree();	
            LinkedHashMap linkedHashMap = a2;	
            double a6 = a4.getUsed();	
            long a7 = Math.round(a6 / a3 * 100.0);	
            linkedHashMap.put("used", ByteUtils.formatByteSize((long)((long)a6)));	
            a2.put("free", ByteUtils.formatByteSize((long)((long)a5)));	
            a2.put("total", ByteUtils.formatByteSize((long)((long)d)));	
            linkedHashMap.put("usedPerc", a7 + "%");	
            return a2;	
        }	
        catch (SigarException a8) {	
            a8.printStackTrace();	
            return a2;	
        }	
    }	
}	
	
