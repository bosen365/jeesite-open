/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.modules.sys.utils;	
	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpSession;	
	
public class ValidCodeUtils {	
    public static final String VALID_CODE = "validCode";	
	
    public static boolean validate(HttpServletRequest request, String validCode) {	
        return ValidCodeUtils.validate(request, VALID_CODE, validCode);	
    }	
	
    public static boolean validate(HttpServletRequest request, String attributeName, String validCode) {	
        return ValidCodeUtils.validate(request, attributeName, validCode, true);	
    }	
	
    public static boolean validate(HttpServletRequest request, String attributeName, String validCode, boolean isClear) {	
        boolean a;	
        String a2 = (String)request.getSession().getAttribute(attributeName);	
        boolean bl = a = validCode != null && validCode.equalsIgnoreCase(a2);	
        if (a && isClear) {	
            request.getSession().removeAttribute(attributeName);	
        }	
        return a;	
    }	
}	
	
