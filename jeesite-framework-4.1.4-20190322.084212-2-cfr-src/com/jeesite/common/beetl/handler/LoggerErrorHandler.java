/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.beetl.handler;	
	
import com.jeesite.common.beetl.e.l;	
import com.jeesite.common.web.returnjson.ReturnJsonSerializer;	
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
import org.hyperic.sigar.NetRoute;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
	
public class LoggerErrorHandler	
implements ErrorHandler {	
    protected Logger logger;	
	
    protected String getResourceName(String resourceId) {	
        return StringUtils.abbreviate(resourceId, 1000);	
    }	
	
    protected int[] getRange(int line) {	
        int a = 0;	
        int a2 = 0;	
        a2 = (line > 3 ? (a = line - 3) : (a = 1)) + 6;	
        int[] arrn = new int[2];	
        arrn[0] = a;	
        arrn[1] = a2;	
        return arrn;	
    }	
	
    public LoggerErrorHandler() {	
        LoggerErrorHandler loggerErrorHandler = this;	
        loggerErrorHandler.logger = LoggerFactory.getLogger(loggerErrorHandler.getClass());	
    }	
	
    @Override	
    public void processExcption(BeetlException ex, Writer writer) {	
        ErrorInfo a = new ErrorInfo(ex);	
        if (a.getErrorCode().equals("CLIENT_IO_ERROR_ERROR") && !ex.gt.getConf().isIgnoreClientIOError()) {	
            this.logger.error(new StringBuilder().insert(0, "客户端IO异常:").append(this.getResourceName(ex.resource.getId())).append(":").append(a.getMsg()).toString(), ex);	
            return;	
        }	
        ErrorInfo errorInfo = a;	
        int a2 = errorInfo.getErrorTokenLine();	
        StringBuilder a3 = new StringBuilder(">>").append(this.getDateTime()).append(":").append(a.getType()).append(":").append(a.getErrorTokenText()).append(" 位于").append(a2).append("行").append(" 资源:").append(this.getResourceName(ex.resource.getId()));	
        StringBuilder a4 = new StringBuilder("\n");	
        if (errorInfo.getErrorCode().equals("TEMPLATE_LOAD_ERROR")) {	
            if (a.getMsg() != null) {	
                a3.append(a.getMsg());	
            }	
            a4.append(new StringBuilder().insert(0, a3.toString()).append("\n").toString());	
            a4.append(ex.gt.getResourceLoader().getInfo() + "\n");	
            return;	
        }	
        a4.append(new StringBuilder().insert(0, a3.toString()).append("\n").toString());	
        if (ex.getMessage() != null) {	
            a4.append(new StringBuilder().insert(0, ex.getMessage()).append("\n").toString());	
        }	
        ResourceLoader a5 = ex.gt.getResourceLoader();	
        Resource a6 = null;	
        String a7 = null;	
        try {	
            a6 = a5.getResource(ex.resource.getId());	
            int[] a8 = this.getRange(a2);	
            a7 = a6.getContent(a8[0], a8[1]);	
            if (a7 != null) {	
                String[] a9 = a7.split(ex.cr);	
                int a10 = a8[0];	
                for (int a11 = 0; a11 < a9.length; ++a11) {	
                    StringBuilder stringBuilder = new StringBuilder().insert(0, "").append(a10).append("|").append(a9[a11]);	
                    ++a10;	
                    a4.append(stringBuilder.append("\n").toString());	
                }	
            }	
        }	
        catch (IOException a12) {	
            a12.printStackTrace();	
        }	
        if (a.hasCallStack()) {	
            a4.append("  ========================\n");	
            a4.append("  调用栈:\n");	
            int a12 = 0;	
            int n = a12;	
            while (n < a.getResourceCallStack().size()) {	
                StringBuilder stringBuilder = new StringBuilder().insert(0, "  ").append(a.getResourceCallStack().get(a12)).append(" 行：").append(a.getTokenCallStack().get((int)a12).line);	
                a4.append(stringBuilder.append("\n").toString());	
                n = ++a12;	
            }	
        }	
        if (a6 != null && a6.getResourceLoader() instanceof l) {	
            this.logger.error(a4.toString(), ex);	
        }	
        throw new BeetlException(a4.toString(), ex);	
    }	
	
    protected String getDateTime() {	
        Date a = new Date();	
        return new SimpleDateFormat("hh:mm:ss").format(a);	
    }	
}	
	
