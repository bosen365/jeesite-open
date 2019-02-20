/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.codec.EncodeUtils	
 *  org.beetl.core.Format	
 */	
package com.jeesite.common.beetl.ext.format;	
	
import com.jeesite.common.codec.EncodeUtils;	
import org.beetl.core.Format;	
	
public class XssFormat	
implements Format {	
    public Object format(Object data, String pattern) {	
        if (data != null && data instanceof String) {	
            return EncodeUtils.xssFilter((String)((String)data));	
        }	
        return null;	
    }	
}	
	
