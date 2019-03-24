/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.validator;	
	
import com.jeesite.common.validator.PatternValueValidator;	
import java.lang.annotation.Annotation;	
import java.lang.annotation.Documented;	
import java.lang.annotation.ElementType;	
import java.lang.annotation.Retention;	
import java.lang.annotation.RetentionPolicy;	
import java.lang.annotation.Target;	
import javax.validation.Constraint;	
import javax.validation.Payload;	
import org.hyperic.sigar.ProcCredName;	
import org.hyperic.sigar.test.GetPass;	
	
@Documented	
@Constraint(validatedBy={PatternValueValidator.class})	
@Target(value={ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})	
@Retention(value=RetentionPolicy.RUNTIME)	
public @interface PatternValue {	
    public Flag[] flags() default {};	
	
    public Class<?>[] groups() default {};	
	
    public String regexp() default "";	
	
    public String message() default "{javax.validation.constraints.Pattern.message}";	
	
    public String value();	
	
    public Class<? extends Payload>[] payload() default {};	
	
    @Target(value={ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})	
    @Retention(value=RetentionPolicy.RUNTIME)	
    @Documented	
    public static @interface List {	
        public PatternValue[] value();	
    }	
	
    public static final class Flag	
    extends Enum<Flag> {	
        private final int value;	
        private static final /* synthetic */ Flag[] $VALUES;	
        public static final /* enum */ Flag MULTILINE;	
        public static final /* enum */ Flag DOTALL;	
        public static final /* enum */ Flag COMMENTS;	
        public static final /* enum */ Flag UNICODE_CASE;	
        public static final /* enum */ Flag UNIX_LINES;	
        public static final /* enum */ Flag CASE_INSENSITIVE;	
        public static final /* enum */ Flag CANON_EQ;	
	
        public static Flag valueOf(String name) {	
            return Enum.valueOf(Flag.class, name);	
        }	
	
        /*	
         * Exception decompiling	
         */	
        private /* synthetic */ Flag(int varnull) {	
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
	
        public int getValue() {	
            return this.value;	
        }	
	
        public static Flag[] values() {	
            return (Flag[])$VALUES.clone();	
        }	
	
        static {	
            UNIX_LINES = new Flag("UNIX_LINES", 0, 1);	
            CASE_INSENSITIVE = new Flag("CASE_INSENSITIVE", 1, 2);	
            COMMENTS = new Flag("COMMENTS", 2, 4);	
            MULTILINE = new Flag("MULTILINE", 3, 8);	
            DOTALL = new Flag("DOTALL", 4, 32);	
            UNICODE_CASE = new Flag("UNICODE_CASE", 5, 64);	
            CANON_EQ = new Flag("CANON_EQ", 6, 128);	
            Flag[] arrflag = new Flag[7];	
            arrflag[0] = UNIX_LINES;	
            arrflag[1] = CASE_INSENSITIVE;	
            arrflag[2] = COMMENTS;	
            arrflag[3] = MULTILINE;	
            arrflag[4] = DOTALL;	
            arrflag[5] = UNICODE_CASE;	
            arrflag[6] = CANON_EQ;	
            $VALUES = arrflag;	
        }	
    }	
	
}	
	
