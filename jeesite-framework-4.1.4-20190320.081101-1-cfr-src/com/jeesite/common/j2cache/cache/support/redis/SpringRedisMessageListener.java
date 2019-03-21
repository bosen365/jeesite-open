/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  net.oschina.j2cache.cluster.ClusterPolicy	
 *  org.slf4j.Logger	
 *  org.slf4j.LoggerFactory	
 *  org.springframework.data.redis.connection.Message	
 *  org.springframework.data.redis.connection.MessageListener	
 */	
package com.jeesite.common.j2cache.cache.support.redis;	
	
import net.oschina.j2cache.cluster.ClusterPolicy;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
import org.springframework.data.redis.connection.Message;	
import org.springframework.data.redis.connection.MessageListener;	
	
public class SpringRedisMessageListener	
implements MessageListener {	
    private String channel;	
    private static Logger logger = LoggerFactory.getLogger(SpringRedisMessageListener.class);	
    private ClusterPolicy clusterPolicy;	
	
    /*	
     * Exception decompiling	
     */	
    public void onMessage(Message message, byte[] pattern) {	
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.	
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [9[DOLOOP]], but top level block is 3[CASE]	
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
	
    SpringRedisMessageListener(ClusterPolicy clusterPolicy, String channel) {	
        SpringRedisMessageListener springRedisMessageListener = this;	
        springRedisMessageListener.clusterPolicy = clusterPolicy;	
        springRedisMessageListener.channel = channel;	
    }	
}	
	
