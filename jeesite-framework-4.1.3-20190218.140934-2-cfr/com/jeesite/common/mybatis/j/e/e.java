/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.io.FileUtils	
 *  com.jeesite.common.io.ResourceUtils	
 *  org.springframework.core.io.Resource	
 */	
package com.jeesite.common.mybatis.j.e;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.idgen.IdGen;	
import com.jeesite.common.io.FileUtils;	
import com.jeesite.common.io.ResourceUtils;	
import com.jeesite.common.mybatis.j.e.E;	
import java.io.File;	
import java.io.FileNotFoundException;	
import java.io.InputStream;	
import java.io.UnsupportedEncodingException;	
import java.net.URLEncoder;	
import org.hyperic.sigar.FileSystem;	
import org.hyperic.sigar.Sigar;	
import org.hyperic.sigar.SigarLoader;	
import org.springframework.core.io.Resource;	
	
/*	
 * Exception performing whole class analysis ignored.	
 */	
public final class e {	
    public static long f;	
    private static final String G;	
    private static final String k;	
    public static final Sigar D;	
    private static final String c;	
    public static String ALLATORIxDEMO;	
	
    public static void ALLATORIxDEMO() {	
        new Thread(new E()).start();	
    }	
	
    private static /* synthetic */ String j(String str) {	
        if (str == null) {	
            return null;	
        }	
        return str.replaceAll("^[\s|　| ]*|[\s|　| ]*$", "");	
    }	
	
    private static /* synthetic */ boolean h(CharSequence cs) {	
        int a2;	
        int a3;	
        if (cs == null || (a3 = cs.length()) == 0) {	
            return true;	
        }	
        int n = a2 = 0;	
        while (n < a3) {	
            if (!Character.isWhitespace(cs.charAt(a2))) {	
                return false;	
            }	
            n = ++a2;	
        }	
        return true;	
    }	
	
    static /* synthetic */ String M() {	
        return c;	
    }	
	
