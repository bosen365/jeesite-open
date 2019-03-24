/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.l;	
	
import com.jeesite.common.l.m;	
import com.jeesite.common.mybatis.mapper.query.QueryColumn;	
import com.jeesite.common.ueditor.define.BaseState;	
import javax.servlet.http.HttpServletRequest;	
import org.hyperic.sigar.cmd.EventLogTail;	
	
/*	
 * Duplicate member names - consider using --renamedupmembers true	
 */	
public class h {	
    private String l;	
    private m h;	
    private String J;	
    private HttpServletRequest c;	
    private String ALLATORIxDEMO;	
	
    /*	
     * Exception decompiling	
     */	
    public String e() {	
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.	
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [6[DOLOOP]], but top level block is 1[CASE]	
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:432)	
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:484)	
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:607)	
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:696)	
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:184)	
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:129)	
        // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:96)	
        // org.benf.cfr.reader.entities.Method.analyse(Method.java:397)	
        // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:890)	
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:792)	
        // org.benf.cfr.reader.Driver.doJar(Driver.java:128)	
        // org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:63)	
        // org.benf.cfr.reader.Main.main(Main.java:48)	
        throw new IllegalStateException("Decompilation failed");	
    }	
	
    public int ALLATORIxDEMO() {	
        String a = this.c.getParameter("tart");	
        try {	
            return Integer.parseInt(a);	
        }	
        catch (Exception a2) {	
            return 0;	
        }	
    }	
	
    public boolean ALLATORIxDEMO(String name) {	
        return name.matches("^[a-zA-Z_]+[\w0-9_]*$");	
    }	
	
    public String ALLATORIxDEMO() {	
        String a = this.c.getParameter("callback");	
        if (a != null) {	
            if (!this.ALLATORIxDEMO(a)) {	
                return new BaseState(false, 401).toJSONString();	
            }	
            return new StringBuilder().insert(0, a).append("(").append(this.e()).append(");").toString();	
        }	
        return this.e();	
    }	
	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = 4 << 3 ^ 4;	
        (2 ^ 5) << 4 ^ (2 ^ 5);	
        int n4 = n2;	
        int n5 = (3 ^ 5) << 3 ^ 4;	
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
	
    /*	
     * WARNING - void declaration	
     */	
    public h(HttpServletRequest httpServletRequest, String string, String string2) {	
        void request;	
        h h2 = this;	
        h h3 = this;	
        h h4 = this;	
        this.c = null;	
        h4.l = null;	
        h4.ALLATORIxDEMO = null;	
        h3.J = null;	
        h3.h = null;	
        h2.c = httpServletRequest;	
        h2.l = string;	
        this.J = string2;	
        this.ALLATORIxDEMO = httpServletRequest.getContextPath();	
        this.h = m.ALLATORIxDEMO(this.l, this.ALLATORIxDEMO, request.getRequestURI());	
    }	
	
    public h(HttpServletRequest request, String rootPath) {	
        HttpServletRequest httpServletRequest = request;	
        this(httpServletRequest, rootPath, httpServletRequest.getParameter("action"));	
    }	
}	
	
