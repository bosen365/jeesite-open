package com.jeesite.modules.sys.entity;	
	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.io.ResourceUtils;	
import com.jeesite.common.l.l.j;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.query.QueryType;	
import com.jeesite.common.shiro.realm.LoginInfo;	
import java.io.IOException;	
import java.io.InputStream;	
import org.apache.commons.io.Charsets;	
import org.apache.commons.io.IOUtils;	
import org.apache.commons.lang3.StringUtils;	
import org.hibernate.validator.constraints.Length;	
import org.hibernate.validator.constraints.NotBlank;	
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
   private String moduleName;	
   public static final String MODULE_CMS = "cms";	
   public static final String MODULE_MSG = "msg";	
   private String currentVersion;	
   private static final long serialVersionUID = 1L;	
   public static final String MODULE_CORE = "core";	
   private String moduleCode;	
   private String description;	
   private String upgradeInfo;	
   private String[] versions;	
   private String mainClassName;	
	
   public void setMainClassName(String mainClassName) {	
      this.mainClassName = mainClassName;	
   }	
	
   public Module() {	
      this((String)null);	
   }	
	
   public void setCurrentVersion(String currentVersion) {	
      this.currentVersion = currentVersion;	
   }	
	
   @Length(	
      min = 0,	
      max = 500,	
      message = "模块描述长度不能超过 500 个字符"	
   )	
   public String getDescription() {	
      return this.description;	
   }	
	
   public Boolean getIsEnable() {	
      return this.getIsLoader() && "0".equals(this.status);	
   }	
	
   public Module(String id) {	
      super(id);	
      this.versions = null;	
   }	
	
   @Length(	
      min = 0,	
      max = 300,	
      message = "升级信息长度不能超过 300 个字符"	
   )	
   public String getUpgradeInfo() {	
      return this.upgradeInfo;	
   }	
	
   public void setUpgradeInfo(String upgradeInfo) {	
      this.upgradeInfo = upgradeInfo;	
   }	
	
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
	
   public void setDescription(String description) {	
      this.description = description;	
   }	
	
   @Length(	
      min = 0,	
      max = 50,	
      message = "当前版本长度不能超过 50 个字符"	
   )	
   public String getCurrentVersion() {	
      return this.currentVersion;	
   }	
	
   public synchronized String getLastVersion() {	
      String[] a;	
      return (a = this.getVersions()).length == 0 ? "未知版本号" : a[a.length - 1];	
   }	
	
   public Boolean getIsLoader() {	
      try {	
         return StringUtils.isNotBlank(this.mainClassName) ? Class.forName(this.mainClassName) != null : false;	
      } catch (ClassNotFoundException var2) {	
         return false;	
      }	
   }	
	
   public synchronized String[] getVersions() {	
      Module var10000;	
      Resource a;	
      if (this.versions == null && (a = ResourceUtils.getResource((new StringBuilder()).insert(0, "/db/upgrade/").append(this.moduleCode).append("/versions").toString())).exists()) {	
         label71: {	
            InputStream a = null;	
	
            label62: {	
               try {	
                  try {	
                     String a = IOUtils.toString(a = a.getInputStream(), Charsets.toCharset("UTF-8"));	
                     this.versions = StringUtils.split(a, "\r\n");	
                     break label62;	
                  } catch (IOException var7) {	
                     var7.printStackTrace();	
                  }	
               } catch (Throwable var8) {	
                  IOUtils.closeQuietly(a);	
                  throw var8;	
               }	
	
               var10000 = this;	
               IOUtils.closeQuietly(a);	
               break label71;	
            }	
	
            var10000 = this;	
            IOUtils.closeQuietly(a);	
         }	
      } else {	
         var10000 = this;	
      }	
	
      if (var10000.versions == null) {	
         String[] var10001 = new String[0];	
         boolean var10003 = true;	
         this.versions = var10001;	
      }	
	
      return this.versions;	
   }	
	
   public void setModuleCode(String moduleCode) {	
      this.moduleCode = moduleCode;	
   }	
	
   public void setModuleName(String moduleName) {	
      this.moduleName = moduleName;	
   }	
	
   @Length(	
      min = 0,	
      max = 500,	
      message = "主类全名长度不能超过 500 个字符"	
   )	
   public String getMainClassName() {	
      return this.mainClassName;	
   }	
	
   public String getModuleCode() {	
      return this.moduleCode;	
   }	
}	
