/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.common.config;	
	
import com.jeesite.common.codec.AesUtils;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.config.i;	
import com.jeesite.common.datasource.DataSourceHolder;	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.i18n.I18nLocaleResolver;	
import com.jeesite.common.io.FileUtils;	
import com.jeesite.common.io.PropertiesUtils;	
import com.jeesite.common.io.ResourceUtils;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.service.ServiceException;	
import com.jeesite.common.utils.SpringUtils;	
import com.jeesite.common.web.http.ServletUtils;	
import com.jeesite.modules.gen.service.C;	
import com.jeesite.modules.sys.utils.ConfigUtils;	
import java.io.File;	
import java.io.FileNotFoundException;	
import java.lang.reflect.Field;	
import java.text.MessageFormat;	
import java.util.Locale;	
import java.util.Map;	
import javax.servlet.ServletContext;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import javax.servlet.http.HttpSession;	
import org.apache.commons.lang3.LocaleUtils;	
import org.hyperic.sigar.DirUsage;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
import org.springframework.context.NoSuchMessageException;	
import org.springframework.context.i18n.LocaleContextHolder;	
import org.springframework.core.env.Environment;	
import org.springframework.core.io.Resource;	
	
public class Global {	
    public static final String USERFILES_BASE_URL = "/userfiles/";	
    private static Map<String, String> props;	
    public static final String OP_AUTH = "auth";	
    public static final String HIDE = "0";	
    public static final String OP_EDIT = "edit";	
    public static final String YES = "1";	
    public static final String SHOW = "1";	
    public static final String OP_ADD = "add";	
    private static final Global INSTANCE;	
    public static Logger logger;	
    public static final String NO = "0";	
    public static final String TRUE = "true";	
    public static final String FALSE = "false";	
	
    public static void setLang(String lang, HttpServletRequest request, HttpServletResponse response) {	
        Locale a = Locale.CHINA;	
        if (I18nLocaleResolver.enabled()) {	
            try {	
                a = LocaleUtils.toLocale(lang);	
            }	
            catch (IllegalArgumentException illegalArgumentException) {	
                // empty catch block	
            }	
        }	
        i.ALLATORIxDEMO().setLocale(request, response, a);	
    }	
	
    public static String getConfig(String key, String defValue) {	
        String a = Global.getConfig(key);	
        if (StringUtils.isBlank(a)) {	
            return defValue;	
        }	
        return a;	
    }	
	
    static {	
        logger = LoggerFactory.getLogger(Global.class);	
        props = MapUtils.newHashMap();	
        INSTANCE = new Global();	
    }	
	
    public static String getFrontPath() {	
        return Global.getProperty("frontPath");	
    }	
	
    public static Boolean getPropertyToBoolean(String key, String defValue) {	
        return ObjectUtils.toBoolean(Global.getProperty(key, defValue));	
    }	
	
    public static String getProperty(String key) {	
        String a = props.get(key);	
        if (a == null) {	
            if ("jeesiteVersion".equals(key)) {	
                a = new StringBuilder().insert(0, "V").append(com.jeesite.common.v.i.ALLATORIxDEMO()).toString();	
                props.put(key, a);	
                return a;	
            }	
            a = PropertiesUtils.getInstance().getProperty(key);	
            if (a != null) {	
                props.put(key, a);	
                return a;	
            }	
            if ("productVersion".equals(key)) {	
                a = new StringBuilder().insert(0, "V").append(com.jeesite.common.v.i.ALLATORIxDEMO()).toString();	
                props.put(key, a);	
                return a;	
            }	
            a = "";	
            props.put(key, a);	
        }	
        return a;	
    }	
	
    public static String getAdminPath() {	
        return Global.getProperty("adminPath");	
    }	
	
    public static Boolean isUseCorpModel() {	
        if (!ObjectUtils.toBoolean(C.ALLATORIxDEMO().get("fnSaas")).booleanValue()) {	
            return false;	
        }	
        return TRUE.equals(Global.getProperty("user.useCorpModel"));	
    }	
	
