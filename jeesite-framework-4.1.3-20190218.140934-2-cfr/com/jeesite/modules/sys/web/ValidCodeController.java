/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.image.CaptchaUtils	
 *  javax.servlet.ServletException	
 *  javax.servlet.ServletOutputStream	
 *  javax.servlet.http.HttpServletRequest	
 *  javax.servlet.http.HttpServletResponse	
 *  javax.servlet.http.HttpSession	
 *  org.apache.commons.lang3.StringUtils	
 *  org.springframework.stereotype.Controller	
 *  org.springframework.web.bind.annotation.RequestMapping	
 */	
package com.jeesite.modules.sys.web;	
	
import com.jeesite.common.image.CaptchaUtils;	
import com.jeesite.common.mybatis.mapper.query.QueryWhere;	
import com.jeesite.modules.sys.utils.ValidCodeUtils;	
import java.io.IOException;	
import java.io.OutputStream;	
import javax.servlet.ServletException;	
import javax.servlet.ServletOutputStream;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import javax.servlet.http.HttpSession;	
import org.apache.commons.lang3.StringUtils;	
import org.hyperic.sigar.pager.SortAttribute;	
import org.springframework.stereotype.Controller;	
import org.springframework.web.bind.annotation.RequestMapping;	
	
@Controller	
public class ValidCodeController {	
    @RequestMapping(value={"/validCode"})	
    public void validCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
        String a2 = request.getParameter("validCode");	
        if (StringUtils.isNotBlank((CharSequence)a2)) {	
            boolean a3;	
            response.getOutputStream().print((a3 = ValidCodeUtils.validate(request, "validCode", a2, false)) ? "true" : "false");	
            return;	
        }	
        HttpServletResponse httpServletResponse = response;	
        HttpServletResponse httpServletResponse2 = response;	
        response.setContentType("image/png");	
        httpServletResponse2.setHeader("Cache-Control", "no-cache, no-store");	
        httpServletResponse.setHeader("Pragma", "no-cache");	
        long a4 = System.currentTimeMillis();	
        httpServletResponse2.setDateHeader("Last-Modified", a4);	
        httpServletResponse.setDateHeader("Date", a4);	
        httpServletResponse.setDateHeader("Expires", a4);	
        String a5 = CaptchaUtils.generateCaptcha((OutputStream)httpServletResponse.getOutputStream());	
        request.getSession().setAttribute("validCode", (Object)a5);	
    }	
}	
	
