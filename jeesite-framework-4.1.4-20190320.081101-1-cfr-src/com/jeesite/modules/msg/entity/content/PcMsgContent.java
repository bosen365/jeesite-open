/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.modules.msg.entity.content;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.modules.msg.entity.content.BaseMsgContent;	
import java.util.List;	
import java.util.Map;	
	
public class PcMsgContent	
extends BaseMsgContent {	
    private String type;	
    private List<Map<String, String>> buttons = ListUtils.newArrayList();	
    private static final long serialVersionUID = 1L;	
    private Long timeout;	
	
    @Override	
    public String getMsgType() {	
        return "pc";	
    }	
	
    public String getType() {	
        return this.type;	
    }	
	
    public void setType(String type) {	
        this.type = type;	
    }	
	
    public void setButtons(List<Map<String, String>> buttons) {	
        this.buttons = buttons;	
    }	
	
    public Long getTimeout() {	
        return this.timeout;	
    }	
	
    public void setTimeout(Long timeout) {	
        this.timeout = timeout;	
    }	
	
    public List<Map<String, String>> getButtons() {	
        return this.buttons;	
    }	
	
    /*	
     * Exception decompiling	
     */	
    public /* varargs */ void addButton(String ... values) {	
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.	
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [5[DOLOOP]], but top level block is 1[CASE]	
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
}	
	
