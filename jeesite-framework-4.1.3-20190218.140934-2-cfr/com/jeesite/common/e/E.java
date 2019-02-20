/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.io.IOUtils	
 *  com.jeesite.common.io.ResourceUtils	
 *  com.jeesite.common.lang.DateUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  org.apache.ibatis.jdbc.ScriptRunner	
 *  org.slf4j.Logger	
 *  org.slf4j.LoggerFactory	
 *  org.springframework.beans.factory.annotation.Autowired	
 */	
package com.jeesite.common.e;	
	
import com.jeesite.common.cache.CacheUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.io.IOUtils;	
import com.jeesite.common.io.ResourceUtils;	
import com.jeesite.common.lang.DateUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.mapper.query.QueryColumn;	
import com.jeesite.modules.sys.entity.Module;	
import com.jeesite.modules.sys.service.ModuleService;	
import com.jeesite.modules.sys.utils.ModuleUtils;	
import java.io.FileNotFoundException;	
import java.io.IOException;	
import java.io.InputStream;	
import java.io.Reader;	
import java.io.StringReader;	
import java.sql.Connection;	
import java.sql.SQLException;	
import java.util.Collection;	
import java.util.Iterator;	
import javax.annotation.PostConstruct;	
import javax.sql.DataSource;	
import org.apache.ibatis.jdbc.ScriptRunner;	
import org.hyperic.sigar.ptql.ProcessFinder;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
import org.springframework.beans.factory.annotation.Autowired;	
	
public class E {	
    @Autowired	
    private DataSource D;	
    private static Logger c = LoggerFactory.getLogger(E.class);	
    @Autowired	
    private ModuleService ALLATORIxDEMO;	
	
    /*	
     * WARNING - Removed try catching itself - possible behaviour change.	
     * Loose catch block	
     * Enabled aggressive block sorting	
     * Enabled unnecessary exception pruning	
     * Enabled aggressive exception aggregation	
     * Lifted jumps to return sites	
     */	
    private /* synthetic */ boolean ALLATORIxDEMO(Module module, String currentVersion) {	
        Connection connection;	
        Connection a3;	
        String a2;	
        boolean bl;	
        InputStream a4;	
        block25 : {	
            block24 : {	
                void a5;	
                a2 = "";	
                a4 = null;	
                a3 = null;	
                String a6 = Global.getJdbcType();	
                a2 = new StringBuilder().insert(0, "/db/ugrade/").append(module.getModuleCode()).append("/").append(a6).append("/").append(a6).append("_").append(currentVersion).append(".sql").toString();	
                a4 = ResourceUtils.getResourceFileStream((String)a2);	
                String a7 = IOUtils.toString((InputStream)a4, (String)"UTF-8");	
                c.info(new StringBuilder().insert(0, "检测到").append(module.getModuleCode()).append("模块的数据库有更新，现在运行数据库升级脚本：{}\r\n").toString(), (Object)a2);	
                a7 = StringUtils.replace((String)a7, (String)"${_prefix}", (String)Global.getTablePrefix());	
                String a8 = "______sql_script_delimiter______";	
                a7 = a7.replaceAll(";([ \f\t\v]*)([\r|\n]|$)", new StringBuilder().insert(0, a8).append("\n").toString());	
                a3 = this.D.getConnection();	
                ScriptRunner scriptRunner = new ScriptRunner(a3);	
                void v0 = a5;	
                a5.setAutoCommit(true);	
                v0.setDelimiter(a8);	
                v0.setFullLineDelimiter(false);	
                void v1 = a5;	
                v0.runScript((Reader)new StringReader(a7));	
                c.info(new StringBuilder().insert(0, "恭喜您").append(module.getModuleCode()).append("模块的数据库已升级完成：{}").toString(), (Object)a2);	
                bl = true;	
                if (a4 == null) break block24;	
                try {	
                    a4.close();	
                    connection = a3;	
                    break block25;	
                }	
                catch (IOException iOException) {	
                    // empty catch block	
                }	
            }	
            connection = a3;	
        }	
        if (connection == null) return bl;	
        try {	
            a3.close();	
            return bl;	
        }	
        catch (SQLException sQLException) {	
            // empty catch block	
        }	
        return bl;	
        catch (FileNotFoundException a9) {	
            boolean a7;	
            Connection connection2;	
            block27 : {	
                block26 : {	
                    a7 = true;	
                    if (a4 == null) break block26;	
                    {	
                        catch (Throwable throwable) {	
                            Connection connection3;	
                            Throwable throwable2;	
                            block30 : {	
                                if (a4 != null) {	
                                    try {	
                                        a4.close();	
                                        connection3 = a3;	
                                        break block30;	
                                    }	
                                    catch (IOException iOException) {	
                                        // empty catch block	
                                    }	
                                }	
                                connection3 = a3;	
                            }	
                            if (connection3 != null) {	
                                try {	
                                    a3.close();	
                                    throwable2 = throwable;	
                                    throw throwable2;	
                                }	
                                catch (SQLException sQLException) {	
                                    // empty catch block	
                                }	
                            }	
                            throwable2 = throwable;	
                            throw throwable2;	
                        }	
                    }	
                    try {	
                        a4.close();	
                        connection2 = a3;	
                        break block27;	
                    }	
                    catch (IOException a8) {	
                        // empty catch block	
                    }	
                }	
                connection2 = a3;	
            }	
            if (connection2 == null) return a7;	
            try {	
                a3.close();	
                return a7;	
            }	
            catch (SQLException a8) {	
                // empty catch block	
            }	
            return a7;	
            catch (Exception a1022222222) {	
                Connection connection4;	
                block29 : {	
                    block28 : {	
                        c.warn(new StringBuilder().insert(0, "很抱歉").append(module.getModuleCode()).append("模块的数据库升级脚本执行错误：{}").toString(), (Object)a2, (Object)a1022222222);	
                        if (a4 == null) break block28;	
                        try {	
                            a4.close();	
                            connection4 = a3;	
                            break block29;	
                        }	
                        catch (IOException a1022222222) {	
                            // empty catch block	
                        }	
                    }	
                    connection4 = a3;	
                }	
                if (connection4 == null) return false;	
                try {	
                    a3.close();	
                    return false;	
                }	
                catch (SQLException a1022222222) {	
                    return false;	
                }	
            }	
        }	
    }	
	
