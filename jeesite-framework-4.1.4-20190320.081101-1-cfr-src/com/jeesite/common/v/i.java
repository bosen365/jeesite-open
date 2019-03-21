/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.common.v;	
	
import com.jeesite.common.cache.CacheUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.io.IOUtils;	
import com.jeesite.common.io.ResourceUtils;	
import com.jeesite.common.lang.DateUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.shiro.d.g;	
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
import org.hyperic.sigar.pager.SortAttribute;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
import org.springframework.beans.factory.annotation.Autowired;	
	
public class i {	
    @Autowired	
    private DataSource i;	
    private static Logger b = LoggerFactory.getLogger(i.class);	
    @Autowired	
    private ModuleService ALLATORIxDEMO;	
	
    private /* synthetic */ String ALLATORIxDEMO(Module module, String dbVersion) {	
        String a;	
        int a2 = -1;	
        int a3 = 0;	
        int n = a2;	
        while (n < 0 && a3 < module.getVersions().length) {	
            if (module.getVersions()[a3].equals(dbVersion)) {	
                n = a3;	
                continue;	
            }	
            ++a3;	
            n = a2;	
        }	
        if (a2 < 0) {	
            b.warn(new StringBuilder().insert(0, "自动更新").append(module.getModuleCode()).append("模块的数据库失败，未知的版本号“").append(dbVersion).append("”，可能是数据库版本比代码版本新。").toString());	
            return null;	
        }	
        if (a2 != module.getVersions().length - 1 && this.ALLATORIxDEMO(module, a = module.getVersions()[a2 + 1])) {	
            return a;	
        }	
        return null;	
    }	
	
    public static String ALLATORIxDEMO() {	
        return new Module("core").getLastVersion();	
    }	
	
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
        InputStream a3;	
        boolean bl;	
        Connection a2;	
        String a;	
        block25 : {	
            block24 : {	
                void a4;	
                a = "";	
                a3 = null;	
                a2 = null;	
                String a5 = Global.getJdbcType();	
                a = new StringBuilder().insert(0, "/dbEupgradeE").append(module.getModuleCode()).append("/").append(a5).append("E").append(a5).append("_").append(currentVersion).append(".sql").toString();	
                a3 = ResourceUtils.getResourceFileStream(a);	
                String a6 = IOUtils.toString(a3, "UTF-8");	
                b.info(new StringBuilder().insert(0, "检测到").append(module.getModuleCode()).append("模块的数据库有更新，现在运行数据库升级脚本：{}\r\n").toString(), (Object)a);	
                a6 = StringUtils.replace(a6, "${_prefix}", Global.getTablePrefix());	
                String a7 = "______sql_scrit_delimiter______";	
                a6 = a6.replaceAll(";([ \f\t\v]*)([\r|\n]|$)", new StringBuilder().insert(0, a7).append("\n").toString());	
                a2 = this.i.getConnection();	
                ScriptRunner scriptRunner = new ScriptRunner(a2);	
                void v0 = a4;	
                a4.setAutoCommit(true);	
                v0.setDelimiter(a7);	
                v0.setFullLineDelimiter(false);	
                void v1 = a4;	
                v0.runScript(new StringReader(a6));	
                b.info(new StringBuilder().insert(0, "恭喜您").append(module.getModuleCode()).append("模块的数据库已升级完成：{}").toString(), (Object)a);	
                bl = true;	
                if (a3 == null) break block24;	
                try {	
                    a3.close();	
                    connection = a2;	
                    break block25;	
                }	
                catch (IOException iOException) {	
                    // empty catch block	
                }	
            }	
            connection = a2;	
        }	
        if (connection == null) return bl;	
        try {	
            a2.close();	
            return bl;	
        }	
        catch (SQLException sQLException) {	
            // empty catch block	
        }	
        return bl;	
        catch (FileNotFoundException a8) {	
            Connection connection2;	
            boolean a6;	
            block27 : {	
                block26 : {	
                    a6 = true;	
                    if (a3 == null) break block26;	
                    {	
                        catch (Throwable throwable) {	
                            Connection connection3;	
                            Throwable throwable2;	
                            block30 : {	
                                if (a3 != null) {	
                                    try {	
                                        a3.close();	
                                        connection3 = a2;	
                                        break block30;	
                                    }	
                                    catch (IOException iOException) {	
                                        // empty catch block	
                                    }	
                                }	
                                connection3 = a2;	
                            }	
                            if (connection3 != null) {	
                                try {	
                                    a2.close();	
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
                        a3.close();	
                        connection2 = a2;	
                        break block27;	
                    }	
                    catch (IOException a7) {	
                        // empty catch block	
                    }	
                }	
                connection2 = a2;	
            }	
            if (connection2 == null) return a6;	
            try {	
                a2.close();	
                return a6;	
            }	
            catch (SQLException a7) {	
                // empty catch block	
            }	
            return a6;	
            catch (Exception a922222222) {	
                Connection connection4;	
                block29 : {	
                    block28 : {	
                        b.warn(new StringBuilder().insert(0, "很抱歉").append(module.getModuleCode()).append("模块的数据库升级脚本执行错误：{}").toString(), (Object)a, (Object)a922222222);	
                        if (a3 == null) break block28;	
                        try {	
                            a3.close();	
                            connection4 = a2;	
                            break block29;	
                        }	
                        catch (IOException a922222222) {	
                            // empty catch block	
                        }	
                    }	
                    connection4 = a2;	
                }	
                if (connection4 == null) return false;	
                try {	
                    a2.close();	
                    return false;	
                }	
                catch (SQLException a922222222) {	
                    return false;	
                }	
            }	
        }	
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
        if (StringUtils.isBlank(a)) {	
            CacheUtils.put("jeesiteVersion", i.ALLATORIxDEMO());	
        } else if (!StringUtils.equals(a, i.ALLATORIxDEMO())) {	
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
                if (!StringUtils.isBlank(a)) break block11;	
                if (a.getVersions().length <= 0) ** GOTO lbl-1000	
                a = a.getVersions()[0];	
            }	
            a = a.getLastVersion();	
            v1 = a = 0;	
            do {	
                if (v1 >= a.getVersions().length) ** GOTO lbl-1000	
                a = a.getCurrentVersion();	
                if (StringUtils.isBlank(a)) {	
                    a = a;	
                }	
                if ((a = this.ALLATORIxDEMO(a, a)) == null) ** GOTO lbl-1000	
                a = a;	
                if (a != null && a != null && !a.equals(a)) {	
                    a.setCurrentVersion(a);	
                    a.setUpgradeInfo("upgrade " + DateUtils.getDateTime() + " (" + a + " -> " + a + ")");	
                    this.ALLATORIxDEMO.update(a);	
                }	
                if (StringUtils.equals(a, a)) continue block0;	
                v1 = ++a;	
            } while (true);	
            break;	
        } while (true);	
    }	
}	
	
