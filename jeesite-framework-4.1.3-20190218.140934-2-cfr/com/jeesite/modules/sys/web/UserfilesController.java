/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.codec.EncodeUtils	
 *  com.jeesite.common.io.FileUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  javax.servlet.RequestDispatcher	
 *  javax.servlet.ServletException	
 *  javax.servlet.ServletRequest	
 *  javax.servlet.ServletResponse	
 *  javax.servlet.http.HttpServletRequest	
 *  javax.servlet.http.HttpServletResponse	
 *  org.apache.commons.lang3.math.NumberUtils	
 *  org.slf4j.Logger	
 *  org.springframework.boot.autoconfigure.condition.ConditionalOnProperty	
 *  org.springframework.stereotype.Controller	
 *  org.springframework.web.bind.annotation.RequestMapping	
 */	
package com.jeesite.modules.sys.web;	
	
import com.jeesite.common.codec.EncodeUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.io.FileUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.mapper.SqlMap;	
import com.jeesite.common.web.BaseController;	
import java.io.File;	
import java.io.IOException;	
import javax.servlet.RequestDispatcher;	
import javax.servlet.ServletException;	
import javax.servlet.ServletRequest;	
import javax.servlet.ServletResponse;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.apache.commons.lang3.math.NumberUtils;	
import org.hyperic.sigar.SysInfo;	
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
        Object a2;	
        HttpServletRequest httpServletRequest;	
        String a3;	
        HttpServletRequest httpServletRequest2 = request;	
        String a4 = StringUtils.substringAfter((String)httpServletRequest2.getRequestURI(), (String)"/userfiles/");	
        String a5 = httpServletRequest2.getParameter("preview");	
        if (StringUtils.isNotBlank((CharSequence)a5)) {	
            HttpServletRequest httpServletRequest3 = request;	
            String a6 = httpServletRequest3.getRequestURL().toString().replaceAll("(\?|&)preview=([^&]*)(&|$)", "$1").replaceAll("[\?|\&]$", "");	
            String a7 = new StringBuilder().insert(0, "/file/").append(a5).append("/preview").toString();	
            httpServletRequest3.setAttribute("fileUrl", (Object)a6);	
            httpServletRequest3.setAttribute("filePath", (Object)a4);	
            httpServletRequest3.setAttribute("javax.servlet.forward.request_uri", (Object)a7);	
            httpServletRequest3.getRequestDispatcher(a7).forward((ServletRequest)request, (ServletResponse)response);	
            return null;	
        }	
        Object a8 = new File(EncodeUtils.decodeUrl((String)(a4 = Global.getUserfilesBaseDir(a4))));	
        if (!((File)a8).exists() && ((File)(a2 = new File(EncodeUtils.decodeUrl((String)a4, (String)"GBK")))).exists()) {	
            a8 = a2;	
        }	
        if (StringUtils.isNotBlank((CharSequence)(a2 = request.getHeader("Range")))) {	
            httpServletRequest = request;	
            this.logger.debug("File: {}  Range: {}", a8, a2);	
        } else {	
            this.logger.debug("File: {}", a8);	
            httpServletRequest = request;	
        }	
        String a9 = httpServletRequest.getParameter("fileName");	
        if (StringUtils.isBlank((CharSequence)a9)) {	
            a9 = ((File)a8).getName();	
        }	
        if (NumberUtils.isCreatable((String)(a3 = StringUtils.substringAfterLast((String)FileUtils.getFileNameWithoutExtension((String)a9), (String)"_$")))) {	
            a9 = StringUtils.replace((String)a9, (String)("_$" + a3), (String)"");	
        }	
        if (((File)a8).exists()) {	
            FileUtils.downFile((File)a8, (HttpServletRequest)request, (HttpServletResponse)response, (String)a9);	
            return null;	
        }	
        request.setAttribute("responseStatus", (Object)200);	
        request.setAttribute("message", (Object)UserfilesController.text("sys.file.downloadFileNotExist", new String[0]));	
        request.getRequestDispatcher("/error/404").forward((ServletRequest)request, (ServletResponse)response);	
        return null;	
    }	
}	
	
