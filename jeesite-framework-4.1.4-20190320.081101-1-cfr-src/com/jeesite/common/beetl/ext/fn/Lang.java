/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.common.beetl.ext.fn;	
	
import com.jeesite.common.config.Global;	
import org.beetl.core.Context;	
import org.beetl.core.Function;	
	
public class Lang	
implements Function {	
    @Override	
    public Object call(Object[] paras, Context ctx) {	
        return Global.getLang();	
    }	
}	
	
