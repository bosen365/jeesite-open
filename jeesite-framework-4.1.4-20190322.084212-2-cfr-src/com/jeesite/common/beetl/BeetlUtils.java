/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.beetl;	
	
import com.jeesite.common.beetl.e.I;	
import com.jeesite.common.beetl.e.l;	
import com.jeesite.common.collect.SetUtils;	
import com.jeesite.common.io.PropertiesUtils;	
import com.jeesite.common.io.ResourceUtils;	
import com.jeesite.common.l.m;	
import com.jeesite.common.lang.ExceptionUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.validator.ValidatorUtils;	
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
    private static GroupTemplate resourceGroupTemplate;	
    private static Logger logger;	
    private static GroupTemplate stringGroupTemplate;	
    private static Configuration configuration;	
	
    public static synchronized Configuration getConfiguration() {	
        if (configuration == null) {	
            int n;	
            LinkedHashSet<String> a = SetUtils.newLinkedHashSet();	
            Resource[] arrresource = ResourceUtils.getResources("classpath*:/config/beetl-*.*");	
            int n2 = arrresource.length;	
            int n3 = n = 0;	
            while (n3 < n2) {	
                Map.Entry a2;	
                Resource resource = arrresource[n];	
                a.add("claspath:/config/" + a2.getFilename());	
                n3 = ++n;	
            }	
            try {	
                a.add("classpath:/config/beetl.properties");	
                logger.debug("Beetl config file: {}", (Object)a);	
                LinkedHashSet<String> linkedHashSet = a;	
                PropertiesUtils a3 = new PropertiesUtils(linkedHashSet.toArray(new String[linkedHashSet.size()]));	
                configuration = new Configuration(a3.getProperties());	
                Set a4 = a3.getProperties().entrySet();	
                for (Map.Entry a2 : a4) {	
                    int n4;	
                    String a5 = (String)a2.getKey();	
                    String a6 = (String)a2.getValue();	
                    if (!StringUtils.startsWithIgnoreCase(a5, new StringBuilder().insert(0, Configuration.IMPORT_PACKAGE).append("_").toString()) || !StringUtils.isNotBlank(a6)) continue;	
                    String[] arrstring = a6.split(";");	
                    int n5 = arrstring.length;	
                    int n6 = n4 = 0;	
                    while (n6 < n5) {	
                        String a7 = arrstring[n4];	
                        configuration.getPkgList().add(a7);	
                        n6 = ++n4;	
                    }	
                }	
            }	
            catch (IOException a2) {	
                logger.error(a2.getMessage(), a2);	
            }	
        }	
        return configuration;	
    }	
	
    public static synchronized GroupTemplate getResourceGroupTemplate() {	
        if (resourceGroupTemplate == null) {	
            try {	
                BeetlUtil.getWebRoot();	
            }	
            catch (Exception a) {	
                BeetlUtil.setWebroot(System.getProperty("user.dir"));	
            }	
            I a = new I();	
            resourceGroupTemplate = new GroupTemplate(a, BeetlUtils.getConfiguration());	
        }	
        return resourceGroupTemplate;	
    }	
	
    static {	
        logger = LoggerFactory.getLogger(BeetlUtils.class);	
    }	
	
    public static String renderFromResource(String tplResourcePath, Map<String, ?> data) {	
        try {	
            GroupTemplate a = BeetlUtils.getResourceGroupTemplate();	
            Template template = a.getTemplate(tplResourcePath);	
            template.binding(data);	
            return template.render();	
        }	
        catch (Exception a) {	
            Exception exception = a;	
            exception.printStackTrace();	
            throw ExceptionUtils.unchecked(exception);	
        }	
    }	
	
    public static String renderFromString(String tplString, Map<String, ?> data) {	
        try {	
            GroupTemplate a = BeetlUtils.getStringGroupTemplate();	
            Template template = a.getTemplate(tplString);	
            template.binding(data);	
            return template.render();	
        }	
        catch (Exception a) {	
            throw ExceptionUtils.unchecked(a);	
        }	
    }	
	
    public static synchronized GroupTemplate getStringGroupTemplate() {	
        if (stringGroupTemplate == null) {	
            l a = new l();	
            stringGroupTemplate = new GroupTemplate(a, BeetlUtils.getConfiguration());	
        }	
        return stringGroupTemplate;	
    }	
}	
	