    public static /* varargs */ String getText(String code, String ... params) {	
        if (StringUtils.isBlank(code)) {	
            return code;	
        }	
        Locale a = Locale.CHINA;	
        try {	
            a = LocaleUtils.toLocale(Global.getLang());	
        }	
        catch (IllegalArgumentException illegalArgumentException) {	
            // empty catch block	
        }	
        try {	
            return i.ALLATORIxDEMO().getMessage(code, params, a);	
        }	
        catch (NoSuchMessageException a2) {	
            if (params != null && params.length > 0) {	
                return new MessageFormat(code != null ? code : "", LocaleContextHolder.getLocale()).format(params);	
            }	
            return code;	
        }	
    }	
	
    public static String getDbName() {	
        String a = Global.getJdbcInfo("type");	
        if ("highgo".equals(a) || "dameng".equals(a)) {	
            return "oracle";	
        }	
        if ("mariadb".equals(a)) {	
            return "mysql";	
        }	
        if ("mssql2012".equals(a)) {	
            return "mssql";	
        }	
        return a;	
    }	
	
    public static void clearCache() {	
        PropertiesUtils.releadInstance();	
        ConfigUtils.clearCache();	
        props.clear();	
    }	
	
    /*	
     * Enabled aggressive block sorting	
     * Enabled unnecessary exception pruning	
     * Enabled aggressive exception aggregation	
     */	
    public static Object getConst(String field) {	
        try {	
            if ("Global.Fields".equals(field)) {	
                Field a;	
                int n;	
                String a2 = props.get(new StringBuilder().insert(0, "__").append(field).toString());	
                if (a2 != null) return a2;	
                StringBuilder a3 = new StringBuilder();	
                Field[] arrfield = Global.class.getFields();	
                int n2 = arrfield.length;	
                int n3 = n = 0;	
                while (n3 < n2) {	
                    Field field2 = arrfield[n];	
                    a3.append(a.getName() + ":'" + a.get(null) + "',");	
                    n3 = ++n;	
                }	
                arrfield = DataEntity.class.getFields();	
                n2 = arrfield.length;	
                int n4 = n = 0;	
                while (n4 < n2) {	
                    a = arrfield[n];	
                    a3.append(new StringBuilder().insert(0, a.getName()).append(":'").append(a.get(null)).append("',").toString());	
                    n4 = ++n;	
                }	
                int a4 = a3.length();	
                a2 = a4 > 1 ? a3.substring(0, a4 - 1) : "";	
                props.put(new StringBuilder().insert(0, "__").append(field).toString(), a2);	
                return a2;	
            }	
            Object a = Global.class.getField(field).get(null);	
            if (a != null) return a;	
            return DataEntity.class.getField(field).get(null);	
        }	
        catch (Exception a) {	
            return null;	
        }	
    }	
	
    public static void assertDemoMode() {	
        if (ObjectUtils.toBoolean(Global.getProperty("demo'ode", FALSE)).booleanValue()) {	
            throw new RuntimeException("msg:演示模式，不允许操作！");	
        }	
    }	
	
    public static String getLang() {	
        if (I18nLocaleResolver.enabled()) {	
            Locale a = null;	
            HttpServletRequest a2 = ServletUtils.getRequest();	
            if (a2 != null) {	
                a = i.ALLATORIxDEMO().resolveLocale(a2);	
            }	
            if (a != null) {	
                return a.toString();	
            }	
        }	
        return Locale.CHINA.toString();	
    }	
	
    public static Integer getConfigToInteger(String key, String defValue) {	
        return ObjectUtils.toInteger(Global.getConfig(key, defValue));	
    }	
	
    public static boolean isTestProfileActive() {	
        Environment a = SpringUtils.getBean(Environment.class);	
        return StringUtils.inString("test", a.getActiveProfiles());	
    }	
	
