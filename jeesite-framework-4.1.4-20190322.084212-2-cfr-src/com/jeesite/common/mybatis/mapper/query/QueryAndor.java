/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.mybatis.mapper.query;	
	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.modules.sys.utils.ModuleUtils;	
import org.hyperic.sigar.pager.PageList;	
	
public final class QueryAndor	
extends Enum<QueryAndor> {	
    private final String value;	
    private static final /* synthetic */ QueryAndor[] $VALUES;	
    public static final /* enum */ QueryAndor OR_BRACKET;	
    public static final /* enum */ QueryAndor OR;	
    public static final /* enum */ QueryAndor AND;	
    public static final /* enum */ QueryAndor AND_BRACKET;	
    public static final /* enum */ QueryAndor END_BRACKET;	
	
    static {	
        AND = new QueryAndor("AND", 0, "AND");	
        AND_BRACKET = new QueryAndor("AND_BRACKET", 1, "AND (");	
        OR = new QueryAndor("OR", 2, "OR");	
        OR_BRACKET = new QueryAndor("OR_BRACKET", 3, "OR (");	
        END_BRACKET = new QueryAndor("END_BRACKET", 4, ")");	
        QueryAndor[] arrqueryAndor = new QueryAndor[5];	
        arrqueryAndor[0] = AND;	
        arrqueryAndor[1] = AND_BRACKET;	
        arrqueryAndor[2] = OR;	
        arrqueryAndor[3] = OR_BRACKET;	
        arrqueryAndor[4] = END_BRACKET;	
        $VALUES = arrqueryAndor;	
    }	
	
    public static QueryAndor[] values() {	
        return (QueryAndor[])$VALUES.clone();	
    }	
	
    public static void removeLastBracket(StringBuilder sql) {	
        String a = StringUtils.trim(sql.toString());	
        if (StringUtils.endsWith(a, QueryAndor.AND_BRACKET.value)) {	
            String string = a;	
            a = string.substring(0, string.length() - QueryAndor.AND_BRACKET.value.length());	
        }	
        if (StringUtils.endsWith(a, QueryAndor.OR_BRACKET.value)) {	
            String string = a;	
            a = string.substring(0, string.length() - QueryAndor.OR_BRACKET.value.length());	
        }	
        if (StringUtils.endsWith(a, "(")) {	
            String string = a;	
            a = string.substring(0, string.length() - "(".length());	
        }	
        sql.replace(0, sql.length(), a);	
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
	
    public static boolean isOnlyAndor(String sql) {	
        String a = StringUtils.trim(sql);	
        return StringUtils.equalsIgnoreCase(a, QueryAndor.AND.value) || StringUtils.equalsIgnoreCase(a, QueryAndor.OR.value);	
    }	
	
    String value() {	
        return this.value;	
    }	
	
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
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:792)	
        // org.benf.cfr.reader.Driver.doJar(Driver.java:128)	
        // org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:63)	
        // org.benf.cfr.reader.Main.main(Main.java:48)	
        throw new IllegalStateException("Decompilation failed");	
    }	
	
    public static boolean isLastBracket(String sql) {	
        String a = StringUtils.trim(sql);	
        return StringUtils.endsWith(a, QueryAndor.AND_BRACKET.value) || StringUtils.endsWith(a, QueryAndor.OR_BRACKET.value) || StringUtils.endsWith(a, "(");	
    }	
	
    public static QueryAndor valueOf(String name) {	
        return Enum.valueOf(QueryAndor.class, name);	
    }	
}	
	
