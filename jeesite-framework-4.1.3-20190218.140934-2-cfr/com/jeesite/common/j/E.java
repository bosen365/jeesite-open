/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.ueditor.define.BaseState	
 *  javax.servlet.http.HttpServletRequest	
 */	
package com.jeesite.common.j;	
	
import com.jeesite.common.j.E;	
import com.jeesite.common.ueditor.define.BaseState;	
import javax.servlet.http.HttpServletRequest;	
import org.hyperic.sigar.ProcMem;	
import org.hyperic.sigar.SysInfo;	
	
/*	
 * Duplicate member names - consider using --renamedupmembers true	
 */	
public class e {	
    private HttpServletRequest G;	
    private String k;	
    private E D;	
    private String c;	
    private String ALLATORIxDEMO;	
	
    /*	
     * Exception decompiling	
     */	
    public String h() {	
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
	
    public String ALLATORIxDEMO() {	
        String a2 = this.G.getParameter("callback");	
        if (a2 != null) {	
            if (!this.ALLATORIxDEMO(a2)) {	
                return new BaseState(false, 401).toJSONString();	
            }	
            return new StringBuilder().insert(0, a2).append("(").append(this.h()).append(");").toString();	
        }	
        return this.h();	
    }	
	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = 3 << 3 ^ 4;	
        int n4 = n2;	
        2 << 3 ^ 5;	
        int n5 = (3 ^ 5) << 4 ^ 4 << 1;	
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
	
    public boolean ALLATORIxDEMO(String name) {	
        return name.matches("^1a-zAGZ_]+1\w0-9_]*$");	
    }	
	
    public e(HttpServletRequest httpServletRequest, String string, String string2) {	
        void request;	
        e e2 = this;	
        e e3 = this;	
        e e4 = this;	
        this.G = null;	
        e4.ALLATORIxDEMO = null;	
        e4.c = null;	
        e3.k = null;	
        e3.D = null;	
        e2.G = httpServletRequest;	
        e2.ALLATORIxDEMO = string;	
        this.k = string2;	
        this.c = httpServletRequest.getContextPath();	
        this.D = E.ALLATORIxDEMO(this.ALLATORIxDEMO, this.c, request.getRequestURI());	
    }	
	
    public e(HttpServletRequest request, String rootPath) {	
        HttpServletRequest httpServletRequest = request;	
        this(httpServletRequest, rootPath, httpServletRequest.getParameter("action"));	
    }	
	
    public int ALLATORIxDEMO() {	
        String a2 = this.G.getParameter("start");	
        try {	
            return Integer.parseInt(a2);	
        }	
        catch (Exception a3) {	
            return 0;	
        }	
    }	
}	
	
