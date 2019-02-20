/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.lang.StringUtils	
 *  org.beetl.core.Context	
 *  org.beetl.core.Function	
 *  org.beetl.ext.fn.EmptyExpressionFunction	
 */	
package com.jeesite.common.beetl.ext.fn;	
	
import com.jeesite.common.lang.StringUtils;	
import org.beetl.core.Context;	
import org.beetl.core.Function;	
import org.beetl.ext.fn.EmptyExpressionFunction;	
	
public class IsBlank	
implements Function {	
    EmptyExpressionFunction fn;	
	
    public IsBlank() {	
        IsBlank isBlank = this;	
        isBlank.fn = new EmptyExpressionFunction();	
    }	
	
    public Object call(Object[] paras, Context ctx) {	
        Object a2 = paras[0];	
        if (a2 == null) {	
            return true;	
        }	
        if (a2 instanceof String) {	
            return StringUtils.isBlank((CharSequence)((String)a2));	
        }	
        return this.fn.call(paras, ctx);	
    }	
}	
	
