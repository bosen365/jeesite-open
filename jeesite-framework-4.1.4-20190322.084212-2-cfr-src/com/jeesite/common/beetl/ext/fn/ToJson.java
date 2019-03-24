/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.beetl.ext.fn;	
	
import com.jeesite.common.mapper.JsonMapper;	
import org.beetl.core.Context;	
import org.beetl.core.Function;	
	
public class ToJson	
implements Function {	
    @Override	
    public Object call(Object[] paras, Context ctx) {	
        Object a = paras[0];	
        if (a == null) {	
            return "";	
        }	
        return JsonMapper.toJson(a);	
    }	
}	
	
