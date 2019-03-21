/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.common.web.d;	
	
import java.util.Locale;	
import org.apache.commons.logging.Log;	
import org.hyperic.sigar.ProcCredName;	
import org.hyperic.sigar.shell.ShellCommandInitException;	
import org.springframework.context.ApplicationContext;	
import org.springframework.core.io.Resource;	
import org.springframework.web.servlet.view.JstlView;	
	
public class l	
extends JstlView {	
    @Override	
    public boolean checkResource(Locale locale) throws Exception {	
        block3 : {	
            Resource a = null;	
            a = this.getApplicationContext().getResource(this.getUrl());	
            if (!a.exists()) break block3;	
            return true;	
        }	
        try {	
            this.logger.debug(new StringBuilder().insert(0, "View not exists [").append(this.getUrl()).append("], to access the default view. ").toString());	
        }	
        catch (Exception exception) {	
            // empty catch block	
        }	
        return false;	
    }	
}	
	
