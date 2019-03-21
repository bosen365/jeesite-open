/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.common.beetl.d;	
	
import com.jeesite.common.beetl.v.m;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.modules.sys.entity.Lang;	
import com.jeesite.modules.sys.service.LangService;	
import com.jeesite.modules.sys.utils.ConfigUtils;	
import java.io.IOException;	
import java.text.MessageFormat;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Locale;	
import java.util.Properties;	
import java.util.Set;	
import javax.annotation.PostConstruct;	
import org.apache.ibatis.io.VFS;	
import org.hyperic.sigar.FileWatcher;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.context.support.ReloadableResourceBundleMessageSource;	
	
public class i	
extends ReloadableResourceBundleMessageSource {	
    private final String MAP_SPLIT_CODE = "|";	
    @Autowired(required=false)	
    private LangService langService;	
    private final Properties properties;	
	
    @Override	
    protected String resolveCodeWithoutArguments(String code, Locale locale) {	
        String a = this.getText(code, locale);	
        if (a == null) {	
            a = super.resolveCodeWithoutArguments(code, locale);	
        }	
        return a;	
    }	
	
    @Override	
    protected MessageFormat resolveCode(String code, Locale locale) {	
        String a = this.getText(code, locale);	
        if (a == null) {	
            return super.resolveCode(code, locale);	
        }	
        return this.createMessageFormat(a, locale);	
    }	
	
    /*	
     * Enabled aggressive block sorting	
     * Enabled unnecessary exception pruning	
     * Enabled aggressive exception aggregation	
     */	
    public i() {	
        i i2 = this;	
        this.MAP_SPLIT_CODE = "|";	
        i i3 = this;	
        i2.properties = new Properties();	
        i2.setDefaultEncoding("UTF-8");	
        Set<String> a = i2.getBasenameSet();	
        try {	
            List<String> a2 = VFS.getInstance().list("i18n");	
            Iterator<String> iterator = a2.iterator();	
            block4 : do {	
                Iterator<String> iterator2 = iterator;	
                while (iterator2.hasNext()) {	
                    String a3 = iterator.next();	
                    if (!a3.endsWith(".properties")) continue block4;	
                    if (!a3.contains("zh_CN")) {	
                        if (!Global.getPropertyToBoolean("lang.enabled", "true").booleanValue()) {	
                            iterator2 = iterator;	
                            continue;	
                        }	
                        if (!ObjectUtils.toBoolean(m.ALLATORIxDEMO().get("fnI18n")).booleanValue()) {	
                            iterator2 = iterator;	
                            continue;	
                        }	
                    }	
                    a.add(new StringBuilder().insert(0, "classpath:").append(StringUtils.substringBeforeLast(a3, "i18n_")).append("i18n").toString());	
                    continue block4;	
                }	
                return;	
                break;	
            } while (true);	
        }	
        catch (IOException a4) {	
            a4.printStackTrace();	
        }	
    }	
	
    @PostConstruct	
    private /* synthetic */ void initialize() {	
        if (Global.isTestProfileActive() || !ObjectUtils.toBoolean(m.ALLATORIxDEMO().get("fnI18n")).booleanValue()) {	
            return;	
        }	
        if (this.langService != null && !Global.getPropertyToBoolean("lang.enabled", "true").booleanValue()) {	
            Iterator<Lang> iterator;	
            i i2 = this;	
            i2.properties.clear();	
            Iterator<Lang> iterator2 = iterator = i2.langService.findList(new Lang()).iterator();	
            while (iterator2.hasNext()) {	
                Lang a = iterator.next();	
                iterator2 = iterator;	
                this.properties.put(new StringBuilder().insert(0, a.getLangCode()).append("|").append(a.getLangType()).toString(), a.getLangText());	
            }	
        }	
    }	
	
    private /* synthetic */ String getText(String code, Locale locale) {	
        String a = this.properties.getProperty(new StringBuilder().insert(0, code).append("|").append(locale.toString()).toString());	
        if (a == null) {	
            a = this.properties.getProperty(new StringBuilder().insert(0, code).append("|").append(locale.getLanguage()).toString());	
        }	
        return a;	
    }	
	
    @Override	
    public void clearCache() {	
        i i2 = this;	
        super.clearCache();	
        i2.initialize();	
    }	
}	
	
