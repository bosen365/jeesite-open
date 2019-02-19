package com.jeesite.autoconfigure.sys;	
	
import com.jeesite.modules.sys.service.ConfigService;	
import com.jeesite.modules.sys.service.DataScopeService;	
import com.jeesite.modules.sys.service.DictDataService;	
import com.jeesite.modules.sys.service.DictTypeService;	
import com.jeesite.modules.sys.service.LangService;	
import com.jeesite.modules.sys.service.MenuService;	
import com.jeesite.modules.sys.service.ModuleService;	
import com.jeesite.modules.sys.service.RoleService;	
import com.jeesite.modules.sys.service.UserService;	
import com.jeesite.modules.sys.service.support.ConfigServiceSupport;	
import com.jeesite.modules.sys.service.support.DataScopeServiceSupport;	
import com.jeesite.modules.sys.service.support.DictDataServiceSupport;	
import com.jeesite.modules.sys.service.support.DictTypeServiceSupport;	
import com.jeesite.modules.sys.service.support.LangServiceSupport;	
import com.jeesite.modules.sys.service.support.MenuServiceSupport;	
import com.jeesite.modules.sys.service.support.ModuleServiceSupport;	
import com.jeesite.modules.sys.service.support.RoleServiceSupport;	
import com.jeesite.modules.sys.service.support.UserServiceSupport;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;	
import org.springframework.context.annotation.Bean;	
import org.springframework.context.annotation.Configuration;	
import org.springframework.context.annotation.DependsOn;	
	
@Configuration	
public class Sys0AutoConfiguration {	
   public static String ALLATORIxDEMO(String s) {	
      int var10000 = 4 << 4 ^ 3 << 1;	
      int var10001 = 4 << 3 ^ 5;	
      int var10002 = 5 << 4 ^ 2 << 2 ^ 3;	
      int var10003 = (s = (String)s).length();	
      char[] var10004 = new char[var10003];	
      boolean var10006 = true;	
      int var5 = var10003 - 1;	
      var10003 = var10002;	
      int var3;	
      var10002 = var3 = var5;	
      char[] var1 = var10004;	
      int var4 = var10003;	
      var10000 = var10002;	
	
      for(int var2 = var10001; var10000 >= 0; var10000 = var3) {	
         var10001 = var3;	
         char var6 = s.charAt(var3);	
         --var3;	
         var1[var10001] = (char)(var6 ^ var2);	
         if (var3 < 0) {	
            break;	
         }	
	
         var10002 = var3--;	
         var1[var10002] = (char)(s.charAt(var10002) ^ var4);	
      }	
	
      return new String(var1);	
   }	
	
   @Bean	
   @ConditionalOnMissingBean	
   public ConfigService configService() {	
      return new ConfigServiceSupport();	
   }	
	
   @Bean	
   @ConditionalOnMissingBean	
   public DataScopeService dataScopeService() {	
      return new DataScopeServiceSupport();	
   }	
	
   @Bean	
   @ConditionalOnMissingBean	
   public MenuService menuService() {	
      return new MenuServiceSupport();	
   }	
	
   @Bean	
   @ConditionalOnMissingBean	
   public DictTypeService dictTypeService() {	
      return new DictTypeServiceSupport();	
   }	
	
   @Bean	
   @ConditionalOnMissingBean	
   public RoleService roleService() {	
      return new RoleServiceSupport();	
   }	
	
   @Bean	
   @ConditionalOnMissingBean	
   public DictDataService dictDataService() {	
      return new DictDataServiceSupport();	
   }	
	
   @Bean	
   @ConditionalOnMissingBean	
   public UserService userService() {	
      return new UserServiceSupport();	
   }	
	
   @Bean	
   @DependsOn({"dbUpgrade"})	
   @ConditionalOnMissingBean	
   @ConditionalOnProperty(	
      name = {"lang.enabled"},	
      havingValue = "true",	
      matchIfMissing = true	
   )	
   public LangService langService() {	
      return new LangServiceSupport();	
   }	
	
   @Bean	
   @ConditionalOnMissingBean	
   public ModuleService moduleService() {	
      return new ModuleServiceSupport();	
   }	
}	
