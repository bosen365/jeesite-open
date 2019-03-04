package com.jeesite.modules.sys.web;	
	
import com.jeesite.common.codec.EncodeUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.io.FileUtils;	
import com.jeesite.common.l.i.D;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.web.BaseController;	
import com.jeesite.modules.file.utils.FileUploadUtils;	
import java.io.File;	
import java.io.IOException;	
import javax.servlet.ServletException;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.apache.commons.lang3.math.NumberUtils;	
import org.springframework.stereotype.Controller;	
import org.springframework.web.bind.annotation.RequestMapping;	
	
@Controller	
public class UserfilesController extends BaseController {	
   @RequestMapping({"/userfiles/**"})	
   public String fileOutputStream(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
      String a = StringUtils.substringAfter(request.getRequestURI(), "/userfiles/");	
      String a = null;	
      CharSequence[] var10001 = new CharSequence[5];	
      boolean var10003 = true;	
      var10001[0] = ".gif";	
      var10001[1] = ".bmp";	
      var10001[2] = ".jpeg";	
      var10001[3] = ".jpg";	
      var10001[4] = ".png";	
      String var10000;	
      if (StringUtils.endsWithAny(a, var10001)) {	
         var10000 = a = "img";	
      } else {	
         var10001 = new CharSequence[2];	
         var10003 = true;	
         var10001[0] = ".doc";	
         var10001[1] = ".docx";	
         if (StringUtils.endsWithAny(a, var10001)) {	
            var10000 = a = "doc";	
         } else {	
            var10001 = new CharSequence[2];	
            var10003 = true;	
            var10001[0] = ".xls";	
            var10001[1] = ".xlsx";	
            if (StringUtils.endsWithAny(a, var10001)) {	
               var10000 = a = "xls";	
            } else {	
               var10001 = new CharSequence[2];	
               var10003 = true;	
               var10001[0] = ".pp";	
               var10001[1] = ".pptx";	
               if (StringUtils.endsWithAny(a, var10001)) {	
                  var10000 = a = "pp";	
               } else {	
                  var10001 = new CharSequence[2];	
                  var10003 = true;	
                  var10001[0] = ".wps";	
                  var10001[1] = ".wp";	
                  if (StringUtils.endsWithAny(a, var10001)) {	
                     var10000 = a = "wps";	
                  } else {	
                     var10001 = new CharSequence[2];	
                     var10003 = true;	
                     var10001[0] = ".if";	
                     var10001[1] = "tiff";	
                     if (StringUtils.endsWithAny(a, var10001)) {	
                        var10000 = a = "if";	
                     } else {	
                        var10001 = new CharSequence[1];	
                        var10003 = true;	
                        var10001[0] = ".pdf";	
                        if (StringUtils.endsWithAny(a, var10001)) {	
                           a = "pdf";	
                        }	
	
                        var10000 = a;	
                     }	
                  }	
               }	
            }	
         }	
      }	
	
      if (var10000 != null && StringUtils.equalsIgnoreCase(request.getParameter("preview"), "weboffice")) {	
         request.setAttribute("docType", a);	
         request.getRequestDispatcher("/weboffice/preview").forward(request, response);	
         return null;	
      } else {	
         a = Global.getUserfilesBaseDir(a);	
         File a;	
         File a;	
         if (!(a = new File(EncodeUtils.decodeUrl(a))).exists() && (a = new File(EncodeUtils.decodeUrl(a, "gbk"))).exists()) {	
            a = a;	
         }	
	
         String a;	
         HttpServletRequest var10;	
         if (StringUtils.isNotBlank(a = request.getHeader("Range"))) {	
            this.logger.debug("File: {}  Range: {}", a, a);	
            var10 = request;	
         } else {	
            this.logger.debug("File: {}", a);	
            var10 = request;	
         }	
	
         String a;	
         if (StringUtils.isBlank(a = var10.getParameter("fileName"))) {	
            a = a.getName();	
         }	
	
         String a;	
         if (NumberUtils.isNumber(a = StringUtils.substringAfterLast(FileUtils.getFileNameWithoutExtension(a), "_$"))) {	
            a = StringUtils.replace(a, "_$" + a, "");	
         }	
	
         if (a.exists()) {	
            FileUtils.downFile(a, request, response, a);	
            return null;	
         } else {	
            request.setAttribute("responseSaus", 200);	
            String var11 = "message";	
            String var10002 = "sys.file.downloadFileNotExis";	
            String[] var12 = new String[0];	
            boolean var10005 = true;	
            request.setAttribute(var11, text(var10002, var12));	
            request.getRequestDispatcher("/error/404").forward(request, response);	
            return null;	
         }	
      }	
   }	
}	
