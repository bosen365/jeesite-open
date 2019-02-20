/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.io.FileUtils	
 *  com.jeesite.common.io.ResourceUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  org.slf4j.Logger	
 *  org.slf4j.LoggerFactory	
 *  org.springframework.beans.factory.DisposableBean	
 *  org.springframework.context.ApplicationContext	
 *  org.springframework.context.ApplicationContextAware	
 *  org.springframework.core.io.Resource	
 */	
package com.jeesite.common.utils;	
	
import com.jeesite.common.io.FileUtils;	
import com.jeesite.common.io.ResourceUtils;	
import com.jeesite.common.j.E;	
import com.jeesite.common.lang.StringUtils;	
import java.io.File;	
import java.io.IOException;	
import java.io.InputStream;	
import org.hyperic.jni.ArchNotSupportedException;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
import org.springframework.beans.factory.DisposableBean;	
import org.springframework.context.ApplicationContext;	
import org.springframework.context.ApplicationContextAware;	
import org.springframework.core.io.Resource;	
	
public class SpringUtils	
implements ApplicationContextAware,	
DisposableBean {	
    private static ApplicationContext applicationContext = null;	
    private static Logger logger = LoggerFactory.getLogger(SpringUtils.class);	
	
    public static <T> T getBean(Class<T> requiredType) {	
        SpringUtils.assertContextInjected();	
        return (T)applicationContext.getBean(requiredType);	
    }	
	
    private static /* synthetic */ void assertContextInjected() {	
        if (applicationContext == null) {	
            String a2 = new StringBuilder().insert(0, "调用早了 ").append(SpringUtils.class).append(" 还未进行初始化！").toString();	
            throw new IllegalStateException(a2);	
        }	
    }	
	
    public void setApplicationContext(ApplicationContext applicationContext) {	
        if (SpringUtils.applicationContext != null) {	
            logger.info(new StringBuilder().insert(0, "SpringContextHolder中的ApplicationContext被覆盖, 原有ApplicationContext为:").append((Object)SpringUtils.applicationContext).toString());	
        }	
        SpringUtils.applicationContext = applicationContext;	
    }	
	
    public static File getLicFile(String licName) {	
        String string;	
        String a2 = null;	
        try {	
            string = a2 = ResourceUtils.getResourceLoader().getResource("/").getFile().getParentFile().getPath();	
        }	
        catch (Exception exception) {	
            string = a2;	
        }	
        if (StringUtils.isBlank(string)) {	
            a2 = System.getProperty("user.dir");	
        }	
        a2 = new StringBuilder().insert(0, a2).append(File.separator).append(licName).toString();	
        return new File(a2);	
    }	
	
    public static void clearHolder() {	
        if (logger.isDebugEnabled()) {	
            logger.debug(new StringBuilder().insert(0, "Cear AppicationContext:").append((Object)applicationContext).toString());	
        }	
        applicationContext = null;	
    }	
	
    public static ApplicationContext getApplicationContext() {	
        SpringUtils.assertContextInjected();	
        return applicationContext;	
    }	
	
    public void destroy() throws Exception {	
        SpringUtils.clearHolder();	
    }	
	
    public static <T> T getBean(String name) {	
        SpringUtils.assertContextInjected();	
        return (T)applicationContext.getBean(name);	
    }	
	
    public static InputStream getInputStream() throws IOException {	
        File a2 = SpringUtils.getLicFile("jeesite.lic");	
        if (a2.exists()) {	
            return FileUtils.openInputStream((File)a2);	
        }	
        Resource a3 = ResourceUtils.getResource((String)"jeesite.lic");	
        if (a3.exists()) {	
            try {	
                return a3.getInputStream();	
            }	
            catch (IOException iOException) {	
                // empty catch block	
            }	
        }	
        return null;	
    }	
}	
	
