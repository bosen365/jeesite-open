/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.lang.ObjectUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  com.jeesite.common.mapper.JsonMapper	
 *  org.beetl.core.Context	
 *  org.beetl.core.Function	
 */	
package com.jeesite.common.beetl.ext.fn;	
	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mapper.JsonMapper;	
import com.jeesite.common.shiro.cas.CasOutHandler;	
import java.util.Map;	
import org.beetl.core.Context;	
import org.beetl.core.Function;	
import org.hyperic.sigar.NfsServerV2;	
	
public class FromJson	
implements Function {	
    public Object call(Object[] paras, Context ctx) {	
        String a = ObjectUtils.toString((Object)paras[0]);	
        if (StringUtils.isBlank((CharSequence)a)) {	
            return null;	
        }	
        String a2 = ObjectUtils.toString((Object)(paras.length < 2 ? "" : paras[1]));	
        if (StringUtils.inString((String)a2, (String[])new String[]{"", "list"})) {	
            return JsonMapper.fromJsonForMapList((String)a);	
        }	
        if (StringUtils.equals((CharSequence)a2, (CharSequence)"map")) {	
            return JsonMapper.fromJson((String)a, Map.class);	
        }	
        try {	
            Class<?> a3 = Class.forName(a2);	
            return JsonMapper.fromJson((String)a, a3);	
        }	
        catch (ClassNotFoundException a4) {	
            return null;	
        }	
    }	
}	
	
