/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.beetl.l;	
	
import com.jeesite.common.beetl.e.j;	
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
import org.hyperic.sigar.NetRoute;	
import org.hyperic.sigar.cmd.Watch;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.context.support.ReloadableResourceBundleMessageSource;	
	
public class m	
extends ReloadableResourceBundleMessageSource {	
    private final Properties properties;	
    private final String MAP_SPLIT_CODE = "|";	
    @Autowired(required=false)	
    private LangService langService;	
	
    @Override	
    public void clearCache() {	
        m m2 = this;	
        super.clearCache();	
        m2.initialize();	
    }	
	
    private /* synthetic */ String getText(String code, Locale locale) {	
        String a = this.properties.getProperty(new StringBuilder().insert(0, code).append("|").append(locale.toString()).toString());	
        if (a == null) {	
            a = this.properties.getProperty(new StringBuilder().insert(0, code).append("|").append(locale.getLanguage()).toString());	
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
	
    @Override	
    protected String resolveCodeWithoutArguments(String code, Locale locale) {	
        String a = this.getText(code, locale);	
        if (a == null) {	
            a = super.resolveCodeWithoutArguments(code, locale);	
        }	
        return a;	
    }	
	
    /*	
     * Enabled aggressive block sorting	
     * Enabled unnecessary exception pruning	
     * Enabled aggressive exception aggregation	
     */	
    public m() {	
        m m2 = this;	
        this.MAP_SPLIT_CODE = "|";	
        m m3 = this;	
        m2.properties = new Properties();	
        m2.setDefaultEncoding("UTF-8");	
        Set<String> a = m2.getBasenameSet();	
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
                        if (!ObjectUtils.toBoolean(j.ALLATORIxDEMO().get("fnI18n")).booleanValue()) {	
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
        if (Global.isTestProfileActive() || !ObjectUtils.toBoolean(j.ALLATORIxDEMO().get("fnI18n")).booleanValue()) {	
            return;	
        }	
        if (this.langService != null && !Global.getPropertyToBoolean("lang.enabled", "true").booleanValue()) {	
            Iterator<Lang> iterator;	
            m m2 = this;	
            m2.properties.clear();	
            Iterator<Lang> iterator2 = iterator = m2.langService.findList(new Lang()).iterator();	
            while (iterator2.hasNext()) {	
                Lang a = iterator.next();	
                iterator2 = iterator;	
                this.properties.put(new StringBuilder().insert(0, a.getLangCode()).append("|").append(a.getLangType()).toString(), a.getLangText());	
            }	
        }	
    }	
}	
	
