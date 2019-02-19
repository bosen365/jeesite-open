package com.jeesite.modules.sys.web;	
	
import com.jeesite.common.codec.EncodeUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.io.FileUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.mapper.SqlMap;	
import com.jeesite.common.web.BaseController;	
import java.io.File;	
import java.io.IOException;	
import javax.servlet.ServletException;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.apache.commons.lang3.math.NumberUtils;	
import org.hyperic.sigar.SysInfo;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;	
import org.springframework.stereotype.Controller;	
import org.springframework.web.bind.annotation.RequestMapping;	
	
@Controller	
@ConditionalOnProperty(	
   name = {"file.isFileStreamDown"},	
   havingValue = "true",	
   matchIfMissing = true	
)	
public class UserfilesController extends BaseController {	
   @RequestMapping({"/userfiles/**"})	
   public String fileStreamDown(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
      String a = StringUtils.substringAfter(request.getRequestURI(), "/userfiles/");	
      String a;	
      String a;	
      if (StringUtils.isNotBlank(a = request.getParameter("preview"))) {	
         String a = request.getRequestURL().toString().replaceAll("(\?|&)preview=([^&]*)(&|$)", "$1").replaceAll("[\?|\&]$", "");	
         a = (new StringBuilder()).insert(0, "/file/").append(a).append("/preview").toString();	
         request.setAttribute("fileUrl", a);	
         request.setAttribute("filePath", a);	
         request.setAttribute("javax.servlet.forward.request_uri", a);	
         request.getRequestDispatcher(a).forward(request, response);	
         return null;	
      } else {	
         a = Global.getUserfilesBaseDir(a);	
         File a;	
         File a;	
         if (!(a = new File(EncodeUtils.decodeUrl(a))).exists() && (a = new File(EncodeUtils.decodeUrl(a, "GBK"))).exists()) {	
            a = a;	
         }	
	
         HttpServletRequest var10000;	
         if (StringUtils.isNotBlank(a = request.getHeader("Range"))) {	
            var10000 = request;	
            this.logger.debug("File: {}  Range: {}", a, a);	
         } else {	
            this.logger.debug("File: {}", a);	
            var10000 = request;	
         }	
	
         String a;	
         if (StringUtils.isBlank(a = var10000.getParameter("fileName"))) {	
            a = a.getName();	
         }	
	
         String a;	
         if (NumberUtils.isCreatable(a = StringUtils.substringAfterLast(FileUtils.getFileNameWithoutExtension(a), "_$"))) {	
            a = StringUtils.replace(a, "_$" + a, "");	
         }	
	
         if (a.exists()) {	
            FileUtils.downFile(a, request, response, a);	
            return null;	
         } else {	
            request.setAttribute("responseStatus", 200);	
            String var10001 = "message";	
            String var10002 = "sys.file.downloadFileNotExist";	
            String[] var10003 = new String[0];	
            boolean var10005 = true;	
            request.setAttribute(var10001, text(var10002, var10003));	
            request.getRequestDispatcher("/error/404").forward(request, response);	
            return null;	
         }	
      }	
   }	
}	
