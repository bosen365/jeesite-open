/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  org.beetl.core.Context	
 *  org.beetl.core.Function	
 */	
package com.jeesite.common.beetl.ext.fn;	
	
import com.jeesite.common.config.Global;	
import org.beetl.core.Context;	
import org.beetl.core.Function;	
	
public class Lang	
implements Function {	
    public Object call(Object[] paras, Context ctx) {	
        return Global.getLang();	
    }	
}	
	
