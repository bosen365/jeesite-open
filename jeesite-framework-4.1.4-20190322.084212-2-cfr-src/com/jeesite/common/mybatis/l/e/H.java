/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.mybatis.l.e;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.idgen.IdGen;	
import com.jeesite.common.io.FileUtils;	
import com.jeesite.common.io.ResourceUtils;	
import com.jeesite.common.mybatis.l.e.m;	
import com.jeesite.common.mybatis.mapper.provider.InsertSqlProvider;	
import java.io.File;	
import java.io.FileNotFoundException;	
import java.io.InputStream;	
import java.io.UnsupportedEncodingException;	
import java.net.URLEncoder;	
import org.hyperic.sigar.DirUsage;	
import org.hyperic.sigar.Sigar;	
import org.hyperic.sigar.SigarLoader;	
import org.springframework.core.io.Resource;	
	
/*	
 * Exception performing whole class analysis ignored.	
 */	
public final class h {	
    public static final Sigar a;	
    public static long l;	
    private static final String h;	
    private static final String J;	
    public static String c;	
    private static final String ALLATORIxDEMO;	
	
    static /* synthetic */ String j() {	
        return J;	
    }	
	
    /*	
     * Unable to fully structure code	
     * Enabled aggressive exception aggregation	
     */	
    static {	
        h.J = Global.getProperty("companyName").replaceAll("\\*|\\!", "_");	
        h.ALLATORIxDEMO = Global.getProperty("productName").replaceAll("\\*|\\!", "_");	
        h.h = Global.getProperty("productVersion");	
        h.l = 0L;	
        h.c = null;	
        a = h.ALLATORIxDEMO(FileUtils.path(new StringBuilder().insert(0, System.getProperty("user.home")).append("/.sigarlib").toString()));	
        a = new File(new StringBuilder().insert(0, a).append(File.separator).append(IdGen.randomBase62(8).toLowerCase()).toString());	
        if (!a.exists()) {	
            a.mkdirs();	
        }	
        a = new SigarLoader(Sigar.class).getLibraryName();	
        a = new File(a, a);	
        a = ResourceUtils.getResource(new StringBuilder().insert(0, "/sigarlib/").append(a).toString());	
        if (!a.exists() || a.exists()) ** GOTO lbl-1000	
        try {	
            FileUtils.copyToFile(a.getInputStream(), a);	
            v0 = a;	
            ** GOTO lbl23	
        }	
        catch (FileNotFoundException var5_5) {	
            try lbl-1000: // 2 sources:	
            {	
                v0 = a;	
lbl23: // 2 sources:	
                if (v0.exists()) {	
                    System.load(a.getAbsolutePath());	
                }	
            }	
            catch (Throwable a) {	
                // empty catch block	
            }	
        }	
        h.a = new Sigar();	
    }	
	
    private static /* synthetic */ boolean e(CharSequence cs) {	
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
	
    public static String e(String url, String ctx) {	
        if (c == null && url != null) {	
            CharSequence[] arrcharSequence = new CharSequence[5];	
            arrcharSequence[0] = "://127.0.0.1";	
            arrcharSequence[1] = "://localhost";	
            arrcharSequence[2] = "://10.";	
            arrcharSequence[3] = "://172.";	
            arrcharSequence[4] = "://192.";	
            if (!h.ALLATORIxDEMO((CharSequence)url, arrcharSequence)) {	
                if (h.e((CharSequence)ctx) || "/".equals(ctx)) {	
                    int a;	
                    String a2 = url;	
                    int a3 = a2.indexOf("://");	
                    if (a3 != -1 && (a = url.indexOf("/", a3 + 3)) != -1) {	
                        a2 = url.substring(0, a);	
                    }	
                    c = new StringBuilder().insert(0, a2).append("/licence").toString();	
                } else {	
                    c = new StringBuilder().insert(0, h.ALLATORIxDEMO(url, ctx)).append(ctx).append("/licence").toString();	
                }	
                h.ALLATORIxDEMO();	
            }	
        }	
        return c;	
    }	
	
    static /* synthetic */ String H(String x0) {	
        return h.j(x0);	
    }	
	
    private static /* synthetic */ String j(String part) {	
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
	
    private static /* synthetic */ boolean ALLATORIxDEMO(CharSequence seq, CharSequence searchSeq) {	
        if (seq == null || searchSeq == null) {	
            return false;	
        }	
        return h.ALLATORIxDEMO(seq, searchSeq, 0) >= 0;	
    }	
	
    private static /* synthetic */ String e(String str) {	
        if (str == null) {	
            return null;	
        }	
        return str.replaceAll("^[\\s|　| ]*|[\\s|　| ]*$", "");	
    }	
	
    /*	
     * Exception decompiling	
     */	
    private static /* synthetic */ void e(String param) {	
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.	
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 2[TRYBLOCK]	
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:432)	
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:484)	
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:607)	
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:696)	
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:184)	
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:129)	
        // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:96)	
        // org.benf.cfr.reader.entities.Method.analyse(Method.java:397)	
        // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:885)	
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:792)	
        // org.benf.cfr.reader.Driver.doJar(Driver.java:128)	
        // org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:63)	
        // org.benf.cfr.reader.Main.main(Main.java:48)	
        throw new IllegalStateException("Decompilation failed");	
    }	
	
    static /* synthetic */ boolean ALLATORIxDEMO(CharSequence x0) {	
        return h.e(x0);	
    }	
	
    public static void ALLATORIxDEMO() {	
        new Thread(new m()).start();	
    }	
	
    public h() {	
    }	
	
    private static /* synthetic */ boolean ALLATORIxDEMO(CharSequence cs, CharSequence ... searchCharSequences) {	
        int n;	
        if (h.e(cs) || searchCharSequences == null || searchCharSequences.length <= 0) {	
            return false;	
        }	
        CharSequence[] arrcharSequence = searchCharSequences;	
        int n2 = arrcharSequence.length;	
        int n3 = n = 0;	
        while (n3 < n2) {	
            CharSequence a = arrcharSequence[n];	
            if (h.ALLATORIxDEMO(cs, (CharSequence)h.e(a.toString()))) {	
                return true;	
            }	
            n3 = ++n;	
        }	
        return false;	
    }	
	
    static /* synthetic */ void ALLATORIxDEMO(String x0) {	
        h.e(x0);	
    }	
	
    private static /* synthetic */ String ALLATORIxDEMO(String str, String separator) {	
        if (h.e((CharSequence)str) || separator == null) {	
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
	
    static /* synthetic */ String e() {	
        return ALLATORIxDEMO;	
    }	
	
    private static /* synthetic */ String ALLATORIxDEMO(String dirName) {	
        File a = new File(dirName);	
        if (a.exists() && a.isDirectory()) {	
            int a2;	
            File[] a3 = a.listFiles();	
            int n = a2 = 0;	
            while (n < a3.length) {	
                if (a3[a2].exists()) {	
                    if (a3[a2].isDirectory()) {	
                        h.ALLATORIxDEMO(a3[a2].getAbsolutePath());	
                    }	
                    a3[a2].delete();	
                }	
                n = ++a2;	
            }	
        }	
        return dirName;	
    }	
	
    private static /* synthetic */ int ALLATORIxDEMO(CharSequence cs, CharSequence searchChar, int start) {	
        return cs.toString().indexOf(searchChar.toString(), start);	
    }	
	
    static /* synthetic */ String ALLATORIxDEMO() {	
        return h;	
    }	
}	
	
