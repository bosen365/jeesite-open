/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.common.i18n;	
	
import com.jeesite.autoconfigure.sys.MsgAutoConfiguration;	
import com.jeesite.common.beetl.d.c;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.j2cache.autoconfigure.J2CacheSpringRedisAutoConfiguration;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.mybatis.v.m;	
import java.util.Locale;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.springframework.context.i18n.LocaleContext;	
	
public class I18nLocaleResolver	
extends c {	
    private static Boolean enabled;	
	
    public static boolean enabled() {	
        if (enabled == null) {	
            enabled = Global.getPropertyToBoolean("lang.enabled", "true") == false ? Boolean.valueOf(false) : (ObjectUtils.toBoolean(m.ALLATORIxDEMO().get("fnI18n")) == false ? Boolean.valueOf(false) : Boolean.valueOf(true));	
        }	
        return enabled;	
    }	
	
    @Override	
    public Locale resolveLocale(HttpServletRequest request) {	
        return super.resolveLocale(request);	
    }	
	
    @Override	
    public LocaleContext resolveLocaleContext(HttpServletRequest request) {	
        return super.resolveLocaleContext(request);	
    }	
	
    @Override	
    public void setLocaleContext(HttpServletRequest request, HttpServletResponse response, LocaleContext localeContext) {	
        super.setLocaleContext(request, response, localeContext);	
    }	
	
    @Override	
    protected Locale determineDefaultLocale(HttpServletRequest request) {	
        return super.determineDefaultLocale(request);	
    }	
}	
	
