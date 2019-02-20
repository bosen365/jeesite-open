/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  org.apache.shiro.codec.Base64	
 *  org.apache.shiro.crypto.AesCipherService	
 *  org.apache.shiro.web.mgt.CookieRememberMeManager	
 */	
package com.jeesite.common.shiro.web;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.common.validator.ValidatorUtils;	
import java.io.PrintStream;	
import java.security.Key;	
import org.apache.shiro.codec.Base64;	
import org.apache.shiro.crypto.AesCipherService;	
import org.apache.shiro.web.mgt.CookieRememberMeManager;	
	
public class RememberMeManager	
extends CookieRememberMeManager {	
    public RememberMeManager() {	
        RememberMeManager rememberMeManager = this;	
        rememberMeManager.setCipherKey(Base64.decode((String)Global.getProperty("shiro.rememberMe.secretKey", "r0e3c1\IdVkouZgk1TKVMg==")));	
    }	
	
    public static void main(String[] arrstring) {	
        System.out.println("\n################################################\n#                                              #\n#        ## #   #    ## ### ### ##  ###        #\n#       # # #   #   # #  #  # # # #  #         #\n#       ### #   #   ###  #  # # ##   #         #\n#       # # ### ### # #  #  ### # # ###        #\n#                                              #\n# Obfuscation by Allatori Obfuscator v6.7 .EMO #\n#                                              #\n#           http://www.allatori.com            #\n#                                              #\n################################################\n");	
        System.out.println(new StringBuilder().insert(0, "shiro.rememberMe.secretKey = ").append(Base64.encodeToString((byte[])new AesCipherService().generateNewKey().getEncoded())).toString());	
    }	
}	
	
