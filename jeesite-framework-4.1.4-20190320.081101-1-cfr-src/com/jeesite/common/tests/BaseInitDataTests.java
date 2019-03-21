/*	
 * Decompiled with CFR 0.140.	
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
import com.jeesite.common.entity.Extend;	
import com.jeesite.common.io.IOUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.common.tests.BaseSpringContextTests;	
import com.jeesite.modules.job.d.i;	
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
import org.springframework.jdbc.core.JdbcTemplate;	
	
public class BaseInitDataTests	
extends BaseSpringContextTests {	
    protected String excelFile;	
	
    @Override	
    public void begin() {	
        super.begin();	
        if (!"true".equals(System.getProperty("jeesite.initdata"))) {	
            String a = "为了防止误操作，请运行时增加 -Djeesite.initdata=true 参数。";	
            this.logger.error((Object)a);	
            throw new RuntimeException(a);	
        }	
    }	
	
    protected void clearTable(Class<? extends BaseEntity<?>> entity) {	
        String a = MapperHelper.getTableName(entity);	
        String a2 = new StringBuilder().insert(0, "TRUNCATE TABLE ").append(a).toString();	
        if ("db2".equals(Global.getJdbcType())) {	
            a2 = new StringBuilder().insert(0, a2).append(" IMMEDIATE").toString();	
        }	
        BaseInitDataTests baseInitDataTests = this;	
        baseInitDataTests.logger.debug((Object)a2);	
        baseInitDataTests.jdbcTemplate.update(a2);	
    }	
	
    /*	
     * WARNING - Removed try catching itself - possible behaviour change.	
     */	
    protected void runScript(String sqlFile) throws IOException, SQLException {	
        Connection a;	
        Connection connection;	
        block14 : {	
            block13 : {	
                InputStream a2 = null;	
                a = null;	
                try {	
                    void a3;	
                    sqlFile = new StringBuilder().insert(0, "db/").append(Global.getJdbcType()).append("/").append(sqlFile).toString();	
                    BaseInitDataTests baseInitDataTests = this;	
                    baseInitDataTests.logger.debug((Object)new StringBuilder().insert(0, "runScript: ").append(sqlFile).toString());	
                    a2 = FileUtils.openInputStream((File)new File(sqlFile));	
                    String a4 = IOUtils.toString((InputStream)a2, (String)"UTF-8");	
                    a4 = StringUtils.replace((String)a4, (String)"${_prefix}", (String)Global.getTablePrefix());	
                    String a5 = "______sql_script_delimiter______";	
                    a4 = a4.replaceAll(";([ \f\t\v]*)([\r|\n]|$)", new StringBuilder().insert(0, a5).append("\n").toString());	
                    a = baseInitDataTests.dataSource.getConnection();	
                    ScriptRunner scriptRunner = new ScriptRunner(a);	
                    void v1 = a3;	
                    a3.setAutoCommit(true);	
                    v1.setDelimiter(a5);	
                    v1.setFullLineDelimiter(false);	
                    void v2 = a3;	
                    v1.runScript((Reader)new StringReader(a4));	
                    baseInitDataTests.logger.debug((Object)new StringBuilder().insert(0, "runScript Complete: ").append(sqlFile).toString());	
                    if (a2 == null) break block13;	
                }	
                catch (Throwable throwable) {	
                    Throwable throwable2;	
                    block16 : {	
                        Connection connection2;	
                        block15 : {	
                            if (a2 != null) {	
                                try {	
                                    a2.close();	
                                    connection2 = a;	
                                    break block15;	
                                }	
                                catch (IOException iOException) {	
                                    // empty catch block	
                                }	
                            }	
                            connection2 = a;	
                        }	
                        if (connection2 != null) {	
                            try {	
                                a.close();	
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
                    a2.close();	
                    connection = a;	
                    break block14;	
                }	
                catch (IOException a4) {	
                    // empty catch block	
                }	
            }	
            connection = a;	
        }	
        if (connection != null) {	
            try {	
                a.close();	
                return;	
            }	
            catch (SQLException a4) {	
                return;	
            }	
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
	