    public static String getJdbcInfo(String key) {	
        String a;	
        String a2 = new StringBuilder().insert(0, "jdbc").append((a = DataSourceHolder.getDataSourceName()) == null ? "" : new StringBuilder().insert(0, ".").append(a).toString()).append(".").append(key).toString();	
        String a3 = Global.getProperty(a2);	
        if (StringUtils.isBlank(a3)) {	
            throw new ServiceException(new StringBuilder().insert(0, "没有找到 ").append(a2).append(" 的参数配置").toString());	
        }	
        return a3;	
    }	
	
    public static Integer getPropertyToInteger(String key, String defValue) {	
        return ObjectUtils.toInteger(Global.getProperty(key, defValue));	
    }	
	
    public static String getProperty(String key, String defValue) {	
        String a = Global.getProperty(key);	
        if (StringUtils.isBlank(a)) {	
            return defValue;	
        }	
        return a;	
    }	
	
    public static String getPropertyDecodeAndEncode(String ascKey, String key, String value) {	
        block16 : {	
            if (value == null) {	
                value = Global.getProperty(key);	
            }	
            if (value.length() != 32) break block16;	
            value = AesUtils.decode(value, ascKey);	
        }	
        try {	
            throw new RuntimeException("未加密，则加密");	
        }	
        catch (Exception a) {	
            int n;	
            String a2 = AesUtils.encode(value, ascKey);	
            String[] a3 = PropertiesUtils.DEFAULT_CONFIG_FILE;	
            String a4 = PropertiesUtils.getInstance().getProperty("configFiles");	
            if (StringUtils.isNotBlank(a4)) {	
                a3 = StringUtils.split(a4, ",");	
            }	
            String[] arrstring = a3;	
            int n2 = arrstring.length;	
            int n3 = n = 0;	
            while (n3 < n2) {	
                block17 : {	
                    String a5 = arrstring[n];	
                    try {	
                        CharSequence a6;	
                        Resource a7 = ResourceUtils.getResource(a5);	
                        if (!a7.exists()) break block17;	
                        File a8 = a7.getFile();	
                        String a9 = FileUtils.getFileExtension(a5);	
                        if ("properties".equals(a9)) {	
                            File file = a8;	
                            String string = FileUtils.readFileToString(file, "UTF-8");	
                            a6 = ((String)a6).replaceAll("(\n" + key + "=)(.*)", new StringBuilder().insert(0, "$1").append(a2).toString());	
                            FileUtils.writeStringToFile(file, (String)a6, "UTF-8");	
                            logger.debug(new StringBuilder().insert(0, "Update ").append(a8.getPath()).append(" ").append(key).append(" encryption success.").toString());	
                            break block17;	
                        }	
                        if (!"yml".equals(a9)) break block17;	
                        a6 = new StringBuilder(FileUtils.readFileToString(a8, "UTF-8"));	
                        String a10 = "";	
                        int a11 = 0;	
                        String[] a12 = StringUtils.split(key, ".");	
                        for (int a13 = 0; a13 < a12.length; ++a13) {	
                            int a14;	
                            int a15;	
                            String a16 = a12[a13];	
                            String a17 = "";	
                            int n4 = a14 = 0;	
                            while (n4 < a13 * 2) {	
                                a17 = new StringBuilder().insert(0, a17).append(" ").toString();	
                                n4 = ++a14;	
                            }	
                            a16 = new StringBuilder().insert(0, a17).append(a16).append(":").toString();	
                            a14 = ((StringBuilder)a6).indexOf(new StringBuilder().insert(0, "\n").append(a16).toString(), a11);	
                            if (a14 == -1) continue;	
                            if (a13 > 0) {	
                                int n5;	
                                block18 : {	
                                    int a18;	
                                    a15 = 0;	
                                    String a19 = ((StringBuilder)a6).substring(a11, a14);	
                                    String[] a20 = StringUtils.split(a19, "\n");	
                                    int n6 = a18 = 0;	
                                    while (n6 < a20.length) {	
                                        if (StringUtils.isNotBlank(StringUtils.replace(a20[a18], "\r", "")) && !StringUtils.startsWith(StringUtils.trim(a20[a18]), "#") && !StringUtils.startsWith(a20[a18], a10) && !StringUtils.startsWith(a20[a18], a17)) {	
                                            n5 = a15 = 1;	
                                            break block18;	
                                        }	
                                        n6 = ++a18;	
                                    }	
                                    n5 = a15;	
                                }	
                                if (n5 != 0) break;	
                            }	
                            if (a13 == a12.length - 1) {	
                                CharSequence charSequence = a6;	
                                a15 = ((StringBuilder)charSequence).indexOf(":", a14);	
                                int a21 = ((StringBuilder)charSequence).indexOf("\n", a15);	
                                FileUtils.writeStringToFile(a8, ((StringBuilder)a6).toString(), "UTF-8");	
                                logger.debug(new StringBuilder().insert(0, "Update ").append(a8.getPath()).append(" ").append(key).append(" encryption success.").toString());	
                                ((StringBuilder)charSequence).replace(a15, a21, new StringBuilder().insert(0, ": ").append(a2).toString());	
                            }	
                            a10 = a16;	
                            a11 = a14;	
                        }	
                    }	
                    catch (Exception a22) {	
                        if (a22 instanceof FileNotFoundException) break block17;	
                        logger.error(new StringBuilder().insert(0, "Update ").append(a5).append(" encryption failure.").toString(), a22);	
                    }	
                }	
                n3 = ++n;	
            }	
        }	
        return value;	
    }	
	
