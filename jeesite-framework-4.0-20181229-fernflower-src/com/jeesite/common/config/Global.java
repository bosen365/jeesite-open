package com.jeesite.common.config;	
	
import com.jeesite.common.codec.AesUtils;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.datasource.DataSourceHolder;	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.io.FileUtils;	
import com.jeesite.common.io.PropertiesUtils;	
import com.jeesite.common.io.ResourceUtils;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.mapper.query.QueryWhere;	
import com.jeesite.common.utils.SpringUtils;	
import com.jeesite.common.web.http.ServletUtils;	
import com.jeesite.modules.sys.service.j;	
import com.jeesite.modules.sys.utils.ConfigUtils;	
import com.jeesite.modules.sys.utils.DictUtils;	
import java.io.File;	
import java.io.FileNotFoundException;	
import java.lang.reflect.Field;	
import java.text.MessageFormat;	
import java.util.Locale;	
import java.util.Map;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.apache.commons.lang3.LocaleUtils;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
import org.springframework.context.NoSuchMessageException;	
import org.springframework.context.i18n.LocaleContextHolder;	
import org.springframework.core.env.Environment;	
import org.springframework.core.io.Resource;	
	
public class Global {	
   public static final String YES = "1";	
   public static final String OP_EDIT = "edit";	
   private static Logger logger = LoggerFactory.getLogger(Global.class);	
   public static final String USERFILES_BASE_URL = "/userfiles/";	
   private static Map props = MapUtils.newHashMap();	
   public static final String OP_ADD = "add";	
   public static final String HIDE = "0";	
   public static final String OP_AUTH = "auth";	
   public static final String TRUE = "true";	
   private static final Global INSTANCE = new Global();	
   public static final String FALSE = "false";	
   public static final String NO = "0";	
   public static final String SHOW = "1";	
	
   public static String getText(String code, String... params) {	
      if (StringUtils.isBlank(code)) {	
         return code;	
      } else {	
         Locale a = null;	
	
         try {	
            a = LocaleUtils.toLocale(getLang());	
         } catch (IllegalArgumentException var4) {	
            a = Locale.CHINA;	
         }	
	
         try {	
            return null.ALLATORIxDEMO().getMessage(code, params, a);	
         } catch (NoSuchMessageException var5) {	
            return params != null && params.length > 0 ? (new MessageFormat(code != null ? code : "", LocaleContextHolder.getLocale())).format(params) : code;	
         }	
      }	
   }	
	
   public static void setLang(String lang, HttpServletRequest request, HttpServletResponse response) {	
      if (StringUtils.isNotBlank(DictUtils.getDictLabel("sys_lang_type", lang, (String)null))) {	
         Locale a = null;	
	
         try {	
            a = LocaleUtils.toLocale(lang);	
         } catch (IllegalArgumentException var5) {	
            a = Locale.CHINA;	
         }	
	
         null.ALLATORIxDEMO().setLocale(request, response, a);	
      } else {	
         null.ALLATORIxDEMO().setLocale(request, response, Locale.CHINA);	
      }	
   }	
	
   public static String getFrontPath() {	
      return getProperty("frntPath");	
   }	
	
   public static String getProperty(String key, String defValue) {	
      String a;	
      return StringUtils.isBlank(a = getProperty(key)) ? defValue : a;	
   }	
	
   public static Global getInstance() {	
      return INSTANCE;	
   }	
	
   public static Boolean isUseCorpModel() {	
      return !ObjectUtils.toBoolean(j.ALLATORIxDEMO().get("fnSaas")) ? false : "true".equals(getProperty("user.useCorpModel"));	
   }	
	
   public static String getTablePrefix() {	
      return getProperty("jdbc.tablePrefix");	
   }	
	
   public static String getConfig(String key, String defValue) {	
      String a;	
      return StringUtils.isBlank(a = getConfig(key)) ? defValue : a;	
   }	
	
   public static boolean isTestProfileActive() {	
      Environment a = (Environment)SpringUtils.getBean(Environment.class);	
      return StringUtils.inString("test", a.getActiveProfiles());	
   }	
	
