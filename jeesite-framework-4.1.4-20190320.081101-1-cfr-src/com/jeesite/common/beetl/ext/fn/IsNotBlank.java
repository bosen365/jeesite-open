/*	
 * Decompiled with CFR 0.140.	
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
        Object a = paras[0];	
        if (a == null) {	
            return false;	
        }	
        if (a instanceof String) {	
            return StringUtils.isNotBlank((CharSequence)((String)a));	
        }	
        return this.fn.call(paras, ctx) == false;	
    }	
	
    public IsNotBlank() {	
        IsNotBlank isNotBlank = this;	
        isNotBlank.fn = new EmptyExpressionFunction();	
    }	
}	
	
