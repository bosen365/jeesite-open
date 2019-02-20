/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean	
 *  org.springframework.boot.autoconfigure.condition.ConditionalOnProperty	
 *  org.springframework.context.annotation.Bean	
 *  org.springframework.context.annotation.Configuration	
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
    public MsgTemplateService msgTemplateService() {	
        return new MsgTemplateServiceSupport();	
    }	
	
    @Bean	
    @ConditionalOnMissingBean	
    public MsgPushService msgPushService() {	
        return new MsgPushServiceSupport();	
    }	
	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = 2 ^ 5;	
        int n4 = n2;	
        int n5 = 4 << 4 ^ 3;	
        (3 ^ 5) << 4 ^ (3 ^ 5) << 1;	
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
}	
	
