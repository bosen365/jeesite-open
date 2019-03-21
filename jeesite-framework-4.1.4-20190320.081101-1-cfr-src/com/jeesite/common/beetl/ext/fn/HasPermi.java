/*	
 * Decompiled with CFR 0.140.	
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
import com.jeesite.common.mybatis.mapper.query.QueryWhereEntity;	
import com.jeesite.modules.sys.utils.UserUtils;	
import org.apache.commons.lang3.BooleanUtils;	
import org.beetl.core.Context;	
import org.beetl.core.Function;	
import org.hyperic.sigar.Tcp;	
	
public class HasPermi	
implements Function {	
    public Object call(Object[] paras, Context ctx) {	
        String a = ObjectUtils.toString((Object)paras[0]);	
        if (StringUtils.isBlank((CharSequence)a)) {	
            return false;	
        }	
        String a2 = "and";	
        if (paras.length >= 2) {	
            a2 = ObjectUtils.toString((Object)paras[1]);	
        }	
        boolean[] a3 = UserUtils.getSubject().isPermitted(StringUtils.split((String)a, (String)","));	
        if ("or".equalsIgnoreCase(a2)) {	
            return BooleanUtils.or((boolean[])a3);	
        }	
        return BooleanUtils.and((boolean[])a3);	
    }	
}	
	
