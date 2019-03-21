/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.common.web.http.wrapper;	
	
import com.jeesite.autoconfigure.sys.MsgAutoConfiguration;	
import javax.servlet.ServletRequest;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletRequestWrapper;	
	
public class GetHttpServletRequestWrapper	
extends HttpServletRequestWrapper {	
    @Override	
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
	
