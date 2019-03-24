/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.i18n;	
	
import com.jeesite.common.beetl.l.h;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.mybatis.e.j;	
import java.util.Locale;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.hyperic.sigar.DirStat;	
import org.hyperic.sigar.test.GetPass;	
import org.springframework.context.i18n.LocaleContext;	
	
public class I18nLocaleResolver	
extends h {	
    private static Boolean enabled;	
	
    @Override	
    public Locale resolveLocale(HttpServletRequest request) {	
        return super.resolveLocale(request);	
    }	
	
    public static boolean enabled() {	
        if (enabled == null) {	
            enabled = Global.getPropertyToBoolean("lang.enabled", "true") == false ? Boolean.valueOf(false) : (ObjectUtils.toBoolean(j.ALLATORIxDEMO().get("fnI18n")) == false ? Boolean.valueOf(false) : Boolean.valueOf(true));	
        }	
        return enabled;	
    }	
	
    @Override	
    public void setLocaleContext(HttpServletRequest request, HttpServletResponse response, LocaleContext localeContext) {	
        super.setLocaleContext(request, response, localeContext);	
    }	
	
    @Override	
    public LocaleContext resolveLocaleContext(HttpServletRequest request) {	
        return super.resolveLocaleContext(request);	
    }	
	
    @Override	
    protected Locale determineDefaultLocale(HttpServletRequest request) {	
        return super.determineDefaultLocale(request);	
    }	
}	
	
