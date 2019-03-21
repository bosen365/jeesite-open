/*	
 * Decompiled with CFR 0.140.	
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
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.lang.ByteUtils;	
import com.jeesite.common.lang.DateUtils;	
import com.jeesite.common.lang.TimeUtils;	
import com.jeesite.common.mybatis.d.v.c;	
import com.jeesite.common.service.BaseService;	
import com.jeesite.common.utils.SpringUtils;	
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
import org.hyperic.sigar.ProcCredName;	
import org.hyperic.sigar.Sigar;	
import org.hyperic.sigar.SigarException;	
import org.hyperic.sigar.Who;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;	
import org.springframework.core.env.Environment;	
import org.springframework.stereotype.Service;	
	
@Service	
@ConditionalOnProperty(name={"state.enabled"}, havingValue="true", matchIfMissing=true)	
public class StateServerService	
extends BaseService {	
    public Map<String, Object> getCpuInfo() {	
        LinkedHashMap a = MapUtils.newLinkedHashMap();	
        try {	
            CpuInfo[] a2 = c.a.getCpuInfoList();	
            CpuInfo a3 = a2[0];	
            double a4 = (double)a3.getMhz() / 1024.0;	
            DecimalFormat a5 = new DecimalFormat("0.00");	
            CpuPerc a6 = c.a.getCpuPerc();	
            double a7 = a6.getIdle();	
            double a8 = a6.getUser();	
            a8 += a6.getSys();	
            a8 += a6.getIrq();	
            a8 += a6.getNice();	
            a8 += a6.getWait();	
            a8 += a6.getSoftIrq();	
            a8 += a6.getStolen();	
            a.put("maxGhz", new StringBuilder().insert(0, a5.format(a4)).append("Ghz").toString());	
            a.put("model", a3.getModel());	
            a.put("vendor", a3.getVendor());	
            int n = a2.length;	
            double a9 = a7 / (a8 += a6.getCombined()) / (double)n;	
            LinkedHashMap linkedHashMap = a;	
            long a10 = Math.round(a6.getCombined() * 100.0);	
            linkedHashMap.put("usedPerc", a10 + "%");	
            a.put("currGhz", new StringBuilder().insert(0, a5.format(a9)).append("Ghz").toString());	
            a.put("cpuNum", n + "个");	
            linkedHashMap.put("cacheSize", a3.getCacheSize());	
            return a;	
        }	
        catch (SigarException a11) {	
            a11.printStackTrace();	
            return a;	
        }	
    }	
	
    public Map<String, Object> getServerInfo() {	
        LinkedHashMap linkedHashMap;	
        OperatingSystemMXBean a;	
        Object a22;	
        LinkedHashMap a3 = MapUtils.newLinkedHashMap();	
        try {	
            InetAddress inetAddress = InetAddress.getLocalHost();	
            LinkedHashMap linkedHashMap2 = a3;	
            linkedHashMap2.put("hostAddress", ((InetAddress)((Object)a)).getHostAddress());	
            linkedHashMap2.put("hostName", ((InetAddress)((Object)a)).getHostName());	
        }	
        catch (UnknownHostException a4) {	
            a4.printStackTrace();	
        }	
        a = ManagementFactory.getOperatingSystemMXBean();	
        RuntimeMXBean a5 = ManagementFactory.getRuntimeMXBean();	
        a3.put("uptime", TimeUtils.formatDateAgo((long)a5.getUptime()));	
        a3.put("startTime", DateUtils.formatDate((long)a5.getStartTime(), (String)"yyyy-MM-dd HH:mm:ss"));	
        a3.put("javaArgs", a5.getInputArguments());	
        a3.put("javaHome", System.getProperty("java.home"));	
        a3.put("javaVendor", System.getProperty("java.vm.vendor"));	
        a3.put("javaVersion", new StringBuilder().insert(0, System.getProperty("java.runtime.version")).append(" / ").append(System.getProperty("java.vm.version")).toString());	
        a3.put("javaName", System.getProperty("java.vm.name"));	
        a3.put("osArch", a.getArch());	
        a3.put("osProcessors", a.getAvailableProcessors());	
        a3.put("osVersion", a.getVersion());	
        a3.put("osName", a.getName());	
        try {	
            a22 = SpringUtils.getBean(Environment.class);	
            a3.put("activeProfiles", a22.getActiveProfiles());	
            linkedHashMap = a3;	
        }	
        catch (IllegalStateException a22) {	
            linkedHashMap = a3;	
        }	
        a3.put("userfilesDir", Global.getUserfilesBaseDir(""));	
        a3.put("logPath", System.getProperty("logPath"));	
        linkedHashMap.put("userDir", System.getProperty("user.dir"));	
        if (Global.getPropertyToBoolean("demoMode", "false").booleanValue()) {	
            a22 = "*** 演示模式，不展示数据 ***";	
            LinkedHashMap linkedHashMap3 = a3;	
            linkedHashMap3.put("javaHome", a22);	
            a3.put("hostAddress", a22);	
            a3.put("userfilesDir", a22);	
            a3.put("logPath", a22);	
            a3.put("userDir", a22);	
            linkedHashMap3.put("javaArgs", ListUtils.newArrayList((Object[])new String[]{a22}));	
        }	
        return a3;	
    }	
	
    public Map<String, Object> getJvmInfo() {	
        void a;	
        LinkedHashMap a2 = MapUtils.newLinkedHashMap();	
        Runtime runtime = Runtime.getRuntime();	
        double a3 = runtime.totalMemory();	
        double a4 = runtime.freeMemory();	
        double d = a3 - a4;	
        LinkedHashMap linkedHashMap = a2;	
        long a5 = Math.round((double)(a / a3 * 100.0));	
        MemoryMXBean a6 = ManagementFactory.getMemoryMXBean();	
        MemoryUsage a7 = a6.getHeapMemoryUsage();	
        MemoryUsage a8 = a6.getNonHeapMemoryUsage();	
        a2.put("nonHeapUsed", ByteUtils.formatByteSize((long)a8.getUsed()));	
        a2.put("nonHeapMax", ByteUtils.formatByteSize((long)a8.getMax()));	
        a2.put("nonHeapInit", ByteUtils.formatByteSize((long)a8.getInit()));	
        a2.put("heapAvailable", ByteUtils.formatByteSize((long)(a7.getMax() - a7.getUsed())));	
        a2.put("heapUsed", ByteUtils.formatByteSize((long)a7.getUsed()));	
        a2.put("heapMax", ByteUtils.formatByteSize((long)a7.getMax()));	
        a2.put("heapInit", ByteUtils.formatByteSize((long)a7.getInit()));	
        a2.put("usedPerc", a5 + "%");	
        linkedHashMap.put("used", ByteUtils.formatByteSize((long)((long)a)));	
        a2.put("free", ByteUtils.formatByteSize((long)((long)a4)));	
        a2.put("total", ByteUtils.formatByteSize((long)((long)a3)));	
        linkedHashMap.put("nonHeapAvailable", ByteUtils.formatByteSize((long)(a8.getMax() == -1L ? -1L : a8.getMax() - a8.getUsed())));	
        return a2;	
    }	
	
    public Map<String, Object> getMemInfo() {	
        LinkedHashMap a = MapUtils.newLinkedHashMap();	
        try {	
            void a2;	
            Mem a3 = c.a.getMem();	
            double d = a3.getTotal();	
            double a4 = a3.getFree();	
            LinkedHashMap linkedHashMap = a;	
            double a5 = a3.getUsed();	
            long a6 = Math.round(a5 / a2 * 100.0);	
            linkedHashMap.put("used", ByteUtils.formatByteSize((long)((long)a5)));	
            a.put("free", ByteUtils.formatByteSize((long)((long)a4)));	
            a.put("total", ByteUtils.formatByteSize((long)((long)d)));	
            linkedHashMap.put("usedPerc", a6 + "%");	
            return a;	
        }	
        catch (SigarException a7) {	
            a7.printStackTrace();	
            return a;	
        }	
    }	
	
    public void gc() {	
        System.gc();	
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
}	
	
