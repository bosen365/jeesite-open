/*	
 * Decompiled with CFR 0.139.	
 */	
package com.jeesite.common.mybatis.annotation;	
	
import com.jeesite.common.mybatis.annotation.Column;	
import java.lang.annotation.Annotation;	
import java.lang.annotation.Documented;	
import java.lang.annotation.ElementType;	
import java.lang.annotation.Retention;	
import java.lang.annotation.RetentionPolicy;	
import java.lang.annotation.Target;	
import org.hyperic.sigar.ProcTime;	
import org.hyperic.sigar.shell.ShellCommandInitException;	
	
@Retention(value=RetentionPolicy.RUNTIME)	
@Target(value={ElementType.TYPE})	
@Documented	
public @interface JoinTable {	
    public String alias();	
	
    public Type type() default Type.JOIN;	
	
    public Class<?> entity();	
	
    public Column[] columns() default {};	
	
    public String on();	
	
    public String attrName() default "";	
	
    public static enum Type {	
        JOIN("JOIN"),	
        LEFT_JOIN("LEFT JOIN"),	
        RIGHT_JOIN("RIGHT JOIN");	
        	
        private final String value;	
	
        /*	
         * Exception decompiling	
         */	
        private /* synthetic */ Type(String varnull) {	
            // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.	
            // java.lang.IllegalStateException	
            // org.benf.cfr.reader.bytecode.analysis.variables.VariableFactory.localVariable(VariableFactory.java:53)	
            // org.benf.cfr.reader.bytecode.analysis.opgraph.Op02WithProcessedDataAndRefs.mkRetrieve(Op02WithProcessedDataAndRefs.java:911)	
            // org.benf.cfr.reader.bytecode.analysis.opgraph.Op02WithProcessedDataAndRefs.createStatement(Op02WithProcessedDataAndRefs.java:959)	
            // org.benf.cfr.reader.bytecode.analysis.opgraph.Op02WithProcessedDataAndRefs.access$100(Op02WithProcessedDataAndRefs.java:56)	
            // org.benf.cfr.reader.bytecode.analysis.opgraph.Op02WithProcessedDataAndRefs$11.call(Op02WithProcessedDataAndRefs.java:2010)	
            // org.benf.cfr.reader.bytecode.analysis.opgraph.Op02WithProcessedDataAndRefs$11.call(Op02WithProcessedDataAndRefs.java:2007)	
            // org.benf.cfr.reader.util.graph.AbstractGraphVisitorFI.process(AbstractGraphVisitorFI.java:60)	
            // org.benf.cfr.reader.bytecode.analysis.opgraph.Op02WithProcessedDataAndRefs.convertToOp03List(Op02WithProcessedDataAndRefs.java:2019)	
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:340)	
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:182)	
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:127)	
            // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:96)	
            // org.benf.cfr.reader.entities.Method.analyse(Method.java:396)	
            // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:885)	
            // org.benf.cfr.reader.entities.ClassFile.analyseInnerClassesPass1(ClassFile.java:773)	
            // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:870)	
            // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:792)	
            // org.benf.cfr.reader.Driver.doJar(Driver.java:128)	
            // org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:63)	
            // org.benf.cfr.reader.Main.main(Main.java:48)	
            throw new IllegalStateException("Decompilation failed");	
        }	
	
        public String value() {	
            return this.value;	
        }	
    }	
	
}	
	
