/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.lang.ObjectUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  org.apache.commons.lang3.BooleanUtils	
 *  org.beetl.core.Context	
 *  org.beetl.core.Function	
 */	
package com.jeesite.common.beetl.ext.fn;	
	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.modules.sys.utils.UserUtils;	
import com.jeesite.modules.sys.utils.ValidCodeUtils;	
import org.apache.commons.lang3.BooleanUtils;	
import org.beetl.core.Context;	
import org.beetl.core.Function;	
import org.hyperic.sigar.FileWatcher;	
	
public class HasPermi	
implements Function {	
    public Object call(Object[] paras, Context ctx) {	
        String a2 = ObjectUtils.toString((Object)paras[0]);	
        if (StringUtils.isBlank((CharSequence)a2)) {	
            return false;	
        }	
        String a3 = "and";	
        if (paras.length >= 2) {	
            a3 = ObjectUtils.toString((Object)paras[1]);	
        }	
        boolean[] a4 = UserUtils.getSubject().isPermitted(StringUtils.split((String)a2, (String)","));	
        if ("or".equalsIgnoreCase(a3)) {	
            return BooleanUtils.or((boolean[])a4);	
        }	
        return BooleanUtils.and((boolean[])a4);	
    }	
}	
	
