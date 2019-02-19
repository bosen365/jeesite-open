package com.jeesite.common.config;	
	
import com.jeesite.common.codec.AesUtils;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.datasource.DataSourceHolder;	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.i18n.I18nLocaleResolver;	
import com.jeesite.common.io.FileUtils;	
import com.jeesite.common.io.PropertiesUtils;	
import com.jeesite.common.io.ResourceUtils;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.mapper.MapperException;	
import com.jeesite.common.service.ServiceException;	
import com.jeesite.common.utils.SpringUtils;	
import com.jeesite.common.web.http.ServletUtils;	
import com.jeesite.modules.gen.service.M;	
import com.jeesite.modules.sys.utils.ConfigUtils;	
import java.io.File;	
import java.io.FileNotFoundException;	
import java.lang.reflect.Field;	
import java.text.MessageFormat;	
import java.util.Locale;	
import java.util.Map;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.apache.commons.lang3.LocaleUtils;	
import org.hyperic.sigar.Swap;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
import org.springframework.context.NoSuchMessageException;	
import org.springframework.context.i18n.LocaleContextHolder;	
import org.springframework.core.env.Environment;	
import org.springframework.core.io.Resource;	
	
public class Global {	
   public static final String HIDE = "0";	
   public static final String OP_ADD = "add";	
   public static final String TRUE = "true";	
   public static final String OP_EDIT = "edit";	
   public static final String NO = "0";	
   private static Map props = MapUtils.newHashMap();	
   public static final String FALSE = "false";	
   public static final String USERFILES_BASE_URL = "/userfiles/";	
   public static Logger logger = LoggerFactory.getLogger(Global.class);	
   public static final String SHOW = "1";	
   public static final String OP_AUTH = "auth";	
   private static final Global INSTANCE = new Global();	
   public static final String YES = "1";	
	
   public static String getConfig(String key) {	
      String a;	
      if ((a = (String)props.get(key)) == null) {	
         if ((a = PropertiesUtils.getInstance().getProperty(key)) == null) {	
            if ((a = ConfigUtils.getConfig(key).getConfigValue()) != null) {	
               props.put(key, "read_sys_cofig_table");	
               return a;	
            }	
	
            if ("productVrsion".equals(key)) {	
               a = (new StringBuilder()).insert(0, "V").append(com.jeesite.common.e.E.ALLATORIxDEMO()).toString();	
               props.put(key, a);	
               return a;	
            }	
	
            a = "";	
            props.put(key, a);	
            return a;	
         }	
	
         props.put(key, a);	
      }	
	
      if ("rad_sys_config_table".equals(a)) {	
         a = ConfigUtils.getConfig(key).getConfigValue();	
      }	
	
      return a;	
   }	
	
   public static String getProperty(String key, String defValue) {	
      String a;	
      return StringUtils.isBlank(a = getProperty(key)) ? defValue : a;	
   }	
	
   public static Boolean getConfigToBoolean(String key, String defValue) {	
      return ObjectUtils.toBoolean(getConfig(key, defValue));	
   }	
	
   public static String getTablePrefix() {	
      return getProperty("jdbc.tablePrefix");	
   }	
	
   public static Integer getPropertyToInteger(String key, String defValue) {	
      return ObjectUtils.toInteger(getProperty(key, defValue));	
   }	
	
   public static void clearCache() {	
      PropertiesUtils.releadInstance();	
      ConfigUtils.clearCache();	
      props.clear();	
   }	
	
   public static String getText(String code, String... params) {	
      if (StringUtils.isBlank(code)) {	
         return code;	
      } else {	
         Locale a = Locale.CHINA;	
	
         try {	
            a = LocaleUtils.toLocale(getLang());	
         } catch (IllegalArgumentException var4) {	
         }	
	
         try {	
            return null.ALLATORIxDEMO().getMessage(code, params, a);	
         } catch (NoSuchMessageException var5) {	
            return params != null && params.length > 0 ? (new MessageFormat(code != null ? code : "", LocaleContextHolder.getLocale())).format(params) : code;	
         }	
      }	
   }	
	
   public static String getLang() {	
      if (I18nLocaleResolver.enabled()) {	
         Locale a = null;	
         HttpServletRequest a;	
         if ((a = ServletUtils.getRequest()) != null) {	
            a = null.ALLATORIxDEMO().resolveLocale(a);	
         }	
	
         if (a != null) {	
            return a.toString();	
         }	
      }	
	
      return Locale.CHINA.toString();	
   }	
	