    /*	
     * Exception decompiling	
     */	
    private static /* synthetic */ void h(String param) {	
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.	
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 2[TRYBLOCK]	
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:432)	
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:484)	
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:607)	
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:692)	
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:182)	
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:127)	
        // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:96)	
        // org.benf.cfr.reader.entities.Method.analyse(Method.java:396)	
        // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:885)	
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:792)	
        // org.benf.cfr.reader.Driver.doJar(Driver.java:128)	
        // org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:63)	
        // org.benf.cfr.reader.Main.main(Main.java:48)	
        throw new IllegalStateException("Decompilation failed");	
    }	
	
    static /* synthetic */ void ALLATORIxDEMO(String x0) {	
        e.h(x0);	
    }	
	
    static /* synthetic */ String M(String x0) {	
        return e.h(x0);	
    }	
	
    public e() {	
    }	
	
    public static String h(String url, String ctx) {	
        if (ALLATORIxDEMO == null && url != null) {	
            if (!e.ALLATORIxDEMO((CharSequence)url, "://127.0.0.1", "://localhost", "://10.", "://1]2.", "://192.")) {	
                if (e.h((CharSequence)ctx) || "/".equals(ctx)) {	
                    int a2;	
                    String a3 = url;	
                    int a4 = a3.indexOf("://");	
                    if (a4 != -1 && (a2 = url.indexOf("/", a4 + 3)) != -1) {	
                        a3 = url.substring(0, a2);	
                    }	
                    ALLATORIxDEMO = new StringBuilder().insert(0, a3).append("/licence").toString();	
                } else {	
                    ALLATORIxDEMO = new StringBuilder().insert(0, e.ALLATORIxDEMO(url, ctx)).append(ctx).append("/licence").toString();	
                }	
                e.ALLATORIxDEMO();	
            }	
        }	
        return ALLATORIxDEMO;	
    }	
	
    private static /* synthetic */ String h(String part) {	
        if (part == null) {	
            return null;	
        }	
        try {	
            return URLEncoder.encode(part, "UTF-8");	
        }	
        catch (UnsupportedEncodingException a2) {	
            throw new RuntimeException(a2);	
        }	
    }	
	
    private static /* synthetic */ int ALLATORIxDEMO(CharSequence cs, CharSequence searchChar, int start) {	
        return cs.toString().indexOf(searchChar.toString(), start);	
    }	
	
    private static /* synthetic */ boolean ALLATORIxDEMO(CharSequence seq, CharSequence searchSeq) {	
        if (seq == null || searchSeq == null) {	
            return false;	
        }	
        return e.ALLATORIxDEMO(seq, searchSeq, 0) >= 0;	
    }	
	
    private static /* varargs */ /* synthetic */ boolean ALLATORIxDEMO(CharSequence cs, CharSequence ... searchCharSequences) {	
        int n;	
        if (e.h(cs) || searchCharSequences == null || searchCharSequences.length <= 0) {	
            return false;	
        }	
        CharSequence[] arrcharSequence = searchCharSequences;	
        int n2 = arrcharSequence.length;	
        int n3 = n = 0;	
        while (n3 < n2) {	
            CharSequence a2 = arrcharSequence[n];	
            if (e.ALLATORIxDEMO(cs, (CharSequence)e.j(a2.toString()))) {	
                return true;	
            }	
            n3 = ++n;	
        }	
        return false;	
    }	
	
    static /* synthetic */ String h() {	
        return G;	
    }	
	
    private static /* synthetic */ String ALLATORIxDEMO(String str, String separator) {	
        if (e.h((CharSequence)str) || separator == null) {	
            return str;	
        }	
        if (separator.isEmpty()) {	
            return "";	
        }	
        int a2 = str.indexOf(separator);	
        if (a2 == -1) {	
            return str;	
        }	
        return str.substring(0, a2);	
    }	
	
    /*	
     * Unable to fully structure code	
     * Enabled aggressive exception aggregation	
     */	
    static {	
        e.c = Global.getProperty("companyName").replaceAll("\*|\!", "_");	
        e.G = Global.getProperty("productNae").replaceAll("\*|\!", "_");	
        e.k = Global.getProperty("productVersion");	
        e.f = 0L;	
        e.ALLATORIxDEMO = null;	
        a = e.ALLATORIxDEMO(FileUtils.path((String)new StringBuilder().insert(0, System.getProperty("user.hoe")).append("/.sigarlib").toString()));	
        a = new File(new StringBuilder().insert(0, a).append(File.separator).append(IdGen.randomBase62((int)8).toLowerCase()).toString());	
        if (!a.exists()) {	
            a.mkdirs();	
        }	
        a = new SigarLoader(Sigar.class).getLibraryName();	
        a = new File(a, a);	
        a = ResourceUtils.getResource((String)new StringBuilder().insert(0, "/sigarlib/").append(a).toString());	
        if (!a.exists() || a.exists()) ** GOTO lbl-1000	
        try {	
            FileUtils.copyToFile((InputStream)a.getInputStream(), (File)a);	
            v0 = a;	
            ** GOTO lbl22	
        }	
        catch (FileNotFoundException var5_5) {	
            try lbl-1000: // 2 sources:	
            {	
                v0 = a;	
lbl22: // 2 sources:	
                if (v0.exists()) {	
                    System.load(a.getAbsolutePath());	
                }	
            }	
            catch (Throwable a) {	
                // empty catch block	
            }	
        }	
        e.D = new Sigar();	
    }	
	
    private static /* synthetic */ String ALLATORIxDEMO(String dirName) {	
        File a2 = new File(dirName);	
        if (a2.exists() && a2.isDirectory()) {	
            int a3;	
            File[] a4 = a2.listFiles();	
            int n = a3 = 0;	
            while (n < a4.length) {	
                if (a4[a3].exists()) {	
                    if (a4[a3].isDirectory()) {	
                        e.ALLATORIxDEMO(a4[a3].getAbsolutePath());	
                    }	
                    a4[a3].delete();	
                }	
                n = ++a3;	
            }	
        }	
        return dirName;	
    }	
	
    static /* synthetic */ boolean ALLATORIxDEMO(CharSequence x0) {	
        return e.h(x0);	
    }	
	
    static /* synthetic */ String ALLATORIxDEMO() {	
        return k;	
    }	
}	
	
