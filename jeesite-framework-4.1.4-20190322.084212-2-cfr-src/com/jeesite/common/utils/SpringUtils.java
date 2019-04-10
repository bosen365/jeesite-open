/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.utils;	
	
import com.jeesite.common.io.FileUtils;	
import com.jeesite.common.io.ResourceUtils;	
import com.jeesite.common.l.h;	
import com.jeesite.common.lang.StringUtils;	
import java.io.File;	
import java.io.IOException;	
import java.io.InputStream;	
import org.hyperic.sigar.win32.EventLogRecord;	
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
	
    public static void clearHolder() {	
        if (logger.isDebugEnabled()) {	
            logger.debug(new StringBuilder().insert(0, "Clear ApplicationContext:").append(applicationContext).toString());	
        }	
        applicationContext = null;	
    }	
	
    @Override	
    public void setApplicationContext(ApplicationContext applicationContext) {	
        if (SpringUtils.applicationContext != null) {	
            logger.info(new StringBuilder().insert(0, "SpringContextHolder中的ApplicationContext被覆盖, 原有ApplicationContext为:").append(SpringUtils.applicationContext).toString());	
        }	
        SpringUtils.applicationContext = applicationContext;	
    }	
	
    private static /* synthetic */ void assertContextInjected() {	
        if (applicationContext == null) {	
            String a = new StringBuilder().insert(0, "调用早了 ").append(SpringUtils.class).append(" 还未进行初始化！").toString();	
            throw new IllegalStateException(a);	
        }	
    }	
	
    public static InputStream getInputStream() throws IOException {	
        File a = SpringUtils.getLicFile("jeesite.lic");	
        if (a.exists()) {	
            return FileUtils.openInputStream(a);	
        }	
        Resource a2 = ResourceUtils.getResource("jeesite.lic");	
        if (a2.exists()) {	
            try {	
                return a2.getInputStream();	
            }	
            catch (IOException iOException) {	
                // empty catch block	
            }	
        }	
        return null;	
    }	
	
    public static File getLicFile(String licName) {	
        String string;	
        String a = null;	
        try {	
            string = a = ResourceUtils.getResourceLoader().getResource("/").getFile().getParentFile().getPath();	
        }	
        catch (Exception exception) {	
            string = a;	
        }	
        if (StringUtils.isBlank(string)) {	
            a = System.getProperty("user.dir");	
        }	
        a = new StringBuilder().insert(0, a).append(File.separator).append(licName).toString();	
        return new File(a);	
    }	
	
    public static ApplicationContext getApplicationContext() {	
        SpringUtils.assertContextInjected();	
        return applicationContext;	
    }	
	
    public static <T> T getBean(Class<T> requiredType) {	
        SpringUtils.assertContextInjected();	
        return applicationContext.getBean(requiredType);	
    }	
	
    public static <T> T getBean(String name) {	
        SpringUtils.assertContextInjected();	
        return (T)applicationContext.getBean(name);	
    }	
	
    @Override	
    public void destroy() throws Exception {	
        SpringUtils.clearHolder();	
    }	
}	
	
