/*	
 * Decompiled with CFR 0.139.	
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
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.common.mybatis.mapper.query.QueryWhere;	
import java.util.Map;	
import org.beetl.core.Context;	
import org.beetl.core.Function;	
	
public class FromJson	
implements Function {	
    public Object call(Object[] paras, Context ctx) {	
        String a2 = ObjectUtils.toString((Object)paras[0]);	
        if (StringUtils.isBlank((CharSequence)a2)) {	
            return null;	
        }	
        String a3 = ObjectUtils.toString((Object)(paras.length < 2 ? "" : paras[1]));	
        if (StringUtils.inString((String)a3, (String[])new String[]{"", "list"})) {	
            return JsonMapper.fromJsonForMapList((String)a2);	
        }	
        if (StringUtils.equals((CharSequence)a3, (CharSequence)"map")) {	
            return JsonMapper.fromJson((String)a2, Map.class);	
        }	
        try {	
            Class<?> a4 = Class.forName(a3);	
            return JsonMapper.fromJson((String)a2, a4);	
        }	
        catch (ClassNotFoundException a5) {	
            return null;	
        }	
    }	
}	
	