   public static String getLang() {	
      Locale a = null;	
      HttpServletRequest a;	
      if ((a = ServletUtils.getRequest()) != null) {	
         a = null.ALLATORIxDEMO().resolveLocale(a);	
      }	
	
      return a != null ? a.toString() : Locale.CHINA.toString();	
   }	
	
   public static void assertDemoMode() {	
      if (ObjectUtils.toBoolean(getProperty("demoMode"))) {	
         throw new RuntimeException("msg:演示模式，不允许操作！");	
      }	
   }	
	
   public static String getAdminPath() {	
      return getProperty("adminPath");	
   }	
	
   public static String getConfig(String key) {	
      String a;	
      if ((a = (String)props.get(key)) == null) {	
         if ((a = PropertiesUtils.getInstance().getProperty(key)) == null) {	
            if ((a = ConfigUtils.getConfig(key).getConfigValue()) != null) {	
               props.put(key, "read_sys_cnfig_table");	
               return a;	
            }	
	
            if ("productVersion".equals(key)) {	
               a = (new StringBuilder()).insert(0, "V").append(com.jeesite.common.d.l.ALLATORIxDEMO()).toString();	
               props.put(key, a);	
               return a;	
            }	
	
            a = "";	
            props.put(key, a);	
            return a;	
         }	
	
         props.put(key, a);	
      }	
	
      if ("read_sys_config_table".equals(a)) {	
         a = ConfigUtils.getConfig(key).getConfigValue();	
      }	
	
      return a;	
   }	
	
   public static Object getConst(String field) {	
      try {	
         if (!"Glbal.Fields".equals(field)) {	
            Object a;	
            if ((a = Global.class.getField(field).get((Object)null)) == null) {	
               a = DataEntity.class.getField(field).get((Object)null);	
            }	
	
            return a;	
         } else {	
            String a;	
            if ((a = (String)props.get((new StringBuilder()).insert(0, "__").append(field).toString())) == null) {	
               StringBuilder a = new StringBuilder();	
               Field[] var3;	
               int var4 = (var3 = Global.class.getFields()).length;	
	
               int var10000;	
               StringBuilder var10001;	
               String var10002;	
               int var5;	
               Field a;	
               for(var10000 = var5 = 0; var10000 < var4; var10000 = var5) {	
                  a = var3[var5];	
                  var10001 = (new StringBuilder()).append(a.getName()).append(":'").append(a.get((Object)null));	
                  var10002 = "',";	
                  ++var5;	
                  a.append(var10001.append(var10002).toString());	
               }	
	
               var4 = (var3 = DataEntity.class.getFields()).length;	
	
               for(var10000 = var5 = 0; var10000 < var4; var10000 = var5) {	
                  a = var3[var5];	
                  var10001 = (new StringBuilder()).insert(0, a.getName()).append(":'").append(a.get((Object)null));	
                  var10002 = "',";	
                  ++var5;	
                  a.append(var10001.append(var10002).toString());	
               }	
	
               int a;	
               a = (a = a.length()) > 1 ? a.substring(0, a - 1) : "";	
               props.put((new StringBuilder()).insert(0, "__").append(field).toString(), a);	
            }	
	
            return a;	
         }	
      } catch (Exception var7) {	
         return null;	
      }	
   }	
	
   public static String getDbName() {	
      String a = getJdbcInfo("type");	
      return "highg".equals(a) ? "oracle" : a;	
   }	
	
   public static String getJdbcInfo(String key) {	
      String a = DataSourceHolder.getDataSourceName();	
      String a;	
      String a;	
      if (StringUtils.isBlank(a = getProperty(a = (new StringBuilder()).insert(0, "jdbc").append(a == null ? "" : (new StringBuilder()).insert(0, ".").append(a).toString()).append(".").append(key).toString()))) {	
         throw new RuntimeException((new StringBuilder()).insert(0, "properties 文件中缺少 ").append(a).append(" 数据源参数配置.").toString());	
      } else {	
         return a;	
      }	
   }	
	
   public static void clearCache() {	
      PropertiesUtils.releadInstance();	
      ConfigUtils.clearCache();	
      props.clear();	
   }	
	
