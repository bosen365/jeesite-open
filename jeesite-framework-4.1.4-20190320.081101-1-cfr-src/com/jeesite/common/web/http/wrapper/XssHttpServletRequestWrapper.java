/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.codec.EncodeUtils	
 *  javax.servlet.http.HttpServletRequest	
 *  javax.servlet.http.HttpServletRequestWrapper	
 */	
package com.jeesite.common.web.http.wrapper;	
	
import com.jeesite.common.codec.EncodeUtils;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletRequestWrapper;	
	
public class XssHttpServletRequestWrapper	
extends HttpServletRequestWrapper {	
    public String getQueryString() {	
        return super.getQueryString();	
    }	
	
    public String getParameter(String name) {	
        return EncodeUtils.xssFilter((String)super.getParameter(name));	
    }	
	
    public String[] getParameterValues(String name) {	
        String[] a = super.getParameterValues(name);	
        if (a != null) {	
            int a2;	
            int a3 = a.length;	
            String[] a4 = new String[a3];	
            int n = a2 = 0;	
            while (n < a3) {	
                int n2 = a2++;	
                a4[n2] = EncodeUtils.xssFilter((String)a[n2]);	
                n = a2;	
            }	
            return a4;	
        }	
        return a;	
    }	
	
    public XssHttpServletRequestWrapper(HttpServletRequest request) {	
        super(request);	
    }	
	
    public String getHeader(String name) {	
        return super.getHeader(name);	
    }	
}	
	