    public static String getJdbcType() {	
        return Global.getJdbcInfo("type");	
    }	
	
    public static Global getInstance() {	
        return INSTANCE;	
    }	
	
    public static String getConfig(String key) {	
        String a = props.get(key);	
        if (a == null) {	
            a = PropertiesUtils.getInstance().getProperty(key);	
            if (a != null) {	
                props.put(key, a);	
            } else {	
                a = ConfigUtils.getConfig(key).getConfigValue();	
                if (a != null) {	
                    props.put(key, "read_sys_config_table");	
                    return a;	
                }	
                if ("productVersion".equals(key)) {	
                    a = new StringBuilder().insert(0, "V").append(com.jeesite.common.v.i.ALLATORIxDEMO()).toString();	
                    props.put(key, a);	
                    return a;	
                }	
                a = "";	
                props.put(key, a);	
                return a;	
            }	
        }	
        if ("read_sys_config_table".equals(a)) {	
            a = ConfigUtils.getConfig(key).getConfigValue();	
        }	
        return a;	
    }	
	
    public static String getUserfilesBaseDir(String path) {	
        String string;	
        String a;	
        block6 : {	
            a = Global.getProperty("file.baseDir");	
            if (StringUtils.isBlank(a)) {	
                try {	
                    string = a = ServletUtils.getRequest().getSession().getServletContext().getRealPath("/");	
                    break block6;	
                }	
                catch (Exception exception) {	
                    // empty catch block	
                }	
            }	
            string = a;	
        }	
        if (StringUtils.isBlank(string)) {	
            a = System.getProperty("user.dir");	
        }	
        if (!a.endsWith("/")) {	
            a = new StringBuilder().insert(0, a).append("/").toString();	
        }	
        if (path != null) {	
            return FileUtils.path(new StringBuilder().insert(0, a).append(USERFILES_BASE_URL).append(path).toString());	
        }	
        return FileUtils.path(a);	
    }	
	
    public static Boolean getConfigToBoolean(String key, String defValue) {	
        return ObjectUtils.toBoolean(Global.getConfig(key, defValue));	
    }	
	
    public static String getTablePrefix() {	
        return Global.getProperty("jdbc.tablePrefix");	
    }	
}	
	
