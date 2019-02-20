/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.lang.ObjectUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  org.apache.ibatis.io.VFS	
 *  org.springframework.beans.factory.annotation.Autowired	
 *  org.springframework.context.support.ReloadableResourceBundleMessageSource	
 */	
package com.jeesite.common.beetl.j;	
	
import com.jeesite.common.beetl.e.F;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.modules.sys.entity.Lang;	
import com.jeesite.modules.sys.service.LangService;	
import java.io.IOException;	
import java.text.MessageFormat;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Locale;	
import java.util.Properties;	
import java.util.Set;	
import javax.annotation.PostConstruct;	
import org.apache.ibatis.io.VFS;	
import org.hyperic.sigar.NfsServerV3;	
import org.hyperic.sigar.pager.PageList;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.context.support.ReloadableResourceBundleMessageSource;	
	
public class E	
extends ReloadableResourceBundleMessageSource {	
    private final String MAP_SPLIT_CODE = "|";	
    @Autowired(required=false)	
    private LangService langService;	
    private final Properties properties;	
	
    /*	
     * Enabled aggressive block sorting	
     * Enabled unnecessary exception pruning	
     * Enabled aggressive exception aggregation	
     */	
    public E() {	
        E e2 = this;	
        this.MAP_SPLIT_CODE = "|";	
        E e3 = this;	
        e2.properties = new Properties();	
        e2.setDefaultEncoding("UTF-8");	
        Set a2 = e2.getBasenameSet();	
        try {	
            List a3 = VFS.getInstance().list("i18n");	
            Iterator iterator = a3.iterator();	
            block4 : do {	
                Iterator iterator2 = iterator;	
                while (iterator2.hasNext()) {	
                    String a4 = (String)iterator.next();	
                    if (!a4.endsWith(".properties")) continue block4;	
                    if (!a4.contains("zh_CN")) {	
                        if (!Global.getPropertyToBoolean("lang.enabled", "true").booleanValue()) {	
                            iterator2 = iterator;	
                            continue;	
                        }	
                        if (!ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnI18n")).booleanValue()) {	
                            iterator2 = iterator;	
                            continue;	
                        }	
                    }	
                    a2.add(new StringBuilder().insert(0, "classpath:").append(StringUtils.substringBeforeLast((String)a4, (String)"i18n_")).append("i18n").toString());	
                    continue block4;	
                }	
                return;	
                break;	
            } while (true);	
        }	
        catch (IOException a5) {	
            a5.printStackTrace();	
        }	
    }	
	
    @PostConstruct	
    private /* synthetic */ void initialize() {	
        if (Global.isTestProfileActive() || !ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnI18n")).booleanValue()) {	
            return;	
        }	
        if (this.langService != null && !Global.getPropertyToBoolean("lang.enabled", "true").booleanValue()) {	
            Iterator<Lang> iterator;	
            E e2 = this;	
            e2.properties.clear();	
            Iterator<Lang> iterator2 = iterator = e2.langService.findList(new Lang()).iterator();	
            while (iterator2.hasNext()) {	
                Lang a2 = iterator.next();	
                iterator2 = iterator;	
                this.properties.put(new StringBuilder().insert(0, a2.getLangCode()).append("|").append(a2.getLangType()).toString(), a2.getLangText());	
            }	
        }	
    }	
	
    public void clearCache() {	
        E e2 = this;	
        super.clearCache();	
        e2.initialize();	
    }	
	
    private /* synthetic */ String getText(String code, Locale locale) {	
        String a2 = this.properties.getProperty(new StringBuilder().insert(0, code).append("|").append(locale.toString()).toString());	
        if (a2 == null) {	
            a2 = this.properties.getProperty(new StringBuilder().insert(0, code).append("|").append(locale.getLanguage()).toString());	
        }	
        return a2;	
    }	
	
    protected String resolveCodeWithoutArguments(String code, Locale locale) {	
        String a2 = this.getText(code, locale);	
        if (a2 == null) {	
            a2 = super.resolveCodeWithoutArguments(code, locale);	
        }	
        return a2;	
    }	
	
    protected MessageFormat resolveCode(String code, Locale locale) {	
        String a2 = this.getText(code, locale);	
        if (a2 == null) {	
            return super.resolveCode(code, locale);	
        }	
        return this.createMessageFormat(a2, locale);	
    }	
}	
	
