/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  javax.servlet.http.HttpServletRequest	
 *  javax.servlet.http.HttpSession	
 */	
package com.jeesite.modules.sys.utils;	
	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpSession;	
	
public class ValidCodeUtils {	
    public static final String VALID_CODE = "validCode";	
	
    public static boolean validate(HttpServletRequest request, String validCode) {	
        return ValidCodeUtils.validate(request, VALID_CODE, validCode);	
    }	
	
    public static boolean validate(HttpServletRequest request, String attributeName, String validCode, boolean isClear) {	
        boolean a2;	
        String a3 = (String)request.getSession().getAttribute(attributeName);	
        boolean bl = a2 = validCode != null && validCode.equalsIgnoreCase(a3);	
        if (a2 && isClear) {	
            request.getSession().removeAttribute(attributeName);	
        }	
        return a2;	
    }	
	
    public static boolean validate(HttpServletRequest request, String attributeName, String validCode) {	
        return ValidCodeUtils.validate(request, attributeName, validCode, true);	
    }	
	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = (3 ^ 5) << 4 ^ 1;	
        int n4 = n2;	
        5 << 3;	
        int n5 = 4 << 4 ^ 5 << 1;	
        while (n4 >= 0) {	
            int n6 = n2--;	
            arrc[n6] = (char)(s.charAt(n6) ^ n5);	
            if (n2 < 0) break;	
            int n7 = n2--;	
            arrc[n7] = (char)(s.charAt(n7) ^ n3);	
            n4 = n2;	
        }	
        return new String(arrc);	
    }	
}	
	
