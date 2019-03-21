/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.common.beetl.ext.fn;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.lang.ObjectUtils;	
import org.beetl.core.Context;	
import org.beetl.core.Function;	
	
public class Text	
implements Function {	
    @Override	
    public Object call(Object[] paras, Context ctx) {	
        int a;	
        Object a2 = paras[0];	
        if (a2 == null) {	
            return "";	
        }	
        String a3 = ObjectUtils.toString(a2);	
        String[] a4 = new String[paras.length - 1];	
        int n = a = 1;	
        while (n < paras.length) {	
            int n2 = a - 1;	
            String string = ObjectUtils.toString(paras[a]);	
            a4[n2] = string;	
            n = ++a;	
        }	
        return Global.getText(a3, a4);	
    }	
}	
	
