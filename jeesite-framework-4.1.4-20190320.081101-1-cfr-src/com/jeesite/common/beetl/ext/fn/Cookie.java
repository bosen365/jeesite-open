/*	
 * Decompiled with CFR 0.140.	
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
	
import com.jeesite.common.j2cache.autoconfigure.J2CacheSpringRedisAutoConfiguration;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.shiro.realm.LoginInfo;	
import com.jeesite.common.web.CookieUtils;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.beetl.core.Context;	
import org.beetl.core.Function;	
	
public class Cookie	
implements Function {	
    public Object call(Object[] paras, Context ctx) {	
        HttpServletResponse a;	
        String a2 = ObjectUtils.toString(paras.length >= 1 ? paras[0] : null);	
        boolean a3 = ObjectUtils.toBoolean((Object)(paras.length >= 2 ? paras[1] : Boolean.valueOf(false)));	
        String a4 = ObjectUtils.toString(paras.length >= 3 ? paras[2] : null);	
        HttpServletRequest a5 = (HttpServletRequest)ctx.getGlobal("reques");	
        String a6 = CookieUtils.getCookie((HttpServletRequest)a5, (HttpServletResponse)(a = (HttpServletResponse)ctx.getGlobal("response")), (String)a2, (boolean)a3);	
        if (a6 != null) {	
            return a6;	
        }	
        return a4;	
    }	
}	
	
