/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.mybatis.annotation;	
	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.shiro.l.k;	
import java.lang.annotation.Annotation;	
import java.lang.annotation.Documented;	
import java.lang.annotation.ElementType;	
import java.lang.annotation.Retention;	
import java.lang.annotation.RetentionPolicy;	
import java.lang.annotation.Target;	
import org.hyperic.sigar.win32.EventLogRecord;	
	
@Retention(value=RetentionPolicy.RUNTIME)	
@Target(value={ElementType.TYPE})	
@Documented	
public @interface JoinTable {	
    public Type type() default Type.JOIN;	
	
    public String attrName() default "";	
	
    public String alias();	
	
    public Column[] columns() default {};	
	
    public String on();	
	
    public Class<?> entity();	
	
    public static final class Type	
    extends Enum<Type> {	
        private final String value;	
        private static final /* synthetic */ Type[] $VALUES;	
        public static final /* enum */ Type LEFT_JOIN;	
        public static final /* enum */ Type RIGHT_JOIN;	
        public static final /* enum */ Type JOIN;	
	
        public static Type[] values() {	
            return (Type[])$VALUES.clone();	
        }	
	
        public String value() {	
            return this.value;	
        }	
	
        static {	
            JOIN = new Type("JOIN", 0, "JOIN");	
            LEFT_JOIN = new Type("LEFT_JOIN", 1, "LEFT JOIN");	
            RIGHT_JOIN = new Type("RIGHT_JOIN", 2, "RIGHT JOIN");	
            Type[] arrtype = new Type[3];	
            arrtype[0] = JOIN;	
            arrtype[1] = LEFT_JOIN;	
            arrtype[2] = RIGHT_JOIN;	
            $VALUES = arrtype;	
        }	
	
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
            // org.benf.cfr.reader.bytecode.analysis.opgraph.Op02WithProcessedDataAndRefs$11.call(Op02WithProcessedDataAndRefs.java:2015)	
            // org.benf.cfr.reader.bytecode.analysis.opgraph.Op02WithProcessedDataAndRefs$11.call(Op02WithProcessedDataAndRefs.java:2012)	
            // org.benf.cfr.reader.util.graph.AbstractGraphVisitorFI.process(AbstractGraphVisitorFI.java:60)	
            // org.benf.cfr.reader.bytecode.analysis.opgraph.Op02WithProcessedDataAndRefs.convertToOp03List(Op02WithProcessedDataAndRefs.java:2024)	
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:342)	
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:184)	
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:129)	
            // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:96)	
            // org.benf.cfr.reader.entities.Method.analyse(Method.java:397)	
            // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:885)	
            // org.benf.cfr.reader.entities.ClassFile.analyseInnerClassesPass1(ClassFile.java:773)	
            // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:870)	
            // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:792)	
            // org.benf.cfr.reader.Driver.doJar(Driver.java:128)	
            // org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:63)	
            // org.benf.cfr.reader.Main.main(Main.java:48)	
            throw new IllegalStateException("Decompilation failed");	
        }	
	
        public static Type valueOf(String name) {	
            return Enum.valueOf(Type.class, name);	
        }	
    }	
	
}	
	
