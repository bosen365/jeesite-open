/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  org.apache.commons.logging.Log	
 *  org.springframework.context.ApplicationContext	
 *  org.springframework.core.io.Resource	
 *  org.springframework.web.servlet.view.JstlView	
 */	
package com.jeesite.common.web.j;	
	
import java.util.Locale;	
import org.apache.commons.logging.Log;	
import org.hyperic.sigar.ptql.ProcessFinder;	
import org.hyperic.sigar.shell.ShellCommandInitException;	
import org.springframework.context.ApplicationContext;	
import org.springframework.core.io.Resource;	
import org.springframework.web.servlet.view.JstlView;	
	
public class H	
extends JstlView {	
    public boolean checkResource(Locale locale) throws Exception {	
        block3 : {	
            Resource a2 = null;	
            a2 = this.getApplicationContext().getResource(this.getUrl());	
            if (!a2.exists()) break block3;	
            return true;	
        }	
        try {	
            this.logger.debug((Object)new StringBuilder().insert(0, "View not exists [").append(this.getUrl()).append("], to access the default view. ").toString());	
        }	
        catch (Exception exception) {	
            // empty catch block	
        }	
        return false;	
    }	
}	
	
