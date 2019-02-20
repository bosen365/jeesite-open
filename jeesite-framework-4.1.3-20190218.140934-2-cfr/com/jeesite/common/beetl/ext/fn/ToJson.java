/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.mapper.JsonMapper	
 *  org.beetl.core.Context	
 *  org.beetl.core.Function	
 */	
package com.jeesite.common.beetl.ext.fn;	
	
import com.jeesite.common.mapper.JsonMapper;	
import org.beetl.core.Context;	
import org.beetl.core.Function;	
	
public class ToJson	
implements Function {	
    public Object call(Object[] paras, Context ctx) {	
        Object a2 = paras[0];	
        if (a2 == null) {	
            return "";	
        }	
        return JsonMapper.toJson((Object)a2);	
    }	
}	
	
