/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.modules.sys.web;	
	
import com.jeesite.common.image.CaptchaUtils;	
import com.jeesite.common.mybatis.mapper.query.QueryWhereEntity;	
import com.jeesite.modules.sys.utils.ValidCodeUtils;	
import java.io.IOException;	
import javax.servlet.ServletException;	
import javax.servlet.ServletOutputStream;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import javax.servlet.http.HttpSession;	
import org.apache.commons.lang3.StringUtils;	
import org.hyperic.sigar.ProcState;	
import org.springframework.stereotype.Controller;	
import org.springframework.web.bind.annotation.RequestMapping;	
	
@Controller	
public class ValidCodeController {	
    @RequestMapping(value={"/validCode"})	
    public void validCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
        String a = request.getParameter("validCode");	
        if (StringUtils.isNotBlank(a)) {	
            boolean a2;	
            response.getOutputStream().print((a2 = ValidCodeUtils.validate(request, "validCode", a, false)) ? "true" : "false");	
            return;	
        }	
        HttpServletResponse httpServletResponse = response;	
        HttpServletResponse httpServletResponse2 = response;	
        response.setContentType("image/png");	
        httpServletResponse2.setHeader("Cache-Control", "no-cache, no-store");	
        httpServletResponse.setHeader("Pragma", "no-cache");	
        long a3 = System.currentTimeMillis();	
        httpServletResponse2.setDateHeader("Last-Modified", a3);	
        httpServletResponse.setDateHeader("Date", a3);	
        httpServletResponse.setDateHeader("Expires", a3);	
        String a4 = CaptchaUtils.generateCaptcha(httpServletResponse.getOutputStream());	
        request.getSession().setAttribute("validCode", a4);	
    }	
}	
	
