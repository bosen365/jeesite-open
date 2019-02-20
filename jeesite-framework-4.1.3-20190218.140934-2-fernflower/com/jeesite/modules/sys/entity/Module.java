package com.jeesite.modules.sys.entity;	
	
import com.fasterxml.jackson.annotation.JsonIgnore;	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.io.ResourceUtils;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.query.QueryType;	
import com.jeesite.common.mybatis.mapper.query.QueryWhere;	
import java.io.IOException;	
import java.io.InputStream;	
import javax.validation.constraints.NotBlank;	
import org.apache.commons.io.Charsets;	
import org.apache.commons.io.IOUtils;	
import org.apache.commons.lang3.StringUtils;	
import org.hibernate.validator.constraints.Length;	
import org.hyperic.sigar.NfsClientV3;	
import org.springframework.core.io.Resource;	
	
@Table(	
   name = "${_prefix}sys_module",	
   columns = {@Column(	
   includeEntity = DataEntity.class	
), @Column(	
   name = "module_code",	
   attrName = "moduleCode",	
   label = "模块编码",	
   isPK = true	
), @Column(	
   name = "module_name",	
   attrName = "moduleName",	
   label = "模块名称",	
   queryType = QueryType.LIKE	
), @Column(	
   name = "description",	
   attrName = "description",	
   label = "模块描述"	
), @Column(	
   name = "main_class_name",	
   attrName = "mainClassName",	
   label = "主类全名",	
   queryType = QueryType.LIKE	
), @Column(	
   name = "current_version",	
   attrName = "currentVersion",	
   label = "当前版本"	
), @Column(	
   name = "upgrade_info",	
   attrName = "upgradeInfo",	
   label = "升级信息"	
)},	
   orderBy = "a.update_date DESC"	
)	
public class Module extends DataEntity {	
   private String currentVersion;	
   private String moduleCode;	
   public static final String MODULE_MSG = "msg";	
   private String moduleName;	
   private String mainClassName;	
   private String[] versions;	
   public static final String MODULE_CORE = "core";	
   private String upgradeInfo;	
   private String description;	
   public static final String MODULE_CMS = "cms";	
   private static final long serialVersionUID = 1L;	
	
   @NotBlank(	
      message = "模块名称不能为空"	
   )	
   @Length(	
      min = 0,	
      max = 100,	
      message = "模块名称长度不能超过 100 个字符"	
   )	
   public String getModuleName() {	
      return this.moduleName;	
   }	
	
   @JsonIgnore	
   public synchronized String[] getVersions() {	
      Resource a;	
      if (this.versions == null && (a = ResourceUtils.getResource((new StringBuilder()).insert(0, "/db/upgrade/").append(this.moduleCode).append("/versions").toString())).exists()) {	
         try {	
            InputStream a = a.getInputStream();	
            Object var3 = null;	
	
            try {	
               try {	
                  String a = IOUtils.toString(a, Charsets.toCharset("UTF-8"));	
                  this.versions = StringUtils.split(a, "\r\n");	
               } catch (Throwable var13) {	
                  throw var13;	
               }	
            } catch (Throwable var15) {	
               Throwable var10000;	
               if (a != null) {	
                  if (var3 != null) {	
                     try {	
                        a.close();	
                     } catch (Throwable var14) {	
                        var10000 = var15;	
                        ((Throwable)var3).addSuppressed(var14);	
                        throw var10000;	
                     }	
	
                     var10000 = var15;	
                     throw var10000;	
                  }	
	
                  a.close();	
               }	
	
               var10000 = var15;	
               throw var10000;	
            }	
	
            if (a != null) {	
               if (var3 != null) {	
                  try {	
                     a.close();	
                  } catch (Throwable var12) {	
                     ((Throwable)var3).addSuppressed(var12);	
                  }	
               } else {	
                  a.close();	
               }	
            }	
         } catch (IOException var16) {	
            var16.printStackTrace();	
         }	
      }	
	
      if (this.versions == null) {	
         String[] var10001 = new String[0];	
         boolean var10003 = true;	
         this.versions = var10001;	
      }	
	
      return this.versions;	
   }	
	
   public Module() {	
      this((String)null);	
   }	
	
   public void setDescription(String description) {	
      this.description = description;	
   }	
	
   public void setModuleCode(String moduleCode) {	
      this.moduleCode = moduleCode;	
   }	
	
   public void setMainClassName(String mainClassName) {	
      this.mainClassName = mainClassName;	
   }	
	
   @Length(	
      min = 0,	
      max = 500,	
      message = "主类全名长度不能超过 500 个字符"	
   )	
   public String getMainClassName() {	
      return this.mainClassName;	
   }	
	
   @Length(	
      min = 0,	
      max = 500,	
      message = "模块描述长度不能超过 500 个字符"	
   )	
   public String getDescription() {	
      return this.description;	
   }	
	
   @Length(	
      min = 0,	
      max = 300,	
      message = "升级信息长度不能超过 300 个字符"	
   )	
   public String getUpgradeInfo() {	
      return this.upgradeInfo;	
   }	
	
   public void setModuleName(String moduleName) {	
      this.moduleName = moduleName;	
   }	
	
   public void setCurrentVersion(String currentVersion) {	
      this.currentVersion = currentVersion;	
   }	
	
   public Module(String id) {	
      super(id);	
      this.versions = null;	
   }	
	
   @Length(	
      min = 0,	
      max = 50,	
      message = "当前版本长度不能超过 50 个字符"	
   )	
   public String getCurrentVersion() {	
      return this.currentVersion;	
   }	
	
   public void setUpgradeInfo(String upgradeInfo) {	
      this.upgradeInfo = upgradeInfo;	
   }	
	
   public synchronized String getLastVersion() {	
      String[] a;	
      return (a = this.getVersions()).length == 0 ? "未知版本号" : a[a.length - 1];	
   }	
	
   public String getModuleCode() {	
      return this.moduleCode;	
   }	
	
   public Boolean getIsLoader() {	
      try {	
         return StringUtils.isNotBlank(this.mainClassName) ? Class.forName(this.mainClassName) != null : false;	
      } catch (ClassNotFoundException var2) {	
         return false;	
      }	
   }	
	
   public Boolean getIsEnable() {	
      return this.getIsLoader() && "0".equals(this.status);	
   }	
}	
