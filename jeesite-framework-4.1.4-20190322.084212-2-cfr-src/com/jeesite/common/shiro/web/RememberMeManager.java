/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.shiro.web;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.web.returnjson.ReturnJsonSerializer;	
import com.jeesite.modules.sys.web.AdviceController;	
import java.io.PrintStream;	
import java.security.Key;	
import org.apache.shiro.codec.Base64;	
import org.apache.shiro.crypto.AesCipherService;	
import org.apache.shiro.web.mgt.CookieRememberMeManager;	
	
public class RememberMeManager	
extends CookieRememberMeManager {	
    public RememberMeManager() {	
        RememberMeManager rememberMeManager = this;	
        rememberMeManager.setCipherKey(Base64.decode(Global.getProperty("shiro.rememberMe.secretKey", "r0e3c16IdVkouZgk1TKVMg==")));	
    }	
	
    public static void main(String[] arrstring) {	
        System.out.println("\n################################################\n#                                              #\n#        ## #   #    ## ### ### ##  ###        #\n#       # # #   #   # #  #  # # # #  #         #\n#       ### #   #   ###  #  # # ##   #         #\n#       # # ### ### # #  #  ### # # ###        #\n#                                              #\n# Obfuscation by Allatori Obfuscator v6.7 DEMO #\n#                                              #\n#           http://www.allatori.com            #\n#                                              #\n################################################\n");	
        System.out.println(new StringBuilder().insert(0, "shiro.rememberMe.secretKey = ").append(Base64.encodeToString(new AesCipherService().generateNewKey().getEncoded())).toString());	
    }	
}	
	
