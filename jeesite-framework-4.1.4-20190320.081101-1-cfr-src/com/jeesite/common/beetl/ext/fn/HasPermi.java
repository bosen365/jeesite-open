/*	
 * Decompiled with CFR 0.140.	
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
    @Override	
    public Object call(Object[] paras, Context ctx) {	
        String a = ObjectUtils.toString(paras[0]);	
        if (StringUtils.isBlank(a)) {	
            return false;	
        }	
        String a2 = "and";	
        if (paras.length >= 2) {	
            a2 = ObjectUtils.toString(paras[1]);	
        }	
        boolean[] a3 = UserUtils.getSubject().isPermitted(StringUtils.split(a, ","));	
        if ("or".equalsIgnoreCase(a2)) {	
            return BooleanUtils.or(a3);	
        }	
        return BooleanUtils.and(a3);	
    }	
}	
	
