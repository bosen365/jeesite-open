/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.lang.ObjectUtils	
 *  com.jeesite.common.web.CookieUtils	
 *  javax.servlet.http.HttpServletRequest	
 *  javax.servlet.http.HttpServletResponse	
 *  org.beetl.core.Context	
 *  org.beetl.core.Function	
 */	
package com.jeesite.common.beetl.ext.fn;	
	
import com.jeesite.autoconfigure.core.DataSourceAutoConfiguration;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.common.web.CookieUtils;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.beetl.core.Context;	
import org.beetl.core.Function;	
	
public class Cookie	
implements Function {	
    public Object call(Object[] paras, Context ctx) {	
        HttpServletResponse a2;	
        String a3 = ObjectUtils.toString(paras.length >= 1 ? paras[0] : null);	
        boolean a4 = ObjectUtils.toBoolean((Object)(paras.length >= 2 ? paras[1] : Boolean.valueOf(false)));	
        String a5 = ObjectUtils.toString(paras.length >= 3 ? paras[2] : null);	
        HttpServletRequest a6 = (HttpServletRequest)ctx.getGlobal("request");	
        String a7 = CookieUtils.getCookie((HttpServletRequest)a6, (HttpServletResponse)(a2 = (HttpServletResponse)ctx.getGlobal("response")), (String)a3, (boolean)a4);	
        if (a7 != null) {	
            return a7;	
        }	
        return a5;	
    }	
}	
	
