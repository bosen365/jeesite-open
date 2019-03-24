/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.beetl.ext.fn;	
	
import com.jeesite.autoconfigure.sys.FileAutoConfiguration;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mapper.JsonMapper;	
import com.jeesite.common.mybatis.mapper.query.QueryColumn;	
import java.util.Map;	
import org.beetl.core.Context;	
import org.beetl.core.Function;	
	
public class FromJson	
implements Function {	
    @Override	
    public Object call(Object[] paras, Context ctx) {	
        String a = ObjectUtils.toString(paras[0]);	
        if (StringUtils.isBlank(a)) {	
            return null;	
        }	
        String a2 = ObjectUtils.toString(paras.length < 2 ? "" : paras[1]);	
        String[] arrstring = new String[2];	
        arrstring[0] = "";	
        arrstring[1] = "list";	
        if (StringUtils.inString(a2, arrstring)) {	
            return JsonMapper.fromJsonForMapList(a);	
        }	
        if (StringUtils.equals(a2, "map")) {	
            return JsonMapper.fromJson(a, Map.class);	
        }	
        try {	
            Class<?> a3 = Class.forName(a2);	
            return JsonMapper.fromJson(a, a3);	
        }	
        catch (ClassNotFoundException a4) {	
            return null;	
        }	
    }	
}	
	
