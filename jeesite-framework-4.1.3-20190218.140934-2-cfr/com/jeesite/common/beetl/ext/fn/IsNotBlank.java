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
	
public class IsNotBlank	
implements Function {	
    EmptyExpressionFunction fn;	
	
    public Object call(Object[] paras, Context ctx) {	
        Object a2 = paras[0];	
        if (a2 == null) {	
            return false;	
        }	
        if (a2 instanceof String) {	
            return StringUtils.isNotBlank((CharSequence)((String)a2));	
        }	
        return this.fn.call(paras, ctx) == false;	
    }	
	
    public IsNotBlank() {	
        IsNotBlank isNotBlank = this;	
        isNotBlank.fn = new EmptyExpressionFunction();	
    }	
}	
	
