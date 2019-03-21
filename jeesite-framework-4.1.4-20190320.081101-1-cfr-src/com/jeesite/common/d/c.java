/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.common.d;	
	
import com.jeesite.common.cache.JedisUtils;	
import com.jeesite.common.d.i;	
import com.jeesite.common.ueditor.define.BaseState;	
import javax.servlet.http.HttpServletRequest;	
import org.hyperic.sigar.FileSystemUsage;	
	
/*	
 * Duplicate member names - consider using --renamedupmembers true	
 */	
public class c {	
    private String g;	
    private i J;	
    private String i;	
    private HttpServletRequest b;	
    private String ALLATORIxDEMO;	
	
    public boolean ALLATORIxDEMO(String name) {	
        return name.matches("^[a-zA-Z_]+[\w0-9_]*$");	
    }	
	
    /*	
     * Exception decompiling	
     */	
    public String d() {	
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.	
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [6[DOLOOP]], but top level block is 1[CASE]	
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:432)	
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:484)	
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:607)	
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:692)	
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
	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = 5 << 4 ^ 4 << 1;	
        int n4 = n2;	
        4 << 3 ^ 3;	
        int n5 = 5 << 4 ^ (3 ^ 5) << 1;	
        while (n4 >= 0) {	
            int n6 = n2--;	
            arrc[n6] = (char)(s.charAt(n6) ^ n5);	
            if (n2 < 0) break;	
            int n7 = n2--;	
            arrc[n7] = (char)(s.charAt(n7) ^ n3);	
            n4 = n2;	
        }	
        return new String(arrc);	
    }	
	
    public int ALLATORIxDEMO() {	
        String a = this.b.getParameter("start");	
        try {	
            return Integer.parseInt(a);	
        }	
        catch (Exception a2) {	
            return 0;	
        }	
    }	
	
    public String ALLATORIxDEMO() {	
        String a = this.b.getParameter("callback");	
        if (a != null) {	
            if (!this.ALLATORIxDEMO(a)) {	
                return new BaseState(false, 401).toJSONString();	
            }	
            return new StringBuilder().insert(0, a).append("(").append(this.d()).append(");").toString();	
        }	
        return this.d();	
    }	
	
    public c(HttpServletRequest request, String rootPath) {	
        HttpServletRequest httpServletRequest = request;	
        this(httpServletRequest, rootPath, httpServletRequest.getParameter("action"));	
    }	
	
    public c(HttpServletRequest httpServletRequest, String string, String string2) {	
        void request;	
        c c2 = this;	
        c c3 = this;	
        c c4 = this;	
        this.b = null;	
        c4.i = null;	
        c4.ALLATORIxDEMO = null;	
        c3.g = null;	
        c3.J = null;	
        c2.b = httpServletRequest;	
        c2.i = string;	
        this.g = string2;	
        this.ALLATORIxDEMO = httpServletRequest.getContextPath();	
        this.J = i.ALLATORIxDEMO(this.i, this.ALLATORIxDEMO, request.getRequestURI());	
    }	
}	
	
