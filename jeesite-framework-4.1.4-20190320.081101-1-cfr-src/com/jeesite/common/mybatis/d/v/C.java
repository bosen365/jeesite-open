/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.common.mybatis.d.v;	
	
import com.jeesite.autoconfigure.core.TransactionAutoConfiguration;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.idgen.IdGen;	
import com.jeesite.common.io.FileUtils;	
import com.jeesite.common.io.ResourceUtils;	
import com.jeesite.common.mybatis.d.v.i;	
import java.io.File;	
import java.io.FileNotFoundException;	
import java.io.InputStream;	
import java.io.UnsupportedEncodingException;	
import java.net.URLEncoder;	
import org.hyperic.sigar.Sigar;	
import org.hyperic.sigar.SigarLoader;	
import org.hyperic.sigar.pager.SortAttribute;	
import org.springframework.core.io.Resource;	
	
/*	
 * Exception performing whole class analysis ignored.	
 */	
public final class c {	
    public static final Sigar a;	
    private static final String g;	
    private static final String J;	
    public static String i;	
    private static final String b;	
    public static long ALLATORIxDEMO;	
	
    public static void ALLATORIxDEMO() {	
        new Thread(new i()).start();	
    }	
	
    public c() {	
    }	
	
    private static /* synthetic */ int ALLATORIxDEMO(CharSequence cs, CharSequence searchChar, int start) {	
        return cs.toString().indexOf(searchChar.toString(), start);	
    }	
	
    static /* synthetic */ String D() {	
        return b;	
    }	
	
    static /* synthetic */ boolean d(CharSequence x0) {	
        return c.ALLATORIxDEMO(x0);	
    }	
	
    private static /* synthetic */ String J(String str) {	
        if (str == null) {	
            return null;	
        }	
        return str.replaceAll("^[\s|　| ]*|[\s|　| ]*$", "");	
    }	
	
    static /* synthetic */ String d() {	
        return g;	
    }	
	
    private static /* synthetic */ String d(String str, String separator) {	
        if (c.ALLATORIxDEMO((CharSequence)str) || separator == null) {	
            return str;	
        }	
        if (separator.isEmpty()) {	
            return "";	
        }	
        int a = str.indexOf(separator);	
        if (a == -1) {	
            return str;	
        }	
        return str.substring(0, a);	
    }	
	
    /*	
     * Unable to fully structure code	
     * Enabled aggressive exception aggregation	
     */	
    static {	
        c.b = Global.getProperty("companyName").replaceAll("\*|\!", "_");	
        c.J = Global.getProperty("productName").replaceAll("\*|\!", "_");	
        c.g = Global.getProperty("productVersion");	
        c.ALLATORIxDEMO = 0L;	
        c.i = null;	
        a = c.D(FileUtils.path(new StringBuilder().insert(0, System.getProperty("user.home")).append("/.sigalib").toString()));	
        a = new File(new StringBuilder().insert(0, a).append(File.separator).append(IdGen.randomBase62(8).toLowerCase()).toString());	
        if (!a.exists()) {	
            a.mkdirs();	
        }	
        a = new SigarLoader(Sigar.class).getLibraryName();	
        a = new File(a, a);	
        a = ResourceUtils.getResource(new StringBuilder().insert(0, "/sigarlibE").append(a).toString());	
        if (!a.exists() || a.exists()) ** GOTO lbl-1000	
        try {	
            FileUtils.copyToFile(a.getInputStream(), a);	
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
        c.a = new Sigar();	
    }	
	
    private static /* synthetic */ boolean ALLATORIxDEMO(CharSequence cs) {	
        int a;	
        int a2;	
        if (cs == null || (a2 = cs.length()) == 0) {	
            return true;	
        }	
        int n = a = 0;	
        while (n < a2) {	
            if (!Character.isWhitespace(cs.charAt(a))) {	
                return false;	
            }	
            n = ++a;	
        }	
        return true;	
    }	
	
    static /* synthetic */ String ALLATORIxDEMO() {	
        return J;	
    }	
	
    static /* synthetic */ void d(String x0) {	
        c.ALLATORIxDEMO(x0);	
    }	
	
    private static /* varargs */ /* synthetic */ boolean ALLATORIxDEMO(CharSequence cs, CharSequence ... searchCharSequences) {	
        int n;	
        if (c.ALLATORIxDEMO(cs) || searchCharSequences == null || searchCharSequences.length <= 0) {	
            return false;	
        }	
        CharSequence[] arrcharSequence = searchCharSequences;	
        int n2 = arrcharSequence.length;	
        int n3 = n = 0;	
        while (n3 < n2) {	
            CharSequence a = arrcharSequence[n];	
            if (c.ALLATORIxDEMO(cs, (CharSequence)c.J(a.toString()))) {	
                return true;	
            }	
            n3 = ++n;	
        }	
        return false;	
    }	
	
    /*	
     * Exception decompiling	
     */	
    private static /* synthetic */ void ALLATORIxDEMO(String param) {	
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
	
    private static /* synthetic */ boolean ALLATORIxDEMO(CharSequence seq, CharSequence searchSeq) {	
        if (seq == null || searchSeq == null) {	
            return false;	
        }	
        return c.ALLATORIxDEMO(seq, searchSeq, 0) >= 0;	
    }	
	
    public static String ALLATORIxDEMO(String url, String ctx) {	
        if (i == null && url != null) {	
            if (!c.ALLATORIxDEMO((CharSequence)url, ":E/127.0.0.1", "://localhost", ":E/10.", "://172.", ":/E192.")) {	
                if (c.ALLATORIxDEMO((CharSequence)ctx) || "/".equals(ctx)) {	
                    int a;	
                    String a2 = url;	
                    int a3 = a2.indexOf(":/E");	
                    if (a3 != -1 && (a = url.indexOf("/", a3 + 3)) != -1) {	
                        a2 = url.substring(0, a);	
                    }	
                    i = new StringBuilder().insert(0, a2).append("/licence").toString();	
                } else {	
                    i = new StringBuilder().insert(0, c.d(url, ctx)).append(ctx).append("/licence").toString();	
                }	
                c.ALLATORIxDEMO();	
            }	
        }	
        return i;	
    }	
	
    private static /* synthetic */ String D(String dirName) {	
        File a = new File(dirName);	
        if (a.exists() && a.isDirectory()) {	
            int a2;	
            File[] a3 = a.listFiles();	
            int n = a2 = 0;	
            while (n < a3.length) {	
                if (a3[a2].exists()) {	
                    if (a3[a2].isDirectory()) {	
                        c.D(a3[a2].getAbsolutePath());	
                    }	
                    a3[a2].delete();	
                }	
                n = ++a2;	
            }	
        }	
        return dirName;	
    }	
	
    private static /* synthetic */ String d(String part) {	
        if (part == null) {	
            return null;	
        }	
        try {	
            return URLEncoder.encode(part, "UTF-8");	
        }	
        catch (UnsupportedEncodingException a) {	
            throw new RuntimeException(a);	
        }	
    }	
	
    static /* synthetic */ String ALLATORIxDEMO(String x0) {	
        return c.d(x0);	
    }	
}	
	