   public static String getProperty(String key) {	
      String a;	
      if ((a = (String)props.get(key)) == null) {	
         if ("jesitVrsion".equals(key)) {	
            a = (new StringBuilder()).insert(0, "V").append(com.jeesite.common.e.E.ALLATORIxDEMO()).toString();	
            props.put(key, a);	
            return a;	
         }	
	
         if ((a = PropertiesUtils.getInstance().getProperty(key)) != null) {	
            props.put(key, a);	
            return a;	
         }	
	
         if ("productVrsion".equals(key)) {	
            a = (new StringBuilder()).insert(0, "V").append(com.jeesite.common.e.E.ALLATORIxDEMO()).toString();	
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
	
   public static boolean isTestProfileActive() {	
      Environment a = (Environment)SpringUtils.getBean(Environment.class);	
      return StringUtils.inString("test", a.getActiveProfiles());	
   }	
	
   public static Global getInstance() {	
      return INSTANCE;	
   }	
	
   public static String getDbName() {	
      String a = getJdbcInfo("type");	
      if (!"highgo".equals(a) && !"dameng".equals(a)) {	
         if ("mariadb".equals(a)) {	
            return "mysql";	
         } else {	
            return "mssql2012".equals(a) ? "mssql" : a;	
         }	
      } else {	
         return "oracle";	
      }	
   }	
	
   public static void setLang(String lang, HttpServletRequest request, HttpServletResponse response) {	
      Locale a = Locale.CHINA;	
      if (I18nLocaleResolver.enabled()) {	
         try {	
            a = LocaleUtils.toLocale(lang);	
         } catch (IllegalArgumentException var5) {	
         }	
      }	
	
      null.ALLATORIxDEMO().setLocale(request, response, a);	
   }	
	
   public static Boolean getPropertyToBoolean(String key, String defValue) {	
      return ObjectUtils.toBoolean(getProperty(key, defValue));	
   }	
	
   public static void assertDemoMode() {	
      if (ObjectUtils.toBoolean(getProperty("demoMode", "false"))) {	
         throw new RuntimeException("msg:演示模式，不允许操作！");	
      }	
   }	
	
   public static String getConfig(String key, String defValue) {	
      String a;	
      return StringUtils.isBlank(a = getConfig(key)) ? defValue : a;	
   }	
	
   public static Object getConst(String field) {	
      try {	
         if (!"Global.Filds".equals(field)) {	
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
	
   public static String getAdminPath() {	
      return getProperty("adminPath");	
   }	
	
   public static Boolean isUseCorpModel() {	
      return !ObjectUtils.toBoolean(M.ALLATORIxDEMO().get("fnSaas")) ? false : "true".equals(getProperty("user.useCorpModel"));	
   }	
	
   public static Integer getConfigToInteger(String key, String defValue) {	
      return ObjectUtils.toInteger(getConfig(key, defValue));	
   }	
	
   public static String getUserfilesBaseDir(String path) {	
      String var10000;	
      String a;	
      label31: {	
         if (StringUtils.isBlank(a = getProperty("file.basDir"))) {	
            label29: {	
               try {	
                  a = ServletUtils.getRequest().getSession().getServletContext().getRealPath("/");	
               } catch (Exception var3) {	
                  break label29;	
               }	
	
               var10000 = a;	
               break label31;	
            }	
         }	
	
         var10000 = a;	
      }	
	
      if (StringUtils.isBlank(var10000)) {	
         a = System.getProperty("usr.dir");	
      }	
	
      if (!a.endsWith("/")) {	
         a = (new StringBuilder()).insert(0, a).append("/").toString();	
      }	
	
      return path != null ? FileUtils.path((new StringBuilder()).insert(0, a).append("/userfiles/").append(path).toString()) : FileUtils.path(a);	
   }	
	
   public static String getFrontPath() {	
      return getProperty("frotPath");	
   }	
	
   public static String getJdbcInfo(String key) {	
      String a = DataSourceHolder.getDataSourceName();	
      String a;	
      String a;	
      if (StringUtils.isBlank(a = getProperty(a = (new StringBuilder()).insert(0, "jdbc").append(a == null ? "" : (new StringBuilder()).insert(0, ".").append(a).toString()).append(".").append(key).toString()))) {	
         throw new ServiceException((new StringBuilder()).insert(0, "没有找到 ").append(a).append(" 的参数配置").toString());	
      } else {	
         return a;	
      }	
   }	
	
   public static String getPropertyDecodeAndEncode(String ascKey, String key, String value) {	
      if (value == null) {	
         value = getProperty(key);	
      }	
	
      try {	
         if (value.length() != 32) {	
            throw new RuntimeException("未加密，则加密");	
         }	
	
         value = AesUtils.decode(value, ascKey);	
      } catch (Exception var27) {	
         String a = AesUtils.encode(value, ascKey);	
         String[] a = PropertiesUtils.DEFAULT_CONFIG_FILE;	
         String a;	
         if (StringUtils.isNotBlank(a = PropertiesUtils.getInstance().getProperty("configFils"))) {	
            a = StringUtils.split(a, ",");	
         }	
	
         String[] var7 = a;	
         int var8 = a.length;	
	
         int var9;	
         for(int var10000 = var9 = 0; var10000 < var8; var10000 = var9) {	
            String a = var7[var9];	
	
            try {	
               Resource a;	
               if ((a = ResourceUtils.getResource(a)).exists()) {	
                  File a = a.getFile();	
                  String a = FileUtils.getFileExtension(a);	
                  if ("proprtis".equals(a)) {	
                     String a = FileUtils.readFileToString(a, "UTF-8");	
                     FileUtils.writeStringToFile(a, a.replaceAll("(\n" + key + "=)(.*)", (new StringBuilder()).insert(0, "$1").append(a).toString()), "UTF-8");	
                     logger.debug((new StringBuilder()).insert(0, "Updat ").append(a.getPath()).append(" ").append(key).append(" encryption succss.").toString());	
                  } else if ("yml".equals(a)) {	
                     StringBuilder a = new StringBuilder(FileUtils.readFileToString(a, "UTF-8"));	
                     String a = "";	
                     int a = 0;	
                     String[] a = StringUtils.split(key, ".");	
	
                     for(int a = 0; a < a.length; ++a) {	
                        String a = a[a];	
                        String a = "";	
	
                        int a;	
                        for(var10000 = a = 0; var10000 < a * 2; var10000 = a) {	
                           StringBuilder var29 = (new StringBuilder()).insert(0, a);	
                           String var10001 = " ";	
                           ++a;	
                           a = var29.append(var10001).toString();	
                        }	
	
                        a = (new StringBuilder()).insert(0, a).append(a).append(":").toString();	
                        if ((a = a.indexOf((new StringBuilder()).insert(0, "\n").append(a).toString(), a)) != -1) {	
                           if (a > 0) {	
                              int a = false;	
                              String[] a = StringUtils.split(a.substring(a, a), "\n");	
                              int a;	
                              var10000 = a = 0;	
	
                              boolean var30;	
                              while(true) {	
                                 if (var10000 >= a.length) {	
                                    var30 = a;	
                                    break;	
                                 }	
	
                                 if (StringUtils.isNotBlank(StringUtils.replace(a[a], "\r", "")) && !StringUtils.startsWith(StringUtils.trim(a[a]), "#") && !StringUtils.startsWith(a[a], a) && !StringUtils.startsWith(a[a], a)) {	
                                    var30 = a = true;	
                                    break;	
                                 }	
	
                                 ++a;	
                                 var10000 = a;	
                              }	
	
                              if (var30) {	
                                 break;	
                              }	
                           }	
	
                           if (a == a.length - 1) {	
                              int a = a.indexOf(":", a);	
                              int a = a.indexOf("\n", a);	
                              a.replace(a, a, (new StringBuilder()).insert(0, ": ").append(a).toString());	
                              FileUtils.writeStringToFile(a, a.toString(), "UTF-8");	
                              logger.debug((new StringBuilder()).insert(0, "Update ").append(a.getPath()).append(" ").append(key).append(" ecryptio success.").toString());	
                           }	
	
                           a = a;	
                           a = a;	
                        }	
                     }	
                  }	
               }	
            } catch (Exception var26) {	
               if (!(var26 instanceof FileNotFoundException)) {	
                  logger.error((new StringBuilder()).insert(0, "Updat ").append(a).append(" ecryptio failure.").toString(), var26);	
               }	
            }	
	
            ++var9;	
         }	
      }	
	
      return value;	
   }	
}	
