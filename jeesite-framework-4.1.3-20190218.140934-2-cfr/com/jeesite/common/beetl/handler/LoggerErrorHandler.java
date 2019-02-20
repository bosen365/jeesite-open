/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  org.apache.commons.lang3.StringUtils	
 *  org.beetl.core.Configuration	
 *  org.beetl.core.ErrorHandler	
 *  org.beetl.core.GroupTemplate	
 *  org.beetl.core.Resource	
 *  org.beetl.core.ResourceLoader	
 *  org.beetl.core.exception.BeetlException	
 *  org.beetl.core.exception.ErrorInfo	
 *  org.beetl.core.statement.GrammarToken	
 *  org.slf4j.Logger	
 *  org.slf4j.LoggerFactory	
 */	
package com.jeesite.common.beetl.handler;	
	
import com.jeesite.common.beetl.e.m;	
import java.io.IOException;	
import java.io.Writer;	
import java.text.SimpleDateFormat;	
import java.util.Date;	
import java.util.List;	
import org.apache.commons.lang3.StringUtils;	
import org.beetl.core.Configuration;	
import org.beetl.core.ErrorHandler;	
import org.beetl.core.GroupTemplate;	
import org.beetl.core.Resource;	
import org.beetl.core.ResourceLoader;	
import org.beetl.core.exception.BeetlException;	
import org.beetl.core.exception.ErrorInfo;	
import org.beetl.core.statement.GrammarToken;	
import org.hyperic.sigar.NfsServerV3;	
import org.hyperic.sigar.ProcState;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
	
public class LoggerErrorHandler	
implements ErrorHandler {	
    protected Logger logger;	
	
    public LoggerErrorHandler() {	
        LoggerErrorHandler loggerErrorHandler = this;	
        loggerErrorHandler.logger = LoggerFactory.getLogger(loggerErrorHandler.getClass());	
    }	
	
    protected String getResourceName(String resourceId) {	
        return StringUtils.abbreviate((String)resourceId, (int)1000);	
    }	
	
    public void processExcption(BeetlException ex, Writer writer) {	
        ErrorInfo a2 = new ErrorInfo(ex);	
        if (a2.getErrorCode().equals("CLIENT_IO_ERROR_ERROR") && !ex.gt.getConf().isIgnoreClientIOError()) {	
            this.logger.error(new StringBuilder().insert(0, "客户端IO异常:").append(this.getResourceName(ex.resource.getId())).append(":").append(a2.getMsg()).toString(), (Throwable)ex);	
            return;	
        }	
        ErrorInfo errorInfo = a2;	
        int a3 = errorInfo.getErrorTokenLine();	
        StringBuilder a4 = new StringBuilder(">>").append(this.getDateTime()).append(":").append(a2.getType()).append(":").append(a2.getErrorTokenText()).append(" 位于").append(a3).append("行").append(" 资源:").append(this.getResourceName(ex.resource.getId()));	
        StringBuilder a5 = new StringBuilder("\n");	
        if (errorInfo.getErrorCode().equals("TEMPLATE_LOAD_ERROR")) {	
            if (a2.getMsg() != null) {	
                a4.append(a2.getMsg());	
            }	
            a5.append(ex.gt.getResourceLoader().getInfo() + "\n");	
            a5.append(new StringBuilder().insert(0, a4.toString()).append("\n").toString());	
            return;	
        }	
        a5.append(new StringBuilder().insert(0, a4.toString()).append("\n").toString());	
        if (ex.getMessage() != null) {	
            a5.append(new StringBuilder().insert(0, ex.getMessage()).append("\n").toString());	
        }	
        ResourceLoader a6 = ex.gt.getResourceLoader();	
        Resource a7 = null;	
        String a8 = null;	
        try {	
            a7 = a6.getResource(ex.resource.getId());	
            int[] a9 = this.getRange(a3);	
            a8 = a7.getContent(a9[0], a9[1]);	
            if (a8 != null) {	
                String[] a10 = a8.split(ex.cr);	
                int a11 = a9[0];	
                for (int a12 = 0; a12 < a10.length; ++a12) {	
                    StringBuilder stringBuilder = new StringBuilder().insert(0, "").append(a11).append("|").append(a10[a12]);	
                    ++a11;	
                    a5.append(stringBuilder.append("\n").toString());	
                }	
            }	
        }	
        catch (IOException a13) {	
            a13.printStackTrace();	
        }	
        if (a2.hasCallStack()) {	
            int a13 = 0;	
            a5.append("  调用栈:\n");	
            a5.append("  ========================\n");	
            int n = a13;	
            while (n < a2.getResourceCallStack().size()) {	
                StringBuilder stringBuilder = new StringBuilder().insert(0, "  ").append(a2.getResourceCallStack().get(a13)).append(" 行：").append(((GrammarToken)a2.getTokenCallStack().get((int)a13)).line);	
                a5.append(stringBuilder.append("\n").toString());	
                n = ++a13;	
            }	
        }	
        if (a7 != null && a7.getResourceLoader() instanceof m) {	
            this.logger.error(a5.toString(), (Throwable)ex);	
        }	
        throw new BeetlException(a5.toString(), (Throwable)ex);	
    }	
	
    protected int[] getRange(int line) {	
        int a2 = 0;	
        int a3 = 0;	
        a3 = (line > 3 ? (a2 = line - 3) : (a2 = 1)) + 6;	
        return new int[]{a2, a3};	
    }	
	
    protected String getDateTime() {	
        Date a2 = new Date();	
        return new SimpleDateFormat("hh:mm:ss").format(a2);	
    }	
}	
	