   public static String getPropertyDecodeAndEncode(String ascKey, String key, String value) {	
      if (value == null) {	
         value = getProperty(key);	
      }	
	
      try {	
         value = AesUtils.decode(value, ascKey);	
         return value;	
      } catch (Exception var21) {	
         String a = AesUtils.encode(value, ascKey);	
         String[] var5;	
         int var6 = (var5 = PropertiesUtils.DEFAULT_CONFIG_FILE).length;	
	
         int var7;	
         for(int var10000 = var7 = 0; var10000 < var6; var10000 = var7) {	
            String a = var5[var7];	
	
            try {	
               Resource a;	
               if ((a = ResourceUtils.getResource(a)).exists()) {	
                  File a = a.getFile();	
                  String a = FileUtils.getFileExtension(a);	
                  if ("properties".equals(a)) {	
                     String a = FileUtils.readFileToString(a, "UTF-8");	
                     FileUtils.writeStringToFile(a, a.replaceAll("(\n" + key + "=)(.*)", (new StringBuilder()).insert(0, "$1").append(a).toString()), "UTF-8");	
                     logger.debug((new StringBuilder()).insert(0, "Update ").append(a.getPath()).append(" ").append(key).append(" success.").toString());	
                  } else if ("yml".equals(a)) {	
                     StringBuilder a = new StringBuilder(FileUtils.readFileToString(a, "UTF-8"));	
                     int a = 0;	
                     String[] a = StringUtils.split(key, ".");	
	
                     for(int a = 0; a < a.length; ++a) {	
                        String a = a[a];	
                        String a = "";	
	
                        int a;	
                        for(var10000 = a = 0; var10000 < a * 2; var10000 = a) {	
                           StringBuilder var23 = (new StringBuilder()).insert(0, a);	
                           String var10001 = " ";	
                           ++a;	
                           a = var23.append(var10001).toString();	
                        }	
	
                        a = (new StringBuilder()).insert(0, "\n").append(a).append(a).append(":").toString();	
                        if ((a = a.indexOf(a, a)) != -1 && a == a.length - 1) {	
                           a = a.indexOf(":", a);	
                           int a = a.indexOf("\n", a);	
                           a.replace(a, a, (new StringBuilder()).insert(0, ": ").append(a).toString());	
                           FileUtils.writeStringToFile(a, a.toString(), "UTF-8");	
                           logger.debug((new StringBuilder()).insert(0, "Update ").append(a.getPath()).append(" ").append(key).append(" encryptin success.").toString());	
                        }	
                     }	
                  }	
               }	
            } catch (Exception var20) {	
               if (!(var20 instanceof FileNotFoundException)) {	
                  logger.error((new StringBuilder()).insert(0, "Update ").append(a).append(" encryption failure. ").toString(), var20);	
               }	
            }	
	
            ++var7;	
         }	
	
         return value;	
      }	
   }	
	
   public static String getUserfilesBaseDir(String path) {	
      String var10000;	
      String a;	
      if (StringUtils.isBlank(a = getProperty("file.baseDir"))) {	
         try {	
            a = ServletUtils.getRequest().getSession().getServletContext().getRealPath("/");	
         } catch (Exception var3) {	
            return "";	
         }	
	
         var10000 = a;	
      } else {	
         var10000 = a;	
      }	
	
      if (!var10000.endsWith("/")) {	
         a = (new StringBuilder()).insert(0, a).append("/").toString();	
      }	
	
      return path != null ? FileUtils.path((new StringBuilder()).insert(0, a).append("/userfiles/").append(path).toString()) : FileUtils.path(a);	
   }	
	
   public static String getProperty(String key) {	
      String a;	
      if ((a = (String)props.get(key)) == null) {	
         if ((a = PropertiesUtils.getInstance().getProperty(key)) != null) {	
            props.put(key, a);	
            return a;	
         }	
	
         if ("productVersion".equals(key)) {	
            a = (new StringBuilder()).insert(0, "V").append(com.jeesite.common.d.l.ALLATORIxDEMO()).toString();	
            props.put(key, a);	
            return a;	
         }	
	
         a = "";	
         props.put(key, a);	
      }	
	
      return a;	
   }	
	
   public static String getJdbcType() {	
      return getJdbcInfo("type");	
   }	
}	
