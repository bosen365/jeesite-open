/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.beetl.ext.fn;	
	
import com.jeesite.common.j2cache.autoconfigure.J2CacheAutoConfiguration;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.web.CookieUtils;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.beetl.core.Context;	
import org.beetl.core.Function;	
import org.hyperic.sigar.FileSystemUsage;	
	
public class Cookie	
implements Function {	
    @Override	
    public Object call(Object[] paras, Context ctx) {	
        HttpServletResponse a;	
        String a2 = ObjectUtils.toString(paras.length >= 1 ? paras[0] : null);	
        boolean a3 = ObjectUtils.toBoolean(paras.length >= 2 ? paras[1] : Boolean.valueOf(false));	
        String a4 = ObjectUtils.toString(paras.length >= 3 ? paras[2] : null);	
        HttpServletRequest a5 = (HttpServletRequest)ctx.getGlobal("request");	
        String a6 = CookieUtils.getCookie(a5, a = (HttpServletResponse)ctx.getGlobal("response"), a2, a3);	
        if (a6 != null) {	
            return a6;	
        }	
        return a4;	
    }	
}	
	