    private /* synthetic */ String ALLATORIxDEMO(Module module, String dbVersion) {	
        String a2;	
        int a3 = -1;	
        int a4 = 0;	
        int n = a3;	
        while (n < 0 && a4 < module.getVersions().length) {	
            if (module.getVersions()[a4].equals(dbVersion)) {	
                n = a4;	
                continue;	
            }	
            ++a4;	
            n = a3;	
        }	
        if (a3 < 0) {	
            c.warn(new StringBuilder().insert(0, "自动更新").append(module.getModuleCode()).append("模块的数据库失败，未知的版本号“").append(dbVersion).append("”，可能是数据库版本比代码版本新。").toString());	
            return null;	
        }	
        if (a3 != module.getVersions().length - 1 && this.ALLATORIxDEMO(module, a2 = module.getVersions()[a3 + 1])) {	
            return a2;	
        }	
        return null;	
    }	
	
    /*	
     * Unable to fully structure code	
     * Enabled aggressive block sorting	
     * Lifted jumps to return sites	
     */	
    @PostConstruct	
    private /* synthetic */ void ALLATORIxDEMO() {	
        if (Global.isTestProfileActive()) {	
            return;	
        }	
        a = (String)CacheUtils.get("jeesiteVersion");	
        if (StringUtils.isBlank((CharSequence)a)) {	
            CacheUtils.put("jeesiteVersion", E.ALLATORIxDEMO());	
        } else if (!StringUtils.equals((CharSequence)a, (CharSequence)E.ALLATORIxDEMO())) {	
            CacheUtils.clearCache();	
        }	
        var3_2 = ModuleUtils.getModuleList().values().iterator();	
        block0 : do lbl-1000: // 5 sources:	
        {	
            block11 : {	
                block10 : {	
                    v0 = var3_2;	
                    while (v0.hasNext() != false) {	
                        a = var3_2.next();	
                        if (a.getIsLoader().booleanValue() && a.getVersions().length == 0) {	
                            v0 = var3_2;	
                            continue;	
                        }	
                        break block10;	
                    }	
                    return;	
                }	
                a = a.getCurrentVersion();	
                if (!StringUtils.isBlank((CharSequence)a)) break block11;	
                if (a.getVersions().length <= 0) ** GOTO lbl-1000	
                a = a.getVersions()[0];	
            }	
            a = a.getLastVersion();	
            v1 = a = 0;	
            do {	
                if (v1 >= a.getVersions().length) ** GOTO lbl-1000	
                a = a.getCurrentVersion();	
                if (StringUtils.isBlank((CharSequence)a)) {	
                    a = a;	
                }	
                if ((a = this.ALLATORIxDEMO(a, a)) == null) ** GOTO lbl-1000	
                a = a;	
                if (a != null && a != null && !a.equals(a)) {	
                    a.setCurrentVersion(a);	
                    a.setUpgradeInfo("ugrade " + DateUtils.getDateTime() + " (" + a + " -> " + a + ")");	
                    this.ALLATORIxDEMO.update(a);	
                }	
                if (StringUtils.equals((CharSequence)a, (CharSequence)a)) continue block0;	
                v1 = ++a;	
            } while (true);	
            break;	
        } while (true);	
    }	
	
    public static String ALLATORIxDEMO() {	
        return new Module("core").getLastVersion();	
    }	
}	
	
