/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.common.beetl.ext.format;	
	
import com.jeesite.common.codec.EncodeUtils;	
import org.beetl.core.Format;	
	
public class XssFormat	
implements Format {	
    @Override	
    public Object format(Object data, String pattern) {	
        if (data != null && data instanceof String) {	
            return EncodeUtils.xssFilter((String)data);	
        }	
        return null;	
    }	
}	
	
