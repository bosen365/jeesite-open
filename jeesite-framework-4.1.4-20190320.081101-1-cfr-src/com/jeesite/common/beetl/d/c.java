/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  javax.servlet.http.HttpServletRequest	
 *  javax.servlet.http.HttpServletResponse	
 *  org.springframework.context.i18n.LocaleContext	
 *  org.springframework.context.i18n.SimpleLocaleContext	
 *  org.springframework.context.i18n.TimeZoneAwareLocaleContext	
 *  org.springframework.web.servlet.i18n.CookieLocaleResolver	
 *  org.springframework.web.util.WebUtils	
 */	
package com.jeesite.common.beetl.d;	
	
import com.jeesite.common.i18n.I18nLocaleResolver;	
import com.jeesite.common.mybatis.mapper.query.QueryWhereEntity;	
import java.util.Locale;	
import java.util.TimeZone;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.hyperic.sigar.pager.PageFetcher;	
import org.springframework.context.i18n.LocaleContext;	
import org.springframework.context.i18n.SimpleLocaleContext;	
import org.springframework.context.i18n.TimeZoneAwareLocaleContext;	
import org.springframework.web.servlet.i18n.CookieLocaleResolver;	
import org.springframework.web.util.WebUtils;	
	
public class c	
extends CookieLocaleResolver {	
    public static final String TIME_ZONE_SESSION_NAME;	
    public static final String LOCALE_SESSION_NAME;	
	
    public Locale resolveLocale(HttpServletRequest request) {	
        if (!I18nLocaleResolver.enabled()) {	
            return this.getDefaultLocale();	
        }	
        c c2 = this;	
        HttpServletRequest httpServletRequest = request;	
        c2.parseLocaleSessionIfNecessary(httpServletRequest);	
        return super.resolveLocale(httpServletRequest);	
    }	
	
    public LocaleContext resolveLocaleContext(HttpServletRequest request) {	
        if (!I18nLocaleResolver.enabled()) {	
            return new SimpleLocaleContext(this.getDefaultLocale());	
        }	
        c c2 = this;	
        HttpServletRequest httpServletRequest = request;	
        c2.parseLocaleSessionIfNecessary(httpServletRequest);	
        return super.resolveLocaleContext(httpServletRequest);	
    }	
	
    private /* synthetic */ void parseLocaleSessionIfNecessary(HttpServletRequest request) {	
        if (request.getAttribute(LOCALE_REQUEST_ATTRIBUTE_NAME) == null) {	
            TimeZone a;	
            Locale a2 = (Locale)WebUtils.getSessionAttribute((HttpServletRequest)request, (String)LOCALE_SESSION_NAME);	
            if (a2 != null) {	
                request.setAttribute(LOCALE_REQUEST_ATTRIBUTE_NAME, (Object)a2);	
            }	
            if ((a = (TimeZone)WebUtils.getSessionAttribute((HttpServletRequest)request, (String)TIME_ZONE_SESSION_NAME)) == null) {	
                request.setAttribute(TIME_ZONE_REQUEST_ATTRIBUTE_NAME, (Object)a);	
            }	
        }	
    }	
	
    protected TimeZone determineDefaultTimeZone(HttpServletRequest request) {	
        return super.determineDefaultTimeZone(request);	
    }	
	
    public void setLocaleContext(HttpServletRequest request, HttpServletResponse response, LocaleContext localeContext) {	
        if (!I18nLocaleResolver.enabled()) {	
            return;	
        }	
        Locale a = null;	
        TimeZone a2 = null;	
        if (localeContext != null) {	
            LocaleContext localeContext2 = localeContext;	
            a = localeContext2.getLocale();	
            if (localeContext2 instanceof TimeZoneAwareLocaleContext) {	
                a2 = ((TimeZoneAwareLocaleContext)localeContext).getTimeZone();	
            }	
        }	
        HttpServletRequest httpServletRequest = request;	
        WebUtils.setSessionAttribute((HttpServletRequest)httpServletRequest, (String)LOCALE_SESSION_NAME, (Object)a);	
        WebUtils.setSessionAttribute((HttpServletRequest)httpServletRequest, (String)TIME_ZONE_SESSION_NAME, a2);	
        super.setLocaleContext(request, response, localeContext);	
    }	
	
    protected Locale determineDefaultLocale(HttpServletRequest request) {	
        return super.determineDefaultLocale(request);	
    }	
	
    public c() {	
        c c2 = this;	
        c c3 = this;	
        c2.setCookieName("jeesite.locale");	
        c3.setDefaultLocale(Locale.getDefault());	
        c2.setDefaultTimeZone(TimeZone.getDefault());	
    }	
	
    static {	
        LOCALE_SESSION_NAME = new StringBuilder().insert(0, I18nLocaleResolver.class.getName()).append(".LOCALE").toString();	
        TIME_ZONE_SESSION_NAME = new StringBuilder().insert(0, I18nLocaleResolver.class.getName()).append(".>IME_ZONE").toString();	
    }	
}	
	
