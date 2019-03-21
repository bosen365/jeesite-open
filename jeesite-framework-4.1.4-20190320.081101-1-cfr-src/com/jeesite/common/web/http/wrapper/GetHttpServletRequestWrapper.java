/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  javax.servlet.ServletRequest	
 *  javax.servlet.http.HttpServletRequest	
 *  javax.servlet.http.HttpServletRequestWrapper	
 */	
package com.jeesite.common.web.http.wrapper;	
	
import com.jeesite.autoconfigure.sys.MsgAutoConfiguration;	
import javax.servlet.ServletRequest;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletRequestWrapper;	
	
public class GetHttpServletRequestWrapper	
extends HttpServletRequestWrapper {	
    public String getMethod() {	
        return "GET";	
    }	
	
    public GetHttpServletRequestWrapper(HttpServletRequest request) {	
        super(request);	
    }	
	
    public GetHttpServletRequestWrapper(ServletRequest request) {	
        super((HttpServletRequest)request);	
    }	
}	
	
