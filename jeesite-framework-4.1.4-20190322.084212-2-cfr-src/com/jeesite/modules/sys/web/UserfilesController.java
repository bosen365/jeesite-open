/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.modules.sys.web;	
	
import com.jeesite.common.codec.EncodeUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.io.FileUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.mapper.query.QueryColumn;	
import com.jeesite.common.web.BaseController;	
import com.jeesite.modules.sys.utils.CorpUtils;	
import java.io.File;	
import java.io.IOException;	
import javax.servlet.RequestDispatcher;	
import javax.servlet.ServletException;	
import javax.servlet.ServletRequest;	
import javax.servlet.ServletResponse;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.apache.commons.lang3.math.NumberUtils;	
import org.slf4j.Logger;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;	
import org.springframework.stereotype.Controller;	
import org.springframework.web.bind.annotation.RequestMapping;	
	
@Controller	
@ConditionalOnProperty(name={"file.isFileStreamDown"}, havingValue="true", matchIfMissing=true)	
public class UserfilesController	
extends BaseController {	
    @RequestMapping(value={"/userfiles/**"})	
    public String fileStreamDown(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
        Object a;	
        HttpServletRequest httpServletRequest;	
        String a2;	
        HttpServletRequest httpServletRequest2 = request;	
        String a3 = StringUtils.substringAfter(httpServletRequest2.getRequestURI(), "/userfiles/");	
        String a4 = httpServletRequest2.getParameter("preview");	
        if (StringUtils.isNotBlank(a4)) {	
            HttpServletRequest httpServletRequest3 = request;	
            String a5 = httpServletRequest3.getRequestURL().toString().replaceAll("(\\?|&)preview=([^&]*)(&|$)", "$1").replaceAll("[\\?|\\&]$", "");	
            String a6 = new StringBuilder().insert(0, "/file/").append(a4).append("/preview").toString();	
            httpServletRequest3.setAttribute("fileUrl", a5);	
            httpServletRequest3.setAttribute("filePath", a3);	
            httpServletRequest3.setAttribute("javax.servlet.forward.request_uri", a6);	
            httpServletRequest3.getRequestDispatcher(a6).forward(request, response);	
            return null;	
        }	
        Object a7 = new File(EncodeUtils.decodeUrl(a3 = Global.getUserfilesBaseDir(a3)));	
        if (!((File)a7).exists() && ((File)(a = new File(EncodeUtils.decodeUrl(a3, "GBK")))).exists()) {	
            a7 = a;	
        }	
        if (StringUtils.isNotBlank((CharSequence)(a = request.getHeader("Range")))) {	
            httpServletRequest = request;	
            this.logger.debug("File: {}  Range: {}", a7, a);	
        } else {	
            this.logger.debug("File: {}", a7);	
            httpServletRequest = request;	
        }	
        String a8 = httpServletRequest.getParameter("fileName");	
        if (StringUtils.isBlank(a8)) {	
            a8 = ((File)a7).getName();	
        }	
        if (NumberUtils.isCreatable(a2 = StringUtils.substringAfterLast(FileUtils.getFileNameWithoutExtension(a8), "_$"))) {	
            a8 = StringUtils.replace(a8, "_$" + a2, "");	
        }	
        if (((File)a7).exists()) {	
            FileUtils.downFile((File)a7, request, response, a8);	
            return null;	
        }	
        request.setAttribute("responseStatus", 200);	
        request.setAttribute("message", UserfilesController.text("sys.file.downloadFileNotExist", new String[0]));	
        request.getRequestDispatcher("/error/404").forward(request, response);	
        return null;	
    }	
}	
	
