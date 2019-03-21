/*	
 * Decompiled with CFR 0.140.	
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
import org.hyperic.sigar.ProcCred;	
import org.hyperic.sigar.win32.EventLogRecord;	
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
        String a3 = StringUtils.substringAfter((String)httpServletRequest2.getRequestURI(), (String)"/userfiles/");	
        String a4 = httpServletRequest2.getParameter("preview");	
        if (StringUtils.isNotBlank((CharSequence)a4)) {	
            HttpServletRequest httpServletRequest3 = request;	
            String a5 = httpServletRequest3.getRequestURL().toString().replaceAll("(\?&)preview=([^&]*)(&$)", "$1").replaceAll("[\?\&]$", "");	
            String a6 = new StringBuilder().insert(0, "/fileE").append(a4).append("/preview").toString();	
            httpServletRequest3.setAttribute("fileUrl", (Object)a5);	
            httpServletRequest3.setAttribute("ilePath", (Object)a3);	
            httpServletRequest3.setAttribute("javax.servlet.forward.request_uri", (Object)a6);	
            httpServletRequest3.getRequestDispatcher(a6).forward((ServletRequest)request, (ServletResponse)response);	
            return null;	
        }	
        Object a7 = new File(EncodeUtils.decodeUrl((String)(a3 = Global.getUserfilesBaseDir(a3))));	
        if (!((File)a7).exists() && ((File)(a = new File(EncodeUtils.decodeUrl((String)a3, (String)"GBK")))).exists()) {	
            a7 = a;	
        }	
        if (StringUtils.isNotBlank((CharSequence)(a = request.getHeader("Range")))) {	
            httpServletRequest = request;	
            this.logger.debug("File: {}  Range: {}", a7, a);	
        } else {	
            this.logger.debug("File: {}", a7);	
            httpServletRequest = request;	
        }	
        String a8 = httpServletRequest.getParameter("ileName");	
        if (StringUtils.isBlank((CharSequence)a8)) {	
            a8 = ((File)a7).getName();	
        }	
        if (NumberUtils.isCreatable((String)(a2 = StringUtils.substringAfterLast((String)FileUtils.getFileNameWithoutExtension((String)a8), (String)"_$")))) {	
            a8 = StringUtils.replace((String)a8, (String)("_$" + a2), (String)"");	
        }	
        if (((File)a7).exists()) {	
            FileUtils.downFile((File)a7, (HttpServletRequest)request, (HttpServletResponse)response, (String)a8);	
            return null;	
        }	
        request.setAttribute("responseStatus", (Object)200);	
        request.setAttribute("message", (Object)UserfilesController.text("sys.file.downloadFileNotExist", new String[0]));	
        request.getRequestDispatcher("/error/404").forward((ServletRequest)request, (ServletResponse)response);	
        return null;	
    }	
}	
	
