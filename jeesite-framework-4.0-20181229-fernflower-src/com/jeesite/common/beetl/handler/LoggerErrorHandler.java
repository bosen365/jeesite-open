package com.jeesite.common.beetl.handler;	
	
import com.jeesite.common.mybatis.mapper.query.QueryOrder;	
import com.jeesite.modules.job.l.l;	
import java.io.IOException;	
import java.io.Writer;	
import java.text.SimpleDateFormat;	
import java.util.Date;	
import org.apache.commons.lang3.StringUtils;	
import org.beetl.core.ErrorHandler;	
import org.beetl.core.Resource;	
import org.beetl.core.ResourceLoader;	
import org.beetl.core.exception.BeetlException;	
import org.beetl.core.exception.ErrorInfo;	
import org.beetl.core.statement.GrammarToken;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
	
public class LoggerErrorHandler implements ErrorHandler {	
   protected Logger logger = LoggerFactory.getLogger(this.getClass());	
	
   protected int[] getRange(int line) {	
      int a = false;	
      int a = false;	
      int a;	
      int a = (line > 3 ? (a = line - 3) : (a = 1)) + 6;	
      int[] var10000 = new int[2];	
      boolean var10002 = true;	
      var10000[0] = a;	
      var10000[1] = a;	
      return var10000;	
   }	
	
   public void processExcption(BeetlException ex, Writer writer) {	
      ErrorInfo a;	
      if ((a = new ErrorInfo(ex)).getErrorCode().equals("CLIENT_IO_ERROR_ERROR") && !ex.gt.getConf().isIgnoreClientIOError()) {	
         this.logger.error((new StringBuilder()).insert(0, "客户端IO异常:").append(this.getResourceName(ex.resource.getId())).append(":").append(a.getMsg()).toString(), ex);	
      } else {	
         int a = a.getErrorTokenLine();	
         StringBuilder a = (new StringBuilder(">>")).append(this.getDateTime()).append(":").append(a.getType()).append(":").append(a.getErrorTokenText()).append(" 位于").append(a).append("行").append(" 资源:").append(this.getResourceName(ex.resource.getId()));	
         StringBuilder a = new StringBuilder("\n");	
         if (a.getErrorCode().equals("TEMPLATE_LOAD_ERROR")) {	
            if (a.getMsg() != null) {	
               a.append(a.getMsg());	
            }	
	
            a.append((new StringBuilder()).insert(0, a.toString()).append("\n").toString());	
            a.append(ex.gt.getResourceLoader().getInfo() + "\n");	
         } else {	
            a.append((new StringBuilder()).insert(0, a.toString()).append("\n").toString());	
            if (ex.getMessage() != null) {	
               a.append((new StringBuilder()).insert(0, ex.getMessage()).append("\n").toString());	
            }	
	
            ResourceLoader a = ex.gt.getResourceLoader();	
            Resource a = null;	
            String a = null;	
	
            StringBuilder var10001;	
            String var10002;	
            try {	
               a = a.getResource(ex.resource.getId());	
               int[] a = this.getRange(a);	
               if ((a = a.getContent(a[0], a[1])) != null) {	
                  String[] a = a.split(ex.cr);	
                  int a = a[0];	
	
                  for(int a = 0; a < a.length; ++a) {	
                     var10001 = (new StringBuilder()).insert(0, "").append(a).append("|").append(a[a]);	
                     var10002 = "\n";	
                     ++a;	
                     a.append(var10001.append(var10002).toString());	
                  }	
               }	
            } catch (IOException var14) {	
               var14.printStackTrace();	
            }	
	
            if (a.hasCallStack()) {	
               a.append("  ========================\n");	
               a.append("  调用栈:\n");	
               int a = 0;	
	
               for(int var10000 = a; var10000 < a.getResourceCallStack().size(); var10000 = a) {	
                  var10001 = (new StringBuilder()).insert(0, "  ").append(a.getResourceCallStack().get(a)).append(" 行：").append(((GrammarToken)a.getTokenCallStack().get(a)).line);	
                  var10002 = "\n";	
                  ++a;	
                  a.append(var10001.append(var10002).toString());	
               }	
            }	
	
            if (a != null && a.getResourceLoader() instanceof com.jeesite.common.beetl.l.l) {	
               this.logger.error(a.toString(), ex);	
            }	
	
            throw new BeetlException(a.toString(), ex);	
         }	
      }	
   }	
	
   protected String getResourceName(String resourceId) {	
      return StringUtils.abbreviate(resourceId, 1000);	
   }	
	
   protected String getDateTime() {	
      Date a = new Date();	
      return (new SimpleDateFormat("hh:mm:ss")).format(a);	
   }	
}	
