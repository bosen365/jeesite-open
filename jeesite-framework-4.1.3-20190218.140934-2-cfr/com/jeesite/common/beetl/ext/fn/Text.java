/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.lang.ObjectUtils	
 *  org.beetl.core.Context	
 *  org.beetl.core.Function	
 */	
package com.jeesite.common.beetl.ext.fn;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.lang.ObjectUtils;	
import org.beetl.core.Context;	
import org.beetl.core.Function;	
	
public class Text	
implements Function {	
    public Object call(Object[] paras, Context ctx) {	
        int a2;	
        Object a3 = paras[0];	
        if (a3 == null) {	
            return "";	
        }	
        String a4 = ObjectUtils.toString((Object)a3);	
        String[] a5 = new String[paras.length - 1];	
        int n = a2 = 1;	
        while (n < paras.length) {	
            int n2 = a2 - 1;	
            String string = ObjectUtils.toString((Object)paras[a2]);	
            a5[n2] = string;	
            n = ++a2;	
        }	
        return Global.getText(a4, a5);	
    }	
}	
	
