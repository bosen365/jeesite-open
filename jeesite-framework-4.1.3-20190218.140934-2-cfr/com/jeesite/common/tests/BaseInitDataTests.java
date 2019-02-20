/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.callback.MethodCallback	
 *  com.jeesite.common.io.IOUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  org.apache.commons.io.FileUtils	
 *  org.apache.commons.logging.Log	
 *  org.apache.ibatis.jdbc.ScriptRunner	
 *  org.springframework.jdbc.core.JdbcTemplate	
 */	
package com.jeesite.common.tests;	
	
import com.jeesite.common.callback.MethodCallback;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.io.IOUtils;	
import com.jeesite.common.j2cache.cache.support.redis.ConfigureNotifyKeyspaceEventsAction;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.common.tests.BaseSpringContextTests;	
import java.io.File;	
import java.io.IOException;	
import java.io.InputStream;	
import java.io.Reader;	
import java.io.StringReader;	
import java.sql.Connection;	
import java.sql.SQLException;	
import javax.sql.DataSource;	
import org.apache.commons.io.FileUtils;	
import org.apache.commons.logging.Log;	
import org.apache.ibatis.jdbc.ScriptRunner;	
import org.hyperic.sigar.ProcFd;	
import org.springframework.jdbc.core.JdbcTemplate;	
	
public class BaseInitDataTests	
extends BaseSpringContextTests {	
    protected String excelFile;	
	
    protected void clearTable(Class<? extends BaseEntity<?>> entity) {	
        String a2 = MapperHelper.getTableName(entity);	
        String a3 = new StringBuilder().insert(0, "TRUNCATE TABLE ").append(a2).toString();	
        if ("db2".equals(Global.getJdbcType())) {	
            a3 = new StringBuilder().insert(0, a3).append(" IMMEDIATE").toString();	
        }	
        BaseInitDataTests baseInitDataTests = this;	
        baseInitDataTests.logger.debug((Object)a3);	
        baseInitDataTests.jdbcTemplate.update(a3);	
    }	
	
    /*	
     * WARNING - Removed try catching itself - possible behaviour change.	
     */	
    protected void runScript(String sqlFile) throws IOException, SQLException {	
        Connection a2;	
        Connection connection;	
        block14 : {	
            block13 : {	
                InputStream a3 = null;	
                a2 = null;	
                try {	
                    void a4;	
                    sqlFile = new StringBuilder().insert(0, "db/").append(Global.getJdbcType()).append("/").append(sqlFile).toString();	
                    BaseInitDataTests baseInitDataTests = this;	
                    baseInitDataTests.logger.debug((Object)new StringBuilder().insert(0, "runScript: ").append(sqlFile).toString());	
                    a3 = FileUtils.openInputStream((File)new File(sqlFile));	
                    String a5 = IOUtils.toString((InputStream)a3, (String)"UTF-8");	
                    a5 = StringUtils.replace((String)a5, (String)"${_prefix}", (String)Global.getTablePrefix());	
                    String a6 = "______ql_cript_delimiter______";	
                    a5 = a5.replaceAll(";([ \f\t\v]*)([\r|\n]|$)", new StringBuilder().insert(0, a6).append("\n").toString());	
                    a2 = baseInitDataTests.dataSource.getConnection();	
                    ScriptRunner scriptRunner = new ScriptRunner(a2);	
                    void v1 = a4;	
                    a4.setAutoCommit(true);	
                    v1.setDelimiter(a6);	
                    v1.setFullLineDelimiter(false);	
                    void v2 = a4;	
                    v1.runScript((Reader)new StringReader(a5));	
                    baseInitDataTests.logger.debug((Object)new StringBuilder().insert(0, "runS\tript Complete: ").append(sqlFile).toString());	
                    if (a3 == null) break block13;	
                }	
                catch (Throwable throwable) {	
                    Throwable throwable2;	
                    block16 : {	
                        Connection connection2;	
                        block15 : {	
                            if (a3 != null) {	
                                try {	
                                    a3.close();	
                                    connection2 = a2;	
                                    break block15;	
                                }	
                                catch (IOException iOException) {	
                                    // empty catch block	
                                }	
                            }	
                            connection2 = a2;	
                        }	
                        if (connection2 != null) {	
                            try {	
                                a2.close();	
                                throwable2 = throwable;	
                                break block16;	
                            }	
                            catch (SQLException sQLException) {	
                                // empty catch block	
                            }	
                        }	
                        throwable2 = throwable;	
                    }	
                    throw throwable2;	
                }	
                try {	
                    a3.close();	
                    connection = a2;	
                    break block14;	
                }	
                catch (IOException a5) {	
                    // empty catch block	
                }	
            }	
            connection = a2;	
        }	
        if (connection != null) {	
            try {	
                a2.close();	
                return;	
            }	
            catch (SQLException a5) {	
                return;	
            }	
        }	
    }	
	
    @Override	
    public void begin() {	
        super.begin();	
        if (!"true".equals(System.getProperty("jeesite.initdata"))) {	
            String a2 = "为了防止误操作，请运行时增加 -Djeesite.initdata=true 参数。";	
            this.logger.error((Object)a2);	
            throw new RuntimeException(a2);	
        }	
    }	
	
    /*	
     * Exception decompiling	
     */	
    protected void initExcelData(Class<? extends BaseEntity<?>> entityClass, MethodCallback callback) throws Exception {	
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.	
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [3[TRYBLOCK]], but top level block is 15[FORLOOP]	
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
	
