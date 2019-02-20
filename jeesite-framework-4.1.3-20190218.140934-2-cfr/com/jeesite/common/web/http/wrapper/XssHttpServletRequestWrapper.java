/*	
 * Decompiled with CFR 0.139.	
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
	
    public String getHeader(String name) {	
        return super.getHeader(name);	
    }	
	
    public XssHttpServletRequestWrapper(HttpServletRequest request) {	
        super(request);	
    }	
	
    public String[] getParameterValues(String name) {	
        String[] a2 = super.getParameterValues(name);	
        if (a2 != null) {	
            int a3;	
            int a4 = a2.length;	
            String[] a5 = new String[a4];	
            int n = a3 = 0;	
            while (n < a4) {	
                int n2 = a3++;	
                a5[n2] = EncodeUtils.xssFilter((String)a2[n2]);	
                n = a3;	
            }	
            return a5;	
        }	
        return a2;	
    }	
}	
	
