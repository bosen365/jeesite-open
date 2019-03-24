/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.beetl.e;	
	
import com.jeesite.autoconfigure.core.CacheAutoConfiguration;	
import java.io.File;	
import java.io.IOException;	
import java.io.Reader;	
import org.beetl.core.Resource;	
import org.beetl.core.ResourceLoader;	
import org.hyperic.sigar.DirUsage;	
	
public class A	
extends Resource {	
    private static String l = "UTF-8";	
    private static String[] h;	
    private File J;	
    private String c;	
    private long ALLATORIxDEMO;	
	
    @Override	
    public boolean isModified() {	
        if (this.J != null) {	
            return this.J.lastModified() != this.ALLATORIxDEMO;	
        }	
        return false;	
    }	
	
    static {	
        String[] arrstring = new String[4];	
        arrstring[0] = "/views/error/";	
        arrstring[1] = "/views/functions/";	
        arrstring[2] = "/views/htmltags/";	
        arrstring[3] = "/views/tagsview/";	
        h = arrstring;	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    public A(String string, String string2, ResourceLoader resourceLoader) {	
        void key;	
        void root;	
        A a = this;	
        super(string2, resourceLoader);	
        a.c = null;	
        a.J = null;	
        this.ALLATORIxDEMO = 0L;	
        A a2 = this;	
        a2.c = (String)root + (String)key;	
    }	
	
    @Override	
    public String getId() {	
        return this.id;	
    }	
	
    /*	
     * Exception decompiling	
     */	
    public static void main(String[] var0) throws IOException {	
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.	
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 7[CATCHBLOCK]	
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
	
    /*	
     * Exception decompiling	
     */	
    @Override	
    public Reader openReader() {	
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.	
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 4[CATCHBLOCK]	
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
}	
	
