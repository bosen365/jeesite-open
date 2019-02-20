/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean	
 *  org.springframework.boot.autoconfigure.condition.ConditionalOnProperty	
 *  org.springframework.context.annotation.Bean	
 *  org.springframework.context.annotation.Configuration	
 *  org.springframework.context.annotation.DependsOn	
 */	
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
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = 5 << 4 ^ (2 << 2 ^ 3);	
        int n4 = n2;	
        int n5 = 4 << 3 ^ 5;	
        4 << 4 ^ 3 << 1;	
        while (n4 >= 0) {	
            int n6 = n2--;	
            arrc[n6] = (char)(s.charAt(n6) ^ n5);	
            if (n2 < 0) break;	
            int n7 = n2--;	
            arrc[n7] = (char)(s.charAt(n7) ^ n3);	
            n4 = n2;	
        }	
        return new String(arrc);	
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
    @DependsOn(value={"dbUpgrade"})	
    @ConditionalOnMissingBean	
    @ConditionalOnProperty(name={"lang.enabled"}, havingValue="true", matchIfMissing=true)	
    public LangService langService() {	
        return new LangServiceSupport();	
    }	
	
    @Bean	
    @ConditionalOnMissingBean	
    public ModuleService moduleService() {	
        return new ModuleServiceSupport();	
    }	
}	
	
