/*	
 * Decompiled with CFR 0.140.	
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
	
import com.jeesite.common.beetl.v.D;	
import com.jeesite.common.beetl.v.L;	
import com.jeesite.common.collect.SetUtils;	
import com.jeesite.common.io.PropertiesUtils;	
import com.jeesite.common.io.ResourceUtils;	
import com.jeesite.common.lang.ExceptionUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.modules.job.d.i;	
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
import org.hyperic.sigar.Who;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
import org.springframework.core.io.Resource;	
	
public class BeetlUtils {	
    private static Configuration configuration;	
    private static GroupTemplate resourceGroupTemplate;	
    private static GroupTemplate stringGroupTemplate;	
    private static Logger logger;	
	
    public static synchronized GroupTemplate getResourceGroupTemplate() {	
        if (resourceGroupTemplate == null) {	
            try {	
                BeetlUtil.getWebRoot();	
            }	
            catch (Exception a) {	
                BeetlUtil.setWebroot((String)System.getProperty("user.dir"));	
            }	
            D a = new D();	
            resourceGroupTemplate = new GroupTemplate((ResourceLoader)a, BeetlUtils.getConfiguration());	
        }	
        return resourceGroupTemplate;	
    }	
	
    public static synchronized GroupTemplate getStringGroupTemplate() {	
        if (stringGroupTemplate == null) {	
            L a = new L();	
            stringGroupTemplate = new GroupTemplate((ResourceLoader)a, BeetlUtils.getConfiguration());	
        }	
        return stringGroupTemplate;	
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
            throw ExceptionUtils.unchecked((Exception)exception);	
        }	
    }	
	
    public static synchronized Configuration getConfiguration() {	
        if (configuration == null) {	
            int n;	
            LinkedHashSet a = SetUtils.newLinkedHashSet();	
            Resource[] arrresource = ResourceUtils.getResources((String)"classpath*:/config/beetl-*.*");	
            int n2 = arrresource.length;	
            int n3 = n = 0;	
            while (n3 < n2) {	
                Map.Entry a2;	
                Resource resource = arrresource[n];	
                a.add("classpath:/config/" + a2.getFilename());	
                n3 = ++n;	
            }	
            try {	
                logger.debug("Beetl config files: {}", (Object)a);	
                a.add("classpath:/config/beetl.properties");	
                LinkedHashSet linkedHashSet = a;	
                PropertiesUtils a3 = new PropertiesUtils(linkedHashSet.toArray(new String[linkedHashSet.size()]));	
                configuration = new Configuration(a3.getProperties());	
                Set a4 = a3.getProperties().entrySet();	
                for (Map.Entry a2 : a4) {	
                    int n4;	
                    String a5 = (String)a2.getKey();	
                    String a6 = (String)a2.getValue();	
                    if (!StringUtils.startsWithIgnoreCase((CharSequence)a5, (CharSequence)new StringBuilder().insert(0, Configuration.IMPORT_PACKAGE).append("_").toString()) || !StringUtils.isNotBlank((CharSequence)a6)) continue;	
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
                logger.error(a2.getMessage(), (Throwable)a2);	
            }	
        }	
        return configuration;	
    }	
	
    public static String renderFromString(String tplString, Map<String, ?> data) {	
        try {	
            GroupTemplate a = BeetlUtils.getStringGroupTemplate();	
            Template template = a.getTemplate(tplString);	
            template.binding(data);	
            return template.render();	
        }	
        catch (Exception a) {	
            throw ExceptionUtils.unchecked((Exception)a);	
        }	
    }	
}	
	
