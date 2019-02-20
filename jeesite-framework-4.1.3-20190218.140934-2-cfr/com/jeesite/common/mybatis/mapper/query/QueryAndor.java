/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.lang.StringUtils	
 */	
package com.jeesite.common.mybatis.mapper.query;	
	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.validator.ValidatorUtils;	
import org.hyperic.jni.ArchLoaderException;	
	
public enum QueryAndor {	
    AND("AND"),	
    AND_BRACKET("AND ("),	
    OR("OR"),	
    OR_BRACKET("OR ("),	
    END_BRACKET(")");	
    	
    private final String value;	
	
    /*	
     * Exception decompiling	
     */	
    private /* synthetic */ QueryAndor(String varnull) {	
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
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:792)	
        // org.benf.cfr.reader.Driver.doJar(Driver.java:128)	
        // org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:63)	
        // org.benf.cfr.reader.Main.main(Main.java:48)	
        throw new IllegalStateException("Decompilation failed");	
    }	
	
    String value() {	
        return this.value;	
    }	
	
    public static void removeLastBracket(StringBuilder sql) {	
        String a2 = StringUtils.trim((String)sql.toString());	
        if (StringUtils.endsWith((CharSequence)a2, (CharSequence)QueryAndor.AND_BRACKET.value)) {	
            String string = a2;	
            a2 = string.substring(0, string.length() - QueryAndor.AND_BRACKET.value.length());	
        }	
        if (StringUtils.endsWith((CharSequence)a2, (CharSequence)QueryAndor.OR_BRACKET.value)) {	
            String string = a2;	
            a2 = string.substring(0, string.length() - QueryAndor.OR_BRACKET.value.length());	
        }	
        if (StringUtils.endsWith((CharSequence)a2, (CharSequence)"(")) {	
            String string = a2;	
            a2 = string.substring(0, string.length() - "(".length());	
        }	
        sql.replace(0, sql.length(), a2);	
    }	
	
    public static void addAndor(StringBuilder sql, StringBuilder sqlWhere, QueryAndor andor) {	
        if (sql.length() != 0) {	
            QueryAndor queryAndor = andor;	
            sqlWhere.append(" " + queryAndor.value());	
            if (queryAndor != END_BRACKET) {	
                sqlWhere.append(" ");	
                return;	
            }	
        } else if (andor == AND_BRACKET || andor == OR_BRACKET) {	
            sqlWhere.append("( ");	
        }	
    }	
	
    public static boolean isLastBracket(String sql) {	
        String a2 = StringUtils.trim((String)sql);	
        return StringUtils.endsWith((CharSequence)a2, (CharSequence)QueryAndor.AND_BRACKET.value) || StringUtils.endsWith((CharSequence)a2, (CharSequence)QueryAndor.OR_BRACKET.value) || StringUtils.endsWith((CharSequence)a2, (CharSequence)"(");	
    }	
	
    public static boolean isOnlyAndor(String sql) {	
        String a2 = StringUtils.trim((String)sql);	
        return StringUtils.equalsIgnoreCase((CharSequence)a2, (CharSequence)QueryAndor.AND.value) || StringUtils.equalsIgnoreCase((CharSequence)a2, (CharSequence)QueryAndor.OR.value);	
    }	
}	
	
