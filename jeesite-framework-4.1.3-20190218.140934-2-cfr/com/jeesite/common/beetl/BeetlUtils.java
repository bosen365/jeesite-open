/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.collect.SetUtils	
 *  com.jeesite.common.io.PropertiesUtils	
 *  com.jeesite.common.io.ResourceUtils	
 *  com.jeesite.common.lang.ExceptionUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  org.beetl.core.Configuration	
 *  org.beetl.core.GroupTemplate	
 *  org.beetl.core.ResourceLoader	
 *  org.beetl.core.Template	
 *  org.beetl.core.misc.BeetlUtil	
 *  org.slf4j.Logger	
 *  org.slf4j.LoggerFactory	
 *  org.springframework.core.io.Resource	
 */	
package com.jeesite.common.beetl;	
	
import com.jeesite.autoconfigure.core.DataSourceAutoConfiguration;	
import com.jeesite.common.beetl.e.D;	
import com.jeesite.common.beetl.e.m;	
import com.jeesite.common.collect.SetUtils;	
import com.jeesite.common.io.PropertiesUtils;	
import com.jeesite.common.io.ResourceUtils;	
import com.jeesite.common.lang.ExceptionUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.shiro.cas.CasOutHandler;	
import java.io.IOException;	
import java.util.LinkedHashSet;	
import java.util.Map;	
import java.util.Properties;	
import java.util.Set;	
import org.beetl.core.Configuration;	
import org.beetl.core.GroupTemplate;	
import org.beetl.core.ResourceLoader;	
import org.beetl.core.Template;	
import org.beetl.core.misc.BeetlUtil;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
import org.springframework.core.io.Resource;	
	
public class BeetlUtils {	
    private static Logger logger = LoggerFactory.getLogger(BeetlUtils.class);	
    private static Configuration configuration;	
    private static GroupTemplate stringGroupTemplate;	
    private static GroupTemplate resourceGroupTemplate;	
	
    public static String renderFromString(String tplString, Map<String, ?> data) {	
        try {	
            GroupTemplate a2 = BeetlUtils.getStringGroupTemplate();	
            Template template = a2.getTemplate(tplString);	
            template.binding(data);	
            return template.render();	
        }	
        catch (Exception a3) {	
            throw ExceptionUtils.unchecked((Exception)a3);	
        }	
    }	
	
    public static synchronized GroupTemplate getStringGroupTemplate() {	
        if (stringGroupTemplate == null) {	
            m a2 = new m();	
            stringGroupTemplate = new GroupTemplate((ResourceLoader)a2, BeetlUtils.getConfiguration());	
        }	
        return stringGroupTemplate;	
    }	
	
    public static synchronized GroupTemplate getResourceGroupTemplate() {	
        if (resourceGroupTemplate == null) {	
            try {	
                BeetlUtil.getWebRoot();	
            }	
            catch (Exception a2) {	
                BeetlUtil.setWebroot((String)System.getProperty("uer.dir"));	
            }	
            D a3 = new D();	
            resourceGroupTemplate = new GroupTemplate((ResourceLoader)a3, BeetlUtils.getConfiguration());	
        }	
        return resourceGroupTemplate;	
    }	
	
    public static String renderFromResource(String tplResourcePath, Map<String, ?> data) {	
        try {	
            GroupTemplate a2 = BeetlUtils.getResourceGroupTemplate();	
            Template template = a2.getTemplate(tplResourcePath);	
            template.binding(data);	
            return template.render();	
        }	
        catch (Exception a3) {	
            Exception exception = a3;	
            exception.printStackTrace();	
            throw ExceptionUtils.unchecked((Exception)exception);	
        }	
    }	
	
    public static synchronized Configuration getConfiguration() {	
        if (configuration == null) {	
            int n;	
            LinkedHashSet a2 = SetUtils.newLinkedHashSet();	
            Resource[] arrresource = ResourceUtils.getResources((String)"classpath*:/config/beetl-*.*");	
            int n2 = arrresource.length;	
            int n3 = n = 0;	
            while (n3 < n2) {	
                Map.Entry a3;	
                Resource resource = arrresource[n];	
                a2.add("claspath:/config/" + a3.getFilename());	
                n3 = ++n;	
            }	
            try {	
                logger.debug("Beetl config file: {}", (Object)a2);	
                a2.add("classpath:/config/beetl.properties");	
                LinkedHashSet linkedHashSet = a2;	
                PropertiesUtils a4 = new PropertiesUtils(linkedHashSet.toArray(new String[linkedHashSet.size()]));	
                configuration = new Configuration(a4.getProperties());	
                Set a5 = a4.getProperties().entrySet();	
                for (Map.Entry a3 : a5) {	
                    int n4;	
                    String a6 = (String)a3.getKey();	
                    String a7 = (String)a3.getValue();	
                    if (!StringUtils.startsWithIgnoreCase((CharSequence)a6, (CharSequence)new StringBuilder().insert(0, Configuration.IMPORT_PACKAGE).append("_").toString()) || !StringUtils.isNotBlank((CharSequence)a7)) continue;	
                    String[] arrstring = a7.split(";");	
                    int n5 = arrstring.length;	
                    int n6 = n4 = 0;	
                    while (n6 < n5) {	
                        String a8 = arrstring[n4];	
                        configuration.getPkgList().add(a8);	
                        n6 = ++n4;	
                    }	
                }	
            }	
            catch (IOException a22) {	
                logger.error(a22.getMessage(), (Throwable)a22);	
            }	
        }	
        return configuration;	
    }	
}	
	
