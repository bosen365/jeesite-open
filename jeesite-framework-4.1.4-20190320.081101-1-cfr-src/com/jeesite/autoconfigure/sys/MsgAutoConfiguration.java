/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.autoconfigure.sys;	
	
import com.jeesite.modules.msg.service.MsgPushService;	
import com.jeesite.modules.msg.service.MsgTemplateService;	
import com.jeesite.modules.msg.service.support.MsgPushServiceSupport;	
import com.jeesite.modules.msg.service.support.MsgTemplateServiceSupport;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;	
import org.springframework.context.annotation.Bean;	
import org.springframework.context.annotation.Configuration;	
	
@Configuration	
@ConditionalOnProperty(name={"msg.enabled"}, havingValue="true", matchIfMissing=true)	
public class MsgAutoConfiguration {	
    @Bean	
    @ConditionalOnMissingBean	
    public MsgPushService msgPushService() {	
        return new MsgPushServiceSupport();	
    }	
	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = 5 << 3 ^ 3;	
        int n4 = n2;	
        4 << 3;	
        int n5 = 3 << 3 ^ 3;	
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
    public MsgTemplateService msgTemplateService() {	
        return new MsgTemplateServiceSupport();	
    }	
}	
	
